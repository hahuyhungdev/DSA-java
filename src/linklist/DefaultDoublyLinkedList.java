package linklist;

import java.util.Iterator;

public class DefaultDoublyLinkedList<T> implements DoublyLinkedList<T> {

  private int size;
  private Node<T> head = null;
  private Node<T> tail = null;

  @Override
  public void clear() {
    Node<T> currentNode = head;
    while (currentNode != null) {
      Node<T> nextNode = currentNode.getNext();
      currentNode.setNext(null);
      currentNode.setPrev(null);
      currentNode.setData(null);
      currentNode = nextNode;
    }
    head = tail = null;
    size = 0;
  }

  @Override
  public String logHeadAndTail() {
    return "head: " + head + " tail: " + tail;
  }

  @Override
  public Node<T> findNode(T data) {
    Node<T> currentNode = head;
    while (currentNode != null) {
      if (isEquals(currentNode.getData(), data)) {
        System.out.println("Node with value " + data + " FOUNDED");
        return currentNode;
      }
      currentNode = currentNode.getNext();
    }
    System.out.println("Node with value " + data + " not found");
    return null;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public void add(T element) {
    addLast(element);
  }

  @Override
  public void addFirst(T element) {
    if (isEmpty()) {
      head = tail = new Node<>(element, null, null);
    } else {
      head.setPrev(new Node<>(element, null, head));
      head = head.getPrev();
    }
    size++;
  }

  @Override
  public void addLast(T element) {
    if (isEmpty()) {
      head = tail = new Node<>(element, null, null);
    } else {
      tail.setNext(new Node<>(element, tail, null));
      tail = tail.getNext();
    }
    size++;
  }

  @Override
  public T peekFirst() {
    if (isEmpty()) {
      throw new RuntimeException("Empty linked list!");
    }
    return head.getData();
  }

  @Override
  public T peekLast() {
    if (isEmpty()) {
      throw new RuntimeException("Empty linked list!");
    }
    return tail.getData();
  }

  @Override
  public T removeFirst() {
    if (isEmpty()) {
      throw new RuntimeException("Empty linked list!");
    }
    T data = head.getData();
    head = head.getNext();
    size--;
    if (isEmpty()) {
      tail = null;
    } else {
      head.setPrev(null);
    }

    return data;
  }

  @Override
  public T removeLast() {
    if (isEmpty()) {
      throw new RuntimeException("Empty linked list!");
    }
    T data = tail.getData();
    tail = tail.getPrev();
    size--;
    if (isEmpty()) {
      head = null;
    } else {
      tail.setNext(null);
    }

    return data;
  }

  @Override
  public void clearNodeReference(Node<T> node) {
    node.setNext(null);
    node.setPrev(null);
    node.setData(null);
  }

  @Override
  public T remove(Node<T> node) {
    if (node == null) {
      throw new IllegalArgumentException("Node is null");
    }
    System.out.println("Node to remove: " + node);
    if (node == head) {
      return removeFirst();
    }
    if (node == tail) {
      return removeLast();
    }

    node.getPrev().setNext(node.getNext());
    node.getNext().setPrev(node.getPrev());

    T data = node.getData();
    size--;

    clearNodeReference(node);
    return data;
  }

  private boolean isEquals(T nodeData, Object searchData) {
    if (searchData == null) {
      return nodeData == null;
    }
    return searchData.equals(nodeData);
  }

  @Override
  public boolean remove(Object object) {
    if (head == null) {
      return false;
    }
    if (isEquals(head.getData(), object)) {
      removeFirst();
      return true;
    }

    Node<T> currentNode = head.getNext();
    while (currentNode != null) {
      if (isEquals(currentNode.getData(), object)) {
        remove(currentNode);
        return true;
      }
      currentNode = currentNode.getNext();
    }
    return false;
  }

  @Override
  public T removeAt(int indexRm) {
    if (indexRm < 0 || indexRm >= size) {
      throw new IllegalArgumentException();
    }
    if (indexRm == 0) {
      return removeFirst();
    }
    if (indexRm == size - 1) {
      return removeLast();
    }

    Node<T> nodeToRemove;
    if (indexRm < size / 2) {
      nodeToRemove = getNodeFromStart(indexRm);
    } else {
      nodeToRemove = getNodeFromEnd(indexRm);
    }
    return remove(nodeToRemove);
  }

  private Node<T> getNodeFromStart(int index) {
    Node<T> currentNode = head;
    for (int i = 0; i < index; i++) {
      currentNode = currentNode.getNext();
    }
    return currentNode;
  }

  private Node<T> getNodeFromEnd(int index) {
    Node<T> currentNode = tail;
    for (int i = size - 1; i > index; i--) {
      currentNode = currentNode.getPrev();
    }
    return currentNode;
  }

  public int indexOf(Object obj) {
    int index = 0;
    Node<T> currentNode = head;

    while (currentNode != null) {
      if (isEquals(currentNode.getData(), obj)) {
        return index;
      }
      currentNode = currentNode.getNext();
      index++;
    }
    return -1;
  }

  @Override
  public boolean contains(Object object) {
    return indexOf(object) != -1;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private Node<T> currentNode = head;

      @Override
      public boolean hasNext() {
        return currentNode != null;
      }

      @Override
      public T next() {
        if (!hasNext()) {
          throw new IllegalArgumentException("No more element");
        }
        T data = currentNode.getData();
        currentNode = currentNode.getNext();
        return data;
      }
    };
  }

  @Override
  public String toString() {
    if (isEmpty()) {
      return "[]";
    } else {
      StringBuilder sb = new StringBuilder(size);
      sb.append("[ ");

      Node<T> currentNode = head;

      while (currentNode != null) {
        sb.append(currentNode.getData());
        if (currentNode.getNext() != null) {
          sb.append(", ");
        }
        currentNode = currentNode.getNext();
      }
      sb.append(" ]");
      return sb.toString();
    }
  }

  // run DefaultDoublyLinkedList class demo
  public static void main(String[] args) {
    DefaultDoublyLinkedList<String> list = new DefaultDoublyLinkedList<>();

    // Thêm phần tử vào danh sách
    list.addFirst("Hello");
    list.addFirst(null);
    list.addLast("World");
    list.addLast("World1");
    System.out.println("list = " + list);
    list.removeAt(1);
    System.out.println("list = " + list);
  }
}

