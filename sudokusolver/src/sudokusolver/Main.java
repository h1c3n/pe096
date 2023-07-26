package sudokusolver;

import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	    System.out.println("Hello world!");
	    try {
	      File sudoku = new File("src/p096_sudoku.txt");
	      Scanner reader = new Scanner(sudoku);
	      int gridNum = 0;
	      int sum = 0;
	      while(reader.hasNextLine()) {
	        String line = reader.nextLine().trim();
	        if(!line.startsWith("Grid")) {
	          gridNum++;
	          int[][] grid = new int[9][9];
	          for(int row = 0; row < 9; row++) {
	            for(int col= 0; col < 9; col++) {
	              grid[row][col] = Character.getNumericValue(line.charAt(col));
	            }
	            line = reader.nextLine().trim();
	          }
	          solve(grid);
	          int first3 = grid[0][0] * 100 + grid[0][1] * 10 + grid[0][2];
	          sum+=first3;
	          System.out.println("Grid " + gridNum);
	          System.out.println("first 3 digits " + first3);
	          for(int[] row: grid) {
	            System.out.println("");
	            for(int n: row) {
	              System.out.print(n + " ");
	            }
	          }
	          System.out.println("");
	        }
	      }
	      reader.close();
	      System.out.println("answer: " + sum);
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    }
	}
	
	public static boolean solve(int[][] puzzle) {
	    for(int row = 0; row < puzzle.length; row++) {
	      for(int col = 0; col < puzzle.length; col++) {
	        if(puzzle[row][col] == 0) {
	          for(int i = 1; i <= 9; i++) {
	            if(isValid(puzzle, i, row, col)) {
	              puzzle[row][col] = i;
	              if(solve(puzzle)) {
	                return true;
	              }
	              puzzle[row][col] = 0;
	            }
	          }
	          return false;
	        }
	      }
	    }
	    return true;
	}
	
	public static boolean isValid(int[][] board, int n, int row, int col) {
        for(int r = 0; r < 9; r++) {
            if(board[r][col] == n) return false;
        }
        for(int c = 0; c < 9; c++) {
            if(board[row][c] == n) return false;
        }
        int dr = (row/3)*3, cr = (col/3)*3;
        for(int r = dr; r < dr+3; r++) {
            for(int c = cr; c < cr+3; c++) {
                if(board[r][c] == n) return false;
            }
        }
        return true;
    }

}
