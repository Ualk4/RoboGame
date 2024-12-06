package fileManagers;

import methods.AStarSearch;
import entities.*;
import program.Control;
import world.Path;
import world.Tile;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

public class ScriptLoader {

    final JFrame errorFrame = new JFrame("Error massage:");
    JTextArea helpTxt = new JTextArea();
    JScrollPane errorScrollPane = new JScrollPane(helpTxt);

    boolean frameIsOpen = false;


    public String readByLine(String scriptFileName) throws IOException {
        String fileType = scriptFileName.substring(Math.max(scriptFileName.length() - 2, 0));
        FileReader fr = null;
        switch (fileType) {
            case "js" :
                fr = new FileReader("files/scripts/javaScript/"  + scriptFileName);
                break;
            case "py" :
                fr = new FileReader("files/scripts/python/"  + scriptFileName);
                break;
            case "rb" :
                fr = new FileReader("files/scripts/ruby/"  + scriptFileName);
                break;
        }
        BufferedReader br = new BufferedReader(fr);
        ArrayList<String> lines = new ArrayList<>();
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            lines.add(line);
        }
        br.close();
        return String.join("\n", lines);
    }

    public void saveByLine(String scriptName, String scriptTxt) throws IOException {
        String fileType = scriptName.substring(Math.max(scriptName.length() - 2, 0));
        FileWriter fw = null;
        switch (fileType) {
            case "js" :
                fw = new FileWriter("files/scripts/javaScript/" + scriptName);
                break;
            case "py" :
                fw = new FileWriter("files/scripts/python/" + scriptName);
                break;
            case "rb" :
                fw = new FileWriter("files/scripts/ruby/" + scriptName);
                break;
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.print(scriptTxt);
        pw.close();
    }

    public void loadScriptFile(String scriptName){
        String fileType = scriptName.substring(Math.max(scriptName.length() - 2, 0));
        switch (fileType) {
            case "js" :
                loadJavaScriptFromFile(scriptName);
                break;
            case "py" :
                loadPythonScriptfromFile(scriptName);
                break;
            case "rb" :
                loadRubyScriptFromFile(scriptName);
                break;
        }
    }

    public void freeStyleScript(String scriptTxt, String scriptType){
        switch (scriptType) {
            case "js" :
                loadJavaScript(scriptTxt);
                break;
            case "py" :
                loadPythonScript(scriptTxt);
                break;
            case "rb" :
                loadRubyScript(scriptTxt);
                break;
        }
    }

    public void loadJavaScriptFromFile(String scriptFile){
        ScriptEngine nashorn = new ScriptEngineManager().getEngineByName("nashorn");
        File file = new File("files/scripts/javaScript/" + scriptFile);
        try {
            nashorn.put("path",file.getAbsolutePath().split(scriptFile)[0]);
            nashorn.put("isLastOne", Control.map.isLastOne());
            nashorn.put("isThereSavable", Control.map.isThereSavable());
            nashorn.put("singleTile", new Tile());
            nashorn.put("AStar", new AStarSearch());
            nashorn.put("singlePath", new Path());
            nashorn.put("singleItem", new Item());
            nashorn.put("singleFood", new Food());
            nashorn.put("singleNonFood", new NonFood());
            nashorn.put("singleCharacter", new MrMitten());
            nashorn.put("currentCharacter", Control.map.getCurrentCharacter());
            nashorn.put("singleEnemyCharacter", new Enemy());
            nashorn.put("characterArray", Control.map.getCharactersAsArray());
            if(scriptFile.contains(".js"))
                nashorn.eval(new FileReader("files/scripts/javaScript/" + scriptFile));
            else
                nashorn.eval(scriptFile);
        } catch (ScriptException | FileNotFoundException e) {
            Control.setStarted(false);
            errorMassage(e.toString());
        }
    }

    public void loadPythonScriptfromFile(String scriptFile){
        ScriptEngine jython = new ScriptEngineManager().getEngineByName("jython");
        try {
            jython.put("path","files/scripts/");
            jython.put("isLastOne", Control.map.isLastOne());
            jython.put("isThereSavable", Control.map.isThereSavable());
            jython.put("singleTile", new Tile());
            jython.put("AStar", new AStarSearch());
            jython.put("singlePath", new Path());
            jython.put("singleItem", new Item());
            jython.put("singleFood", new Food());
            jython.put("singleNonFood", new NonFood());
            jython.put("singleCharacter", new MrMitten());
            jython.put("currentCharacter", Control.map.getCurrentCharacter());
            jython.put("singleEnemyCharacter", new Enemy());
            jython.put("characterArray", Control.map.getCharacters().toArray());
            if(scriptFile.contains(scriptFile + ""))
                jython.eval(new FileReader("files/scripts/python/" + scriptFile));
            else
                jython.eval(scriptFile);
        } catch (ScriptException | FileNotFoundException e) {
            Control.setStarted(false);
            errorMassage(e.toString());

        }
    }

    public void loadRubyScriptFromFile(String scriptFile){
        ScriptEngine jruby = new ScriptEngineManager().getEngineByName("jruby");
        try {
            jruby.put("path","../../RoboGame/files/scripts/ruby/");
            jruby.put("isLastOne", Control.map.isLastOne());
            jruby.put("isThereSavable", Control.map.isThereSavable());
            jruby.put("singleTile", new Tile());
            jruby.put("AStar", new AStarSearch());
            jruby.put("singlePath", new Path());
            jruby.put("singleItem", new Item());
            jruby.put("singleFood", new Food());
            jruby.put("singleNonFood", new NonFood());
            jruby.put("singleCharacter", new MrMitten());
            jruby.put("currentCharacter", Control.map.getCurrentCharacter());
            jruby.put("singleEnemyCharacter", new Enemy());
            jruby.put("characterArray", Control.map.getCharacters().toArray());
            if(scriptFile.contains("."))
                jruby.eval(new FileReader("files/scripts/ruby/" +scriptFile));
            else
                jruby.eval(scriptFile);
        } catch (ScriptException | FileNotFoundException e) {
            Control.setStarted(false);
            errorMassage(e.toString());
        }
    }

    public void loadJavaScript(String scriptTxt){
        ScriptEngine nashorn = new ScriptEngineManager().getEngineByName("nashorn");
        nashorn.put("path","D:/bme/szakdolgozat/RoboGame/files/scripts/javaScript");
        nashorn.put("isLastOne", Control.map.isLastOne());
        nashorn.put("isThereSavable", Control.map.isThereSavable());
        nashorn.put("singleTile", new Tile());
        nashorn.put("AStar", new AStarSearch());
        nashorn.put("singlePath", new Path());
        nashorn.put("singleItem", new Item());
        nashorn.put("singleFood", new Food());
        nashorn.put("singleNonFood", new NonFood());
        nashorn.put("singleCharacter", new MrMitten());
        nashorn.put("currentCharacter", Control.map.getCurrentCharacter());
        nashorn.put("singleEnemyCharacter", new Enemy());
        nashorn.put("characterArray", Control.map.getCharacters().toArray());
        try {
            nashorn.eval(scriptTxt);
        } catch (ScriptException e) {
            Control.setStarted(false);
            errorMassage(e.toString());
        }
    }

    public void loadPythonScript(String scriptTxt){
        ScriptEngine jython = new ScriptEngineManager().getEngineByName("jython");
        jython.put("path","files/scripts/");
        jython.put("isLastOne", Control.map.isLastOne());
        jython.put("isThereSavable", Control.map.isThereSavable());
        jython.put("singleTile", new Tile());
        jython.put("AStar", new AStarSearch());
        jython.put("singlePath", new Path());
        jython.put("singleItem", new Item());
        jython.put("singleFood", new Food());
        jython.put("singleNonFood", new NonFood());
        jython.put("singleCharacter", new MrMitten());
        jython.put("currentCharacter", Control.map.getCurrentCharacter());
        jython.put("singleEnemyCharacter", new Enemy());
        jython.put("characterArray", Control.map.getCharacters().toArray());
        try {
            jython.eval(scriptTxt);
        } catch (ScriptException e) {
            Control.setStarted(false);
            errorMassage(e.toString());
        }
    }

    public void loadRubyScript(String scriptTxt){
        ScriptEngine jruby = new ScriptEngineManager().getEngineByName("jruby");
        jruby.put("path","../../RoboGame/files/scripts/ruby/");
        jruby.put("isLastOne", Control.map.isLastOne());
        jruby.put("isThereSavable", Control.map.isThereSavable());
        jruby.put("singleTile", new Tile());
        jruby.put("AStar", new AStarSearch());
        jruby.put("singlePath", new Path());
        jruby.put("singleItem", new Item());
        jruby.put("singleFood", new Food());
        jruby.put("singleNonFood", new NonFood());
        jruby.put("singleCharacter", new MrMitten());
        jruby.put("currentCharacter", Control.map.getCurrentCharacter());
        jruby.put("singleEnemyCharacter", new Enemy());
        jruby.put("characterArray", Control.map.getCharacters().toArray());
        try {
            jruby.eval(scriptTxt);
        } catch (ScriptException e) {
            Control.setStarted(false);
            errorMassage(e.toString());
        }
    }

    private void errorMassage(String exception){
        Control.setStarted(false);
        if (!frameIsOpen) {
            frameIsOpen = true;
            errorFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    frameIsOpen = false;
                }
            });
            errorFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            errorFrame.setLayout(new BorderLayout());
            errorFrame.setAlwaysOnTop(true);
            errorFrame.setSize(500, 500);
            errorFrame.setLocation((Control.frame.getWidth() - errorFrame.getWidth()) / 2, (Control.frame.getHeight() - errorFrame.getHeight()) / 2);
            errorFrame.setIconImage(new ImageIcon("images/warning.png").getImage());

            helpTxt.setText(exception);
            helpTxt.setWrapStyleWord(true);
            helpTxt.setLineWrap(true);
            helpTxt.setEditable(false);
            helpTxt.setOpaque(false);

            errorFrame.add(errorScrollPane, BorderLayout.CENTER);
            errorFrame.setVisible(true);
        }
    }
}