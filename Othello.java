import java.awt.*;
import javax.swing.*;

public class Othello extends JFrame {
	
	private OthelloCanvas canvas;
	
	public Othello() {
		canvas = new OthelloCanvas(this);
		canvas.setBackground(Color.GREEN);
		canvas.setFocusable(true);
		canvas.requestFocus();
		
		Container c = getContentPane();
		c.add(canvas, BorderLayout.CENTER);
		pack();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Othello window = new Othello();
		window.setTitle("Othello");
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
	}

}
