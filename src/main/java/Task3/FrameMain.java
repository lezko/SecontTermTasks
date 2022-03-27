package Task3;

import util.JTableUtils;
import util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;


public class FrameMain extends JFrame {
    public static void main(String[] args) {
        new FrameMain();
    }

    private JPanel panelMain;
    private JTable tableInput;
    private JButton buttonSaveInputIntoFile;
    private JButton buttonAddToQueue;
    private JTextField textInput;
    private JButton buttonDeleteFromQueue;
    private JButton buttonReverse;
    private Queue<String> q;
    private List<String> list;

    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;


    public FrameMain() {
        this.setTitle("Reverse queue");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();

        q = new LinkedList<>();

        JTableUtils.initJTableForArray(tableInput, 70, false, false, false, false);
        //tableOutput.setEnabled(false);
        tableInput.setRowHeight(25);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        JTableUtils.writeArrayToJTable(tableInput, new String[]{""});

        this.pack();

        buttonAddToQueue.addActionListener(actionEvent -> {
            try {
                String elem = textInput.getText();
                if (Objects.equals(elem, "")) {
                    return;
                }
                q.add(elem);
                list = (List<String>) q;
                String[] strArr = new String[q.size()];
                for (int i = 0; i < q.size(); i++) {
                    strArr[i] = list.get(q.size() - i - 1);
                }
                JTableUtils.writeArrayToJTable(tableInput, strArr);
                textInput.setText("");
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        buttonDeleteFromQueue.addActionListener(actionEvent -> {
            try {
                q.poll();
                list = (List<String>) q;
                String[] strArr = new String[q.size()];
                for (int i = 0; i < q.size(); i++) {
                    strArr[i] = list.get(q.size() - i - 1);
                }
                JTableUtils.writeArrayToJTable(tableInput, strArr);
                textInput.setText("");
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        buttonReverse.addActionListener(actionEvent -> {
            try {
                q = Task.reverse(q);
                list = (List<String>) q;
                String[] strArr = new String[q.size()];
                for (int i = 0; i < q.size(); i++) {
                    strArr[i] = list.get(q.size() - i - 1);
                }
                JTableUtils.writeArrayToJTable(tableInput, strArr);
                textInput.setText("");
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        buttonSaveInputIntoFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    String[] array = JTableUtils.readStringArrayFromJTable(tableInput);
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    PrintWriter out = new PrintWriter(file);
                    list = (List<String>) q;
                    for (int i = 0; i < list.size(); i++) {
                        out.print(list.get(list.size() - i - 1) + " ");
                    }
                    out.close();
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });
    }
}
