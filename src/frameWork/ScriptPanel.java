package frameWork;

import fileManagers.FileNameLoader;
import fileManagers.ScriptLoader;
import program.Control;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

import static program.Control.frame;

public class ScriptPanel extends JPanel {

    JPanel scriptChoosePanel = new JPanel(new CardLayout());
    JPanel chooseExample = new JPanel(new FlowLayout());
    JTextArea scriptTxt = new JTextArea("Try making your own code!",200,getWidth());
    JScrollPane scrollPane = new JScrollPane(scriptTxt);

    JPanel bottomPanel = new JPanel(new BorderLayout());
    JPanel buttonPanel = new JPanel(new GridLayout(1,3));

    JPanel buttons = new JPanel(new BorderLayout());
    JPanel buttonPanel_ScriptChoose = new JPanel(new GridLayout(1,2));

    JButton freeStyleButton = new JButton(new ImageIcon("images/button/freestyle_button.png"));
    JButton loadScriptsButton = new JButton(new ImageIcon("images/button/loadscript_button.png"));
    JButton loadScript = new JButton(new ImageIcon("images/button/load_button.png"));
    JButton clear = new JButton(new ImageIcon("images/button/clear_button.png"));
    JButton save = new JButton(new ImageIcon("images/button/save_button.png"));
    JPanel scriptButtons = new JPanel(new GridLayout(1,2,20,20));

    JButton javaScript = new JButton(new ImageIcon("images/button/javascript_button.png"));
    JButton python = new JButton(new ImageIcon("images/button/python_button.png"));
    JButton ruby = new JButton(new ImageIcon("images/button/ruby_button.png"));
    JComboBox<String> scriptFileNames;

    ScriptLoader scriptLoader = new ScriptLoader();
    FileNameLoader fileNameLoader = new FileNameLoader();

    String scriptType = null;

