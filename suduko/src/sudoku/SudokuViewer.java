package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class SudokuViewer {
	
	private JTextField [][] fields;
	private JPanel sudokuPanel;
	private SudokuSolver board;
	private int dim;
	private int height;
	private int width;

    public SudokuViewer(String title, SudokuSolver b, int height, int width) {
    	this.height = height;
    	this.width = width;
    	this.board = b;
		this.dim = 9;
		fields = new JTextField[dim][dim];
        SwingUtilities.invokeLater(() -> createWindow(title));
    }

    /**
     * Private helper method, to confine all Swing-related work to
     * Swing's Event Dispatch Thread (EDT).
     */
    private void createWindow(String title) {

        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();

        sudokuPanel = new JPanel(new GridLayout(dim, dim));
        JPanel buttonPanel = new JPanel();
        this.buildBoard(true);
        
        JButton clearButton = new JButton("clear");
        JButton solveButton = new JButton("solve");
        

        clearButton.setFont(new Font("Arial", Font.BOLD, height/dim/4));
        clearButton.setForeground(Color.white);
        clearButton.setBackground(Color.DARK_GRAY);
        
        solveButton.setFont(new Font("Arial", Font.BOLD, height/dim/4));
        solveButton.setForeground(Color.white);
        solveButton.setBackground(Color.DARK_GRAY);
        
        
        clearButton.addActionListener((e) -> {
        	board.clear();
        	this.buildBoard(false);
        });
        
        solveButton.addActionListener((e) -> {
        	int[][] inputValues = new int[dim][dim];
        	for(int r=0;r<dim;r++) {
        		for(int c=0;c<dim;c++) {
        			if(fields[r][c].getText().length()==0) {
        				inputValues[r][c]=0;
        			}else {
        				
        				int inputVal;
        				try {
        				 	inputVal = Integer.parseInt(fields[r][c].getText());
						} catch (Exception e2) {
	        				JOptionPane.showMessageDialog(frame, "Enbart siffrorna 1-9 är tillåtna", "Error", JOptionPane.ERROR_MESSAGE);
	        				fields[r][c].requestFocus();
							fields[r][c].selectAll();
	        				return;
						}
        				
	        			if(inputVal < 1 || inputVal > 9){
	        				JOptionPane.showMessageDialog(frame, "Enbart siffrorna 1-9 är tillåtna", "Error", JOptionPane.ERROR_MESSAGE);
							fields[r][c].requestFocus();
							fields[r][c].selectAll();
	        				return;
	        			}
	        			inputValues[r][c] = inputVal;
	        			fields[r][c].setForeground(Color.black);
	        			//fields[r][c].setBackground(Color.BLACK);
        			}
        		}
        	}
        	board.setMatrix(inputValues);
        	if(board.solve()) {
            	this.buildBoard(false);
        		JOptionPane.showMessageDialog(frame, "Sudokut är löst!", "GRATTIS!", JOptionPane.INFORMATION_MESSAGE);
        	}else {
            	this.buildBoard(false);
        		JOptionPane.showMessageDialog(frame, "Sudokut har inga lösningar!", "Tyvärr!", JOptionPane.ERROR_MESSAGE);
        	}
        });
        
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        
        contentPane.add(sudokuPanel, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.setSize(width,height);
        frame.pack();
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    public void buildBoard(boolean firstTime) {
    	for(int r=0;r<dim;r++) {
    		for(int c=0;c<dim;c++) {
    			int nbr = board.get(r, c);
    			String str = "";
    			
    			if(nbr!=0) {
    				str+=nbr;
    			}
        		
        		if(firstTime) {
        			fields[r][c] = new JTextField("");
        			fields[r][c].setPreferredSize(new Dimension(height/dim, width/dim));
					fields[r][c].setHorizontalAlignment(SwingConstants.CENTER);
        			fields[r][c].setFont(new Font("Arial", Font.BOLD, height/dim/2));
        			fields[r][c].setBorder(BorderFactory.createLineBorder(Color.white));
        			
        			// SET COLORS
        			if((r/3 == 1 && c/3 != 1) || (c/3 == 1 && r/3 != 1)) {
        				fields[r][c].setForeground(Color.white);
            			fields[r][c].setBackground(Color.DARK_GRAY);
        			}else {
            			fields[r][c].setForeground(Color.DARK_GRAY);
            			fields[r][c].setBackground(Color.ORANGE);
        			}
        			
        			sudokuPanel.add(fields[r][c]).setLocation(r, c);
        		}
        		
        		fields[r][c].setText(str);
        	}
    	}
    }
}