package views;

import javax.swing.JPanel;

import model.Pregunta;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TableroView extends JPanel {

	GridBagLayout gridBagLayout;
	JButton Boton1;
	JButton Boton2;
	JButton Boton3;
	JButton Boton4;
	
	public TableroView( MainWindow main, Pregunta pregunta ) {

		gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWidths = new int[] {225, 225, 0};
		gridBagLayout.rowHeights = new int[] { 100, 100, 100, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel( pregunta.getPregunta() );
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		Boton1 = new JButton(pregunta.getRespuestas()[0]);
		Boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.getClientGame().choose(0);
				DisableButtons();				
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		add(Boton1, gbc_btnNewButton);
		
		Boton2 = new JButton(pregunta.getRespuestas()[1]);
		Boton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getClientGame().choose(1);
				DisableButtons();
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 1;
		add(Boton2, gbc_btnNewButton_2);
		
		Boton3 = new JButton(pregunta.getRespuestas()[2]);
		Boton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getClientGame().choose(2);
				DisableButtons();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 2;
		add(Boton3, gbc_btnNewButton_1);
		
		Boton4 = new JButton(pregunta.getRespuestas()[3]);
		Boton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getClientGame().choose(3);
				DisableButtons();
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 2;
		add(Boton4, gbc_btnNewButton_3);

	}
	
	public void DisableButtons() {
		Boton1.setEnabled(false);
		Boton2.setEnabled(false);
		Boton3.setEnabled(false);
		Boton4.setEnabled(false);
	}
	
	public void UpdateButons() {
		Boton1.setEnabled(true);
		Boton2.setEnabled(true);
		Boton3.setEnabled(true);
		Boton4.setEnabled(true);
	}
	
	public void ReSize() {
		int height = this.getParent().getSize().height;
		int width = this.getParent().getSize().width;
		
		gridBagLayout.columnWidths = new int[] {width / 2, width / 2, 0};
		gridBagLayout.rowHeights = new int[] {height / 3, height / 3, height / 3, 0};
	}

}
