import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JavaCrud {

	JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
	
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
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
	public JavaCrud() {
		initialize();
		Connect();
		 table_load();
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root", "");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	
	  public void table_load() { try { pst =
	  con.prepareStatement("select * from book"); rs = pst.executeQuery();
	  table.setModel(DbUtils.resultSetToTableModel(rs)); }
	  catch (SQLException e) {
	  e.printStackTrace(); }}
	 

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(235, 11, 195, 47);
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(26, 96, 310, 143);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 30, 90, 20);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 61, 90, 20);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(10, 92, 90, 20);
		panel.add(lblNewLabel_1_1_1);

		txtbname = new JTextField();
		txtbname.setBounds(113, 32, 176, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);

		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(113, 63, 176, 20);
		panel.add(txtedition);

		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(113, 94, 176, 20);
		panel.add(txtprice);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String bname, edition, price;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				try {
					pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					 table_load();

					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
				}

				catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(36, 250, 85, 35);
		frame.getContentPane().add(btnNewButton);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				System.exit(0);
				
				
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExit.setBounds(141, 250, 85, 35);
		frame.getContentPane().add(btnExit);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
				
				
				
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnClear.setBounds(251, 250, 85, 35);
		frame.getContentPane().add(btnClear);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(366, 83, 287, 202);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(26, 303, 261, 47);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_1_2 = new JLabel("Book ID");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(33, 16, 90, 20);
		panel_1.add(lblNewLabel_1_1_2);

		txtbid = new JTextField();
		txtbid.setToolTipText("Enter Integer");
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
			          
		            String id = txtbid.getText();
		 
		                pst = con.prepareStatement("select name,edition,price from book where id = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();
		 
		            if(rs.next()==true)
		            {
		              
		                String name = rs.getString(1);
		                String edition = rs.getString(2);
		                String price = rs.getString(3);
		                
		                txtbname.setText(name);
		                txtedition.setText(edition);
		                txtprice.setText(price);
		                
		                
		            }  
		            else
		            {
		             txtbname.setText("");
		             txtedition.setText("");
		                txtprice.setText("");
		                
		            }
		            
		 
		 
		        }
		catch (SQLException ex) {
		          
		        }
				
			}
		});
		txtbid.setColumns(10);
		txtbid.setBounds(105, 18, 127, 20);
		panel_1.add(txtbid);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price,bid;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				bid  = txtbid.getText();
				try {
				pst = con.prepareStatement("update book set name= ?,edition=?,price=? where id =?");
				pst.setString(1, bname);
				            pst.setString(2, edition);
				            pst.setString(3, price);
				            pst.setString(4, bid);
				            pst.executeUpdate();
				            JOptionPane.showMessageDialog(null, "Record Update!!!!!");
				            table_load();
				          
				            txtbname.setText("");
				            txtedition.setText("");
				            txtprice.setText("");
				            txtbname.requestFocus();
				}
				 
				            catch (SQLException e1) {
				e1.printStackTrace();
				}
				
				
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdate.setBounds(384, 299, 85, 35);
		frame.getContentPane().add(btnUpdate);

		JButton btnClear_1_1 = new JButton("Delete");
		btnClear_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
                String bid;
bid  = txtbid.getText();
try {
pst = con.prepareStatement("delete from book where id =?");
    pst.setString(1, bid);
    pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
    table_load();
  
    txtbname.setText("");
    txtedition.setText("");
    txtprice.setText("");
    txtbname.requestFocus();
}

    catch (SQLException e1) {
e1.printStackTrace();
}
				
				
				
				
				
				
			}
		});
		btnClear_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnClear_1_1.setBounds(495, 299, 85, 35);
		frame.getContentPane().add(btnClear_1_1);
	}
}
