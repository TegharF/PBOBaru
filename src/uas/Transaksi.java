package uas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.*;

import net.proteanit.sql.DbUtils;
public class Transaksi extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtJumlah;
	private JTextField txtTanggal;
	private JComboBox cmbIdPrd;
	private JFormattedTextField amountField;
	private JButton btnOK;
	private Connection konek = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transaksi frame = new Transaksi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void refresh()
	{
		try 
		{
			 Class.forName(koneksi.DATABASE_DRIVER);
			 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
			 String query="select * from products order by IdPrd asc";
			 PreparedStatement pst=konek.prepareStatement(query);
			 ResultSet rs=pst.executeQuery();
			 table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public Transaksi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdTranskasi = new JLabel("Tanggal");
		lblIdTranskasi.setBounds(13, 94, 93, 14);
		contentPane.add(lblIdTranskasi);
		
		JLabel lblTgl = new JLabel("Id Prd");
		lblTgl.setBounds(13, 128, 46, 14);
		contentPane.add(lblTgl);
		
		JLabel lblUser = new JLabel("Jumlah");
		lblUser.setBounds(13, 159, 46, 14);
		contentPane.add(lblUser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(273, 59, 309, 285);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		 txtTanggal= new JTextField();
		 txtTanggal.setBounds(116, 91, 138, 20);
		 contentPane.add( txtTanggal);
		 txtTanggal.setColumns(10);
		
		 
		 cmbIdPrd = new JComboBox();
			
					try
					{
						Class.forName(koneksi.DATABASE_DRIVER);
						konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
						String query="select IdPrd from products";
						PreparedStatement pst=konek.prepareStatement(query);
						ResultSet rs=pst.executeQuery();
						
						while(rs.next())
						{
							cmbIdPrd.addItem(rs.getString(1));
							
						}
						
					}
					catch (Exception ex)
					{
						ex.printStackTrace();

					}

					
		
		 cmbIdPrd.setBounds(116, 122, 138, 20);
		 contentPane.add(cmbIdPrd);
		
		
		
		txtJumlah = new JTextField();
		txtJumlah.setEnabled(true);
		//txtJumlah.setText("");
		txtJumlah.setBounds(116, 153, 138, 20);
		contentPane.add(txtJumlah);
		txtJumlah.setColumns(10);
		
		JButton btnNewButton = new JButton("Refresh");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					 Class.forName(koneksi.DATABASE_DRIVER);
					 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					 String query="select * from products order by IdPrd asc";
					 PreparedStatement pst=konek.prepareStatement(query);
					 ResultSet rs=pst.executeQuery();
					 table.setModel(DbUtils.resultSetToTableModel(rs));
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(13, 300, 100, 25);
		contentPane.add(btnNewButton);
		
	
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					String query="select * from users where status=1";
					PreparedStatement pst=konek.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					int count=0;
					String tampung="unknown";
					while (rs.next())
					{
						count=count+1;
						tampung=rs.getString("IdUser");
						
						//JOptionPane.showMessageDialog(null,tampung);
					}
					if(count==1)
					{
						String tamp2="";
						tamp2=txtJumlah.getText();
						
						
						Class.forName(koneksi.DATABASE_DRIVER);
						konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
						String query1="insert into transaksi values (UNIQUEKEY('transaksi'),'"+txtTanggal.getText()+"','"+cmbIdPrd.getSelectedItem().toString()+"',"+txtJumlah.getText()+","+tampung+")";   
						PreparedStatement pst1=konek.prepareStatement(query1);
						/*pst1.setString(1,txtTanggal.getText());
						pst1.setString(2,cmbIdPrd.getSelectedItem().toString());
						pst1.setInt(3,Integer.parseInt(tamp2));
						pst1.setString(4,tampung);*/
						pst1.execute();
						JOptionPane.showMessageDialog(null, "Data Berhasil di insert");
						pst1.close();
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Anda Harus Login Terlebih Dahulu");
					}
					
				
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		btnOk.setBounds(142, 300, 89, 23);
		contentPane.add(btnOk);
		refresh();
	}

}
