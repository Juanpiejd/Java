package sudoku;

public interface SudokuSolver {
	/**
	 * Solves the sudoku
	 * 
	 * @return 	<ul><li>true	Returns true if a solution exists</li>
	 * 			<li>false	Returns false if no solution exists</li></ul>
	 * 
	 */
	boolean solve();

	/**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	void add(int row, int col, int digit);

	/**
	 * Removes digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 *                                  
	 */
	void remove(int row, int col);

	/**
	 * Returns digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 *                                  
	 */
	int get(int row, int col);

	/**
	 * Checks that all filled in digits follows the the sudoku rules.
	 */
	boolean isValid();

	/**
	 * Clears all boxes of digits
	 * 
	 */
	void clear();

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	void setMatrix(int[][] m);

	/**
	 * Returns the matrix consisting of all boxes.
	 * 
	 */
	int[][] getMatrix();
}