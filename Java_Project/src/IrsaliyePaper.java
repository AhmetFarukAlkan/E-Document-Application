import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.sun.prism.Graphics;

import java.util.Date;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
 

public class IrsaliyePaper extends JFrame {
	MySQL sql = new MySQL();
	E_Irsaliye irsaliye = new E_Irsaliye();
	loginGUI loggui = new loginGUI();
	
	static Member mmbr = new Member();
	private JPanel contentPane;	
	private JTextField textFieldFaturaNo;
	private JTable table;
	private JTextField textFieldTeslimEden;
	private JTextField textFieldTeslimAlan;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;
	private JComboBox<String> comboBox_3;
	private JComboBox<String> comboBox_4;
	private JComboBox<String> comboBox_5;
	private JButton btnKaydet;
	private JButton btnEkranGrnts;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IrsaliyePaper frame = new IrsaliyePaper(mmbr);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static BufferedImage getScreenShot(Component component) {
		BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
		component.paint(image.getGraphics());
		return image;
	}
	public static void SaveScreenShot(Component component , String filename)throws Exception{
		BufferedImage img = getScreenShot(component);
		ImageIO.write(img, "png", new File(filename));
	}
	
	void takeSnapShot(Component capture,String filename){
		try {
			SaveScreenShot(contentPane,filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */

	public boolean ControlUsers(String nick){
		for(int i=0;i<loggui.getList().size();i++){
			if(loggui.getList().get(i).getNickname().equals(nick)){
				return true;
			}
		}
		return false;
	}
	
	public void Goruntuleme(Member mmbr, E_Irsaliye irsaliye,boolean edit) {
		IrsaliyePaper irspaper = new IrsaliyePaper(mmbr);
		irspaper.textFieldTeslimEden.setText(irsaliye.getgonderenIsim());
		irspaper.textFieldTeslimAlan.setText(irsaliye.getAliciIsim());
		irspaper.textFieldFaturaNo.setText(irsaliye.getId());
		String[] tarih = irsaliye.getTarih().split("/");
		irspaper.comboBox.setSelectedItem(tarih[0]);
		irspaper.comboBox_1.setSelectedItem(tarih[1]);
		irspaper.comboBox_2.setSelectedItem(tarih[2]);
		tarih = irsaliye.getSevkTarihi().split("/");

		
		irspaper.comboBox_3.setSelectedItem(tarih[0]);
		irspaper.comboBox_4.setSelectedItem(tarih[1]);
		irspaper.comboBox_5.setSelectedItem(tarih[2]);
		
		for(int i=0;i<irsaliye.getMallar().size();i++){
			irspaper.table.setValueAt(irsaliye.getMallar().get(i).getMalCinsi(), i, 0);
			irspaper.table.setValueAt(irsaliye.getMallar().get(i).getAdet(), i, 1);
			irspaper.table.setValueAt(irsaliye.getMallar().get(i).getBrmFiyat(), i, 2);
			irspaper.table.setValueAt(irsaliye.getMallar().get(i).getTutar(), i, 3);
		}
		irspaper.btnEkranGrnts.setVisible(false);
		if(!edit){

			irspaper.table.setEnabled(false);
			irspaper.textFieldTeslimAlan.setEditable(false);
			irspaper.textFieldTeslimEden.setEditable(false);
			/*irspaper.comboBox.setEnabled(false);
			irspaper.comboBox_1.setEnabled(false);
			irspaper.comboBox_2.setEnabled(false);
			irspaper.comboBox_3.setEnabled(false);
			irspaper.comboBox_4.setEnabled(false);
			irspaper.comboBox_5.setEnabled(false);*/
			irspaper.btnKaydet.setVisible(false);
			irspaper.btnEkranGrnts.setVisible(true);
		}
		irspaper.setVisible(true);
	
	}

	public int searchIrsaliye(Member mmbr,String id){
		for(int i =0 ; i<mmbr.getIrsaliyeler().size();i++){
			if(mmbr.getIrsaliyeler().get(i).getId().equals(id)){
				return i;
			}	
		}
		return -1;
	}
	public String idSearch(Member mmbr, E_Irsaliye irsaliye){
		for(int i =0 ; i<mmbr.getIrsaliyeler().size();i++){
			if(mmbr.getIrsaliyeler().get(i).getSaat().equals(irsaliye.getSaat())&&mmbr.getIrsaliyeler().get(i).getTarih().equals(irsaliye.getTarih())){
				return mmbr.getIrsaliyeler().get(i).getId();
			}	
		}
		return "0";
		
	}
	public boolean rowisEmpty(int row) {
		for(int i=0;i<3;i++){
			if(table.getValueAt(row, i)==null || table.getValueAt(row, i).toString().equals("")){
				return true;
			}
		}
		
		return false;
	}
	public boolean isTableEmpty(){
		for(int i=0;i<15;i++){
			if(!rowisEmpty(i)){
				return false;
			}
		}
		return true;
	}
	
	public IrsaliyePaper(final Member mmbr) {
		setTitle("E-\u0130rsaliye");
		setResizable(false);
		setBackground(UIManager.getColor("CheckBox.interiorBackground"));
		setBounds(100, 100, 622, 694);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("CheckBox.interiorBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSevkIrsaliyesi = new JLabel("Sevk \u0130rsaliyesi");
		lblSevkIrsaliyesi.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		Date zmn = new Date();

		
		JLabel lblDzenlenmeTarihi = new JLabel("D\u00FCzenlenme Tarihi");
		lblDzenlenmeTarihi.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		DateFormat day = new SimpleDateFormat("dd");
		comboBox.setSelectedItem(day.format(zmn));
	
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Ocak", "\u015Eubat", "Mart", "Nisan", "May\u0131s", "Haziran", "Temmuz", "A\u011Fustos", "Eyl\u00FCl", "Ekim", "Kas\u0131m", "Aral\u0131k"}));
		DateFormat month = new SimpleDateFormat("MM");
		comboBox_1.setSelectedIndex(Integer.valueOf(month.format(zmn).toString())-1);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"2025","2024","2023","2022","2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930"}));
		DateFormat year = new SimpleDateFormat("yyyy");
		comboBox_2.setSelectedItem(year.format(zmn));
		JLabel lblFiilSevkTarihi = new JLabel("Fiil Sevk Tarihi");
		lblFiilSevkTarihi.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		
		comboBox_3 = new JComboBox<String>();
		comboBox_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));

		
		comboBox_4 = new JComboBox<String>();
		comboBox_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox_4.setModel(new DefaultComboBoxModel<String>(new String[] {"Ocak", "\u015Eubat", "Mart", "Nisan", "May\u0131s", "Haziran", "Temmuz", "A\u011Fustos", "Eyl\u00FCl", "Ekim", "Kas\u0131m", "Aral\u0131k"}));
		
