package fileManagers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entities.*;
import program.Control;
import world.Cords;
import world.Map;

public class MapLoader {

    public MapLoader(String mapFileName) throws IOException {
        FileReader fr = new FileReader("files/maps/" + mapFileName);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<String> lines = new ArrayList<>();
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            lines.add(line);
        }
        br.close();

        int idx = 0;
        String[] map = lines.get(idx).split("x");
        int map_x = Integer.parseInt(map[0]);
        int map_y = Integer.parseInt(map[1]);
        Control.map = new Map(map_x, map_y);

        char[][] walls = new char[map_x][map_y];
        int yy = 0;
        for (int i = 0; i < map_y; i++) {
            String s = lines.get(i + 1);
            int xx = 0;
            for (char ch : s.toCharArray()) {
                walls[xx][yy] = ch;
                xx++;
            }
            yy++;
        }

        for (int y = 0; y < (map_y); y++) {
            Control.map.getTiles()[0][y].setLeft_wall(true);
        }

        for (int y = 0; y < (map_y); y++) {
            Control.map.getTiles()[map_x - 1][y].setRight_wall(true);
        }

        for (int x = 0; x < (map_x); x++) {
            for (int y = 0; y < (map_y); y++) {
                if (walls[x][y] == 'K') {
                    Control.map.getTiles()[x][y].setKitchen(true);
                    if (y == 0) {
                        Control.map.getTiles()[x][y].setTop_wall(true);
                    } else if (y == map_y - 1) {
                        Control.map.getTiles()[x][y].setDown_wall(true);
                    }
                    if ((y > 0) && (walls[x][y - 1] != 'K') && (walls[x][y - 1] != 'k')) {
                        Control.map.getTiles()[x][y].setTop_wall(true);
                    }
                    if ((y < (map_y - 1)) && (walls[x][y + 1] != 'K') && (walls[x][y + 1] != 'k')) {
                        Control.map.getTiles()[x][y].setDown_wall(true);
                    }
                    if ((x < (map_x - 1)) && (walls[x + 1][y] != 'K') && (walls[x + 1][y] != 'k')) {
                        Control.map.getTiles()[x][y].setRight_wall(true);
                    }
                    if ((x > 0) && (walls[x - 1][y] != 'K') && (walls[x - 1][y] != 'k')) {
                        Control.map.getTiles()[x][y].setLeft_wall(true);
                    }
                }

                if (walls[x][y] == 'R') {
                    if (y == 0) {
                        Control.map.getTiles()[x][y].setTop_wall(true);
                    } else if (y == map_y - 1) {
                        Control.map.getTiles()[x][y].setDown_wall(true);
                    }
                    if ((y > 0) && (walls[x][y - 1] != 'R') && (walls[x][y - 1] != 'r')) {
                        Control.map.getTiles()[x][y].setTop_wall(true);
                    }
                    if ((y < (map_y - 1)) && (walls[x][y + 1] != 'R') && (walls[x][y + 1] != 'r')) {
                        Control.map.getTiles()[x][y].setDown_wall(true);
                    }
                    if ((x < (map_x - 1)) && (walls[x + 1][y] != 'R') && (walls[x + 1][y] != 'r')) {
                        Control.map.getTiles()[x][y].setRight_wall(true);
                    }
                    if ((x > 0) && (walls[x - 1][y] != 'R') && (walls[x - 1][y] != 'r')) {
                        Control.map.getTiles()[x][y].setLeft_wall(true);
                    }
                }

                if (walls[x][y] == 'H') {
                    if (y == 0) {
                        Control.map.getTiles()[x][y].setTop_wall(true);
                    } else if (y == map_y - 1) {
                        Control.map.getTiles()[x][y].setDown_wall(true);
                    }
                    if ((y > 0) && (walls[x][y - 1] != 'H') && (walls[x][y - 1] != 'h')) {
                        Control.map.getTiles()[x][y].setTop_wall(true);
                    }
                    if ((y < (map_y - 1)) && (walls[x][y + 1] != 'H') && (walls[x][y + 1] != 'h')) {
                        Control.map.getTiles()[x][y].setDown_wall(true);
                    }
                    if ((x < (map_x - 1)) && (walls[x + 1][y] != 'H') && (walls[x + 1][y] != 'h')) {
                        Control.map.getTiles()[x][y].setRight_wall(true);
                    }
                    if ((x > 0) && (walls[x - 1][y] != 'H') && (walls[x - 1][y] != 'h')) {
                        Control.map.getTiles()[x][y].setLeft_wall(true);
                    }
                }

                if (walls[x][y] == 'k') {
                    Control.map.getTiles()[x][y].setKitchen(true);
                    if (y == 0) {
                        Control.map.getTiles()[x][y].setTop_wall(true);
                    } else if (y == map_y - 1) {
                        Control.map.getTiles()[x][y].setDown_wall(true);
                    }
                    if ((y > 0) && ((walls[x][y - 1] != 'K') && (walls[x][y - 1] != 'k') && (walls[x][y - 1] != 'h') && (walls[x][y - 1] != 'r'))) {
                        Control.map.getTiles()[x][y].setTop_wall(true);
                    }
                    if ((y < (map_y - 1)) && ((walls[x][y + 1] != 'K') && (walls[x][y + 1] != 'k') && (walls[x][y + 1] != 'h') && (walls[x][y + 1] != 'r'))) {
                        Control.map.getTiles()[x][y].setDown_wall(true);
                    }
                    if ((x < (map_x - 1)) && ((walls[x + 1][y] != 'K') && (walls[x + 1][y] != 'k') && (walls[x + 1][y] != 'h') && (walls[x + 1][y] != 'r'))) {
                        Control.map.getTiles()[x][y].setRight_wall(true);
                    }
                    if ((x > 0) && ((walls[x - 1][y] != 'K') && (walls[x - 1][y] != 'k') && (walls[x - 1][y] != 'h') && (walls[x - 1][y] != 'r'))) {
                        Control.map.getTiles()[x][y].setLeft_wall(true);
                    }
                }


                if (walls[x][y] == 'h') {
                    if (y == 0) {
                        Control.map.getTiles()[x][y].setTop_wall(true);
                    } else if (y == map_y - 1) {
                        Control.map.getTiles()[x][y].setDown_wall(true);
                    }
                    if ((y > 0) && ((walls[x][y - 1] != 'k') && (walls[x][y - 1] != 'h') && (walls[x][y - 1] != 'H') && (walls[x][y - 1] != 'r'))) {
                        Control.map.getTiles()[x][y].setTop_wall(true);
                    }
                    if ((y < (map_y - 1)) && ((walls[x][y + 1] != 'k') && (walls[x][y + 1] != 'h') && (walls[x][y + 1] != 'H') && (walls[x][y + 1] != 'r'))) {
                        Control.map.getTiles()[x][y].setDown_wall(true);
                    }
                    if ((x < (map_x - 1)) && ((walls[x + 1][y] != 'k') && (walls[x + 1][y] != 'h') && (walls[x + 1][y] != 'H') && (walls[x + 1][y] != 'r'))) {
                        Control.map.getTiles()[x][y].setRight_wall(true);
                    }
                    if ((x > 0) && ((walls[x - 1][y] != 'k') && (walls[x - 1][y] != 'h') && (walls[x - 1][y] != 'H') && (walls[x - 1][y] != 'r'))) {
                        Control.map.getTiles()[x][y].setLeft_wall(true);
                    }
                }

                if (walls[x][y] == 'r') {
                    if (y == 0) {
                        Control.map.getTiles()[x][y].setTop_wall(true);
                    } else if (y == map_y - 1) {
                        Control.map.getTiles()[x][y].setDown_wall(true);
                    }
                    if ((y > 0) && ((walls[x][y - 1] != 'k') && (walls[x][y - 1] != 'h') && (walls[x][y - 1] != 'r') && (walls[x][y - 1] != 'R'))) {
                        Control.map.getTiles()[x][y].setTop_wall(true);
                    }
                    if ((y < (map_y - 1)) && ((walls[x][y + 1] != 'k') && (walls[x][y + 1] != 'h') && (walls[x][y + 1] != 'r') && (walls[x][y + 1] != 'R'))) {
                        Control.map.getTiles()[x][y].setDown_wall(true);
                    }
                    if ((x < (map_x - 1)) && ((walls[x + 1][y] != 'k') && (walls[x + 1][y] != 'h') && (walls[x + 1][y] != 'r') && (walls[x + 1][y] != 'R'))) {
                        Control.map.getTiles()[x][y].setRight_wall(true);
                    }
                    if ((x > 0) && ((walls[x - 1][y] != 'k') && (walls[x - 1][y] != 'h') && (walls[x - 1][y] != 'r') && (walls[x - 1][y] != 'R'))) {
                        Control.map.getTiles()[x][y].setLeft_wall(true);
                    }
                }
            }
        }

