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

        // Collect input
        while (n != 0 && m != 0) {
            final char[][] minefield = getMinefield(n, m, sc);

            final char[][] revealedMinefield = revealMineImpact(minefield, n, m);

            result.append(minefieldToString(n, m, revealedMinefield));




            // Collect the new dimensions
            n = sc.nextInt();
            m = sc.nextInt();
        }
        result.append(n).append(" ").append(m);
        System.out.print(result.toString());
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

    public static String minefieldToString(final int theRows, final int theCols, final char[][] theMinefield) {
        StringBuilder result = new StringBuilder();
        result.append(theRows).append(" ").append(theCols).append("\n");
        for (char[] row : theMinefield) {
            for (char character : row) {
                result.append(character);
            }
            result.append("\n"); // Move to the next row
        }
        return result.toString();
    }

}
