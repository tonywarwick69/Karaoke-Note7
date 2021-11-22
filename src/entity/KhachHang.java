package entity;

import java.sql.Date;


public class KhachHang {
	protected int makh,cmnd,tuoi;
	protected boolean gioitinh;
	protected String tenkh;
	protected Date ngaysinh;
	protected int sdt;
	
	@Override
	public String toString() {
		return "KhachHang [makh=" + makh + ", cmnd=" + cmnd + ", tuoi=" + tuoi + ", gioitinh=" + gioitinh + ", tenkh="
				+ tenkh + ", ngaysinh=" + ngaysinh + ", sdt=" + sdt + "]";
	}

	public int getSdt() {
		return sdt;
	}

	public void setSdt(int sdt) {
		this.sdt = sdt;
	}



	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public KhachHang(String tenkh,int cmnd, int tuoi, boolean gioitinh, Date ngaysinh,int sdt) {
		super();
		this.cmnd = cmnd;
		this.tuoi = tuoi;
		this.gioitinh = gioitinh;
		this.tenkh = tenkh;
		this.ngaysinh = ngaysinh;
		this.sdt=sdt;
	}

	public KhachHang(int makh,String tenkh, int cmnd, int tuoi, boolean gioitinh, Date ngaysinh,int sdt) {
		super();
		this.makh = makh;
		this.cmnd = cmnd;
		this.tuoi = tuoi;
		this.gioitinh = gioitinh;
		this.tenkh = tenkh;
		this.ngaysinh = ngaysinh;
		this.sdt=sdt;
	}

	public int getMakh() {
		return makh;
	}

	public void setMakh(int makh) {
		     this.makh = makh;
	}

	public int getCmnd() {
		return cmnd;
	}

	public void setCmnd(int cmnd) {
		this.cmnd = cmnd;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public boolean isGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(boolean gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getTenkh() {
		return tenkh;
	}

	public void setTenkh(String tenkh) {
		     this.tenkh = tenkh;
		
	}

	public Date getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

}