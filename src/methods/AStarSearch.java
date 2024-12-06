package methods;

import program.Control;
import world.Path;
import world.Tile;

import java.util.ArrayList;

public class AStarSearch {

    static ArrayList<AStar> openList = new ArrayList<>();
    static ArrayList<AStar> closedList = new ArrayList<>();

    public Tile trashCan() {
        Tile trashCan = new Tile();
        for (Tile[] tiles : Control.map.getTiles()) {
            for (Tile t : tiles) {
                if (!t.getItems().isEmpty()) {
                    if (t.isTrashCan())
                        trashCan = t;
                }
            }
        }
        return trashCan;
    }

    public Path search(Tile start, Tile target, int maxDistance) {
        openList.clear();
        closedList.clear();

        if ((start == null) || (target == null)) {
            return null;
        }

        AStar startCell = new AStar(start, 0, heuristics(start, target), null);
        openList.add(startCell);
        while (!openList.isEmpty()) {
            int bestIndex = 0;
            double bestF = openList.get(bestIndex).getF();
            for (int i = 1; i < openList.size(); i++) {
                if (openList.get(i).getF() < bestF) {
                    bestIndex = i;
                    bestF = openList.get(i).getF();
                }
            }
            AStar selectedCell = openList.get(bestIndex);

            if (maxDistance > 0 && selectedCell.getSumG() > maxDistance) {
                return null;
            }

            if (selectedCell.getTile().equals(target)) {
                Path result = new Path();
                while (selectedCell != null) {
                    result.addTileToPath(selectedCell.getTile());
                    selectedCell = selectedCell.getParent();
                }
                return result;
            }

            ArrayList<Tile> neighbours = new ArrayList<>();
            if ((!selectedCell.getTile().hasTop_wall()) && (!Control.map.getTiles()[selectedCell.getTile().getCords().getX()][selectedCell.getTile().getCords().getY() - 1].isBarrel()))
                neighbours.add(Control.map.getTiles()[selectedCell.getTile().getCords().getX()][selectedCell.getTile().getCords().getY() - 1]);
            else
                neighbours.add(null);

            if ((!selectedCell.getTile().hasRight_wall()) && (!Control.map.getTiles()[selectedCell.getTile().getCords().getX() + 1][selectedCell.getTile().getCords().getY()].isBarrel()))
                neighbours.add(Control.map.getTiles()[selectedCell.getTile().getCords().getX() + 1][selectedCell.getTile().getCords().getY()]);
            else
                neighbours.add(null);

            if ((!selectedCell.getTile().hasDown_wall()) && (!Control.map.getTiles()[selectedCell.getTile().getCords().getX()][selectedCell.getTile().getCords().getY() + 1].isBarrel()))
                neighbours.add(Control.map.getTiles()[selectedCell.getTile().getCords().getX()][selectedCell.getTile().getCords().getY() + 1]);
            else
                neighbours.add(null);

            if ((!selectedCell.getTile().hasLeft_wall()) && (!Control.map.getTiles()[selectedCell.getTile().getCords().getX() - 1][selectedCell.getTile().getCords().getY()].isBarrel()))
                neighbours.add(Control.map.getTiles()[selectedCell.getTile().getCords().getX() - 1][selectedCell.getTile().getCords().getY()]);
            else
                neighbours.add(null);

            for (Tile possibleNeighbour : neighbours) {
                if (possibleNeighbour != null) {
                    if (!possibleNeighbour.isBarrel() || possibleNeighbour.equals(target)) {
                        boolean skip = false;

                        for (AStar aStar : openList) {
                            if (aStar.getTile().equals(possibleNeighbour)) {
                                skip = true;
                                break;
                            }
                        }

                        if (skip) {
                            continue;
                        }
                        for (AStar aStar : closedList) {
                            if (aStar.getTile().equals(possibleNeighbour)) {
                                skip = true;
                                break;
                            }
                        }

                        openList.add(new AStar(possibleNeighbour, selectedCell.getSumG() + 1, heuristics(possibleNeighbour, target), selectedCell));
                    }
                }
            }
            openList.remove(selectedCell);
            closedList.add(selectedCell);
        }

        return null;
    }

    public double heuristics(Tile start_tile, Tile end_tile) {
        return Math.sqrt(Math.pow(start_tile.getCords().getX() - end_tile.getCords().getX(), 2) + Math.pow(start_tile.getCords().getY() - end_tile.getCords().getY(), 2));
    }
}