package final_project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.Random;
import javax.swing.JFrame;

public class Frame {
	
  public class Game2048 extends JFrame implements GameConfig {

     private Game2048Listener gl;
     // initialize frame
     public void initUI() {
 		this.setTitle("2048");
 		this.setSize(500, 700);
 		this.setDefaultCloseOperation(3);
 		this.setLocationRelativeTo(null);
 		this.setLayout(null);
 		this.setResizable(false);

 		array = new int[4][4];
                 gl = new Game2048Listener(array, this);
 		this.addKeyListener(gl);
 		this.setVisible(true);
         }

     public void paint(Graphics g) {
 			super.paint(g);
 			draw2048UI(g);
 		}

  // initialize data

    public void draw2048UI(Graphics g) {
		Image img = createImage(this.getWidth(), this.getHeight());//get image?
		Graphics ig = img.getGraphics();
		Graphics2D g2d = (Graphics2D) ig;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// paint


		// set getBackground
		ig.setColor(new Color(128, 118, 105));
		ig.fillRoundRect(x, y, x + (size2 + distance2) * array[0].length, y - 135 + (size2 + distance2) * array.length,
				arcWidth, arcHeight);

		// get square
		for (int r = 0; r < array.length; r++) {
			for (int c = 0; c < array[r].length; c++) {
                                //paint squares
				switch (array[r][c]) {
				case 0:
					ig.setColor(new Color(192, 192, 192));
					break;
				case 2:
					ig.setColor(new Color(250, 240, 230));
					break;
				case 4:
					ig.setColor(new Color(252, 230, 202));
					break;
				case 8:
					ig.setColor(new Color(237, 145, 33));
					break;
				case 16:
					ig.setColor(new Color(255, 128, 0));
					break;
				case 32:
					ig.setColor(new Color(255, 99, 71));
					break;
				case 64:
					ig.setColor(new Color(255, 0, 0));
					break;
				case 128:
					ig.setColor(new Color(255, 227, 132));
					break;
				case 256:
					ig.setColor(new Color(255, 255, 0));
					break;
				case 512:
					ig.setColor(new Color(255, 215, 0));
					break;
				case 1024:
					ig.setColor(new Color(255, 215, 0));
					break;
				case 2048:
					ig.setColor(new Color(255, 215, 0));
					break;
				case 4096:
					ig.setColor(new Color(128, 42, 42));
					break;
				}
				ig.fillRoundRect(x + 10 + distance2 + (size2 + distance2) * c, y + distance2 + (size2 + distance2) * r,
						size2, size2, 20, 20);
			}
		}
	}
}

