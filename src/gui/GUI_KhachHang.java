package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Image;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import connectDB.ConnectDB;

import dao.KhachHang_Dao;
import entity.KhachHang;
import entity.Phong;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.UIManager;
import com.toedter.calendar.JDateChooser;

public class GUI_KhachHang extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtMakh;
	private JTextField txtTenkh;
	private JTextField txtCMND;
	private JTextField txtTuoi;
	private DefaultTableModel modelKH;
	private JTable tableKH;
	private KhachHang_Dao kh_dao;
	private JComboBox cboGioitinh;
	private JTextField txtSDT;
	private JDateChooser dateNgaydat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_KhachHang frame = new GUI_KhachHang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_KhachHang() {
		getContentPane().setBackground(new Color(127, 255, 212));
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		kh_dao=new KhachHang_Dao();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1265, 723);
		getContentPane().setLayout(null);
		
		JPanel pnChucnang = new JPanel();
		pnChucnang.setBackground(new Color(127, 255, 212));
		pnChucnang.setBounds(0, 0, 1249, 126);
		getContentPane().add(pnChucnang);
		pnChucnang.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		lblNewLabel.setForeground(new Color(255, 140, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(0, 11, 293, 41);
		pnChucnang.add(lblNewLabel);
		
		JButton btnQuaylai = new JButton("Quay lại trang chủ");
		btnQuaylai.setFont(new Font("Tahoma", Font.BOLD, 13));
		Image img_b = new ImageIcon(this.getClass().getResource("/image/undo2.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		btnQuaylai.setIcon(new ImageIcon(img_b));
		btnQuaylai.setBackground(new Color(255, 255, 0));
		btnQuaylai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_Chinh main=new GUI_Chinh();
				main.setVisible(true);
				dispose();
			}
		});
		btnQuaylai.setBounds(20, 63, 200, 41);
		pnChucnang.add(btnQuaylai);
		
		
		getContentPane().setLayout(null);

		JButton button = new JButton("New button");
		pnChucnang.add(button);
		
		JComboBox cboTimSDT = new JComboBox();
		cboTimSDT.setBounds(902, 76, 243, 35);
		pnChucnang.add(cboTimSDT);
		cboTimSDT.setEditable(true);
		ArrayList<KhachHang> listkh =kh_dao.getTatCaKhachHang();
		//Tạo list combobox khach hang
		for(KhachHang kh:listkh) {
			cboTimSDT.addItem(kh.getSdt());
		}
		cboTimSDT.setSelectedItem("");
		AutoCompleteDecorator.decorate(cboTimSDT);
		JPanel pnBang = new JPanel();
		pnBang.setBackground(new Color(127, 255, 212));
		pnBang.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnBang.setBounds(10, 137, 876, 520);
		getContentPane().add(pnBang);
		pnBang.setLayout(null);

		JButton btnTimSDT = new JButton("Tìm khách hàng theo SDT");
		Image img_tim = new ImageIcon(this.getClass().getResource("/image/search.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		btnTimSDT.setIcon(new ImageIcon(img_tim));
		btnTimSDT.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTimSDT.setBackground(new Color(255, 255, 0));
		btnTimSDT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o= e.getSource();
				if(o.equals(btnTimSDT)) {
					XoaHetDuLieuTable();
					List<KhachHang> listTimsdt= kh_dao.getTimSDT(Integer.parseInt(cboTimSDT.getSelectedItem().toString()));
					cboTimSDT.setSelectedItem("");
					for(KhachHang kh: listTimsdt) {
						modelKH.addRow(new Object[] {
								kh.getMakh(),kh.getTenkh(),kh.getCmnd(),kh.getTuoi(),kh.isGioitinh()== false ? "Nữ" : "Nam", kh.getNgaysinh(),kh.getSdt()
						});
					}
				}
			}
		});
		btnTimSDT.setBounds(571, 76, 301, 35);
		pnChucnang.add(btnTimSDT);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 851, 456);
		pnBang.add(scrollPane);
		

		tableKH = new JTable();
		
		tableKH.setModel(modelKH=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã khách hàng", "Tên khách hàng", "CMND", "Tuổi", "Giới tính","Ngày sinh","Số điện thoại"
			}
		));
		tableKH.getColumnModel().getColumn(0).setMinWidth(100);
		tableKH.getColumnModel().getColumn(1).setMinWidth(100);
		tableKH.getColumnModel().getColumn(2).setMinWidth(100);
		tableKH.getColumnModel().getColumn(3).setMinWidth(100);
		tableKH.getColumnModel().getColumn(4).setMinWidth(100);
		tableKH.getColumnModel().getColumn(5).setMinWidth(100);
		tableKH.setBounds(0, 36, 906, 520);
		DocDuLieuDBVaoTable();
		scrollPane.setViewportView(tableKH);
		
		JLabel lblNewLabel_1 = new JLabel("Danh sách khách hàng");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setBounds(10, 3, 261, 37);
		pnBang.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
	
		
		
		JPanel pnKhachhang = new JPanel();
		pnKhachhang.setBackground(new Color(127, 255, 212));
		pnKhachhang.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnKhachhang.setBounds(896, 137, 347, 520);
		getContentPane().add(pnKhachhang);
		pnKhachhang.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Thông tin khách hàng");
		lblNewLabel_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_2.setBounds(0, 0, 261, 49);
		pnKhachhang.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Mã khách hàng");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(10, 60, 86, 20);
		pnKhachhang.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Tên khách hàng");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(10, 99, 96, 20);
		pnKhachhang.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("CMND");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(10, 141, 46, 20);
		pnKhachhang.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Tuổi");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(10, 187, 46, 17);
		pnKhachhang.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Giới tính");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(10, 236, 70, 18);
		pnKhachhang.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Ngày sinh");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(10, 283, 86, 17);
		pnKhachhang.add(lblNewLabel_8);
		
		txtMakh = new JTextField();
		txtMakh.setEditable(false);
		txtMakh.setBounds(116, 60, 216, 20);
		pnKhachhang.add(txtMakh);
		txtMakh.setColumns(10);
		
		txtTenkh = new JTextField();
		txtTenkh.setColumns(10);
		txtTenkh.setBounds(116, 96, 216, 20);
		pnKhachhang.add(txtTenkh);
		
		txtCMND = new JTextField();
		txtCMND.setColumns(10);
		txtCMND.setBounds(116, 138, 216, 20);
		pnKhachhang.add(txtCMND);
		
		txtTuoi = new JTextField();
		txtTuoi.setColumns(10);
		txtTuoi.setBounds(116, 184, 216, 20);
		pnKhachhang.add(txtTuoi);
		
		dateNgaydat = new JDateChooser();
		dateNgaydat.setBounds(116, 281, 216, 22);
		pnKhachhang.add(dateNgaydat);
		JComboBox cboGioitinh = new JComboBox();
		cboGioitinh.setBounds(116, 232, 216, 22);
		pnKhachhang.add(cboGioitinh);
		cboGioitinh.addItem("Nữ");
		cboGioitinh.addItem("Nam");
		JButton btnThem = new JButton("Thêm");
		Image img_them = new ImageIcon(this.getClass().getResource("/image/login.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		btnThem.setIcon(new ImageIcon(img_them));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThem.setBackground(new Color(255, 255, 0));
		btnThem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = e.getSource();
				if(o.equals(btnThem)) {
					if(validData()) {
						String ten=txtTenkh.getText();
						int cmnd=Integer.parseInt(txtCMND.getText());
						int tuoi=Integer.parseInt(txtTuoi.getText());
						boolean gioitinh=cboGioitinh.getSelectedItem()== "Nữ" ? false : true;
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						Date ngaysinh = Date.valueOf(df.format(dateNgaydat.getDate()));
						int sdt=Integer.parseInt(txtSDT.getText());
						KhachHang kh=new KhachHang(ten,cmnd,tuoi,gioitinh,ngaysinh,sdt);
						if(!kh_dao.create(kh))
							JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại hoặc khách hàng đã tồn tại");
						else {
							JOptionPane.showMessageDialog(null, "Thêm thành công");
							GUI_KhachHang gdkh=new GUI_KhachHang();
							gdkh.setVisible(true);
							dispose();
						}
					}
				}
			}
		});
		btnThem.setBounds(10, 372, 147, 40);
		pnKhachhang.add(btnThem);
		
		JButton btnSua = new JButton("Cập nhật");
		Image img_sua = new ImageIcon(this.getClass().getResource("/image/update.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		btnSua.setIcon(new ImageIcon(img_sua));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSua.setBackground(new Color(255, 255, 0));
		btnSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(validData()) {
					int row = tableKH.getSelectedRow();
					if(row < 0)
					{
						return;
					}
					modelKH.setValueAt(txtMakh.getText().trim(), row, 0);
					modelKH.setValueAt(txtTenkh.getText().trim(), row, 1);
					modelKH.setValueAt(txtCMND.getText().trim(), row, 2);
					modelKH.setValueAt(txtTuoi.getText().trim(), row, 3);
					modelKH.setValueAt(cboGioitinh.getSelectedItem(), row, 4);
					modelKH.setValueAt(dateNgaydat.getDateFormatString(), row, 5);
					modelKH.setValueAt(txtSDT.getText().trim(), row, 6);

					//gioitinh=false "Nữ" gioitinh=true "Nam"
					KhachHang kh=new KhachHang();
					kh.setMakh(Integer.parseInt(txtMakh.getText().trim()));
					kh.setTenkh(txtTenkh.getText().trim());
					kh.setCmnd(Integer.parseInt(txtCMND.getText().trim()));
					kh.setTuoi(Integer.parseInt(txtTuoi.getText()));
					kh.setGioitinh(cboGioitinh.getSelectedItem() == "Nữ" ? false : true);
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Date ngaysinh = Date.valueOf(df.format(dateNgaydat.getDate()));
					kh.setNgaysinh(ngaysinh);
					kh.setSdt(Integer.parseInt(txtSDT.getText().trim()));
					kh_dao.update(kh);
					if(!kh_dao.update(kh))
						JOptionPane.showMessageDialog(pnBang, "Sửa thông tin khách hàng thất bại");
					else {
						JOptionPane.showMessageDialog(pnBang, "Đã sửa thông tin khách hàng");
						GUI_KhachHang qlkh = new GUI_KhachHang();
						qlkh.setVisible(true);
						dispose();
					}
				
				}
			}
		});
		btnSua.setBounds(10, 439, 147, 40);
		pnKhachhang.add(btnSua);
		
		JButton btnXoa = new JButton("Xóa");
		Image img_xoa = new ImageIcon(this.getClass().getResource("/image/delete.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		btnXoa.setIcon(new ImageIcon(img_xoa));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoa.setBackground(new Color(255, 255, 0));
		btnXoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = e.getSource();
				if(o.equals(btnXoa)) {
					int row = tableKH.getSelectedRow();
					if(row==-1)
						JOptionPane.showMessageDialog(pnKhachhang, "Bạn chưa chọn dòng để xóa");
					else {
						int xoa;
						xoa=JOptionPane.showConfirmDialog(pnKhachhang, "Bạn có muốn xóa dòng đã chọn?", "Nhắc nhở", JOptionPane.YES_NO_OPTION);
						modelKH.removeRow(row);
						int ma=Integer.parseInt(txtMakh.getText());
						kh_dao.delete(ma);
						JOptionPane.showMessageDialog(pnKhachhang, "Xóa thành công");
						txtMakh.setText("");
						txtMakh.setEditable(false);
						txtTenkh.setText("");
						txtCMND.setText("");
						txtTuoi.setText("");
						cboGioitinh.setSelectedItem(null);
						dateNgaydat.setDate(null);
						txtSDT.setText("");
					}
				}
			}
		});
		btnXoa.setBounds(183, 372, 134, 40);
		pnKhachhang.add(btnXoa);
		
		JButton btnClear = new JButton("Xóa trắng");
		Image img_xt = new ImageIcon(this.getClass().getResource("/image/load.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		btnClear.setIcon(new ImageIcon(img_xt));
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnClear.setBackground(Color.YELLOW);
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMakh.setText("");
				txtMakh.setEditable(false);
				txtTenkh.setText("");
				txtCMND.setText("");
				txtTuoi.setText("");
				cboGioitinh.setSelectedItem(null);
				dateNgaydat.setDate(null);
				txtSDT.setText("");
			}
		});
		btnClear.setBounds(183, 439, 134, 40);
		pnKhachhang.add(btnClear);
		
		JLabel lblNewLabel_9 = new JLabel("SĐT");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_9.setBounds(10, 325, 46, 17);
		pnKhachhang.add(lblNewLabel_9);
		
		txtSDT = new JTextField();
		txtSDT.setBounds(116, 322, 216, 20);
		pnKhachhang.add(txtSDT);
		txtSDT.setColumns(10);
		
		 
		
		tableKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableKH.getSelectedRow();
				txtMakh.setText(modelKH.getValueAt(row, 0).toString());
				txtTenkh.setText(modelKH.getValueAt(row, 1).toString());
				txtCMND.setText(modelKH.getValueAt(row, 2).toString());
				txtTuoi.setText(modelKH.getValueAt(row, 3).toString());
				cboGioitinh.setSelectedItem(modelKH.getValueAt(row, 4));
				dateNgaydat.setDate(Date.valueOf(modelKH.getValueAt(row, 5).toString()));
				txtSDT.setText(modelKH.getValueAt(row, 6).toString());
				txtMakh.setEditable(false);
				
			}
		});
		
	}
	private void DocDuLieuDBVaoTable() {
		List<KhachHang> list = kh_dao.getTatCaKhachHang();
		for(KhachHang kh : list) {
			modelKH.addRow(new Object[] {
					kh.getMakh(),kh.getTenkh(),kh.getCmnd(),kh.getTuoi(),kh.isGioitinh()== false ? "Nữ" : "Nam", kh.getNgaysinh(),kh.getSdt()
			});
		}
	}
	private boolean validData() {
		String ten=txtTenkh.getText();
		int tuoi = Integer.parseInt(txtTuoi.getText());
		int cmnd= Integer.parseInt(txtCMND.getText());
		if(ten.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Tên không được để trống");
		} else if(ten.matches("[0-9]+")){
			JOptionPane.showMessageDialog(null, "Tên không được nhập số");
			return false;
		}
		if(tuoi<15) {
			JOptionPane.showMessageDialog(null, "Tuổi phải lớn hơn 15");
			return false;
		} 
		return true;
	}
	private void XoaHetDuLieuTable() {
		DefaultTableModel dtm = (DefaultTableModel) tableKH.getModel();
		dtm.setRowCount(0);
	}
}
