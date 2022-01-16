
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.sql.*;

import java.util.*;

public class RegisterGUI extends JFrame {
	final MySQL sql = new MySQL();
	final loginGUI logGui = new loginGUI();
	final Member mmbr = new Member();
	
	private JPanel contentPane;
	private JTextField textFieldRgstIsim;
	private JTextField textFieldRgstSyism;
	private JTextField textFieldRgstSifre;
	private JTextField textFieldRgstAgSifre;
	private JTextField textFieldRgstTlfn;
	private JTextField textFieldIl;
	private JTextField textFieldMail;
	private JTextField textFieldIlce;
	private JTextField textFieldNickName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public boolean ControlSameUsers(String nick){
		for(int i=0;i<logGui.getList().size();i++){
			if(logGui.getList().get(i).getNickname().equals(nick)){
				return true;
			}
		}
		return false;
	}

	
	public RegisterGUI() {
		setTitle("Kay\u0131t Ol");
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 566);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel registerLbl = new JLabel("Kay\u0131t Ol");
		registerLbl.setBounds(165, 5, 184, 42);
		registerLbl.setFont(new Font("Arial Black", Font.BOLD, 15));
		registerLbl.setBackground(Color.WHITE);
		registerLbl.setForeground(Color.DARK_GRAY);
		registerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		textFieldRgstIsim = new JTextField();
		textFieldRgstIsim.setBounds(273, 115, 211, 29);
		textFieldRgstIsim.setColumns(10);
		
		JLabel isimlbl = new JLabel("\u0130sim");
		isimlbl.setBounds(17, 118, 37, 21);
		isimlbl.setFont(new Font("Arial Black", Font.BOLD, 14));
		
		textFieldRgstSyism = new JTextField();
		textFieldRgstSyism.setBounds(273, 157, 211, 29);
		textFieldRgstSyism.setColumns(10);
		
		textFieldRgstSifre = new JTextField();
		textFieldRgstSifre.setBounds(273, 199, 211, 29);
		textFieldRgstSifre.setColumns(10);
		
		textFieldRgstAgSifre = new JTextField();
		textFieldRgstAgSifre.setBounds(273, 241, 211, 29);
		textFieldRgstAgSifre.setColumns(10);
		
		textFieldRgstTlfn = new JTextField();
		textFieldRgstTlfn.setBounds(273, 283, 211, 29);
		textFieldRgstTlfn.setColumns(10);
		
		textFieldNickName = new JTextField();
		textFieldNickName.setBounds(273, 73, 211, 29);
		textFieldNickName.setColumns(10);
		
		JLabel lblSoyisim = new JLabel("Soyisim");
		lblSoyisim.setBounds(17, 160, 87, 21);
		lblSoyisim.setFont(new Font("Arial Black", Font.BOLD, 14));
		
		JLabel lblifre = new JLabel("\u015Eifre");
		lblifre.setBounds(17, 202, 40, 21);
		lblifre.setFont(new Font("Arial Black", Font.BOLD, 14));
		
		JLabel lblTekrarifre = new JLabel("Tekrar \u015Eifre");
		lblTekrarifre.setBounds(17, 244, 101, 21);
		lblTekrarifre.setFont(new Font("Arial Black", Font.BOLD, 14));
		
		JLabel lblTelefonNumaras = new JLabel("Telefon Numaras\u0131");
		lblTelefonNumaras.setBounds(17, 283, 150, 27);
		lblTelefonNumaras.setFont(new Font("Arial Black", Font.BOLD, 14));
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(273, 328, 54, 22);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox.setSelectedIndex(0);
		
		final JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(339, 328, 77, 22);
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Ocak", "\u015Eubat", "Mart", "Nisan", "May\u0131s", "Haziran", "Temmuz", "A\u011Fustos", "Eyl\u00FCl", "Ekim", "Kas\u0131m", "Aral\u0131k"}));
		comboBox_1.setSelectedIndex(0);
		
		final JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(430, 328, 54, 22);
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930"}));
		comboBox_2.setSelectedIndex(0);
		
		JLabel lblDoumTarihi = new JLabel("Do\u011Fum Tarihi");
		lblDoumTarihi.setBounds(17, 328, 150, 21);
		lblDoumTarihi.setFont(new Font("Arial Black", Font.BOLD, 14));
		
