package frameWork;

import program.Control;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import java.awt.*;

public class GameFrame extends JFrame {

    public JPanel mapSettings = new MapSettings();
    public JPanel mapCardSettings = new JPanel(new CardLayout());
    public JPanel mapPanel = new JPanel(new CardLayout());
    public JPanel startPanel = new StartPanel();
    public JPanel scriptCardPanel = new JPanel(new CardLayout());
    public JPanel scriptPanel = new ScriptPanel();
    public JSplitPane mainPane;

    public GameFrame(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(GameFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout(5, 5));

        this.setIconImage(new ImageIcon("images/logo.png").getImage());

        mapPanel.setLayout(new CardLayout());
        mainPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mapPanel, scriptCardPanel);

        startPanel.setPreferredSize(new Dimension(1900,1000));
        mapPanel.add(startPanel);

        Control.setControl(this, mainPane, mapPanel,scriptCardPanel, scriptPanel);
        mapCardSettings.add(mapSettings);

        mainPane.setDividerSize(10);
        mainPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        BasicSplitPaneDivider divider = (BasicSplitPaneDivider) mainPane.getComponent(2);
        divider.setBackground(Color.white);
        divider.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 4));

        this.add(mapCardSettings, BorderLayout.NORTH);
        this.add(mainPane, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }
}