        idx += map_y + 1;
        String[] map_entities_count = lines.get(idx).split("\t");
        int player_count = Integer.parseInt(map_entities_count[0]);
        int enemy_count = Integer.parseInt(map_entities_count[1]);
        int food_count = Integer.parseInt(map_entities_count[2]);
        int nonfood_count = Integer.parseInt(map_entities_count[3]);
        idx++;

        for (int i = idx; i < (idx + player_count); i++) {
            String[] playerPos = lines.get(i).split("\t");
            int pos_x = Integer.parseInt(playerPos[0]) - 1;
            int pos_y = Integer.parseInt(playerPos[1]) - 1;
            Control.map.addCharacter(new MrMitten(pos_x, pos_y));
        }
        idx += player_count;

        for (int i = idx; i < idx + enemy_count; i++) {
            String[] enemyPos = lines.get(i).split("\t");
            int pos_x = Integer.parseInt(enemyPos[0]) - 1;
            int pos_y = Integer.parseInt(enemyPos[1]) - 1;
            Control.map.addEnemy(new Enemy(pos_x, pos_y));
        }
        idx += enemy_count;

        for (int i = idx; i < idx + food_count; i++) {
            String[] food_stats = lines.get(i).split("\t");
            int food_x = Integer.parseInt(food_stats[0]) - 1;
            int food_y = Integer.parseInt(food_stats[1]) - 1;
            int food_health = Integer.parseInt(food_stats[2]);
            Control.map.addItem(new Food(food_stats[3], food_x, food_y, food_health, "FOOD"));
        }
        idx += food_count;

