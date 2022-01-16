
public class Belge {
	private String gonderenIsim,aliciIsim,tarih,saat,id="0",tur,olusturan;

	public Belge(){
		
	}

	public Belge(String gonderenIsim, String aliciIsim, String tarih, String saat, String tur, String olusturan) {
		super();
		this.gonderenIsim = gonderenIsim;
		this.aliciIsim = aliciIsim;
		this.tarih = tarih;
		this.saat = saat;
		this.tur = tur;
		this.olusturan = olusturan;
	}

		
	
	public String getTur() {
		return tur;
	}

	public String getSaat() {
		return saat;
	}

	public void setSaat(String saat) {
		this.saat = saat;
	}

	public String getOlusturan() {
		return olusturan;
	}

	public void setOlusturan(String olusturan) {
		this.olusturan = olusturan;
	}

	public void setTur(String tur) {
		this.tur = tur;
	}

	public String getgonderenIsim() {
		return gonderenIsim;
	}

	public void setgonderenIsim(String yazarIsim) {
		this.gonderenIsim = yazarIsim;
	}

	public String getAliciIsim() {
		return aliciIsim;
	}

	public void setAliciIsim(String aliciIsim) {
		this.aliciIsim = aliciIsim;
	}

	public String getTarih() {
		return tarih;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

}
