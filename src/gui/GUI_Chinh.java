package gui;

import javax.swing.*;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import connectDB.ConnectDB;
import entity.DatPhong;
import entity.KhachHang;
import entity.Phong;
import dao.DatPhong_DAO;
import dao.KhachHang_Dao;
import dao.Phong_DAO;
import gui.GUI_KhachHang;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;


public class GUI_Chinh extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel labell;
	private JTable tablePhong;
	private DefaultTableModel modelPhong;
	private JButton btnQuanLyDatPhong;
	private JTextField txtMakh;
	private JTextField txtTenkh;
	private JTextField txtCMND;
	private JTextField txtTuoi;
	private JTextField txtGioitinh;
	private Phong_DAO p_dao;
	private KhachHang_Dao kh_dao;
	private JTextField txtNgaysinh;
	private JTextField txtSDT;
	private DatPhong_DAO dp_dao;
	private JLabel lblTenTK;
	private JLabel lblVaitro;
	private JButton btnQuanLyPhanCaLam;
	private JButton btnQuanLyNhanVien;
	public void sendlogin(String tentk,boolean vaitro) {
		//vaitro = 0 = false => "Nhân viên" vaitro = 1 = true => "Nhân viên quản lý"
		lblTenTK.setText(tentk);
		lblVaitro.setText(vaitro == false ? "Nhân viên" :"Nhân viên quản lý");
		if(vaitro==false) {
			btnQuanLyPhanCaLam.setVisible(false);
			btnQuanLyNhanVien.setVisible(false);
		} else {
			btnQuanLyPhanCaLam.setVisible(true);
			btnQuanLyNhanVien.setVisible(true);
		}
		
		
	}
	public GUI_Chinh() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		kh_dao=new KhachHang_Dao();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Giao diện chính");
		
		setResizable(false);
		setSize(1500, 774);
		getContentPane().setLayout(null);
		
		JPanel pnChinh = new JPanel();
		pnChinh.setBackground(new Color(127, 255, 212));
		pnChinh.setBounds(0, 0, 1920, 1017);
		getContentPane().add(pnChinh);
		pnChinh.setLayout(null);

		JPanel pnChucNang = new JPanel();
		pnChucNang.setBounds(0, 0, 1495, 169);
		pnChucNang.setBackground(new Color(127, 255, 212));
		pnChinh.add(pnChucNang);
		
		JButton btnQuanLyDatPhong = new JButton(" Quản lý đặt phòng");
		btnQuanLyDatPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnQuanLyDatPhong.setBackground(new Color(255, 255, 0));
		Image img_phong = new ImageIcon(this.getClass().getResource("/image/home.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		btnQuanLyDatPhong.setIcon(new ImageIcon(img_phong));
		btnQuanLyDatPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_QuanLyDatPhong qldp = new GUI_QuanLyDatPhong();
				qldp.setVisible(true);
				dispose();
			}
		});
		btnQuanLyDatPhong.setBounds(10, 108, 202, 40);
		btnQuanLyDatPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pnChucNang.setLayout(null);
		pnChucNang.add(btnQuanLyDatPhong);
		
		JLabel lblNewLabel = new JLabel("KARAOKE NOTE7");
		lblNewLabel.setForeground(new Color(255, 140, 0));
		lblNewLabel.setBounds(363, 11, 619, 49);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 36));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pnChucNang.add(lblNewLabel);
		
		JButton btnQuanLyPhong = new JButton("Quản Lý phòng");
		btnQuanLyPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnQuanLyPhong.setBackground(new Color(255, 255, 0));
		btnQuanLyPhong.setForeground(new Color(0, 0, 0));
		Image img_qlphong = new ImageIcon(this.getClass().getResource("/image/home.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		btnQuanLyPhong.setIcon(new ImageIcon(img_qlphong));
		btnQuanLyPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_QLPhong qlPhong=new GUI_QLPhong();
				qlPhong.setVisible(true);
				dispose();
			}
		});
		btnQuanLyPhong.setBounds(245, 108, 173, 40);
		pnChucNang.add(btnQuanLyPhong);
		
		JButton btnQuanLyKhachHang = new JButton("Quản lý khách hàng");
		btnQuanLyKhachHang.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnQuanLyKhachHang.setBackground(new Color(255, 255, 0));
		Image img_khach = new ImageIcon(this.getClass().getResource("/image/user.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		btnQuanLyKhachHang.setIcon(new ImageIcon(img_khach));
		btnQuanLyKhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_KhachHang guikh=new GUI_KhachHang();
				guikh.setVisible(true);
				dispose();
				
			}
		});
		btnQuanLyKhachHang.setBounds(444, 108, 202, 40);
		pnChucNang.add(btnQuanLyKhachHang);
		
		JButton btnQuanLyHang = new JButton("Quản lý hàng");
		btnQuanLyHang.setFont(new Font("Tahoma", Font.BOLD, 13));
		Image img_hang = new ImageIcon(this.getClass().getResource("/image/food.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		btnQuanLyHang.setIcon(new ImageIcon(img_hang));
		btnQuanLyHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnQuanLyHang.setBackground(new Color(255, 255, 0));
		btnQuanLyHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_QLHang window=new GUI_QLHang();
				window.setVisible(true);
				dispose();
			}
		});
		btnQuanLyHang.setBounds(670, 108, 160, 40);
		pnChucNang.add(btnQuanLyHang);
		
		 btnQuanLyNhanVien = new JButton("Quản lý nhân viên");
		Image img_nv = new ImageIcon(this.getClass().getResource("/image/user4.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		btnQuanLyNhanVien.setIcon(new ImageIcon(img_nv));
		btnQuanLyNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnQuanLyNhanVien.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnQuanLyNhanVien.setBackground(new Color(255, 255, 0));
		btnQuanLyNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_NhanVien gdnv =new GUI_NhanVien();
				gdnv.setVisible(true);
				dispose();
			}
		});
		btnQuanLyNhanVien.setBounds(1264, 108, 193, 40);
		pnChucNang.add(btnQuanLyNhanVien);
		
		 btnQuanLyPhanCaLam = new JButton("Quản lý phân ca");
		Image img_cl = new ImageIcon(this.getClass().getResource("/image/users.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		btnQuanLyPhanCaLam.setIcon(new ImageIcon(img_cl));
		btnQuanLyPhanCaLam.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnQuanLyPhanCaLam.setBackground(new Color(255, 255, 0));
		btnQuanLyPhanCaLam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_CaTruc catruc= new GUI_CaTruc();
				catruc.setVisible(true);
				dispose();
			}
		});
		btnQuanLyPhanCaLam.setBounds(1062, 108, 178, 40);
		pnChucNang.add(btnQuanLyPhanCaLam);
		
		
		JButton btnQuanLyHoaDon = new JButton("Quản lý hóa đơn");
		Image img_hd = new ImageIcon(this.getClass().getResource("/image/pencil.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		btnQuanLyHoaDon.setIcon(new ImageIcon(img_hd));
		btnQuanLyHoaDon.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnQuanLyHoaDon.setBackground(new Color(255, 255, 0));
		btnQuanLyHoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_QLHoaDon qlhd = new GUI_QLHoaDon();
				qlhd.setVisible(true);
				dispose();
			}
		});
		btnQuanLyHoaDon.setBounds(854, 108, 178, 40);
		pnChucNang.add(btnQuanLyHoaDon);
		
		JLabel lblNewLabel_4 = new JLabel("Xin chào :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(43, 25, 91, 28);
		pnChucNang.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Chức vụ:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(43, 63, 91, 35);
		pnChucNang.add(lblNewLabel_5);
		
		 lblTenTK = new JLabel("");
		lblTenTK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenTK.setBounds(172, 25, 181, 28);
		pnChucNang.add(lblTenTK);
		
		 lblVaitro = new JLabel("");
		lblVaitro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVaitro.setBounds(172, 66, 173, 28);
		pnChucNang.add(lblVaitro);
		
		JButton btnNewButton = new JButton("Đăng xuất");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Form_DangNhap fdn = new Form_DangNhap();
				fdn.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(1264, 34, 193, 40);
		pnChucNang.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(127, 255, 212));
		panel.setBounds(0, 168, 910, 56);
		pnChinh.add(panel);
		panel.setLayout(null);
		
		JLabel lblDanhSachPhong = new JLabel("Danh sách phòng");
		lblDanhSachPhong.setForeground(new Color(0, 0, 128));
		lblDanhSachPhong.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSachPhong.setBounds(136, 12, 322, 32);
		lblDanhSachPhong.setFont(new Font("Tahoma", Font.BOLD, 26));
		panel.add(lblDanhSachPhong);
		//
		p_dao=new Phong_DAO();
		
		JComboBox cboTimMaphong = new JComboBox();
		cboTimMaphong.setBounds(673, 11, 185, 33);
		panel.add(cboTimMaphong);
		cboTimMaphong.setEditable(true);
		ArrayList<Phong> listPhong= p_dao.getTatCaPhong();
		//Tao list combox phòng
		for(Phong p: listPhong) {
			cboTimMaphong.addItem(p.getMaphong());
		}
		cboTimMaphong.setSelectedItem("");
		AutoCompleteDecorator.decorate(cboTimMaphong);
		JButton btnTimMaPhong = new JButton("Tìm kiếm");
		Image img_tim1 = new ImageIcon(this.getClass().getResource("/image/search.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		btnTimMaPhong.setIcon(new ImageIcon(img_tim1));
		btnTimMaPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTimMaPhong.setBackground(new Color(255, 255, 0));
		btnTimMaPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o= e.getSource();
				if(o.equals(btnTimMaPhong)) {
					XoaHetDuLieuTable();
					List<Phong> listTimKiemTheoMa= p_dao.getTimKiemTheoMa(Integer.parseInt(cboTimMaphong.getSelectedItem().toString()));
					cboTimMaphong.setSelectedItem("");
					for(Phong p: listTimKiemTheoMa) {
						modelPhong.addRow(new Object[] {
								p.getMaphong(), p.getLoaiphong(), p.getGiathuephong(), p.isTinhtrang()==false ? "Nữ" : "Nam"
						});
					}
				}
					
			}
		});
		btnTimMaPhong.setBounds(468, 11, 195, 34);
		panel.add(btnTimMaPhong);
		
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(0, 223, 910, 521);
		pnChinh.add(scrollPane1);
	
		tablePhong = new JTable();
		tablePhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null,"Phòng "+tablePhong.getModel().getValueAt(tablePhong.getSelectedRow(),0).toString()+" vừa được chọn.");
				/*
				int row = tablePhong.getSelectedRow();
				modelPhong.getValueAt(row, 0).toString();
				modelPhong.getValueAt(row, 1).toString();
				modelPhong.getValueAt(row, 2).toString();
				modelPhong.getValueAt(row, 3).toString() ;
				*/
			}
		});
		tablePhong.setModel(modelPhong = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Số phòng", "Loại phòng", "Giá thuê phòng", "Tình trạng"
			}
		));
		tablePhong.getColumnModel().getColumn(0).setPreferredWidth(15);
		tablePhong.getColumnModel().getColumn(1).setPreferredWidth(150);
		tablePhong.getColumnModel().getColumn(2).setPreferredWidth(150);
		tablePhong.getColumnModel().getColumn(3).setPreferredWidth(15);
		DocDuLieuDBVaoTable();
		scrollPane1.setViewportView(tablePhong);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(127, 255, 212));
		panel_1.setBounds(909, 168, 586, 56);
		pnChinh.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin khách hàng");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setBounds(106, 11, 294, 29);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel_1.add(lblNewLabel_1);
		
		JPanel pnKhachHang = new JPanel();
		pnKhachHang.setBackground(new Color(127, 255, 212));
		pnKhachHang.setBounds(909, 223, 586, 521);
		pnChinh.add(pnKhachHang);
		pnKhachHang.setLayout(null);
		
		
		
		JLabel lblMakh = new JLabel("Mã khách hàng:");
		lblMakh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMakh.setBounds(20, 61, 97, 39);
		pnKhachHang.add(lblMakh);
		
		JLabel lblTenkh = new JLabel("Tên khách hàng:");
		lblTenkh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenkh.setBounds(20, 111, 97, 39);
		pnKhachHang.add(lblTenkh);
		
		JLabel lblCMND = new JLabel("CMND:");
		lblCMND.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCMND.setBounds(20, 161, 97, 39);
		pnKhachHang.add(lblCMND);
		
		JLabel lblTuoi = new JLabel("Tuổi:");
		lblTuoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTuoi.setBounds(20, 211, 97, 39);
		pnKhachHang.add(lblTuoi);
		
		JLabel lblGioitinh = new JLabel("Giới tính:");
		lblGioitinh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGioitinh.setBounds(20, 261, 97, 39);
		pnKhachHang.add(lblGioitinh);
		
		txtMakh = new JTextField();
		txtMakh.setEditable(false);
		txtMakh.setBounds(188, 66, 258, 30);
		pnKhachHang.add(txtMakh);
		txtMakh.setColumns(10);
		
		txtTenkh = new JTextField();
		txtTenkh.setEditable(false);
		txtTenkh.setColumns(10);
		txtTenkh.setBounds(188, 116, 258, 30);
		pnKhachHang.add(txtTenkh);
		
		txtCMND = new JTextField();
		txtCMND.setEditable(false);
		txtCMND.setColumns(10);
		txtCMND.setBounds(188, 166, 258, 30);
		pnKhachHang.add(txtCMND);
		
		txtTuoi = new JTextField();
		txtTuoi.setEditable(false);
		txtTuoi.setColumns(10);
		txtTuoi.setBounds(188, 216, 258, 30);
		pnKhachHang.add(txtTuoi);
		
		txtGioitinh = new JTextField();
		txtGioitinh.setEditable(false);
		txtGioitinh.setColumns(10);
		txtGioitinh.setBounds(188, 266, 258, 30);
		pnKhachHang.add(txtGioitinh);
		dp_dao = new DatPhong_DAO();
		ArrayList<DatPhong> listdp = dp_dao.getTatCaDatPhong(); 
		
		
		JButton btnDatphong = new JButton("Đặt phòng");
		btnDatphong.setFont(new Font("Tahoma", Font.BOLD, 13));
		Image img_dat = new ImageIcon(this.getClass().getResource("/image/bell.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		btnDatphong.setIcon(new ImageIcon(img_dat));
		btnDatphong.setBackground(new Color(255, 255, 0));
		btnDatphong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//tinhtrang=0=false -> "Phòng trống" tinhtrang=1=true -> "Phòng đang sử dụng"
				String check=tablePhong.getModel().getValueAt(tablePhong.getSelectedRow(), 3).toString();
				if(check=="Phòng trống" ) {
					int row = tablePhong.getSelectedRow();
					int maphong = Integer.parseInt(tablePhong.getModel().getValueAt(tablePhong.getSelectedRow(), 0).toString());
					int makh = Integer.parseInt(txtMakh.getText());
					String loai = tablePhong.getModel().getValueAt(tablePhong.getSelectedRow(), 1).toString();
					Double gia = Double.parseDouble(modelPhong.getValueAt(row, 2).toString());
					boolean tinhTrang= true;
					LocalTime thoigiandatlocal= LocalTime.now();
					Time thoigiandat = Time.valueOf( thoigiandatlocal );
					LocalDate ngaydatlocal = LocalDate.now();
					Date ngaydat= Date.valueOf(ngaydatlocal);
					DatPhong dp  = new DatPhong(maphong,makh,loai,gia,tinhTrang,thoigiandat,ngaydat);
					
					if(!dp_dao.create(dp))
						JOptionPane.showMessageDialog(null, "Đặt phòng thất bại hoặc phòng đang sử dụng nên không được đặt");
					else {
						JOptionPane.showMessageDialog(null, "Đặt phòng  thành công");
						//tinhtrang=0=false -> "Phòng trống" tinhtrang=1=true -> "Phòng đang sử dụng"
						Phong p = new Phong();
						p.setMaphong(Integer.parseInt(tablePhong.getModel().getValueAt(tablePhong.getSelectedRow(), 0).toString()));
						p.setLoaiphong(tablePhong.getModel().getValueAt(tablePhong.getSelectedRow(), 1).toString());
						p.setGiathuephong(Double.parseDouble(modelPhong.getValueAt(row, 2).toString()));
						p.setTinhtrang(true);
						p_dao.update(p);
						GUI_QuanLyDatPhong qldp = new GUI_QuanLyDatPhong();
						qldp.setVisible(true);
						dispose();
					}	
			} else {
				JOptionPane.showMessageDialog(pnChinh, "Phòng đang có người sử dụng không cho phép đặt phòng");
			}
		}
		});
		btnDatphong.setBounds(214, 440, 195, 58);
		pnKhachHang.add(btnDatphong);
		
		JComboBox cboTimSDT = new JComboBox();
		cboTimSDT.setBounds(188, 12, 258, 39);
		cboTimSDT.setEditable(true);
		ArrayList<KhachHang> listkh =kh_dao.getTatCaKhachHang();
		//Tạo list combobox khach hang
		for(KhachHang kh:listkh) {
			cboTimSDT.addItem(kh.getSdt());
		}
		cboTimSDT.setSelectedItem("");
		AutoCompleteDecorator.decorate(cboTimSDT);
		pnKhachHang.add(cboTimSDT);
		
		JButton btnTimSDT = new JButton("Tìm kiếm SDT");
		btnTimSDT.setFont(new Font("Tahoma", Font.BOLD, 13));
		Image img_tim2 = new ImageIcon(this.getClass().getResource("/image/search.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		btnTimSDT.setIcon(new ImageIcon(img_tim2));
		btnTimSDT.setBackground(new Color(255, 255, 0));
		btnTimSDT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o= e.getSource();
				if(o.equals(btnTimSDT)) {
					List<KhachHang> listTimsdt= kh_dao.getTimSDT(Integer.parseInt(cboTimSDT.getSelectedItem().toString()));
					cboTimSDT.setSelectedItem("");
					
					for(KhachHang kh: listTimsdt) {
						txtMakh.setText(String.valueOf(kh.getMakh()));
						txtTenkh.setText(kh.getTenkh());
						txtCMND.setText(String.valueOf(kh.getCmnd()));
						txtTuoi.setText(String.valueOf(kh.getTuoi()));
						txtGioitinh.setText(kh.isGioitinh()== false ? "Nữ" : "Nam");
						txtNgaysinh.setText(String.valueOf(kh.getNgaysinh()));
						txtSDT.setText(String.valueOf(kh.getSdt()));
					}
				}
			}
		});
		btnTimSDT.setBounds(10, 11, 164, 39);
		pnKhachHang.add(btnTimSDT);
		
		JLabel lblNewLabel_2 = new JLabel("Ngày sinh");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(20, 332, 72, 14);
		pnKhachHang.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("SĐT");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(20, 386, 46, 14);
		pnKhachHang.add(lblNewLabel_3);
		
		txtNgaysinh = new JTextField();
		txtNgaysinh.setEditable(false);
		txtNgaysinh.setBounds(188, 325, 258, 30);
		pnKhachHang.add(txtNgaysinh);
		txtNgaysinh.setColumns(10);
		
		txtSDT = new JTextField();
		txtSDT.setEditable(false);
		txtSDT.setBounds(188, 380, 258, 30);
		pnKhachHang.add(txtSDT);
		txtSDT.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(127, 255, 212));
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.setBounds(0, 223, 868, 521);
		pnChinh.add(panel_2);
		
		
	
	}
	//tinhtrang=0=false -> " Phòng trống " tinhtrang=1=true -> "Phòng đang sử dụng"
	private void DocDuLieuDBVaoTable() {
		List<Phong> list = p_dao.getTatCaPhong();
		for(Phong p : list) {
			modelPhong.addRow(new Object[] {
					p.getMaphong(), p.getLoaiphong(), p.getGiathuephong(),
					p.isTinhtrang() == false ? "Phòng trống" : "Phòng đang sử dụng"
			});
		}
	}
	private void XoaHetDuLieuTable() {
		DefaultTableModel dtm = (DefaultTableModel) tablePhong.getModel();
		dtm.setRowCount(0);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Chinh frame = new GUI_Chinh();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
}
