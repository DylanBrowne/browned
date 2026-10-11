
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MinesweeperTest {

    // Tests for revealMineImpact()

    @Test
    void revealMineImpact_sampleField_calculatesCorrectCounts() {
        char[][] field = {
                {'*', '.', '.', '.'},
                {'.', '.', '.', '.'},
                {'.', '*', '.', '.'},
                {'.', '.', '.', '.'}
        };

        char[][] expected = {
                {'*', '1', '0', '0'},
                {'2', '2', '1', '0'},
                {'1', '*', '1', '0'},
                {'1', '1', '1', '0'}
        };

        assertArrayEquals(
                expected,
                Minesweeper.revealMineImpact(field, 4, 4)
        );
    }

    @Test
    void revealMineImpact_noMines_returnsAllZeros() {
        char[][] field = {
                {'.', '.', '.'},
                {'.', '.', '.'}
        };

        char[][] expected = {
                {'0', '0', '0'},
                {'0', '0', '0'}
        };

        assertArrayEquals(
                expected,
                Minesweeper.revealMineImpact(field, 2, 3)
        );
    }

    @Test
    void revealMineImpact_allMines_preservesEveryMine() {
        char[][] field = {
                {'*', '*'},
                {'*', '*'}
        };

        assertArrayEquals(
                field,
                Minesweeper.revealMineImpact(field, 2, 2)
        );
    }

    @Test
    void revealMineImpact_singleSafeCell_returnsZero() {
        char[][] field = {{'.'}};

        char[][] expected = {{'0'}};

        assertArrayEquals(
                expected,
                Minesweeper.revealMineImpact(field, 1, 1)
        );
    }

    @Test
    void revealMineImpact_singleMine_preservesMine() {
        char[][] field = {{'*'}};

        char[][] expected = {{'*'}};

        assertArrayEquals(
                expected,
                Minesweeper.revealMineImpact(field, 1, 1)
        );
    }

    @Test
    void revealMineImpact_countsAllEightAdjacentMines() {
        char[][] field = {
                {'*', '*', '*'},
                {'*', '.', '*'},
                {'*', '*', '*'}
        };

        char[][] expected = {
                {'*', '*', '*'},
                {'*', '8', '*'},
                {'*', '*', '*'}
        };

        assertArrayEquals(
                expected,
                Minesweeper.revealMineImpact(field, 3, 3)
        );
    }

    @Test
    void revealMineImpact_countsDiagonalMines() {
        char[][] field = {
                {'*', '.'},
                {'.', '.'}
        };

        char[][] expected = {
                {'*', '1'},
                {'1', '1'}
        };

        assertArrayEquals(
                expected,
                Minesweeper.revealMineImpact(field, 2, 2)
        );
    }

    @Test
    void revealMineImpact_singleRow_countsHorizontalNeighbors() {
        char[][] field = {{'*', '.', '*', '.'}};

        char[][] expected = {{'*', '2', '*', '1'}};

        assertArrayEquals(
                expected,
                Minesweeper.revealMineImpact(field, 1, 4)
        );
    }

    @Test
    void revealMineImpact_singleColumn_countsVerticalNeighbors() {
        char[][] field = {
                {'*'},
                {'.'},
                {'*'},
                {'.'}
        };

        char[][] expected = {
                {'*'},
                {'2'},
                {'*'},
                {'1'}
        };

        assertArrayEquals(
                expected,
                Minesweeper.revealMineImpact(field, 4, 1)
        );
    }

    @Test
    void revealMineImpact_doesNotModifyOriginalField() {
        char[][] field = {
                {'*', '.'},
                {'.', '.'}
        };

        char[][] original = {
                {'*', '.'},
                {'.', '.'}
        };

        Minesweeper.revealMineImpact(field, 2, 2);

        assertArrayEquals(original, field);
    }


    // Tests for getMinefield()

    @Test
    void getMinefield_readsRowsAndColumnsCorrectly() {
        Scanner scanner = new Scanner("*..\n.*.\n");

        char[][] expected = {
                {'*', '.', '.'},
                {'.', '*', '.'}
        };

        assertArrayEquals(
                expected,
                Minesweeper.getMinefield(2, 3, scanner)
        );

        scanner.close();
    }

    @Test
    void getMinefield_throwsExceptionWhenInputIsMissing() {
        Scanner scanner = new Scanner("*..\n");

        assertThrows(
                IllegalArgumentException.class,
                () -> Minesweeper.getMinefield(2, 3, scanner)
        );

        scanner.close();
    }


    // Tests for minefieldToString()

    @Test
    void minefieldToString_formatsRowsWithNewlines() {
        char[][] field = {
                {'*', '1', '0'},
                {'1', '1', '0'}
        };

        String expected = "*10\n110\n";

        assertEquals(expected, Minesweeper.minefieldToString(field));
    }

    @Test
    void minefieldToString_emptyField_returnsEmptyString() {
        assertEquals("", Minesweeper.minefieldToString(new char[0][0]));
    }


    // Tests for main()

    @Test
    void main_sampleInput_producesExpectedOutput() {
        String input =
                "4 4\n" +
                        "*...\n" +
                        "....\n" +
                        ".*..\n" +
                        "....\n" +
                        "3 5\n" +
                        "**...\n" +
                        ".....\n" +
                        ".*...\n" +
                        "0 0\n";

        String expected =
                "Field #1:\n" +
                        "*100\n" +
                        "2210\n" +
                        "1*10\n" +
                        "1110\n" +
                        "\n" +
                        "Field #2:\n" +
                        "**100\n" +
                        "33200\n" +
                        "1*100\n";

        assertEquals(expected, runMain(input));
    }

    @Test
    void main_zeroByZeroInput_producesNoOutput() {
        assertEquals("", runMain("0 0\n"));
    }

    @Test
    void main_multipleFields_numbersFieldsSequentially() {
        String input =
                "1 1\n" +
                        ".\n" +
                        "1 1\n" +
                        "*\n" +
                        "0 0\n";

        String expected =
                "Field #1:\n" +
                        "0\n" +
                        "\n" +
                        "Field #2:\n" +
                        "*\n";

        assertEquals(expected, runMain(input));
    }

    private String runMain(String input) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(
                    input.getBytes(StandardCharsets.UTF_8)
            ));

            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));

            Minesweeper.main(new String[0]);

            return output.toString(StandardCharsets.UTF_8);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}

