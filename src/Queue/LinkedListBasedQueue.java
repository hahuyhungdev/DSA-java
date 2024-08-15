package Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import linklist.DefaultDoublyLinkedList;
import linklist.DoublyLinkedList;

public class LinkedListBasedQueue<T> implements QueueADT<T> {

  private final DoublyLinkedList<T> linkedList;
  private final int capacity;

  /**
   * Constructs an empty queue with unlimited capacity.
   */
  public LinkedListBasedQueue() {
    this(Integer.MAX_VALUE);
  }

  /**
   * Constructs an empty queue with the specified capacity.
   *
   * @param capacity the maximum number of elements the queue can hold
   * @throws IllegalArgumentException if capacity is non-positive
   */
  public LinkedListBasedQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be positive");
    }
    this.capacity = capacity;
    this.linkedList = new DefaultDoublyLinkedList<>();
  }

  /**
   * Constructs a queue with the specified first element and unlimited capacity.
   *
   * @param firstElem the first element to be added to the queue
   */
  public LinkedListBasedQueue(T firstElem) {
    this();
    enQueue(firstElem);
  }

  /**
   * Adds an element to the rear of the queue.
   *
   * @param element the element to add
   * @throws IllegalStateException if the queue is full
   */
  @Override
  public void enQueue(T element) {
    if (size() == capacity) {
      throw new IllegalStateException("Queue is full");
    }
    linkedList.addLast(element);
  }

  /**
   * Removes and returns the element at the front of the queue.
   *
   * @return the element at the front of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public T deQueue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    return linkedList.removeFirst();
  }

  /**
   * Returns, but does not remove, the element at the front of the queue.
   *
   * @return the element at the front of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public T peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    return linkedList.peekFirst();
  }

  /**
   * Returns the number of elements in the queue.
   *
   * @return the number of elements in the queue
   */
  @Override
  public int size() {
    return linkedList.size();
  }

  /**
   * Checks if the queue is empty.
   *
   * @return true if the queue is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return linkedList.isEmpty();
  }

  /**
   * Returns an iterator over the elements in the queue.
   *
   * @return an iterator over the elements in the queue
   */
  @Override
  public Iterator<T> iterator() {
    return linkedList.iterator();
  }

  @Override
  public boolean isFull() {
    return size() == capacity;
  }

  /**
   * Returns a string representation of the queue.
   *
   * @return a string representation of the queue
   */
  @Override
  public String toString() {
    return "LinkedListBasedQueue{" +
        "elements=" + linkedList +
        ", size=" + size() +
        ", capacity=" + capacity +
        '}';
  }

  // Main method for demonstration
  public static void main(String[] args) {
    LinkedListBasedQueue<Integer> queue = new LinkedListBasedQueue<>(5);

    System.out.println("Enqueuing elements:");
    for (int i = 1; i <= 5; i++) {
      queue.enQueue(i * 10);
      System.out.println("Enqueued: " + (i * 10) + ", Queue: " + queue);
    }

    System.out.println("\nTrying to enqueue when full:");
    try {
      queue.enQueue(60);
    } catch (IllegalStateException e) {
      System.out.println("Error: " + e.getMessage());
    }

    System.out.println("\nDequeuing elements:");
    while (!queue.isEmpty()) {
      System.out.println("Dequeued: " + queue.deQueue() + ", Queue: " + queue);
    }

    System.out.println("\nTrying to dequeue when empty:");
    try {
      queue.deQueue();
    } catch (NoSuchElementException e) {
      System.out.println("Error: " + e.getMessage());
    }

    System.out.println("\nPeeking at an empty queue:");
    try {
      queue.peek();
    } catch (NoSuchElementException e) {
      System.out.println("Error: " + e.getMessage());
    }

    System.out.println("\nUsing iterator to print queue elements:");
    queue.enQueue(100);
    queue.enQueue(200);
    queue.enQueue(300);
    for (Integer element : queue) {
      System.out.println(element);
    }
  }
}