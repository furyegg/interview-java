package interview.tree;

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
        tt.midOrder(TREE1);
        System.out.println();
        tt.postOrder(TREE1);
        System.out.println();
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
}