import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.ImageIcon;
import java.awt.Window.Type;
import javax.swing.LayoutStyle.ComponentPlacement;



public class MemberGUI extends JFrame {
	final MySQL sql = new MySQL();
	private JPanel contentPane;
	loginGUI lgngui = new loginGUI();
	
	static loginGUI logGui = new loginGUI();
	static Member mmbr = new Member();
	private JTextField textFieldIsým;
	private JTextField textFieldSoyism;
	private JTextField textFieldTlfnNum;
	private JTextField textFieldIl;
	private JTextField textFieldIlce;
	private JTextField textFieldMail;
	private JTable table;
	private JTable tableAlinan;
	private JTextField textFieldAliciÝsmiOlust;
	private JTextField textFieldGonderenIsmi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberGUI frame = new MemberGUI(mmbr);
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

	public void DosyaEkle(Belge b){
		
	}
	public void evraklariyerlesti() throws SQLException {
		for(int i=0;i<lgngui.getList().size();i++){
			lgngui.getList().get(i).setIrsaliyeler(sql.ÝrsaliyeOkuma(lgngui.getList().get(i).getIrsaliyeler(),lgngui.getList().get(i)));
			lgngui.getList().get(i).setIrsaliyeler(sql.MalOkuma(lgngui.getList().get(i)));
			lgngui.getList().get(i).setTutanaklar(sql.TutanakOkuma(lgngui.getList().get(i).getTutanaklar(), lgngui.getList().get(i)));
			lgngui.getList().get(i).setFaturalar(sql.FaturaOku(lgngui.getList().get(i).getFaturalar(), lgngui.getList().get(i)));
			lgngui.getList().get(i).setFaturalar(sql.MalHizmetOkuma(lgngui.getList().get(i)));
			lgngui.getList().get(i).setMailler(sql.MailOkuma(lgngui.getList().get(i), lgngui.getList().get(i).getMailler()));
		}
	}
	
	public void AlinanEvraklariEkle(Member mmbr) throws SQLException{
		evraklariyerlesti();
		mmbr.alinanirsaliyeler.clear();
		mmbr.alinanFaturalar.clear();
		mmbr.alinantutanaklar.clear();
		mmbr.alinanMailler.clear();
		for(int i=0;i<lgngui.getList().size();i++){
			for(int j=0;j<lgngui.getList().get(i).getIrsaliyeler().size();j++){
				if(lgngui.getList().get(i).getIrsaliyeler().get(j).getAliciIsim().equals(mmbr.getNickname())){
					mmbr.addGelenFile(lgngui.getList().get(i).getIrsaliyeler().get(j));
				}
				
			}
			for(int j=0;j<lgngui.getList().get(i).getFaturalar().size();j++){
				if(lgngui.getList().get(i).getFaturalar().get(j).getAliciIsim().equals(mmbr.getNickname())){
					mmbr.addGelenFatura(lgngui.getList().get(i).getFaturalar().get(j));
				}
				
			}
			for(int j=0;j<lgngui.getList().get(i).getTutanaklar().size();j++){
				if(lgngui.getList().get(i).getTutanaklar().get(j).getAliciIsim().equals(mmbr.getNickname())||lgngui.getList().get(i).getTutanaklar().get(j).getAliciIsim2().equals(mmbr.getNickname())||lgngui.getList().get(i).getTutanaklar().get(j).getAliciIsim3().equals(mmbr.getNickname())){
					mmbr.addGelenTutanak(lgngui.getList().get(i).getTutanaklar().get(j));
				}
			}
			for(int j=0;j<lgngui.getList().get(i).getMailler().size();j++){
				//System.out.println("dýs");
				if(searchUserforMail(lgngui.getList().get(i).getMailler().get(j).getAliciIsim(),mmbr)){			
					//System.out.println("ic");
					mmbr.addGelenMail(lgngui.getList().get(i).getMailler().get(j));
				}
			}
		}
	}
	public int SearchUser(String nick) {
		
		for(int i=0;i<logGui.getList().size();i++){
			if(logGui.getList().get(i).getNickname().equals(nick)){
				return i;
			}
		}
		
		return 0;
	}
	
	public int SearchUserwithNick(String nickname) {
		
		for(int i=0;i<logGui.getList().size();i++){
			if(logGui.getList().get(i).getNickname().equals(nickname)){
				return i;
			}
		}
		
		return 0;
	}
	
