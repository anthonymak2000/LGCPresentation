package slides;

import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.xslf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class Runner {
    public static void makePowerpoint(File[] songFolder, File template) {
        try {
            XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(template));
            int count = 1;
            for (File file : songFolder) {
                if (file != null) {
                    // Loading in the text file for worship song
                    System.out.println(file.getName());
                    Song temp = new Song(file);

                    // Getting the layout for the worship slide background
                    XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);

                    //Change this line according to what they label the slide master for songs
                    XSLFSlideLayout layout = defaultMaster.getLayout("Song" + count);

                    XSLFSlide slide = ppt.createSlide(layout);

                    // Putting title in first slide
                    XSLFTextShape title = slide.getPlaceholder(0);
                    title.clearText();
                    XSLFTextParagraph p = title.addNewTextParagraph();
                    XSLFTextRun r = p.addNewTextRun();
                    r.setText(temp.getTitleAndArtist());

                    // Emptying the body of the first slide
                    title = slide.getPlaceholder(1);
                    title.clearText();
                    r = title.addNewTextParagraph().addNewTextRun();
                    r.setText(" ");

                    // Making the slides with the lyrics
                    for (int i = 0; i < temp.getNumStanza(); i++) {
                        Stanza curr = temp.getStanza(i);
                        boolean go = false;
                        do {
                            slide = ppt.createSlide(layout);

                            // Fills lyrics
                            title = slide.getPlaceholder(1);
                            title.clearText();
                            r = title.addNewTextParagraph().addNewTextRun();
                            r.setText(curr.toString());

                            // Fills artist and title
                            title = slide.getPlaceholder(0);
                            title.clearText();
                            r = title.addNewTextParagraph().addNewTextRun();
                            r.setText(temp.getTitleAndArtist());

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
            }

            // Outputs the powerpoint
            FileOutputStream out = new FileOutputStream("Worship Songs.pptx");
            ppt.write(out);
            out.close();
            ppt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
