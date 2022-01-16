import java.util.ArrayList;


public class E_Fatura extends Belge{
	ArrayList<MalzemeHizmet> MalzemeHizmetler = new ArrayList<>();
	
	public void addMalzemeHizmet(MalzemeHizmet mz) {
		this.MalzemeHizmetler.add(mz);
	}
	
	public E_Fatura() {
		super();
		super.setTur("Fatura");
	}
	public E_Fatura(String gonderenIsim, String aliciIsim, String tarih,
			String saat, String tur, String olusturan) {
		super(gonderenIsim, aliciIsim, tarih, tur, saat, olusturan);
		super.setTur("Fatura");
	}
	public ArrayList<MalzemeHizmet> getMalzemeHizmetler() {
		return MalzemeHizmetler;
	}
	public void setMalzemeHizmetler(ArrayList<MalzemeHizmet> malzemeHizmetler) {
		MalzemeHizmetler = malzemeHizmetler;
	}
	
}	

