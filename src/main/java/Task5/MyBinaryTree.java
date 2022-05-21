package Task5;

import Task5.BinaryTree;

import java.awt.*;

public interface MyBinaryTree<T> extends BinaryTree<T> {
    interface MyTreeNode<T> extends BinaryTree.TreeNode<T> {
        void setColor(Color color);
    }
    int getHeight();
    void setGradient(Color topColor, Color bottomColor);
}
