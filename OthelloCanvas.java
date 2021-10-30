import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OthelloCanvas extends JPanel implements MouseListener {

	private final boolean playAgainstAi = true;

	private Othello app;

	private Board board = new Board();

	private boolean loop = true;
	
	private int skippedCount = 0;

	public OthelloCanvas(Othello applet) {
		app = applet;
		addMouseListener(this);
		repaint();
		board.board[3][3] = -1;
		board.board[4][4] = -1;
		board.board[3][4] = 1;
		board.board[4][3] = 1;
		if(!playAgainstAi) {
			Move.team = 0;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		for(int i = 0; i <= 8; i++) {
			g.drawLine(i*50, 0, i*50, 400);
			g.drawLine(0, i*50, 400, i*50);
		}
		for(int i = 0; i < board.board.length; i++) {
			for(int j = 0; j < board.board[0].length; j++) {
				if(board.board[i][j] == 1) {
					g.setColor(Color.BLACK);
					g.fillOval(i*50+5, j*50+5,40, 40);
				} else if(board.board[i][j] == -1) {
					g.setColor(Color.WHITE);
					g.fillOval(i*50+5, j*50+5,40, 40);
				}
			}
		}

		//Move.team = board.turn;
		if(board.turn == Move.team) {
			Move move = new Move(board);
			if(board.makeTurn(move.x, move.y)) {
				skippedCount = 0;
			} else {
				skippedCount++;
			}
			board.turn = board.turn == 1 ? -1:1;
			if(skippedCount > 1) {
				endGame();
			}
		} else {
			if(!board.canPlay()) {
				System.out.println("Can't play, skipping");
				board.turn = board.turn == 1 ? -1:1;
			}
		}
		if(loop) {
			repaint();
		}
	}

	public void endGame() {
		loop = false;
		int blackCount = 0, whiteCount = 0;
		for(int i = 0; i < board.board.length; i++) {
			for(int j = 0; j < board.board[0].length; j++) {
				if(board.board[i][j] == 1) {
					blackCount++;
				} else if(board.board[i][j] == -1) {
					whiteCount++;
				}
			}
		}
		if(blackCount > whiteCount) {
			System.out.println("Black wins!");
		} else if(whiteCount > blackCount) {
			System.out.println("White wins!");
		} else {
			System.out.println("Tie");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(board.turn == Move.team) {
			return;
		}
		int clickedX = arg0.getX()/50;
		int clickedY = arg0.getY()/50;
		//board[clickedX][clickedY] = turn;
		if(board.makeTurn(clickedX, clickedY)) {
			board.turn = board.turn == 1 ? -1:1;
		}
		boolean over = true;
		for(int i = 0; i < 2; i++) {
			if(board.canPlay()) {
				over = false;
			}
			board.turn = board.turn == 1 ? -1:1;
		}
		if(over) {
			endGame();
		}
		if(loop && !board.canPlay()) {
			System.out.println("Can't play, skipping");
			skippedCount++;
			board.turn = board.turn == 1 ? -1:1;
		} else {
			skippedCount = 0;
		}
		//if(skippedCount > 1) {
			//endGame();
		//}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public Dimension getPreferredSize() {
		return new Dimension(400, 400);
	}

}
