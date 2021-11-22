package entity;

import java.sql.Time;
import java.sql.Date;

public class DatPhong extends Phong{
	private Time thoigiandatphong;
	private Date ngaydatphong;
	private int makh;
	
	protected int mahddv;
	protected double tiendichvu;
	private int mahang;
	

	
	public int getMakh() {
		return makh;
	}
	public void setMakh(int makh) {
		this.makh = makh;
	}
	@Override
	public String toString() {
		return "DatPhong [thoigiandatphong=" + thoigiandatphong + ", ngaydatphong=" + ngaydatphong
				+ ", maphong=" + maphong + ", loaiphong=" + loaiphong + ", giathuephong=" + giathuephong
				+ ", tinhtrang=" + tinhtrang + "]";
	}
	public DatPhong() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DatPhong(int maphong) {
		super(maphong);
		
	}



	public DatPhong(int maphong,int makh, String loaiphong, double giathuephong, boolean tinhtrang,
			Time thoigiandatphong, Date ngaydatphong) {
		super(maphong, loaiphong, giathuephong, tinhtrang);
		this.makh= makh;
		this.thoigiandatphong = thoigiandatphong;
		this.ngaydatphong = ngaydatphong;
	}
	public Time getThoigiandatphong() {
		return thoigiandatphong;
	}
	public void setThoigiandatphong(Time thoigiandatphong) {
		this.thoigiandatphong = thoigiandatphong;
	}
	public Date getNgaydatphong() {
		return ngaydatphong;
	}
	public void setNgaydatphong(Date ngaydatphong) {
		this.ngaydatphong = ngaydatphong;
	}
	

}
