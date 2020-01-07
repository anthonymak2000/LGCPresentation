package UI;

import UI.utilities.ExtensionFileFilter;
import slides.Runner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class Presentation {
    private JPanel rootPanel;
    private JLabel titleLabel;
    private JButton worshipSongButton1;
    private JTextField testTextField;
    private JButton worshipSongButton2;
    private JLabel worshipSongLabel2;
    private JLabel worshipSongLabel1;
    private JButton worshipSongButton3;
    private JButton worshipSongButton4;
    private JLabel worshipSongLabel3;
    private JLabel worshipSongLabel4;
    private JLabel worshipSongLabel5;
    private JButton worshipSongButton5;
    private JComboBox bibleBook;
    private JButton createButton;
    private JButton templateButton;
    private JLabel templateLabel;
    private JPanel panel1;
    private int lastIndex = -1;
    private File[] songs = new File[5];
    private File template;

    public Presentation() {
        worshipSongButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new java.io.File("C:/Users/Anthony Mak/Desktop"));
                fc.setDialogTitle("Choose worship song 1:");
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.setFileFilter(new ExtensionFileFilter("Text Files", "txt"));
                if (fc.showOpenDialog(worshipSongButton1) == JFileChooser.APPROVE_OPTION) {
                    worshipSongButton1.setText(fc.getSelectedFile().getName().substring(0, fc.getSelectedFile().getName().length()-4));
                    songs[0] = fc.getSelectedFile();
                }
            }
        });
        worshipSongButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new java.io.File("C:/Users/Anthony Mak/Desktop"));
                fc.setDialogTitle("Choose worship song 2:");
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.setFileFilter(new ExtensionFileFilter("Text Files", "txt"));
                if (fc.showOpenDialog(worshipSongButton2) == JFileChooser.APPROVE_OPTION) {
                    worshipSongButton2.setText(fc.getSelectedFile().getName().substring(0, fc.getSelectedFile().getName().length()-4));
                    songs[1] = fc.getSelectedFile();
                }
            }
        });
        worshipSongButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new java.io.File("C:/Users/Anthony Mak/Desktop"));
                fc.setDialogTitle("Choose worship song 3:");
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.setFileFilter(new ExtensionFileFilter("Text Files", "txt"));
                if (fc.showOpenDialog(worshipSongButton3) == JFileChooser.APPROVE_OPTION) {
                    worshipSongButton3.setText(fc.getSelectedFile().getName().substring(0, fc.getSelectedFile().getName().length()-4));
                    songs[2] = fc.getSelectedFile();
                }
            }
        });
        worshipSongButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new java.io.File("C:/Users/Anthony Mak/Desktop"));
                fc.setDialogTitle("Choose worship song 4:");
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.setFileFilter(new ExtensionFileFilter("Text Files", "txt"));
                if (fc.showOpenDialog(worshipSongButton4) == JFileChooser.APPROVE_OPTION) {
                    worshipSongButton4.setText(fc.getSelectedFile().getName().substring(0, fc.getSelectedFile().getName().length() - 4));
                    songs[3] = fc.getSelectedFile();
                }
            }
        });
        worshipSongButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new java.io.File("C:/Users/Anthony Mak/Desktop"));
                fc.setDialogTitle("Choose worship song 4:");
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.setFileFilter(new ExtensionFileFilter("Text Files", "txt"));
                if (fc.showOpenDialog(worshipSongButton5) == JFileChooser.APPROVE_OPTION) {
                    worshipSongButton5.setText(fc.getSelectedFile().getName().substring(0, fc.getSelectedFile().getName().length()-4));
                    songs[4] = fc.getSelectedFile();
                }
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!templateButton.getText().equals("Upload")){
                    Runner.makePowerpoint(songs, template);
                }
            }
        });
        templateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new java.io.File("C:/Users/Anthony Mak/Desktop"));
                fc.setDialogTitle("Choose Worship template");
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.setFileFilter(new ExtensionFileFilter("Powerpoint File (.pptx)", "pptx"));
                if (fc.showOpenDialog(templateButton) == JFileChooser.APPROVE_OPTION) {
                    templateButton.setText(fc.getSelectedFile().getName().substring(0, fc.getSelectedFile().getName().length()-5));
                    template = fc.getSelectedFile();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Presentation");
        frame.setContentPane(new Presentation().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
