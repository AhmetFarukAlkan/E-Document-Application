import java.util.ArrayList;


public class User {
	//ArrayList<Belge> belgeList = new ArrayList<>();
	ArrayList<E_Irsaliye> irsaliyeler = new ArrayList<>();
	ArrayList<E_Irsaliye> alinanirsaliyeler = new ArrayList<>();
	ArrayList<E_Tutanak> tutanaklar = new ArrayList<>();
	ArrayList<E_Tutanak> alinantutanaklar = new ArrayList<>();
	ArrayList<E_Fatura> Faturalar = new ArrayList<>();
	ArrayList<E_Fatura> alinanFaturalar = new ArrayList<>();
	ArrayList<Mail> mailler = new ArrayList<>();
	ArrayList<Mail> alinanMailler = new ArrayList<>();
	
	protected int id;
	private String name, surname, sifre ,mail,il,ilce,nickname;
	protected String tel_num;
	private String dgmGun, dgmAy , dgmYil;
	
	public void addFile(E_Irsaliye b){
		this.irsaliyeler.add(b);
	}
	public void addGelenFile(E_Irsaliye b){
		this.alinanirsaliyeler.add(b);
	}
	
	public void addTutanak(E_Tutanak t) {
		this.tutanaklar.add(t);
	}
	public void addGelenTutanak(E_Tutanak t) {
		this.alinantutanaklar.add(t);
	}
	
	public void addFatura(E_Fatura f){
		this.Faturalar.add(f);
	}
	public void addGelenFatura(E_Fatura f){
		this.alinanFaturalar.add(f);
	}
	public void addMail(Mail m){
		this.mailler.add(m);
	}
	public void addGelenMail(Mail m){
		this.alinanMailler.add(m);
	}
	
	
	public User (){
		
	}
	public User(int id, String name, String surname, String sifre, String mail,
			String il, String ilce, String tel_num, String dgmGun,
			String dgmAy, String dgmYil,String nickname) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.sifre = sifre;
		this.mail = mail;
		this.il = il;
		this.ilce = ilce;
		this.tel_num = tel_num;
		this.dgmGun = dgmGun;
		this.dgmAy = dgmAy;
		this.dgmYil = dgmYil;
		this.nickname = nickname;
	}
	
	
	
	public ArrayList<Mail> getMailler() {
		return mailler;
	}
	public void setMailler(ArrayList<Mail> mailler) {
		this.mailler = mailler;
	}
	public ArrayList<Mail> getAlinanMailler() {
		return alinanMailler;
	}
	public void setAlinanMailler(ArrayList<Mail> alinanMailler) {
		this.alinanMailler = alinanMailler;
	}
	public ArrayList<E_Fatura> getFaturalar() {
		return Faturalar;
	}
	public void setFaturalar(ArrayList<E_Fatura> faturalar) {
		Faturalar = faturalar;
	}
	public ArrayList<E_Tutanak> getTutanaklar() {
		return tutanaklar;
	}
	public void setTutanaklar(ArrayList<E_Tutanak> tutanaklar) {
		this.tutanaklar = tutanaklar;
	}
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public ArrayList<E_Irsaliye> getIrsaliyeler() {
		return irsaliyeler;
	}

	public void setIrsaliyeler(ArrayList<E_Irsaliye> irsaliyeler) {
		this.irsaliyeler = irsaliyeler;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/*public ArrayList<Belge> getBelgeList() {
		return belgeList;
	}

	public void setBelgeList(ArrayList<Belge> belgeList) {
		this.belgeList = belgeList;
	}*/
	
	public ArrayList<E_Irsaliye> getAlinanirsaliyeler() {
		return alinanirsaliyeler;
	}
	public void setAlinanirsaliyeler(ArrayList<E_Irsaliye> alinanirsaliyeler) {
		this.alinanirsaliyeler = alinanirsaliyeler;
	}
	public ArrayList<E_Tutanak> getAlinantutanaklar() {
		return alinantutanaklar;
	}
	public void setAlinantutanaklar(ArrayList<E_Tutanak> alinantutanaklar) {
		this.alinantutanaklar = alinantutanaklar;
	}
	public ArrayList<E_Fatura> getAlinanFaturalar() {
		return alinanFaturalar;
	}
	public void setAlinanFaturalar(ArrayList<E_Fatura> alinanFaturalar) {
		this.alinanFaturalar = alinanFaturalar;
	}
	public String getIl() {
		return il;
	}

	public void setIl(String il) {
		this.il = il;
	}

	public String getIlce() {
		return ilce;
	}

	public void setIlce(String ilce) {
		this.ilce = ilce;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getSifre() {
		return sifre;
	}
	public String getDgmGun() {
		return dgmGun;
	}

	public void setDgmGun(String dgmGun) {
		this.dgmGun = dgmGun;
	}

	public String getDgmAy() {
		return dgmAy;
	}

	public void setDgmAy(String dgmAy) {
		this.dgmAy = dgmAy;
	}

	public String getDgmYil() {
		return dgmYil;
	}

	public void setDgmYil(String dgmYil) {
		this.dgmYil = dgmYil;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	public String getTel_num() {
		return tel_num;
	}
	public void setTel_num(String tel_num) {
		this.tel_num = tel_num;
	}
	
	public void BelgeOlustur() {
		
	}
}
