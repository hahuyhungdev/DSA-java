package Tree;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import stack.LinkedListBasedStack;
import stack.StackADT;

public class BinarySearchTree<T extends Comparable<T>> implements TreeADT<T> {

  private int nodeCount = 0;
  private Node<T> root = null;

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public int height() {
    return height(root);
  }

  @Override
  public int size() {
    return nodeCount;
  }

  @Override
  public boolean contains(T elem) {
    if (elem == null)
      throw new IllegalArgumentException("Element cannot be null");
    return contains(root, elem);
  }

  @Override
  public boolean add(T elem) {
    if (elem == null)
      throw new IllegalArgumentException("Cannot add null element");
    if (contains(elem)) {
      return false;
    }
    root = add(root, elem);
    nodeCount++;
    return true;
  }

  @Override
  public boolean remove(T elem) {
    if (elem == null)
      throw new IllegalArgumentException("Cannot remove null element");
    if (!contains(elem)) {
      return false;
    }
    root = remove(root, elem);
    nodeCount--;
    return true;
  }

  private Node<T> remove(Node<T> node, T elem) {
    if (node == null) {
      return null;
    }

    int compareResult = elem.compareTo(node.getData());
    if (compareResult > 0) {
      node.setRight(remove(node.getRight(), elem));
    } else if (compareResult < 0) {
      node.setLeft(remove(node.getLeft(), elem));
    } else {
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      } else {
        T smallestValue = findMin(node.getRight());
        node.setData(smallestValue);
        node.setRight(remove(node.getRight(), smallestValue));
      }
    }
    return node;
  }

  private T findMin(Node<T> node) {
    while (node.getLeft() != null) {
      node = node.getLeft();
    }
    return node.getData();
  }

  @Override
  public Iterator<T> traverse(TreeTraverseType type) {
    switch (type) {
      case PRE_ORDER:
        return preOrderTraverse();
      case IN_ORDER:
        return inOrderTraverse();
      case POST_ORDER:
        return postOrderTraverse();
      case LEVEL_ORDER:
        return levelOrderTraverse();
      default:
        return null;
    }
  }

  /*
   * 
   * ### Tóm tắt cách duyệt Pre-Order:
   * Duyệt pre-order hoạt động theo nguyên tắc sau:
   * 
   * 1. **Duyệt nút hiện tại**: Pop node từ stack và xử lý (trả về giá trị).
   * 2. **Push con phải của node** (nếu có) vào stack.
   * 3. **Push con trái của node** (nếu có) vào stack.
   * 
   * Vì stack là cấu trúc **LIFO (Last In, First Out)** nên khi push con trái sau
   * con phải, con trái sẽ được duyệt trước (vì nó được pop ra sau con phải). Điều
   * này đảm bảo rằng khi duyệt pre-order, ta duyệt nút hiện tại trước, sau đó là
   * cây con trái rồi đến cây con phải.
   * 
   * ### Chi tiết quá trình duyệt:
   * 
   * 1. **Khởi đầu với node gốc 10**:
   * - Stack: `[10]`
   * - Pop node `10`: xử lý node `10` và trả về giá trị của nó.
   * - Push con phải `15` của `10` vào stack: Stack: `[15]`
   * - Push con trái `5` của `10` vào stack: Stack: `[15, 5]`
   * - **Kết quả tạm thời**: `10`
   * 
   * 2. **Duyệt node 5**:
   * - Stack: `[15, 5]`
   * - Pop node `5`: xử lý node `5` và trả về giá trị của nó.
   * - Push con phải `7` của `5` vào stack: Stack: `[15, 7]`
   * - Push con trái `3` của `5` vào stack: Stack: `[15, 7, 3]`
   * - **Kết quả tạm thời**: `10, 5`
   * 
   * 3. **Duyệt node 3**:
   * - Stack: `[15, 7, 3]`
   * - Pop node `3`: xử lý node `3` và trả về giá trị của nó.
   * - Node `3` không có con trái hay con phải, nên không có gì để push vào stack.
   * - **Kết quả tạm thời**: `10, 5, 3`
   * 
   * 4. **Quay lại node 7**:
   * - Stack: `[15, 7]`
   * - Pop node `7`: xử lý node `7` và trả về giá trị của nó.
   * - Node `7` không có con trái hay con phải, nên không có gì để push vào stack.
   * - **Kết quả tạm thời**: `10, 5, 3, 7`
   * 
   * 5. **Quay lại node 15**:
   * - Stack: `[15]`
   * - Pop node `15`: xử lý node `15` và trả về giá trị của nó.
   * - Push con phải `17` của `15` vào stack: Stack: `[17]`
   * - Push con trái `12` của `15` vào stack: Stack: `[17, 12]`
   * - **Kết quả tạm thời**: `10, 5, 3, 7, 15`
   * 
   * 6. **Duyệt node 12**:
   * - Stack: `[17, 12]`
   * - Pop node `12`: xử lý node `12` và trả về giá trị của nó.
   * - Node `12` không có con trái hay con phải, nên không có gì để push vào
   * stack.
   * - **Kết quả tạm thời**: `10, 5, 3, 7, 15, 12`
   * 
   * 7. **Cuối cùng duyệt node 17**:
   * - Stack: `[17]`
   * - Pop node `17`: xử lý node `17` và trả về giá trị của nó.
   * - Node `17` không có con trái hay con phải, nên không có gì để push vào
   * stack.
   * - **Kết quả tạm thời**: `10, 5, 3, 7, 15, 12, 17`
   * 
   * ### Tổng Kết:
   * 
   * Với thuật toán pre-order, sau khi pop một node, ta sẽ **luôn** lấy giá trị
   * của node đó ngay lập tức, sau đó push con phải trước rồi mới đến con trái vào
   * stack. Điều này đảm bảo rằng các node ở cây con trái sẽ được xử lý trước các
   * node ở cây con phải, và cuối cùng sẽ duyệt hết các nút theo thứ tự `Root ->
   * Left -> Right`.
   * 
   */
  private Iterator<T> preOrderTraverse() {
    final int expectedCount = nodeCount;
    final StackADT<Node<T>> stack = new LinkedListBasedStack<>();
    if (root != null) {
      stack.push(root);
    }

    return new Iterator<T>() {

      private void checkForModification() {
        if (expectedCount != nodeCount) {
          throw new ConcurrentModificationException();
        }
      }

      @Override
      public boolean hasNext() {
        checkForModification();

        return !stack.isEmpty();
      }

      @Override
      public T next() {
        if (!hasNext())
          throw new NoSuchElementException();
        Node<T> node = stack.pop();

        if (node.getRight() != null)
          stack.push(node.getRight());
        if (node.getLeft() != null)
          stack.push(node.getLeft());

        return node.getData();
      }
    };
  }
  /*
   * In-order Traverse: Duyệt nút bên trái trước, rồi đến nút gốc (root), cuối
   * cùng là nút bên phải.
   * 
   * Quá Trình Duyệt In-Order Theo Từng Bước:
   * Bắt đầu với root (10):
   * 
   * Stack: []
   * nextNodeToProcess là 10.
   * Đi đến node con trái (5):
   * 
   * Stack: [10]
   * nextNodeToProcess là 5.
   * Tiếp tục đi đến node con trái của 5 (3):
   * 
   * Stack: [10, 5]
   * nextNodeToProcess là 3.
   * Đi đến node con trái của 3 (1):
   * 
   * Stack: [10, 5, 3]
   * nextNodeToProcess là 1.
   * Node 1 là node con trái nhất, không có con trái nữa:
   * 
   * Stack: [10, 5, 3]
   * Pop node 1 ra khỏi stack.
   * Node 1 không có con phải, do đó nextNodeToProcess được gán giá trị null.
   * Kết quả tạm thời: 1
   * Quay lại node 3:
   * 
   * Stack: [10, 5]
   * Pop node 3 ra khỏi stack.
   * Node 3 không có con phải, nên nextNodeToProcess vẫn là null.
   * Kết quả tạm thời: 1, 3
   * Quay lại node 5:
   * 
   * Stack: [10]
   * Pop node 5 ra khỏi stack.
   * Thiết lập nextNodeToProcess là con phải của 5 (node 7).
   * Kết quả tạm thời: 1, 3, 5
   * Duyệt node 7:
   * 
   * Stack: [10]
   * nextNodeToProcess là 7.
   * Node 7 không có con trái, nên pop node 7 ra khỏi stack.
   * Node 7 cũng không có con phải, nên nextNodeToProcess là null.
   * Kết quả tạm thời: 1, 3, 5, 7
   * Quay lại root và duyệt node 10:
   * 
   * Stack: []
   * Pop node 10 ra khỏi stack.
   * Thiết lập nextNodeToProcess là con phải của 10 (node 15).
   * Kết quả tạm thời: 1, 3, 5, 7, 10
   * Duyệt node 15:
   * 
   * Stack: []
   * nextNodeToProcess là 15.
   * Đi đến node con trái của 15 (node 12).
   * Stack: [15]
   * Pop node 12 ra khỏi stack.
   * Node 12 không có con trái hay con phải, nên nextNodeToProcess là null.
   * Kết quả tạm thời: 1, 3, 5, 7, 10, 12
   * Quay lại và duyệt node 15:
   * 
   * Stack: []
   * Pop node 15 ra khỏi stack.
   * Thiết lập nextNodeToProcess là con phải của 15 (node 17).
   * Kết quả tạm thời: 1, 3, 5, 7, 10, 12, 15
   * Duyệt node 17:
   * 
   * Stack: []
   * nextNodeToProcess là 17.
   * Node 17 không có con trái, nên pop node 17 ra khỏi stack.
   * Node 17 cũng không có con phải, nên nextNodeToProcess là null.
   * Kết quả tạm thời: 1, 3, 5, 7, 10, 12, 15, 17
   * Tổng Kết:
   * Kết quả cuối cùng của quá trình duyệt in-order trên cây nhị phân này là:
   * 
   * Kết quả: 1, 3, 5, 7, 10, 12, 15, 17
   * Trong quá trình này, khi pop một node ra khỏi stack và node đó không có con
   * phải (nextNodeToProcess = nodeToReturn.getRight() sẽ là null), thì quá trình
   * sẽ tiếp tục pop node tiếp theo từ stack cho đến khi stack trống.
   */

  private Iterator<T> inOrderTraverse() {
    final int expectedCount = nodeCount;
    final StackADT<Node<T>> stack = new LinkedListBasedStack<>();

    return new Iterator<T>() {
      private Node<T> currentNode = root;

      private void checkForModification() {
        if (expectedCount != nodeCount) {
          throw new ConcurrentModificationException();
        }
      }
 
      @Override
      public boolean hasNext() {   
        checkForModification();
        return currentNode != null || !stack.isEmpty();
      }

      @Override
      public T next() {
        checkForModification();
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        while (currentNode != null) {
          stack.push(currentNode);
          currentNode = currentNode.getLeft();
        }
        Node<T> nodeToReturn = stack.pop();
        currentNode = nodeToReturn.getRight();
        return nodeToReturn.getData();
      }
    };
  }

  /*
   * Cập nhật quá trình duyệt post-order chính xác (nguyên tắc post-order là duyệt
   * trái trước, sau đó phải, và cuối cùng là gốc)
   * Bắt đầu với root (10):
   * 
   * Stack: []
   * currentNode là 10.
   * Đi đến node con trái (5):
   * 
   * Stack: [10]
   * currentNode là 5.
   * Tiếp tục đi đến node con trái của 5 (3):
   * 
   * Stack: [10, 5]
   * currentNode là 3.
   * Đi đến node con trái của 3 (1):
   * 
   * Stack: [10, 5, 3]
   * currentNode là 1.
   * Node 1 là node con trái nhất, không có con trái và con phải:
   * 
   * Stack: [10, 5, 3]
   * Pop node 1 ra khỏi stack, node 1 được xử lý và trả về giá trị.
   * Thiết lập lastVisitedNode là 1.
   * Kết quả tạm thời: 1
   * Quay lại node 3:
   * 
   * Stack: [10, 5]
   * currentNode là null.
   * Node 3 không có con phải, và con trái đã được duyệt (1 là lastVisitedNode).
   * Pop node 3 ra khỏi stack, node 3 được xử lý và trả về giá trị.
   * Thiết lập lastVisitedNode là 3.
   * Kết quả tạm thời: 1, 3
   * Quay lại node 5 và xử lý con phải (7):
   * 
   * Stack: [10, 5]
   * peekNode là 5.
   * Node 5 có con phải là 7 và con này chưa được duyệt (lastVisitedNode là 3).
   * Thiết lập currentNode là 7.
   * Đẩy 7 vào stack.
   * Stack: [10, 5, 7].
   * Duyệt node 7:
   * 
   * Stack: [10, 5, 7]
   * currentNode là 7.
   * Node 7 không có con trái hay con phải, pop node 7 ra khỏi stack và xử lý.
   * Thiết lập lastVisitedNode là 7.
   * Kết quả tạm thời: 1, 3, 7
   * Quay lại node 5:
   * 
   * Stack: [10, 5]
   * peekNode là 5.
   * Node 5 đã duyệt xong cả con trái (3) và con phải (7).
   * Pop node 5 ra khỏi stack, node 5 được xử lý và trả về giá trị.
   * Thiết lập lastVisitedNode là 5.
   * Kết quả tạm thời: 1, 3, 7, 5
   * Quay lại root và đi đến node phải (15):
   * 
   * Stack: [10]
   * currentNode là 15.
   * Duyệt node trái của 15 (12):
   * 
   * Stack: [10, 15]
   * currentNode là 12.
   * Node 12 không có con trái hay con phải, pop node 12 ra khỏi stack và xử lý.
   * Thiết lập lastVisitedNode là 12.
   * Kết quả tạm thời: 1, 3, 7, 5, 12
   * Quay lại node 15 và duyệt node phải (17):
   * 
   * Stack: [10, 15]
   * Pop node 17 ra khỏi stack, node 17 được xử lý và trả về giá trị.
   * Thiết lập lastVisitedNode là 17.
   * Kết quả tạm thời: 1, 3, 7, 5, 12, 17
   * Quay lại root (10):
   * 
   * Stack: [10]
   * Pop node 10 ra khỏi stack, node 10 được xử lý và trả về giá trị.
   * Kết quả tạm thời: 1, 3, 7, 5, 12, 17, 15
   * Tổng Kết:
   * Kết quả cuối cùng của quá trình duyệt post-order sau khi sửa là:
   * 
   * Kết quả: 1, 3, 7, 5, 12, 17, 15, 10
   */
  private Iterator<T> postOrderTraverse() {
    final int expectedCount = nodeCount;
    final StackADT<Node<T>> stack = new LinkedListBasedStack<>();

    return new Iterator<T>() {
      private Node<T> lastVisitedNode = null;
      private Node<T> currentNode = root;

      private void checkForModification() {
        if (expectedCount != nodeCount) {
          throw new ConcurrentModificationException();
        }
      }

      @Override

      public boolean hasNext() {
        checkForModification();
        return !stack.isEmpty() || currentNode != null;
      }

      @Override
      public T next() {
        checkForModification();
        if (!hasNext()) {
          throw new NoSuchElementException();
        }

        while (currentNode != null) {
          stack.push(currentNode);
          currentNode = currentNode.getLeft();
        }

        Node<T> peekNode = stack.top();
        // if the right node has been visited or does not exist, visit the current node
        if (peekNode.getRight() == null || peekNode.getRight() == lastVisitedNode) {
          stack.pop();
          lastVisitedNode = peekNode;
          return peekNode.getData();
        } else {
          // if the right node has not been visited, visit the right node
          currentNode = peekNode.getRight();
        }

        return next();
      }

    };

  }

  private Iterator<T> levelOrderTraverse() {
    final int expectedCount = nodeCount;
    final Queue<Node<T>> queue = new LinkedList<>();

    return new Iterator<T>() {
      Node<T> currentNode = root;

      private void checkForModification() {
        if (expectedCount != nodeCount) {
          throw new ConcurrentModificationException();
        }
      }

      @Override
      public boolean hasNext() {
        checkForModification();
        return currentNode != null || !queue.isEmpty();
      }

      @Override
      public T next() {
        checkForModification();
        if (!hasNext()) {
          throw new NoSuchElementException();
        }

        if (currentNode != null) {
          queue.add(currentNode);
          currentNode = null; // Ensure that we do not add the root again.
        }

        Node<T> node = queue.poll();
        if (node != null) {
          if (node.getLeft() != null) {
            queue.add(node.getLeft());
          }
          if (node.getRight() != null) {
            queue.add(node.getRight());
          }
        }

        return node.getData();
      }
    };
  }

  private int height(Node<T> node) {
    if (node == null)
      return 0;
    return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
  }

  private Node<T> add(Node<T> node, T elem) {
    if (node == null) {
      return new Node<>(elem, null, null);
    }
    int cmp = elem.compareTo(node.getData());
    if (cmp < 0) {
      node.setLeft(add(node.getLeft(), elem));
    } else {
      node.setRight(add(node.getRight(), elem));
    }
    return node;
  }

  private boolean contains(Node<T> node, T elem) {
    while (node != null) {
      int cmp = elem.compareTo(node.getData());
      if (cmp < 0)
        node = node.getLeft();
      else if (cmp > 0)
        node = node.getRight();
      else
        return true;
    }
    return false;
  }

  public boolean isValidBST() {
    return isValidBST(root, null, null);
  }

  private boolean isValidBST(Node<T> node, T min, T max) {
    if (node == null)
      return true;
    if ((min != null && node.getData().compareTo(min) <= 0) ||
        (max != null && node.getData().compareTo(max) >= 0)) {
      return false;
    }
    return isValidBST(node.getLeft(), min, node.getData()) &&
        isValidBST(node.getRight(), node.getData(), max);
  }
}