package ArraysAndHashing.Medium;

import java.util.HashSet;

public class ValidSudoku {

    public static void main(String[] args) {
        ValidSudoku start = new ValidSudoku();
        char[][] input = {
                {'.','.','4','.','.','.','6','3','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'5','.','.','.','.','.','.','9','.'},
                {'.','.','.','5','6','.','.','.','.'},
                {'4','.','3','.','.','.','.','.','1'},
                {'.','.','.','7','.','.','.','.','.'},
                {'.','.','.','5','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
        };

        System.out.println(start.isValidSudoku(input));
    }

    // Strat 1 - take each number entry as a String of rowx,coly,numz
    // RESULT: 23ms kinda slow but remove the Math.floor and its 13ms which is acceptable.
    // Time Complexity: O(1) because the board is always 9x9
    public boolean isValidSudoku(char[][] board) {

        HashSet<String> rowMap = new HashSet<>();       // row5,num2
        HashSet<String> colMap = new HashSet<>();       // col2,num5
        HashSet<String> boxMap = new HashSet<>();       // box0-2,num8

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // rows
                if (board[i][j] == '.') {
                    continue;
                }

                char num = board[i][j];
                String row = "row" + i + "," + "num" + num;
                String col = "col" + j + "," + "num" + num;
                // you could check which box the number belongs in by doing lots of if-else statements but that looks ugly
                // E.g. reads "box0-2,num4" - box + row + col + number.
                String box = "box" + Math.floor(i/3.0) + "-" + Math.floor(j/3.0) + "," + "num" + num;       // technically reads box0.0-0.0,numz but doesn't matter

                // if number already exists in any row col or box then return false
                if (!rowMap.add(row) || !colMap.add(col) || !boxMap.add(box)) {
                    return false;
                }
            }
        }

        return true;
    }


}
