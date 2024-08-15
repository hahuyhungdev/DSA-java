package Queue;

import java.util.Iterator;

public class CircularArrayBasedQueue<T> implements QueueADT<T> {

  private final T[] array;
  private int front;
  private int end;
  private final int size;

  public CircularArrayBasedQueue(int maxSize) {
    front = end = 0;
    size = maxSize + 1;
    array = (T[]) new Object[size];
  }

  @Override
  public void enQueue(T element) {

    if (isFull()) {
      throw new IllegalStateException("Queue is full");
    }
    array[end] = element;
    end = (end + 1) % size;
    logState("Enqueue " + element);
  }

  @Override
  public T deQueue() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    T element = array[front];
    array[front] = null;
    front = (front + 1) % size;
    logState("Dequeue");
    return element;
  }

  @Override
  public T peek() {
    if (isEmpty()) {
      throw new RuntimeException("Queue empty");
    }
    return array[front];
  }

  @Override
  public boolean isFull() {
    return (end + 1) % size == front;
  }

  @Override
  public int size() {
    if (front > end) {
      return (end + size - front);
    }
    return end - front;
  }

  @Override
  public boolean isEmpty() {
    return front == end;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      @Override
      public boolean hasNext() {
        return false;
      }

      @Override
      public T next() {
        return null;
      }
    };
  }

  private void logState(String operation) {
    StringBuilder sb = new StringBuilder();
    sb.append(operation).append(":\n[");
    for (int i = 0; i < size; i++) {
      if (i == front && i == end) {
        sb.append("f/e");
      } else if (i == front) {
        sb.append("f");
      } else if (i == end) {
        sb.append("e");
      } else {
        sb.append(" ");
      }
      sb.append(array[i] == null ? "_" : array[i]);
      if (i < size - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    System.out.println(sb);
  }

  public static void main(String[] args) {
    CircularArrayBasedQueue<Integer> queue = new CircularArrayBasedQueue<>(
        5);  // Capacity 5, actual size 6

    try {
      System.out.println("Enqueuing elements:");
      queue.enQueue(10);
      queue.enQueue(20);
      queue.enQueue(30);
      queue.enQueue(40);
      queue.enQueue(50);

      System.out.println("\nTrying to enQueue when full:");
      try {
        queue.enQueue(60);  // This should throw an exception
      } catch (IllegalStateException e) {
        System.out.println("Error: " + e.getMessage());
      }

      System.out.println("\nDequeuing elements:");
      System.out.println("Dequeued: " + queue.deQueue());
      System.out.println("Dequeued: " + queue.deQueue());

      System.out.println("\nEnqueuing after deQueue:");
      queue.enQueue(60);
      queue.enQueue(70);

      System.out.println("\nDequeuing all elements:");
      while (!queue.isEmpty()) {
        System.out.println("Dequeued: " + queue.deQueue());
      }

      System.out.println("\nTrying to deQueue when empty:");
      try {
        queue.deQueue();  // This should throw an exception
      } catch (IllegalStateException e) {
        System.out.println("Error: " + e.getMessage());
      }

      System.out.println("\nEnqueuing after emptying the queue:");
      queue.enQueue(80);
      queue.enQueue(90);
      queue.enQueue(100);

    } catch (IllegalStateException e) {
      System.out.println("Unexpected error: " + e.getMessage());
    }
  }
}
