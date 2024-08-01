import java.util.Scanner;

public class App {
    public static void main(String[] args)  {
            Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();

            System.out.println("enter your age");
            int age = scanner.nextInt();

            System.out.println("My name is " + name + " and I am " + age + " years old.");
        }
        catch (java.util.InputMismatchException e) {
            System.out.println("Please enter a valid age");
        }
        catch (Exception e) {
            System.out.println("An error occurred");
        }
        finally {
            scanner.close();
        }

    }

}
