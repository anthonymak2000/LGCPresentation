package UI;

import UI.utilities.ExtensionFileFilter;
import slides.Runner;
import slides.Song;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class Presentation {
    private JPanel rootPanel;
    private JLabel titleLabel;
    private JButton wSUpload1;
    private JButton wSUpload2;
    private JButton wSUpload3;
    private JButton wSUpload4;
    private JButton wSUpload5;
    private JLabel wSLabel1;
    private JLabel wSLabel2;
    private JLabel wSLabel3;
    private JLabel wSLabel4;
    private JLabel wSLabel6;
    private JButton createButton;
    private JButton templateButton;
    private JLabel templateLabel;
    private JPanel panel1;
    private JButton wSAdd1;
    private JButton wSAdd2;
    private JButton wSAdd3;
    private JButton wSAdd4;
    private JButton wSAdd5;
    private JLabel wSLabel5;
    private JButton wSUpload6;
    private JButton wSAdd6;
    private Song[] songs = new Song[6];
    private File template;

    public Presentation() {
        wSUpload1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonPressed(wSUpload1, 0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        wSUpload2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonPressed(wSUpload2, 1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        wSUpload3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonPressed(wSUpload3, 2);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        wSUpload4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonPressed(wSUpload4, 3);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        wSUpload5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonPressed(wSUpload5, 4);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        wSUpload6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonPressed(wSUpload6, 5);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!templateButton.getText().equals("Upload")) {
                    JFileChooser fc = new JFileChooser();
                    JFrame parent = new JFrame();
                    fc.showSaveDialog(parent);
                    Runner.makePowerpoint(songs, template, fc);
                } else {
                    JOptionPane.showMessageDialog(new JFrame("Error"), "Please choose your template", "Please choose your template", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        templateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File("C:/Users/Anthony Mak/Desktop"));
                fc.setDialogTitle("Choose Worship template");
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.setFileFilter(new ExtensionFileFilter("Powerpoint File (.pptx)", "pptx"));
                if (fc.showOpenDialog(templateButton) == JFileChooser.APPROVE_OPTION) {
                    templateButton.setText(fc.getSelectedFile().getName().substring(0, fc.getSelectedFile().getName().length() - 5));
                    template = fc.getSelectedFile();
                }
            }
        });

    }

    public void buttonPressed(JButton button, int i) throws Exception {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("C:/Users/Anthony Mak/Desktop"));
        fc.setDialogTitle("Choose worship song " + (i+1) + ":");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setFileFilter(new ExtensionFileFilter("Text Files", "txt"));
        if (fc.showOpenDialog(button) == JFileChooser.APPROVE_OPTION) {
            button.setText(fc.getSelectedFile().getName().substring(0, fc.getSelectedFile().getName().length()-4));
            songs[i] = new Song(fc.getSelectedFile());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Presentation");
        frame.setContentPane(new Presentation().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
