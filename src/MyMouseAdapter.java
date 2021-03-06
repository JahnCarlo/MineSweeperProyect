import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {

	private Random generator = new Random();
	private Random rand = new Random();

	public void mousePressed(MouseEvent e) {

		switch (e.getButton()) {
		case 1:	//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}

			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:	
			//Right Mouse Button
			Component d = e.getComponent();
			while (!(d instanceof JFrame)) {
				d = d.getParent();
				if (d == null) {
					return;
				}
			}

			JFrame myFramed = (JFrame) d;
			MyPanel myPaneld = (MyPanel) myFramed.getContentPane().getComponent(0);
			Insets myInsetsd = myFramed.getInsets();
			int x1d = myInsetsd.left;
			int y1d = myInsetsd.top;
			e.translatePoint(-x1d, -y1d);
			int xd = e.getX();
			int yd = e.getY();
			myPaneld.x = xd;
			myPaneld.y = yd;
			myPaneld.mouseDownGridX = myPaneld.getGridX(xd, yd);
			myPaneld.mouseDownGridY = myPaneld.getGridY(xd, yd);
			myPaneld.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {

		switch (e.getButton()) {
		case 1:	//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}

			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);



			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						if (myPanel.numbersArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] >=0 && myPanel.numbersArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] < 9 ){
							//If there is no bomb or number, turn white
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.WHITE;
							myPanel.repaint();
						}
						
						if (myPanel.numbersArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == 9){
							//If there is a bomb, show red
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.RED;
							myPanel.repaint();
						}
					}
				}
			}
			myPanel.repaint();
			break;
		case 3:	

			Component d = e.getComponent();
			while (!(d instanceof JFrame)) {
				d = d.getParent();
				if (d == null) {
					return;
				}
			}

			JFrame myFramed = (JFrame)d;
			MyPanel myPaneld = (MyPanel) myFramed.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsetsd = myFramed.getInsets();
			int x1d = myInsetsd.left;
			int y1d = myInsetsd.top;
			e.translatePoint(-x1d, -y1d);
			int xd = e.getX();
			int yd = e.getY();
			myPaneld.x = xd;
			myPaneld.y = yd;

			if ((myPaneld.mouseDownGridX == -1) || (myPaneld.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			}

			myPaneld.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}