	public boolean searchUserforMail(String alici,Member mmbr){
		String[] users = alici.split(","); 
		for(int i=0;i<users.length;i++){
			if(mmbr.getNickname().equals(String.valueOf(users[i]))){
				System.out.println("True");
				return true;
			}
		}
		
		return false;
	}
	public boolean searchUserforMail(String name,String searcName){
		String[] users = name.split(","); 
		for(int i=0;i<users.length;i++){
			if(searcName.equals(String.valueOf(users[i]))){
				return true;
			}
		}
		
		return false;
	}
	public MemberGUI(final Member mmbr){
		setResizable(false);
		
		try {
			mmbr.setIrsaliyeler(sql.ÝrsaliyeOkuma(mmbr.getIrsaliyeler(),mmbr));
			mmbr.setTutanaklar(sql.TutanakOkuma(mmbr.getTutanaklar(), mmbr));
			mmbr.setFaturalar(sql.FaturaOku(mmbr.getFaturalar(), mmbr));
			mmbr.setMailler(sql.MailOkuma(mmbr, mmbr.getMailler()));
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setTitle("Kullan\u0131c\u0131 Ekran\u0131");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 502);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldin " + mmbr.getName());
		lblNewLabel.setBounds(17, 18, 337, 42);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
		
		JButton btnExit = new JButton("\u00C7\u0131k\u0131\u015F");
		btnExit.setBounds(574, 18, 140, 42);
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				logGui.setVisible(true);
				dispose();
			}
		});
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(17, 78, 709, 356);
		
		final DefaultTableModel model = new DefaultTableModel()  {
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
		
		Object[] kolunlar = {"Gönderici","Alýcý","Türü","Tarih","Id"};
		final Object[] satirlar = new Object[5];
		model.setColumnIdentifiers(kolunlar);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setForeground(new Color(0, 0, 0));
		layeredPane.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Kullan\u0131c\u0131 Bilgileri", null, layeredPane, null);
		tabbedPane.setEnabledAt(0, true);
		tabbedPane.setBackgroundAt(0, Color.WHITE);
		
		JLabel lblAd = new JLabel("Ad\u0131");
		lblAd.setBounds(22, 28, 34, 22);
		lblAd.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblAd.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblSoyad = new JLabel("Soyad\u0131");
		lblSoyad.setBounds(22, 63, 54, 22);
		lblSoyad.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblSoyad.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblNewLabel_1 = new JLabel("Dogum Tarihi");
		lblNewLabel_1.setBounds(22, 145, 107, 34);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblTelefonNumaras = new JLabel("Telefon Numaras\u0131");
		lblTelefonNumaras.setBounds(22, 98, 143, 34);
		lblTelefonNumaras.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblTelefonNumaras.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lbIl = new JLabel("\u0130l");
		lbIl.setBounds(22, 192, 20, 28);
		lbIl.setFont(new Font("Arial Black", Font.BOLD, 13));
		lbIl.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblIlce = new JLabel("\u0130lce");
		lblIlce.setBounds(22, 233, 46, 28);
		lblIlce.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblIlce.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblMail = new JLabel("Mail ");
		lblMail.setBounds(22, 274, 46, 39);
		lblMail.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblMail.setHorizontalAlignment(SwingConstants.LEFT);
		
		textFieldIsým = new JTextField();
		textFieldIsým.setBounds(180, 25, 236, 28);
		textFieldIsým.setEditable(false);
		textFieldIsým.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldIsým.setText(mmbr.getName());
		textFieldIsým.setColumns(10);
		
		textFieldSoyism = new JTextField();
		textFieldSoyism.setBounds(180, 64, 236, 28);
		textFieldSoyism.setEditable(false);
		textFieldSoyism.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldSoyism.setColumns(10);
		textFieldSoyism.setText(mmbr.getSurname());
		
		textFieldTlfnNum = new JTextField();
		textFieldTlfnNum.setBounds(180, 105, 236, 28);
		textFieldTlfnNum.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldTlfnNum.setColumns(10);
		textFieldTlfnNum.setText(mmbr.getTel_num());
		
		textFieldIl = new JTextField();
		textFieldIl.setBounds(180, 196, 236, 28);
		textFieldIl.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldIl.setColumns(10);
		textFieldIl.setText(mmbr.getIl());
		
		textFieldIlce = new JTextField();
		textFieldIlce.setBounds(180, 237, 236, 28);
		textFieldIlce.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldIlce.setColumns(10);
		textFieldIlce.setText(mmbr.getIlce());
		
		textFieldMail = new JTextField();
		textFieldMail.setBounds(180, 283, 236, 28);
		textFieldMail.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldMail.setColumns(10);
		textFieldMail.setText(mmbr.getMail());
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(180, 152, 54, 22);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox.setSelectedItem(mmbr.getDgmGun());
		
		final JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(257, 152, 77, 22);
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Ocak", "\u015Eubat", "Mart", "Nisan", "May\u0131s", "Haziran", "Temmuz", "A\u011Fustos", "Eyl\u00FCl", "Ekim", "Kas\u0131m", "Aral\u0131k"}));
		comboBox_1.setSelectedItem(mmbr.getDgmAy());
		
		final JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(362, 152, 54, 22);
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930"}));
		comboBox_2.setSelectedItem(mmbr.getDgmYil());
		
		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.setBounds(496, 278, 135, 33);
		btnKaydet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldIsým.getText().length()!=0&&textFieldSoyism.getText().length()!=0&&textFieldTlfnNum.getText().length()!=0
						&&textFieldIl.getText().length()!=0&&textFieldIlce.getText().length()!=0&&textFieldMail.getText().length()!=0){
					
					mmbr.setName(textFieldIsým.getText());
					mmbr.setSurname(textFieldSoyism.getText());
					mmbr.setTel_num(textFieldTlfnNum.getText());
					mmbr.setDgmGun(comboBox.getItemAt(comboBox.getSelectedIndex()));
					mmbr.setDgmAy(comboBox_1.getItemAt(comboBox_1.getSelectedIndex()));
					mmbr.setDgmYil(comboBox_2.getItemAt(comboBox_2.getSelectedIndex()));
					mmbr.setIl(textFieldIl.getText());
					mmbr.setIlce(textFieldIlce.getText());
					mmbr.setMail(textFieldMail.getText());
					try {
						sql.KullaniciVerileriniGüncelle(mmbr);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(contentPane,"Kullanýcý Bilgilerinde Eksik Var","Hata",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
		JLabel lblId = new JLabel("\u0130d : ");
		lblId.setBounds(575, 13, 56, 16);
		lblId.setText("Id:"+mmbr.getId());
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 704, 336);
		panel.setBackground(Color.LIGHT_GRAY);
		layeredPane.setLayout(null);
		layeredPane.add(lblNewLabel_1);
		layeredPane.add(comboBox);
		layeredPane.add(comboBox_1);
		layeredPane.add(comboBox_2);
		layeredPane.add(lbIl);
		layeredPane.add(textFieldIl);
		layeredPane.add(lblIlce);
		layeredPane.add(textFieldIlce);
		layeredPane.add(lblMail);
		layeredPane.add(textFieldMail);
		layeredPane.add(btnKaydet);
		layeredPane.add(lblAd);
		layeredPane.add(textFieldIsým);
		layeredPane.add(lblSoyad);
		layeredPane.add(textFieldSoyism);
		layeredPane.add(lblTelefonNumaras);
		layeredPane.add(textFieldTlfnNum);
		layeredPane.add(lblId);
		layeredPane.add(panel);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("Dosya Olu\u015Ftur", null, layeredPane_1, null);
		
		JButton btnEfatura = new JButton("E-Fatura Olu\u015Ftur");
		btnEfatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FaturaPaper faturapaper = new FaturaPaper(mmbr);
				faturapaper.setVisible(true);
			}
		});
		btnEfatura.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnEfatura.setBounds(30, 29, 266, 126);
		layeredPane_1.add(btnEfatura);
		
		JButton btnErsaliye = new JButton("E-Irsaliye  Olu\u015Ftur");
		btnErsaliye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IrsaliyePaper irsaliyepaper = new IrsaliyePaper(mmbr);
				irsaliyepaper.setVisible(true);

			}
		});
		btnErsaliye.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnErsaliye.setBounds(338, 29, 266, 126);
		layeredPane_1.add(btnErsaliye);
		
		JButton btnMail = new JButton("Mail Olu\u015Ftur");
		btnMail.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MailPaper mPaper = new MailPaper(mmbr);
				mPaper.setVisible(true);
			}
		});
		btnMail.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnMail.setBounds(30, 176, 266, 126);
		layeredPane_1.add(btnMail);
		
		JButton btnTutanak = new JButton("E-Tutanak Olu\u015Ftur");
		btnTutanak.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TutanakPaper ttnkpaper = new TutanakPaper(mmbr);
				ttnkpaper.setVisible(true);	
			}
		});
		btnTutanak.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnTutanak.setBounds(338, 176, 266, 126);
		layeredPane_1.add(btnTutanak);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(0, 0, 704, 326);
		layeredPane_1.add(panel_1);
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		tabbedPane.addTab("Olu\u015Fturulan Dosyalar\u0131 G\u00F6r\u00FCnt\u00FCle", null, layeredPane_2, null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(155, 13, 525, 262);
		layeredPane_2.add(scrollPane);
		
		JButton btnGrntle = new JButton("G\u00F6r\u00FCnt\u00FCle");
		btnGrntle.setBounds(310, 288, 97, 25);
		layeredPane_2.add(btnGrntle);
		
		final JComboBox<String> comboBoxBelgeTipi = new JComboBox<String>();
		comboBoxBelgeTipi.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBoxBelgeTipi.setModel(new DefaultComboBoxModel<String>(new String[] {"Hepsi", "\u0130rsaliye", "Fatura", "Tutanak", "Mail"}));
		comboBoxBelgeTipi.setBounds(12, 74, 131, 22);
		layeredPane_2.add(comboBoxBelgeTipi);
		
		btnGrntle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				model.getDataVector().removeAllElements();
				int i,j;

				for(i = 0;i<mmbr.getIrsaliyeler().size();i++){
					if((textFieldAliciÝsmiOlust.getText().equals("")||textFieldAliciÝsmiOlust.getText().equals(mmbr.getIrsaliyeler().get(i).getAliciIsim()))&&(comboBoxBelgeTipi.getSelectedItem().toString().equals("Hepsi")||comboBoxBelgeTipi.getSelectedItem().equals("\u0130rsaliye"))){
						satirlar[0] = mmbr.getIrsaliyeler().get(i).getgonderenIsim();
						satirlar[1] = mmbr.getIrsaliyeler().get(i).getAliciIsim();
						satirlar[2] = mmbr.getIrsaliyeler().get(i).getTur();
						satirlar[3] = mmbr.getIrsaliyeler().get(i).getTarih();
						satirlar[4] = mmbr.getIrsaliyeler().get(i).getId();
						model.addRow(satirlar);
					}

				}
				for(j=0;j<mmbr.getTutanaklar().size();j++){
					if((textFieldAliciÝsmiOlust.getText().equals("")||textFieldAliciÝsmiOlust.getText().equals(mmbr.getTutanaklar().get(i).getAliciIsim()))&&(comboBoxBelgeTipi.getSelectedItem().toString().equals("Hepsi")||comboBoxBelgeTipi.getSelectedItem().equals("Tutanak"))){
						satirlar[0] = mmbr.getTutanaklar().get(j).getgonderenIsim();
						satirlar[1] = mmbr.getTutanaklar().get(j).getAliciIsim();
						satirlar[2] = mmbr.getTutanaklar().get(j).getTur();
						satirlar[3] = mmbr.getTutanaklar().get(j).getTarih();
						satirlar[4] = mmbr.getTutanaklar().get(j).getId();
						model.addRow(satirlar);	
					}


				}
				for(i=0;i<mmbr.getFaturalar().size();i++){
					if((textFieldAliciÝsmiOlust.getText().equals("")||textFieldAliciÝsmiOlust.getText().equals(mmbr.getFaturalar().get(i).getAliciIsim()))&&(comboBoxBelgeTipi.getSelectedItem().toString().equals("Hepsi")||comboBoxBelgeTipi.getSelectedItem().equals("Fatura"))){
						satirlar[0] = mmbr.getFaturalar().get(i).getgonderenIsim();
						satirlar[1] = mmbr.getFaturalar().get(i).getAliciIsim();
						satirlar[2] = mmbr.getFaturalar().get(i).getTur();
						satirlar[3] = mmbr.getFaturalar().get(i).getTarih();
						satirlar[4] = mmbr.getFaturalar().get(i).getId();
						model.addRow(satirlar);
					}

				}
				System.out.println(mmbr.getMailler().size());
				for(i=0;i<mmbr.getMailler().size();i++){
					if((textFieldAliciÝsmiOlust.getText().equals("")||searchUserforMail(mmbr.getMailler().get(i).getAliciIsim(), textFieldAliciÝsmiOlust.getText()))&&(comboBoxBelgeTipi.getSelectedItem().toString().equals("Hepsi")||comboBoxBelgeTipi.getSelectedItem().equals("Mail"))){
						satirlar[0] = mmbr.getMailler().get(i).getgonderenIsim();
						satirlar[1] = mmbr.getMailler().get(i).getAliciIsim();
						satirlar[2] = mmbr.getMailler().get(i).getTur();
						satirlar[3] = mmbr.getMailler().get(i).getTarih();
						satirlar[4] = mmbr.getMailler().get(i).getId();
						model.addRow(satirlar);
					}
				}
				
				if(model.getRowCount()==0){
					table.setVisible(false);
				}
				else{
					table.setModel(model);
					table.setVisible(true);
				}
				
				try {
					mmbr.setIrsaliyeler(sql.ÝrsaliyeOkuma(mmbr.getIrsaliyeler(),mmbr));
					mmbr.setIrsaliyeler(sql.MalOkuma(mmbr));
					mmbr.setTutanaklar(sql.TutanakOkuma(mmbr.getTutanaklar(), mmbr));
					mmbr.setFaturalar(sql.FaturaOku(mmbr.getFaturalar(), mmbr));
					mmbr.setFaturalar(sql.MalHizmetOkuma(mmbr));
					mmbr.setMailler(sql.MailOkuma(mmbr, mmbr.getMailler()));

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		final JRadioButton rdbtnDzenlemeOlust = new JRadioButton("Evraklar\u0131 D\u00FCzenle");
		rdbtnDzenlemeOlust.setBackground(Color.LIGHT_GRAY);
		rdbtnDzenlemeOlust.setBounds(12, 181, 135, 25);
		layeredPane_2.add(rdbtnDzenlemeOlust);
		
		table = new JTable();
		table.setShowVerticalLines(false);
		table.setModel(model);
			table.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent arg0) {
					// TODO Auto-generated method stub
					for(int i=0;i<mmbr.getIrsaliyeler().size();i++){
						if(table.getModel().getValueAt(table.getSelectedRow(),4).toString().equals(mmbr.getIrsaliyeler().get(i).getId()) && table.getModel().getValueAt(table.getSelectedRow(),2).toString().equals("Ýrsaliye")){
							IrsaliyePaper irsaliyepaper = new IrsaliyePaper(mmbr);
							if(rdbtnDzenlemeOlust.isSelected()){
								irsaliyepaper.Goruntuleme(mmbr, mmbr.getIrsaliyeler().get(i),true);
							}
							else{
								irsaliyepaper.Goruntuleme(mmbr, mmbr.getIrsaliyeler().get(i),false);
							}
						}
					}
					for(int i=0;i<mmbr.getTutanaklar().size();i++){
						if(table.getModel().getValueAt(table.getSelectedRow(),4).toString().equals(mmbr.getTutanaklar().get(i).getId()) && table.getModel().getValueAt(table.getSelectedRow(),2).toString().equals("Tutanak")){
							TutanakPaper tutpaper = new TutanakPaper(mmbr);
							if(rdbtnDzenlemeOlust.isSelected()){
								tutpaper.tutanakGoruntuleme(mmbr, mmbr.getTutanaklar().get(i),true);

							}else{
								tutpaper.tutanakGoruntuleme(mmbr, mmbr.getTutanaklar().get(i),false);

							}

						}
					}
					for(int i=0;i<mmbr.getFaturalar().size();i++){
						if(table.getModel().getValueAt(table.getSelectedRow(),4).toString().equals(mmbr.getFaturalar().get(i).getId()) && table.getModel().getValueAt(table.getSelectedRow(),2).toString().equals("Fatura")){
							FaturaPaper faturaPaper = new FaturaPaper(mmbr);
							if(rdbtnDzenlemeOlust.isSelected()){
								faturaPaper.FaturaGoruntule(mmbr, mmbr.getFaturalar().get(i));

							}else{
								faturaPaper.AlinanFaturanGoruntule(mmbr, logGui.getList().get(SearchUserwithNick(mmbr.getFaturalar().get(i).getAliciIsim())),mmbr.getFaturalar().get(i));

							}
						}
					}
					for(int i=0;i<mmbr.getMailler().size();i++){
						if(table.getModel().getValueAt(table.getSelectedRow(),4).toString().equals(mmbr.getMailler().get(i).getId()) && table.getModel().getValueAt(table.getSelectedRow(),2).toString().equals("Mail")){
							MailPaper mailPaper = new MailPaper(mmbr);
							if(rdbtnDzenlemeOlust.isSelected()){
								mailPaper.MailGoruntule(mmbr, mmbr.getMailler().get(i), true);
							}else{
								mailPaper.MailGoruntule(mmbr, mmbr.getMailler().get(i), false);
							}
						}
					}
					
				}
			});
			
			
			scrollPane.setViewportView(table);
			

			
			JLabel lblAlcnnIsmiOlust = new JLabel("Al\u0131c\u0131n\u0131n \u0130smi");
			lblAlcnnIsmiOlust.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblAlcnnIsmiOlust.setHorizontalAlignment(SwingConstants.LEFT);
			lblAlcnnIsmiOlust.setBounds(12, 109, 131, 16);
			layeredPane_2.add(lblAlcnnIsmiOlust);
			
			textFieldAliciÝsmiOlust = new JTextField();
			textFieldAliciÝsmiOlust.setBounds(12, 138, 131, 22);
			layeredPane_2.add(textFieldAliciÝsmiOlust);
			textFieldAliciÝsmiOlust.setColumns(10);
			
			
			
			JLabel lblTabloyuDüzenleOlust = new JLabel("Tabloyu D\u00FCzenle");
			lblTabloyuDüzenleOlust.setHorizontalAlignment(SwingConstants.CENTER);
			lblTabloyuDüzenleOlust.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblTabloyuDüzenleOlust.setBounds(12, 16, 142, 16);
			layeredPane_2.add(lblTabloyuDüzenleOlust);
			
			JLabel lblEvrakTrOlust = new JLabel("Evrak T\u00FCr\u00FC");
			lblEvrakTrOlust.setHorizontalAlignment(SwingConstants.LEFT);
			lblEvrakTrOlust.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblEvrakTrOlust.setBounds(12, 45, 131, 16);
			layeredPane_2.add(lblEvrakTrOlust);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(Color.LIGHT_GRAY);
			panel_2.setBounds(0, 0, 704, 326);
			layeredPane_2.add(panel_2);
		
		
		JLayeredPane layeredPaneAlinanEvraklar = new JLayeredPane();
		tabbedPane.addTab("Size G\u00F6nderilen Evraklar", null, layeredPaneAlinanEvraklar, null);
		
		JScrollPane scrollPaneAlinanEvraklar = new JScrollPane();
		scrollPaneAlinanEvraklar.setBounds(155, 13, 525, 262);
		layeredPaneAlinanEvraklar.add(scrollPaneAlinanEvraklar);
		
		tableAlinan = new JTable();
		tableAlinan.setShowVerticalLines(false);
		
		final JComboBox<String> comboBoxBelgeTipiGond = new JComboBox<String>();
		comboBoxBelgeTipiGond.setModel(new DefaultComboBoxModel<String>(new String[] {"Hepsi", "\u0130rsaliye", "Fatura", "Tutanak", "Mail"}));
		comboBoxBelgeTipiGond.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBoxBelgeTipiGond.setBounds(12, 75, 131, 22);
		layeredPaneAlinanEvraklar.add(comboBoxBelgeTipiGond);
		
		JLabel lblGonderenisim = new JLabel("G\u00F6nderenin \u0130smi");
		lblGonderenisim.setHorizontalAlignment(SwingConstants.LEFT);
		lblGonderenisim.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGonderenisim.setBounds(12, 110, 131, 16);
		layeredPaneAlinanEvraklar.add(lblGonderenisim);
		
		textFieldGonderenIsmi = new JTextField();
		textFieldGonderenIsmi.setColumns(10);
		textFieldGonderenIsmi.setBounds(12, 139, 131, 22);
		layeredPaneAlinanEvraklar.add(textFieldGonderenIsmi);
		
		JLabel lblTabloyuDüzenleGond = new JLabel("Tabloyu D\u00FCzenle");
		lblTabloyuDüzenleGond.setHorizontalAlignment(SwingConstants.CENTER);
		lblTabloyuDüzenleGond.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTabloyuDüzenleGond.setBounds(12, 13, 142, 16);
		layeredPaneAlinanEvraklar.add(lblTabloyuDüzenleGond);
		
		JLabel lblEvrakTrGond = new JLabel("Evrak T\u00FCr\u00FC");
		lblEvrakTrGond.setHorizontalAlignment(SwingConstants.LEFT);
		lblEvrakTrGond.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEvrakTrGond.setBounds(12, 46, 131, 16);
		layeredPaneAlinanEvraklar.add(lblEvrakTrGond);
		
		final DefaultTableModel model2 = new DefaultTableModel() {
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
		
		Object[] kolunlar2 = {"Gönderici","Alýcý","Türü","Tarih","Id"};
		final Object[] satirlar2 = new Object[5];
		model2.setColumnIdentifiers(kolunlar2);
		
		JButton buttonAlinanGoruntule = new JButton("G\u00F6r\u00FCnt\u00FCle");
		buttonAlinanGoruntule.setBounds(310, 288, 97, 25);
		layeredPaneAlinanEvraklar.add(buttonAlinanGoruntule);
		
		buttonAlinanGoruntule.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(null!=tableAlinan.getCellEditor()){
					tableAlinan.getCellEditor().stopCellEditing();
				}
				model2.getDataVector().removeAllElements();
				while(model2.getRowCount() > 0)
				{
				    model2.removeRow(0);
				}
				try {
					AlinanEvraklariEkle(mmbr);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//System.out.println("size:"+mmbr.getAlinanirsaliyeler().size());
				for(int i = 0;i<mmbr.getAlinanirsaliyeler().size();i++){
					if((textFieldGonderenIsmi.getText().equals("")||textFieldGonderenIsmi.getText().equals(mmbr.getAlinanirsaliyeler().get(i).getgonderenIsim()))&&(comboBoxBelgeTipiGond.getSelectedItem().toString().equals("Hepsi")||comboBoxBelgeTipiGond.getSelectedItem().equals("\u0130rsaliye"))){
						satirlar2[0] = mmbr.getAlinanirsaliyeler().get(i).getgonderenIsim();
						satirlar2[1] = mmbr.getAlinanirsaliyeler().get(i).getAliciIsim();
						satirlar2[2] = mmbr.getAlinanirsaliyeler().get(i).getTur();
						satirlar2[3] = mmbr.getAlinanirsaliyeler().get(i).getTarih();
						satirlar2[4] = mmbr.getAlinanirsaliyeler().get(i).getId();
						model2.addRow(satirlar2);
					}
				}
				for(int i = 0;i<mmbr.getAlinantutanaklar().size();i++){
					if((textFieldGonderenIsmi.getText().equals("")||textFieldGonderenIsmi.getText().equals(mmbr.getAlinantutanaklar().get(i).getgonderenIsim()))&&(comboBoxBelgeTipiGond.getSelectedItem().toString().equals("Hepsi")||comboBoxBelgeTipiGond.getSelectedItem().equals("Tutanak"))){
						satirlar2[0] = mmbr.getAlinantutanaklar().get(i).getgonderenIsim();
						satirlar2[1] = mmbr.getAlinantutanaklar().get(i).getAliciIsim();
						satirlar2[2] = mmbr.getAlinantutanaklar().get(i).getTur();
						satirlar2[3] = mmbr.getAlinantutanaklar().get(i).getTarih();
						satirlar2[4] = mmbr.getAlinantutanaklar().get(i).getId();
						model2.addRow(satirlar2);
					}
				}
				for(int i = 0;i<mmbr.getAlinanFaturalar().size();i++){
					if((textFieldGonderenIsmi.getText().equals("")||textFieldGonderenIsmi.getText().equals(mmbr.getAlinanFaturalar().get(i).getgonderenIsim()))&&(comboBoxBelgeTipiGond.getSelectedItem().toString().equals("Hepsi")||comboBoxBelgeTipiGond.getSelectedItem().equals("Fatura"))){
						satirlar2[0] = mmbr.getAlinanFaturalar().get(i).getgonderenIsim();
						satirlar2[1] = mmbr.getAlinanFaturalar().get(i).getAliciIsim();
						satirlar2[2] = mmbr.getAlinanFaturalar().get(i).getTur();
						satirlar2[3] = mmbr.getAlinanFaturalar().get(i).getTarih();
						satirlar2[4] = mmbr.getAlinanFaturalar().get(i).getId();
						model2.addRow(satirlar2);	
					}	
				}
				//mmbr.getAlinanMailler().get(i).getgonderenIsim().equals(textFieldGonderenIsmi.getText())
				System.out.println(mmbr.getAlinanMailler().size());
				for(int i=0;i<mmbr.getAlinanMailler().size();i++){
					if((textFieldGonderenIsmi.getText().equals("")|| searchUserforMail(mmbr.getAlinanMailler().get(i).getgonderenIsim(), textFieldGonderenIsmi.getText()))&&(comboBoxBelgeTipiGond.getSelectedItem().toString().equals("Hepsi")||comboBoxBelgeTipiGond.getSelectedItem().equals("Mail"))){
						satirlar2[0] = mmbr.getAlinanMailler().get(i).getgonderenIsim();
						satirlar2[1] = mmbr.getAlinanMailler().get(i).getAliciIsim();
						satirlar2[2] = mmbr.getAlinanMailler().get(i).getTur();
						satirlar2[3] = mmbr.getAlinanMailler().get(i).getTarih();
						satirlar2[4] = mmbr.getAlinanMailler().get(i).getId();
						model2.addRow(satirlar2);
					}
				}
				if(model2.getRowCount()!=0){
					tableAlinan.setModel(model2);
					tableAlinan.setVisible(true);
				}
				else{
					tableAlinan.setModel(model2);
					tableAlinan.setVisible(false);
				}
			}
		});
		tableAlinan.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent arg0) {
				for(int i=0;i<mmbr.getAlinanirsaliyeler().size();i++){
					if(tableAlinan.getModel().getValueAt(tableAlinan.getSelectedRow(),4).toString().equals(mmbr.getAlinanirsaliyeler().get(i).getId()) && tableAlinan.getModel().getValueAt(tableAlinan.getSelectedRow(),2).toString().equals("Ýrsaliye")){
						IrsaliyePaper irsaliyepaper = new IrsaliyePaper(mmbr);
						irsaliyepaper.Goruntuleme(mmbr, mmbr.getAlinanirsaliyeler().get(i),false);
					}
				}
				for(int i=0;i<mmbr.getAlinantutanaklar().size();i++){
					if(tableAlinan.getModel().getValueAt(tableAlinan.getSelectedRow(),4).toString().equals(mmbr.getAlinantutanaklar().get(i).getId()) && tableAlinan.getModel().getValueAt(tableAlinan.getSelectedRow(),2).toString().equals("Tutanak")){
						TutanakPaper tutpaper = new TutanakPaper(mmbr);
						tutpaper.tutanakGoruntuleme(mmbr, mmbr.getAlinantutanaklar().get(i),false);
					}
				}
				for(int i=0;i<mmbr.getAlinanFaturalar().size();i++){
					if(tableAlinan.getModel().getValueAt(tableAlinan.getSelectedRow(),4).toString().equals(mmbr.getAlinanFaturalar().get(i).getId()) && tableAlinan.getModel().getValueAt(tableAlinan.getSelectedRow(),2).toString().equals("Fatura")){
						FaturaPaper faturaPaper = new FaturaPaper(mmbr);
						faturaPaper.AlinanFaturanGoruntule(lgngui.getList().get(SearchUser(tableAlinan.getValueAt(tableAlinan.getSelectedColumn(), 4).toString())), mmbr,mmbr.getAlinanFaturalar().get(i));
						
					}
				}
				for(int i=0;i<mmbr.getAlinanMailler().size();i++){
					if(tableAlinan.getModel().getValueAt(tableAlinan.getSelectedRow(),4).toString().equals(mmbr.getAlinanMailler().get(i).getId()) && tableAlinan.getModel().getValueAt(tableAlinan.getSelectedRow(),2).toString().equals("Mail")){
						MailPaper mailPaper = new MailPaper(mmbr);
						mailPaper.MailGoruntule(mmbr, mmbr.getAlinanMailler().get(i), false);
					}

				}
				
			}
		});
		
		scrollPaneAlinanEvraklar.setViewportView(tableAlinan);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(0, 0, 704, 326);
		layeredPaneAlinanEvraklar.add(panel_3);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(btnExit);
		contentPane.add(tabbedPane);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("");
		lblNewJgoodiesLabel.setIcon(new ImageIcon("C:\\Users\\dursun\\Desktop\\Ders\\JAVA\\Java_Project\\android-lollipop-material-design-wallpaper-preview.jpg"));
		lblNewJgoodiesLabel.setBounds(0, 0, 743, 467);
		contentPane.add(lblNewJgoodiesLabel);
		
		
		
	}
}