    String[] fileNames = fileNameLoader.loadScriptStrings();
    public ScriptPanel() {
        super();
        setLayout(new BorderLayout());


        scriptFileNames = new JComboBox<>(fileNames);
        scriptFileNames.setModel(new DefaultComboBoxModel<>(fileNames));

        //scriptChoosePanel.setBackground(new Color(100, 100, 200));
        int size = (int) ((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (1.0/3.0));
        scriptChoosePanel.setPreferredSize(new Dimension(size, 70));
        scriptFileNames.setPreferredSize(new Dimension((int) (size * (2.0/3.0)),30));

        scriptFileNames.setUI(new BasicComboBoxUI() {
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
        scriptFileNames.setBackground(new Color(235,234,223));
        scriptFileNames.setForeground(new Color(200,100,80));
        scriptFileNames.setBorder(BorderFactory.createLineBorder(new Color(200,100,80),1,true));
        scriptFileNames.setFont(new Font("Arial", Font.PLAIN, 15));

        chooseExample.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        chooseExample.add(scriptFileNames);
        chooseExample.add(loadScript);

        scriptTxt.setFont(new Font("Courier New", Font.BOLD, 16));
        scriptTxt.setLineWrap(true);
        scriptTxt.setWrapStyleWord(true);

        buttonPanel.add(clear);
        buttonPanel.add(save);

        scriptButtons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scriptButtons.add(javaScript);
        scriptButtons.add(python);
        scriptButtons.add(ruby);

        loadScriptsButton.setEnabled(false);

        buttonPanel_ScriptChoose.add(freeStyleButton);
        buttonPanel_ScriptChoose.add(loadScriptsButton);

        scriptChoosePanel.add(chooseExample);
        buttons.add(buttonPanel_ScriptChoose,BorderLayout.NORTH);
        buttons.add(scriptChoosePanel,BorderLayout.SOUTH);

        this.setButton(freeStyleButton);
        this.setButton(loadScriptsButton);
        this.setButton(loadScript);
        this.setButton(clear);
        this.setButton(save);
        this.setButton(javaScript);
        this.setButton(python);
        this.setButton(ruby);

        add(buttons,BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);


        loadScript.addActionListener(e -> {
            try {
                scriptTxt.setText(scriptLoader.readByLine(String.valueOf(scriptFileNames.getSelectedItem())));
                if(bottomPanel !=null)
                    bottomPanel.removeAll();
                bottomPanel.add(buttonPanel);
                bottomPanel.revalidate();
                bottomPanel.repaint();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        clear.addActionListener(e -> scriptTxt.setText(""));

        save.addActionListener(e -> {
            boolean nameOK = true;

            String firstOptionPanel = (String) JOptionPane.showInputDialog(
                    frame,
                    "Save file as:",
                    "Save file",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    loadScriptsButton.isEnabled() ? "" : scriptFileNames.getSelectedItem());

            if (firstOptionPanel != null) {
                while (nameOK) {
                    if(firstOptionPanel == null){
                        break;
                    }
                    String[] file_Name = firstOptionPanel.split("\\.");
                        if (firstOptionPanel.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Please fill in file name.", "Save as",
                                    JOptionPane.WARNING_MESSAGE);
                        } else if (file_Name.length > 2) {
                            JOptionPane.showMessageDialog(frame, "Please only use one file extension!", "Save as",
                                    JOptionPane.WARNING_MESSAGE);
                        } else if ((file_Name.length == 1) || ((!file_Name[1].equals("js")) && (!file_Name[1].equals("py")) && (!file_Name[1].equals("rb")))) {
                            String massage = "Please write file name that ends with: .js, .py or .rb!";
                            if (loadScriptsButton.isEnabled()) {
                                switch (scriptType) {
                                    case "js":
                                        massage = "Please write file name that ends with: .js!";
                                        break;
                                    case "py":
                                        massage = "Please write file name that ends with: .py!";
                                        break;
                                    case "rb":
                                        massage = "Please write file name that ends with: .rb!";
                                        break;
                                }
                            }
                            JOptionPane.showMessageDialog(frame, massage, "Save as",
                                    JOptionPane.WARNING_MESSAGE);
                        } else {
                            nameOK = false;
                        }
                        if (nameOK) {
                            firstOptionPanel = (String) JOptionPane.showInputDialog(
                                    frame,
                                    "Save file as:",
                                    "Save file",
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    null,
                                    loadScriptsButton.isEnabled() ? "" : scriptFileNames.getSelectedItem());
                        }

                    for (String scriptFileName : fileNames) {
                        if (scriptFileName.equals(firstOptionPanel)) {
                            int sameNameFileOptionPanel = JOptionPane.showConfirmDialog(
                                    frame,
                                    "File name already exist!\n" +
                                            "Would you like to rewrite exiting save file?",
                                    "Save file",
                                    JOptionPane.YES_NO_OPTION);

                            if (sameNameFileOptionPanel == JOptionPane.YES_OPTION) {
                                try {
                                    scriptLoader.saveByLine(firstOptionPanel, scriptTxt.getText());
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                fileNames = fileNameLoader.loadScriptStrings();
                                scriptFileNames.setModel(new DefaultComboBoxModel<>(fileNames));
                            } else {
                                nameOK = false;
                            }
                        }
                    }
                }
            }
            if(firstOptionPanel != null) {
                try {
                    scriptLoader.saveByLine(firstOptionPanel, scriptTxt.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                fileNames = fileNameLoader.loadScriptStrings();
                scriptFileNames.setModel(new DefaultComboBoxModel<>(fileNames));
            }
        });

        freeStyleButton.addActionListener(e -> {
            javaScript.setEnabled(true);
            python.setEnabled(true);
            ruby.setEnabled(true);
            loadScriptsButton.setEnabled(true);
            freeStyleButton.setEnabled(false);
            scriptTxt.setText("Please select a script type!");
            scriptType = null;
            scriptChoosePanel.removeAll();
            scriptChoosePanel.add(scriptButtons);
            if(bottomPanel !=null)
                bottomPanel.removeAll();
            bottomPanel.revalidate();
            bottomPanel.repaint();
            scriptChoosePanel.revalidate();
            scriptChoosePanel.repaint();
        });

        loadScriptsButton.addActionListener(e1 -> {
            loadScriptsButton.setEnabled(false);
            freeStyleButton.setEnabled(true);
            scriptTxt.setText("Please select a script file!");
            scriptType = null;
            scriptChoosePanel.removeAll();
            scriptChoosePanel.add(chooseExample);
            scriptChoosePanel.revalidate();
            scriptChoosePanel.repaint();
            if(bottomPanel !=null) {
                bottomPanel.removeAll();
                bottomPanel.revalidate();
                bottomPanel.repaint();
            }
        });

        javaScript.addActionListener(e -> {
            javaScript.setEnabled(false);
            python.setEnabled(true);
            ruby.setEnabled(true);
            scriptType = "js";
            scriptTxt.setText("Please write your JavaScript code!");
            if(bottomPanel !=null)
                bottomPanel.removeAll();
            bottomPanel.add(buttonPanel);
            bottomPanel.revalidate();
            bottomPanel.repaint();
            Control.scriptType = scriptType;
        });

        python.addActionListener(e -> {
            javaScript.setEnabled(true);
            python.setEnabled(false);
            ruby.setEnabled(true);
            scriptType = "py";
            scriptTxt.setText("Please write your Python code!");
            if(bottomPanel !=null)
                bottomPanel.removeAll();
            bottomPanel.add(buttonPanel);
            bottomPanel.revalidate();
            bottomPanel.repaint();
            Control.scriptType = scriptType;
        });

        ruby.addActionListener(e -> {
            javaScript.setEnabled(true);
            python.setEnabled(true);
            ruby.setEnabled(false);
            scriptType = "py";
            scriptTxt.setText("Please write your Ruby code!");
            if(bottomPanel !=null)
                bottomPanel.removeAll();
            bottomPanel.add(buttonPanel);
            bottomPanel.revalidate();
            bottomPanel.repaint();
            Control.scriptType = scriptType;
        });

        clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                clear.setIcon(new ImageIcon("images/button/clear_button_hover.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                clear.setIcon(new ImageIcon("images/button/clear_button.png"));
            }
        });

        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                save.setIcon(new ImageIcon("images/button/save_button_hover.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                save.setIcon(new ImageIcon("images/button/save_button.png"));
            }
        });

        loadScriptsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
               loadScriptsButton.setIcon(new ImageIcon("images/button/loadscript_button_hover.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                loadScriptsButton.setIcon(new ImageIcon("images/button/loadscript_button.png"));
            }
        });

        freeStyleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                freeStyleButton.setIcon(new ImageIcon("images/button/freestyle_button_hover.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                freeStyleButton.setIcon(new ImageIcon("images/button/freestyle_button.png"));
            }
        });

        loadScript.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                loadScript.setIcon(new ImageIcon("images/button/load_button_hover.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                loadScript.setIcon(new ImageIcon("images/button/load_button.png"));
            }
        });

        javaScript.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                javaScript.setIcon(new ImageIcon("images/button/javascript_button_hover.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                javaScript.setIcon(new ImageIcon("images/button/javascript_button.png"));
            }
        });

        python.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                python.setIcon(new ImageIcon("images/button/python_button_hover.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                python.setIcon(new ImageIcon("images/button/python_button.png"));
            }
        });

        ruby.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ruby.setIcon(new ImageIcon("images/button/ruby_button_hover.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                ruby.setIcon(new ImageIcon("images/button/ruby_button.png"));
            }
        });

        Control.scriptFileNames = scriptFileNames;
        Control.scriptTxt = scriptTxt;
    }

    public void setButton(JButton button){
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }
}