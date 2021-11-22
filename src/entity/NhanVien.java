package entity;

import java.sql.Date;

public class NhanVien  {
	private int manv;
	private String tentk;
	private int maca;
	private String tennv;
	private int cmnd;
	private int tuoi;
	private Date ngaysinh;
	private boolean gioitinh;
	private String diachi;
	private double tienluong;
	private int sdt;
	/**
	 * @return the manv
	 */
	public int getManv() {
		return manv;
	}
	/**
	 * @param manv the manv to set
	 */
	public void setManv(int manv) throws Exception {
		if(manv>0)	
			this.manv = manv;
		else
			throw new Exception("Mã nhân viên không được rỗng và phải > 0");
	}
	/**
	 * @return the tentk
	 */
	public String getTentk() {
		return tentk;
	}
	/**
	 * @param tentk the tentk to set
	 */
	public void setTentk(String tentk) throws Exception {
		if(tentk!=null)
			this.tentk = tentk;
		else
			throw new Exception("Tên tài khoản không được để trống");
	}
	/**
	 * @return the maca
	 */
	public int getMaca() {
		return maca;
	}
	/**
	 * @param maca the maca to set
	 */
	public void setMaca(int maca) {
		this.maca = maca;
	}
	/**
	 * @return the tennv
	 */
	public String getTennv() {
		return tennv;
	}
	/**
	 * @param tennv the tennv to set
	 */
	public void setTennv(String tennv) throws Exception{
		if(tennv!=null)	
			this.tennv = tennv;
		else
			throw new Exception("Tên nhân viên không được để trống");
	}
	/**
	 * @return the cmnd
	 */
	public int getCmnd() {
		return cmnd;
	}
	/**
	 * @param cmnd the cmnd to set
	 */
	public void setCmnd(int cmnd) {
		this.cmnd = cmnd;
	}
	/**
	 * @return the tuoi
	 */
	public int getTuoi() {
		return tuoi;
	}
	/**
	 * @param tuoi the tuoi to set
	 */
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	/**
	 * @return the ngaysinh
	 */
	public Date getNgaysinh() {
		return ngaysinh;
	}
	/**
	 * @param ngaysinh the ngaysinh to set
	 */
	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	/**
	 * @return the gioitinh
	 */
	public boolean isGioitinh() {
		return gioitinh;
	}
	/**
	 * @param gioitinh the gioitinh to set
	 */
	public void setGioitinh(boolean gioitinh) {
		this.gioitinh = gioitinh;
	}
	/**
	 * @return the diachi
	 */
	public String getDiachi() {
		return diachi;
	}
	/**
	 * @param diachi the diachi to set
	 */
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	/**
	 * @return the tienluong
	 */
	public double getTienluong() {
		return tienluong;
	}
	/**
	 * @param tienluong the tienluong to set
	 */
	public void setTienluong(double tienluong) {
		this.tienluong = tienluong;
	}
	/**
	 * @return the sdt
	 */
	public int getSdt() {
		return sdt;
	}
	/**
	 * @param sdt the sdt to set
	 */
	public void setSdt(int sdt) {
		this.sdt = sdt;
	}
	public NhanVien(int manv, String tentk, int maca, String tennv, int cmnd, int tuoi, Date ngaysinh,
			boolean gioitinh, String diachi, double tienluong, int sdt) {
		super();
		this.manv = manv;
		this.tentk = tentk;
		this.maca = maca;
		this.tennv = tennv;
		this.cmnd = cmnd;
		this.tuoi = tuoi;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.diachi = diachi;
		this.tienluong = tienluong;
		this.sdt = sdt;
	}
	public NhanVien( String tentk, int maca, String tennv, int cmnd, int tuoi, Date ngaysinh,
			boolean gioitinh, String diachi, double tienluong, int sdt) {
		super();
		
		this.tentk = tentk;
		this.maca = maca;
		this.tennv = tennv;
		this.cmnd = cmnd;
		this.tuoi = tuoi;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.diachi = diachi;
		this.tienluong = tienluong;
		this.sdt = sdt;
	}
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
