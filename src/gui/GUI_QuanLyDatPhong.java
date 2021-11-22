package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.DatPhong_DAO;
import dao.KhachHang_Dao;
import dao.Phong_DAO;
import entity.DatPhong;
import entity.KhachHang;
import entity.Phong;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import util.DateUtil;
import java.awt.Color;
import com.toedter.components.JSpinField;
import com.toedter.components.JLocaleChooser;

public class GUI_QuanLyDatPhong extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtLoaiphong;
	private JTextField txtGiathue;
	private JTextField txtThoigiandat;
	private DefaultTableModel modelDatphong;
	private JTable tableDatphong;
	private DatPhong_DAO dp_dao;
	private JComboBox cboTinhtrang;
	private JLabel lblTongTienDV;
	private JLabel lblMaHD;
	private JLabel lblMaphong;
	private JLabel lblTienphong;
	private Phong_DAO p_dao;
	private JDateChooser jdateNgaydat;
	private JButton btnSua;
	private KhachHang_Dao kh_dao;
	private JComboBox cboMaKH;
	private JComboBox cboMaphong;
	private JPanel pnBang;
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_QuanLyDatPhong frame = new GUI_QuanLyDatPhong();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void my_update(String mahoadon,String tongtien) {
		lblTongTienDV.setText(tongtien);
		lblMaHD.setText(mahoadon);
	}

	/**
	 * Create the frame.
	 */
	public GUI_QuanLyDatPhong() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		setTitle("Quản lý đặt phòng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1285, 683);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dp_dao = new DatPhong_DAO();
		p_dao = new Phong_DAO();
		ArrayList<Phong> listp= p_dao.getTatCaPhong();
		ArrayList<DatPhong> listdp = dp_dao.getTatCaDatPhong(); 
		
		JPanel pnChucnang = new JPanel();
		pnChucnang.setBounds(0, 0, 1271, 125);
		contentPane.add(pnChucnang);
		pnChucnang.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản lý đặt phòng");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(423, 0, 288, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnChucnang.add(lblNewLabel);
		
		JButton btnQuaylai = new JButton("Quay lại");
		btnQuaylai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_Chinh main = new GUI_Chinh();
				main.setVisible(true);
				dispose();
			}
		});
		btnQuaylai.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnQuaylai.setBounds(10, 68, 127, 37);
		pnChucnang.add(btnQuaylai);
		
		JButton btnThanhtoan = new JButton("Thanh toán");
		btnThanhtoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblMaphong.setText(tableDatphong.getModel().getValueAt(tableDatphong.getSelectedRow(), 0).toString());
				Time thoigiandat = Time.valueOf(tableDatphong.getModel().getValueAt(tableDatphong.getSelectedRow(), 5).toString());
				Time thoigiantra= Time.valueOf(LocalTime.now());
				long diff = thoigiantra.getTime() - thoigiandat.getTime();
				long diffHours = diff / (60 * 60 * 1000) % 24;
				String sogio = String.valueOf(diffHours);
				if(Integer.parseInt(sogio)<0) {
					JOptionPane.showMessageDialog(pnBang, "Giờ trả phòng nhỏ hơn giờ đặt phòng");
				} else if(Integer.parseInt(sogio )<=1 ) {
					lblTienphong.setText(tableDatphong.getModel().getValueAt(tableDatphong.getSelectedRow(), 3).toString());
					
				} else {
					double tienphong=Integer.parseInt(sogio)*Double.valueOf(tableDatphong.getModel().getValueAt(tableDatphong.getSelectedRow(), 3).toString());
					lblTienphong.setText(String.valueOf(tienphong));
				}
				//lblTongTienDV;
				if(lblTongTienDV.getText()=="" && lblTienphong.getText()=="" && lblMaphong.getText()=="") {
					JOptionPane.showMessageDialog(pnChucnang, "Chưa chọn phòng và gọi dịch vụ");
				} else if(lblTongTienDV.getText()=="" && lblTienphong.getText()=="") {
					JOptionPane.showMessageDialog(pnChucnang, "Chưa chọn phòng");
				} else if(lblTongTienDV.getText()=="") {
					String maphong=lblMaphong.getText();
					String tienphong= lblTienphong.getText();
					lblTongTienDV.setText("0");
					lblMaHD.setText("");
					String mahddv = lblMaHD.getText(); 
					String tongtienDV=lblTongTienDV.getText();
					GUI_LapHoaDon qlhd =new GUI_LapHoaDon();
					qlhd.tienphongDV(maphong, tienphong,mahddv, tongtienDV);
					qlhd.setVisible(true);
					dispose();
				} else {
					String maphong=lblMaphong.getText();
					String tienphong= lblTienphong.getText();
					String tongtienDV=lblTongTienDV.getText();
					String mahddv = lblMaHD.getText();
					GUI_LapHoaDon qlhd =new GUI_LapHoaDon();
					qlhd.tienphongDV(maphong, tienphong,mahddv, tongtienDV);
					qlhd.setVisible(true);
					dispose();
				}
				
			}
		});
		btnThanhtoan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnThanhtoan.setBounds(169, 68, 138, 37);
		pnChucnang.add(btnThanhtoan);
		
		JButton btnDichvu = new JButton("Gọi dịch vụ");
		btnDichvu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Form_GoiDichVu gdv = new Form_GoiDichVu();
				gdv.setVisible(true);
				dispose();
			}
		});
		btnDichvu.setBounds(345, 70, 175, 34);
		pnChucnang.add(btnDichvu);
		btnDichvu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		 pnBang = new JPanel();
		pnBang.setBounds(0, 212, 922, 434);
		contentPane.add(pnBang);
		pnBang.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(-3, 45, 922, 389);
		pnBang.add(scrollPane);
		
		/*
		 * private JTextField txtMaphong;
	private JTextField txtLoaiphong;
	private JTextField txtGiathue;
	private JComboBox cboTinhtrang;
	private JTextField txtThoigiandat;
	private JTextField txtTNgaydat;
		 */
		
		
		JPanel pnDichvu = new JPanel();
		pnDichvu.setBounds(922, 124, 349, 522);
		contentPane.add(pnDichvu);
		pnDichvu.setLayout(null);
		
		//jdateNgaydat = JDateChooser("");
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin đặt phòng");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(64, 24, 201, 29);
		pnDichvu.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mã phòng");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 84, 76, 23);
		pnDichvu.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Loại phòng:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 163, 76, 23);
		pnDichvu.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Giá thuê:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 196, 76, 29);
		pnDichvu.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Tình trạng:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(10, 235, 76, 29);
		pnDichvu.add(lblNewLabel_5);
		
		txtLoaiphong = new JTextField();
		txtLoaiphong.setColumns(10);
		txtLoaiphong.setBounds(122, 161, 175, 29);
		pnDichvu.add(txtLoaiphong);
		
		txtGiathue = new JTextField();
		txtGiathue.setColumns(10);
		txtGiathue.setBounds(122, 200, 175, 29);
		pnDichvu.add(txtGiathue);
		
		txtThoigiandat = new JTextField();
		txtThoigiandat.setColumns(10);
		txtThoigiandat.setBounds(122, 272, 175, 29);
		pnDichvu.add(txtThoigiandat);
		
		JLabel lblNewLabel_6 = new JLabel("Thời gian đặt:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(10, 274, 102, 21);
		pnDichvu.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Ngày đặt: ");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(10, 315, 102, 17);
		pnDichvu.add(lblNewLabel_7);
		//Combo tinh trang đặt phòng
		JComboBox cboTinhtrang = new JComboBox();
		cboTinhtrang.setBounds(122, 239, 175, 23);
		pnDichvu.add(cboTinhtrang);
		cboTinhtrang.addItem("Phòng trống");
		cboTinhtrang.addItem("Phòng đang sử dụng");
	
		
		btnSua = new JButton("Sửa");
		btnSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = e.getSource();
				if(validData()) {
					int row = tableDatphong.getSelectedRow();
					if(row < 0)
					{
						return;
					}
					//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					
					//Date ngaydat = Date.valueOf(df.format(jdateNgaydat.getDate()));
					modelDatphong.setValueAt(cboMaphong.getSelectedItem(), row, 0);
					modelDatphong.setValueAt(cboMaKH.getSelectedItem(), row, 1);
					modelDatphong.setValueAt(txtLoaiphong.getText().trim(), row, 2);
					modelDatphong.setValueAt(txtGiathue.getText().trim(), row, 3);
					modelDatphong.setValueAt(cboTinhtrang.getSelectedItem(), row, 4);
					modelDatphong.setValueAt(txtThoigiandat.getText().trim(), row, 5);
					modelDatphong.setValueAt(jdateNgaydat.getDate(), row, 6);
					//tinhtrang=0=false -> "Phòng trống" tinhtrang=1=true -> "Phòng đang sử dụng"
					DatPhong dp= new DatPhong();
					dp.setMaphong(Integer.parseInt(cboMaphong.getSelectedItem().toString()));
					dp.setMakh(Integer.parseInt(cboMaKH.getSelectedItem().toString()));
					dp.setLoaiphong(txtLoaiphong.getText().trim());
					dp.setGiathuephong(Double.parseDouble(txtGiathue.getText().trim()));
					dp.setTinhtrang(cboTinhtrang.getSelectedItem() ==  "Phòng trống" ? false:true);
					Time thoigiandat = Time.valueOf(txtThoigiandat.getText());
					dp.setThoigiandatphong(thoigiandat);
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Date ngaydat = Date.valueOf(df.format(jdateNgaydat.getDate()));
					dp.setNgaydatphong(ngaydat);
					dp_dao.update(dp);
					
				}
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSua.setBounds(209, 377, 124, 36);
		pnDichvu.add(btnSua);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableDatphong.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để xóa");
				} else {
					int xoa;
					xoa = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa dòng đã chọn?", "Nhắc nhở", JOptionPane.YES_NO_OPTION);
					if(xoa == JOptionPane.YES_OPTION) {
						Phong p = new Phong();
						p.setMaphong(Integer.parseInt(tableDatphong.getModel().getValueAt(tableDatphong.getSelectedRow(), 0).toString()));
						p.setLoaiphong(tableDatphong.getModel().getValueAt(tableDatphong.getSelectedRow(), 2).toString());
						p.setGiathuephong(Double.parseDouble(tableDatphong.getValueAt(row, 3).toString()));
						p.setTinhtrang(false);
						p_dao.update(p);
						modelDatphong.removeRow(row);
						int ma = Integer.parseInt(cboMaphong.getSelectedItem().toString());
						dp_dao.delete(ma);
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						cboMaphong.setSelectedItem(null);
						cboMaKH.setSelectedItem(null);
						cboTinhtrang.setSelectedItem(null);
						txtLoaiphong.setText("");
						txtGiathue.setText("");
						txtThoigiandat.setText("");
						jdateNgaydat.setDate(null);
					
					}
					
				}
				
				
			}
		});
	
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.setBounds(41, 449, 124, 36);
		pnDichvu.add(btnXoa);
		jdateNgaydat = new JDateChooser();
		jdateNgaydat.setBounds(122, 313, 175, 29);
		pnDichvu.add(jdateNgaydat);
		
		
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = e.getSource();
				if(o.equals(btnThem)) {
					if(validData()) {
						int maphong = Integer.parseInt(cboMaphong.getSelectedItem().toString());
						int makh = Integer.parseInt(cboMaKH.getSelectedItem().toString()) ;
						String loai = txtLoaiphong.getText();
						Double gia = Double.valueOf(txtGiathue.getText());
						boolean tinhTrang = cboTinhtrang.getSelectedItem()=="Phòng trống" ? false : true ;
						Time thoigiandat = Time.valueOf(txtThoigiandat.getText());
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						
						Date ngaydat = Date.valueOf(df.format(jdateNgaydat.getDate()));
						DatPhong dp  = new DatPhong(maphong,makh,loai,gia,tinhTrang,thoigiandat,ngaydat);
						if(!dp_dao.create(dp))
							JOptionPane.showMessageDialog(null, "Mã phòng chưa tạo hoặc đã tồn tại");
						else {
							JOptionPane.showMessageDialog(null, "Thêm thành công");
							GUI_QuanLyDatPhong qldp = new GUI_QuanLyDatPhong();
							qldp.setVisible(true);
							dispose();
						}
					}
				}
				
				
			}
		});
	
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.setBounds(41, 377, 124, 36);
		pnDichvu.add(btnThem);
		
		JButton btnClear = new JButton("Xóa trắng");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cboMaphong.setSelectedItem(null);
				cboMaKH.setSelectedItem(null);
				txtLoaiphong.setText("");
				txtGiathue.setText("");
				cboTinhtrang.setSelectedItem(null);
				txtThoigiandat.setText("");
				jdateNgaydat.setDate(null);
				cboMaphong.requestFocus();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClear.setBounds(209, 449, 124, 33);
		pnDichvu.add(btnClear);
		
		JLabel lblNewLabel_9 = new JLabel("Mã khách hàng:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(10, 123, 102, 23);
		pnDichvu.add(lblNewLabel_9);
		
		kh_dao=new KhachHang_Dao();
		 cboMaKH = new JComboBox();
		 cboMaKH.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboMaKH.setBounds(122, 122, 175, 29);
		pnDichvu.add(cboMaKH);
		cboMaKH.setEditable(true);
		ArrayList<Phong> listphong = p_dao.getTatCaPhong();
		 cboMaphong = new JComboBox();
		cboMaphong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboMaphong.setBounds(122, 84, 175, 23);
		pnDichvu.add(cboMaphong);
		for(Phong p :listphong) {
			cboMaphong.addItem(p.getMaphong());
		}
		cboMaphong.setEditable(true);
		ArrayList<KhachHang> listkh = kh_dao.getTatCaKhachHang();
		for(KhachHang kh: listkh) {
			cboMaKH.addItem(kh.getMakh());
		}
		
		
		//tinhtrang=0=false -> " Phòng trống " tinhtrang=1=true -> "Phòng đang sử dụng"
		tableDatphong = new JTable();
		tableDatphong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableDatphong.getSelectedRow();
				cboMaphong.setSelectedItem(modelDatphong.getValueAt(row, 0).toString());
				cboMaKH.setSelectedItem(modelDatphong.getValueAt(row, 1).toString());
				txtLoaiphong.setText(modelDatphong.getValueAt(row, 2).toString());
				txtGiathue.setText(modelDatphong.getValueAt(row, 3).toString());
				cboTinhtrang.setSelectedItem(modelDatphong.getValueAt(row, 4).toString());
				txtThoigiandat.setText(modelDatphong.getValueAt(row, 5).toString());
				jdateNgaydat.setDate(Date.valueOf(modelDatphong.getValueAt(row, 6).toString()));
				JOptionPane.showMessageDialog(contentPane, "Phòng "+modelDatphong.getValueAt(row, 0).toString()+" đã được chọn");
			}
		});
		tableDatphong.setModel(modelDatphong = new DefaultTableModel(
				new Object[][] {
					},
				new String[] {
					"Mã phòng ","Mã khách hàng", "Loại phòng", "Giá thuê phòng", "Tình trạng","Thời gian đặt","Ngày đặt"
				}
				));
		DocDuLieuDBVaoTable();
		tableDatphong.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableDatphong.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableDatphong.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableDatphong.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableDatphong.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableDatphong.getColumnModel().getColumn(5).setPreferredWidth(100);
		tableDatphong.getColumnModel().getColumn(6).setPreferredWidth(100);
		scrollPane.setViewportView(tableDatphong);
		
		JComboBox cboTimMaPhong = new JComboBox();
		cboTimMaPhong.setEditable(true);
		ArrayList<DatPhong> listDPhong= dp_dao.getTatCaDatPhong();
		//Tao list combox phòng
		for(DatPhong dp: listDPhong) {
			cboTimMaPhong.addItem(dp.getMaphong());
		}
		cboTimMaPhong.setSelectedItem("");
		cboTimMaPhong.setBounds(700, 10, 219, 34);
		pnBang.add(cboTimMaPhong);
		
		JButton btnTimMaPhong = new JButton("Tìm kiếm");
		btnTimMaPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				XoaHetDuLieuTable();
				List<DatPhong> listTimKiemTheoMa= dp_dao.getTimKiemTheoMa(Integer.parseInt(cboTimMaPhong.getSelectedItem().toString()));
				cboTimMaPhong.setSelectedItem("");
				for(DatPhong dp: listTimKiemTheoMa) {
					modelDatphong.addRow(new Object[] {
							dp.getMaphong(),dp.getMakh(),dp.getLoaiphong(),dp.getGiathuephong(),dp.isTinhtrang() == false ? "Phòng trống" : "Phòng đang sử dụng" ,dp.getThoigiandatphong(),dp.getNgaydatphong()
					});
				}
			}
		});
		btnTimMaPhong.setBounds(548, 7, 127, 35);
		pnBang.add(btnTimMaPhong);
		btnTimMaPhong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_8 = new JLabel("Danh sách đặt phòng");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_8.setBounds(186, 6, 270, 34);
		pnBang.add(lblNewLabel_8);
		
		lblTongTienDV = new JLabel("");
		lblTongTienDV.setBounds(700, 173, 216, 29);
		contentPane.add(lblTongTienDV);
		lblTongTienDV.setBackground(Color.GREEN);
		lblTongTienDV.setForeground(Color.BLACK);
		lblTongTienDV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblHienTienDV = new JLabel("Tiền dịch vụ:");
		lblHienTienDV.setBounds(573, 173, 112, 29);
		contentPane.add(lblHienTienDV);
		
			lblHienTienDV.setHorizontalAlignment(SwingConstants.CENTER);
			lblHienTienDV.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			lblMaHD = new JLabel("");
			lblMaHD.setBounds(735, 135, 181, 29);
			contentPane.add(lblMaHD);
			
			JLabel lblNewLabel_10 = new JLabel("Hóa đơn dịch vụ:");
			lblNewLabel_10.setBounds(573, 135, 112, 21);
			contentPane.add(lblNewLabel_10);
			lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
			
			 lblMaphong = new JLabel("");
			lblMaphong.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblMaphong.setBounds(352, 135, 164, 29);
			contentPane.add(lblMaphong);
			
			 lblTienphong = new JLabel("");
			lblTienphong.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblTienphong.setBounds(352, 173, 164, 29);
			contentPane.add(lblTienphong);
		
	}
	private void DocDuLieuDBVaoTable() {
		List<DatPhong> list = dp_dao.getTatCaDatPhong();
		
		for(DatPhong dp : list) {
			modelDatphong.addRow(new Object[] {
					dp.getMaphong(),dp.getMakh(),dp.getLoaiphong(),dp.getGiathuephong(),dp.isTinhtrang() == false ? "Phòng trống" : "Phòng đang sử dụng" ,dp.getThoigiandatphong(),dp.getNgaydatphong()
			});
			
		}
	}
	private void XoaHetDuLieuTable() {
		DefaultTableModel dtm = (DefaultTableModel) tableDatphong.getModel();
		dtm.setRowCount(0);
	}
	private boolean validData() {
		int maphong= Integer.parseInt(cboMaphong.getSelectedItem().toString());
		int makh = Integer.parseInt(jdateNgaydat.getDate().toString());
		double giaphong = Double.parseDouble(txtGiathue.getText());
		if(maphong<0) {
			JOptionPane.showMessageDialog(null, "Mã phòng phải lớn hơn 0");
			return false;
		}
		if(makh<0) {
			JOptionPane.showMessageDialog(null, "Mã khách hàng phải lớn hơn 0");
			return false;
		}
		return true;
	}
}
