
public class Member extends User{
	private int authority;
	
    public Member() {
		
	}

	public Member(int id, String name, String surname, String sifre,
			String mail, String il, String ilce, String tel_num, String dgmGun,
			String dgmAy, String dgmYil,String nickname) {
		super(id, name, surname, sifre, mail, il, ilce, tel_num, dgmGun, dgmAy, dgmYil,nickname);
		// TODO Auto-generated constructor stub
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public void BelgeOlustur() {
		
	}

}
