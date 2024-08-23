
# Explanation of `CircularArrayBasedQueue` Implementation in Java

This Java class `CircularArrayBasedQueue` implements a circular queue data structure using an array. The queue operates in a circular manner, meaning that the end of the queue wraps around to the beginning of the array once the end is reached, optimizing space usage.

## Class Structure

### Fields:
- `T[] array`: The array that stores the queue elements. The size is `maxSize + 1` to differentiate between full and empty states.
- `int front`: Index pointing to the front of the queue.
- `int end`: Index pointing to the position where the next element will be inserted.
- `int size`: The actual size of the array (capacity + 1).

### Constructor:
```java
public CircularArrayBasedQueue(int maxSize)
```
- Initializes the queue with a given maximum size. 
- The actual size of the array is `maxSize + 1` to handle full/empty states effectively.
- The `front` and `end` pointers are initialized to 0.

### Methods:

#### `void enQueue(T element)`
- Adds an element to the end of the queue.
- Checks if the queue is full before enqueuing.
- Updates the `end` pointer circularly using modulo operation.
- Logs the state after enqueueing.

#### `T deQueue()`
- Removes and returns the front element from the queue.
- Checks if the queue is empty before dequeuing.
- Updates the `front` pointer circularly using modulo operation.
- Logs the state after dequeuing.

#### `T peek()`
- Returns the front element without removing it.
- Throws an exception if the queue is empty.

#### `boolean isFull()`
- Checks if the queue is full by comparing `end + 1 % size` with `front`.

#### `int size()`
- Returns the number of elements in the queue.
- Handles the case where `front` is greater than `end` (wrapped around).

#### `boolean isEmpty()`
- Returns `true` if `front` equals `end`, indicating the queue is empty.

#### `Iterator<T> iterator()`
- Provides a simple iterator that doesn't iterate over elements (can be extended).

### Logging Method:
#### `void logState(String operation)`
- Logs the current state of the queue after each operation (enqueue or dequeue).
- Shows positions of `front` and `end` in the array for easier debugging.

## Main Method (Example Usage)
The `main` method demonstrates the use of the `CircularArrayBasedQueue` class:
- Enqueues elements until the queue is full, then attempts to enqueue one more to show the exception handling.
- Dequeues a couple of elements, then enqueues again to show the circular nature.
- Continues to dequeue until the queue is empty, demonstrating behavior when empty.
- Enqueues again after emptying the queue to show reuse.

### Example:
```java
CircularArrayBasedQueue<Integer> queue = new CircularArrayBasedQueue<>(5); // Capacity 5, actual size 6

// Enqueuing elements
queue.enQueue(10);
queue.enQueue(20);
queue.enQueue(30);
queue.enQueue(40);
queue.enQueue(50);

// Trying to enqueue when full
try {
    queue.enQueue(60); // Should throw an exception
} catch (IllegalStateException e) {
    System.out.println("Error: " + e.getMessage());
}

// Dequeuing elements
System.out.println("Dequeued: " + queue.deQueue());
System.out.println("Dequeued: " + queue.deQueue());

// Enqueuing after dequeuing
queue.enQueue(60);
queue.enQueue(70);

// Dequeuing all elements
while (!queue.isEmpty()) {
    System.out.println("Dequeued: " + queue.deQueue());
}

// Trying to dequeue when empty
try {
    queue.deQueue(); // Should throw an exception
} catch (IllegalStateException e) {
    System.out.println("Error: " + e.getMessage());
}

// Enqueuing after emptying the queue
queue.enQueue(80);
queue.enQueue(90);
queue.enQueue(100);
```

This implementation is efficient for scenarios requiring fixed-size queues with frequent enqueue and dequeue operations. The circular nature allows continuous use of the array without reallocating or shifting elements.
