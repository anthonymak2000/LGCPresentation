package slides;

import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.xslf.usermodel.*;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class Runner {

    public static void makePowerpoint(Song[] songFolder, File template, JFileChooser fc) {
        try {
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
                XSLFSlideLayout layout = defaultMaster.getLayout("Song" + count);
                XSLFSlide slide = ppt.createSlide(layout);

                // Putting title in first slide
                setPlaceHolder(0, temp.getTitleAndArtist(), slide);

                // Emptying the body of the first slide
                setPlaceHolder(1," ", slide);

                // Making the slides with the lyrics
                for (int i = 0; i < temp.getNumStanza(); i++) {
                    Stanza curr = temp.getStanza(i);
                    boolean go;
                    do {
                        slide = ppt.createSlide(layout);

                        // Fills lyrics
                        setPlaceHolder(1, curr.toString(), slide);

                        // Fills artist and title
                        setPlaceHolder(0, temp.getTitleAndArtist(), slide);

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
