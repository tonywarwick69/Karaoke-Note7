package entity;

public class TaiKhoan {
	private String tentk;
	private String matkhau;
	private boolean vaitro;
	/**
	 * @param tentk
	 * @param matkhau
	 * @param vaitro
	 */
	public TaiKhoan(String tentk, String matkhau, boolean vaitro) {
		super();
		this.tentk = tentk;
		this.matkhau = matkhau;
		this.vaitro = vaitro;
	}
	/**
	 * @return the vaitro
	 */
	public boolean isVaitro() {
		return vaitro;
	}
	/**
	 * @param vaitro the vaitro to set
	 */
	public void setVaitro(boolean vaitro) {
		this.vaitro = vaitro;
	}
	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TaiKhoan(String tentk, String matkhau) {
		super();
		this.tentk = tentk;
		this.matkhau = matkhau;
	}
	public String getTenTK() {
		return tentk;
	}
	public void setTenTK(String tentk)throws Exception {
		if(tentk!=null)
			this.tentk = tentk;
		else 
			throw new Exception("Tên tài khoản không được để trống");
	}
	public String getMatkhau() {
		return matkhau;
	}
	public void setMatkhau(String matkhau)throws Exception {
		if(matkhau!=null)
			this.matkhau = matkhau;
		else 
			throw new Exception("Mật khẩu không được để trống");
	}
	

}
