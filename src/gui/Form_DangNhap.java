package gui;

import java.awt.BorderLayout;
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
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import dao.TaiKhoan_DAO;
import entity.TaiKhoan;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
public class Form_DangNhap extends JFrame {

	private JPanel contentPane;
	private JTextField txtTentk;
	private JPasswordField txtPassword;
	private TaiKhoan_DAO tk_dao;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_DangNhap frame = new Form_DangNhap();
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
	public Form_DangNhap() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		setTitle("Đăng Nhập");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 346);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46, 139, 85));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		tk_dao = new TaiKhoan_DAO();
		
		JLabel lblNewLabel_1 = new JLabel("Tài khoản");
		lblNewLabel_1.setForeground(new Color(255, 255, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(253, 86, 124, 30);
		Image img1 = new ImageIcon(this.getClass().getResource("/image/taikhoan.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(img1));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mật khẩu");
		lblNewLabel_2.setForeground(Color.YELLOW);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(266, 147, 111, 30);
		Image img2 = new ImageIcon(this.getClass().getResource("/image/icon-password-3.jpg")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		lblNewLabel_2.setIcon(new ImageIcon(img2));
		contentPane.add(lblNewLabel_2);
		
		txtTentk = new JTextField();
		txtTentk.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTentk.setText("nhanvien");
		txtTentk.setBounds(387, 87, 272, 30);
		contentPane.add(txtTentk);
		txtTentk.setColumns(10);
		
		JButton btnDangnhap = new JButton("ĐĂNG NHẬP");
		btnDangnhap.setBackground(new Color(255, 182, 193));
		btnDangnhap.setForeground(new Color(25, 25, 112));
		btnDangnhap.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDangnhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn;
				try {
					int flag=1;
					conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=karaokeNote7", "sa", "sapassword");
					Statement st= conn.createStatement();
					ResultSet rs= st.executeQuery("select * from taikhoan");
					while(rs.next()) {
						if(rs.getString(1).equals(txtTentk.getText()) && rs.getString(2).equals(txtPassword.getText())) {
							flag=0;
							break;
						} 
					}
					if(flag==0) {
						
						GUI_Chinh main = new GUI_Chinh();
						String tentk = txtTentk.getText();
						boolean vaitro = false;
						ArrayList<TaiKhoan> listtk = tk_dao.getTimVaitro(tentk);
						for(TaiKhoan tk : listtk) {
							 vaitro = tk.isVaitro();
						}
						main.sendlogin(tentk, vaitro);
						main.setVisible(true);
						dispose();
					}  else {
						JOptionPane.showMessageDialog(null,"Nhập sai tên tài khoản hoặc mật khẩu");
					}
						
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnDangnhap.setBounds(387, 203, 133, 36);
		contentPane.add(btnDangnhap);
		
		JButton btnThoat = new JButton("THOÁT");
		btnThoat.setBackground(new Color(255, 182, 193));
		btnThoat.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnThoat.setBounds(533, 203, 126, 37);
		contentPane.add(btnThoat);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setText("123");
		txtPassword.setToolTipText("");
		txtPassword.setBounds(387, 148, 272, 30);
		contentPane.add(txtPassword);
		
		JLabel lblDangky = new JLabel(">>Bấm vào đây để đăng ký tài khoản<<");
		lblDangky.setForeground(new Color(255, 255, 0));
		lblDangky.setHorizontalAlignment(SwingConstants.CENTER);
		lblDangky.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Form_DangKy dk = new Form_DangKy();
				dk.setVisible(true);
				dispose();
				
			}
		});
		lblDangky.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDangky.setBounds(288, 266, 382, 30);
		contentPane.add(lblDangky);
		
		JPanel pnHinh = new JPanel();
		pnHinh.setBounds(10, 11, 244, 228);
		contentPane.add(pnHinh);
		pnHinh.setLayout(null);
		
		JLabel lblHinh = new JLabel("");
		lblHinh.setBounds(0, 0, 244, 228);
		pnHinh.add(lblHinh);
		Image img3 = new ImageIcon(this.getClass().getResource("/image/Karaoke.jpg")).getImage().getScaledInstance(244, 228, Image.SCALE_SMOOTH);
		lblHinh.setIcon(new ImageIcon(img3));
		lblHinh.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("ĐĂNG NHẬP");
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(434, 26, 164, 30);
		contentPane.add(lblNewLabel);
	}
}
