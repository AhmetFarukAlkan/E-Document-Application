import java.awt.Component;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

import javax.swing.UIManager;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JButton;



public class FaturaPaper extends JFrame {

	private JPanel contentPane;
	private JTable tableDuzenleyen;
	private JTable tableSayin;
	private JTable tableMalzemeHizmet;
	private JLabel lblEfatura;
	private JTable tableBilgiler;
	private JButton btnKaydet;
	
	static Member mmbr = new Member();
	E_Fatura fatura = new E_Fatura();
	MySQL sql = new MySQL();
	loginGUI loggui = new loginGUI();
	private JButton btnEkranGrnts;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FaturaPaper frame = new FaturaPaper(mmbr);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
//	public void AlinanFaturanGoruntule(Member mmbrGonderen,Member mmbrAlan,E_Fatura f) {
	
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
	
	public void AlinanFaturanGoruntule(Member mmbrGonderen,Member mmbrAlan,E_Fatura f) {
		FaturaPaper faturaPaper = new FaturaPaper(mmbrGonderen);
		faturaPaper.tableDuzenleyen.setValueAt(f.getgonderenIsim(), 0, 1);
		faturaPaper.tableDuzenleyen.setValueAt(mmbrGonderen.getIl()+"/"+mmbrGonderen.getIlce(), 1, 1);
		faturaPaper.tableDuzenleyen.setValueAt(mmbrGonderen.getTel_num(), 2, 1);
		faturaPaper.tableDuzenleyen.setValueAt(mmbrGonderen.getMail(), 3, 1);
		faturaPaper.tableBilgiler.setValueAt(f.getId(), 0, 1);
		faturaPaper.tableBilgiler.setValueAt(f.getTarih(), 1, 1);
		faturaPaper.tableSayin.setValueAt(f.getAliciIsim(), 0, 1);
		faturaPaper.tableSayin.setValueAt(mmbrAlan.getTel_num(), 1, 1);
		faturaPaper.tableSayin.setValueAt(mmbrAlan.getMail(), 2, 1);
		faturaPaper.tableSayin.setEnabled(false);
		faturaPaper.tableDuzenleyen.setEnabled(false);
		faturaPaper.tableBilgiler.setEnabled(false);
		
		for(int i=0;i<f.getMalzemeHizmetler().size();i++){
			faturaPaper.tableMalzemeHizmet.setValueAt(f.getMalzemeHizmetler().get(i).getId(), i, 0);
			faturaPaper.tableMalzemeHizmet.setValueAt(f.getMalzemeHizmetler().get(i).getAciklama(), i, 1);
			faturaPaper.tableMalzemeHizmet.setValueAt(f.getMalzemeHizmetler().get(i).getMiktar(), i, 2);
			faturaPaper.tableMalzemeHizmet.setValueAt(f.getMalzemeHizmetler().get(i).getBrmfiyat(), i, 3);
			faturaPaper.tableMalzemeHizmet.setValueAt(f.getMalzemeHizmetler().get(i).getKdvOraný(), i, 4);
			faturaPaper.tableMalzemeHizmet.setValueAt(f.getMalzemeHizmetler().get(i).getDigerVergiler(), i, 5);
			faturaPaper.tableMalzemeHizmet.setValueAt(f.getMalzemeHizmetler().get(i).getTutar(), i, 6);
		}
		faturaPaper.tableMalzemeHizmet.setEnabled(false);
		faturaPaper.btnKaydet.setVisible(false);
		faturaPaper.btnEkranGrnts.setVisible(true);
		faturaPaper.setVisible(true);
	}
	
