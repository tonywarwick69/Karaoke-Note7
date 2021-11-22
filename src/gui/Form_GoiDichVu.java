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
import dao.GoiDichVu_DAO;

public class Form_GoiDichVu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableHang;
	private JTextField txtMaHD;
	private JTextField txtTenhang;
	private JTextField txtSoluong;
	private DefaultTableModel modelHang;
	private Hang_DAO h_dao;
	private JTextField txtDongia;
	private DefaultTableModel modelHoadonDV;
	private JTable tableHoadonDV;
	private GoiDichVu_DAO dv_dao;
	private JTextField txtMahang;
	private GUI_QuanLyDatPhong quanlydp;
	private JLabel lblTongtien; 
	private JLabel lblMaHD;
	



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_GoiDichVu frame = new Form_GoiDichVu();
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
	public Form_GoiDichVu() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		h_dao = new Hang_DAO();
		dv_dao = new GoiDichVu_DAO();
		ArrayList<Hang> dshddv=new ArrayList<Hang>();
		dshddv=dv_dao.getTatcaDV();
		setTitle("QUẢN LÝ HÀNG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1322, 758);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnchinh = new JPanel();
		pnchinh.setBounds(0, 0, 1308, 719);
		contentPane.add(pnchinh);
		pnchinh.setLayout(null);
		
		JPanel pnMenu = new JPanel();
		pnMenu.setBackground(new Color(255, 255, 255));
		pnMenu.setBounds(0, 0, 591, 119);
		pnchinh.add(pnMenu);
		pnMenu.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 591, 119);
		pnMenu.add(panel);
		panel.setLayout(null);
		
		JButton btnQuayLai = new JButton("Quay trở lại");
		btnQuayLai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_QuanLyDatPhong qldp= new GUI_QuanLyDatPhong();
				qldp.setVisible(true);
				dispose();
						
			}
		});
		btnQuayLai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnQuayLai.setBounds(10, 10, 124, 42);
		panel.add(btnQuayLai);
		
		JLabel lblNewLabel_2 = new JLabel("Menu dịch vụ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_2.setBounds(166, 62, 218, 32);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(589, 0, 720, 54);
		pnchinh.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Danh sách đặt");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_3.setBounds(127, 10, 324, 38);
		panel_1.add(lblNewLabel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 119, 591, 482);
		pnchinh.add(scrollPane);
		
		tableHang = new JTable();
	
		tableHang.setModel(modelHang = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Mã hàng", "Tên hàng", "Đơn giá", "Số lượng"
				}
		));
		tableHang.getColumnModel().getColumn(0).setPreferredWidth(15);
		tableHang.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableHang.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableHang.getColumnModel().getColumn(3).setPreferredWidth(50);
		DocDuLieuDBVaoTableHang();
		scrollPane.setViewportView(tableHang);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(589, 55, 720, 250);
		pnchinh.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMHng = new JLabel("Mã hóa đơn");
		lblMHng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMHng.setHorizontalAlignment(SwingConstants.CENTER);
		lblMHng.setBounds(20, 20, 78, 39);
		panel_2.add(lblMHng);
		
		JLabel lblTnHng = new JLabel("Tên hàng");
		lblTnHng.setHorizontalAlignment(SwingConstants.CENTER);
		lblTnHng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnHng.setBounds(326, 20, 97, 39);
		panel_2.add(lblTnHng);
		
		JLabel lblSLng = new JLabel("Số lượng");
		lblSLng.setHorizontalAlignment(SwingConstants.CENTER);
		lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSLng.setBounds(592, 20, 97, 39);
		panel_2.add(lblSLng);
		
		txtMaHD = new JTextField();
		txtMaHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtMaHD.setColumns(10);
		txtMaHD.setBounds(20, 69, 78, 30);
		panel_2.add(txtMaHD);
		
		txtTenhang = new JTextField();
		txtTenhang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenhang.setColumns(10);
		txtTenhang.setBounds(250, 69, 173, 30);
		txtTenhang.setEditable(false);
		panel_2.add(txtTenhang);
		
		txtSoluong = new JTextField();
		txtSoluong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSoluong.setColumns(10);
		txtSoluong.setBounds(592, 69, 97, 30);
		panel_2.add(txtSoluong);
		lblTongtien = new JLabel("");
		lblTongtien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTongtien.setBounds(799, 668, 209, 28);
		pnchinh.add(lblTongtien);
		lblMaHD = new JLabel("");
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaHD.setBounds(845, 624, 120, 28);
		pnchinh.add(lblMaHD);
		
		JButton btnChovaogio = new JButton("Cho vào giỏ hàng");
		btnChovaogio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnChovaogio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XoaHetDuLieuTableHoaDon();
				Object o = e.getSource();
				if(o.equals(btnChovaogio)) {	
						
						int mahd = Integer.parseInt(txtMaHD.getText().toString());
						int mahang = Integer.parseInt(txtMahang.getText().toString());
						String ten = txtTenhang.getText().toString();
						int soluong = Integer.parseInt(txtSoluong.getText().toString());
						double dongia = Double.parseDouble(txtDongia.getText().toString());
						double thanhtien=soluong*dongia;
						Hang h = new Hang( mahd,mahang,ten,dongia,soluong,thanhtien );
						if(!dv_dao.createHoadonDV(h))
							JOptionPane.showMessageDialog(pnchinh, "Hóa đơn dịch vụ đã tồn tại");
						
						else {
							DocDuLieuDBVaoTableHoaDonDVTheoMa(Integer.parseInt(txtMaHD.getText()));
							JOptionPane.showMessageDialog(pnchinh, "Thêm thành công");
							String mahoadondv=txtMaHD.getText();
							lblMaHD.setText(mahoadondv);
							String tongtien = dv_dao.TongTienDV(Integer.parseInt(txtMaHD.getText()));
							lblTongtien.setText(tongtien); 
							int soluongsauthanhtoan = Integer.parseInt(tableHang.getModel().getValueAt(tableHang.getSelectedRow(), 3).toString()) -soluong;
							h.setSoluong(soluongsauthanhtoan);
							h.setMahang(mahang);
							h_dao.updateThanhtoan(h);
							Form_GoiDichVu gdv = new Form_GoiDichVu();
							gdv.setVisible(true);
							dispose();
						}
			}
			}
			
		});
		
		btnChovaogio.setBounds(191, 205, 215, 46);
		panel_2.add(btnChovaogio);
	
		
		JButton btnXoa = new JButton("Xóa trắng");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							txtMaHD.setText("");
							txtMahang.setText("");
							txtTenhang.setText("");
							txtSoluong.setText("");
							txtDongia.setText("");
							txtTenhang.requestFocus();
					
				
			}
		});
	
		btnXoa.setBounds(242, 126, 110, 46);
		panel_2.add(btnXoa);
		
		JLabel lblNewLabel = new JLabel("Đơn giá");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(480, 20, 90, 39);
		panel_2.add(lblNewLabel);
		
		txtDongia = new JTextField();
		txtDongia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDongia.setBounds(452, 69, 117, 30);
		txtDongia.setEditable(false);
		panel_2.add(txtDongia);
		txtDongia.setColumns(10);
		
		txtMahang = new JTextField();
		txtMahang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMahang.setBounds(117, 71, 110, 30);
		txtMahang.setEditable(false);
		panel_2.add(txtMahang);
		txtMahang.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Mã hàng");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(117, 23, 97, 32);
		panel_2.add(lblNewLabel_4);
		
		JButton btnXemHoadonDV =  new JButton("Xem danh sách hóa đơn");
		btnXemHoadonDV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<Hang> list = dv_dao.getTatCaHoaDonDV();
				XoaHetDuLieuTableHoaDon();
				
				for(Hang h : list) {
					if((txtMaHD.getText()).equals(String.valueOf(h.getMahddv()))) {
						modelHoadonDV.addRow(new Object[] {
								h.getMahddv(),h.getTenhang(),h.getDongia(),h.getSoluong(),h.getThanhtien()
								
						});
						String mahoadondv=txtMaHD.getText();
						lblMaHD.setText(mahoadondv);
						String tongtien = dv_dao.TongTienDV(Integer.parseInt(txtMaHD.getText()));
						lblTongtien.setText(tongtien);
					}  	
				}
				 if(txtMaHD.getText().isEmpty()) {
					 DocTatCaDuLieuDBVaoTableHoaDonDV();
					String mahoadondv=txtMaHD.getText();
					lblMaHD.setText(mahoadondv);
				}
				
			}
		});
		btnXemHoadonDV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXemHoadonDV.setBounds(452, 207, 209, 44);
		panel_2.add(btnXemHoadonDV);
		//table hoadon dich vu
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 600, 591, 119);
		pnchinh.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnGoiDV = new JButton("Gọi dịch vụ");
		btnGoiDV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = e.getSource();
				if(o.equals(btnGoiDV)) {
					int row = tableHang.getSelectedRow();
					txtMahang.setText(modelHang.getValueAt(row, 0).toString());
					txtTenhang.setText(modelHang.getValueAt(row, 1).toString());
					txtDongia.setText(modelHang.getValueAt(row, 2).toString());
					
					txtDongia.setEditable(false);
					txtTenhang.setEditable(false);
					txtMahang.setEditable(false);
				}
				
			}
		});
		btnGoiDV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGoiDV.setBounds(235, 0, 114, 51);
		panel_3.add(btnGoiDV);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(589, 304, 726, 297);
		pnchinh.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Hóa đơn dịch vụ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(0, 0, 724, 33);
		panel_4.add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 32, 724, 265);
		panel_4.add(scrollPane_1);
		
		tableHoadonDV = new JTable();
		tableHoadonDV.setModel(modelHoadonDV=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã hóa đơn", "Tên hàng", "Đơn giá", "Số lượng", "Thành tiền"
			}
		));
		
		tableHoadonDV.getColumnModel().getColumn(0).setPreferredWidth(15);
		tableHoadonDV.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableHoadonDV.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableHoadonDV.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableHoadonDV.getColumnModel().getColumn(4).setPreferredWidth(50);
		scrollPane_1.setViewportView(tableHoadonDV);
		
		JLabel lblNewLabel_5 = new JLabel("Tổng tiền dịch vụ:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(648, 668, 165, 28);
		pnchinh.add(lblNewLabel_5);
		JButton btnThanhtoanDV = new JButton("Thanh toán dịch vụ");
		
		btnThanhtoanDV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					
					String tongtien= lblTongtien.getText();
					String mahoadon = lblMaHD.getText();
					GUI_QuanLyDatPhong qldatphong = new GUI_QuanLyDatPhong();
					qldatphong.my_update(mahoadon,tongtien);
					qldatphong.setVisible(true);
					dispose();
			}
		});
		btnThanhtoanDV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThanhtoanDV.setBounds(1032, 624, 248, 39);
		pnchinh.add(btnThanhtoanDV);
		
		JLabel lblNewLabel_6 = new JLabel("Hóa đơn số:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(654, 619, 136, 28);
		pnchinh.add(lblNewLabel_6);
		
		
		
	}
	private void DocDuLieuDBVaoTableHang() {
		List<Hang> list = h_dao.getHangs();
		for(Hang h : list) {
			modelHang.addRow(new Object[] {
					h.getMahang(), h.getTenhang(), h.getDongia(), h.getSoluong()
			});
		}
	}
	private void DocDuLieuDBVaoTableHoaDonDV() {
		List<Hang> list = dv_dao.getTatCaHoaDonDV();
		for(Hang h : list) {
			modelHoadonDV.addRow(new Object[] {
					h.getMahddv(),h.getTenhang(),h.getDongia(),h.getSoluong(),h.getThanhtien()
			});
		}
	}
	private void DocTatCaDuLieuDBVaoTableHoaDonDV() {
		List<Hang> list = dv_dao.getTatCaMaHoaDonDV();
		for(Hang h : list) {
			modelHoadonDV.addRow(new Object[] {
					h.getMahddv(),h.getTenhang(),h.getDongia(),h.getSoluong(),h.getThanhtien()
			});
		}
	}
	private void DocDuLieuDBVaoTableHoaDonDVTheoMa(int ma) {
		List<Hang> list = dv_dao.getTimMaHoaDon(ma);
		for(Hang h : list) {
			modelHoadonDV.addRow(new Object[] {
					h.getMahddv(),h.getTenhang(),h.getDongia(),h.getSoluong(),h.getThanhtien()
			});
		}
	}
	private void XoaHetDuLieuTableHoaDon() {
		DefaultTableModel dtm = (DefaultTableModel) tableHoadonDV.getModel();
		dtm.setRowCount(0);
	}
}
