package slides;

import java.util.Scanner;

public class Stanza {
    private String stanzaName;
    private String lyrics;
    private String formattedLyrics;
    private Stanza overflow;
    private int lines;
    private int linesPerSlide;

    public Stanza(String title, int limit) {
        stanzaName = title;
        overflow = null;
        lyrics = "";
        lines = 0;
        linesPerSlide = limit;
        formattedLyrics = "";
    }

    public void addLyricLine(String lyric) {
        lines++;
        lyrics = lyrics.concat(lyric + "\n");
    }

    private void setLinesPerSlide() {
        if (lines <= 6) {
            linesPerSlide = lines;
        } else if (lines % 4 == 0) {
            linesPerSlide = 4;
        } else if (lines % 5 == 0) {
            linesPerSlide = 5;
        } else if (lines % 6 == 0) {
            linesPerSlide = 6;
        } else {
            linesPerSlide = lines / (lines /  4);
        }
    }

    public void formatLyrics() {
        setLinesPerSlide();
        Scanner input = new Scanner(lyrics);
        for (int i = 0; i < lines; i++) {
            String lyric = input.nextLine();
            if (i >= linesPerSlide) {
                sendLyricToOverflow(lyric);
            } else {
                formattedLyrics = formattedLyrics.concat(lyric + "\n");
            }
        }
        if (hasOverflow()) {
            overflow.formatLyrics();
        }
    }

    private void sendLyricToOverflow(String line) {
        if (!hasOverflow()) {
            overflow = new Stanza(stanzaName, linesPerSlide);
        }
        overflow.addLyricLine(line);
    }

    public boolean hasOverflow() {
        return overflow != null;
    }

    public String toString() {
        return formattedLyrics + "\n";
    }

    public Stanza getOverflow() {
        return overflow;
    }

    public String getStanzaName() {
        return stanzaName;
    }
}
