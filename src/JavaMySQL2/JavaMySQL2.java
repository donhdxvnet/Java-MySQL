package JavaMySQL2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;

public class JavaMySQL2 {

	private JFrame frame;
	private JTable MyTable;
	private DefaultTableModel MyModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaMySQL2 window = new JavaMySQL2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaMySQL2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 657, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyTable = new JTable();
		frame.getContentPane().add(MyTable, BorderLayout.CENTER);
		
	    JScrollPane MyScrollPane = new JScrollPane(MyTable);
	    MyScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    MyScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	    frame.getContentPane().add(MyScrollPane, BorderLayout.EAST);
	
		MyModel = new DefaultTableModel();
		MyModel.addColumn("colonne 1");
		MyModel.addColumn("colonne 2");	    
		MyModel.addColumn("colonne 3");
		MyModel.addColumn("colonne 4");
		MyModel.addColumn("colonne 5");
		MyModel.addColumn("colonne 6");
		MyModel.addColumn("colonne 7");
		MyModel.addColumn("colonne 8");
		MyModel.addColumn("colonne 9");
		MyModel.addColumn("colonne 10");
		MyModel.addColumn("colonne 11");
		
		MyModel.addRow(
		new Object[]{
				"Data 1",
				"Data 2",
				"Data 3",
				"Data 4",
				"Data 5",
				"Data 6",
				"Data 7",
				"Data 8",
				"Data 9",
				"Data 10",
				"Data 11"
		});		
	    MyTable.setModel(MyModel);	    
	}
}
