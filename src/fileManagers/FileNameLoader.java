package fileManagers;

import java.io.File;
import java.util.*;

public class FileNameLoader {
    ArrayList<String> maps;


    public ArrayList<String> getMaps() {
        return maps;
    }

    public String[] loadMapStrings() {
        File mapsFolder = new File("files/maps");
        File[] mapNames = mapsFolder.listFiles();
        ArrayList<String> mapList = new ArrayList<>();
        for (File mapName : mapNames)
            if (mapName.isFile()) {
                mapList.add(mapName.getName());
            }

        return mapList.toArray(new String[mapList.size()]);
    }

    public String[] loadScriptStrings() {
        File scriptFolder = new File("files/scripts");
        File[] scriptNames = scriptFolder.listFiles();
        ArrayList<String> scriptList = new ArrayList<>();
        for (File scriptName : scriptNames) {
            if (scriptName.isDirectory()) {
                File scriptSubFolder = new File(scriptName + "/");
                File[] scriptSubNames = scriptSubFolder.listFiles();
                Arrays.sort(scriptSubNames, Comparator.comparingLong(File::lastModified).reversed());
                for (File scriptSubName : scriptSubNames) {
                    if (scriptName.isDirectory())
                        scriptList.add(scriptSubName.getName());
                    else if (scriptName.isFile()) {
                        scriptList.add(scriptName.getName());
                    }
                }
            }
        }
        return scriptList.toArray(new String[scriptList.size()]);
    }
}