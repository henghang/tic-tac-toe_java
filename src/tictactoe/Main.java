package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        boolean isValidMove;
        String[][] tictactoe = new String[3][3];

        initializeBoard(tictactoe);
        printBoard(tictactoe);
        String user = "X";
        while (checkBoard(tictactoe)) {
            do {
                isValidMove = userMove(tictactoe, user);
                if (isValidMove) {
                    if ("X".equals(user)) {
                        user = "O";
                    } else {
                        user = "X";
                    }
                }
            } while (!isValidMove);
            printBoard(tictactoe);
        }
    }

    public static void initializeBoard(String[][] tictactoe) {
        System.out.println("Game Start!");

        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                tictactoe[i][j] = "_";
            }
        }
    }

    public static boolean userMove(String[][] tictactoe, String user) {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        int[] userCoordinates = new int[2];

        while (!isValid) {
            System.out.print("Enter the coordinates: ");
            String[] userCoordinatesString = scanner.nextLine().split(" ");
            for (int i = 0; i < userCoordinates.length; i++) {
                try {
                    userCoordinates[i] = Integer.parseInt(userCoordinatesString[i]);
                    if (!(userCoordinates[i] > 0 && userCoordinates[i] < 4)) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        break;
                    }
                    if (i == userCoordinates.length - 1) {
                        isValid = true;
                    }
                } catch(NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                    break;
                }
            }
        }
        int i = Math.abs(userCoordinates[1] - 3);
        int j = userCoordinates[0]- 1;
        if ("_".equals(tictactoe[i][j])) {
            tictactoe[i][j] = user;
            return true;
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
    }

    public static void printBoard(String[][] tictactoe) {
        System.out.println("---------");
        for (int i = 0; i < 3; i ++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tictactoe[i][j] + " ");
            }
            System.out.printf("|%n");
        }
        System.out.println("---------");
    }

    public static boolean isBoardValid(String[][] tictactoe) {
        int countX = 0;
        int countO = 0;

        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                if ("X".equals(tictactoe[i][j])) {
                    countX++;
                } else if ("O".equals(tictactoe[i][j])) {
                    countO++;
                }
            }
        }
        return !(Math.abs(countO - countX) > 1);
    }

    public static boolean checkBoardHorizontal(String[][] tictactoe, String cell) {
        int count = 0;
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                if (cell.equals(tictactoe[i][j])) {
                    count++;
                    if (count == 3) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
            count = 0;
        }
        return false;
    }

    public static boolean checkBoardVertical(String[][] tictactoe, String cell) {
        int count = 0;
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                if (cell.equals(tictactoe[j][i])) {
                    count++;
                    if (count == 3) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
            count = 0;
        }
        return false;
    }

    public static boolean checkBoardDiagonal(String[][] tictactoe, String cell) {
        int count = 0;
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    if (cell.equals(tictactoe[i][j])) {
                        count++;
                        if (count == 3) {
                            return true;
                        }
                    }
                }
            }
        }
        count = 0;
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                if (Math.abs(j - i) == 2 || (j == 1 && i == 1)) {
                    if (cell.equals(tictactoe[i][j])) {
                        count++;
                        if (count == 3) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean isGameFinished(String[][] tictactoe) {
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                if ("_".equals(tictactoe[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkBoard(String[][] tictactoe) {
        boolean XWin = false;
        boolean OWin = false;
        if (checkBoardVertical(tictactoe, "X") || checkBoardHorizontal(tictactoe, "X") || checkBoardDiagonal(tictactoe, "X")) {
            XWin = true;
        }
        if (checkBoardVertical(tictactoe, "O") || checkBoardHorizontal(tictactoe, "O") || checkBoardDiagonal(tictactoe, "O")) {
            OWin = true;
        }

        if (XWin) {
            System.out.println("X wins");
            return false;
        } else if (OWin) {
            System.out.println("O wins");
            return false;
        } else if (isGameFinished(tictactoe)) {
            System.out.println("Draw");
            return false;
        } else {
            return true;
        }
    }
}
