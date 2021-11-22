package entity;

public class Phong {
	protected int maphong;
	protected String loaiphong;
	protected double giathuephong;
	protected boolean tinhtrang;
	@Override
	public String toString() {
		return "Phong [maphong=" + maphong + ", loaiphong=" + loaiphong + ", giathuephong=" + giathuephong
				+ ", tinhtrang=" + tinhtrang + "]";
	}
	public Phong() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Phong(int maphong) {
		super();
		this.maphong = maphong;
	}
	//Constructor theo SQL
	public Phong( String loaiphong, double giathuephong, boolean tinhtrang) {
		super();
		
		this.loaiphong = loaiphong;
		this.giathuephong = giathuephong;
		this.tinhtrang = tinhtrang;
	}
	public Phong(int maphong, String loaiphong, double giathuephong, boolean tinhtrang) {
		super();
		this.maphong = maphong;
		this.loaiphong = loaiphong;
		this.giathuephong = giathuephong;
		this.tinhtrang = tinhtrang;
	}
	public int getMaphong() {
		return maphong;
	}
	public void setMaphong(int maphong) {
		this.maphong = maphong;
	}
	public String getLoaiphong() {
		return loaiphong;
	}
	public void setLoaiphong(String loaiphong) {
		this.loaiphong = loaiphong;
	}
	public double getGiathuephong() {
		return giathuephong;
	}
	public void setGiathuephong(double giathuephong) {
		this.giathuephong = giathuephong;
	}
	public boolean isTinhtrang() {
		return tinhtrang;
	}
	public void setTinhtrang(boolean tinhtrang) {
		this.tinhtrang = tinhtrang;
	}
	

}
