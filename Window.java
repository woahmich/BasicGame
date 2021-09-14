package Game;
import java.awt.Canvas;
import javax.swing.JFrame;
import java.awt.Dimension;

public class Window extends Canvas {

	private static final long serialVersionUID = 573860602378245302L;
	
	public Window(int width, int height, String title, Game game) {
		// JFrame is the frame of window
		JFrame frame = new JFrame(title);
		
		// Set sizing of the frame
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		// closes the frame when exiting
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// disable resizing of window
		frame.setResizable(false);
		// window starts middle of screen instead of top left
		frame.setLocationRelativeTo(null);
		// adds game class to the frame
		frame.add(game);
		// makes frame visible
		frame.setVisible(true);
		// calls start method
		game.start();
		
	}

}
