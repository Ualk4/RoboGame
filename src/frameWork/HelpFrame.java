package frameWork;

import program.Control;

import javax.swing.*;
import java.awt.*;

public class HelpFrame extends JFrame {

    HelpFrame() {
        this.setTitle("HELP CENTER");

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setAlwaysOnTop(true);
        this.setSize(500, 500);
        this.setLocation((Control.frame.getWidth() - this.getWidth()) / 2, (Control.frame.getHeight() - this.getHeight()) / 2);
        this.setIconImage(new ImageIcon("images/help.png").getImage());

        JTextArea helpTxt = new JTextArea();
        helpTxt.setText("How to play:\n" +
                "   First select a map, then load it!\n" +
                "   Then select witch type of game You wanna play:\n" +
                "     o KEYBOARD, where you play with the keyboard:\n" +
                "           arrows for moving, Q for picking up an item and W to put down.\n" +
                "     o or USE SCRIPTS, where you use scripts to take a turn\n" +
                "          Load existing scripts or choose witch script type You wanna play, You can also save them!\n" +
                "   Finally You can START the game or STEP one turn at a time.\n" +
                "   You can also STOP the game or PAUSE it.\n" +
                "   Bring all the savable items to the green tiled kitchen!\n" +
                "   Bring all spoiled food to the trash can!\n" +
                "   There are some guide lines helping You:\n" +
                "       RED for the closest item, CYAN for trash can and GREEN for the closest kitchen entrance.\n" +
                "\nTo use character: \n" +
                "   [character_id].moveUp()\n" +
                "       Move character one tile up, if possible.\n" +
                "   [character_id].moveDown()\n" +
                "       Move character one tile down, if possible.\n" +
                "   [character_id].moveRight()\n" +
                "       Move character one tile right, if possible.\n" +
                "   [character_id].moveLeft()\n" +
                "       Move character one tile left, if possible.\n" +
                "\n   [character_id].pickUp()\n" +
                "       Pick up item on current tile, if theres one.\n" +
                "   [character_id].putDown()\n" +
                "       Put down item on current tile, must be empty.\n" +
                "\nItem functions:\n" +
	            "   [item_id].isMovable()\n" +
                "       \n" +
                "   [item_id].isInHand()\n" +
                "   [item_id].getType()\n" +
                "   Character functions \n" +
                "   [character_id].getItem()\n" +
                "   [character_id].setAim()\n" +
                "   [character_id].getAim()\n" +
                "   [character_id].isInfected()\n" +
                "   [character_id].getClosestItemTile()\n" +
                "   [character_id].getClosestKitchenEntrance()\n" +
                "   [character_id].getClosestItemTileWithout(Item[] without_Items)\n" +
                "   [character_id].heuristics(Tile start_tile, Tile end_tile)\n" +
                "\nTile functions\n" +
                "   [tile_id].getItems()\n" +
                "   [tile_id].getItemsAsArray()\n" +
                "   [tile_id].getEntities()\n" +
                "   [tile_id].getEntitiesAsArray()\n" +
                "   [tile_id].constainsItem(Item item)\n" +
                "   [tile_id].isMovable()\n" +
                "   [tile_id].isKitchen()\n" +
                "   [tile_id].isBarrel()\n" +
                "   [tile_id].isTrashCan()\n" +
                "\nHelyzet lekérdezése\n" +
                "A helyzetet mind a karakterketől, tárgyaktól és celláktól lekérdezhető\n" +
                "   [id].getCoords()\n" +
                "   [id].getCoords().getX()\n" +
                "   [id].getCoords().getY()\n" +
                "\nPath functions\n" +
                "   [path_id].addTileToPath(Tile tile)\n" +
                "   [path_id].removeTileToPath(Tile tile)\n" +
                "   [path_id].getStartTile()\n" +
                "   [path_id].getFirstTile()\n" +
                "   [path_id].getLastTile()\n" +
                "   [path_id].getLength()\n" +
                "\nMap functions\n" +
                "   [map_id].getItems()\n" +
                "   [map_id].getItemsAsArray()\n" +
                "   [map_id].getSavedItems()\n" +
                "   [map_id].getSavedItemsAsArray()\n" +
                "   [map_id].getThrownItems()\n" +
                "   [map_id].getThrownItemsAsArray()\n" +
                "   [map_id].getEatenItems()\n" +
                "   [map_id].getEatenItemsAsArray()\n" +
                "   [map_id].getCharacters()\n" +
                "   [map_id].getCharactersAsArray()\n" +
                "   [map_id].getEnemyCharacters ()\n" +
                "   [map_id].getEnemyCharactersAsArray()\n" +
                "   [map_id].getAllCharacters()\n" +
                "   [map_id].getAllCharactersAsArray()\n" +
                "   [map_id].isThereFood()\n" +
                "   [map_id].isThereSavable()\n" +
                "\nAStarSearch()\n" +
                "   [AStarSearch_id].search(Tile start, Tile target, int maxDistance)\n"
        );
        helpTxt.setWrapStyleWord(true);
        helpTxt.setLineWrap(true);
        helpTxt.setEditable(false);
        helpTxt.setOpaque(false);
        helpTxt.setCaretPosition(0);

        JScrollPane helpScrollPane = new JScrollPane(helpTxt);
        this.add(helpScrollPane, BorderLayout.CENTER);
        this.setVisible(true);

    }
}
