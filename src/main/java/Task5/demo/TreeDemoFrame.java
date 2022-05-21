package Task5.demo;


import Task5.*;
import Task5.bst.BSTree;
import Task5.bst.SimpleBSTree;
import Task5.bst.SimpleBSTreeMap;
import Task5.bst.avl.AVLTreeMap;
import Task5.bst.rb.RBTreeMap;
import util.ArrayUtils;
import util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Map;

public class TreeDemoFrame extends JFrame {
    private JPanel panelMain;
    private JButton buttonPreOrderTraverse;
    private JButton buttonInOrderTraverse;
    private JButton buttonPostOrderTraverse;
    private JButton buttonByLevelTraverse;
    private JTextArea textAreaSystemOut;
    private JTextField textFieldBracketNotationTree;
    private JButton buttonMakeTree;
    private JButton buttonMakeBSTree;
    private JSplitPane splitPaneMain;
    private JTextField textFieldValues;
    private JSpinner spinnerRandomCount;
    private JButton buttonRandomGenerate;
    private JButton buttonSortValues;
    private JButton buttonMakeBSTree2;
    private JButton buttonMakeAVLTree;
    private JButton buttonMakeRBTree;
    private JTextField textFieldSingleValue;
    private JButton buttonAddValue;
    private JButton buttonRemoveValue;
    private JPanel panelPaintArea;
    private JButton buttonSaveImage;
    private JButton buttonToBracketNotation;
    private JCheckBox checkBoxTransparent;
    private JSpinner spinnerSingleValue;
    private JButton buttonSetGradient;

    private JMenuBar menuBarMain;
    private JPanel paintPanel = null;
    private JFileChooser fileChooserSave;

    MyBinaryTree<Integer> tree = new SimpleBinaryTree<>();


