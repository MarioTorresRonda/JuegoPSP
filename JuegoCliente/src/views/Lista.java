package views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Lobby;
import utils.Info;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

public class Lista extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	
	public Lista() {
		setBorder(null);
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		this.add(scrollPane);
		
		table = new JTable();
		
		columns.add("host");
        columns.add("Jugadores");
        
        table = new JTable();
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
		table.setModel( showLobbys() );
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.setRowHeight(50);
		table.setFont( new Font("Dialog", Font.PLAIN, 23) );
		
		table.getTableHeader().setDefaultRenderer( new MyHeaderRenderer() );
		
		table.setModel( showLobbys( Info.getLobbyClient().ListLobbys().getLobbys()) );
		
		table.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
		      JTable target = (JTable)e.getSource();
		      int row = target.getSelectedRow();
		      String ip = target.getValueAt(row, 0).toString();
		      
		      Lobby lobby = Info.getLobbyClient().ListLobbys().find(ip);
		      if ( lobby != null) {
		    	  System.out.println( Info.getLobbyClient().Join( lobby  ) );  
		      }		      		      
		}});
		
		scrollPane.setViewportView(table);

	}
	
	private TableModel tableModel;
	List<String> columns = new ArrayList<String>();
    List<String[]> values = new ArrayList<String[]>();
	
	public void UpdateLista() {
		table.setModel( showLobbys( Info.getLobbyClient().ListLobbys().getLobbys() ));
	}
    
	public TableModel showLobbys( List<Lobby> lobbys ) {
		
		values.clear();
		
		for (Lobby lobby : lobbys) {
			values.add(new String[] { lobby.getHost() , lobby.getJugadores().size() + "" });
		}
		
        tableModel = new MiTableModel(values.toArray(new Object[][] {}), columns.toArray());
        return tableModel;	
	}
	
	public TableModel showLobbys() {
		tableModel = new MiTableModel(values.toArray(new Object[][] {}), columns.toArray());
        return tableModel;	
	}

}

class MiTableModel extends DefaultTableModel
{
	private static final long serialVersionUID = 1L;
	
	public MiTableModel(Object[][] array, Object[] array2) {
		super(array, array2);
	}
	
	public boolean isCellEditable (int row, int column)
	{
       return false;
   	}
}

class MyHeaderRenderer extends DefaultTableCellRenderer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyHeaderRenderer()
	{
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		setText(value.toString());
		setFont( new Font("Dialog", Font.BOLD, 26) );
		setForeground(Color.white);
		setBackground( new Color(32, 136, 203) );
		setHorizontalAlignment(CENTER);
		
		return this;
	}
}



