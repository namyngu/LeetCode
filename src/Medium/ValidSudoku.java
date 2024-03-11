package Medium;

import java.util.HashSet;

public class ValidSudoku {

    public static void main(String[] args) {
        ValidSudoku start = new ValidSudoku();
    }

    // Strat 1 - take each number entry as a String of rowx,coly,numz
    //
    public boolean isValidSudoku(char[][] board) {

        HashSet<String> rowMap = new HashSet<>();       // row5,num2
        HashSet<String> colMap = new HashSet<>();       // col2,num5
        HashSet<String> boxMap = new HashSet<>();       // box0-2,num8

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // rows
                if (board[i][j] == '.') {
                    break;
                }

                char num = board[i][j];
                String row = "row" + i + "," + "num" + num;
                String col = "col" + j + "," + "num" + num;
                // you could check which box the number belongs in by doing lots of if-else statements but that looks ugly
                String box = "box" + Math.floor(i/3) + "-" + Math.floor(j/3) + "," + "num" + num;

                // if number already exists in any row col or box then return false
                if (!rowMap.add(row) || !colMap.add(col) || !boxMap.add(box)) {
                    return false;
                }
            }
        }

        return true;
    }


}
