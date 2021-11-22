package entity;

import java.sql.Date;
import java.sql.Time;



public class CaTruc {
	private int maca;
	private String loaica;
	private Time thoigianlam;
	@Override
	public String toString() {
		return "CaTruc [maca=" + maca + ", loaica=" + loaica + ", thoigianlam=" + thoigianlam + "]";
	}
	public CaTruc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CaTruc(int maca, String loaica, Time thoigianlam) {
		super();
		this.maca = maca;
		this.loaica = loaica;
		this.thoigianlam = thoigianlam;
	}
	public CaTruc(String loaica, Time thoigianlam) {
		super();
		this.loaica = loaica;
		this.thoigianlam = thoigianlam;
	}
	public int getMaca() {
		return maca;
	}
	public void setMaca(int maca) {
		this.maca = maca;
	}
	public String getLoaica() {
		return loaica;
	}
	public void setLoaica(String loaica) {
		this.loaica = loaica;
	}
	public Time getThoigianlam() {
		return thoigianlam;
	}
	public void setThoigianlam(Time thoigianlam) {
		this.thoigianlam = thoigianlam;
	}
	
}

