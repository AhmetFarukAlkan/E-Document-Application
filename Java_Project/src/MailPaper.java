import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JTextArea;
import javax.swing.JButton;

import com.sun.org.apache.bcel.internal.generic.LNEG;


public class MailPaper extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldKime;
	private JTextField textFieldKonu;
	private JLabel lblKonu;
	private JButton btnSs;
	private JTextArea textAreaMail;
	private JButton btnGnder;
	
	loginGUI logGui = new loginGUI();
	Mail mail = new Mail();
	MySQL sql = new MySQL();
	static Member mmbr = new Member();
	private JLabel lblId;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MailPaper frame = new MailPaper(mmbr);
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
	public void MailGoruntule(Member mmbr,Mail mail,boolean edit) {
		MailPaper mailPaper = new MailPaper(mmbr);
		mailPaper.textFieldKime.setText(mail.getAliciIsim());
		mailPaper.textFieldKonu.setText(mail.getBaslik());
		mailPaper.textAreaMail.setText(mail.getIcerik());
		mailPaper.lblId.setText(mail.getId());
		mailPaper.btnSs.setVisible(false);
		if(!edit){
			mailPaper.textAreaMail.setEditable(false);
			mailPaper.textFieldKime.setEditable(false);
			mailPaper.textFieldKonu.setEditable(false);
			mailPaper.btnGnder.setVisible(false);
			mailPaper.btnSs.setVisible(true);
		}
		mailPaper.setVisible(true);
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
	
	public boolean searchUser(String nick) {
		for(int i=0;i<logGui.getList().size();i++){
			if(nick.equals(logGui.getList().get(i).getNickname())){
				return true;
			}
		}
		return false;
	}
	
	public boolean seachUsers(String alici){
		String[] users = alici.split(","); 
		for(int i=0;i<users.length;i++){
			if(!searchUser(String.valueOf(users[i]))){
				return false;
			}
		}
		
		return true;
	}
	
	public int searchMail(Member mmbr,String id) {
		for(int i=0;i<mmbr.getMailler().size();i++){
			if(mmbr.getMailler().get(i).getId().equals(id)){
				return i;
			}
		}
		return -1;
	}
	
	
	public MailPaper(final Member mmbr) {
		setTitle("E-Mail");
		setBounds(100, 100, 689, 504);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textFieldKime = new JTextField();
		textFieldKime.setColumns(10);
		
		JLabel lblKime = new JLabel("Kime");
		lblKime.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKime.setHorizontalAlignment(SwingConstants.LEFT);
		
		textFieldKonu = new JTextField();
		textFieldKonu.setColumns(10);
		
		lblKonu = new JLabel("Konu");
		lblKonu.setHorizontalAlignment(SwingConstants.LEFT);
		lblKonu.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblId = new JLabel("");
		
		textAreaMail = new JTextArea();
		textAreaMail.setFont(new Font("Arial Black", Font.BOLD, 13));
		
		btnGnder = new JButton("Kaydet ve Gönder");
		btnGnder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Date zmn = new Date();
				DateFormat day = new SimpleDateFormat("dd");
				DateFormat month = new SimpleDateFormat("MM");
				DateFormat year = new SimpleDateFormat("yyyy");
				LocalTime now= LocalTime.now();
				String saat = now.getHour()+":"+now.getMinute()+":"+now.getSecond();
				
				mail.setAliciIsim(textFieldKime.getText());
				mail.setgonderenIsim(mmbr.getNickname());
				mail.setIcerik(textAreaMail.getText());
				mail.setOlusturan(String.valueOf(mmbr.getId()));
				mail.setBaslik(textFieldKonu.getText());
				mail.setSaat(saat);
				mail.setTarih(day.format(zmn)+"/"+month.format(zmn)+"/"+year.format(zmn));
				if(searchMail(mmbr, lblId.getText())!=-1){
					if(seachUsers(mail.getAliciIsim())){
						mmbr.getMailler().get(searchMail(mmbr, lblId.getText())).setIcerik(mail.getIcerik());
						mmbr.getMailler().get(searchMail(mmbr, lblId.getText())).setBaslik(mail.getBaslik());
						mmbr.getMailler().get(searchMail(mmbr, lblId.getText())).setAliciIsim(mail.getAliciIsim());
						
						try {
							sql.MailGuncelle(mmbr.getMailler().get(searchMail(mmbr, lblId.getText())));
							mmbr.setMailler(sql.MailOkuma(mmbr, mmbr.getMailler()));
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						dispose();
					}else{
						JOptionPane.showMessageDialog(contentPane,"Alicinin kullanici adini kontrol ediniz","Hata",JOptionPane.WARNING_MESSAGE);
					}

				}
				else{
					if(mail.getAliciIsim().length()==0){
						JOptionPane.showMessageDialog(contentPane,"Alýcý bilgisini girmediniz","Hata",JOptionPane.WARNING_MESSAGE);
					}
					else if(mail.getBaslik().length()==0){
						JOptionPane.showMessageDialog(contentPane,"Baslik bilgisi girmediniz","Hata",JOptionPane.WARNING_MESSAGE);
					}
					else if(mail.getIcerik().length()==0){
						JOptionPane.showMessageDialog(contentPane,"Mail'e bir þey yazmadýnýz","Hata",JOptionPane.WARNING_MESSAGE);
					}
					else if(seachUsers(mail.getAliciIsim())){
						mmbr.addMail(mail);
						try {
							sql.MailYazma(mail);
							mmbr.setMailler(sql.MailOkuma(mmbr, mmbr.getMailler()));
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
		});
		
		
		btnSs = new JButton("SS");
		btnSs.setVisible(false);
		btnSs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Date zmn = new Date();
				DateFormat day = new SimpleDateFormat("dd");
				DateFormat month = new SimpleDateFormat("mm");
				DateFormat year = new SimpleDateFormat("yyyy");
				LocalTime now= LocalTime.now();
				String saat = now.getHour()+"_"+now.getMinute()+"_"+now.getSecond();
				try {
					SaveScreenShot(contentPane, "Mail"+day.format(zmn)+"_"+month.format(zmn)+"_"+year.format(zmn)+"_"+saat+".png");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(textAreaMail, GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(229)
							.addComponent(btnGnder, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
							.addGap(201)
							.addComponent(lblId))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnSs))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblKime, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
								.addComponent(lblKonu, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textFieldKonu)
								.addComponent(textFieldKime, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))))
					.addGap(20))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(btnSs)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldKime, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
						.addComponent(lblKime))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldKonu, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
						.addComponent(lblKonu, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addComponent(textAreaMail, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGnder)
						.addComponent(lblId)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
