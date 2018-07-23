package interview.tree;

import com.google.common.collect.Lists;

import java.util.Deque;

public class TreeTraverse {
    
    private final static TreeNode<String> TREE1 = TreeNode.<String>builder()
            .value("A")
            .left(TreeNode.<String>builder()
                    .value("B")
                    .right(TreeNode.<String>builder()
                            .value("C")
                            .left(TreeNode.<String>builder().value("D").build())
                            .build())
                    .build())
            .right(TreeNode.<String>builder()
                    .value("E")
                    .right(TreeNode.<String>builder()
                            .value("F")
                            .left(TreeNode.<String>builder()
                                    .value("G")
                                    .left(TreeNode.<String>builder().value("H").build())
                                    .right(TreeNode.<String>builder().value("K").build())
                                    .build())
                            .build())
                    .build())
            .build();
    
    public static void main(String[] args) {
        TreeTraverse tt = new TreeTraverse();
        tt.preOrder(TREE1);
        System.out.println();
        tt.preOrderNoRec(TREE1);
        System.out.println("\n");
        
        tt.midOrder(TREE1);
        System.out.println();
        tt.midOrderNoRec(TREE1);
        System.out.println("\n");
        
        tt.postOrder(TREE1);
        System.out.println();
        System.out.println("\n");
    }
    
    public TreeTraverse() {
    
    }
    
    private void preOrder(TreeNode<String> tree) {
        if (tree == null) {
            return;
        }
        visitNode(tree);
        preOrder(tree.getLeft());
        preOrder(tree.getRight());
    }
    
    private void midOrder(TreeNode<String> tree) {
        if (tree == null) {
            return;
        }
        midOrder(tree.getLeft());
        visitNode(tree);
        midOrder(tree.getRight());
    }
    
    private void postOrder(TreeNode<String> tree) {
        if (tree == null) {
            return;
        }
        postOrder(tree.getLeft());
        postOrder(tree.getRight());
        visitNode(tree);
    }
    
    private void visitNode(TreeNode<String> node) {
        System.out.print(node.getValue());
    }
    
    private void preOrderNoRec(TreeNode<String> tree) {
        Deque<TreeNode<String>> stack = Lists.newLinkedList();
        stack.push(tree);
        while (!stack.isEmpty()) {
            TreeNode<String> node = stack.pop();
            if (node == null) {
                continue;
            }
            visitNode(node);
            stack.push(node.getRight());
            stack.push(node.getLeft());
        }
    }
    
    private void midOrderNoRec(TreeNode<String> tree) {
        Deque<TreeNode<String>> stack = Lists.newLinkedList();
        stack.push(tree);
        while (!stack.isEmpty()) {
        
        }
    }
}