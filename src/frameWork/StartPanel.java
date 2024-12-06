package frameWork;

import program.Control;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartPanel extends JPanel {

    public StartPanel() {
        super();
        setBackground(new Color(4, 100, 4));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        BufferedImage img;

        int width = this.getWidth();
        int height = this.getHeight();

        try {
            img = ImageIO.read(new File("images/background.jpg"));
            g2.drawImage(img, 0, 0, width, height, null);

            g.setColor(new Color(200,100,80));
            g.fillRect((width / 4), (height / 4), (width / 2), (height / 2));

            img = ImageIO.read(new File("images/MrMitten/cat_inlove.png"));
            g2.drawImage(img, (width / 4), (height / 4) - 80, 80, 80, null);

            img = ImageIO.read(new File("images/enemy.png"));
            g2.drawImage(img, (width / 4) * 3 - 80, (height / 4) - 80, 80, 80, null);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 100);
        String str = "Welcome!";
        FontMetrics fm  = g.getFontMetrics(font);
        Rectangle2D bounds = fm.getStringBounds(str, g);
        int fontWidth = (int)bounds.getWidth();
        g.setFont(font);
        g.setColor(Color.DARK_GRAY);
        g2.drawString(str, ((width - fontWidth) / 2), (height  / 4) - 5);


        int lineCounter = 1;
        int lineSize = ((height / 2) / 21);
        font = new Font(Font.SANS_SERIF, Font.PLAIN, lineSize - 2);
        g.setFont(font);
        str = "Dear Player, I'm asking You to help MrMitten who is in dire need of help!";
        fm  = g.getFontMetrics(font);
        bounds = fm.getStringBounds(str, g);
        fontWidth = (int)bounds.getWidth();
        g2.drawString(str, ((width - fontWidth) / 2), (height  / 4) + (lineSize * lineCounter++));


        str = "Unfortunately there was a bit of a disaster inside his pantry,";
        bounds = fm.getStringBounds(str, g);
        fontWidth = (int)bounds.getWidth();
        g2.drawString(str, ((width - fontWidth) / 2), (height  / 4) + (lineSize * lineCounter++));

        str = "all the food is going bad and all the other items also need to be saved!";
        bounds = fm.getStringBounds(str, g);
        fontWidth = (int)bounds.getWidth();
        g2.drawString(str, ((width - fontWidth) / 2), (height  / 4) + (lineSize * lineCounter++));

        str = "And be aware, he noticed that some food keep disappearing, there must be a culprit!";
        bounds = fm.getStringBounds(str, g);
        fontWidth = (int)bounds.getWidth();
        g2.drawString(str, ((width - fontWidth) / 2), (height  / 4) + (lineSize * lineCounter++));

        g2.drawString("", 0, (height  / 4) + (lineSize * lineCounter++));

        str = "How to play:";
        g2.drawString(str, (width / 4), (height  / 4) + (lineSize * lineCounter++));

        str = "First select a map, then load it!";
        g2.drawString(str, (width / 4) + 20, (height  / 4) + (lineSize * lineCounter++));

        str = "Then select witch type of game You wanna play:";
        g2.drawString(str, (width / 4) + 20, (height  / 4) + (lineSize * lineCounter++));

        str = "    o KEYBOARD, where you play with the keyboard:";
        g2.drawString(str, (width / 4) + 20, (height  / 4) + (lineSize * lineCounter++));

        str ="          arrows for moving, Q for picking up an item and W to put down.";
        g2.drawString(str, (width / 4) + 20, (height  / 4) + (lineSize * lineCounter++));

        str = "    o or USE SCRIPTS, where you use scripts to take a turn";
        g2.drawString(str, (width / 4) + 20, (height  / 4) + (lineSize * lineCounter++));

        str = "         Load existing scripts or choose witch script type You wanna play, You can also save them!";
        g2.drawString(str, (width / 4) + 20, (height  / 4) + (lineSize * lineCounter++));

        str = "Finally You can START the game or STEP one turn at a time.";
        g2.drawString(str, (width / 4) + 20, (height  / 4) + (lineSize * lineCounter++));

        str = "You can also STOP the game or PAUSE it.";
        g2.drawString(str, (width / 4) + 20, (height  / 4) + (lineSize * lineCounter++));

        g2.drawString("", 0, (height  / 4) + (lineSize * lineCounter++));

        str = "Bring all the savable items to the green tiled kitchen!";
        bounds = fm.getStringBounds(str, g);
        fontWidth = (int)bounds.getWidth();
        g2.drawString(str, ((width - fontWidth) / 2), (height  / 4) + (lineSize * lineCounter++));

        str = "Bring all spoiled food to the trash can!";
        bounds = fm.getStringBounds(str, g);
        fontWidth = (int)bounds.getWidth();
        g2.drawString(str, ((width - fontWidth) / 2), (height  / 4) + (lineSize * lineCounter++));

        str = "There are some guide lines helping You:";
        g2.drawString(str, (width / 4) + 20, (height  / 4) + (lineSize * lineCounter++));

        g.setColor(Color.RED);
        str = "RED";
        g2.drawString(str, (width  / 4) + 40, (height  / 4) + (lineSize * lineCounter));

        g.setColor(Color.DARK_GRAY);
        bounds = fm.getStringBounds("RED", g);
        fontWidth = (int)bounds.getWidth();
        str = " for the closest item, ";
        g2.drawString(str, (width  / 4) + fontWidth + 40, (height  / 4) + (lineSize * lineCounter));

        g.setColor(Color.CYAN);
        bounds = fm.getStringBounds("RED for the closest item, ", g);
        fontWidth = (int)bounds.getWidth();
        str ="CYAN";
        g2.drawString(str, (width  / 4) + fontWidth + 40, (height  / 4) + (lineSize * lineCounter));

        g.setColor(Color.DARK_GRAY);
        bounds = fm.getStringBounds("RED for the closest item, CYAN", g);
        fontWidth = (int)bounds.getWidth();
        str = " for trash can and ";
        g2.drawString(str, (width  / 4) + fontWidth + 40, (height  / 4) + (lineSize * lineCounter));

        g.setColor(Color.GREEN);
        bounds = fm.getStringBounds("RED for the closest item, CYAN for trash can and ", g);
        fontWidth = (int)bounds.getWidth();
        str = "GREEN";
        g2.drawString(str, (width  / 4) + fontWidth + 40, (height  / 4) + (lineSize * lineCounter));

        g.setColor(Color.DARK_GRAY);
        bounds = fm.getStringBounds("RED for the closest item, CYAN for trash can and GREEN", g);
        fontWidth = (int)bounds.getWidth();
        str = " for the closest kitchen entrance.";
        g2.drawString(str, (width  / 4) + fontWidth + 40, (height  / 4) + (lineSize * lineCounter++));

        g2.drawString("", 0, (height  / 4) + (lineSize * lineCounter++));

        str = "Enjoy the game and Good Luck!";
        bounds = fm.getStringBounds(str, g);
        fontWidth = (int)bounds.getWidth();
        g2.drawString(str, ((width - fontWidth) / 2), (height  / 4) + (lineSize * lineCounter));
    }
}