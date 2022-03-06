package sudoku;

import java.util.LinkedList;

public class SudokuBoardOLD implements SudokuSolver {
	int[][] m;
	int dim = 9;
	
	@Override
	public boolean solve() {
		return solveFor(0, 0, 1);
	}
	
	private boolean solveFor(int row, int col, int digit) {
		boolean valid = true;
		System.out.println(row + " : " + col + " - " + digit + " CUR: " + m[row][col]);
		
		if(digit>9) {
			digit = 9;
		}
		
		if(m[row][col]!=0) {
			valid = true;
		}else {
			for(int i=0;i<dim;i++) {
				// CHECK HORIXONTALLY
				if(digit == m[row][i] && i != col) {
					valid = false;
				}
				// CHECK VERTICALLY
				if(digit == m[i][col] && i != row) {
					valid = false;
				}
				// CHECK REGIONALLY
				int rB = row/3;
				int cB = col/3;
				LinkedList<Integer> values = new LinkedList<Integer>();
				for(int r=rB*3;r<rB*3+3;r++) {
					for(int c=cB*3;c<cB*3+3;c++) {
						if(c != col && r != row) {
							values.add(m[r][c]);
						}
					}
				}
				if(values.contains(digit)) {
					valid = false;
				}
			}
		}
		
		if(!valid) {
			if(digit>=dim) {
				if(col==0 && row!=0) {
					
					this.remove(row-1, dim-1);
					if(m[row-1][dim-1]!=9) {
						return solveFor(row-1, dim-1, m[row-1][dim-1]+1);
					}else {
						return solveFor(row-1, dim-2, m[row-1][dim-2]+1);
					}
				}else {
					if(m[row][col-1]!=9) {
						return solveFor(row, col-1, m[row][col-1]+1);
					}else {
						return solveFor(row, col-2, m[row][col-2]+1);
					}
				}
			}else {
				return solveFor(row, col, digit+1);
			}
		}else {
			this.add(row, col, digit);
			if(col==dim-1) {
				return solveFor(row+1, 0, 1);
			}
			return solveFor(row, col+1, 1);
		}
	}

	@Override
	public void add(int row, int col, int digit) {
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

	@Override
	public boolean isValid() {
		boolean valid = true;
		for(int i=0;i<dim;i++) {
			if(!validHorizontal(i) || !validVertical(i)) {
				valid = false;
			}
		}
		for(int rB=0;rB<dim/3;rB++) {
			for(int cB=0;cB<dim/3;cB++) {
				if(!validRegion(rB, cB)) {
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
		this.m = m;
	}

	@Override
	public int[][] getMatrix() {
		return m;
	}
	
	private boolean validHorizontal(int r) {
		for(int c=0;c<dim;c++) {
			if(m[r][c]==0) {
				return false;
			}
			for(int i=0;i<dim;i++) {
				if(m[r][c] == m[r][i] && i != c) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean validVertical(int c) {
		for(int r=0;r<dim;r++) {
			if(m[r][c]==0) {
				return false;
			}
			for(int i=0;i<dim;i++) {
				if(m[r][c] == m[i][c] && i != r) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean validRegion(int rB, int cB) {
		LinkedList<Integer> values = new LinkedList<Integer>();
		for(int r=rB*3;r<rB*3+3;r++) {
			for(int c=cB*3;c<cB*3+3;c++) {
				if(values.contains(m[r][c]) || m[r][c]==0){
					return false;
				}else {
					values.add(m[r][c]);
				}
			}
		}
		return true;
	}

}