		JButton btnKayýt = new JButton("Kay\u0131t");
		btnKayýt.setBounds(120, 473, 95, 28);
		btnKayýt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldRgstIsim.getText().length()==0 ||textFieldRgstSyism.getText().length()==0 ||textFieldRgstSifre.getText().length()==0 
						||textFieldRgstAgSifre.getText().length()==0 ||textFieldRgstTlfn.getText().length()==0||textFieldNickName.getText().length()==0 ){
					JOptionPane.showMessageDialog(contentPane,"Kullanýcý Bilgilerinde Eksik Var","Hata",JOptionPane.WARNING_MESSAGE);
					
				}
				else{
					if(!textFieldRgstSifre.getText().equals(textFieldRgstAgSifre.getText())){
						JOptionPane.showMessageDialog(contentPane,"Girilen Ýki Þifre Ayný Deðil","Hata",JOptionPane.WARNING_MESSAGE);

					}
					
					else if(textFieldRgstTlfn.getText().length()<10 || textFieldRgstTlfn.getText().length()>12){
						JOptionPane.showMessageDialog(contentPane,"Telefon Numarasý Yanlýþ Girildi","Hata",JOptionPane.WARNING_MESSAGE);

					}
					else if(textFieldRgstAgSifre.getText().length()<5){
						JOptionPane.showMessageDialog(contentPane,"Þifre 5 Haneden Daha Kucuk Olamaz","Hata",JOptionPane.WARNING_MESSAGE);

					}
					else if(ControlSameUsers(textFieldNickName.getText())){
						JOptionPane.showMessageDialog(contentPane,"Bu nickname'e sahip bir kullanýcý var","Hata",JOptionPane.WARNING_MESSAGE);
					}
					else{
						
						mmbr.setName(textFieldRgstIsim.getText());
						mmbr.setSurname(textFieldRgstSyism.getText());
						mmbr.setSifre(textFieldRgstSifre.getText());
						mmbr.setTel_num(textFieldRgstTlfn.getText());
						mmbr.setDgmGun(comboBox.getItemAt(comboBox.getSelectedIndex()));
						mmbr.setDgmAy(comboBox_1.getItemAt(comboBox_1.getSelectedIndex()));
						mmbr.setDgmYil(comboBox_2.getItemAt(comboBox_2.getSelectedIndex()));
						mmbr.setIl(textFieldIl.getText());
						mmbr.setIlce(textFieldIlce.getText());
						mmbr.setMail(textFieldMail.getText());
						mmbr.setNickname(textFieldNickName.getText());
						logGui.memberAdd(mmbr);
						try {
							sql.KullanýcýYazma(mmbr);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						logGui.setVisible(true);
						dispose();
						
					}
				}
				
			}
		});
		
		
		JButton btnÇýkýþ = new JButton("\u00C7\u0131k\u0131\u015F");
		btnÇýkýþ.setBounds(290, 473, 98, 28);
		
		btnÇýkýþ.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0){
				
				logGui.setVisible(true);
				dispose();
				
			}
		});
		
		JLabel lblKonum = new JLabel("Konum(\u0130l/\u0130lce)");
		lblKonum.setBounds(17, 370, 150, 21);
		lblKonum.setFont(new Font("Arial Black", Font.BOLD, 14));
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(17, 409, 150, 21);
		lblMail.setFont(new Font("Arial Black", Font.BOLD, 14));
		
		textFieldIl = new JTextField();
		textFieldIl.setBounds(273, 370, 92, 29);
		textFieldIl.setColumns(10);
		
		textFieldMail = new JTextField();
		textFieldMail.setBounds(273, 406, 211, 29);
		textFieldMail.setColumns(10);
		
		textFieldIlce = new JTextField();
		textFieldIlce.setBounds(383, 371, 101, 26);
		textFieldIlce.setColumns(10);
		
		JLabel lblKullancIsmi = new JLabel("Kullan\u0131c\u0131 \u0130smi");
		lblKullancIsmi.setBounds(17, 81, 180, 21);
		lblKullancIsmi.setFont(new Font("Arial Black", Font.BOLD, 14));
		contentPane.setLayout(null);
		contentPane.add(registerLbl);
		contentPane.add(lblifre);
		contentPane.add(lblTekrarifre);
		contentPane.add(lblTelefonNumaras);
		contentPane.add(lblSoyisim);
		contentPane.add(isimlbl);
		contentPane.add(lblDoumTarihi);
		contentPane.add(lblKonum);
		contentPane.add(lblMail);
		contentPane.add(btnKayýt);
		contentPane.add(textFieldRgstAgSifre);
		contentPane.add(textFieldRgstSifre);
		contentPane.add(textFieldRgstSyism);
		contentPane.add(textFieldRgstIsim);
		contentPane.add(textFieldRgstTlfn);
		contentPane.add(comboBox);
		contentPane.add(comboBox_1);
		contentPane.add(comboBox_2);
		contentPane.add(textFieldIl);
		contentPane.add(textFieldIlce);
		contentPane.add(textFieldMail);
		contentPane.add(textFieldNickName);
		contentPane.add(btnÇýkýþ);
		contentPane.add(lblKullancIsmi);
	}
}
