package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.HoaDon_DAO;
import dao.NhanVien_DAO;
import entity.HoaDon;
import entity.NhanVien;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class GUI_QLHoaDon extends JFrame {

	private JPanel contentPane;
	private JTable tableHoadon;
	private DefaultTableModel modelHD;
	private HoaDon_DAO hd_dao;
	private JTextField txtMaHD;
	private JTextField txtMaphong;
	private JTextField txtNgaylapHD;
	private JTextField txtThanhtien;
	private JComboBox cboMaNV;
	private NhanVien_DAO nv_dao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_QLHoaDon frame = new GUI_QLHoaDon();
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
	public GUI_QLHoaDon() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1338, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		hd_dao = new HoaDon_DAO();
		
		JPanel pnTieude = new JPanel();
		pnTieude.setLayout(null);
		pnTieude.setBounds(0, 0, 888, 72);
		contentPane.add(pnTieude);
		
		JButton btnQuaylai = new JButton("Quay lại");
		btnQuaylai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_Chinh main = new GUI_Chinh();
				main.setVisible(true);
				dispose();
			}
		});
		btnQuaylai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnQuaylai.setBounds(10, 10, 114, 52);
		pnTieude.add(btnQuaylai);
		
		JComboBox cboTimMaHD = new JComboBox();
		cboTimMaHD.setEditable(true);
		cboTimMaHD.setBounds(528, 12, 195, 52);
		pnTieude.add(cboTimMaHD);
		ArrayList<HoaDon> listHD =hd_dao.getAllHoaDon();
		for(HoaDon hd: listHD) {
			cboTimMaHD.addItem(hd.getMahd());
		}
		
		JButton btnTimMaHD = new JButton("Tìm Mã hóa đơn");
		btnTimMaHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				XoaHetDuLieuTable();
				List<HoaDon> listTimKiemTheoMa= hd_dao.getTimKiemTheoMa(Integer.parseInt(cboTimMaHD.getSelectedItem().toString()));
				cboTimMaHD.setSelectedItem("");
				for(HoaDon hd: listTimKiemTheoMa) {
					modelHD.addRow(new Object[] {
							hd.getMahd(),hd.getMaphong(),hd.getManv(),hd.getTennv(),hd.getNgaylaphd(),hd.getThanhtien()
					});
				}
			}
		});
		btnTimMaHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTimMaHD.setBounds(353, 10, 148, 52);
		pnTieude.add(btnTimMaHD);
		
		JLabel lblMaHDDV = new JLabel("");
		lblMaHDDV.setBounds(0, 0, 0, 0);
		pnTieude.add(lblMaHDDV);
		
		JPanel pnBang = new JPanel();
		pnBang.setLayout(null);
		pnBang.setBounds(0, 71, 888, 526);
		contentPane.add(pnBang);
		
		JLabel lblNewLabel = new JLabel("Danh sách hóa đơn");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(318, 10, 276, 36);
		pnBang.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 55, 888, 478);
		pnBang.add(scrollPane);
		
		
		tableHoadon = new JTable();
		tableHoadon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableHoadon.getSelectedRow();
				txtMaHD.setText(modelHD.getValueAt(row, 0).toString());
				txtMaphong.setText(modelHD.getValueAt(row, 1).toString());
				cboMaNV.setSelectedItem(modelHD.getValueAt(row, 2).toString());
				
				txtNgaylapHD.setText(modelHD.getValueAt(row, 4).toString());
				txtThanhtien.setText(modelHD.getValueAt(row, 5).toString());
				txtMaHD.setEditable(false);
			}
		});
		scrollPane.setViewportView(tableHoadon);
		
		tableHoadon.setModel(modelHD= new DefaultTableModel(
				new Object[] [] {
				},
				new String[] {
						"Mã hóa đơn","Mã phòng","Mã nhân viên","Tên nhân viên","Ngày lập hóa đơn","Thành tiền"
				}
				));
		tableHoadon.getColumnModel().getColumn(0).setPreferredWidth(15);
		tableHoadon.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableHoadon.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableHoadon.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableHoadon.getColumnModel().getColumn(4).setPreferredWidth(50);
		tableHoadon.getColumnModel().getColumn(5).setPreferredWidth(50);
		DocDuLieuDBVaoTable();
		
		JButton btnClear = new JButton("Xóa trắng");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClear.setBounds(746, 13, 111, 37);
		pnBang.add(btnClear);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin hóa đơn");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(983, 79, 233, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mã hóa đơn:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(918, 144, 99, 27);
		contentPane.add(lblNewLabel_2);
		
		txtMaHD = new JTextField();
		txtMaHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMaHD.setEditable(false);
		txtMaHD.setColumns(10);
		txtMaHD.setBounds(1050, 147, 245, 21);
		contentPane.add(txtMaHD);
		
		JLabel lblNewLabel_3 = new JLabel("Mã phòng:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(918, 221, 99, 27);
		contentPane.add(lblNewLabel_3);
		
		txtMaphong = new JTextField();
		txtMaphong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMaphong.setColumns(10);
		txtMaphong.setBounds(1050, 221, 245, 21);
		contentPane.add(txtMaphong);
		
		JLabel lblNewLabel_4 = new JLabel("Mã nhân viên:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(918, 280, 99, 33);
		contentPane.add(lblNewLabel_4);
		
		 cboMaNV = new JComboBox();
		cboMaNV.setBounds(1050, 284, 245, 26);
		contentPane.add(cboMaNV);
		nv_dao=new NhanVien_DAO();
		ArrayList<NhanVien> listnv=nv_dao.getTatCaNhanVien();
		for(NhanVien nv : listnv) {
			cboMaNV.addItem(nv.getManv());
		}
		
		JLabel lblNewLabel_6 = new JLabel("Ngày lập HD:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(918, 349, 99, 27);
		contentPane.add(lblNewLabel_6);
		
		txtNgaylapHD = new JTextField();
		txtNgaylapHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNgaylapHD.setColumns(10);
		txtNgaylapHD.setBounds(1050, 352, 245, 21);
		contentPane.add(txtNgaylapHD);
		
		JLabel lblNewLabel_7 = new JLabel("Thành tiền:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setBounds(913, 417, 104, 27);
		contentPane.add(lblNewLabel_7);
		
		txtThanhtien = new JTextField();
		txtThanhtien.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtThanhtien.setEditable(true);
		txtThanhtien.setColumns(10);
		txtThanhtien.setBounds(1050, 420, 245, 21);
		contentPane.add(txtThanhtien);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableHoadon.getSelectedRow();
				if(row <0) {
					return ;
				}
				modelHD.setValueAt(txtMaHD.getText().trim(), row, 0);
				modelHD.setValueAt(txtMaphong.getText().trim(), row, 1);
				modelHD.setValueAt(cboMaNV.getSelectedItem().toString().trim(), row, 2);
				//modelHD.setValueAt(txtTenNV.getText().trim(), row, 3);
				modelHD.setValueAt(txtNgaylapHD.getText().trim(), row, 3);
				modelHD.setValueAt(txtThanhtien.getText().trim(), row, 4);
				HoaDon hd = new HoaDon();
				hd.setMahd(Integer.parseInt(txtMaHD.getText().trim()));
				hd.setMaphong(Integer.parseInt(txtMaphong.getText().trim()));
				hd.setManv(Integer.parseInt(cboMaNV.getSelectedItem().toString().trim()));
				hd.setNgaylaphd(Date.valueOf(txtNgaylapHD.getText().trim()));
				hd.setThanhtien(Double.parseDouble(txtThanhtien.getText().trim()));
				hd_dao.update(hd);
				if(!hd_dao.update(hd))
					JOptionPane.showMessageDialog(pnBang, "Sửa hóa đơn thất bại");
				else {
					
					
					JOptionPane.showMessageDialog(pnBang, "Sửa hóa đơn thành công");
					GUI_LapHoaDon qlhd=new GUI_LapHoaDon();
					qlhd.setVisible(true);
					dispose();
				}
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSua.setBounds(918, 487, 136, 33);
		contentPane.add(btnSua);
		
		JButton btnXoa = new JButton("Xóa trắng");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoa.setBounds(1150, 487, 136, 33);
		contentPane.add(btnXoa);
	}
	private void DocDuLieuDBVaoTable() {
		List<HoaDon> list = hd_dao.getAllHoaDon();
		for(HoaDon hd : list) {
			modelHD.addRow(new Object[] {
					hd.getMahd(),hd.getMaphong(),hd.getManv(),hd.getTennv(),hd.getNgaylaphd(),hd.getThanhtien()
			});
		}
	}

	private void XoaHetDuLieuTable() {
		DefaultTableModel dtm = (DefaultTableModel) tableHoadon.getModel();
		dtm.setRowCount(0);
	}
}
