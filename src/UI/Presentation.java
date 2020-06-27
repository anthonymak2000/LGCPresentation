package UI;

import UI.utilities.ExtensionFileFilter;
import com.formdev.flatlaf.FlatDarkLaf;
import slides.Runner;
import slides.Song;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Presentation {
    private JPanel rootPanel;
    private JPanel panel1;
    private JLabel titleLabel;
    private JButton createButton;
    private JButton templateButton;
    private JLabel templateLabel;
    private JButton wSUpload1;
    private JButton wSUpload2;
    private JButton wSUpload3;
    private JButton wSUpload4;
    private JButton wSUpload5;
    private JButton wSUpload6;
    private final JButton[] wSUploads = {wSUpload1, wSUpload2, wSUpload3, wSUpload4, wSUpload5, wSUpload6};
    private JLabel wSLabel1;
    private JLabel wSLabel2;
    private JLabel wSLabel3;
    private JLabel wSLabel4;
    private JLabel wSLabel5;
    private JLabel wSLabel6;
    private JButton wSAdd1;
    private JButton wSAdd2;
    private JButton wSAdd3;
    private JButton wSAdd4;
    private JButton wSAdd5;
    private JButton wSAdd6;
    private final JButton[] wSAdds = {wSAdd1, wSAdd2, wSAdd3, wSAdd4, wSAdd5, wSAdd6};
    private JButton wSRemove1;
    private JButton wSRemove2;
    private JButton wSRemove3;
    private JButton wSRemove4;
    private JButton wSRemove5;
    private JButton wSRemove6;
    private final JButton[] wSRemoves = {wSRemove1, wSRemove2, wSRemove3, wSRemove4, wSRemove5, wSRemove6};
    private JCheckBox covidCheckBox;
    private JPanel templatePanel;
    private JPanel wSPanel1;
    private JPanel wSPanel2;
    private JPanel wSPanel3;
    private JPanel wSPanel4;
    private JPanel wSPanel5;
    private JPanel wSPanel6;
    private final JPanel[] wSPanels = {wSPanel1, wSPanel2, wSPanel3, wSPanel4, wSPanel5, wSPanel6};
    private Song[] songs = new Song[6];
    private File template;
    private ArrayList<ArrayList<JComboBox>> comboArray = new ArrayList<>();
    private ArrayList<JComboBox> combo1 = new ArrayList<>();
    private ArrayList<JComboBox> combo2 = new ArrayList<>();
    private ArrayList<JComboBox> combo3 = new ArrayList<>();
    private ArrayList<JComboBox> combo4 = new ArrayList<>();
    private ArrayList<JComboBox> combo5 = new ArrayList<>();
    private ArrayList<JComboBox> combo6 = new ArrayList<>();
    private ArrayList[] combos = {combo1, combo2, combo3, combo4, combo5, combo6};

    public Presentation() {
        wSUpload1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadSong(0);
            }
        });
        wSUpload2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadSong(1);
            }
        });
        wSUpload3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadSong(2);
            }
        });
        wSUpload4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadSong(3);
            }
        });
        wSUpload5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadSong(4);
            }
        });
        wSUpload6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadSong(5);
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!templateButton.getText().equals("Upload")) {
                    JFileChooser fc = new JFileChooser();
                    JFrame parent = new JFrame();
                    fc.setFileFilter(new ExtensionFileFilter("Powerpoint File (.pptx)", "pptx"));
                    fc.showSaveDialog(parent);
                    fc.setSelectedFile(addPPTXType(fc.getSelectedFile()));
                    System.out.println(fc.getSelectedFile().getPath());
                    Runner.makePowerpoint(songs, template, fc, comboArray, covidCheckBox.isSelected());
                } else {
                    JOptionPane.showMessageDialog(new JFrame("Error"), "Please choose your template", "Please choose your template", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        templateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File("C:/Users/Anthony Mak/Downloads"));
                fc.setDialogTitle("Choose Worship template");
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.setFileFilter(new ExtensionFileFilter("Powerpoint File (.pptx)", "pptx"));
                if (fc.showOpenDialog(templateButton) == JFileChooser.APPROVE_OPTION) {
                    templateButton.setText(fc.getSelectedFile().getName().substring(0, fc.getSelectedFile().getName().length() - 5));
                    template = fc.getSelectedFile();
                }
            }
        });

        wSAdd1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("button pressed");
                addComboBox(0);
            }
        });
        wSAdd2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComboBox(1);
            }
        });
        wSAdd3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComboBox(2);
            }
        });
        wSAdd4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComboBox(3);
            }
        });

        wSAdd5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComboBox(4);
            }
        });
        wSAdd6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComboBox(5);
            }
        });
        wSRemove1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeComboBox(0);
            }
        });
        wSRemove2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeComboBox(1);
            }
        });
        wSRemove3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeComboBox(2);
            }
        });
        wSRemove4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeComboBox(3);
            }
        });
        wSRemove5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeComboBox(4);
            }
        });
        wSRemove6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeComboBox(5);
            }
        });
    }

    private void uploadSong(int i) {
        try {
            buttonPressed(wSUploads[i], i);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addComboBox(int i) {
        JComboBox cB = new JComboBox(makeComboBox(i));
        combos[i].add(cB);
        wSRemoves[i].setEnabled(true);
        wSPanels[i].add(cB);
        wSPanels[i].validate();
        System.out.println("tada");
    }

    private void removeComboBox(int i) {
        JComboBox cB = (JComboBox) combos[i].remove(combos[i].size() - 1);
        wSPanels[i].remove(cB);
        wSPanels[i].validate();
        if (combos[i].size() == 0) {
            wSRemoves[i].setEnabled(false);
        }
        System.out.println(combos[i].size());
    }

    private void buttonPressed(JButton button, int i) throws Exception {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("C:/Users/Anthony Mak/Desktop"));
        fc.setDialogTitle("Choose worship song " + (i + 1) + ":");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setFileFilter(new ExtensionFileFilter("Text Files", "txt"));
        if (fc.showOpenDialog(button) == JFileChooser.APPROVE_OPTION) {
            button.setText(fc.getSelectedFile().getName().substring(0, fc.getSelectedFile().getName().length() - 4));
            songs[i] = new Song(fc.getSelectedFile());
            wSAdds[i].setEnabled(true);
            ArrayList<JComboBox> temp = combos[i];
            if (!temp.isEmpty()) {
                for (JComboBox jc : temp) {
                    wSPanels[i].remove(jc);
                    wSPanels[i].validate();
                }
                combos[i].clear();
            }
            comboArray.add(combos[i]);
        }
    }

    private File addPPTXType(File f) {
        String filePath = f.getPath();
        if (!filePath.toLowerCase().endsWith(".pptx")) {
            f = new File(filePath + ".pptx");
        }
        return f;
    }

    private String[] makeComboBox(int i) {
        String[] stanzas = new String[songs[i].getNumStanza()];
        for (int j = 0; j < songs[i].getNumStanza(); j++) {
            stanzas[j] = songs[i].getStanza(j).getStanzaName();
        }
        Arrays.sort(stanzas);
        return stanzas;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.err.println("Failed to initialize LaF");
        }
        JFrame frame = new JFrame("Presentation");
        frame.setContentPane(new Presentation().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