		comboBox_5 = new JComboBox<String>();
		comboBox_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox_5.setModel(new DefaultComboBoxModel<String>(new String[] {"2025","2024","2023","2022","2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930"}));
		
		JLabel lblFaturaNo = new JLabel("Fatura No");
		lblFaturaNo.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		textFieldFaturaNo = new JTextField();
		textFieldFaturaNo.setEditable(false);
		textFieldFaturaNo.setBackground(UIManager.getColor("Button.background"));
		textFieldFaturaNo.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblTemsilEden = new JLabel("Teslim Eden");
		lblTemsilEden.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblTeslimAlan = new JLabel("Teslim Alan");
		lblTeslimAlan.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		btnKaydet = new JButton("Kaydet");
		
		textFieldTeslimEden = new JTextField();
		textFieldTeslimEden.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTeslimEden.setColumns(10);
		textFieldTeslimEden.setText(mmbr.getNickname());
		
		textFieldTeslimAlan = new JTextField();
		textFieldTeslimAlan.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTeslimAlan.setColumns(10);
		
		btnEkranGrnts = new JButton("SS");
		btnEkranGrnts.setVisible(false);
		btnEkranGrnts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Date zmn = new Date();
				DateFormat day = new SimpleDateFormat("dd");
				DateFormat month = new SimpleDateFormat("mm");
				DateFormat year = new SimpleDateFormat("yyyy");
				LocalTime now= LocalTime.now();
				String saat = now.getHour()+"_"+now.getMinute()+"_"+now.getSecond();
				try {
					SaveScreenShot(contentPane, "irsaliye"+day.format(zmn)+"_"+month.format(zmn)+"_"+year.format(zmn)+"_"+saat+".png");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(84)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(16)
									.addComponent(lblTemsilEden))
								.addComponent(textFieldTeslimEden, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textFieldTeslimAlan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(55))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblTeslimAlan)
									.addGap(73))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(277, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnKaydet)
							.addGap(218))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(230)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblFiilSevkTarihi)
										.addComponent(lblFaturaNo))
									.addGap(40))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblDzenlenmeTarihi)
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(textFieldFaturaNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
											.addGap(13)))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(comboBox_2, 0, 0, Short.MAX_VALUE)
										.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
					.addGap(24))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(206, Short.MAX_VALUE)
					.addComponent(lblSevkIrsaliyesi, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addGap(210))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(529, Short.MAX_VALUE)
					.addComponent(btnEkranGrnts)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnEkranGrnts)
					.addGap(4)
					.addComponent(lblSevkIrsaliyesi, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDzenlenmeTarihi))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFiilSevkTarihi)
						.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFaturaNo)
						.addComponent(textFieldFaturaNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTemsilEden)
						.addComponent(lblTeslimAlan))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldTeslimEden, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldTeslimAlan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addComponent(btnKaydet)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Mal\u0131n Cinsi", "Adet", "Birim Fiyat\u0131", "Tutar"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		btnKaydet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
					if(null != table.getCellEditor()){
						table.getCellEditor().stopCellEditing();
					}

					LocalTime now= LocalTime.now();
					String saat = now.getHour()+":"+now.getMinute()+":"+now.getSecond();
					irsaliye.setSaat(saat); 
					irsaliye.setAliciIsim(textFieldTeslimAlan.getText());
					irsaliye.setgonderenIsim(textFieldTeslimEden.getText());
					irsaliye.setTarih(comboBox.getSelectedItem()+"/"+comboBox_1.getSelectedItem()+"/"+comboBox_2.getSelectedItem());
					irsaliye.setSevkTarihi(comboBox_3.getSelectedItem()+"/"+comboBox_4.getSelectedItem()+"/"+comboBox_5.getSelectedItem());
					irsaliye.setOlusturan(String.valueOf(mmbr.getId()));
					
					if(searchIrsaliye(mmbr,textFieldFaturaNo.getText())!=-1){
						boolean hata = false;
						mmbr.getIrsaliyeler().get(searchIrsaliye(mmbr,textFieldFaturaNo.getText())).setgonderenIsim(irsaliye.getgonderenIsim());
						mmbr.getIrsaliyeler().get(searchIrsaliye(mmbr,textFieldFaturaNo.getText())).setAliciIsim(irsaliye.getAliciIsim());
						mmbr.getIrsaliyeler().get(searchIrsaliye(mmbr,textFieldFaturaNo.getText())).setMallar(irsaliye.getMallar());
						mmbr.getIrsaliyeler().get(searchIrsaliye(mmbr,textFieldFaturaNo.getText())).setOlusturan(irsaliye.getOlusturan());
						mmbr.getIrsaliyeler().get(searchIrsaliye(mmbr,textFieldFaturaNo.getText())).setSevkTarihi(irsaliye.getSevkTarihi());
						if(ControlUsers(irsaliye.getAliciIsim())){
							try {
								sql.IrsaliyeVerileriniGüncelle(mmbr.getIrsaliyeler().get(searchIrsaliye(mmbr,textFieldFaturaNo.getText())));
								mmbr.setIrsaliyeler(sql.MalOkuma(mmbr));

							} catch (SQLException e) {
								e.printStackTrace();
							}
											
							for(int i=0;i<mmbr.getIrsaliyeler().get(searchIrsaliye(mmbr, textFieldFaturaNo.getText())).getMallar().size();i++){
									if(table.getValueAt(i,0)!=null && table.getValueAt(i,1)!=null&&table.getValueAt(i,2)!=null){
										if(table.getValueAt(i, 0).equals("")||table.getValueAt(i, 1).equals("")||table.getValueAt(i, 2).equals("")){
											try {
												sql.MalSil(mmbr.getIrsaliyeler().get(searchIrsaliye(mmbr, textFieldFaturaNo.getText())).getMallar().get(i));
												continue;
											} catch (SQLException e) {
												e.printStackTrace();
											}
										}
										try{
											mmbr.getIrsaliyeler().get(searchIrsaliye(mmbr, textFieldFaturaNo.getText())).getMallar().get(i).setMalCinsi(table.getValueAt(i, 0).toString());
											mmbr.getIrsaliyeler().get(searchIrsaliye(mmbr, textFieldFaturaNo.getText())).getMallar().get(i).setAdet(Integer.parseInt(table.getValueAt(i, 1).toString()));
											mmbr.getIrsaliyeler().get(searchIrsaliye(mmbr, textFieldFaturaNo.getText())).getMallar().get(i).setBrmFiyat(Float.parseFloat(table.getValueAt(i, 2).toString()));
											mmbr.getIrsaliyeler().get(searchIrsaliye(mmbr, textFieldFaturaNo.getText())).getMallar().get(i).setTutar(Float.parseFloat(table.getValueAt(i, 2).toString())*Float.parseFloat(table.getValueAt(i, 1).toString()));
											
										}catch(Exception e){
											hata=true;
											JOptionPane.showMessageDialog(contentPane,"Mal bilgileri hatalý girdiniz","Hata",JOptionPane.WARNING_MESSAGE);
										}
										
										try {
											sql.MalGuncelle(mmbr.getIrsaliyeler().get(searchIrsaliye(mmbr, textFieldFaturaNo.getText())).getMallar().get(i));
										} catch (SQLException e) {
											e.printStackTrace();
										}
									}

							}
							if(hata){
								ArrayList<Mal> mallar = new ArrayList<>();
								for(int i=mmbr.getIrsaliyeler().get(searchIrsaliye(mmbr, textFieldFaturaNo.getText())).getMallar().size();i<15;i++){
									if(table.getValueAt(i,0)!=null && table.getValueAt(i,1)!=null&&table.getValueAt(i,2)!=null){
										Mal mal = new Mal();
										mal.setMalCinsi(table.getValueAt(i, 0).toString());
										mal.setAdet(Integer.parseInt(table.getValueAt(i, 1).toString())); 
										mal.setBrmFiyat(Float.valueOf(table.getValueAt(i, 2).toString()));
										mal.setTutar(mal.getBrmFiyat()*mal.getAdet());	
										mal.setIrsaliyeid(textFieldFaturaNo.getText());
										mallar.add(mal);
									}		
								}
								irsaliye.setMallar(mallar);
								try {
									mmbr.setIrsaliyeler(sql.ÝrsaliyeOkuma(mmbr.getIrsaliyeler(),mmbr));
									sql.malYazma(irsaliye.getMallar());
									mmbr.setIrsaliyeler(sql.MalOkuma(mmbr));
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							
							dispose();
						}
						else{
							JOptionPane.showMessageDialog(contentPane,"Alicinin kullanici adini kontrol ediniz","Hata",JOptionPane.WARNING_MESSAGE);
						}

					}
					else{
						if(ControlUsers(irsaliye.getAliciIsim())){
							if(!isTableEmpty()){
								mmbr.addFile(irsaliye);
								try {
									sql.ÝrsaliyeYazma(irsaliye);
									mmbr.setIrsaliyeler(sql.ÝrsaliyeOkuma(mmbr.getIrsaliyeler(),mmbr));
								} catch (SQLException e) {
									e.printStackTrace();
								}
								ArrayList<Mal> mallar = new ArrayList<>();

								try{
									for (int i = 0; i<15;i++){
										if(table.getValueAt(i,0)!=null && table.getValueAt(i,1)!=null&&table.getValueAt(i,2)!=null){
												Mal mal = new Mal();
												mal.setMalCinsi(table.getValueAt(i, 0).toString());
												mal.setAdet(Integer.parseInt(table.getValueAt(i, 1).toString())); 
												mal.setBrmFiyat(Float.valueOf(table.getValueAt(i, 2).toString()));
												mal.setTutar(mal.getBrmFiyat()*mal.getAdet());	
												mal.setIrsaliyeid(idSearch(mmbr, irsaliye));
												mallar.add(mal);
										}		
									}
									irsaliye.setMallar(mallar);
								}catch(Exception e){
									JOptionPane.showMessageDialog(contentPane,"Bilgileri hatalý girdiniz","Hata",JOptionPane.WARNING_MESSAGE);
								}
								
								try {
									sql.malYazma(irsaliye.getMallar());
								} catch (SQLException e) {
									e.printStackTrace();
								}
		
								
								dispose();
							}
							else{
								JOptionPane.showMessageDialog(contentPane,"Hiç bir mal bilgisi girmediniz","Hata",JOptionPane.WARNING_MESSAGE);

							}
							
						}
						else{
							JOptionPane.showMessageDialog(contentPane,"Alicinin kullanici adini kontrol ediniz","Hata",JOptionPane.WARNING_MESSAGE);
						}	
					}
			}
		});
	}
}
