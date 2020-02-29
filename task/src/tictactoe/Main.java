package tictactoe;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {


    public static String checkWin(char[][] field) {
        String result = "";
        int counterWins = 0;
        char[] symbols = new char[9];
        symbols[0] = field[0][2];
        symbols[3] = field[0][1];
        symbols[6] = field[0][0];

        symbols[1] = field[1][2];
        symbols[4] = field[1][1];
        symbols[7] = field[1][0];

        symbols[2] = field[2][2];
        symbols[5] = field[2][1];
        symbols[8] = field[2][0];




        for (int i = 0; i < symbols.length; i++) {
            //check horizontal
            if (i % 3 == 0) {
                if (symbols[i] == symbols[i + 1] && symbols[i + 1] == symbols[i + 2]) {
                    if(symbols[i] == 'X' || symbols[i] == 'O') {
                        result = symbols[i] + " wins";
                        counterWins++;
                    }
                }
            }
            //check vertical
            if (i < 3) {
                if (symbols[i] == symbols[i + 3] && symbols[i + 3] == symbols[i + 6]) {
                    if(symbols[i] == 'X' || symbols[i] == 'O') {
                        result = symbols[i] + " wins";
                        counterWins++;
                    }
                }
            }



        }
        //check diagonal
        if (symbols[0] == symbols[4] && symbols[4] == symbols[8]) {
            if(symbols[0] == 'X' || symbols[0] == 'O') {
                result = symbols[0] + " wins";
                counterWins++;
            }
        }

        if (symbols[2] == symbols[4] && symbols[4] == symbols[6]) {
            if(symbols[2] == 'X' || symbols[2] == 'O') {
                result = symbols[2] + " wins";
                counterWins++;
            }
        }
        if (counterWins != 1 && counterWins != 0) {
            result = "Impossible";
        }

        return result;
    }

    public static boolean isGameFinished(char[][] field) {

        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[0].length; j++) {
                if(field[i][j] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    public static String isImpossible(char[][] field) {
        String result = "";
        int counterX = 0;
        int counterO = 0;

        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field.length; j++) {
                if (field[i][j] == 'X') {
                    counterX++;
                }
                if (field[i][j] == 'O') {
                    counterO++;
                }
            }
        }

        if (Math.abs(counterX - counterO) >= 2) {
            result = "Impossible";
        }


        return result;
    }

    public static boolean isNullOrEmpty(String str) {
        if (str != null && !str.isEmpty())
            return false;
        return true;
    }

    public static boolean makeMove(char[][] field, int x, int y, int counterMove) {
        boolean isNotSuccess = true;

        if(x > 2 || x < 0 || y > 2 || y < 0) {
            System.out.println("Coordinates should be from 1 to 3!");
            System.out.print("Enter the coordinates: ");
            return true;
        }

        if(field[x][y] == 'X' || field[x][y] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            System.out.print("Enter the coordinates: ");
        } else {

            if(counterMove % 2 != 0) {
                field[x][y] = 'X';
            } else {
                field[x][y] = 'O';
            }

            isNotSuccess = false;
        }



        return isNotSuccess;
    }

    public static void displayField(char[][] field, boolean isNotEnter) {
        System.out.println("---------");
        System.out.println("| " + field[0][2] + " " + field[1][2] + " " + field[2][2] + " |");
        System.out.println("| " + field[0][1] + " " + field[1][1] + " " + field[2][1] + " |");
        System.out.println("| " + field[0][0] + " " + field[1][0] + " " + field[2][0] + " |");
        System.out.println("---------");

    }

    public static WinState endGame(char[][] field) {
        boolean isGameNotEnd = true;
        String result;

        result = checkWin(field);
        if (isNullOrEmpty(result)) {
            if (!isGameFinished(field)) {
                result = isImpossible(field);
                if (isNullOrEmpty(result)) {
                    result = "Game not finished";
                }
            } else {
                result = checkWin(field);
                if (isNullOrEmpty(result)) {
                    result = "Draw";
                }
            }
        }

        switch(result){
            case "X wins":
                isGameNotEnd = false;
                break;
            case "O wins":
                isGameNotEnd = false;
                break;
            case "Draw":
                isGameNotEnd = false;
                break;
            case "Impossible":
                isGameNotEnd = false;
                break;
        }
        WinState winState = new WinState(result, isGameNotEnd);
        return winState;
    }




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*
        //System.out.println("Enter cells: ");
        //String inputSymbols = scanner.nextLine();
        //char[] symbols = inputSymbols.toCharArray();

        char[] symbols = new char[9];
        for(int i = 0; i < symbols.length; i++) {
            symbols[i] = ' ';
        }
        */
        /*
        field[0][2] = symbols[0];
        field[0][1] = symbols[3];
        field[0][0] = symbols[6];

        field[1][2] = symbols[1];
        field[1][1] = symbols[4];
        field[1][0] = symbols[7];

        field[2][2] = symbols[2];
        field[2][1] = symbols[5];
        field[2][0] = symbols[8];

*/
        boolean flag = true;
        char[][] field = new char[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                field[i][j] = ' ';
            }
        }
        displayField(field, false);

        int x = -1;
        int y = -1;

        boolean flagIsNotFinished = true;
        boolean flagException;
        int counterExceptions = 0;
        String result;
        int counterMove = 1;




        while(flagIsNotFinished) {
            if((counterExceptions + 1) % 2 == 0) {
                System.out.print("Enter the coordinates: ");
            }

            try {
                x = scanner.nextInt() - 1;
                y = scanner.nextInt() - 1;
                flagException = false;
            } catch(InputMismatchException e) {
                flagException = true;
                if(counterExceptions % 2 == 0) {
                    System.out.println("You should enter numbers!");
                }
                counterExceptions++;
                scanner.next();
            }

            if(!flagException) {

                flag = makeMove(field, x, y, counterMove);
                if(!flag) {
                    counterMove++;
                    displayField(field, flag);
                }

                flagIsNotFinished = endGame(field).flag;

            }



        }




        //check game when wins

        result = endGame(field).result;
        System.out.println(result);
    }
}
