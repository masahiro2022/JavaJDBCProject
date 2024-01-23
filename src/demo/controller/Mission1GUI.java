package demo.controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Mission1GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel backGroundPicture;
	private Image img;
	private JLabel accountLabel;
	private JLabel passwordLabel;
	private JLabel wellcomLabel;
	private JLabel hintLabel;
	private JButton loginBtn;
	private JTextField account;
	private JTextField pwd;
	private JButton exitBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mission1GUI frame = new Mission1GUI();
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
	public Mission1GUI() {
		setTitle("帳號登入");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/komame.jpg")));

		panel = new JPanel();
		panel.setBounds(0, 0, 836, 513);
		contentPane.add(panel);
		panel.setLayout(null);

		account = new JTextField();
		account.setFont(new Font("新細明體", Font.PLAIN, 15));
		account.setColumns(10);
		account.setBounds(330, 232, 160, 30);
		panel.add(account);

		pwd = new JTextField();
		pwd.setFont(new Font("新細明體", Font.PLAIN, 15));
		pwd.setColumns(10);
		pwd.setBounds(330, 287, 160, 30);
		panel.add(pwd);

		accountLabel = new JLabel("帳號/Account  :");
		accountLabel.setForeground(new Color(255, 128, 64));
		accountLabel.setFont(new Font("微軟正黑體", Font.BOLD, 17));
		accountLabel.setBounds(191, 231, 129, 30);
		panel.add(accountLabel);

		passwordLabel = new JLabel("密碼/Password:");
		passwordLabel.setForeground(new Color(255, 128, 64));
		passwordLabel.setFont(new Font("微軟正黑體", Font.BOLD, 17));
		passwordLabel.setBounds(191, 287, 129, 30);
		panel.add(passwordLabel);

		wellcomLabel = new JLabel("TAS浪愛滿屋犬貓收容及認養計畫");
		wellcomLabel.setForeground(new Color(255, 0, 255));
		wellcomLabel.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		wellcomLabel.setBounds(220, 89, 334, 46);
		panel.add(wellcomLabel);

		hintLabel = new JLabel();
		hintLabel.setForeground(new Color(255, 128, 64));
		hintLabel.setFont(new Font("微軟正黑體", Font.BOLD, 17));
		hintLabel.setText("帳號 watcher 密碼 P@ssw0rd");
		hintLabel.setBounds(278, 172, 239, 39);
		panel.add(hintLabel);

		exitBtn = new JButton("離開/Exit");
		exitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		loginBtn = new JButton("登入");
		loginBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				String A = account.getText();
				String B = pwd.getText();
		
				if (A.equals("watcher") && B.equals("P@ssw0rd")) {
					Mission1_main mis = new Mission1_main();
					mis.setVisible(true);
					dispose();// 關閉前一個視窗

				} else {
					account.setText("");
					pwd.setText("");
					JOptionPane.showMessageDialog(loginBtn, "帳號密碼有誤，請重新輸入。");
				}

			}

		});
		loginBtn.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		loginBtn.setBounds(351, 349, 112, 39);
		panel.add(loginBtn);

		exitBtn.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		exitBtn.setBounds(635, 39, 112, 39);
		panel.add(exitBtn);

		backGroundPicture = new JLabel("New label");
		img = new ImageIcon(this.getClass().getResource("/bg.jpg")).getImage();
		backGroundPicture.setIcon(new ImageIcon(img));
		backGroundPicture.setBounds(0, 0, 836, 513);
		panel.add(backGroundPicture);
	}
}
