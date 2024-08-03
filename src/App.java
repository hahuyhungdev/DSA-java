import dsa.DefaultDoublyLinkedList;

public class App {

  public static void main(String[] args) {
    DefaultDoublyLinkedList<String> list = new DefaultDoublyLinkedList<>();

    // Thêm phần tử vào danh sách
    list.addFirst("Hello");
    list.addFirst(null);
    list.addLast("World");
    list.addLast("World1");
    list.remove("World");
    System.out.println("List" + list);
//    Node<String> nodeToRemove = list.findNode("World");

  }
}