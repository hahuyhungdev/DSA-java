package tree;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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

  private Iterator<T> inOrderTraverse() {
    final int expectedCount = nodeCount;
    final StackADT<Node<T>> stack = new LinkedListBasedStack<>();

    return new Iterator<T>() {
      private Node<T> nextNodeToProcess = root;

      private void checkForModification() {
        if (expectedCount != nodeCount) {
          throw new ConcurrentModificationException();
        }
      }

      @Override
      public boolean hasNext() {
        checkForModification();
        return nextNodeToProcess != null || !stack.isEmpty();
      }

      @Override
      public T next() {
        checkForModification();
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        // Go to the left most node
        while (nextNodeToProcess != null) {
          stack.push(nextNodeToProcess);
          nextNodeToProcess = nextNodeToProcess.getLeft();
        }

        // pop value of node from stack
        Node<T> nodeToReturn = stack.pop();
        // set the next node to be processed as the right child of the current node
        nextNodeToProcess = nodeToReturn.getRight();

        // return the data of the node
        return nodeToReturn.getData();
      }
    };
  }

  private Iterator<T> levelOrderTraverse() {
    return null;
  }

  private Iterator<T> postOrderTraverse() {
    return null;
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