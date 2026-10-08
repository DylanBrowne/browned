import java.util.Scanner;

public class Minesweeper {
    public Minesweeper() {

    }

    public static void main(final String[] theArgs) {
        final Scanner sc = new Scanner(System.in);

        // n -> Rows
        int n = sc.nextInt();

        // m -> Columns
        int m = sc.nextInt();
        StringBuilder result = new StringBuilder();
        result.append(n).append(" ").append(m).append("\n");

        // Collect input
        while (n != 0 && m != 0) {
            char[][] minefield = getMinefield(n, m, sc);

            // Logic for calculating the impact of the bombs


            // Collect the new dimensions
            n = sc.nextInt();
            m = sc.nextInt();
            result.append(n).append(" ").append(m).append("\n");
        }
    }

    public static char[][] getMinefield(final int theRow, final int theCol, final Scanner theScanner) {
        char[][] minefield = new char[theRow][theCol];

        int currentRow = 0;
        // Collect the minefield in a 2D array
        while (currentRow < theRow) {
            final String currentLine = theScanner.next(); // Get the next line
            int currentIndex = 0; // Set the current index to 0 at the start of every line
            // Logic for the current line
            while (currentIndex < theCol) {
                final char currentChar = currentLine.charAt(currentIndex);

                minefield[currentRow][currentIndex] = currentChar;

                currentIndex++;
            }
            currentRow++;
        }

        return minefield;
    }

}
