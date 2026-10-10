import java.util.Scanner;

/**
 Represents a Minesweeper minefield and provides methods to read its
 dimensions and contents, calculate the number of adjacent mines for
 each non-mine cell, and display the resulting minefield.

 The program processes multiple minefields until a pair of zero
 dimensions is provided.

 @author Dylan Browne
 @version 1.0
 */
public class Minesweeper {
    /**
     Creates a new Minesweeper instance
     */
    public Minesweeper() {

    }

    /**
     Reads minefield dimensions and contents from standard input, calculates
     the number of adjacent mines for each cell, and prints the formatted results.

     @param theArgs command-line arguments supplied when the program starts
     */
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

    /**
     Calculates the number of adjacent mines for every non-mine cell in the
     given minefield. Mine cells remain unchanged, while other cells are
     replaced with a character representing their adjacent mine count.

     @param theMinefield the two-dimensional array representing the minefield
     @param theRow the number of rows in the minefield
     @param theCol the number of columns in the minefield
     @return the minefield with adjacent mine counts calculated
     */
    public static char[][] revealMineImpact(final char[][] theMinefield, final int theRow, final int theCol) {
        final char[][] revealedMinefield = new char[theRow][theCol];

        // Copy each row into the new array
        for (int row = 0; row < theRow; row++) {
            revealedMinefield[row] = theMinefield[row].clone();
        }

        // Logic for calculating the impact of the bombs
        for (int row = 0; row < theRow; row++) {
            for (int col = 0; col < theCol; col++) {
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

                    revealedMinefield[row][col] = (char) (mineCount + '0');
                }
            }
        }
        return revealedMinefield;
    }

    /**
     Reads the minefield contents from the provided scanner and stores them
     in a two-dimensional character array.

     @param theRow the number of rows in the minefield
     @param theCol the number of columns in the minefield
     @param theScanner the scanner used to read the minefield input
     @return a two-dimensional character array containing the minefield
     */
    public static char[][] getMinefield(final int theRow, final int theCol, final Scanner theScanner) {
        char[][] minefield = new char[theRow][theCol];

        int currentRow = 0;
        // Collect the minefield in a 2D array
        while (currentRow < theRow) {
            if (!theScanner.hasNext()) {
                throw new IllegalArgumentException("Input not valid");
            }
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

    /**
     Converts a two-dimensional character array into a string, with each
     row appearing on a separate line.

     @param theMinefield the minefield to convert into a string
     @return a string representation of the minefield
     */
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