package views;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.google.gson.Gson;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

import controller.HubController;
import model.Client;
import model.Lobby;
import sockets.GameClient;
import sockets.GameServer;

public class Main extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	private JScrollPane scrollPane;
	private JTable table;
	private HubController hubController;
	
	public MainWindow main;	
	Gson gson;
	//scrollPane.setViewportView( hubController.showLobbys( client.ListLobbys() ) );
	
	public Main( MainWindow main ) {

		gson = new Gson();
		table = new JTable();
		hubController = new HubController( table );
		this.main = main;
		
		table.setModel(hubController.showLobbys( main.getClient().ListLobbys().getLobbys()));
				
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		add(panel, BorderLayout.EAST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JButton btnNewButton = new JButton("refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				table.setModel(hubController.showLobbys( main.getClient().ListLobbys().getLobbys() ));
			}
		});
		btnNewButton.setAlignmentX(1.0f);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("host");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Lobby lobby = main.getClient().Create();
			}
		});
		btnNewButton_1.setAlignmentX(1.0f);
		panel.add(btnNewButton_1);
		
		
		scrollPane = new JScrollPane();
		this.add(scrollPane, BorderLayout.CENTER);

		table.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
		      JTable target = (JTable)e.getSource();
		      int row = target.getSelectedRow();
		      String ip = target.getValueAt(row, 0).toString();
		      
		      Lobby lobby = main.getClient().ListLobbys().find(ip);
		      if ( lobby != null) {
		    	  System.out.println( main.getClient().Join( lobby  ) );  
		      }		      		      
		}});

		
		scrollPane.setViewportView(table);		

	}
	


}
