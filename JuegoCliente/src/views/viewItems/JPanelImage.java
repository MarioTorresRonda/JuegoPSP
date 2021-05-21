package views.viewItems;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelImage extends JPanel {

	private static final long serialVersionUID = 1L;

	Image fondo;
	Image bgImage;
	
	public JPanelImage( Image _fondo) {
		super();
		fondo = _fondo;
		bgImage = fondo;
	}
	
	public void ReSize() {
		if ( getWidth() > 0 && getHeight() > 0) {
			Image img = bgImage.getScaledInstance( getWidth() - 10, getHeight() - 10, Image.SCALE_DEFAULT );
			ImageIcon imageIcon = new ImageIcon(img);
			bgImage = imageIcon.getImage();
		}
		repaint();
	}
	
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);
	}
	
	
}