        for (int i = idx; i < idx + nonfood_count; i++) {
            String[] food_stats = lines.get(i).split("\t");
            int food_x = Integer.parseInt(food_stats[0]) - 1;
            int food_y = Integer.parseInt(food_stats[1]) - 1;
            boolean movable = Boolean.parseBoolean(food_stats[2]);
            Control.map.addItem(new NonFood(food_stats[3], food_x, food_y, movable, "NONFOOD"));
            if (food_stats[3].equals("barrel")) {
                Control.map.getTiles()[food_x][food_y].setBarrel(true);
            }
            if (food_stats[3].equals("trash_can")) {
                Control.map.getTiles()[food_x][food_y].setTrashCan(true);
            }
        }

        for (int i = 0; i < Control.map.getTiles().length; i++)
            for (int j = 0; j < Control.map.getTiles()[0].length; j++) {
                Control.map.getTiles()[i][j].setSeeable(false);
                Control.map.getTiles()[i][j].setCords(new Cords(i,j));
            }

        for (Entity e : Control.map.getCharacters()) {
            int entity_x = e.getCords().getX();
            int entity_y = e.getCords().getY();
            Control.map.getTiles()[entity_x][entity_y].setSeeable(true);
            if (entity_x - 2 >= 0)
                Control.map.getTiles()[entity_x - 2][entity_y].setSeeable(true);
            if (entity_x - 1 >= 0) {
                Control.map.getTiles()[entity_x - 1][entity_y].setSeeable(true);
                if (entity_y - 1 >= 0)
                    Control.map.getTiles()[entity_x - 1][entity_y - 1].setSeeable(true);
                if (entity_y < map_y)
                    Control.map.getTiles()[entity_x - 1][entity_y + 1].setSeeable(true);
            }
            if (entity_x + 1 < map_x) {
                Control.map.getTiles()[entity_x + 1][entity_y].setSeeable(true);
                if (entity_y - 1 >= 0)
                    Control.map.getTiles()[entity_x + 1][entity_y - 1].setSeeable(true);
                if (entity_y + 1 < map_y)
                    Control.map.getTiles()[entity_x + 1][entity_y + 1].setSeeable(true);
            }
            if (entity_x + 2 < map_x)
                Control.map.getTiles()[entity_x + 2][entity_y].setSeeable(true);

            if (entity_y - 2 >= 0)
                Control.map.getTiles()[entity_x][entity_y - 2].setSeeable(true);
            if (entity_y - 1 >= 0)
                Control.map.getTiles()[entity_x][entity_y - 1].setSeeable(true);
            if (entity_y + 1 < map_y)
                Control.map.getTiles()[entity_x][entity_y + 1].setSeeable(true);
            if (entity_y + 2 < map_y)
                Control.map.getTiles()[entity_x][entity_y + 2].setSeeable(true);
        }

        Control.map.setCharacters();
        Control.map.setEnemyCharacters();
        Control.map.setItems();
    }
}