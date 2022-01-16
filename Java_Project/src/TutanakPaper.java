
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;



public class TutanakPaper extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBaslik;
	private JTextField textisim1;
	private JTextField textisim2;
	private JTextField textisim3;
	private JTextField textTeslimEden;
	private JLabel lblTarih;
	private JLabel lblSaat;
	private JTextField textFieldTarih;
	private JTextField textFieldSaat;
	private JButton btnKaydet;
	private JLabel lblTeslimAlan2;
	private JLabel lblTeslimAlan1;
	private JLabel lblTeslimAlan3;
	private JTextArea textArea;
	private JLabel lblId;
	
	MySQL sql = new MySQL();
	static Member mmbr = new Member();
	E_Tutanak tutanak = new E_Tutanak();
	loginGUI loggui = new loginGUI();
	private JButton buttonEkranGrnts;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TutanakPaper frame = new TutanakPaper(mmbr);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public boolean ControlUsers(String nick){
		if(nick.equals("")){
			return true;
		}
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
	
	public void tutanakGoruntuleme(Member mmbr, E_Tutanak tutanak,boolean edit) {
		TutanakPaper tutpaper = new TutanakPaper(mmbr);
		tutpaper.textFieldBaslik.setText(tutanak.getBaslik());
		tutpaper.textFieldSaat.setText(tutanak.getSaat());
		tutpaper.textFieldTarih.setText(tutanak.getTarih());
		tutpaper.textisim1.setText(tutanak.getAliciIsim());
		tutpaper.textisim2.setText(tutanak.getAliciIsim2());
		tutpaper.textisim3.setText(tutanak.getAliciIsim3());
		tutpaper.textTeslimEden.setText(tutanak.getgonderenIsim());
		tutpaper.textArea.setText(tutanak.getIcerik());
		tutpaper.lblId.setText(tutanak.getId());
		tutpaper.buttonEkranGrnts.setVisible(false);

		if(!edit){
			tutpaper.textFieldBaslik.setEditable(false);
			tutpaper.textFieldSaat.setEditable(false);
			tutpaper.textFieldTarih.setEditable(false);
			tutpaper.textisim1.setEditable(false);
			tutpaper.textisim2.setEditable(false);
			tutpaper.textisim3.setEditable(false);
			tutpaper.textTeslimEden.setEditable(false);
			tutpaper.textArea.setEditable(false);
			tutpaper.btnKaydet.setVisible(false);
			tutpaper.buttonEkranGrnts.setVisible(true);	
		}
		tutpaper.setVisible(true);
	}
	
	public int searchTutanak(Member mmbr,String id) {
		for(int i=0;i<mmbr.getTutanaklar().size();i++){
			if(mmbr.getTutanaklar().get(i).getId().equals(id)){
				return i;
			}
		}
		return -1;
	}
	public String idSearch(Member mmbr, E_Tutanak tutanak) {
		for(int i=0;i<mmbr.getTutanaklar().size();i++){
			if(mmbr.getTutanaklar().get(i).getSaat().equals(tutanak.getSaat())){
				return mmbr.getTutanaklar().get(i).getId();
			}
		}
		
		return "0";
	}
	
	
	public TutanakPaper(final Member mmbr) {
		setTitle("E-Tutanak");
		setResizable(false);
		setBackground(UIManager.getColor("CheckBox.interiorBackground"));
		setBounds(100, 100, 689, 654);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.disabledShadow"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textFieldBaslik = new JTextField();
		textFieldBaslik.setBackground(SystemColor.inactiveCaptionBorder);
		textFieldBaslik.setFont(new Font("Tahoma", Font.BOLD, 17));
		textFieldBaslik.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldBaslik.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setText("\t");
		textArea.setFont(new Font("Tahoma", Font.BOLD, 15));
		textArea.setBackground(SystemColor.inactiveCaptionBorder);
		
		JLabel lbl1 = new JLabel("\u0130mza");
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lbl2 = new JLabel("\u0130mza");
		lbl2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lbl4 = new JLabel("\u0130mza");
		lbl4.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lbl5 = new JLabel("\u0130mza");
		lbl5.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		textisim1 = new JTextField();
		textisim1.setToolTipText("");
		textisim1.setBackground(SystemColor.inactiveCaptionBorder);
		textisim1.setFont(new Font("Tahoma", Font.BOLD, 13));
		textisim1.setColumns(10);
		
		textisim2 = new JTextField();
		textisim2.setBackground(SystemColor.inactiveCaptionBorder);
		textisim2.setFont(new Font("Tahoma", Font.BOLD, 13));
		textisim2.setColumns(10);
		
		textisim3 = new JTextField();
		textisim3.setBackground(SystemColor.inactiveCaptionBorder);
		textisim3.setFont(new Font("Tahoma", Font.BOLD, 13));
		textisim3.setColumns(10);
		
		textTeslimEden = new JTextField();
		textTeslimEden.setBackground(SystemColor.inactiveCaptionBorder);
		textTeslimEden.setFont(new Font("Tahoma", Font.BOLD, 13));
		textTeslimEden.setColumns(10);
		textTeslimEden.setText(mmbr.getNickname());
		
		lblTarih = new JLabel("Tarih");
		lblTarih.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblSaat = new JLabel("Saat");
		lblSaat.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		Date zmn = new Date();
		DateFormat day = new SimpleDateFormat("dd");
		DateFormat month = new SimpleDateFormat("MM");
		DateFormat year = new SimpleDateFormat("yyyy");
		
		textFieldTarih = new JTextField();
		textFieldTarih.setBackground(SystemColor.inactiveCaptionBorder);
		textFieldTarih.setEditable(false);
		textFieldTarih.setColumns(10);
		textFieldTarih.setText(day.format(zmn)+"/"+month.format(zmn)+"/"+year.format(zmn));
		
		LocalTime now= LocalTime.now();
		String saat = now.getHour()+":"+now.getMinute()+":"+now.getSecond();
		textFieldSaat = new JTextField();
		textFieldSaat.setBackground(SystemColor.inactiveCaptionBorder);
		textFieldSaat.setEditable(false);
		textFieldSaat.setColumns(10);
		textFieldSaat.setText(saat);
		
		
		JLabel lblTeslimEden = new JLabel("Teslim Eden");
		lblTeslimEden.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblTeslimAlan2 = new JLabel("Teslim Alan");
		lblTeslimAlan2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblTeslimAlan1 = new JLabel("Teslim Alan");
		lblTeslimAlan1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblTeslimAlan3 = new JLabel("Teslim Alan");
		lblTeslimAlan3.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblId = new JLabel("");
		
		buttonEkranGrnts = new JButton("SS");
		buttonEkranGrnts.setVisible(false);
		buttonEkranGrnts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Date zmn = new Date();
				DateFormat day = new SimpleDateFormat("dd");
				DateFormat month = new SimpleDateFormat("mm");
				DateFormat year = new SimpleDateFormat("yyyy");
				LocalTime now= LocalTime.now();
				String saat = now.getHour()+"_"+now.getMinute()+"_"+now.getSecond();
				try {
					SaveScreenShot(contentPane, "tutanak"+day.format(zmn)+"_"+month.format(zmn)+"_"+year.format(zmn)+"_"+saat+".png");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				tutanak.setOlusturan(String.valueOf(mmbr.getId()));
				tutanak.setAliciIsim(textisim1.getText());
				tutanak.setAliciIsim2(textisim2.getText());
				tutanak.setAliciIsim3(textisim3.getText());
				tutanak.setBaslik(textFieldBaslik.getText());
				tutanak.setgonderenIsim(textTeslimEden.getText());
				tutanak.setIcerik(textArea.getText());
				tutanak.setSaat(textFieldSaat.getText());
				tutanak.setTarih(textFieldTarih.getText());
				System.out.println(lblId.getText());
				
				if(searchTutanak(mmbr, lblId.getText())!=-1){
					if(ControlUsers(tutanak.getAliciIsim())&&ControlUsers(tutanak.getAliciIsim2()) &&ControlUsers(tutanak.getAliciIsim3())){
						mmbr.getTutanaklar().get(searchTutanak(mmbr, lblId.getText())).setgonderenIsim(tutanak.getgonderenIsim());
						mmbr.getTutanaklar().get(searchTutanak(mmbr, lblId.getText())).setAliciIsim(tutanak.getAliciIsim());
						mmbr.getTutanaklar().get(searchTutanak(mmbr, lblId.getText())).setAliciIsim2(tutanak.getAliciIsim2());
						mmbr.getTutanaklar().get(searchTutanak(mmbr, lblId.getText())).setAliciIsim3(tutanak.getAliciIsim3());
						mmbr.getTutanaklar().get(searchTutanak(mmbr, lblId.getText())).setBaslik(tutanak.getBaslik());
						mmbr.getTutanaklar().get(searchTutanak(mmbr, lblId.getText())).setIcerik(tutanak.getIcerik());
						mmbr.getTutanaklar().get(searchTutanak(mmbr, lblId.getText())).setId(lblId.getText());
						mmbr.getTutanaklar().get(searchTutanak(mmbr, lblId.getText())).setSaat(tutanak.getSaat());
						mmbr.getTutanaklar().get(searchTutanak(mmbr, lblId.getText())).setTarih(tutanak.getTarih());
						try {
							sql.TutanakVerileriniGuncelle(mmbr.getTutanaklar().get(searchTutanak(mmbr, lblId.getText())));
							mmbr.setTutanaklar(sql.TutanakOkuma(mmbr.getTutanaklar(), mmbr));
						} catch (SQLException e) {
							e.printStackTrace();
						}
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(contentPane,"Alicinin kullanici adini kontrol ediniz","Hata",JOptionPane.WARNING_MESSAGE);
					}

				}
				else{
					if(tutanak.getAliciIsim().length()==0&&tutanak.getAliciIsim2().length()==0&&tutanak.getAliciIsim3().length()==0){
						JOptionPane.showMessageDialog(contentPane,"Alýcý bilgisini girmediniz","Hata",JOptionPane.WARNING_MESSAGE);
					}
					else if(tutanak.getBaslik().length()==0){
						JOptionPane.showMessageDialog(contentPane,"Baslik bilgisi girmediniz","Hata",JOptionPane.WARNING_MESSAGE);
					}
					else if(tutanak.getIcerik().length()==0){
						JOptionPane.showMessageDialog(contentPane,"Ýcerik olarak bir þey yazmadýnýz","Hata",JOptionPane.WARNING_MESSAGE);
					}
					else{
						if(ControlUsers(tutanak.getAliciIsim())&&ControlUsers(tutanak.getAliciIsim2())&&ControlUsers(tutanak.getAliciIsim3())){
							mmbr.addTutanak(tutanak);
							try {
								sql.TutanakYazma(tutanak);
								mmbr.setTutanaklar(sql.TutanakOkuma(mmbr.getTutanaklar(), mmbr));
							} catch (SQLException e) {
								e.printStackTrace();
							}
							dispose();
						}
						else{
							JOptionPane.showMessageDialog(contentPane,"Alicilarin kullanici adini kontrol ediniz","Hata",JOptionPane.WARNING_MESSAGE);
						}
						
					}
					
					
				}
			}
		});
		

		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblId)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(35)
											.addComponent(lblTeslimAlan1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
											.addGap(49)
											.addComponent(lblTeslimAlan2, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
											.addComponent(lblTeslimAlan3, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
											.addGap(112))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblTarih)
												.addComponent(lblSaat))
											.addGap(35)))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldSaat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldTarih, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblTeslimEden))))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textisim1, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textisim2, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnKaydet)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textisim3, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
											.addGap(27)
											.addComponent(textTeslimEden, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)))))))
					.addGap(29))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(238)
							.addComponent(textFieldBaslik, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(206)
							.addComponent(buttonEkranGrnts, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 642, GroupLayout.PREFERRED_SIZE)))
					.addGap(19))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(81)
					.addComponent(lbl1)
					.addGap(107)
					.addComponent(lbl2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(lbl5, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
					.addComponent(lbl4, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(100))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonEkranGrnts)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(textFieldBaslik, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
					.addGap(37)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textFieldTarih, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldSaat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTarih)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSaat)))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTeslimAlan1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTeslimAlan3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTeslimAlan2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTeslimEden))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textisim1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textTeslimEden, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textisim3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textisim2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addComponent(lbl4)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl5)
								.addComponent(lbl2)
								.addComponent(lbl1))
							.addGap(29)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblId)
						.addComponent(btnKaydet))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
