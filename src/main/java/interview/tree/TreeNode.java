package interview.tree;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TreeNode<T> {
    private T value;
    private TreeNode<T> left;
    private TreeNode<T> right;
    
    public boolean hasLeft() {
        return left != null;
    }
    
    public boolean hasRight() {
        return right != null;
    }
    
    public boolean isLeaf() {
        return !hasLeft() && !hasRight();
    }
}