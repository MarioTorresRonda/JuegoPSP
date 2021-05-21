package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Lobby;

public class HubController {

	private TableModel tableModel;
	List<String> columns = new ArrayList<String>();
    List<String[]> values = new ArrayList<String[]>();
	
    public HubController(JTable table) {
    	columns.add("host");
        columns.add("Jugadores");
        
        table = new JTable();
		table.setModel( showLobbys() );
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
   
		
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

