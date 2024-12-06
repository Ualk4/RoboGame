package frameWork;

import org.python.jline.internal.Log;
import program.Control;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LogFrame extends JFrame {

    LogFrame(){
        this.setTitle("LOGGED DATA");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setAlwaysOnTop(true);
        this.setSize(500, 500);
        this.setLocation((Control.frame.getWidth() - this.getWidth()) / 2, (Control.frame.getHeight() - this.getHeight()) / 2);
        this.setIconImage(new ImageIcon("images/log.png").getImage());

        JTextArea logTxt = new JTextArea();
        try {
            logTxt.setText(Control.logger.loadLog());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        logTxt.setWrapStyleWord(true);
        logTxt.setLineWrap(true);
        logTxt.setEditable(false);
        logTxt.setOpaque(false);
        logTxt.setCaretPosition(0);

        JScrollPane helpScrollPane = new JScrollPane(logTxt);
        this.add(helpScrollPane, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
