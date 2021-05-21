package views;

import javax.swing.JPanel;
import com.google.gson.Gson;

import utils.Assets;
import utils.Info;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import views.viewItems.BotonImagen;
import views.viewItems.JPanelImage;

import java.awt.FlowLayout;

public class Main extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	private Lista lista;
	Gson gson;
	
	public Main() {
		setBorder(null);

		gson = new Gson();
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanelImage(Assets.fondos[1]);
		panel.setBorder(null);
		add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new BotonImagen(" Refrescar ", Assets.ButtonI[0], Assets.ButtonI[1]);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lista.UpdateLista();
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnNewButton.setAlignmentX(1.0f);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new BotonImagen(" Host ", Assets.ButtonV[0], Assets.ButtonV[1]);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Info.getLobbyClient().Create();
			}
		});
		btnNewButton_1.setAlignmentX(1.0f);
		panel.add(btnNewButton_1);
		
		lista = new Lista();
		this.add(lista, BorderLayout.CENTER);
		
	}
	


}
