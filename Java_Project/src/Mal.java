public class Mal{
		private String MalCinsi,irsaliyeid,id;
		private int adet;
		private float brmFiyat,tutar;
		
		public Mal(){
			
		}
		
		public Mal(String malCinsi, String irsaliyeid, int adet, float brmFiyat, float tutar) {
			super();
			MalCinsi = malCinsi;
			this.irsaliyeid = irsaliyeid;
			this.adet = adet;
			this.brmFiyat = brmFiyat;
			this.tutar = tutar;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getIrsaliyeid() {
			return irsaliyeid;
		}

		public void setIrsaliyeid(String irsaliyeid) {
			this.irsaliyeid = irsaliyeid;
		}

		public String getMalCinsi() {
			return MalCinsi;
		}
		public void setMalCinsi(String malCinsi) {
			MalCinsi = malCinsi;
		}
		public int getAdet() {
			return adet;
		}
		public void setAdet(int adet) {
			this.adet = adet;
		}
		public float getBrmFiyat() {
			return brmFiyat;
		}
		public void setBrmFiyat(float brmFiyat) {
			this.brmFiyat = brmFiyat;
		}
		public float getTutar() {
			return tutar;
		}
		public void setTutar(float tutar) {
			this.tutar = tutar;
		}
	}