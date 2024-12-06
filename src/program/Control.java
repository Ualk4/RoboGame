package program;

import fileManagers.LogFile;
import world.Map;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Control {
    public static Map map;
    public static JFrame frame;
    public static JPanel mapPanel;
    public static JPanel scriptPanel;
    public static JPanel scriptCardPanel;

    public static JTextArea scriptTxt;
    public static JComboBox<String> scriptFileNames;

    public static JSplitPane mainPane;
    public static String mapName = null;
    public static String scriptType = null;

    public static String currentState = "", currentCharacterNumber = "", currentCharacter = "", keyState = "";

    public static int speedRate = 1, turnCounter = 0;
    private static boolean isAllSeeable = false, paused = false, started = false, step = false, moving = false, keyPressed = false;

    private static boolean freestyleOn = true, logChanged = true;

    public static LogFile logger = new LogFile();

    public static void setControl(final JFrame frame, JSplitPane mainPane, JPanel mapPanel, JPanel scriptCardPanel, JPanel scriptPanel){
        Control.frame = frame;
        Control.mapPanel = mapPanel;
        Control.scriptCardPanel = scriptCardPanel;
        Control.scriptPanel = scriptPanel;
        Control.mainPane = mainPane;
    }

    public static void addKeyBindingsToFrame() {
        if(mapName != null) {
            InputMap inputMap = ((JPanel) frame.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = ((JPanel) frame.getContentPane()).getActionMap();

            if (started) {
                final String moveUp = "move_up";
                final String moveDown = "move_down";
                final String moveRight = "move_right";
                final String moveLeft = "move_left";
                final String pickUp = "pick_up";
                final String putDown = "put_down";

                Action moveUpAction = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        keyPressed = true;
                        map.getCurrentCharacter().moveUp();
                        mapPanel.revalidate();
                        mapPanel.repaint();
                    }
                };
                Action moveDownAction = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        keyPressed = true;
                        map.getCurrentCharacter().moveDown();
                        mapPanel.revalidate();
                        mapPanel.repaint();
                    }
                };
                Action moveRightAction = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        keyPressed = true;
                        map.getCurrentCharacter().moveRight();
                        mapPanel.revalidate();
                        mapPanel.repaint();
                    }
                };
                Action moveLeftAction = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        keyPressed = true;
                        map.getCurrentCharacter().moveLeft();
                        mapPanel.revalidate();
                        mapPanel.repaint();
                    }
                };

                Action pickUpAction = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        keyPressed = true;
                        map.getCurrentCharacter().pickUp();
                        mapPanel.revalidate();
                        mapPanel.repaint();
                    }
                };

                Action putDownAction = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        keyPressed = true;
                        map.getCurrentCharacter().putDown();
                        mapPanel.revalidate();
                        mapPanel.repaint();
                    }
                };

                inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), moveUp);
                actionMap.put(moveUp, moveUpAction);
                inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), moveDown);
                actionMap.put(moveDown, moveDownAction);
                inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), moveRight);
                actionMap.put(moveRight, moveRightAction);
                inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), moveLeft);
                actionMap.put(moveLeft, moveLeftAction);
                inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, false), pickUp);
                actionMap.put(pickUp, pickUpAction);
                inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), putDown);
                actionMap.put(putDown, putDownAction);
            }
        }
    }

    public static void removeKeyBindingsToFrame() {
        if (mapName != null) {
            InputMap inputMap = ((JPanel) frame.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = ((JPanel) frame.getContentPane()).getActionMap();

            inputMap.clear();
            actionMap.clear();
        }
    }
    public static boolean isPaused() {
        return paused;
    }

    public static boolean isStarted() {
        return started;
    }

    public static boolean isIsAllSeeable() {
        return isAllSeeable;
    }

    public static void setPaused(boolean paused) {
        Control.paused = paused;
    }

    public static void setStarted(boolean started) {
        Control.started = started;
    }

    public static void setIsAllSeeable(boolean isAllSeeable) {
        Control.isAllSeeable = isAllSeeable;
    }

    public static boolean isStep() {
        return step;
    }
    public static boolean isMoving() {
        return moving;
    }

    public static boolean isKeyPressed() {
        return keyPressed;
    }

    public static String getKeyState() {
        return keyState;
    }

    public static void setKeyState(String keyState) {
        Control.keyState = keyState;
    }

    public static void setKeyPressed(boolean keyPressed) {
        Control.keyPressed = keyPressed;
    }

    public static void setStep(boolean step) {
        Control.step = step;
    }
    public static void setMoving(boolean moving) {
        Control.moving = moving;
    }

    public static void setFreestyleOn(boolean freestyleOn) {
        Control.freestyleOn = freestyleOn;
    }

    public static boolean isFreestyleOn() {
        return freestyleOn;
    }

    public static String getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(String currentState) {
        Control.currentState = currentState;
    }

    public static void addToCurrentState(String currentState) {
        Control.currentState += currentState;
    }

    public static boolean isLogChanged() {
        return logChanged;
    }

    public static void setLogChanged(boolean logChanged) {
        Control.logChanged = logChanged;
    }

    public static int getSpeedRate() {
        return speedRate;
    }

    public static int getTurnCounter() {
        turnCounter++;
        return turnCounter;
    }

    public static void setTurnCounter() {
        Control.turnCounter = 0;
    }

    public static void setSpeedRate(int speedRate) {
        Control.speedRate = speedRate;
    }

    public static void setCurrentCharacter(String currentCharacter) {
        Control.currentCharacter = currentCharacter;
    }

    public static String getCurrentCharacter() {
        return currentCharacter;
    }

    public static String getCurrentCharacterNumber() {
        return currentCharacterNumber;
    }

    public static void setCurrentCharacterNumber(String currentCharacterNumber) {
        Control.currentCharacterNumber = currentCharacterNumber;
    }
}