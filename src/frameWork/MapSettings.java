package frameWork;

import fileManagers.FileNameLoader;
import fileManagers.MapLoader;
import program.Control;

import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.SliderUI;
import javax.swing.plaf.basic.*;
import javax.swing.plaf.metal.MetalSliderUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.Objects;

import static program.Control.frame;
import static program.Control.map;

public class MapSettings extends JPanel {
    JPanel listS = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

    JPanel buttons = new JPanel(new GridLayout(1, 4, 0, 0));
    JPanel buttons2 = new JPanel(new GridLayout(1, 2, 0, 0));

    JButton loadMap = new JButton(new ImageIcon("images/button/load_button.png"));
    FileNameLoader fileNameLoader = new FileNameLoader();
    JComboBox<String> mapFileNames = new JComboBox<>(fileNameLoader.loadMapStrings());

    JSlider speed = new JSlider(JSlider.HORIZONTAL);

    JLabel helpIcon = new JLabel(), logIcon = new JLabel(), speedLabel = new JLabel("SPEED:");

    JButton auto = new JButton(new ImageIcon("images/button/auto_button.png"));
    JButton script = new JButton(new ImageIcon("images/button/usescript_button.png"));
    JButton start = new JButton(new ImageIcon("images/button/start_button.png"));

    JButton pause = new JButton(new ImageIcon("images/button/pause_button.png"));

    JButton stop = new JButton(new ImageIcon("images/button/stop_button.png"));
    JButton step = new JButton(new ImageIcon("images/button/step_button.png"));
    MapLoader mapLoader;

    JButton isSeeable = new JButton(new ImageIcon("images/button/robotviewoff_button.png"));

    boolean helpFrameIsOpen = false, logFrameIsOpen = false;

    public MapSettings() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.setButton(loadMap);
        this.setButton(start);
        this.setButton(step);
        this.setButton(pause);
        this.setButton(stop);
        this.setButton(auto);
        this.setButton(script);
        this.setButton(isSeeable);

