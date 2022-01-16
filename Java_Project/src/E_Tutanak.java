
public class E_Tutanak extends Belge{
	private String icerik, baslik, aliciIsim2, aliciIsim3; 

	public E_Tutanak() {
		super();
		super.setTur("Tutanak");
	}

	public E_Tutanak(String gonderenIsim, String aliciIsim, String tarih,
			String saat, String tur, String olusturan) {
		super(gonderenIsim, aliciIsim, tarih, saat, tur, olusturan);
		super.setTur("Tutanak");
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

	public String getAliciIsim2() {
		return aliciIsim2;
	}

	public void setAliciIsim2(String aliciIsim2) {
		this.aliciIsim2 = aliciIsim2;
	}

	public String getAliciIsim3() {
		return aliciIsim3;
	}

	public void setAliciIsim3(String aliciIsim3) {
		this.aliciIsim3 = aliciIsim3;
	}
	
	
	
	
}
