package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import connectDB.ConnectDB;
import entity.DatPhong;
import entity.Hang;
import entity.HoaDon;
import entity.NhanVien;
import entity.Phong;
import dao.DatPhong_DAO;
import dao.GoiDichVu_DAO;
import dao.HoaDon_DAO;
import dao.NhanVien_DAO;
import dao.Phong_DAO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;
public class GUI_LapHoaDon extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaphong;
	private JTextField txtTienDV;
	private JTextField txtTienPhong;
	private JTextField txtThanhtien;
	private DefaultTableModel modelHD;
	private HoaDon_DAO hd_dao;
	private Phong_DAO p_dao;
	private DatPhong_DAO dp_dao;
	private NhanVien_DAO nv_dao;
	private GoiDichVu_DAO dv_dao;
	private JLabel lblMaHDDV;
	private JDateChooser dateNgaydat;
	private JComboBox cboMaNV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_LapHoaDon frame = new GUI_LapHoaDon();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void tienphongDV(String maphong,String tienphong,String mahddv,String tiendv) {
		txtMaphong.setText(maphong);
		txtTienPhong.setText(tienphong);
		txtTienDV.setText(tiendv);
		lblMaHDDV.setText(mahddv);
			
	}


	/**
	 * Create the frame.
	 */
	public GUI_LapHoaDon() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		p_dao= new Phong_DAO();
		dp_dao=new DatPhong_DAO();
		
		hd_dao =new HoaDon_DAO();	 
		
		JPanel pnTieude = new JPanel();
		pnTieude.setBounds(0, 0, 888, 82);
		contentPane.add(pnTieude);
		pnTieude.setLayout(null);
		dv_dao= new GoiDichVu_DAO();
		ArrayList<Hang> listDV = dv_dao.getTatCaHoaDonDV();
	
		JButton btnQuaylai = new JButton("Quay lại");
		btnQuaylai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_QuanLyDatPhong qldp = new GUI_QuanLyDatPhong();
				qldp.setVisible(true);
				dispose();
			}
		});
		btnQuaylai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnQuaylai.setBounds(10, 10, 114, 52);
		pnTieude.add(btnQuaylai);
	
		 //Mã hóa đơn dịch vụ
		lblMaHDDV= new JLabel("");
		pnTieude.add(lblMaHDDV);
		
		
		
		JPanel pnBang = new JPanel();
		pnBang.setBounds(0, 79, 888, 518);
		contentPane.add(pnBang);
		pnBang.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin hóa đơn thanh toán");
		lblNewLabel_1.setBounds(247, 10, 373, 59);
		pnBang.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtMaphong = new JTextField();
		txtMaphong.setBounds(170, 186, 245, 21);
		pnBang.add(txtMaphong);
		txtMaphong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMaphong.setColumns(10);
		txtMaphong.setEditable(false);
		
		JLabel lblNewLabel_3 = new JLabel("Mã phòng:");
		lblNewLabel_3.setBounds(28, 183, 99, 27);
		pnBang.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 cboMaNV = new JComboBox();
		cboMaNV.setBounds(170, 244, 245, 26);
		pnBang.add(cboMaNV);
		nv_dao=new NhanVien_DAO();
		ArrayList<NhanVien> listnv=nv_dao.getTatCaNhanVien();
		for(NhanVien nv : listnv) {
			cboMaNV.addItem(nv.getManv());
		}
		
		
		 dateNgaydat = new JDateChooser();
		 dateNgaydat.setBounds(170, 305, 245, 21);
		 pnBang.add(dateNgaydat);
		 
		 JLabel lblNewLabel_4 = new JLabel("Mã nhân viên:");
		 lblNewLabel_4.setBounds(28, 240, 99, 33);
		 pnBang.add(lblNewLabel_4);
		 lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 
		 JLabel lblNewLabel_6 = new JLabel("Ngày lập HD:");
		 lblNewLabel_6.setBounds(28, 299, 99, 27);
		 pnBang.add(lblNewLabel_6);
		 lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 
		 JButton btnNewButton = new JButton("Xem thành tiền");
		 btnNewButton.setBounds(342, 80, 224, 54);
		 pnBang.add(btnNewButton);
		 btnNewButton.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		double thanhtien=0;
		 		String tienDV =txtTienDV.getText();
		 		Double tienphong =Double.parseDouble(txtTienPhong.getText());
		 		tienDV =txtTienDV.getText();
		 		thanhtien=tienphong+Double.parseDouble(tienDV);
		 		txtThanhtien.setText(String.valueOf(thanhtien));
		 	
		 	}
		 });
		 btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		 
		 JLabel lblNewLabel_8 = new JLabel("Tiền dịch vụ:");
		 lblNewLabel_8.setBounds(447, 183, 95, 27);
		 pnBang.add(lblNewLabel_8);
		 lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 
		 txtTienDV = new JTextField();
		 txtTienDV.setBounds(583, 186, 245, 21);
		 pnBang.add(txtTienDV);
		 txtTienDV.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 txtTienDV.setColumns(10);
		 txtTienDV.setEditable(false);
		 
		 txtTienPhong = new JTextField();
		 txtTienPhong.setBounds(583, 246, 245, 21);
		 pnBang.add(txtTienPhong);
		 txtTienPhong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 txtTienPhong.setColumns(10);
		 txtTienPhong.setEditable(false);
		 
		 JLabel lblNewLabel_9 = new JLabel("Tiền phòng:");
		 lblNewLabel_9.setBounds(447, 240, 99, 33);
		 pnBang.add(lblNewLabel_9);
		 lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 
		 JLabel lblNewLabel_7 = new JLabel("Thành tiền:");
		 lblNewLabel_7.setBounds(447, 299, 104, 27);
		 pnBang.add(lblNewLabel_7);
		 lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		 
		 txtThanhtien = new JTextField();
		 txtThanhtien.setBounds(583, 302, 245, 21);
		 pnBang.add(txtThanhtien);
		 txtThanhtien.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 txtThanhtien.setColumns(10);
		 //Tính tổng tiền
		 txtThanhtien.setEditable(false);
		 
		 	
		 	JButton btnThem = new JButton("Thanh toán");
		 	btnThem.setBounds(447, 371, 224, 54);
		 	pnBang.add(btnThem);
		 	//tinhtrang=0=false -> "Phòng trống" tinhtrang=1=true -> "Phòng đang sử dụng"
		 	btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		 	JButton btnXoa = new JButton("Xóa trắng");
		 	btnXoa.setBounds(222, 371, 163, 54);
		 	pnBang.add(btnXoa);
		 	btnXoa.addMouseListener(new MouseAdapter() {
		 		@Override
		 		public void mouseClicked(MouseEvent e) {
		 		
		 				
		 				txtMaphong.setText("");
		 				cboMaNV.setSelectedItem(null);
		 				dateNgaydat.setDate(null);
		 				txtTienDV.setText("");
		 				txtTienDV.setEditable(false);
		 				txtTienPhong.setText("");
		 				txtTienPhong.setEditable(false);
		 				txtThanhtien.setText("");
		 				
		 		}
		 	});
		 	
		 		
		 		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		 	btnThem.addActionListener(new ActionListener() {
		 		public void actionPerformed(ActionEvent e) {
		 			int maphong = Integer.parseInt(txtMaphong.getText());
		 			int manv = Integer.parseInt(cboMaNV.getSelectedItem().toString());
		 			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 			Date ngaylap = Date.valueOf(df.format(dateNgaydat.getDate()));
		 			double thanhtien = Double.parseDouble(txtThanhtien.getText());
		 			
		 			boolean tinhtrang = true;
		 			boolean tinhtrangphong = false;
		 			HoaDon hd = new HoaDon( maphong, manv,ngaylap, thanhtien);
		 		
		 			if(lblMaHDDV.getText().isEmpty()) {
		 				DatPhong dp =new DatPhong();
		 				Phong p= new Phong();	
		 				p.setTinhtrang(tinhtrangphong);
		 				p.setMaphong(maphong);
		 				p_dao.updateThanhToan(p);
		 				dp.setTinhtrang(tinhtrangphong);
		 				dp.setMaphong(maphong);
		 				dp_dao.updateThanhToan(dp);
		 			} else {
		 				Hang h =new Hang();
		 				int mahddv = Integer.parseInt(lblMaHDDV.getText());
		 				h.setTinhtrang(tinhtrang);
		 				h.setMahddv(mahddv);
		 				dv_dao.updateHoaDonDV(h);
		 				DatPhong dp =new DatPhong();
		 				Phong p= new Phong();	
		 				p.setTinhtrang(tinhtrangphong);
		 				p.setMaphong(maphong);
		 				p_dao.updateThanhToan(p);
		 				dp.setTinhtrang(tinhtrangphong);
		 				dp.setMaphong(maphong);
		 				dp_dao.updateThanhToan(dp);
		 			}
		 			if(!hd_dao.create(hd))
		 				JOptionPane.showMessageDialog(pnBang, "Thêm hóa đơn thất bại");
		 			else {
		 			
		 				JOptionPane.showMessageDialog(pnBang, "Thêm thành công");
		 				GUI_LapHoaDon qlhd=new GUI_LapHoaDon();
		 				qlhd.setVisible(true);
		 				dispose();
		 			}
		 		}
		 			
		 	});
		
		
		
	}



}
