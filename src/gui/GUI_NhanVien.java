package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.Font;
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


import connectDB.ConnectDB;
import dao.CaTruc_DAO;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.CaTruc;
import entity.KhachHang;
import entity.NhanVien;
import entity.Phong;
import entity.TaiKhoan;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class GUI_NhanVien extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtManv;
	private JTextField txtTennv;
	private JTextField txtCMND;
	private JTextField txtTuoi;
	private DefaultTableModel modelNV;
	private JTable tableNV;
	private NhanVien_DAO nv_dao;
	private JComboBox cboGioitinh;
	private JTextField txtSDT;
	private JTextField txtDiachi;
	private JTextField txtTienluong;
	private CaTruc_DAO ca_dao;
	private JComboBox cboTenTK;
	private TaiKhoan_DAO tk_dao;
	private JDateChooser dateNgaysinh;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_NhanVien frame = new GUI_NhanVien();
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
	public GUI_NhanVien() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		nv_dao=new NhanVien_DAO();
		tk_dao = new TaiKhoan_DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1446, 767);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1432, 126);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản lý nhân viên");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(422, 11, 243, 41);
		panel.add(lblNewLabel);
		
		JButton btnQuaylai = new JButton("Quay lại trang chủ");
		btnQuaylai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_Chinh main=new GUI_Chinh();
				main.setVisible(true);
				dispose();
			}
		});
		btnQuaylai.setBounds(20, 63, 181, 41);
		panel.add(btnQuaylai);
		
		getContentPane().setLayout(null);

		JButton button = new JButton("New button");
		panel.add(button);
		
		JComboBox cboTimSDT = new JComboBox();
		cboTimSDT.setBounds(902, 76, 243, 35);
		panel.add(cboTimSDT);
		cboTimSDT.setEditable(true);
		ArrayList<NhanVien> listnv =nv_dao.getTatCaNhanVien();
		//Tạo list combobox khach hang
		for(NhanVien nv:listnv) {
			cboTimSDT.addItem(nv.getSdt());
		}
		cboTimSDT.setSelectedItem("");
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 125, 1063, 605);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton btnTimSDT = new JButton("Tìm nhân viên theo SDT");
		btnTimSDT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o= e.getSource();
				if(o.equals(btnTimSDT)) {
					XoaHetDuLieuTable();
					List<NhanVien> listTimsdt= nv_dao.getTimSDT(Integer.parseInt(cboTimSDT.getSelectedItem().toString()));
					cboTimSDT.setSelectedItem("");
					for(NhanVien nv: listTimsdt) {
						modelNV.addRow(new Object[] {
								nv.getManv(),nv.getTentk(),nv.getMaca(),nv.getTennv(),nv.getCmnd(),nv.getTuoi(),nv.getNgaysinh(),nv.isGioitinh()== false ? "Nữ" : "Nam",nv.getDiachi(),nv.getTienluong(),nv.getSdt()
						});
					}
				}
			}
		});
		btnTimSDT.setBounds(651, 76, 221, 35);
		panel.add(btnTimSDT);
		
		JLabel lblNewLabel_1 = new JLabel("Danh sách nhân viên");
		lblNewLabel_1.setBounds(288, 0, 261, 37);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		panel_1.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 51, 1063, 553);
		panel_1.add(scrollPane);
		

		tableNV = new JTable();
		
		tableNV.setModel(modelNV=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã nhân viên", "Tên tài khoản", "Mã ca", "Tên nhân viên", "CMND","Tuổi","Ngày sinh","Giới tính","Địa chỉ","Tiền lương","Số điện thoại"
			}
		));
		tableNV.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableNV.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableNV.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableNV.getColumnModel().getColumn(3).setPreferredWidth(250);
		tableNV.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableNV.getColumnModel().getColumn(5).setPreferredWidth(100);
		tableNV.getColumnModel().getColumn(6).setPreferredWidth(100);
		tableNV.getColumnModel().getColumn(7).setPreferredWidth(100);
		tableNV.getColumnModel().getColumn(8).setPreferredWidth(100);
		tableNV.getColumnModel().getColumn(9).setPreferredWidth(100);
		tableNV.getColumnModel().getColumn(10).setPreferredWidth(100);
		tableNV.setBounds(0, 36, 906, 520);
		DocDuLieuDBVaoTable();
		scrollPane.setViewportView(tableNV);
	
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(1063, 125, 369, 605);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Thông tin nhân viên");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_2.setBounds(0, 0, 261, 49);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Mã nhân viên");
		lblNewLabel_3.setBounds(10, 60, 86, 14);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Tên nhân viên");
		lblNewLabel_4.setBounds(10, 181, 96, 14);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("CMND");
		lblNewLabel_5.setBounds(10, 212, 46, 14);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Tuổi");
		lblNewLabel_6.setBounds(10, 243, 46, 14);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Giới tính");
		lblNewLabel_7.setBounds(10, 308, 70, 14);
		panel_2.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Ngày sinh");
		lblNewLabel_8.setBounds(10, 274, 86, 14);
		panel_2.add(lblNewLabel_8);
		
		txtManv = new JTextField();
		txtManv.setEditable(false);
		txtManv.setBounds(116, 60, 216, 20);
		panel_2.add(txtManv);
		txtManv.setColumns(10);
		
		txtTennv = new JTextField();
		txtTennv.setColumns(10);
		txtTennv.setBounds(116, 178, 216, 20);
		panel_2.add(txtTennv);
		
		txtCMND = new JTextField();
		txtCMND.setColumns(10);
		txtCMND.setBounds(116, 209, 216, 20);
		panel_2.add(txtCMND);
		
		txtTuoi = new JTextField();
		txtTuoi.setColumns(10);
		txtTuoi.setBounds(116, 240, 216, 20);
		panel_2.add(txtTuoi);
		ca_dao = new CaTruc_DAO();
		ArrayList<CaTruc> listca= ca_dao.getDSCa();
		JComboBox cboMaca = new JComboBox();
		cboMaca.setBounds(116, 142, 216, 26);
		panel_2.add(cboMaca);
		//tạo list combobox
		for(CaTruc ca : listca) {
			cboMaca.addItem(ca.getMaca());
		}
		JComboBox cboGioitinh = new JComboBox();
		cboGioitinh.setBounds(116, 304, 216, 22);
		panel_2.add(cboGioitinh);
		cboGioitinh.addItem("Nữ");
		cboGioitinh.addItem("Nam");
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = e.getSource();
				if(o.equals(btnThem)) {
					if(validData()) {
						String tentk=cboTenTK.getSelectedItem().toString();
						int maca=Integer.parseInt(cboMaca.getSelectedItem().toString());
						String tennv=txtTennv.getText();
						int cmnd=Integer.parseInt(txtCMND.getText());
						int tuoi=Integer.parseInt(txtTuoi.getText());
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						Date ngaysinh = Date.valueOf(df.format(dateNgaysinh.getDate()));
						boolean gioitinh=cboGioitinh.getSelectedItem()== "Nữ" ? false : true;
						String diachi=txtDiachi.getText();
						double tien=Double.parseDouble(txtTienluong.getText());
						int sdt=Integer.parseInt(txtSDT.getText());
						NhanVien nv=new NhanVien(tentk,maca,tennv,cmnd,tuoi,ngaysinh,gioitinh,diachi,tien,sdt);
						if(!nv_dao.create(nv))
							JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại");
						else {
							JOptionPane.showMessageDialog(null, "Thêm nhân viên  thành công");
							GUI_NhanVien gdnv=new GUI_NhanVien();
							gdnv.setVisible(true);
							dispose();
						}
					}
				}
			}
		});
		btnThem.setBounds(22, 465, 123, 33);
		panel_2.add(btnThem);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(validData()) {
					int row = tableNV.getSelectedRow();
					if(row < 0)
					{
						return;
					}
					modelNV.setValueAt(txtManv.getText().trim(), row, 0);
					modelNV.setValueAt(cboTenTK.getSelectedItem(), row, 1);
					modelNV.setValueAt(cboMaca.getSelectedItem().toString(), row, 2);
					modelNV.setValueAt(txtTennv.getText().trim(), row, 3);
					modelNV.setValueAt(txtCMND.getText().trim(), row, 4);
					modelNV.setValueAt(txtTuoi.getText().trim(), row, 5);
					modelNV.setValueAt(dateNgaysinh.getDate().toString(), row, 6);
					modelNV.setValueAt(cboGioitinh.getSelectedItem(), row, 7);
					modelNV.setValueAt(txtDiachi.getText().trim(), row, 8);
					modelNV.setValueAt(txtTienluong.getText().trim(), row, 9);
					modelNV.setValueAt(txtSDT.getText().trim(), row, 10);

					//gioitinh=false "Nữ" gioitinh=true "Nam"
					NhanVien nv=new NhanVien();
					try {
						nv.setManv(Integer.parseInt(txtManv.getText().trim()));
					} catch (NumberFormatException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					} catch (Exception e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					try {
						nv.setTentk(cboTenTK.getSelectedItem().toString());
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					nv.setMaca(Integer.parseInt(cboMaca.getSelectedItem().toString().trim()));
					try {
						nv.setTennv(txtTennv.getText().trim());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					nv.setCmnd(Integer.parseInt(txtCMND.getText().trim()));
					nv.setTuoi(Integer.parseInt(txtTuoi.getText()));
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Date ngaysinh = Date.valueOf(df.format(dateNgaysinh.getDate()));
					nv.setNgaysinh(ngaysinh);
					nv.setGioitinh(cboGioitinh.getSelectedItem() == "Nữ" ? false : true);
					nv.setDiachi(txtDiachi.getText().trim());
					Double tien = Double.valueOf(txtTienluong.getText());
					nv.setSdt(Integer.parseInt(txtSDT.getText().trim()));
					nv_dao.update(nv);
					if(!nv_dao.update(nv))
						JOptionPane.showMessageDialog(panel, "Sửa thông tin nhân viên thất bại");
					else {
						JOptionPane.showMessageDialog(panel, "Đã sửa thông tin nhân viên");
						GUI_NhanVien qlnv = new GUI_NhanVien();
						qlnv.setVisible(true);
						dispose();
					}
				}
			}
		});
		btnSua.setBounds(199, 465, 110, 33);
		panel_2.add(btnSua);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnXoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = e.getSource();
				if(o.equals(btnXoa)) {
					int row = tableNV.getSelectedRow();
					if(row==-1)
						JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để xóa");
					else {
						int xoa;
						xoa=JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa dòng đã chọn?", "Nhắc nhở", JOptionPane.YES_NO_OPTION);
						if(xoa == JOptionPane.YES_OPTION) {
							modelNV.removeRow(row);
							int ma=Integer.parseInt(txtManv.getText());
							nv_dao.delete(ma);
							JOptionPane.showMessageDialog(null, "Xóa thành công");
							txtManv.setText("");
							txtManv.setEditable(false);
							cboTenTK.setSelectedItem(null);
							cboMaca.setSelectedItem(null);
							txtTennv.setText("");
							txtCMND.setText("");
							txtTuoi.setText("");
							dateNgaysinh.setDate(null);
							cboGioitinh.setSelectedItem(null);
							txtDiachi.setText("");
							txtTienluong.setText("");
							txtSDT.setText("");
						}
					
					}
				}
			}
		});
		btnXoa.setBounds(22, 515, 123, 39);
		panel_2.add(btnXoa);
		
		JButton btnClear = new JButton("Xóa trắng");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtManv.setText("");
				txtManv.setEditable(false);
				cboTenTK.setSelectedItem(null);
				cboMaca.setSelectedItem(null);
				txtTennv.setText("");
				txtCMND.setText("");
				txtTuoi.setText("");
				dateNgaysinh.setDate(null);
				cboGioitinh.setSelectedItem(null);
				txtDiachi.setText("");
				txtTienluong.setText("");
				txtSDT.setText("");
				
			}
		});
		btnClear.setBounds(199, 519, 110, 31);
		panel_2.add(btnClear);
		
		JLabel lblNewLabel_9 = new JLabel("SĐT");
		lblNewLabel_9.setBounds(10, 409, 46, 14);
		panel_2.add(lblNewLabel_9);
		
		txtSDT = new JTextField();
		txtSDT.setBounds(116, 406, 216, 20);
		panel_2.add(txtSDT);
		txtSDT.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("Tên tài khoản");
		lblNewLabel_3_1.setBounds(10, 106, 86, 14);
		panel_2.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Mã ca");
		lblNewLabel_3_1_1.setBounds(10, 145, 86, 14);
		panel_2.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_9_1 = new JLabel("Địa chỉ");
		lblNewLabel_9_1.setBounds(10, 342, 46, 14);
		panel_2.add(lblNewLabel_9_1);
		
		JLabel lblNewLabel_9_1_1 = new JLabel("Tiền lương");
		lblNewLabel_9_1_1.setBounds(10, 378, 86, 14);
		panel_2.add(lblNewLabel_9_1_1);
		
		txtDiachi = new JTextField();
		txtDiachi.setColumns(10);
		txtDiachi.setBounds(116, 339, 216, 20);
		panel_2.add(txtDiachi);
		
		txtTienluong = new JTextField();
		txtTienluong.setColumns(10);
		txtTienluong.setBounds(116, 375, 216, 20);
		panel_2.add(txtTienluong);
		
		 dateNgaysinh = new JDateChooser();
		dateNgaysinh.setBounds(115, 270, 217, 26);
		panel_2.add(dateNgaysinh);
		
		
		 cboTenTK = new JComboBox();
		cboTenTK.setBounds(116, 100, 216, 26);
		panel_2.add(cboTenTK);
		ArrayList<TaiKhoan> listTK = tk_dao.getTatCaTK();
		for(TaiKhoan tk: listTK) {
			cboTenTK.addItem(tk.getTenTK());
		}
		
		tableNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableNV.getSelectedRow();
				txtManv.setText(modelNV.getValueAt(row, 0).toString());
				cboTenTK.setSelectedItem(modelNV.getValueAt(row, 1).toString());
				cboMaca.setSelectedItem(modelNV.getValueAt(row, 2).toString());
				txtTennv.setText(modelNV.getValueAt(row, 3).toString());
				txtCMND.setText(modelNV.getValueAt(row, 4).toString());
				txtTuoi.setText(modelNV.getValueAt(row, 5).toString());
				dateNgaysinh.setDate(Date.valueOf(modelNV.getValueAt(row, 6).toString()));
				cboGioitinh.setSelectedItem(modelNV.getValueAt(row, 7));
				txtDiachi.setText(modelNV.getValueAt(row, 8).toString());
				txtTienluong.setText(modelNV.getValueAt(row, 9).toString());
				txtSDT.setText(modelNV.getValueAt(row, 10).toString());
				txtManv.setEditable(false);
				
			}
		});
		
	}
	private void DocDuLieuDBVaoTable() {
		List<NhanVien> list = nv_dao.getTatCaNhanVien();
		for(NhanVien nv : list) {
			modelNV.addRow(new Object[] {
					nv.getManv(),nv.getTentk(),nv.getMaca(),nv.getTennv(),nv.getCmnd(),nv.getTuoi(),nv.getNgaysinh(),nv.isGioitinh()== false ? "Nữ" : "Nam",nv.getDiachi(),nv.getTienluong(),nv.getSdt()
			});
		}
	}
	private boolean validData() {
		String ten=txtTennv.getText();
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
		DefaultTableModel dtm = (DefaultTableModel) tableNV.getModel();
		dtm.setRowCount(0);
	}
}

