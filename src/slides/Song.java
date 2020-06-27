package slides;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Song {
    private String songTitle;
    private String songArtist;
    private ArrayList<String> lines;
    private ArrayList<Stanza> stanzas;
    private String[] lineArray;
    private String songInfo;
    private boolean isInfo;

    public Song(File songFile) throws Exception {
            songInfo = "";
            Scanner input = new Scanner(songFile);
            stanzas = new ArrayList<>();
            songTitle = input.nextLine();
            lines = new ArrayList<>();
            while (input.hasNextLine()) {
                lines.add(input.nextLine());
            }
            lineArray = new String[lines.size()];
            lines.toArray(lineArray);
            for (int i = 0; i < lineArray.length; i++) {
                String nextLine = lineArray[i];
                if (nextLine.contains("CCLI Song")) {
                    isInfo = true;
                    songArtist = "\"" + songTitle + "\" words and music by " + lineArray[i+1] + " " + lineArray[i+2] + " " + lineArray[i+4];
                }
                if (isInfo) {
                    songInfo += nextLine + "\n";
                }
                if (nextLine.contains("CCLI License")) {
                    isInfo = false;
                }
                if(nextLine.equals("") && lineArray[i+1].equals("")) {
                    makeStanza(i+2);
                }
            }
    }

    public String getTitleAndArtist() {
        //return songArtist;
        return songInfo;
    }

    private void makeStanza(int index) {
        Stanza temp = new Stanza(lineArray[index], 10);
        while (!(lineArray[index+1].equals("") && (lineArray[index+2].equals("") || lineArray[index+2].contains("CCLI Song")))) {
            if (lineArray[index+1].equals("")){
                index++;
                continue;
            }
            temp.addLyricLine(lineArray[index+1]);
            index++;
        }
        temp.formatLyrics();
        stanzas.add(temp);
    }

    public int getNumStanza() {
        return stanzas.size();
    }

    public Stanza getStanza(int num) {
        return stanzas.get(num);
    }
    public Stanza getStanza(String name) {
        for (Stanza stanza: stanzas) {
            if (stanza.getStanzaName().equals(name)) {
                return stanza;
            }
        }
        return null;
    }
}
