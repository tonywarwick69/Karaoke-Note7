package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import connectDB.ConnectDB;
import dao.Phong_DAO;
import entity.Phong;
import util.DateUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class GUI_QLPhong extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLoaiPhong;
	private JTextField txtGiaThuePhong;
	private JTable tablePhong;
	private JTextField txtMaPhong;
	private Phong_DAO p_dao;
	private DefaultTableModel modelPhong;
	private JTextField txtThongBao;
	private JComboBox cboTinhtrang_1;
	private JComboBox cboTimMaPhong;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_QLPhong frame = new GUI_QLPhong();
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
	public GUI_QLPhong() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		p_dao = new Phong_DAO();
		
		setTitle("QUẢN LÝ PHÒNG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1611, 882);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JPanel pnChinh = new JPanel();
		pnChinh.setBackground(Color.WHITE);
		pnChinh.setBounds(0, 0, 1550, 854);
		contentPane.add(pnChinh);
		pnChinh.setLayout(null);
		
		JButton btnQuayLai = new JButton("QUAY LẠI");
		btnQuayLai.setBounds(56, 37, 173, 80);
		btnQuayLai.setForeground(new Color(139, 0, 0));
		btnQuayLai.setBackground(new Color(100, 149, 237));
		btnQuayLai.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnQuayLai.setMnemonic(KeyEvent.VK_Q);
		btnQuayLai.setToolTipText("Bấm Alt + Q");
		pnChinh.add(btnQuayLai);
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Chinh chinh = new GUI_Chinh();
				chinh.setVisible(true);
				dispose();
			}
		});
	
		
		JPanel pnBang = new JPanel();
		pnBang.setBounds(0, 193, 1550, 900);
		pnChinh.add(pnBang);
		pnBang.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 68, 989, 595);
		pnBang.add(scrollPane);
		//tinhtrang=0=false -> " Phòng trống " tinhtrang=1=true -> "Phòng đang sử dụng"
		JComboBox cboTinhtrang_1 = new JComboBox();
		cboTinhtrang_1.setBounds(1155, 292, 264, 22);
		pnBang.add(cboTinhtrang_1);
		cboTinhtrang_1.addItem("Phòng trống");
		cboTinhtrang_1.addItem("Phòng đang sử dụng");
		
		
	
		JComboBox cboTimMaPhong = new JComboBox();
		cboTimMaPhong.setBounds(736, 68, 238, 50);
		pnChinh.add(cboTimMaPhong);
		cboTimMaPhong.setEditable(true);
		ArrayList<Phong> listPhong= p_dao.getTatCaPhong();
		//Tao list combox phòng
		for(Phong p: listPhong) {
			cboTimMaPhong.addItem(p.getMaphong());
		}
		cboTimMaPhong.setSelectedItem("");
		AutoCompleteDecorator.decorate(cboTimMaPhong);
		
		tablePhong = new JTable();
		tablePhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tablePhong.getSelectedRow();
				txtMaPhong.setText(modelPhong.getValueAt(row, 0).toString());
				txtLoaiPhong.setText(modelPhong.getValueAt(row, 1).toString());
				txtGiaThuePhong.setText(modelPhong.getValueAt(row, 2).toString());
				cboTinhtrang_1.setSelectedItem(modelPhong.getValueAt(row, 3).toString());
				txtMaPhong.setEditable(false);
			}
		});
		tablePhong.setModel(modelPhong = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã phòng ", "Loại phòng", "Giá thuê phòng", "Tình trạng"
			}
		));
		DocDuLieuDBVaoTable();
		tablePhong.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablePhong.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablePhong.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablePhong.getColumnModel().getColumn(3).setPreferredWidth(100);
		
		
		scrollPane.setViewportView(tablePhong);
		
		txtMaPhong = new JTextField();
		txtMaPhong.setBounds(1155, 119, 264, 20);
		pnBang.add(txtMaPhong);
		txtMaPhong.setEditable(false);
		txtMaPhong.setColumns(10);
		
		JLabel lblMaPhong = new JLabel("Mã phòng:");
		lblMaPhong.setBounds(999, 115, 146, 24);
		pnBang.add(lblMaPhong);
		lblMaPhong.setForeground(new Color(0, 0, 205));
		lblMaPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblLoaiPhong = new JLabel("Loại Phòng:");
		lblLoaiPhong.setBounds(999, 168, 146, 24);
		pnBang.add(lblLoaiPhong);
		lblLoaiPhong.setForeground(new Color(0, 0, 205));
		lblLoaiPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblGiaThuePhong = new JLabel("Giá Phòng:");
		lblGiaThuePhong.setBounds(999, 230, 146, 24);
		pnBang.add(lblGiaThuePhong);
		lblGiaThuePhong.setForeground(new Color(0, 0, 205));
		lblGiaThuePhong.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblTinhTrang = new JLabel("Tình Trạng:");
		lblTinhTrang.setBounds(999, 290, 146, 24);
		pnBang.add(lblTinhTrang);
		lblTinhTrang.setForeground(new Color(0, 0, 205));
		lblTinhTrang.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtLoaiPhong = new JTextField();
		txtLoaiPhong.setBounds(1155, 172, 264, 20);
		pnBang.add(txtLoaiPhong);
		txtLoaiPhong.setColumns(10);
		
		txtGiaThuePhong = new JTextField();
		txtGiaThuePhong.setBounds(1155, 234, 264, 20);
		pnBang.add(txtGiaThuePhong);
		txtGiaThuePhong.setColumns(10);
		
		
		JButton btnThem = new JButton("THÊM");
		btnThem.setBounds(1028, 384, 173, 80);
		pnBang.add(btnThem);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if(o.equals(btnThem)) {
					if(validData()) {
						String loai = txtLoaiPhong.getText();
						Double gia = Double.valueOf(txtGiaThuePhong.getText());
						boolean tinhTrang = cboTinhtrang_1.getSelectedItem()=="Phòng trống" ? false : true ;
						Phong p = new Phong(loai, gia, tinhTrang);
						if(!p_dao.create(p))
							JOptionPane.showMessageDialog(pnChinh, "Thêm phòng thất bại");
						else {
							JOptionPane.showMessageDialog(pnChinh, "Thêm phòng thành công");
							GUI_QLPhong qlp=new GUI_QLPhong();
							qlp.setVisible(true);
							dispose();
						
						}
					}
				}
			}

			
		});
		btnThem.setForeground(new Color(139, 0, 0));
		btnThem.setBackground(new Color(100, 149, 237));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThem.setMnemonic(KeyEvent.VK_T);
		btnThem.setToolTipText("Bấm Alt + T");
		
				JButton btnSua = new JButton("SỬA");
				btnSua.setBounds(1262, 384, 173, 80);
				pnBang.add(btnSua);
				btnSua.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Object o = e.getSource();
						if(validData()) {
							int row = tablePhong.getSelectedRow();
							if(row < 0)
							{
								return;
							}
							modelPhong.setValueAt(txtMaPhong.getText().trim(), row, 0);
							modelPhong.setValueAt(txtLoaiPhong.getText().trim(), row, 1);
							modelPhong.setValueAt(txtGiaThuePhong.getText().trim(), row, 2);
							modelPhong.setValueAt(cboTinhtrang_1.getSelectedItem(), row, 3);

							//tinhtrang=0=false -> "Phòng trống" tinhtrang=1=true -> "Phòng đang sử dụng"
							Phong p = new Phong();
							p.setMaphong(Integer.parseInt(txtMaPhong.getText().trim()));
							p.setLoaiphong(txtLoaiPhong.getText().trim());
							Double gia = Double.valueOf(txtGiaThuePhong.getText());
							p.setGiathuephong(gia);
							p.setTinhtrang(cboTinhtrang_1.getSelectedItem()==  "Phòng trống" ? false:true);
							p_dao.update(p);
							if(!p_dao.update(p))
								JOptionPane.showMessageDialog(null, "Sửa thông tin phòng thất bại");
							else {
								JOptionPane.showMessageDialog(null, "Đã sửa thông tin phòng");
								GUI_QLPhong qlp = new GUI_QLPhong();
								qlp.setVisible(true);
								dispose();
							}
						}	
					}
				});
				btnSua.setForeground(new Color(139, 0, 0));
				btnSua.setBackground(new Color(100, 149, 237));
				btnSua.setFont(new Font("Tahoma", Font.BOLD, 13));
				btnSua.setMnemonic(KeyEvent.VK_S);
				btnSua.setToolTipText("Bấm Alt + S");
						
							
							JButton btnXoaTrang = new JButton("XÓA TRẮNG");
							btnXoaTrang.setBounds(1028, 517, 173, 80);
							pnBang.add(btnXoaTrang);
							btnXoaTrang.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									Object o = e.getSource();
									if(o.equals(btnXoaTrang)) {
										txtMaPhong.setText("");
										txtMaPhong.setEditable(false);
										txtLoaiPhong.setText("");
										txtGiaThuePhong.setText("");
										cboTinhtrang_1.setSelectedItem(null);
										txtLoaiPhong.requestFocus();
										
									}
								}
							});
							btnXoaTrang.setForeground(new Color(139, 0, 0));
							btnXoaTrang.setBackground(new Color(100, 149, 237));
							btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 13));
							btnXoaTrang.setMnemonic(KeyEvent.VK_R);
							btnXoaTrang.setToolTipText("Bấm Alt + R");
							
							JPanel panel = new JPanel();
							panel.setBounds(989, 0, 562, 94);
							pnBang.add(panel);
							panel.setLayout(null);
							
							JLabel lblNewLabel = new JLabel("Thông tin phòng");
							lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
							lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
							lblNewLabel.setBounds(172, 5, 338, 78);
							panel.add(lblNewLabel);
							
							JButton btnXoa = new JButton("XÓA");
							btnXoa.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									Object o = e.getSource();
									if(o.equals(btnXoa)) {
										int row = tablePhong.getSelectedRow();
										if(row == -1)
											JOptionPane.showMessageDialog(pnChinh, "Bạn chưa chọn dòng để xóa");
										else {
											int xoa;
											xoa = JOptionPane.showConfirmDialog(pnChinh, "Bạn có muốn xóa dòng đã chọn?", "Nhắc nhở", JOptionPane.YES_NO_OPTION);
											if(xoa == JOptionPane.YES_OPTION) {
												modelPhong.removeRow(row);
												int ma = Integer.parseInt(txtMaPhong.getText());
												p_dao.delete(ma);
												JOptionPane.showMessageDialog(pnChinh, "Xóa thành công");
												txtMaPhong.setText("");
												txtLoaiPhong.setText("");
												cboTinhtrang_1.setSelectedItem(null);
												txtGiaThuePhong.setText("");
												txtLoaiPhong.requestFocus();
												
											}
										}
									}
								}
							});
							btnXoa.setToolTipText("Bấm Alt + X");
							btnXoa.setMnemonic(KeyEvent.VK_X);
							btnXoa.setForeground(new Color(139, 0, 0));
							btnXoa.setFont(new Font("Tahoma", Font.BOLD, 13));
							btnXoa.setBackground(new Color(100, 149, 237));
							btnXoa.setBounds(1262, 517, 173, 80);
							pnBang.add(btnXoa);
							
							JLabel lblNewLabel_1 = new JLabel("Danh sách phòng");
							lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
							lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
							lblNewLabel_1.setBounds(299, 10, 278, 32);
							pnBang.add(lblNewLabel_1);
		
		txtThongBao = new JTextField();
		txtThongBao.setForeground(Color.RED);
		txtThongBao.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		txtThongBao.setEditable(false);
		txtThongBao.setBorder(null);
		txtThongBao.setBackground(Color.WHITE);
		txtThongBao.setBounds(91, 175, 379, 19);
		pnChinh.add(txtThongBao);
		
	
	
		JButton btnTimMa = new JButton("Tìm kiếm mã phòng");
		btnTimMa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o= e.getSource();
				if(o.equals(btnTimMa)) {
					XoaHetDuLieuTable();
					List<Phong> listTimKiemTheoMa= p_dao.getTimKiemTheoMa(Integer.parseInt(cboTimMaPhong.getSelectedItem().toString()));
					cboTimMaPhong.setSelectedItem("");
					for(Phong p: listTimKiemTheoMa) {
						modelPhong.addRow(new Object[] {
								p.getMaphong(), p.getLoaiphong(), p.getGiathuephong(), p.isTinhtrang() == false ? "Phòng trống" : "Phòng đang sử dụng"
						});
					}
				}
			}
		});
		btnTimMa.setBounds(566, 67, 146, 50);
		pnChinh.add(btnTimMa);
		
		
	
		
		
	}

	private void DocDuLieuDBVaoTable() {
		List<Phong> list = p_dao.getTatCaPhong();
		for(Phong p : list) {
			modelPhong.addRow(new Object[] {
					p.getMaphong(), p.getLoaiphong(), p.getGiathuephong(), p.isTinhtrang() == false ? "Phòng trống" : "Phòng đang sử dụng"
			});
		}
	}
	private boolean validData() {
		
		String loai = txtLoaiPhong.getText();
		double gia = Double.parseDouble(txtGiaThuePhong.getText());
		if(!(loai.length() > 0)) {
			showMessage("Loại phòng không được để trống", txtLoaiPhong);
			return false;
		}
		else if(!(loai.matches("[^&%$#@!~_',/\\.?();]*"))) {
			showMessage("Loại phòng không được nhập ký tự đặc biệt", txtLoaiPhong);
			return false;
		}

		if(gia < 0) {
			showMessage("Giá phòng phải lớn hơn 0", txtGiaThuePhong);
			return false;
		}
	return true;
	}
	private void XoaHetDuLieuTable() {
		DefaultTableModel dtm = (DefaultTableModel) tablePhong.getModel();
		dtm.setRowCount(0);
	}
	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		txtThongBao.setText(message);
	}
}

