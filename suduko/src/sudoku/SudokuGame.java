package sudoku;

public class SudokuGame {
	
	SudokuViewer w;
	SudokuSolver b;
	
	public static void main(String[] args) {
		new SudokuGame();
	}
	
	public SudokuGame() {
		b= new SudokuBoard();
		w = new SudokuViewer("Sudoku", b, 450, 450);
		//int[][] matrix = {{9,2,6,0,3,0,0,0,4},{5,0,0,0,9,4,0,7,3},{0,0,7,8,0,1,2,5,0},{1,9,0,0,7,3,0,0,2},{0,0,2,0,4,8,9,0,5},{8,3,0,9,0,0,0,6,7},{0,0,0,7,0,6,3,4,0},{4,1,8,3,0,0,7,0,0},{0,7,3,4,8,0,5,9,0}};
		//b.setMatrix(matrix);
	}

}
