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

    public Song(File songFile) throws Exception {
            Scanner input = new Scanner(songFile);
            stanzas = new ArrayList<Stanza>();
            songTitle = input.nextLine();
            lines = new ArrayList<String>();
            while (input.hasNextLine()) {
                lines.add(input.nextLine());
            }
            lineArray = new String[lines.size()];
            lines.toArray(lineArray);
            for (int i = 0; i < lineArray.length; i++) {
                String nextLine = lineArray[i];
                if (nextLine.contains("CCLI Song")) {
                    songArtist = lineArray[i+1];
                }
                if(nextLine.equals("") && lineArray[i+1].equals("")) {
                    makeStanza(i+2);
                }
            }
    }

    public String getTitleAndArtist() {
        return songTitle + ", " + songArtist;
    }

    private void makeStanza(int index) {
        Stanza temp = new Stanza(lineArray[index]);
        while (!lineArray[index+1].equals("")) {
            temp.addLyricLine(lineArray[index+1]);
            index++;
        }
        stanzas.add(temp);
    }

    public int getNumStanza() {
        return stanzas.size();
    }

    public Stanza getStanza(int num) {
        return stanzas.get(num);
    }
}
