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
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import entity.CaTruc;
import entity.NhanVien;
import dao.CaTruc_DAO;
public class GUI_CaTruc extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTimkiem;
	private JTable tableCaTruc1;
	private JTextField txtMaca;
	private JTextField txtLoaica;
	private JTextField txtThoigianlam;
	private DefaultTableModel modelCaTruc;
	private JTextField txtThongbao;
	private CaTruc_DAO ct_dao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_CaTruc frame = new GUI_CaTruc();
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
	public GUI_CaTruc() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ct_dao = new CaTruc_DAO();
		setTitle("QUẢN LÝ CA TRỰC");
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
		
		JLabel lblNewLabel = new JLabel("QUẢN LÝ CA TRỰC");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(464, 11, 253, 46);
		pnchucnang.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm theo loại ca:\r\n");
		lblNewLabel_1.setBounds(202, 94, 135, 14);
		pnchucnang.add(lblNewLabel_1);
		
		txtTimkiem = new JTextField();
		txtTimkiem.setBounds(361, 88, 259, 20);
		pnchucnang.add(txtTimkiem);
		txtTimkiem.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 119, 782, 54);
		pnchinh.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Danh Sách Ca Trực");
		lblNewLabel_2.setBounds(258, 11, 325, 32);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 26));
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(781, 119, 398, 54);
		pnchinh.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Thông Tin Ca Trực");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_3.setBounds(41, 0, 324, 38);
		panel_1.add(lblNewLabel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 173, 782, 546);
		pnchinh.add(scrollPane);
		
		tableCaTruc1 = new JTable();
		tableCaTruc1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableCaTruc1.getSelectedRow();
				txtMaca.setText(modelCaTruc.getValueAt(row, 0).toString());
				txtLoaica.setText(modelCaTruc.getValueAt(row, 1).toString());
				txtThoigianlam.setText(modelCaTruc.getValueAt(row, 2).toString());
				txtMaca.setEditable(false);
			}
		});
		tableCaTruc1.setModel(modelCaTruc = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Mã ca", "Loại ca", "Thời gian làm"
				}
		));
		tableCaTruc1.getColumnModel().getColumn(0).setPreferredWidth(15);
		tableCaTruc1.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableCaTruc1.getColumnModel().getColumn(2).setPreferredWidth(50);
	
		DocDuLieuDBVaoTable();
		scrollPane.setViewportView(tableCaTruc1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(781, 173, 398, 546);
		pnchinh.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMCa = new JLabel("Mã ca:");
		lblMCa.setBounds(10, 11, 97, 39);
		panel_2.add(lblMCa);
		
		JLabel lblLoiCa = new JLabel("Loại ca:");
		lblLoiCa.setBounds(10, 111, 97, 39);
		panel_2.add(lblLoiCa);
		
		JLabel lblThGiLa = new JLabel("Thời gian làm:");
		lblThGiLa.setBounds(10, 207, 97, 39);
		panel_2.add(lblThGiLa);
		
		txtMaca = new JTextField();
		txtMaca.setEditable(false);
		txtMaca.setColumns(10);
		txtMaca.setBounds(117, 20, 218, 30);
		panel_2.add(txtMaca);
		
		txtLoaica = new JTextField();
		txtLoaica.setColumns(10);
		txtLoaica.setBounds(117, 120, 218, 30);
		panel_2.add(txtLoaica);
		
		txtThoigianlam = new JTextField();
		txtThoigianlam.setColumns(10);
		txtThoigianlam.setBounds(117, 207, 218, 30);
		panel_2.add(txtThoigianlam);
		
		JButton btnThem = new JButton("Thêm ca trực");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if(o.equals(btnThem)) {
					if(validData()) {	
						String loai = txtLoaica.getText();
						Time time = Time.valueOf(txtThoigianlam.getText());
						CaTruc ca = new CaTruc(loai, time);
						if(!ct_dao.create(ca))
							JOptionPane.showMessageDialog(pnchinh, "Mã ca trực đã tồn tại");
						else {
							
							
							JOptionPane.showMessageDialog(pnchinh, "Thêm thành công");
							GUI_CaTruc qlhang=new GUI_CaTruc();
							qlhang.setVisible(true);
							dispose();
						}
					}
				}
				
			}

			
		});
		
		btnThem.setBounds(10, 276, 110, 46);
		panel_2.add(btnThem);
		
		JButton btnXoa = new JButton("Xóa ca trực");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if(o.equals(btnXoa)) {
					int row = tableCaTruc1.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(pnchinh, "Bạn chưa chọn dòng để xóa");
					else {
						int xoa;
						xoa = JOptionPane.showConfirmDialog(pnchinh, "Bạn có muốn xóa dòng đã chọn?", "Nhắc nhở", JOptionPane.YES_NO_OPTION);
						if(xoa == JOptionPane.YES_OPTION) {
							modelCaTruc.removeRow(row);
							int ma = Integer.parseInt(txtMaca.getText());
							ct_dao.delete(ma);
							JOptionPane.showMessageDialog(pnchinh, "Xóa thành công");
							txtMaca.setText("");
							txtLoaica.setText("");
							txtThoigianlam.setText("");
							txtMaca.requestFocus();
						}
					}
				}
			}
		});
	
		btnXoa.setBounds(144, 276, 110, 46);
		panel_2.add(btnXoa);
		
		JButton btnSua = new JButton("Sửa ca trực");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validData()) {
					int row = tableCaTruc1.getSelectedRow();
					if(row < 0)
					{
						return;
					}
					modelCaTruc.setValueAt(txtMaca.getText().trim(), row, 0);
					modelCaTruc.setValueAt(txtLoaica.getText().trim(), row, 1);
					modelCaTruc.setValueAt(txtThoigianlam.getText().trim(), row, 2);
					CaTruc ca = new CaTruc();
					ca.setMaca(Integer.parseInt(txtMaca.getText().trim()));
					ca.setLoaica(txtLoaica.getText().trim());
					ca.setThoigianlam(Time.valueOf(txtThoigianlam.getText().trim()));
					
					ct_dao.update(ca);
				}	
			}
		});
		
		btnSua.setBounds(278, 276, 110, 46);
		panel_2.add(btnSua);
		
		txtThongbao = new JTextField();
		txtThongbao.setForeground(Color.RED);
		txtThongbao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtThongbao.setEditable(false);
		txtThongbao.setBorder(null);
		txtThongbao.setBounds(10, 401, 378, 20);
		panel_2.add(txtThongbao);
		
		JButton btnClear = new JButton("Xóa trắng");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMaca.setText("");
				txtLoaica.setText("");
				txtThoigianlam.setText("");
				txtMaca.setEditable(false);
				txtLoaica.requestFocus();
			}
		});
		btnClear.setBounds(144, 352, 110, 39);
		panel_2.add(btnClear);
	}
	private void DocDuLieuDBVaoTable() {
		List<CaTruc> list = ct_dao.getDSCa();
		for(CaTruc ca : list) {
			modelCaTruc.addRow(new Object[] {
					ca.getMaca(), ca.getLoaica(), ca.getThoigianlam()
			});
		}
	}
	
	private boolean validData() {
		String loai = txtLoaica.getText();
		String time = txtThoigianlam.getText();
		
		if(!(loai.length() > 0 )) {
			showMessage("Loại ca trực không được để trống", txtLoaica);
			return false;
		}
		
		if(!(time.length() > 0 )) {
			showMessage("Thời gian làm không được để trống", txtThoigianlam);
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