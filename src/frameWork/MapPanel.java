package frameWork;

import methods.AStarSearch;
import entities.*;
import program.Control;
import world.Map;
import world.Path;
import world.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MapPanel extends JPanel {

    private Map map;

    public MapPanel() {
        super();
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        this.map = Control.map;
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        BufferedImage img;

        int tileX = (int) (double) (this.getWidth() / map.getTiles().length - 2);
        int tileY = (int) (double) (((this.getHeight() - 200) / map.getTiles()[0].length - 2));
        int width = map.getTiles().length;
        int height = map.getTiles()[0].length;
        int tileSize = Math.min(tileX, tileY);
        int img_adjust = tileSize / 3;

        try {
            img = ImageIO.read(new File("images/game_background.jpg"));
            g2.drawImage(img, 0, 100, this.getWidth(), this.getHeight() - 200, null);

            g.setColor(new Color(238, 238, 238));
            g2.fillRect(0, 0, getWidth(), 100);

            Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
            FontMetrics fm = g.getFontMetrics(font);
            Rectangle2D bounds = fm.getStringBounds(Control.getCurrentState(), g);
            int fontWidth = (int) bounds.getWidth();
            bounds = fm.getStringBounds(Control.getKeyState(), g);
            int fontWidthPressKey = (int) bounds.getWidth();
            g.setFont(font);
            g.setColor(Color.BLACK);
            g2.drawString(Control.getCurrentState(), ((this.getWidth() - fontWidth) / 2), 30);
            g2.drawString(Control.getKeyState(), ((this.getWidth() - fontWidthPressKey) / 2), 80);

            g.setColor(new Color(189, 177, 174));
            g2.fillRect(((getWidth() - (tileSize * width)) / 2), ((getHeight() - (tileSize * height)) / 2), tileSize * width, tileSize * height);

            for (int x = 0; x < width; x += 1) {
                for (int y = 0; y < height; y += 1) {
                    g.setColor(new Color(235, 234, 223));
                    g2.fillRect(x * tileSize + ((getWidth() - (tileSize * width)) / 2), y * tileSize + ((getHeight() - (tileSize * height)) / 2), tileSize - 1, tileSize - 1);
                    if (map.getTiles()[x][y].isKitchen()) {
                        g.setColor(new Color(209, 226, 195));
                        g2.fillRect(x * tileSize + ((getWidth() - (tileSize * width)) / 2), y * tileSize + ((getHeight() - (tileSize * height)) / 2), tileSize - 1, tileSize - 1);
                    }
                }
            }

            for (int i = 0; i < map.getTiles().length; i++) {
                for (int j = 0; j < map.getTiles()[i].length; j++) {
                    ArrayList<Entity> entities = map.getTiles()[i][j].getEntities();
                    ArrayList<Item> mapItems = map.getTiles()[i][j].getItems();
                    if (!entities.isEmpty()) {
                        for (Entity en : entities) {
                            img = ImageIO.read(new File(en.getPngName()));
                            g2.drawImage(img, en.getCords().getX() * tileSize + ((getWidth() - (tileSize * width)) / 2), en.getCords().getY() * tileSize + ((getHeight() - (tileSize * height)) / 2), tileSize - 1, tileSize - 1, null);
                        }
                    }
                    for (Item item : mapItems) {
                        if ((item != null) && (map.getItems().contains(item))) {
                            img = ImageIO.read(new File(item.getPngName()));
                            if (item.isInHand()) {
                                g2.drawImage(img, item.getCords().getX() * tileSize + ((getWidth() - (tileSize * width)) / 2), item.getCords().getY() * tileSize + ((getHeight() - (tileSize * height)) / 2), tileSize / 2, tileSize / 2, null);
                            } else {
                                g2.drawImage(img, item.getCords().getX() * tileSize + ((getWidth() - (tileSize * width)) / 2) + (img_adjust / 2), item.getCords().getY() * tileSize + ((getHeight() - (tileSize * height)) / 2) + (img_adjust / 2), tileSize - img_adjust, tileSize - img_adjust, null);
                                if (item.getType().equals("FOOD")) {
                                    g.setColor(Color.black);
                                    g.drawRect(item.getCords().getX() * tileSize + ((getWidth() - (tileSize * width)) / 2) + 3, item.getCords().getY() * tileSize + ((getHeight() - (tileSize * height)) / 2) + (tileSize / 5) * 4 - 1, tileSize - 8, 6);
                                    g.setColor(Color.white);
                                    g.fillRect(item.getCords().getX() * tileSize + ((getWidth() - (tileSize * width)) / 2) + 4, item.getCords().getY() * tileSize + ((getHeight() - (tileSize * height)) / 2) + (tileSize / 5) * 4, tileSize - 9, 5);
                                    if (((Food) item).getHealth() == 0) {
                                        g.setColor(Color.BLACK);
                                        g.fillRect(item.getCords().getX() * tileSize + ((getWidth() - (tileSize * width)) / 2) + 4, item.getCords().getY() * tileSize + ((getHeight() - (tileSize * height)) / 2) + (tileSize / 5) * 4, (tileSize - 9), 5);
                                    } else {
                                        g.setColor(calculateColor(1 - ((Food) item).getHealthRatio()));
                                        g.fillRect(item.getCords().getX() * tileSize + ((getWidth() - (tileSize * width)) / 2) + 4, item.getCords().getY() * tileSize + ((getHeight() - (tileSize * height)) / 2) + (tileSize / 5) * 4, (int) ((tileSize - 10) * ((Food) item).getHealthRatio()), 5);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            g.setColor(new Color(238, 238, 238));
            g2.fillRect(0, this.getHeight() - 100, this.getWidth(), 100);

            g.setColor(Color.DARK_GRAY);
            g2.drawString("Saved items:", 10, this.getHeight() - 80);

            for (int i = 0; i < map.getItems_saved().size(); i++) {
                Item item = map.getItems_saved().get(i);
                img = ImageIO.read(new File(item.getPngName()));
                g2.drawImage(img, i * tileSize + 10, this.getHeight() - 75, 30, 30, null);
            }

            g.setColor(Color.DARK_GRAY);
            g2.drawString("Thrown items:", 10, this.getHeight() - 30);
            for (int i = 0; i < map.getItems_thrown().size(); i++) {
                Item item = map.getItems_thrown().get(i);
                img = ImageIO.read(new File(item.getPngName()));
                g2.drawImage(img, i * tileSize + 10, this.getHeight() - 25, 30, 30, null);
            }

            if (!Control.map.getEnemyCharacters().isEmpty()) {
                g.setColor(Color.DARK_GRAY);
                g2.drawString("Eaten items :", (this.getWidth() / 3) * 2, this.getHeight() - 80);
                for (int i = 0; i < map.getItems_eaten().size(); i++) {
                    Item item = map.getItems_eaten().get(i);
                    img = ImageIO.read(new File(item.getPngName()));
                    g2.drawImage(img, i * tileSize + ((this.getWidth() / 3) * 2), this.getHeight() - 75, 30, 30, null);
                }
            }

            for (int x = 0; x < width; x += 1) {
                for (int y = 0; y < height; y += 1) {
                    ((Graphics2D) g).setStroke(new BasicStroke(4));
                    g.setColor(new Color(200, 100, 80));
                    if (map.getTiles()[x][y].hasTop_wall()) {
                        g2.drawLine(x * tileSize + ((getWidth() - (tileSize * width)) / 2), y * tileSize + ((getHeight() - (tileSize * height)) / 2), x * tileSize + ((getWidth() - (tileSize * width)) / 2) + tileSize, y * tileSize + ((getHeight() - (tileSize * height)) / 2));
                    }
                    if (map.getTiles()[x][y].hasDown_wall()) {
                        g2.drawLine(x * tileSize + ((getWidth() - (tileSize * width)) / 2), y * tileSize + ((getHeight() - (tileSize * height)) / 2) + tileSize, x * tileSize + ((getWidth() - (tileSize * width)) / 2) + tileSize, y * tileSize + ((getHeight() - (tileSize * height)) / 2) + tileSize);
                    }
                    if (map.getTiles()[x][y].hasRight_wall()) {
                        g2.drawLine(x * tileSize + ((getWidth() - (tileSize * width)) / 2) + tileSize, y * tileSize + ((getHeight() - (tileSize * height)) / 2), x * tileSize + ((getWidth() - (tileSize * width)) / 2) + tileSize, y * tileSize + ((getHeight() - (tileSize * height)) / 2) + tileSize);
                    }
                    if (map.getTiles()[x][y].hasLeft_wall()) {
                        g2.drawLine(x * tileSize + ((getWidth() - (tileSize * width)) / 2), y * tileSize + ((getHeight() - (tileSize * height)) / 2), x * tileSize + ((getWidth() - (tileSize * width)) / 2), y * tileSize + ((getHeight() - (tileSize * height)) / 2) + tileSize);
                    }
                }
            }

            AStarSearch AStar = new AStarSearch();
            for (MrMitten currentCharacter : Control.map.getCharacters()) {
                Path pathToTile = AStar.search(currentCharacter.getClosestKitchenEntrance(), Control.map.getTiles()[currentCharacter.getCords().getX()][currentCharacter.getCords().getY()], -1);
                if ((!map.getTiles()[currentCharacter.getCords().getX()][currentCharacter.getCords().getY()].isKitchen()) && (pathToTile != null)) {
                    for (int i = 0; i < pathToTile.getLength() - 1; i++) {
                        g.setColor(Color.GREEN);
                        g2.drawLine(pathToTile.getPath().get(i).getCords().getX() * tileSize + ((getWidth() - (tileSize * width)) / 2) + tileSize / 2, pathToTile.getPath().get(i).getCords().getY() * tileSize + ((getHeight() - (tileSize * height)) / 2) + tileSize / 2,
                                pathToTile.getPath().get(i + 1).getCords().getX() * tileSize + ((getWidth() - (tileSize * width)) / 2) + tileSize / 2, pathToTile.getPath().get(i + 1).getCords().getY() * tileSize + ((getHeight() - (tileSize * height)) / 2) + tileSize / 2);
                    }
                }

                pathToTile = AStar.search(AStar.trashCan(), Control.map.getTiles()[currentCharacter.getCords().getX()][currentCharacter.getCords().getY()], -1);
                if (pathToTile != null) {
                    for (int i = 0; i < pathToTile.getLength() - 1; i++) {
                        g.setColor(Color.CYAN);
                        g2.drawLine(pathToTile.getPath().get(i).getCords().getX() * tileSize + ((getWidth() - (tileSize * width)) / 2) + tileSize / 2, pathToTile.getPath().get(i).getCords().getY() * tileSize + ((getHeight() - (tileSize * height)) / 2) + tileSize / 2,
                                pathToTile.getPath().get(i + 1).getCords().getX() * tileSize + ((getWidth() - (tileSize * width)) / 2) + tileSize / 2, pathToTile.getPath().get(i + 1).getCords().getY() * tileSize + ((getHeight() - (tileSize * height)) / 2) + tileSize / 2);
                    }
                }

                if (!Control.map.isLastOne()) {
                    ArrayList<Tile> otherCharacters_Aim = new ArrayList<>();
                    boolean isOK = false;
                    if ((currentCharacter.getClosestItemTile() != null) && !(currentCharacter.getClosestItemTile().getItems().isEmpty())) {
                        for (Item item : currentCharacter.getClosestItemTile().getItems()) {
                            if (item.isMovable() && (!item.isInHand())) {
                                isOK = true;
                                break;
                            }
                        }
                    }
                    if (isOK) {
                        pathToTile = AStar.search(currentCharacter.getClosestItemTile(), Control.map.getTiles()[currentCharacter.getCords().getX()][currentCharacter.getCords().getY()], -1);
                        if (currentCharacter.getItem() == null) {
                            for (MrMitten otherCharacter : Control.map.getCharacters()) {
                                if ((!otherCharacter.equals(currentCharacter)) && (otherCharacter.getAim() != null) && (pathToTile != null)) {
                                    if (otherCharacter.getAim().equals(pathToTile.getLastTile().getItems().get(0))) {
                                        otherCharacters_Aim.add(otherCharacter.getAim());
                                    }
                                }
                                pathToTile = AStar.search(currentCharacter.getClosestItemTileWithout(otherCharacters_Aim), Control.map.getTiles()[currentCharacter.getCords().getX()][currentCharacter.getCords().getY()], -1);
                            }

                            if (pathToTile != null) {
                                if (currentCharacter.getAim() != null) {
                                    for (int i = 0; i < pathToTile.getLength() - 1; i++) {
                                        g.setColor(Color.RED);
                                        g2.drawLine(pathToTile.getPath().get(i).getCords().getX() * tileSize + ((getWidth() - (tileSize * width)) / 2) + tileSize / 2, pathToTile.getPath().get(i).getCords().getY() * tileSize + ((getHeight() - (tileSize * height)) / 2) + tileSize / 2,
                                                pathToTile.getPath().get(i + 1).getCords().getX() * tileSize + ((getWidth() - (tileSize * width)) / 2) + tileSize / 2, pathToTile.getPath().get(i + 1).getCords().getY() * tileSize + ((getHeight() - (tileSize * height)) / 2) + tileSize / 2);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (!Control.isIsAllSeeable()) {
                for (int x = 0; x < width; x += 1) {
                    for (int y = 0; y < height; y += 1) {
                        if (!map.getTiles()[x][y].isSeeable()) {
                            ((Graphics2D) g).setStroke(new BasicStroke(2));
                            g.setColor(new Color(189, 177, 174));
                            g2.drawRect(x * tileSize + ((getWidth() - (tileSize * width)) / 2), y * tileSize + ((getHeight() - (tileSize * height)) / 2), tileSize, tileSize);
                            g.setColor(new Color(200, 100, 80));
                            g2.fillRect(x * tileSize + ((getWidth() - (tileSize * width)) / 2), y * tileSize + ((getHeight() - (tileSize * height)) / 2), tileSize - 1, tileSize - 1);
                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Color calculateColor(float value) {
        double red = 0, green = 0, blue = 0;

        // First, green stays at 100%, red raises to 100%
        if (value < 0.5) {
            green = 1;
            red = 2 * value;
        }

        // Then red stays at 100%, green decays
        if (0.5 <= value) {
            red = 1;
            green = 1 - 2 * (value - 0.5);
        }
        return new Color((int) (255 * red), (int) (255 * green), (int) (255 * blue));
    }
}