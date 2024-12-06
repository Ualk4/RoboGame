package program;

import entities.Entity;
import entities.MrMitten;
import frameWork.GameFrame;

import java.io.IOException;


public class Game {

    GameFrame gameFrame;

    void Start() {
        gameFrame = new GameFrame();
        while (Control.map == null) {
            Control.setKeyState("CHOOSE A MAP AND LOAD IT!");
            try {
                Thread.sleep(100);
            } catch (Exception ignored) {}
        }
        Control.setKeyState("START game or STEP to take a turn!");
        while (Control.map.isThereSavable()) {
            try {
                Thread.sleep(100);
            } catch (Exception ignored) {}
            Game();
        }
        Control.setCurrentState("Game over, theres no more savable item!");
    }
    void Game() {
        Thread t;
        if (Control.isStarted() || Control.isStep() && (!Control.isPaused())) {
            Control.logger.addToLog("\n");
            Control.logger.addToLog(Control.getTurnCounter() + ". turn");
            Control.setKeyState("");
            int counter = 1;
            for (Entity character : Control.map.getAllCharacters()) {
                if(!Control.isFreestyleOn()){
                    while (Control.isMoving()){
                        Control.setKeyState("MOVING");
                        try {
                            Thread.sleep(10);
                        } catch (Exception ignored) {}
                    }
                    Control.setKeyState("");
                }
                if (character.getTypeMovable().equals("MrMitten")) {
                    character.setPngName("images/MrMitten/basic_current.png");
                    Control.setCurrentCharacter(". character ");
                    Control.setCurrentCharacterNumber(String.valueOf(counter));
                    Control.map.setCurrentCharacter((MrMitten) character);
                }
                if (character.getTypeMovable().equals("Enemy")) {
                    if (Control.map.getEnemyCharacters().get(0).equals(character)) {
                        counter = 1;
                    }
                    character.setPngName("images/enemy_current.png");
                    Control.setCurrentCharacter(". Enemy character ");
                    Control.setCurrentCharacterNumber(String.valueOf(counter));
                }
                try {
                    Thread.sleep( 100 * (11 - (Control.getSpeedRate() % 11)));
                } catch (Exception ignored) {}
                Control.mapPanel.repaint();
                Control.setMoving(true);
                counter++;
                t = new Thread(character);
                t.start();
                if((Control.isFreestyleOn()) && (character.getTypeMovable().equals("MrMitten"))) {
                    while (!Control.isKeyPressed()) {
                        Control.setKeyState("PLEASE PRESS KEY");
                        Control.mapPanel.repaint();
                    }
                    Control.setKeyState("PLEASE WAIT");
                    Control.setKeyPressed(false);
                }
                Control.mapPanel.repaint();
                try {
                    Control.logger.saveLog();
                } catch (IOException ignored) {}
            }
            Control.map.stepTime();
            Control.setStep(false);
        }
    }
}