        logIcon.setIcon(new ImageIcon(new ImageIcon("images/log.png").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
        listS.add(logIcon);

        helpIcon.setIcon(new ImageIcon(new ImageIcon("images/help.png").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
        listS.add(helpIcon);

        mapFileNames.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
                basicComboPopup.setFont(new Font("Arial", Font.PLAIN, 15));
                basicComboPopup.setBorder(new LineBorder(new Color(200,100,80),1,true));
                JList list = basicComboPopup.getList();
                list.setForeground(new Color(200,100,80));
                list.setBackground(new Color(235,234,223));
                list.setSelectionForeground(new Color(235,234,223));
                list.setSelectionBackground(new Color(200,100,80));
                return basicComboPopup;
            }
            @Override protected JButton createArrowButton() {
                JButton arrowButton = new JButton();
                setButton(arrowButton);
                arrowButton.setIcon(new ImageIcon("images/arrow.png"));
                return arrowButton;
            }
        });
        mapFileNames.setPreferredSize(new Dimension(250, 35));
        mapFileNames.setBackground(new Color(235,234,223));
        mapFileNames.setForeground(new Color(200,100,80));
        mapFileNames.setBorder(BorderFactory.createLineBorder(new Color(200,100,80),1,true));
        mapFileNames.setFont(new Font("Arial", Font.PLAIN, 15));

        listS.add(mapFileNames);
        listS.add(loadMap);

        speedLabel.setForeground(new Color(200,100,80));
        speedLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        speed.setMinorTickSpacing(1);
        speed.setMajorTickSpacing(2);
        speed.setMinimum(1);
        speed.setMaximum(10);
        speed.setValue(1);
        speed.setUI(new CustomSliderUI(speed));
        listS.add(speedLabel);
        listS.add(speed);
        listS.add(isSeeable);

        start.setEnabled(false);
        step.setEnabled(false);
        auto.setEnabled(false);
        buttons.add(start);
        buttons.add(step);

        pause.setEnabled(false);
        stop.setEnabled(false);
        buttons.add(pause);
        buttons.add(stop);

        listS.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttons2.add(auto);
        buttons2.add(script);

        this.add(listS);
        this.add(buttons);
        this.add(buttons2);
        loadMap.addActionListener(e -> {
            try {
                if(Control.map != null) {
                    Control.map.setNull();
                }
                Control.mapName = mapFileNames.getSelectedItem().toString();
                mapLoader = new MapLoader(Control.mapName);
                Control.setControl(frame, Control.mainPane, Control.mapPanel, Control.scriptCardPanel, Control.scriptPanel);
                if (Control.isFreestyleOn()) {
                    Control.addKeyBindingsToFrame();
                } else {
                    Control.removeKeyBindingsToFrame();
                }
                Control.scriptPanel.setVisible(true);
                Control.mapPanel.removeAll();
                Control.mapPanel.add(new MapPanel());
                Control.setCurrentState("");
                Control.mapPanel.revalidate();
                Control.mapPanel.repaint();
                start.setEnabled(true);
                step.setEnabled(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        auto.addActionListener(e -> {
            Control.setFreestyleOn(true);
            script.setEnabled(true);
            auto.setEnabled(false);
            Control.addKeyBindingsToFrame();
            Control.scriptCardPanel.remove(Control.scriptPanel);
            Control.mainPane.setDividerLocation(getWidth());
            Control.scriptCardPanel.revalidate();
            Control.scriptCardPanel.repaint();
        });

        script.addActionListener(e -> {
            Control.setFreestyleOn(false);
            auto.setEnabled(true);
            script.setEnabled(false);
            Control.removeKeyBindingsToFrame();
            Control.scriptCardPanel.add(Control.scriptPanel);
            Dimension dim = getSize();
            int size = (int) (dim.getWidth() * (2.0 / 3.0));
            Control.mainPane.setDividerLocation(size);
            Control.scriptCardPanel.revalidate();
            Control.scriptCardPanel.repaint();
        });

        start.addActionListener(e -> {
            stop.setEnabled(true);
            pause.setEnabled(true);
            Control.setStarted(true);
            Control.setPaused(false);
        });

        step.addActionListener(e -> {
            stop.setEnabled(true);
            Control.setStep(true);
        });

        pause.addActionListener(e -> {
            Control.setPaused(true);
            Control.setStarted(false);
            stop.setEnabled(false);
            pause.setEnabled(false);
        });

        stop.addActionListener(e -> {
            Control.setStarted(false);
            Control.setTurnCounter();
            stop.setEnabled(false);
            pause.setEnabled(false);
            try {
                Thread.sleep(1000);
            } catch (Exception ignored) {}
            this.loadMap.doClick();
        });

        speed.addChangeListener(e -> Control.setSpeedRate(speed.getValue()));

        isSeeable.addActionListener(e -> {
            Control.setIsAllSeeable(!Control.isIsAllSeeable());
            if(Control.isIsAllSeeable()){
                isSeeable.setIcon(new ImageIcon("images/button/robotviewon_button.png"));
            } else {
                isSeeable.setIcon(new ImageIcon("images/button/robotviewoff_button.png"));
            }
            Control.mapPanel.revalidate();
            Control.mapPanel.repaint();
        });

        helpIcon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!helpFrameIsOpen) {
                    helpFrameIsOpen = true;
                    final JFrame frame = new HelpFrame();
                    frame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            helpFrameIsOpen = false;
                        }
                    });
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

        logIcon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!logFrameIsOpen) {
                    logFrameIsOpen = true;
                    final JFrame logFrame = new LogFrame();
                    logFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            logFrameIsOpen = false;
                        }
                    });

                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        isSeeable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if(Control.isIsAllSeeable()){
                    isSeeable.setIcon(new ImageIcon("images/button/robotviewon_button_hover.png"));
                } else {
                    isSeeable.setIcon(new ImageIcon("images/button/robotviewoff_button_hover.png"));
                }            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                if(Control.isIsAllSeeable()){
                    isSeeable.setIcon(new ImageIcon("images/button/robotviewon_button.png"));
                } else {
                    isSeeable.setIcon(new ImageIcon("images/button/robotviewoff_button.png"));
                }            }
        });

        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                start.setIcon(new ImageIcon("images/button/start_button_hover.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                start.setIcon(new ImageIcon("images/button/start_button.png"));
            }
        });

        step.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                step.setIcon(new ImageIcon("images/button/step_button_hover.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                step.setIcon(new ImageIcon("images/button/step_button.png"));
            }
        });

        loadMap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                loadMap.setIcon(new ImageIcon("images/button/load_button_hover.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                loadMap.setIcon(new ImageIcon("images/button/load_button.png"));
            }
        });

        pause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                pause.setIcon(new ImageIcon("images/button/pause_button_hover.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                pause.setIcon(new ImageIcon("images/button/pause_button.png"));
            }
        });

        stop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                stop.setIcon(new ImageIcon("images/button/stop_button_hover.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                stop.setIcon(new ImageIcon("images/button/stop_button.png"));
            }
        });

        auto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                auto.setIcon(new ImageIcon("images/button/auto_button_hover.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                auto.setIcon(new ImageIcon("images/button/auto_button.png"));
            }
        });

        script.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                script.setIcon(new ImageIcon("images/button/usescript_button_hover.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                script.setIcon(new ImageIcon("images/button/usescript_button.png"));
            }
        });
    }

    public void setButton(JButton button){
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }

    private static class CustomSliderUI extends BasicSliderUI {

        private static final int TRACK_HEIGHT = 10;
        private static final int TRACK_WIDTH = 10;
        private static final int TRACK_ARC = 10;
        private static final Dimension THUMB_SIZE = new Dimension(20, 20);
        private final RoundRectangle2D.Float trackShape = new RoundRectangle2D.Float();

        public CustomSliderUI(final JSlider b) {
            super(b);
        }

        @Override
        protected void calculateTrackRect() {
            super.calculateTrackRect();
            if (isHorizontal()) {
                trackRect.y = trackRect.y + (trackRect.height - TRACK_HEIGHT) / 2;
                trackRect.height = TRACK_HEIGHT;
            } else {
                trackRect.x = trackRect.x + (trackRect.width - TRACK_WIDTH) / 2;
                trackRect.width = TRACK_WIDTH;
            }
            trackShape.setRoundRect(trackRect.x, trackRect.y, trackRect.width, trackRect.height, TRACK_ARC, TRACK_ARC);
        }

        @Override
        protected void calculateThumbLocation() {
            super.calculateThumbLocation();
            if (isHorizontal()) {
                thumbRect.y = trackRect.y + (trackRect.height - thumbRect.height) / 2;
            } else {
                thumbRect.x = trackRect.x + (trackRect.width - thumbRect.width) / 2;
            }
        }

        @Override
        protected Dimension getThumbSize() {
            return THUMB_SIZE;
        }

        private boolean isHorizontal() {
            return slider.getOrientation() == JSlider.HORIZONTAL;
        }

        @Override
        public void paint(final Graphics g, final JComponent c) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            super.paint(g, c);
        }

        @Override
        public void paintTrack(final Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Shape clip = g2.getClip();

            boolean horizontal = isHorizontal();
            boolean inverted = slider.getInverted();

            // Paint shadow.
            g2.setColor(new Color(200, 100 ,80));
            g2.fill(trackShape);

            // Paint track background.
            g2.setColor(new Color(200, 200 ,200));
            g2.setClip(trackShape);
            trackShape.y += 1;
            g2.fill(trackShape);
            trackShape.y = trackRect.y;

            g2.setClip(clip);

            // Paint selected track.
            if (horizontal) {
                boolean ltr = slider.getComponentOrientation().isLeftToRight();
                if (ltr) inverted = !inverted;
                int thumbPos = thumbRect.x + thumbRect.width / 2;
                if (inverted) {
                    g2.clipRect(0, 0, thumbPos, slider.getHeight());
                } else {
                    g2.clipRect(thumbPos, 0, slider.getWidth() - thumbPos, slider.getHeight());
                }

            } else {
                int thumbPos = thumbRect.y + thumbRect.height / 2;
                if (inverted) {
                    g2.clipRect(0, 0, slider.getHeight(), thumbPos);
                } else {
                    g2.clipRect(0, thumbPos, slider.getWidth(), slider.getHeight() - thumbPos);
                }
            }
            g2.setColor(new Color(200,100,80));
            g2.fill(trackShape);
            g2.setClip(clip);
        }

        @Override
        public void paintThumb(final Graphics g) {
            g.setColor(new Color(255, 100, 80));
            g.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
        }

        @Override
        public void paintFocus(final Graphics g) {}
    }
}