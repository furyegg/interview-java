package interview.tree;

import com.google.common.collect.Lists;

import java.util.Deque;
import java.util.Queue;

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
        tt.postOrderNoRec(TREE1);
        System.out.println("\n");
    
        tt.levelOrder(TREE1);
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
        TreeNode<String> node = tree;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                visitNode(node);
                stack.push(node);
                node = node.getLeft();
            } else {
                node = stack.pop();
                node = node.getRight();
            }
        }
    }
    
    private void preOrderNoRec2(TreeNode<String> tree) {
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
        TreeNode<String> node = tree;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.getLeft();
            } else {
                node = stack.pop();
                visitNode(node);
                node = node.getRight();
            }
        }
    }
    
    private void midOrderNoRec2(TreeNode<String> tree) {
        Deque<TreeNode<String>> stack = Lists.newLinkedList();
        TreeNode<String> node = tree;
        boolean poppedNode = false;
        while (node != null || !stack.isEmpty()) {
            if (node.hasLeft() && !poppedNode) {
                stack.push(node);
                node = node.getLeft();
                poppedNode = false;
                continue;
            }
            
            visitNode(node);
            
            if (node.hasRight()) {
                node = node.getRight();
                poppedNode = false;
                continue;
            }
            
            node = stack.pop();
            poppedNode = true;
        }
    }
    
    private void postOrderNoRec(TreeNode<String> tree) {
        Deque<TreeNode<String>> stack = Lists.newLinkedList();
        TreeNode<String> node = tree;
        while (node != null || !stack.isEmpty()) {
            if (node != null && !node.isVisited()) {
                stack.push(node);
                node = node.getLeft();
            } else {
                node = stack.peek();
                if (node.hasRight() && !node.getRight().isVisited()) {
                    node = node.getRight();
                } else {
                    stack.pop();
                    visitNode(node);
                    node.setVisited(true);
                    node = null;
                }
            }
        }
    }
    
    private void levelOrder(TreeNode<String> tree) {
        Queue<TreeNode<String>> queue = Lists.newLinkedList();
        queue.offer(tree);
        while (!queue.isEmpty()) {
            TreeNode<String> node = queue.poll();
            visitNode(node);
            
            if (node.hasLeft()) {
                queue.offer(node.getLeft());
            }
            if (node.hasRight()) {
                queue.offer(node.getRight());
            }
        }
    }
}