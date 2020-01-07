package slides;

public class Stanza {
    private String stanzaName;
    private String lyrics;
    private Stanza overflow;
    private int lines;

    public Stanza(String title) {
        stanzaName = title;
        overflow = null;
        lyrics = "";
        lines = 0;
    }

    public void addLyricLine(String lyric) {
        lines++;
        if (lines > 5) {
            autoFormatLyrics(lyric);
        } else {
            lyrics = lyrics.concat(lyric + "\n");
        }
    }

    private void autoFormatLyrics(String line) {
        if (overflow == null) {
            overflow = new Stanza(stanzaName);
        }
        overflow.addLyricLine(line);
    }

    public boolean hasOverflow() {
        return overflow != null;
    }

    public String toString() {
        return lyrics + "\n";
    }

    public Stanza getOverflow() {
        return overflow;
    }

    public String getStanzaName() {
        return stanzaName;
    }
}