	public void FaturaGoruntule(Member mmbr,E_Fatura f) {
		FaturaPaper faturaPaper = new FaturaPaper(mmbr);
		faturaPaper.tableDuzenleyen.setValueAt(f.getgonderenIsim(), 0, 1);
		faturaPaper.tableDuzenleyen.setValueAt(mmbr.getIl()+"/"+mmbr.getIlce(), 1, 1);
		faturaPaper.tableDuzenleyen.setValueAt(mmbr.getTel_num(), 2, 1);
		faturaPaper.tableDuzenleyen.setValueAt(mmbr.getMail(), 3, 1);
		faturaPaper.tableBilgiler.setValueAt(f.getId(), 0, 1);
		faturaPaper.tableBilgiler.setValueAt(f.getTarih(), 1, 1);
		faturaPaper.tableSayin.setValueAt(f.getAliciIsim(), 0, 1);
		for(int i=0;i<f.getMalzemeHizmetler().size();i++){
			faturaPaper.tableMalzemeHizmet.setValueAt(f.getMalzemeHizmetler().get(i).getId(), i, 0);
			faturaPaper.tableMalzemeHizmet.setValueAt(f.getMalzemeHizmetler().get(i).getAciklama(), i, 1);
			faturaPaper.tableMalzemeHizmet.setValueAt(f.getMalzemeHizmetler().get(i).getMiktar(), i, 2);
			faturaPaper.tableMalzemeHizmet.setValueAt(f.getMalzemeHizmetler().get(i).getBrmfiyat(), i, 3);
			faturaPaper.tableMalzemeHizmet.setValueAt(f.getMalzemeHizmetler().get(i).getKdvOraný(), i, 4);
			faturaPaper.tableMalzemeHizmet.setValueAt(f.getMalzemeHizmetler().get(i).getDigerVergiler(), i, 5);
			faturaPaper.tableMalzemeHizmet.setValueAt(f.getMalzemeHizmetler().get(i).getTutar(), i, 6);
		}
		faturaPaper.btnEkranGrnts.setVisible(false);

		faturaPaper.setVisible(true);
	}
	
	
	public boolean rowisEmpty(int row) {
		for(int i=1;i<6;i++){
			if(tableMalzemeHizmet.getValueAt(row, i)==null || tableMalzemeHizmet.getValueAt(row, i).toString().equals("")){
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
	
	public boolean deleteRow(int row) {
		for(int i=1;i<6;i++){
			if(tableMalzemeHizmet.getValueAt(row, i).toString().equals("")){
				return true;
			}
		}
		
		return false;
	}
	
	public int searchFatura(Member mmbr, String id){
		for(int i=0;i<mmbr.getFaturalar().size();i++){
			if(mmbr.getFaturalar().get(i).getId().equals(id)){
				return i;
			}
		}
		return -1;
	}
	public String idSearch(Member mmbr,E_Fatura fatura){
		for(int i=0;i<mmbr.getFaturalar().size();i++){
			if(mmbr.getFaturalar().get(i).getSaat().equals(fatura.getSaat()) && mmbr.getFaturalar().get(i).getTarih().equals(fatura.getTarih())){
				return mmbr.getFaturalar().get(i).getId();
			}
		}
		
		return "0";
	}
	
	public boolean ControlUsers(String nick){
		for(int i=0;i<loggui.getList().size();i++){
			if(loggui.getList().get(i).getNickname().equals(nick)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Create the frame.
	 */
	public FaturaPaper(final Member mmbr) {
		setResizable(false);
		
		setBounds(100, 100, 989, 685);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblDzenleyen = new JLabel("D\u00FCzenleyen");
		lblDzenleyen.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		tableDuzenleyen = new JTable();
		tableDuzenleyen.setFont(new Font("Tahoma", Font.BOLD, 13));
		tableDuzenleyen.setBackground(UIManager.getColor("Button.background"));
		tableDuzenleyen.setModel(new DefaultTableModel(
			new Object[][] {
				{"\u0130sim/Kurum", null},
				{"Konum", null},
				{"Tel", null},
				{"E-Posta", null},
			},
			new String[] {
				"New column", "New column"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableDuzenleyen.setValueAt(mmbr.getNickname(), 0, 1);
		tableDuzenleyen.setValueAt(mmbr.getIl()+"/"+mmbr.getIlce(), 1, 1);
		tableDuzenleyen.setValueAt(mmbr.getTel_num(), 2, 1);
		tableDuzenleyen.setValueAt(mmbr.getMail(), 3, 1);
		JLabel lblSayn = new JLabel("Say\u0131n");
		lblSayn.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		tableSayin = new JTable();
		tableSayin.setFont(new Font("Tahoma", Font.BOLD, 13));
		tableSayin.setModel(new DefaultTableModel(
			new Object[][] {
				{"Firma", null},
				{"E-Posta", null},
				{"Tel", null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		tableSayin.getColumnModel().getColumn(1).setResizable(false);
		tableSayin.setBackground(SystemColor.menu);
		
		JScrollPane scrollPane = new JScrollPane();
		
		lblEfatura = new JLabel("E-Fatura");
		lblEfatura.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		tableBilgiler = new JTable();
		tableBilgiler.setFont(new Font("Tahoma", Font.BOLD, 13));
		tableBilgiler.setBackground(UIManager.getColor("Button.background"));
		tableBilgiler.setModel(new DefaultTableModel(
			new Object[][] {
				{"Fatura No", null},
				{"Fatura Tarihi", null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		tableBilgiler.getColumnModel().getColumn(0).setMinWidth(25);
		
		Date zmn = new Date();
		DateFormat day = new SimpleDateFormat("dd");
		DateFormat month = new SimpleDateFormat("MM");
		DateFormat year = new SimpleDateFormat("yyyy");
		
		tableBilgiler.setValueAt(day.format(zmn)+"/"+month.format(zmn)+"/"+year.format(zmn), 1, 1);
		
		
		tableMalzemeHizmet = new JTable();
		tableMalzemeHizmet.setFont(new Font("Tahoma", Font.BOLD, 14));
		tableMalzemeHizmet.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"S\u0131ra No", "Malzeme/Hizmet A\u00E7\u0131klamas\u0131", "Miktar", "Birim Miktar", "KDV Oran\u0131", "Diger Vergiler", "Malzeme/Hizmet Tutar\u0131"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		
		tableMalzemeHizmet.getColumnModel().getColumn(0).setResizable(false);
		tableMalzemeHizmet.getColumnModel().getColumn(0).setPreferredWidth(52);
		tableMalzemeHizmet.getColumnModel().getColumn(1).setPreferredWidth(169);
		tableMalzemeHizmet.getColumnModel().getColumn(2).setPreferredWidth(47);
		tableMalzemeHizmet.getColumnModel().getColumn(3).setPreferredWidth(78);
		tableMalzemeHizmet.getColumnModel().getColumn(4).setPreferredWidth(68);
		tableMalzemeHizmet.getColumnModel().getColumn(5).setPreferredWidth(89);
		tableMalzemeHizmet.getColumnModel().getColumn(6).setResizable(false);
		tableMalzemeHizmet.getColumnModel().getColumn(6).setPreferredWidth(143);
		scrollPane.setViewportView(tableMalzemeHizmet);
		
		
		btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(null!=tableDuzenleyen.getCellEditor()){
					tableDuzenleyen.getCellEditor().stopCellEditing();
				}
				if(null!=tableBilgiler.getCellEditor()){
					tableBilgiler.getCellEditor().stopCellEditing();
				}
				if(null!=tableMalzemeHizmet.getCellEditor()){
					tableMalzemeHizmet.getCellEditor().stopCellEditing();
				}
				if(null!=tableSayin.getCellEditor()){
					tableSayin.getCellEditor().stopCellEditing();
				}
				LocalTime now= LocalTime.now();
				String saat = now.getHour()+":"+now.getMinute()+":"+now.getSecond();
				try{
					fatura.setSaat(saat);
					fatura.setgonderenIsim(tableDuzenleyen.getValueAt(0, 1).toString());
					fatura.setOlusturan(String.valueOf(mmbr.getId()));
					fatura.setTarih(tableBilgiler.getValueAt(1, 1).toString());
					fatura.setAliciIsim(tableSayin.getValueAt(0, 1).toString());
					String id;

					try {
						id = tableBilgiler.getValueAt(0, 1).toString();
					} catch (Exception e) {
						id = "0";
					}
					if(searchFatura(mmbr, id)!=-1){
						if(ControlUsers(fatura.getAliciIsim())){
							mmbr.getFaturalar().get(searchFatura(mmbr, id)).setgonderenIsim(fatura.getgonderenIsim());
							mmbr.getFaturalar().get(searchFatura(mmbr, id)).setAliciIsim(fatura.getAliciIsim());
							
							try {
								sql.FaturaVerileriniGuncelle(mmbr.getFaturalar().get(searchFatura(mmbr, id)));
								mmbr.setFaturalar(sql.MalHizmetOkuma(mmbr));
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							for(int i=0;i<mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().size();i++){
								if(!rowisEmpty(i)){
									if(deleteRow(i)){
										try {
											System.out.println(1);
											sql.MalHizmetSilme(mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().get(i));
											continue;
										} catch (SQLException ex) {
											ex.printStackTrace();
										}
										
									}
									mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().get(i).setAciklama(tableMalzemeHizmet.getValueAt(i, 1).toString());
									mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().get(i).setMiktar(Float.parseFloat(tableMalzemeHizmet.getValueAt(i, 2).toString()));
									mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().get(i).setBrmfiyat(Float.parseFloat(tableMalzemeHizmet.getValueAt(i, 3).toString()));
									mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().get(i).setKdvOraný(Float.parseFloat(tableMalzemeHizmet.getValueAt(i, 4).toString()));
									mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().get(i).setDigerVergiler(Float.parseFloat(tableMalzemeHizmet.getValueAt(i, 5).toString()));
									mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().get(i).setTutar(mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().get(i).getMiktar()*mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().get(i).getBrmfiyat()-(mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().get(i).getMiktar()*mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().get(i).getBrmfiyat()*mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().get(i).getKdvOraný()/100 + mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().get(i).getMiktar()*mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().get(i).getBrmfiyat()*mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().get(i).getDigerVergiler()/100));
									try {
										sql.MalzemHizmetGuncelleme(mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().get(i));
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								
							}
							
							
							ArrayList<MalzemeHizmet> malzemeHizmetler = new ArrayList<>();
							for(int i=mmbr.getFaturalar().get(searchFatura(mmbr, id)).getMalzemeHizmetler().size();i<15;i++){
								if(!rowisEmpty(i)){
									System.out.println(22222);
									MalzemeHizmet malzemeHizmet = new MalzemeHizmet();
									
									malzemeHizmet.setAciklama(tableMalzemeHizmet.getValueAt(i, 1).toString());
									malzemeHizmet.setMiktar(Float.parseFloat(tableMalzemeHizmet.getValueAt(i, 2).toString()));
									malzemeHizmet.setBrmfiyat(Float.parseFloat(tableMalzemeHizmet.getValueAt(i, 3).toString()));
									malzemeHizmet.setKdvOraný(Float.parseFloat(tableMalzemeHizmet.getValueAt(i, 4).toString()));
									malzemeHizmet.setDigerVergiler(Float.parseFloat(tableMalzemeHizmet.getValueAt(i, 5).toString()));
									malzemeHizmet.setTutar(malzemeHizmet.getMiktar()*malzemeHizmet.getBrmfiyat()-(malzemeHizmet.getMiktar()*malzemeHizmet.getBrmfiyat()*malzemeHizmet.getKdvOraný()/100 + malzemeHizmet.getMiktar()*malzemeHizmet.getBrmfiyat()*malzemeHizmet.getDigerVergiler()/100));
									malzemeHizmet.setTutanakid(tableBilgiler.getValueAt(2, 1).toString());
									malzemeHizmetler.add(malzemeHizmet);
								}		
							}
							fatura.setMalzemeHizmetler(malzemeHizmetler);
							try {
								mmbr.setFaturalar(sql.FaturaOku(mmbr.getFaturalar(),mmbr));
								sql.MalHizmetYazma(fatura.getMalzemeHizmetler());
								mmbr.setFaturalar(sql.MalHizmetOkuma(mmbr));
							} catch (SQLException e) {
								e.printStackTrace();
							}
							
							dispose();
						}
						else{
							JOptionPane.showMessageDialog(contentPane,"Alicinin kullanici adini kontrol ediniz","Hata",JOptionPane.WARNING_MESSAGE);
						}
						
						
					}else{
						if(!isTableEmpty()){
							if(ControlUsers(fatura.getAliciIsim())){
								mmbr.addFatura(fatura);
								try {
									sql.FaturaYaz(fatura);
									mmbr.setFaturalar(sql.FaturaOku(mmbr.getFaturalar(), mmbr));
								} catch (SQLException e) {
									e.printStackTrace();
								}
								ArrayList<MalzemeHizmet> malzemeHizmetler = new ArrayList<>();
								for(int i=0;i<15;i++){
									if(!rowisEmpty(i)){
										MalzemeHizmet malzemeHizmet = new MalzemeHizmet();
										malzemeHizmet.setTutanakid(idSearch(mmbr, fatura));
										malzemeHizmet.setAciklama(tableMalzemeHizmet.getValueAt(i,1 ).toString());
										malzemeHizmet.setMiktar(Float.parseFloat(tableMalzemeHizmet.getValueAt(i,2 ).toString()));
										malzemeHizmet.setBrmfiyat(Float.parseFloat(tableMalzemeHizmet.getValueAt(i,3 ).toString()));
										malzemeHizmet.setKdvOraný(Float.parseFloat(tableMalzemeHizmet.getValueAt(i,4 ).toString()));
										malzemeHizmet.setDigerVergiler(Float.parseFloat(tableMalzemeHizmet.getValueAt(i,5 ).toString()));
										malzemeHizmet.setTutar(malzemeHizmet.getMiktar()*malzemeHizmet.getBrmfiyat()-(malzemeHizmet.getMiktar()*malzemeHizmet.getBrmfiyat()*malzemeHizmet.getKdvOraný()/100 + malzemeHizmet.getMiktar()*malzemeHizmet.getBrmfiyat()*malzemeHizmet.getDigerVergiler()/100));
										malzemeHizmetler.add(malzemeHizmet);
									}
								}
								fatura.setMalzemeHizmetler(malzemeHizmetler);
								
								try {
									sql.MalHizmetYazma(fatura.getMalzemeHizmetler());
									mmbr.setFaturalar(sql.MalHizmetOkuma(mmbr));
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								dispose();
							}
							else{
								JOptionPane.showMessageDialog(contentPane,"Alicinin kullanici adini kontrol ediniz","Hata",JOptionPane.WARNING_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(contentPane,"Herhangi bir malzeme veya hizmet bilgisi girmediniz","Hata",JOptionPane.WARNING_MESSAGE);
						}	
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(contentPane,"Bilgileri Eksik veya Yanlýþ Girdiniz","Hata",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
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
					SaveScreenShot(contentPane, "farura"+day.format(zmn)+"_"+month.format(zmn)+"_"+year.format(zmn)+"_"+saat+".png");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(435)
					.addComponent(lblEfatura)
					.addPreferredGap(ComponentPlacement.RELATED, 396, Short.MAX_VALUE)
					.addComponent(btnEkranGrnts, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(lblDzenleyen))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(tableDuzenleyen, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
					.addGap(447)
					.addComponent(tableBilgiler, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(lblSayn))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(tableSayin, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 949, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(444)
					.addComponent(btnKaydet, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addComponent(lblEfatura)
							.addGap(7)
							.addComponent(lblDzenleyen))
						.addComponent(btnEkranGrnts))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tableDuzenleyen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tableBilgiler, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblSayn)
					.addGap(13)
					.addComponent(tableSayin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnKaydet))
		);
		contentPane.setLayout(gl_contentPane);
		
		
	}
}
