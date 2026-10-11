# Minesweeper

A Java program that processes Minesweeper minefields and calculates the number of adjacent mines for each non-mine cell.

## Features
- Reads multiple minefields from standard input.
- Uses 2D arrays to represent minefields.
- Counts adjacent mines by checking all eight neighboring cells.
- Preserves the original minefield using a separate result array.
- Formats results with numbered field labels.

## Technologies
- Java
- `Scanner`
- Two-dimensional arrays
- `StringBuilder`

## Example Input
```text
4 4
*...
....
.*..
....
0 0
```

## Example Output
```text
Field #1:
*100
2210
1*10
1110
```

## Running the Program
```bash
javac Minesweeper.java
java Minesweeper
```

Enter `0 0` to terminate input.

## Concepts Practiced
Nested loops, array manipulation, boundary checking, character arithmetic, and input/output processing.

**Author:** Dylan Browne
