package views.viewItems;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BotonImagen extends JButton {

	private static final long serialVersionUID = 1L;

	Image Imagen;
	Image ImagenP;
	
	public BotonImagen(String _texto, Image _Imagen, Image _ImagenP) {
		
		Imagen = _Imagen;
		ImagenP = _ImagenP;
		
		setBackground( null );
		setContentAreaFilled(false);
		
		//Creamos las imagenes
		
		Image img = Imagen.getScaledInstance(_texto.length() * 9, 30, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(img);
		setIcon(imageIcon);
		
		Image imgP = ImagenP.getScaledInstance(_texto.length() * 9, 30, Image.SCALE_SMOOTH);
		ImageIcon imageIconP = new ImageIcon(imgP);
		setPressedIcon(imageIconP);
		
		//Creamos el boton
		setText(_texto);		
		setFont(new Font("Dialog", Font.BOLD, 14));
		setBorder(null);
		setForeground( Color.WHITE );
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
		
	}
	
	public BotonImagen( String _texto, Image _Imagen) {
		
		Imagen = _Imagen;
		
		setBackground( null );
		setContentAreaFilled(false);
		
		Image img = Imagen.getScaledInstance(_texto.length() * 9, 30, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(img);
		setIcon(imageIcon);
		
		//Creamos el boton
		setText(_texto);		
		setFont(new Font("Dialog", Font.BOLD, 14));
		setBorder(null);
		setForeground( Color.WHITE );
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
	}
	
	public BotonImagen(Image _Imagen) {
		
		Imagen = _Imagen;
		
		setBackground( null );
		setContentAreaFilled(false);
		
		Image img = Imagen.getScaledInstance(50, 30, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(img);
		setIcon(imageIcon);
		
		//Creamos el boton
		setFont(new Font("Dialog", Font.BOLD, 14));
		setBorder(null);
		setForeground( Color.WHITE );
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
	}
	
	public void Resize() {
		if ( getWidth() > 0 && getHeight() > 0) {
			Image img = Imagen.getScaledInstance( getWidth() - 10 , getHeight() - 10, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(img);
			setIcon(imageIcon);
		}

	}
	
	public void setImage(Image _Imagen) {
		Imagen = _Imagen;
		if ( getWidth() > 0 && getHeight() > 0) {
			Image img = Imagen.getScaledInstance( getWidth() - 10 , getHeight() - 10, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(img);
			setIcon(imageIcon);
		}
		
	}
}
