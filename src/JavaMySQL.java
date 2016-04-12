import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.net.*;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator; 

public class JavaMySQL {

	private JFrame frmXuanjava;
	private JTextField textField;
	private JTextArea textArea;
	private JButton btnRcuprerIps;
	private JButton btnNewButton;
	private ResultSet resultats = null;
	public JTable tableDonnees;
	public List<Operation> OperationsList = new ArrayList<Operation>();
	private DefaultTableModel aModel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaMySQL window = new JavaMySQL();
					window.frmXuanjava.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaMySQL() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmXuanjava = new JFrame();
		frmXuanjava.setResizable(false);
		frmXuanjava.setTitle("JavaMySQL");
		frmXuanjava.setBounds(100, 100, 596, 403);
		frmXuanjava.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		textArea = new JTextArea();
		textArea.setRows(2);
		frmXuanjava.getContentPane().add(textArea, BorderLayout.SOUTH);
		
		textField = new JTextField();
		frmXuanjava.getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		btnRcuprerIps = new JButton("Toutes les IPs");
		btnRcuprerIps.setEnabled(false);
		btnRcuprerIps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				computing();
			}
		});
		frmXuanjava.getContentPane().add(btnRcuprerIps, BorderLayout.EAST);		
		tableDonnees = new JTable();
		
		btnNewButton = new JButton("Base de donn\u00E9es");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{	
				
				//DisplayData(null);
				
				Connection connection = null;				
				try
				{
					String server = "test";
					String user = "test";
					String password = "test";
					//String db = textField.getText();
					String db = "test";
					//String sql = textArea.getText();
					String sql = "select * from Operations;";
					
					connection = DriverManager.getConnection("jdbc:mysql://" + server + "?user=" + user + "&password=" + password);
				    //Do something with the Connection
				    System.out.println("Good connection");		
				 
				    //Database "test"
				    connection.setCatalog(db);
				    
				   //tao doi tuong statement
				   Statement statement = connection.createStatement();
				 
				   //thuc thi cau lenh sql tra ve resultset
				   resultats = statement.executeQuery(sql);
				 //parcours des donn�es retourn�es
				 //affiche("parcours des donn�es retourn�es");

				   //ProductDetail
				  /* while (resultats.next())
				   {
					   	Operation op = new Operation();
					  // 	System.out.println(resultats.getString("nom_operation"));
				   		op.setNom(resultats.getString("nom_operation"));
				   		//System.out.println(resultats.getString("date"));
				   		op.setDate(resultats.getString("date"));
				   		//System.out.println("dateeeeeeeeeeeeeee : " + op.getDate());
				   		
				   		try {
				   			OperationsList.add(op);
				   		} catch (NullPointerException exception1) {
				   			System.out.println("exception1 : " + exception1.getMessage());
				   		} catch (IllegalArgumentException exception2) {
				   			System.out.println("exception2 : " + exception2.getMessage());
				   		} catch (ClassCastException exception3) {
				   			System.out.println("exception3 : " + exception3.getMessage());
				   		}
				   }*/
				  
				   //DisplayData(OperationsList); //methode traditionnelle
				   				   
				   try {
						
					   /*DefaultTableModel aModel = new DefaultTableModel()
						{
							//setting the jtable read only
							@Override
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};*/					   	
					   
						 ResultSetMetaData rsmd = resultats.getMetaData();
						 int nbCols = rsmd.getColumnCount();							
						 System.out.println("row count : " + nbCols);								 
						 
						 boolean encore = resultats.next();
						 Object[] objects = new Object[nbCols + 1]; 
						aModel = new DefaultTableModel(objects, nbCols);
						
						
						Object[] tableColumnNames = new Object[nbCols + 1];
						 for (int i = 1; i <= nbCols; i++)
						 {	
							 tableColumnNames[i] = "colonne " + i; 
						 }
						aModel.setColumnIdentifiers(tableColumnNames);
						int j = 0;
						while (encore)
						 {	
							j++;
							System.out.println("row : " + j);				 
							 for (int i = 1; i <= nbCols; i++)
							 {								 
								 objects[i] = resultats.getString(i);
								// System.out.print(objects[i] + "\n");
								 //System.out.print(resultats.getString(i) + "\n");
								
							 }	 	
							 aModel.addRow(objects);
							 //System.out.println();
							 encore = resultats.next();
						 }
						 
					System.out.println("row count aModel : " + aModel.getRowCount());				 
					tableDonnees.setModel(aModel); //Remplir le tableau avec le nombre de colonnes dynamique	 
					
					resultats.close();					
															
				 } catch (SQLException e) {
					 System.out.println(e.getMessage());
				 }

				// affiche("fin du programme");
				 //System.exit(0);
				 
				
				}catch (SQLException ex){
				    // handle any errors
				    System.out.println("SQLException: " + ex.getMessage());
				    System.out.println("SQLState: " + ex.getSQLState());
				    System.out.println("VendorError: " + ex.getErrorCode());
				}
											
			}
			
		});
		frmXuanjava.getContentPane().add(btnNewButton, BorderLayout.WEST);
		
		
		frmXuanjava.getContentPane().add(tableDonnees, BorderLayout.CENTER);
		
		DocumentListener documentListener = new DocumentListener() {
		  public void changedUpdate(DocumentEvent documentEvent)
		  {
		    //printIt(documentEvent);		  
		  }
		  
		  public void insertUpdate(DocumentEvent documentEvent) {
		    //printIt(documentEvent);
			
		  }
		  
		  public void removeUpdate(DocumentEvent documentEvent) {
			  //printIt(documentEvent);			
		  }
		  
		  private void printIt(DocumentEvent documentEvent)
		  {
			  DocumentEvent.EventType type = documentEvent.getType();
			    String typeString = null;
			    if (type.equals(DocumentEvent.EventType.CHANGE)) {
			      typeString = "Change";
			}  else if (type.equals(DocumentEvent.EventType.INSERT)) {
			  typeString = "Insert";
			}  else if (type.equals(DocumentEvent.EventType.REMOVE)) {
			  typeString = "Remove";
			}
			System.out.print("Type : " + typeString);
			Document source = documentEvent.getDocument();
			int length = source.getLength();
			System.out.println("Length: " + length);
		  }
		};
		
		textField.getDocument().addDocumentListener(documentListener);	
	
	}	
	
	private void computing()
	{
		textArea.setText(""); //Vider le textarea
		
		String url = textField.getText();
	    System.out.println(url);
	    
		try {
			InetAddress[] addresses =
			InetAddress.getAllByName(url);
			for (int i = 0; i < addresses.length; i++) {
				textArea.append(addresses[i].toString() + "\n");				
				//System.out.println(addresses[i]);
			}
		}
		catch (UnknownHostException e) {
			System.out.println("Could not find : " + url);
		}		    			
	}
	
	private static void arret(String message)
	{				
		System.err.println(message);				
		System.exit(99);				
	}
	
	private static void affiche(String message)
	{	
		System.out.println(message);	
	}
	
	/*
	public void DisplayData(List<Operation> OperationsList)
	{
				
		DefaultTableModel aModel = new DefaultTableModel()
		{
			//setting the jtable read only
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
				
		//Vider la table 
		//aModel.setRowCount(0);
		//aModel.getDataVector().removeAllElements();
		//aModel.fireTableDataChanged();
	    		
		while (aModel.getRowCount() > 0)
		{
			aModel.removeRow(0);
		}
			
		//setting the column name
		Object[] tableColumnNames = new Object[2];
		tableColumnNames[0] = "Product Name";
		tableColumnNames[1] = "Description";
	
		aModel.setColumnIdentifiers(tableColumnNames);
		if (OperationsList == null) {
			this.tableDonnees.setModel(aModel);
			return;
		}
	
		Object[] objects = new Object[2];
		ListIterator<Operation> lstrg = OperationsList.listIterator();
		//populating the tablemodel
		while (lstrg.hasNext())
		{
			Operation newcus = lstrg.next();
			objects[0] = newcus.getNom();
			//System.out.println(newcus.getNom());
			objects[1] = newcus.getDate();	
			//System.out.println(newcus.getDate());
			aModel.addRow(objects);
		}
						
		//binding the jtable to the model
		this.tableDonnees.setModel(aModel);
	}	
	*/
}
