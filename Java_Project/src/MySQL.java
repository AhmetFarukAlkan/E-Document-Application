import java.sql.*;
import java.util.ArrayList;
public class MySQL {
	//Hepsi
	public ArrayList<Member> KullanýcýOkuma(ArrayList<Member> mmbr) throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT * from kullanicilar");
		mmbr.clear();
		while(rs.next()){
			Member newmmbr = new Member();
			newmmbr.setId(rs.getInt("kullanici_id"));
			newmmbr.setName(rs.getString("ad"));
			newmmbr.setSurname(rs.getString("soyad"));
			newmmbr.setSifre(rs.getString("sifre"));
			newmmbr.setTel_num(rs.getString("tel_num"));
			newmmbr.setMail(rs.getString("mail"));
			newmmbr.setDgmGun(rs.getString("dgmGun"));
			newmmbr.setDgmAy(rs.getString("dgmAy"));
			newmmbr.setDgmYil(rs.getString("dgmyýl"));
			newmmbr.setIl(rs.getString("il"));
			newmmbr.setIlce(rs.getString("ilce"));
			newmmbr.setNickname(rs.getString("nickname"));
			mmbr.add(newmmbr);
		}
		c.close();
		st.close();
		return mmbr;
		
	}
	//Bir tane
	public void KullanýcýYazma(Member mmbr) throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		Statement st = c.createStatement();
		String query = "INSERT INTO kullanicilar (ad,soyad,sifre,tel_num,mail,dgmGun,dgmAy,dgmYýl,il,ilce,nickname) VALUES ('"+mmbr.getName()+"','"+mmbr.getSurname()+"','"+mmbr.getSifre()+"','"+mmbr.getTel_num()+"','"+mmbr.getMail()+"','"+mmbr.getDgmGun()+"','"+mmbr.getDgmAy()+"','"+mmbr.getDgmYil()+"','"+mmbr.getIl()+"','"+mmbr.getIlce()+"','"+mmbr.getNickname()+"')";
		int sonuc = st.executeUpdate(query);
		System.out.println(sonuc);
		c.close();
		st.close();
	}
	public void KullaniciVerileriniGüncelle(Member mmbr) throws SQLException{
		String sqlUpdate = "UPDATE kullanicilar SET ad = '"+mmbr.getName()+"',soyad = '"+mmbr.getSurname()+"',sifre = '"+mmbr.getSifre()+"', tel_num= '"+mmbr.getTel_num()+"',mail = '"+mmbr.getMail()+"',dgmGun = '"+mmbr.getDgmGun()+"',dgmAy = '"+mmbr.getDgmAy()+"',dgmYýl = '"+mmbr.getDgmYil()+"',il = '"+mmbr.getIl()+"',ilce = '"+mmbr.getIlce()+"',nickname='"+mmbr.getNickname()+"' WHERE kullanici_id = '"+mmbr.getId()+"'";
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		PreparedStatement pstmt = conn.prepareStatement(sqlUpdate); 
		int sonuc = pstmt.executeUpdate();
		System.out.println(sonuc);
		conn.close();
		pstmt.close();
	}

	public void ÝrsaliyeYazma(E_Irsaliye irsaliye) throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		Statement st = c.createStatement();
		String query = "INSERT INTO irsaliye (gonderen,alici,tarih,tur,sevktarih,saat,Olusturan) VALUES ('"+irsaliye.getgonderenIsim()+"','"+irsaliye.getAliciIsim()+"','"+irsaliye.getTarih()+"','"+irsaliye.getTur()+"','"+irsaliye.getSevkTarihi()+"','"+irsaliye.getSaat()+"','"+irsaliye.getOlusturan()+"')";
		int sonuc = st.executeUpdate(query);
		System.out.println(sonuc);
		c.close();
		st.close();
	}
	
	public ArrayList<E_Irsaliye> ÝrsaliyeOkuma(ArrayList<E_Irsaliye> irsaliyeler,Member mmbr) throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT * from irsaliye");
		irsaliyeler.clear();
		while(rs.next()){
			if(rs.getString("Olusturan").equals(String.valueOf(mmbr.getId()))){
				E_Irsaliye irsaliye = new E_Irsaliye();
				irsaliye.setgonderenIsim(rs.getString("gonderen"));
				irsaliye.setAliciIsim(rs.getString("alici"));
				irsaliye.setTarih(rs.getString("tarih"));
				irsaliye.setTur(rs.getString("tur"));
				irsaliye.setSevkTarihi(rs.getString("sevktarih"));
				irsaliye.setSaat(rs.getString("saat"));
				irsaliye.setOlusturan(rs.getString("Olusturan"));
				irsaliye.setId(rs.getString("id"));
				irsaliyeler.add(irsaliye);
			}
			
		}
		c.close();
		st.close();
		return irsaliyeler;
	}
	
	public void IrsaliyeVerileriniGüncelle(E_Irsaliye irsaliye) throws SQLException{
		String sqlUpdate = "UPDATE irsaliye SET gonderen = '"+irsaliye.getgonderenIsim()+"',alici = '"+irsaliye.getAliciIsim()+"',tarih = '"+irsaliye.getTarih()+"', tur= '"+irsaliye.getTur()+"',sevktarih = '"+irsaliye.getSevkTarihi()+"',saat = '"+irsaliye.getSaat()+"',Olusturan = '"+irsaliye.getOlusturan()+"' WHERE id = '"+irsaliye.getId()+"'";
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		PreparedStatement pstmt = conn.prepareStatement(sqlUpdate); 
		int sonuc = pstmt.executeUpdate();
		System.out.println(sonuc);
		conn.close();
		pstmt.close();
	}

	public ArrayList<E_Irsaliye> MalOkuma(Member mmbr) throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT * from mallar");
		ArrayList<Mal> mallar = new ArrayList<>(); 
		
		while(rs.next()){
			mallar.clear();
			for(int i = 0;i < mmbr.getIrsaliyeler().size();i++){
				
				if(rs.getString("irsaliyeId").equals(String.valueOf(mmbr.getIrsaliyeler().get(i).getId()))){
					Mal mal = new Mal();
					mal.setAdet(rs.getInt("adet"));
					mal.setBrmFiyat(rs.getFloat("birimfiyat"));
					mal.setIrsaliyeid(rs.getString("irsaliyeId"));
					mal.setMalCinsi(rs.getString("cinsi"));
					mal.setTutar(rs.getFloat("tutar"));
					mal.setId(rs.getString("id"));
					mmbr.getIrsaliyeler().get(i).AddMal(mal);
				}
			}
			
		}
		c.close();
		st.close();
		return mmbr.getIrsaliyeler();
	}
	
	public void malYazma(ArrayList<Mal> malllar)throws SQLException{
		
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		Statement st = c.createStatement();
		for(int i=0;i<malllar.size();i++){
			String query = "INSERT INTO mallar (cinsi,adet,birimfiyat,tutar,irsaliyeId) VALUES ('"+malllar.get(i).getMalCinsi()+"','"+malllar.get(i).getAdet()+"','"+malllar.get(i).getBrmFiyat()+"','"+malllar.get(i).getTutar()+"','"+malllar.get(i).getIrsaliyeid()+"')";
			int sonuc = st.executeUpdate(query);
			System.out.println(sonuc);
		}
		
		c.close();
		st.close();
		
	}
	
	public void MalGuncelle(Mal mal)throws SQLException {
		String sqlUpdate = "UPDATE mallar SET cinsi = '"+mal.getMalCinsi()+"',adet = '"+mal.getAdet()+"',birimfiyat = '"+mal.getBrmFiyat()+"', tutar= '"+mal.getTutar()+"',irsaliyeId = '"+mal.getIrsaliyeid()+"' WHERE id = '"+mal.getId()+"' ";
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		PreparedStatement pstmt = conn.prepareStatement(sqlUpdate); 
		int sonuc = pstmt.executeUpdate();
		System.out.println(sonuc);
		conn.close();
		pstmt.close();	
	}
	
	public void MalSil(Mal mal)throws SQLException {
		String sqldelete = "DELETE FROM mallar WHERE id = '"+mal.getId()+"' ";
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		Statement pstmt = conn.createStatement(); 
		int sonuc = pstmt.executeUpdate(sqldelete);
		System.out.println(sonuc);
		conn.close();
		pstmt.close();	
	}
	
	public void TutanakYazma(E_Tutanak tutanak)throws SQLException {
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		Statement st = c.createStatement();
		String query = "INSERT INTO tutanak (icerik,gonderen,alici1,alici2,alici3,tarih,saat,tur,olusturan,baslik) VALUES ('"+tutanak.getIcerik()+"','"+tutanak.getgonderenIsim()+"','"+tutanak.getAliciIsim()+"','"+tutanak.getAliciIsim2()+"','"+tutanak.getAliciIsim3()+"','"+tutanak.getTarih()+"','"+tutanak.getSaat()+"','"+tutanak.getTur()+"','"+tutanak.getOlusturan()+"','"+tutanak.getBaslik()+"')";
		int sonuc = st.executeUpdate(query);
		System.out.println(sonuc);
		c.close();
		st.close();
		
	}
	
	public ArrayList<E_Tutanak> TutanakOkuma(ArrayList<E_Tutanak> tutanaklar, Member mmbr)throws SQLException {
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT * from tutanak");
		tutanaklar.clear();
		while(rs.next()){
			if(rs.getString("Olusturan").equals(String.valueOf(mmbr.getId()))){
				E_Tutanak tutanak = new E_Tutanak();
				tutanak.setgonderenIsim(rs.getString("gonderen"));
				tutanak.setIcerik(rs.getString("icerik"));
				tutanak.setAliciIsim(rs.getString("alici1"));
				tutanak.setAliciIsim2(rs.getString("alici2"));
				tutanak.setAliciIsim3(rs.getString("alici3"));
				tutanak.setTarih(rs.getString("tarih"));
				tutanak.setSaat(rs.getString("saat"));
				tutanak.setTur(rs.getString("tur"));
				tutanak.setOlusturan(rs.getString("olusturan"));
				tutanak.setId(rs.getString("id"));
				tutanak.setBaslik(rs.getString("baslik"));
				tutanaklar.add(tutanak);
			}
			
		}
		c.close();
		st.close();
		return tutanaklar;

	}
	
	public void TutanakVerileriniGuncelle(E_Tutanak tutanak)throws SQLException {
		String sqlUpdate = "UPDATE tutanak SET gonderen = '"+tutanak.getgonderenIsim()+"',icerik = '"+tutanak.getIcerik()+"',alici1 = '"+tutanak.getAliciIsim()+"', alici2= '"+tutanak.getAliciIsim2()+"',alici3 = '"+tutanak.getAliciIsim3()+"',saat = '"+tutanak.getSaat()+"',tarih = '"+tutanak.getTarih()+"',baslik = '"+tutanak.getBaslik()+"' WHERE id = '"+tutanak.getId()+"'";
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		PreparedStatement pstmt = conn.prepareStatement(sqlUpdate); 
		int sonuc = pstmt.executeUpdate();
		System.out.println(sonuc);
		conn.close();
		pstmt.close();
	}
	
	public void FaturaYaz(E_Fatura fatura)throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		Statement st = c.createStatement();
		String query = "INSERT INTO fatura (GonderenIsým,aliciisim,tarih,saat,tur,olusturan) VALUES ('"+fatura.getgonderenIsim()+"','"+fatura.getAliciIsim()+"','"+fatura.getTarih()+"','"+fatura.getSaat()+"','"+fatura.getTur()+"','"+fatura.getOlusturan()+"')";
		int sonuc = st.executeUpdate(query);
		System.out.println(sonuc);
		c.close();
		st.close();
	}
	
	public ArrayList<E_Fatura> FaturaOku(ArrayList<E_Fatura> Faturalar, Member mmbr) throws SQLException{
		
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT * from fatura");
		Faturalar.clear();
		while(rs.next()){
			if(rs.getString("olusturan").equals(String.valueOf(mmbr.getId()))){
				E_Fatura fatura = new E_Fatura();
				fatura.setgonderenIsim(rs.getString("GonderenIsým"));
				fatura.setAliciIsim(rs.getString("aliciisim"));
				fatura.setTarih(rs.getString("tarih"));
				fatura.setSaat(rs.getString("saat"));
				fatura.setTur(rs.getString("tur"));
				fatura.setOlusturan(rs.getString("olusturan"));
				fatura.setId(rs.getString("id"));
				Faturalar.add(fatura);
			}
			
		}
		c.close();
		st.close();
		return Faturalar;
	}
	public void FaturaVerileriniGuncelle(E_Fatura fatura) throws SQLException{
		String sqlUpdate = "UPDATE fatura SET GonderenIsým = '"+fatura.getgonderenIsim()+"',aliciisim = '"+fatura.getAliciIsim()+"',tarih = '"+fatura.getTarih()+"', saat= '"+fatura.getSaat()+"',tur = '"+fatura.getTur()+"',olusturan = '"+fatura.getOlusturan()+"' WHERE id = '"+fatura.getId()+"'";
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		PreparedStatement pstmt = conn.prepareStatement(sqlUpdate); 
		int sonuc = pstmt.executeUpdate();
		System.out.println(sonuc);
		conn.close();
		pstmt.close();
	}
	
	public ArrayList<E_Fatura> MalHizmetOkuma(Member mmbr) throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT * from hizmetmalzeme");
		ArrayList<E_Fatura> faturalar = new ArrayList<>(); 
		
		while(rs.next()){
			faturalar.clear();
			for(int i = 0;i < mmbr.getFaturalar().size();i++){
				
				if(rs.getString("tutanakid").equals(String.valueOf(mmbr.getFaturalar().get(i).getId()))){
					MalzemeHizmet malzemeHizmet = new MalzemeHizmet();
					malzemeHizmet.setMiktar(rs.getFloat("miktar"));
					malzemeHizmet.setBrmfiyat(rs.getFloat("brmfiyat"));
					malzemeHizmet.setKdvOraný(rs.getFloat("kdvOrani"));
					malzemeHizmet.setDigerVergiler(rs.getFloat("digerVergiler"));
					malzemeHizmet.setAciklama(rs.getString("aciklama"));
					malzemeHizmet.setTutanakid(rs.getString("tutanakid"));
					malzemeHizmet.setTutar(rs.getFloat("tutar"));
					malzemeHizmet.setId(rs.getString("id"));
					mmbr.getFaturalar().get(i).addMalzemeHizmet(malzemeHizmet);
				}
			}
			
		}
		c.close();
		st.close();
		return mmbr.getFaturalar();
	}
	
	public void MalHizmetYazma(ArrayList<MalzemeHizmet> malzemeHizmetler)throws SQLException{
		
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		Statement st = c.createStatement();
		for(int i=0;i<malzemeHizmetler.size();i++){
			String query = "INSERT INTO hizmetmalzeme (miktar,brmfiyat,kdvOrani,digerVergiler,tutar,aciklama,tutanakid) VALUES ('"+malzemeHizmetler.get(i).getMiktar()+"','"+malzemeHizmetler.get(i).getBrmfiyat()+"','"+malzemeHizmetler.get(i).getKdvOraný()+"','"+malzemeHizmetler.get(i).getDigerVergiler()+"','"+malzemeHizmetler.get(i).getTutar()+"','"+malzemeHizmetler.get(i).getAciklama()+"','"+malzemeHizmetler.get(i).getTutanakid()+"')";
			int sonuc = st.executeUpdate(query);
			System.out.println(sonuc);
		}
		
		c.close();
		st.close();
		
	}
	public void MalzemHizmetGuncelleme(MalzemeHizmet malzemeHizmet)throws SQLException {
		String sqlUpdate = "UPDATE hizmetmalzeme SET miktar = '"+malzemeHizmet.getMiktar()+"',brmfiyat = '"+malzemeHizmet.getBrmfiyat()+"',kdvOrani = '"+malzemeHizmet.getKdvOraný()+"', digerVergiler= '"+malzemeHizmet.getDigerVergiler()+"',tutar = '"+malzemeHizmet.getTutar()+"',aciklama = '"+malzemeHizmet.getAciklama()+"', tutanakid= '"+malzemeHizmet.getTutanakid()+"' WHERE id = '"+malzemeHizmet.getId()+"' ";
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		PreparedStatement pstmt = conn.prepareStatement(sqlUpdate); 
		int sonuc = pstmt.executeUpdate();
		System.out.println(sonuc);
		conn.close();
		pstmt.close();	
	}
	
	public void MalHizmetSilme(MalzemeHizmet malzemeHizmet)throws SQLException {
		String sqldelete = "DELETE FROM hizmetmalzeme WHERE id = '"+malzemeHizmet.getId()+"' ";
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		Statement pstmt = conn.createStatement(); 
		int sonuc = pstmt.executeUpdate(sqldelete);
		System.out.println(sonuc);
		conn.close();
		pstmt.close();	
	}
	public void MailYazma(Mail mailler)throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		Statement st = c.createStatement();
		String query = "INSERT INTO mail (alici,gonderen,olusturan,tarih,saat,tur,icerik,baslik) VALUES ('"+mailler.getAliciIsim()+"','"+mailler.getgonderenIsim()+"','"+mailler.getOlusturan()+"','"+mailler.getTarih()+"','"+mailler.getSaat()+"','"+mailler.getTur()+"','"+mailler.getIcerik()+"','"+mailler.getBaslik()+"')";
		int sonuc = st.executeUpdate(query);
		System.out.println(sonuc);
		c.close();
		st.close();
	}
	public ArrayList<Mail> MailOkuma(Member mmbr, ArrayList<Mail> mailler)throws SQLException {
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT * from mail");
		mailler.clear();
		while(rs.next()){
			if(rs.getString("olusturan").equals(String.valueOf(mmbr.getId()))){
				Mail mail = new Mail();
				mail.setAliciIsim(rs.getString("alici"));
				mail.setBaslik(rs.getString("baslik"));
				mail.setgonderenIsim(rs.getString("gonderen"));
				mail.setIcerik(rs.getString("icerik"));
				mail.setId(rs.getString("id"));
				mail.setOlusturan(rs.getString("olusturan"));
				mail.setSaat(rs.getString("saat"));
				mail.setTarih(rs.getString("tarih"));
				mailler.add(mail);
			}
			
		}
		c.close();
		st.close();
		return mailler;
	}
	public void MailGuncelle(Mail mail)throws SQLException {
		String sqlUpdate = "UPDATE mail SET gonderen = '"+mail.getgonderenIsim()+"',alici = '"+mail.getAliciIsim()+"',tarih = '"+mail.getTarih()+"', saat= '"+mail.getSaat()+"',tur = '"+mail.getTur()+"',olusturan = '"+mail.getOlusturan()+"',baslik = '"+mail.getBaslik()+"', icerik = '"+mail.getIcerik()+"' WHERE id = '"+mail.getId()+"'";
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/kullanýcý?user=root&password=ucanayak2002");
		PreparedStatement pstmt = conn.prepareStatement(sqlUpdate); 
		int sonuc = pstmt.executeUpdate();
		System.out.println(sonuc);
		conn.close();
		pstmt.close();
	}
	
}
	
