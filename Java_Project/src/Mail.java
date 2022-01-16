
public class Mail extends Belge{
	private String icerik,baslik;
	public Mail() {
		super();
		super.setTur("Mail");
	}
	
	public Mail(String gonderenIsim, String aliciIsim, String tarih,
			String saat, String tur, String olusturan) {
		super(gonderenIsim, aliciIsim, tarih, saat, tur, olusturan);
		super.setTur("Mail");
	}
	
	public String getIcerik() {
		return icerik;
	}

	public void setIcerik(String icerik) {
		this.icerik = icerik;
	}

	public String getBaslik() {
		return baslik;
	}

	public void setBaslik(String baslik) {
		this.baslik = baslik;
	}
	
	
}
