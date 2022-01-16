
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.ImageIcon;
import java.awt.Color;


public class loginGUI extends JFrame {
	static final MySQL sql = new MySQL();
	static ArrayList<Member> mmbr = new ArrayList<>();
	
	private JPanel contentPane;
	private JTextField NameInput;
	private JLabel lblNewLabel;
	private JButton kayitButton;
	private JLabel lblKullancAd;
	private JLabel lblSifre;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	
	public static void main(String[] args) throws SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginGUI frame = new loginGUI();
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
	public ArrayList<Member> getList(){
		return mmbr;
	}
	public void setList(ArrayList<Member> getinglist){
		mmbr = getinglist;
	}
	
	public void memberAdd(Member member) {
		mmbr.add(member);
	}
	public int userSearch() {
		int i;
		for(i=0;i<mmbr.size();i++){
			char[] password = passwordField.getPassword();
			if(NameInput.getText().equals(mmbr.get(i).getNickname()) && String.valueOf(password).equals(mmbr.get(i).getSifre())){
				return i;
			}
			
		}
		return -1 ;
	}
	public void evraklariyerlesti() throws SQLException {
		for(int i=0;i<mmbr.size();i++){
			mmbr.get(i).setIrsaliyeler(sql.ÝrsaliyeOkuma(mmbr.get(i).getIrsaliyeler(),mmbr.get(i)));
			mmbr.get(i).setIrsaliyeler(sql.MalOkuma(mmbr.get(i)));
			mmbr.get(i).setTutanaklar(sql.TutanakOkuma(mmbr.get(i).getTutanaklar(), mmbr.get(i)));
			mmbr.get(i).setFaturalar(sql.FaturaOku(mmbr.get(i).getFaturalar(), mmbr.get(i)));
			mmbr.get(i).setFaturalar(sql.MalHizmetOkuma(mmbr.get(i)));
			mmbr.get(i).setMailler(sql.MailOkuma(mmbr.get(i), mmbr.get(i).getMailler()));
		}
	}
	
	public loginGUI() {
		setResizable(false);
		try {
			mmbr = sql.KullanýcýOkuma(mmbr);
			evraklariyerlesti();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setTitle("Giri\u015F Ekran\u0131");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		NameInput = new JTextField();
		NameInput.setBounds(202, 185, 307, 51);
		NameInput.setFont(new Font("Arial Black", Font.BOLD, 14));
		NameInput.setColumns(10);
		
		lblNewLabel = new JLabel("E-Evrak Sistemine Giri\u015F");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(155, 46, 370, 93);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		kayitButton = new JButton("Kay\u0131t Ol");
		kayitButton.setBounds(364, 354, 144, 47);
		kayitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RegisterGUI rgstrgui = new RegisterGUI();
				rgstrgui.setVisible(true);
				dispose();
				
			}
		});
		JButton girisButton = new JButton("Giris");
		girisButton.setBounds(202, 354, 144, 47);
		girisButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(NameInput.getText().length()!=0 && passwordField.getPassword().length!=0){
					if(mmbr.size()!=0){
						if(userSearch()!=-1){
							MemberGUI memGui = new MemberGUI(mmbr.get(userSearch()));
							memGui.setVisible(true);
							dispose();
						}
						else{
							JOptionPane.showMessageDialog(contentPane,"Böyle Bir Kullanýcý Yok","Hata",JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(contentPane,"Kayýtlý Kullanýcý Yok","Hata",JOptionPane.WARNING_MESSAGE);

					}
				}
				else{
					JOptionPane.showMessageDialog(contentPane,"Kullanýcý Adý veya Þifre Boþ","Hata",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
		lblKullancAd = new JLabel("Kullan\u0131c\u0131 Ad\u0131");
		lblKullancAd.setBounds(76, 194, 121, 33);
		lblKullancAd.setHorizontalAlignment(SwingConstants.CENTER);
		lblKullancAd.setFont(new Font("Arial Black", Font.BOLD, 13));
		
		lblSifre = new JLabel("\u015Eifre");
		lblSifre.setBounds(76, 283, 121, 33);
		lblSifre.setHorizontalAlignment(SwingConstants.CENTER);
		lblSifre.setFont(new Font("Arial Black", Font.BOLD, 13));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(202, 275, 307, 50);
		
		JLabel label = DefaultComponentFactory.getInstance().createLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\dursun\\Desktop\\Ders\\JAVA\\Java_Project\\android-lollipop-material-design-wallpaper-preview.jpg"));
		label.setBounds(5, 5, 689, 460);
		contentPane.setLayout(null);
		contentPane.add(lblKullancAd);
		contentPane.add(lblSifre);
		contentPane.add(NameInput);
		contentPane.add(passwordField);
		contentPane.add(girisButton);
		contentPane.add(kayitButton);
		contentPane.add(lblNewLabel);
		contentPane.add(label);
	}
}
