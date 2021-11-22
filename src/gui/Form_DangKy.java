package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import connectDB.ConnectDB;
import dao.TaiKhoan_DAO;

import entity.TaiKhoan;
import util.DateUtil;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class Form_DangKy extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtTentk;
	private JPasswordField txtPassword;
	private JPasswordField txtPassword1;
	private JTextField txtThongBao;
	private TaiKhoan_DAO tk_dao;
	private JComboBox cboChucvu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_DangKy frame = new Form_DangKy();
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
	public Form_DangKy() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		tk_dao = new TaiKhoan_DAO();
		setTitle("Đăng Ký");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 318);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46, 139, 85));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblTentk = new JLabel("Tên tài khoản");
		lblTentk.setForeground(Color.YELLOW);
		lblTentk.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTentk.setBounds(287, 58, 157, 32);
		Image img1 = new ImageIcon(this.getClass().getResource("/image/taikhoan.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		lblTentk.setIcon(new ImageIcon(img1));
		contentPane.add(lblTentk);
		
		JLabel lblNewLabel_1 = new JLabel("Mật khẩu");
		lblNewLabel_1.setForeground(Color.YELLOW);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(287, 101, 125, 32);
		Image img2 = new ImageIcon(this.getClass().getResource("/image/icon-password-3.jpg")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(img2));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Xác nhận mật khẩu");
		lblNewLabel_2.setForeground(Color.YELLOW);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(287, 138, 193, 36);
		Image img3 = new ImageIcon(this.getClass().getResource("/image/icon-password-3.jpg")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		lblNewLabel_2.setIcon(new ImageIcon(img3));
		contentPane.add(lblNewLabel_2);
		
		txtTentk = new JTextField();
		txtTentk.setBounds(490, 60, 222, 20);
		contentPane.add(txtTentk);
		txtTentk.setColumns(10);
		
		
		JButton btnDangky = new JButton("ĐĂNG KÝ");
		btnDangky.setBackground(Color.PINK);
		btnDangky.setForeground(new Color(0, 0, 0));
		btnDangky.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDangky.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				StringBuilder sb=new StringBuilder();
				if(txtTentk.getText().equals("")) {
					sb.append("Bắt buộc nhập tên tài khoản! \n");
					txtTentk.setBackground(Color.YELLOW);
				} else {
					txtTentk.setBackground(Color.WHITE);
				}
				String password= new String(txtPassword.getPassword());
				if(txtPassword.equals("")) {
					sb.append("Bắt buộc nhập mật khẩu! \n");
					txtPassword.setBackground(Color.YELLOW);
				} else {
					txtPassword.setBackground(Color.WHITE);
				}
				String confirmpassword= new String(txtPassword1.getPassword());
				if(!password.equals(confirmpassword)) {
					sb.append("Mật khẩu nhập và mật khẩu tái xác nhân phải giống nhau \n");
					txtPassword.setBackground(Color.YELLOW);
					txtPassword1.setBackground(Color.YELLOW);
				} else {
					txtPassword.setBackground(Color.WHITE);
					txtPassword1.setBackground(Color.WHITE);
				}
				if(sb.length()>0) {
					JOptionPane.showMessageDialog(null, sb.toString());
					return;
				} else {
					JOptionPane.showMessageDialog(null, "Đăng ký thành công");
					Object o = e.getSource();
					if(o.equals(btnDangky)) {
							String tentk = txtTentk.getText();
							String matkhau = txtPassword.getText();
							//vaitro = 0 = false => "Nhân viên" vaitro = 1 = true => "Nhân viên quản lý"
							boolean chucvu = cboChucvu.getSelectedItem() == "Nhân viên" ? false : true;
							TaiKhoan tk = new TaiKhoan(tentk, matkhau,chucvu);
							
							if(!tk_dao.create(tk))
								JOptionPane.showMessageDialog(null, "Đăng ký thất bại");
							else {
								JOptionPane.showMessageDialog(null, "Thêm thành công");
								Form_DangNhap dn =new Form_DangNhap();
								dn.setVisible(true);
								dispose();
							}
						
					}
				}
				
			}
		});
		btnDangky.setBounds(490, 196, 101, 35);
		contentPane.add(btnDangky);
		
		JButton btnHuybo = new JButton("HỦY BỎ");
		btnHuybo.setBackground(Color.PINK);
		btnHuybo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHuybo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnHuybo.setBounds(620, 196, 92, 35);
		contentPane.add(btnHuybo);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(490, 100, 222, 20);
		contentPane.add(txtPassword);
		
		txtPassword1 = new JPasswordField();
		txtPassword1.setBounds(490, 148, 222, 20);
		contentPane.add(txtPassword1);
		
		JLabel lblNewLabel = new JLabel(">>Bấm vào đây để đăng nhập<<");
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Form_DangNhap dn =new Form_DangNhap();
				dn.setVisible(true);
				dispose();
			}
		});
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(468, 242, 258, 28);
		contentPane.add(lblNewLabel);
		JPanel pnHinh = new JPanel();
		pnHinh.setBounds(10, 11, 244, 228);
		contentPane.add(pnHinh);
		pnHinh.setLayout(null);
		
		JLabel lblHinh = new JLabel("");
		lblHinh.setBounds(0, 0, 244, 228);
		pnHinh.add(lblHinh);
		Image img4 = new ImageIcon(this.getClass().getResource("/image/Karaoke.jpg")).getImage().getScaledInstance(244, 228, Image.SCALE_SMOOTH);
		lblHinh.setIcon(new ImageIcon(img4));
		lblHinh.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_3 = new JLabel("ĐĂNG KÝ");
		lblNewLabel_3.setForeground(new Color(255, 255, 0));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(396, 11, 176, 26);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Chức vụ:");
		lblNewLabel_4.setBounds(297, 184, 147, 20);
		contentPane.add(lblNewLabel_4);
		
		//vaitro = 0 = false => "Nhân viên" vaitro = 1 = true => "Nhân viên quản lý"
		 cboChucvu = new JComboBox();
		cboChucvu.setBounds(297, 214, 147, 32);
		contentPane.add(cboChucvu);
		ArrayList<TaiKhoan> listtk =tk_dao.getTatCaTK();
		cboChucvu.addItem("Nhân viên");
		cboChucvu.addItem("Nhân viên quản lý");
		
	}
}
