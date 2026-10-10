import java.util.Scanner;

public class Minesweeper {
    public Minesweeper() {

    }

    static void main(final String[] theArgs) {
        final Scanner sc = new Scanner(System.in);

        // n -> Rows
        if (!sc.hasNextInt()) {
            throw new IllegalArgumentException("Rows must be an integer");
        }
        int n = sc.nextInt();

        // m -> Columns
        if (!sc.hasNextInt()) {
            throw new IllegalArgumentException("Columns must be an integer");
        }
        int m = sc.nextInt();
        StringBuilder result = new StringBuilder();

        int outputCount = 0;

        // Collect input
        while (n != 0 && m != 0) {
            // Verify that the inputs are valid
            if (n < 0 || m < 0) {
                throw new IllegalArgumentException("Rows and columns must be positive");
            }

            outputCount++;

            final char[][] minefield = getMinefield(n, m, sc);

            final char[][] revealedMinefield = revealMineImpact(minefield, n, m);

            result.append("Field #").append(outputCount).append(":\n");
            result.append(minefieldToString(revealedMinefield));




            // Collect the new dimensions
            n = sc.nextInt();
            m = sc.nextInt();
            if (n != 0 && m != 0) result.append("\n"); // Append new line to separate minefields
        }
        System.out.print(result);
    }

    public static char[][] revealMineImpact(final char[][] theMinefield, final int theRow, final int theCol) {
        // Logic for calculating the impact of the bombs
        for (int row = 0; row < theMinefield.length; row++) {
            for (int col = 0; col < theMinefield[row].length; col++) {
                if (theMinefield[row][col] != '*') {
                    int mineCount = 0;

                    // Loop through -1, 0, +1 for relative row and column offsets
                    for (int dr = -1; dr <= 1; dr++) {
                        for (int dc = -1; dc <= 1; dc++) {
                            int r = row + dr;
                            int c = col + dc;

                            // Ensure neighboring cell is within bounds and contains a mine
                            if (r >= 0 && r < theRow && c >= 0 && c < theCol && theMinefield[r][c] == '*') {
                                mineCount++;
                            }
                        }
                    }

                    theMinefield[row][col] = (char) (mineCount + '0');
                }
            }
        }
        return theMinefield;
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

    public static String minefieldToString(final char[][] theMinefield) {
        StringBuilder result = new StringBuilder();
        for (char[] row : theMinefield) {
            for (char character : row) {
                result.append(character);
            }
            result.append("\n"); // Move to the next row
        }
        return result.toString();
    }

}