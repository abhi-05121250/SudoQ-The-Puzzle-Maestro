package SudoQ.Application.Project;

import java.util.Scanner;

public class SudokuSolver {

	private static Scanner scanner = new Scanner(System.in);
    
    public static void setScanner(Scanner customScanner) {
        scanner = customScanner;
    }
    public static void resetScanner() {
        scanner = new Scanner(System.in);
    }
    
    //Task 1:Function to taken input from user to create sudoku board
    public  void readUserInput(int[][] board) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Sudoku puzzle row by row (use '0' for empty cells):");
        for (int i = 0; i < 9; i++) {
            System.out.print("Row " + (i + 1) + ": ");
            for (int j = 0; j < 9; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        scanner.close();
    }

    }

  //Task 1:Function to print the sudoku board
    public  void printBoard(int[][] board) {
        System.out.println("Sudoku Board:");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

   
  //Task 2:Function to check whether the sudoku board given from user is valid or not
    public boolean initialValidBoard(int[][] board) {
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0 && !isValidPlacement(board, i, j, board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }


  //Task 2:Helper Function to check intial placement of numbers on the sudoku board given from user
    public  boolean initialValidPlacement(int[][] board, int row, int col, int num) {
        // Check if placing 'num' at (row, col) is valid in the row, column, and subgrid

        // Check the row
        for (int i = 0; i < 9; i++) {
            if (i != col && board[row][i] == num) {
                return false;
            }
        }

        // Check the column
        for (int i = 0; i < 9; i++) {
            if (i != row && board[i][col] == num) {
                return false;
            }
        }

        // Check the subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((i + startRow != row || j + startCol != col) && board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }


  //Task 3:Function to solve the sudoku
    public  boolean solveSudoku(int[][] board) {
        int[] emptyCell = findEmptyCell(board);
        int row = emptyCell[0];
        int col = emptyCell[1];

        if (row == -1 && col == -1) {
            // No empty cell found, puzzle is solved
        	
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            if (isValidPlacement(board, row, col, num)) {
                board[row][col] = num;

                if (solveSudoku(board)) {
                    return true; // Puzzle solved successfully
                }

                // Backtrack if the current placement leads to an invalid solution
                board[row][col] = 0;
            }
        }

        return false; // No solution found
    }

  //Task 3:Helper Function to check for empty spaces on the board
    private  int[] findEmptyCell(int[][] board) {
        int[] result = {-1, -1};

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return result; // No empty cell found
    }
  //Task 3:Helper Function to check  placement of numbers on the sudoku board for solving
    private  boolean isValidPlacement(int[][] board, int row, int col, int num) {
        // Check if placing 'num' at (row, col) is valid in the row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // Check if placing 'num' at (row, col) is valid in the column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Check if placing 'num' at (row, col) is valid in the 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true; // Placement is valid
    }
    
    public static void main(String[] args) {
//   	 int[][] board = {
//    		    {5, 3, 4, 6, 7, 8, 9, 1, 2},
//    		    {6, 7, 2, 1, 9, 5, 3, 4, 8},
//    		    {1, 9, 8, 3, 4, 2, 5, 6, 7},
//    		    {8, 5, 9, 7, 6, 1, 4, 2, 3},
//    		    {4, 2, 6, 8, 5, 3, 7, 9, 1},
//    		    {7, 1, 3, 9, 2, 4, 8, 5, 6},
//    		    {9, 6, 1, 5, 3, 7, 2, 8, 4},
//    		    {2, 8, 7, 4, 1, 9, 6, 3, 5},
//    		    {3, 4, 5, 2, 8, 6, 1, 7, 9}
//    		};
//   	int[][] board = {
//   	        {5, 3, 0, 0, 7, 0, 0, 0, 0},
//   	        {6, 0, 0, 1, 9, 5, 0, 0, 0},
//   	        {0, 9, 8, 0, 0, 0, 0, 6, 0},
//   	        {8, 0, 0, 0, 6, 0, 0, 0, 3},
//   	        {4, 0, 0, 8, 0, 3, 0, 0, 1},
//   	        {7, 0, 0, 0, 2, 0, 0, 0, 6},
//   	        {0, 6, 0, 0, 0, 0, 2, 8, 0},
//   	        {0, 0, 0, 4, 1, 9, 0, 0, 5},
//   	        {0, 0, 0, 0, 8, 0, 0, 7, 9}
//   	    };


    	SudokuSolver ob=new SudokuSolver();
   	int[][] board=new int[9][9];
   	ob.readUserInput(board);
       
       

       // Task 2: Validate the Board
       if (ob.initialValidBoard(board)) {
           System.out.println("Sudoku board is valid.");
           ob.printBoard(board);
           // Task 3: Solve the Sudoku
           if (ob.solveSudoku(board)) {
               System.out.println("Sudoku solved:");
               ob.printBoard(board);
           } else {
               System.out.println("No solution exists.");
           }
       }
        else {
           System.out.println("Sudoku board is not valid.");
       }
   }
}