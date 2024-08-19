package Tree;

import java.util.Iterator;

public class BinarySearchTreeMain {
  public static void main(String[] args) {
    TreeADT<Integer> bst = new BinarySearchTree<>();
    bst.add(10);
    bst.add(5);
    bst.add(15);
    bst.add(3);
    bst.add(7);
    bst.add(12);
    bst.add(17);

    Iterator traverse = bst.traverse(TreeTraverseType.POST_ORDER);
    while (traverse.hasNext()) {
      System.out.println(traverse.next());
    }
//    System.out.println("Height: " + bst.height());
//    System.out.println("Size: " + bst.size());
//    System.out.println("Contains 7: " + bst.contains(7));
//    System.out.println("Contains 8: " + bst.contains(8));

  }
}
