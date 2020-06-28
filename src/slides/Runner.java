package slides;

import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.xslf.usermodel.*;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class Runner {

    public static void makePowerpoint(Song[] songFolder, File template, JFileChooser fc, ArrayList<ArrayList<JComboBox>> comboArray, boolean covid) {
        try {
            int lyricPlaceholder = 1;
            int titlePlaceholder = 0;
            XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(template));
            int count = 1;
            for (Song temp : songFolder) {
                if (temp == null) {
                    continue;
                }
                // Loading in the text file for worship song
                System.out.println(temp.getTitleAndArtist());

                // Getting the layout for the worship slide background
                XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
                XSLFSlideLayout layout = defaultMaster.getLayout("Song" + count); // TODO may change depending on layout
                XSLFSlide slide = ppt.createSlide(layout);

                // Putting title in first slide
                String title = (covid) ? " " : temp.getTitleAndArtist();
                setPlaceHolder(titlePlaceholder, title, slide);

                // Emptying the body of the first slide
                String lyric = (covid) ? temp.getTitleAndArtist() : " ";
                setPlaceHolder(lyricPlaceholder, lyric, slide);

                // Making the slides with the lyrics
                ArrayList<JComboBox> currCombo = comboArray.get(count - 1);
                for (int i = 0; i < currCombo.size(); i++) {
                    Stanza curr = temp.getStanza(currCombo.get(i).getSelectedItem().toString());
                    boolean go;
                    do {
                        slide = ppt.createSlide(layout);

                        // Fills lyrics
                        setPlaceHolder(lyricPlaceholder, curr.toString(), slide);

                        // Fills artist and title
                        String artist = (covid) ? " " : temp.getTitleAndArtist();
                        setPlaceHolder(titlePlaceholder, artist, slide);

                        // Puts stanza name in notes
                        XSLFNotes note = ppt.getNotesSlide(slide);
                        for (XSLFTextShape shape : note.getPlaceholders()) {
                            if (shape.getTextType() == Placeholder.BODY) {
                                shape.setText(curr.getStanzaName());
                                break;
                            }
                        }

                        // Continues making slides if more lyrics exist for stanza
                        if (curr.hasOverflow()) {
                            curr = curr.getOverflow();
                            go = true;
                        } else {
                            go = false;
                        }

                    } while (go);
                }
                count++;
                System.out.println("Song successfully placed!");
            }
            // Outputs the powerpoint
            FileOutputStream out = new FileOutputStream(fc.getSelectedFile());
            ppt.write(out);
            out.close();
            ppt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setPlaceHolder(int placeHolder, String text, XSLFSlide slide) {
        XSLFTextShape title = slide.getPlaceholder(placeHolder);
        title.clearText();
        XSLFTextRun r = title.addNewTextParagraph().addNewTextRun();
        r.setText(text);
    }
}
