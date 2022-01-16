import java.util.ArrayList;

	
public class E_Irsaliye extends Belge{
	
	
	private String SevkTarihi;
	ArrayList<Mal> Mallar = new ArrayList<>();
	
	
	public E_Irsaliye() {
		super();
		super.setTur("Ýrsaliye");
	}
	public E_Irsaliye(String gonderenIsim, String aliciIsim, String tarih, String saat, String tur, String olusturan) {
		super(gonderenIsim, aliciIsim, tarih, saat, tur, olusturan);
		super.setTur("irsaliye");
	}
	public String getSevkTarihi() {
		return SevkTarihi;
	}
	public void setSevkTarihi(String sevkTarihi) {
		SevkTarihi = sevkTarihi;
	}
	public ArrayList<Mal> getMallar() {
		return Mallar;
	}
	public void setMallar(ArrayList<Mal> mallar) {
		Mallar = mallar;
	}
	public void AddMal(Mal mal) {
		Mallar.add(mal);
	}
	
	
}
