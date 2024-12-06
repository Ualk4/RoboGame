package fileManagers;

import java.io.*;
import java.util.ArrayList;

public class LogFile {

    String log = "";

    public String loadLog() throws IOException {
        FileReader fr = new FileReader("files/log.txt");
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

    public void setLog(String line){
        this.log += line;
    }

    public void addToLog(String line){
        this.log += line + "\n";
    }

    public void saveLog() throws IOException {
        FileWriter fw = new FileWriter("files/log.txt");
        PrintWriter pw = new PrintWriter(fw);
        pw.print(this.log);
        pw.close();
    }
}