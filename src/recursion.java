public class recursion {

  public static void main(String[] args) {
    System.out.println("reverseString: " + reverseString("Hello, World!"));
  }

  // factorial method
  public static int fatorial(int n) {
    if (n == 0 || n == 1) {
      return 1;
    }
    return n * fatorial(n - 1);
  }

  // reverseString method
  public static String reverseString(String str) {
    if (str.isEmpty()) {
      return str;
    } else {
      return reverseString(str.substring(1)) + str.charAt(0);
    }
  }
}
