
public class Urun {
	private String cinsi;
	private int miktar;
	private float fiyat, tutar;
	public Urun(String cinsi, int miktar, float fiyat, float tutar) {
		super();
		this.cinsi = cinsi;
		this.miktar = miktar;
		this.fiyat = fiyat;
		this.tutar = tutar;
	}
	public String getCinsi() {
		return cinsi;
	}
	public void setCinsi(String cinsi) {
		this.cinsi = cinsi;
	}
	public int getMiktar() {
		return miktar;
	}
	public void setMiktar(int miktar) {
		this.miktar = miktar;
	}
	public float getFiyat() {
		return fiyat;
	}
	public void setFiyat(float fiyat) {
		this.fiyat = fiyat;
	}
	public float getTutar() {
		return tutar;
	}
	public void setTutar(float tutar) {
		this.tutar = tutar;
	}
	
	
}
