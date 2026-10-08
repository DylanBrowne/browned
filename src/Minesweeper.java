import java.util.Scanner;

public class Minesweeper {
    public Minesweeper() {

    }

    public static void main(final String[] theArgs) {
        final Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        StringBuilder result = new StringBuilder();
        result.append(n).append(" ").append(m).append("\n");

        // Collect input
        while (n != 0 && m != 0) {
            sc.nextLine(); // Consume the '\n' after the dimension parameters



            n = sc.nextInt();
            m = sc.nextInt();
            result.append(n).append(" ").append(m).append("\n");
        }
    }

}
