package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuTest {
	
	SudokuViewer w;
	SudokuSolver b;
	int[][] matrix = {{9,2,6,0,3,0,0,0,4},{5,0,0,0,9,4,0,7,3},{0,0,7,8,0,1,2,5,0},{1,9,0,0,7,3,0,0,2},{0,0,2,0,4,8,9,0,5},{8,3,0,9,0,0,0,6,7},{0,0,0,7,0,6,3,4,0},{4,1,8,3,0,0,7,0,0},{0,7,3,4,8,0,5,9,0}};
	int[][] matrixWrongSize = {{9,2,6,0,3,0,0,0,4,10},{5,0,0,0,9,4,0,7,3},{0,0,7,8,0,1,2,5,0},{1,9,0,0,7,3,0,0,2},{0,0,2,0,4,8,9,0,5},{8,3,0,9,0,0,0,6,7},{0,0,0,7,0,6,3,4,0},{4,1,8,3,0,0,7,0,0},{0,7,3,4,8,0,5,9,0}};
	int[][] matrixUnsolvable = {{1,2,3,4,5,6,0,0,0},{0,0,0,0,0,0,7,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}};

	@BeforeEach
	void setUp() throws Exception {
		b = new SudokuBoard();
		w = new SudokuViewer("Sudoku", b, 450, 450);
	}

	@AfterEach
	void tearDown() throws Exception {
		b = new SudokuBoard();
		w = new SudokuViewer("Sudoku", b, 450, 450);
	}

	@Test
	void testAddRemove() {
		b.add(4, 6, 1);
		assertEquals(1, b.get(4, 6));
		b.remove(4, 6);
		assertEquals(0, b.get(4, 6));
	}
	
	@Test
	void testClear() {
		b.add(4, 6, 1);
		b.add(5, 6, 5);
		b.clear();
		assertEquals(0, b.get(4, 6));
		assertEquals(0, b.get(5, 6));
	}
	
	@Test
	void testAddWrong() {
		assertThrows(IllegalArgumentException.class, () -> b.add(4, 6, -1));
		assertThrows(IllegalArgumentException.class, () -> b.add(4, 6, 10));
	}
	
	@Test
	void testSetMatrix() {
		b.setMatrix(matrix);
		assertEquals(matrix, b.getMatrix());
	}
	
	@Test
	void testUnsolvable() {
		b.setMatrix(matrixUnsolvable);
		assertEquals(matrixUnsolvable, b.getMatrix());
		assertFalse(b.solve());
	}
	
	@Test
	void testSolvable() {
		b.setMatrix(matrix);
		assertEquals(matrix, b.getMatrix());
		assertTrue(b.solve());
	}
	
	@Test
	void testSetWrongSizeMatrix() {
		assertThrows(IllegalArgumentException.class, () -> b.setMatrix(matrixWrongSize));
	}
	
	@Test
	void testEmpty() {
		assertTrue(b.solve());
	}

}
