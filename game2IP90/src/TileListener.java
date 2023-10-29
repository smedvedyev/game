import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TileListener extends MouseAdapter {
    States state = States.IDLE_P1;
    States prevState = States.IDLE_P1;
    Tile selectedTile;
    Tile actionTile;
    PopUp popup;
    Troop currentTroop;
    Field field;

    public TileListener(Field field) {
        state = States.IDLE_P1;
        this.field = field;
    }

    public boolean checkRange(Troop t, Tile destinationTile, String action) {
        boolean res = false;
        int differenceX = (int) destinationTile.d.getX() - (int) t.location.getX();
        int differenceY = (int) destinationTile.d.getY() - (int) t.location.getY();
        switch (action) {
            case "walk":
                if (Math.abs(differenceX) <= t.walkRange && Math.abs(differenceY) <= t.walkRange) {
                    // within x and y walking range
                    if (Math.abs(differenceX) == Math.abs(differenceY)) {
                        if (destinationTile.troop == null && destinationTile.object == null) {
                            try {
                                field.checkObstacle(t.location, destinationTile.d, differenceX, differenceY);
                                res = true;
                            }

                            catch (ObstacleException oe) {
                                field.changeInfoLabel(oe.getMessage());

                            }
                            // target destination is empty
                        }
                    } else if (Math.abs(differenceX) != 0 ^ Math.abs(differenceY) != 0) {
                        if (destinationTile.troop == null && destinationTile.object == null) {
                            try {
                                field.checkObstacle(t.location, destinationTile.d, differenceX, differenceY);
                                res = true;
                            }

                            catch (ObstacleException oe) {
                                field.changeInfoLabel(oe.getMessage());

                            }
                        }
                    }
                }
                break;
            case "shoot":
                if (destinationTile.troop != null) {
                    // target is not empty
                    if (t.shoot(Math.abs(differenceX), Math.abs(differenceY))) {
                        System.out.println(differenceX + ", " + differenceY);
                        if ((Math.abs(differenceX) > 1 || Math.abs(differenceY) > 1) && !(t instanceof Lobber)) {
                            System.out.println("diff");
                            try {
                                field.checkObstacle(t.location, destinationTile.d, differenceX, differenceY);
                                res = true;

                            } catch (ObstacleException oe) {
                                field.changeInfoLabel(oe.getMessage());
                            }

                        } else {
                            res = true;
                        }
                    }
                }
                break;
            default:
                break;
        }
        return res;

    }

    public void changeState(String action) {
        switch (action) {
            case "walk":
                if (state == States.POPUP_P1) {
                    state = States.WALK_P1;
                } else if (state == States.POPUP_P2) {
                    state = States.WALK_P2;
                }
                break;
            case "shoot":
                if (state == States.POPUP_P1) {
                    state = States.SHOOT_P1;
                } else if (state == States.POPUP_P2) {
                    state = States.SHOOT_P2;
                }
                break;
            case "popup":
                if (state == States.IDLE_P1) {
                    popup = new PopUp(field, (int) selectedTile.d.getX(), (int) selectedTile.d.getY(), this);
                    // currentTroop = selectedTile.troop;
                    state = States.POPUP_P1;
                } else if (state == States.IDLE_P2) {
                    popup = new PopUp(field, (int) selectedTile.d.getX(), (int) selectedTile.d.getY(), this);
                    state = States.POPUP_P2;
                }
            default:
                break;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        switch (state) {
            case IDLE_P1:
                selectedTile = (Tile) e.getSource();
                // System.out.println("("+(int)selectedTile.d.getX() +","+
                // (int)selectedTile.d.getY()+")");

                if (selectedTile.troop != null && selectedTile.troop.player == 1) {
                    // popup = new PopUp(field, (int)selectedTile.d.getX(),
                    // (int)selectedTile.d.getY(), this);
                    currentTroop = selectedTile.troop;
                    // state = States.POPUP_P1;
                    changeState("popup");
                } else {
                    field.changeInfoLabel("Choose your troop P1");
                }
                break;

            case POPUP_P1:

                Tile newClick1 = (Tile) e.getSource();
                if (newClick1 != selectedTile) {
                    popup.hidePop();
                    state = States.IDLE_P1;
                }
                break;

            case SHOOT_P1:
                actionTile = (Tile) e.getSource();
                if (checkRange(currentTroop, actionTile, "shoot")) {
                    if (field.eliminateTroop(actionTile.d)) {
                        prevState = state;
                        state = States.END_OF_THE_GAME;
                    } else {
                        field.changeInfoLabel("Turn P2");
                        state = States.IDLE_P2;
                    }
                } else {
                    state = States.IDLE_P1;
                    field.changeInfoLabel("Missed, try again");
                    changeState("popup");
                }
                break;

            case WALK_P1:
                actionTile = (Tile) e.getSource();
                // if(checkRange(currentTroop, actionTile, "walk")){

                if (checkRange(currentTroop, actionTile, "walk")) {
                    currentTroop.move(actionTile.d);
                    field.moveTroop(selectedTile.d, actionTile.d);
                    boolean res = false;
                    for (int i = 0; i < 17; i++) {
                        if (currentTroop.location.getX() == 16 && currentTroop.location.getY() == i) {
                            res = true;
                        }
                    }
                    if (res) {
                        prevState = States.WALK_P1;
                        state = States.END_OF_THE_GAME;
                        mouseClicked(e);
                    } else {
                        field.changeInfoLabel("Turn P2");
                        state = States.IDLE_P2;

                    }
                } else {
                    state = States.IDLE_P1;
                    field.changeInfoLabel("Out of range!");

                    changeState("popup");
                }
                break;

            case IDLE_P2:
                selectedTile = (Tile) e.getSource();

                if (selectedTile.troop != null && selectedTile.troop.player == 2) {
                    // popup = new PopUp(field, (int)selectedTile.d.getX(),
                    // (int)selectedTile.d.getY(), this);
                    currentTroop = selectedTile.troop;
                    // state = States.POPUP_P2;
                    changeState("popup");
                } else {
                    field.changeInfoLabel("Choose your troop P2");
                }
                break;

            case POPUP_P2:
                Tile newClick2 = (Tile) e.getSource();
                if (newClick2 != selectedTile) {
                    popup.hidePop();
                    state = States.IDLE_P2;
                }
                break;

            case SHOOT_P2:
                actionTile = (Tile) e.getSource();
                if (checkRange(currentTroop, actionTile, "shoot")) {
                    if (field.eliminateTroop(actionTile.d)) {
                        prevState = state;
                        state = States.END_OF_THE_GAME;
                    } else {
                        field.changeInfoLabel("Turn P1");

                        state = States.IDLE_P1;
                    }
                } else {
                    state = States.IDLE_P2;
                    field.changeInfoLabel("Missed, try again");
                    changeState("popup");

                }
                break;

            case WALK_P2:
                actionTile = (Tile) e.getSource();
                // if(checkRange(currentTroop, actionTile, "walk")){

                if (checkRange(currentTroop, actionTile, "walk")) {
                    currentTroop.move(actionTile.d);
                    field.moveTroop(selectedTile.d, actionTile.d);
                    boolean res = false;
                    for (int i = 0; i < 17; i++) {
                        if (currentTroop.location.getX() == 0 && currentTroop.location.getY() == i) {
                            res = true;
                        }
                    }
                    if (res) {
                        prevState = States.WALK_P2;
                        state = States.END_OF_THE_GAME;
                        mouseClicked(e);
                    } else {
                        field.changeInfoLabel("Turn P1");
                        state = States.IDLE_P1;

                    }
                } else {
                    state = States.IDLE_P2;
                    field.changeInfoLabel("Out of range!");

                    changeState("popup");
                }
                break;

            case END_OF_THE_GAME:
                int winner = 0;
                if (prevState == States.CHECK_ENEMIES_P1 || prevState == States.WALK_P1) {
                    winner = 1;
                } else {
                    winner = 2;
                }
                int screenWinner = winner + 2;
                field.mf.changePanel(screenWinner);
                System.out.println("P" + winner + " wins!!!!");
                break;

            default:
                break;
        }

    }

}
