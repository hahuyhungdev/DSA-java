import dsa.DefaultDoublyLinkedList;

public class App {

  public static void main(String[] args) {
    DefaultDoublyLinkedList<String> list = new DefaultDoublyLinkedList<>();

    // Thêm phần tử vào danh sách
    list.addFirst("Hello");
//    list.addFirst(null);
    list.addLast("World");
    list.addLast("World1");
    System.out.println("list = " + list);
    list.removeAt(1);
    System.out.println("list = " + list);
  }
}