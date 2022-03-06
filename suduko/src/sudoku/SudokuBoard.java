package sudoku;

import java.util.LinkedList;

public class SudokuBoard implements SudokuSolver {
	private int dim = 9;
	private int[][] m = new int[dim][dim];
	
	@Override
	public boolean solve() {
		return solveFor(0, 0);
	}
	
	private boolean solveFor(int row, int col) {
		// Check if square already has a number
		if(!isFilled(row, col)) {
			// If not; check which number to put in
			for(int i=1;i<=dim;i++) {
				this.add(row, col, i);
				if(validHorizontal(row, false) && validVertical(col, false) && validRegion(row, col, false)) {
					if(col < dim-1) {
						if(solveFor(row, col+1)) {
							return true;
						}
					}else if(row < dim-1){
						if(solveFor(row+1, 0)) {
							return true;
						}
					}else{
						return true;
					}
				}
				this.remove(row, col);
			}
			// Not possible to solve
			return false;
		}else {
			// validate already put in number
			if(!(validHorizontal(row, false) && validVertical(col, false) && validRegion(row/3, col/3, false))) {
				return false;
			}
			if(col < dim-1) {
				if(solveFor(row, col+1)) {
					return true;
				}
			}else if(row < dim-1){
				if(solveFor(row+1, 0)) {
					return true;
				}
			}else {
				// Last square, check if valid sudoku board
				return isValid();
			}
		}
		return false;
	}
	
	// Check if square already has a number
	private boolean isFilled(int row, int col){
		if(m[row][col]!=0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void add(int row, int col, int digit) {
		if(digit<1 || digit >9) {
			throw new IllegalArgumentException("Digit måste vara mellan 1-9.");
		}
		if(row<0 || row >9) {
			throw new IllegalArgumentException("Row måste vara mellan 0-9.");
		}
		if(col<0 || col >9) {
			throw new IllegalArgumentException("Col måste vara mellan 0-9.");
		}
		m[row][col]=digit;
	}

	@Override
	public void remove(int row, int col) {
		m[row][col]=0;
	}

	@Override
	public int get(int row, int col) {
		return m[row][col];
	}
	
	//Checks if the board is completed and valid
	@Override
	public boolean isValid() {
		boolean valid = true;
		for(int i=0;i<dim;i++) {
			if(!validHorizontal(i,true) || !validVertical(i,true)) {
				valid = false;
			}
		}
		for(int rB=0;rB<dim;rB++) {
			for(int cB=0;cB<dim;cB++) {
				if(!validRegion(rB, cB,true)) {
					valid = false;
				}
			}
		}
		return valid;
	}

	@Override
	public void clear() {
    	for(int r=0;r<dim;r++) {
    		for(int c=0;c<dim;c++) {
    			m[r][c]=0;
    		}
    	}
	}

	@Override
	public void setMatrix(int[][] m) {
		if(m.length!=dim){
			throw new IllegalArgumentException("m är av fel storlek");
		}
		for(int i=0;i<9;i++) {
			if(m[i].length!=dim) {
				throw new IllegalArgumentException("m är av fel storlek");
			}
		}
		this.m = m;
	}

	@Override
	public int[][] getMatrix() {
		return m;
	}
	
	// Checks if valid in current row
	private boolean validHorizontal(int r, boolean checkZero) {
		for(int c=0;c<dim;c++) {
			if(m[r][c]==0 && checkZero) {
				return false;
			}
			for(int i=0;i<dim;i++) {
				if((m[r][c] == m[r][i]) && i != c) {
					if(!checkZero) {
						if(m[r][c]==0) {
							break;
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	// Checks if valid in current column
	private boolean validVertical(int c, boolean checkZero) {
		for(int r=0;r<dim;r++) {
			if(m[r][c]==0 && checkZero) {
				return false;
			}
			for(int i=0;i<dim;i++) {
				if(m[r][c] == m[i][c] && i != r) {
					if(!checkZero) {
						if(m[r][c]==0) {
							break;
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	// Checks if valid in current region box
	private boolean validRegion(int rB, int cB, boolean checkZero) {
		rB=rB/3;
		cB=cB/3;
		LinkedList<Integer> values = new LinkedList<Integer>();
		for(int r=rB*3;r<rB*3+3;r++) {
			for(int c=cB*3;c<cB*3+3;c++) {
				if(values.contains(m[r][c]) || (m[r][c]==0  && checkZero)){
					return false;
				}else {
					if(checkZero) {
						values.add(m[r][c]);
					}else {
						if(m[r][c]!=0) {
							values.add(m[r][c]);
						}
					}
				}
			}
		}
		return true;
	}

}
