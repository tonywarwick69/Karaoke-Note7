package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Hang;
import entity.Phong;
import dao.Hang_DAO;
import javax.swing.JComboBox;
public class GUI_QLHang extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableHang1;
	private JTextField txtMahang;
	private JTextField txtTenhang;
	private JTextField txtLoaihang;
	private JTextField txtDongia;
	private JTextField txtSoluong;
	private JTextField txtNhaSX;
	private DefaultTableModel modelHang;
	private JTextField txtThongbao;
	private Hang_DAO h_dao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_QLHang frame = new GUI_QLHang();
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
	public GUI_QLHang() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setTitle("QUẢN LÝ HÀNG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1195, 758);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnchinh = new JPanel();
		pnchinh.setBounds(0, 0, 1179, 719);
		contentPane.add(pnchinh);
		pnchinh.setLayout(null);
		
		JPanel pnchucnang = new JPanel();
		pnchucnang.setBackground(new Color(255, 255, 255));
		pnchucnang.setBounds(0, 0, 1179, 119);
		pnchinh.add(pnchucnang);
		pnchucnang.setLayout(null);
		
		JButton btnQuayLai = new JButton("Quay trở lại");
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUI_Chinh chinh = new GUI_Chinh();
				chinh.setVisible(true);
				dispose();
			}
		});
		btnQuayLai.setBounds(10, 62, 154, 46);
		pnchucnang.add(btnQuayLai);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÝ HÀNG");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(464, 11, 253, 46);
		pnchucnang.add(lblNewLabel);
		
	
		
		h_dao = new Hang_DAO();
		ArrayList<Hang> dsh = h_dao.getHangs();
		JComboBox cboTimkiem = new JComboBox();
		cboTimkiem.setBounds(408, 74, 221, 34);
		cboTimkiem.setEditable(true);
		for(Hang h : dsh) {
			cboTimkiem.addItem(h.getTenhang());
		}
		pnchucnang.add(cboTimkiem);
		//
		JButton btnTimTenHang = new JButton("Tìm kiếm theo tên hàng");
		btnTimTenHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o= e.getSource();
				if(o.equals(btnTimTenHang)) {
					XoaHetDuLieuTable();
					List<Hang> listTimTheoTen= h_dao.getTimKiemTheoTen(cboTimkiem.getSelectedItem().toString());
					cboTimkiem.setSelectedItem("");
					for(Hang h: listTimTheoTen) {
						modelHang.addRow(new Object[] {
								h.getMahang(), h.getTenhang(), h.getLoaihang(), h.getDongia(), h.getSoluong(), h.getNhasanxuat()
						});
					}
				}
			}
		});
		btnTimTenHang.setBounds(219, 75, 166, 33);
		pnchucnang.add(btnTimTenHang);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 119, 782, 54);
		pnchinh.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Danh Sách Hàng");
		lblNewLabel_2.setBounds(275, 11, 218, 32);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 26));
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(781, 119, 398, 54);
		pnchinh.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Thông Tin Hàng");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_3.setBounds(41, 0, 324, 38);
		panel_1.add(lblNewLabel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 173, 782, 546);
		pnchinh.add(scrollPane);
		
		tableHang1 = new JTable();
		tableHang1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableHang1.getSelectedRow();
				txtMahang.setText(modelHang.getValueAt(row, 0).toString());
				txtTenhang.setText(modelHang.getValueAt(row, 1).toString());
				txtLoaihang.setText(modelHang.getValueAt(row, 2).toString());
				txtDongia.setText(modelHang.getValueAt(row, 3).toString());
				txtSoluong.setText(modelHang.getValueAt(row, 4).toString());
				txtNhaSX.setText(modelHang.getValueAt(row, 5).toString());
				txtMahang.setEditable(false);
			}
		});
		tableHang1.setModel(modelHang = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Mã hàng", "Tên hàng", "Loại hàng", "Đơn giá", "Số lượng", "Nhà sản xuất"
				}
		));
		tableHang1.getColumnModel().getColumn(0).setPreferredWidth(15);
		tableHang1.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableHang1.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableHang1.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableHang1.getColumnModel().getColumn(4).setPreferredWidth(50);
		tableHang1.getColumnModel().getColumn(5).setPreferredWidth(50);
		DocDuLieuDBVaoTable();
		scrollPane.setViewportView(tableHang1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(781, 173, 398, 546);
		pnchinh.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMHng = new JLabel("Mã hàng:");
		lblMHng.setBounds(10, 11, 97, 39);
		panel_2.add(lblMHng);
		
		JLabel lblTnHng = new JLabel("Tên hàng:");
		lblTnHng.setBounds(10, 61, 97, 39);
		panel_2.add(lblTnHng);
		
		JLabel lblLoiHng = new JLabel("Loại hàng:");
		lblLoiHng.setBounds(10, 111, 97, 39);
		panel_2.add(lblLoiHng);
		
		JLabel lblnGi = new JLabel("Đơn giá:");
		lblnGi.setBounds(10, 161, 97, 39);
		panel_2.add(lblnGi);
		
		JLabel lblSLng = new JLabel("Số lượng:");
		lblSLng.setBounds(10, 211, 97, 39);
		panel_2.add(lblSLng);
		
		JLabel lblNhSnXut = new JLabel("Nhà sản xuất:");
		lblNhSnXut.setBounds(10, 261, 97, 39);
		panel_2.add(lblNhSnXut);
		
		txtMahang = new JTextField();
		txtMahang.setEditable(false);
		txtMahang.setColumns(10);
		txtMahang.setBounds(117, 20, 218, 30);
		panel_2.add(txtMahang);
		
		txtTenhang = new JTextField();
		txtTenhang.setColumns(10);
		txtTenhang.setBounds(117, 70, 218, 30);
		panel_2.add(txtTenhang);
		
		txtLoaihang = new JTextField();
		txtLoaihang.setColumns(10);
		txtLoaihang.setBounds(117, 120, 218, 30);
		panel_2.add(txtLoaihang);
		
		txtDongia = new JTextField();
		txtDongia.setColumns(10);
		txtDongia.setBounds(117, 170, 218, 30);
		panel_2.add(txtDongia);
		
		txtSoluong = new JTextField();
		txtSoluong.setColumns(10);
		txtSoluong.setBounds(117, 220, 218, 30);
		panel_2.add(txtSoluong);
		
		txtNhaSX = new JTextField();
		txtNhaSX.setColumns(10);
		txtNhaSX.setBounds(117, 270, 218, 30);
		panel_2.add(txtNhaSX);
		
		JButton btnThem = new JButton("Thêm hàng");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if(o.equals(btnThem)) {
					if(validData()) {	
						String ten = txtTenhang.getText();
						String loai = txtLoaihang.getText();
						Double gia = Double.valueOf(txtDongia.getText());
						int soluong = Integer.parseInt(txtSoluong.getText());
						String nsx = txtNhaSX.getText();
						Hang h = new Hang( ten, loai, gia,soluong, nsx);
						if(!h_dao.create(h))
							JOptionPane.showMessageDialog(pnchinh, "Thêm hàng thất bại");
						else {
							
							
							JOptionPane.showMessageDialog(pnchinh, "Thêm hàng thành công");
							GUI_QLHang qlhang=new GUI_QLHang();
							qlhang.setVisible(true);
							dispose();
						}
					}
				}
				
			}

			
		});
		
		btnThem.setBounds(32, 311, 110, 46);
		panel_2.add(btnThem);
		
		JButton btnXoa = new JButton("Xóa hàng");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if(o.equals(btnXoa)) {
					int row = tableHang1.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(pnchinh, "Bạn chưa chọn dòng để xóa");
					else {
						int xoa;
						xoa = JOptionPane.showConfirmDialog(pnchinh, "Bạn có muốn xóa dòng đã chọn?", "Nhắc nhở", JOptionPane.YES_NO_OPTION);
						if(xoa == JOptionPane.YES_OPTION) {
							modelHang.removeRow(row);
							int ma = Integer.parseInt(txtMahang.getText());
							h_dao.delete(ma);
							JOptionPane.showMessageDialog(pnchinh, "Xóa thành công");
							txtMahang.setText("");
							txtTenhang.setText("");
							txtLoaihang.setText("");
							txtDongia.setText("");
							txtNhaSX.setText("");
							txtSoluong.setText("");
							txtMahang.requestFocus();
						}
						else {
							GUI_QLHang qlh =new GUI_QLHang();
							qlh.setVisible(true);
							dispose();
						}
					}
				}
			}
		});
	
		btnXoa.setBounds(32, 381, 110, 46);
		panel_2.add(btnXoa);
		
		JButton btnSua = new JButton("Sửa hàng");
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validData()) {
					int row = tableHang1.getSelectedRow();
					if(row < 0)
					{
						return;
					}
					modelHang.setValueAt(txtMahang.getText().trim(), row, 0);
					modelHang.setValueAt(txtTenhang.getText().trim(), row, 1);
					modelHang.setValueAt(txtLoaihang.getText().trim(), row, 2);
					modelHang.setValueAt(txtDongia.getText().trim(), row, 3);
					modelHang.setValueAt(txtSoluong.getText().trim(), row, 4);
					modelHang.setValueAt(txtNhaSX.getText().trim(), row, 5);
					Hang h = new Hang();
					h.setMahang(Integer.parseInt(txtMahang.getText().trim()));
					h.setTenhang(txtTenhang.getText().trim());
					h.setLoaihang(txtLoaihang.getText().trim());
					Double gia = Double.valueOf(txtDongia.getText());
					h.setNhasanxuat(txtNhaSX.getText().trim());
					h.setSoluong(Integer.parseInt(txtSoluong.getText().trim()));
					h.setDongia(gia);
					
					h_dao.update(h);
				}	
			}
		});
		
		btnSua.setBounds(248, 311, 110, 46);
		panel_2.add(btnSua);
		
		txtThongbao = new JTextField();
		txtThongbao.setForeground(Color.RED);
		txtThongbao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtThongbao.setEditable(false);
		txtThongbao.setBorder(null);
		txtThongbao.setBounds(10, 368, 378, 20);
		panel_2.add(txtThongbao);
		
		JButton btnClear = new JButton("Xóa trắng");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMahang.setText("");
				txtTenhang.setText("");
				txtLoaihang.setText("");
				txtDongia.setText("");
				txtNhaSX.setText("");
				txtSoluong.setText("");
				txtMahang.requestFocus();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClear.setBounds(250, 381, 108, 46);
		panel_2.add(btnClear);
	}
	private void DocDuLieuDBVaoTable() {
		List<Hang> list = h_dao.getHangs();
		for(Hang h : list) {
			modelHang.addRow(new Object[] {
					h.getMahang(), h.getTenhang(), h.getLoaihang(), h.getDongia(), h.getSoluong(), h.getNhasanxuat()
			});
		}
	}
	private void XoaHetDuLieuTable() {
		DefaultTableModel dtm = (DefaultTableModel) tableHang1.getModel();
		dtm.setRowCount(0);
	}
	
	private boolean validData() {
		String loai = txtLoaihang.getText();
		double gia = Double.parseDouble(txtDongia.getText());
		String nsx = txtNhaSX.getText();
		int soluong = Integer.parseInt(txtSoluong.getText());
		
		if(!(loai.length() > 0 )) {
			showMessage("Loại hàng không được để trống", txtLoaihang);
			return false;
		}
		if(gia < 0) {
			showMessage("Giá hàng phải lớn hơn 0", txtDongia);
			return false;
		}
		
		if(!(nsx.length() > 0 )) {
			showMessage("Nhà sản xuất không được để trống", txtNhaSX);
			return false;
		}
		if(soluong < 0) {
			showMessage("Số lượng phải lớn hơn 0", txtSoluong);
			return false;
		}
	return true;
		// TODO Auto-generated method stub
	}

	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		txtThongbao.setText(message);
		// TODO Auto-generated method stub
		
	}
}
