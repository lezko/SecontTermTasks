package Task3;

import util.ArrayUtils;
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
    private JButton buttonReverseMyQueue;
    private JButton buttonReverseStandardQueue;
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

        JTableUtils.initJTableForArray(tableInput, 70, false, false, false, true);
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

        buttonReverseMyQueue.addActionListener(actionEvent -> {
            try {
                MyQueue<String> q = new MyLinkedListQueue<>();
                reverseQueue(q);
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        buttonReverseStandardQueue.addActionListener(actionEvent -> {
            try {
                MyQueue<String> q = new ReverseQueue<>();
                reverseQueue(q);
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
                    for (int i = 0; i < array.length; i++) {
                        out.print(array[i] + " ");
                    }
                    out.close();
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });
    }

    private void reverseQueue(MyQueue<String> q) throws Exception {
        String[] strArr = JTableUtils.readStringArrayFromJTable(tableInput);
        for (String str: strArr) {
            q.add(str);
        }
        q.reverse();
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = q.remove();
        }

        JTableUtils.writeArrayToJTable(tableInput, strArr);
    }
}