    public TreeDemoFrame() {
        this.setTitle("Двоичные деревья");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        createMenu();

        splitPaneMain.setDividerLocation(0.5);
        splitPaneMain.setResizeWeight(1.0);
        splitPaneMain.setBorder(null);

        paintPanel = new JPanel() {
            private Dimension paintSize = new Dimension(0, 0);

            @Override
            public void paintComponent(Graphics gr) {
                super.paintComponent(gr);
                paintSize = BinaryTreePainter.paint(tree, gr);
                if (!paintSize.equals(this.getPreferredSize())) {
                    SwingUtils.setFixedSize(this, paintSize.width, paintSize.height);
                }
            }
        };
        JScrollPane paintJScrollPane = new JScrollPane(paintPanel);
        panelPaintArea.add(paintJScrollPane);

        fileChooserSave = new JFileChooser();
        fileChooserSave.setCurrentDirectory(new File("./images"));
        FileFilter filter = new FileNameExtensionFilter("SVG images", "svg");
        fileChooserSave.addChoosableFileFilter(filter);
        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        spinnerRandomCount.setValue(30);
        spinnerSingleValue.setValue(10);

        buttonMakeTree.addActionListener(actionEvent -> {
            try {
                SimpleBinaryTree<Integer> tree = new SimpleBinaryTree<>(Integer::parseInt);
                tree.fromBracketNotation(textFieldBracketNotationTree.getText());
                this.tree = tree;
                repaintTree();
            } catch (Exception ex) {
                SwingUtils.showErrorMessageBox(ex);
            }
        });
        buttonMakeBSTree.addActionListener(actionEvent -> {
            try {
                SimpleBSTree<Integer> tree = new SimpleBSTree<>(Integer::parseInt);
                tree.fromBracketNotation(textFieldBracketNotationTree.getText());
                this.tree = tree;
                repaintTree();
            } catch (Exception ex) {
                SwingUtils.showErrorMessageBox(ex);
            }
        });

        buttonRandomGenerate.addActionListener(actionEvent -> {
            int size = ((Integer) spinnerRandomCount.getValue()).intValue();
            int[] arr = ArrayUtils.createRandomIntArray(size, (size <= 50) ? 100 : 1000);
            textFieldValues.setText(ArrayUtils.toString(arr));
        });
        buttonSortValues.addActionListener(actionEvent -> {
            try {
                int[] arr = ArrayUtils.toIntArray(textFieldValues.getText());
                Arrays.sort(arr);
                textFieldValues.setText(ArrayUtils.toString(arr));
            } catch (Exception ex) {
                SwingUtils.showErrorMessageBox(ex);
            }
        });

        buttonMakeBSTree2.addActionListener(actionEvent -> {
            try {
                makeBSTFromValues(new SimpleBSTree<>(Integer::parseInt));
            } catch (Exception ex) {
                SwingUtils.showErrorMessageBox(ex);
            }
        });
//        buttonMakeAVLTree.addActionListener(actionEvent -> {
//            try {
//                makeBSTFromValues(new AVLTree<>());
//            } catch (Exception ex) {
//                SwingUtils.showErrorMessageBox(ex);
//            }
//        });
//        buttonMakeRBTree.addActionListener(actionEvent -> {
//            try {
//                makeBSTFromValues(new RBTree<>());
//            } catch (Exception ex) {
//                SwingUtils.showErrorMessageBox(ex);
//            }
//        });

        buttonAddValue.addActionListener(actionEvent -> {
            if (!(tree instanceof BSTree)) {
                SwingUtils.showInfoMessageBox("Текущее дерево не является деревом поиска!");
                return;
            }
            try {
                int value = Integer.parseInt(spinnerSingleValue.getValue().toString());
                ((BSTree<Integer>) tree).put(value);
                repaintTree();
            } catch (Exception ex) {
                SwingUtils.showErrorMessageBox(ex);
            }
        });
        buttonRemoveValue.addActionListener(actionEvent -> {
            if (!(tree instanceof BSTree)) {
                SwingUtils.showInfoMessageBox("Текущее дерево не является деревом поиска!");
                return;
            }
            try {
                int value = Integer.parseInt(spinnerSingleValue.getValue().toString());
                ((BSTree<Integer>) tree).remove(value);
                repaintTree();
            } catch (Exception ex) {
                SwingUtils.showErrorMessageBox(ex);
            }
        });

        buttonToBracketNotation.addActionListener(actionEvent -> {
            if (tree == null) {
                return;
            }
            textFieldBracketNotationTree.setText(tree.toBracketStr());
        });

        buttonSaveImage.addActionListener(actionEvent -> {
            if (tree == null) {
                return;
            }
            try {
                if (fileChooserSave.showSaveDialog(TreeDemoFrame.this) == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooserSave.getSelectedFile().getPath();
                    if (!filename.toLowerCase().endsWith(".svg")) {
                        filename += ".svg";
                    }
                    BinaryTreePainter.saveIntoFile(tree, filename, checkBoxTransparent.isSelected());
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        buttonPreOrderTraverse.addActionListener(actionEvent -> {
            showSystemOut(() -> {
                System.out.println("Посетитель:");
                BinaryTreeAlgorithms.preOrderVisit(tree.getRoot(), (value, level) -> {
                    System.out.println(value + " (уровень " + level + ")");
                });
                /*
                // эквивалентная запись без лямбда-выражений
                class InnerVisitor implements BinaryTreeAlgorithms.Visitor<Integer> {
                    @Override
                    public void visit(Integer value, int level) {
                        System.out.println(value + " (уровень " + level + ")");
                    }
                }
                BinaryTreeAlgorithms.preOrderVisit(tree.getRoot(), new InnerVisitor());
                */
                System.out.println();
                System.out.println("Итератор:");
                for (Integer i : BinaryTreeAlgorithms.preOrderValues(tree.getRoot())) {
                    System.out.println(i);
                }
            });
        });
        buttonInOrderTraverse.addActionListener(actionEvent -> {
            showSystemOut(() -> {
                System.out.println("Посетитель:");
                BinaryTreeAlgorithms.inOrderVisit(tree.getRoot(), (value, level) -> {
                    System.out.println(value + " (уровень " + level + ")");
                });
                System.out.println();
                System.out.println("Итератор:");
                for (Integer i : BinaryTreeAlgorithms.inOrderValues(tree.getRoot())) {
                    System.out.println(i);
                }
            });
        });
        buttonPostOrderTraverse.addActionListener(actionEvent -> {
            showSystemOut(() -> {
                System.out.println("Посетитель:");
                BinaryTreeAlgorithms.postOrderVisit(tree.getRoot(), (value, level) -> {
                    System.out.println(value + " (уровень " + level + ")");
                });
                System.out.println();
                System.out.println("Итератор:");
                for (Integer i : BinaryTreeAlgorithms.postOrderValues(tree.getRoot())) {
                    System.out.println(i);
                }
            });
        });
        buttonByLevelTraverse.addActionListener(actionEvent -> {
            showSystemOut(() -> {
                System.out.println("Посетитель:");
                BinaryTreeAlgorithms.byLevelVisit(tree.getRoot(), (value, level) -> {
                    System.out.println(value + " (уровень " + level + ")");
                });
                System.out.println();
                System.out.println("Итератор:");
                for (Integer i : BinaryTreeAlgorithms.byLevelValues(tree.getRoot())) {
                    System.out.println(i + " " + tree.getHeight());
                }
            });
        });
        buttonSetGradient.addActionListener(actionEvent -> {
            tree.setGradient(Color.yellow, Color.red);
            // bigger tree:      8 (6 (4 (5 (1(2, 5), 2(7 (1(,3(5(3)))),3))), 6), 5 (, 5 (2(4), 8(12, 17))))
            repaintTree();
        });
    }

    /**
     * Создание меню
     */
    private void createMenu() {
        JMenu menuTesting = new JMenu("Тестирование");
        Class[] mapClasses = {SimpleBSTreeMap.class, AVLTreeMap.class, RBTreeMap.class};
        for (Class mapClass : mapClasses) {
            JMenuItem menuItem = new JMenuItem("Корректность " + mapClass.getSimpleName());
            menuItem.addActionListener(actionEvent -> {
                try {
                    Map<Integer, Integer> map = (Map<Integer, Integer>) mapClass.getConstructor().newInstance();
                    showSystemOut(() -> {
                        MapTest.testCorrect(map);
                    });
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            });
            menuTesting.add(menuItem);
        }

        menuBarMain = new JMenuBar();
        menuBarMain.add(menuTesting);
        setJMenuBar(menuBarMain);
    }

    /**
     * Перерисовка дерева
     */
    public void repaintTree() {
        //panelPaintArea.repaint();
        paintPanel.repaint();
        //panelPaintArea.revalidate();
    }

    /**
     * Выполнение действия с выводом стандартного вывода в окне (textAreaSystemOut)
     *
     * @param action Выполняемое действие
     */
    private void showSystemOut(Runnable action) {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(baos, true, "UTF-8"));

            action.run();

            textAreaSystemOut.setText(baos.toString("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            SwingUtils.showErrorMessageBox(e);
        }
        System.setOut(oldOut);
    }

    /**
     * Заполнить дерево добавлением всех элементов (textFieldValues)
     *
     * @param tree Дерево
     */
    private void makeBSTFromValues(BSTree<Integer> tree) {
        int[] values = ArrayUtils.toIntArray(textFieldValues.getText());
        tree.clear();
        for (int v : values) {
            tree.put(v);
        }
        this.tree = (MyBinaryTree<Integer>) tree;
        repaintTree();
    }
}
