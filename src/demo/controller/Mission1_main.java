package demo.controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import demo.dao.AnimalShelterDao;
import demo.dao.AnimalshelterDaoFactory;
import demo.model.AnimalShelter;
import javax.swing.JScrollPane;

public class Mission1_main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel backGroundPicture;
	private Image img;
	private JLabel timeLabel;
	private JTextField idText;
	private JLabel idLabel;
	private JLabel areaLabel;
	private JTextField areaText;
	private JLabel nameLabel;
	private JTextField nameText;
	private JLabel addressLabel;
	private JTextField addressText;
	private JLabel phoneLabel;
	private JTextField phoneText;
	private JTextArea outPut;
	private JButton updateBtn;
	private JButton searchBtn;
	private JButton asAddBtn;
	private JButton deleteBtn;
	private JLabel searchLabel;
	private JTextField keyWordText;
	AnimalShelterDao asDao = AnimalshelterDaoFactory.createAnimalShelterDaoFactory();
	AnimalShelter as;
	List<AnimalShelter> aslist;
	private JScrollPane outPutScrollPane;
	private JButton storeFileBtn;
	private JButton logOut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mission1_main frame = new Mission1_main();
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
	public Mission1_main() {
		setTitle("TAS浪愛滿屋犬貓收容及認養計畫-合作商家資訊");
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

		idText = new JTextField();
		idText.setFont(new Font("\u5FAE\u8EDF\u6B63\u9ED1\u9AD4", Font.PLAIN, 14));
		idText.setBackground(new Color(192, 192, 192));
		idText.setBounds(177, 73, 116, 25);
		panel.add(idText);
		idText.setColumns(10);

		idLabel = new JLabel("商家編號/ID");
		idLabel.setForeground(new Color(255, 0, 0));
		idLabel.setBounds(52, 73, 96, 25);
		idLabel.setFont(new Font("微軟正黑體", Font.BOLD, 17));
		panel.add(idLabel);

		areaLabel = new JLabel("地區");
		areaLabel.setForeground(Color.RED);
		areaLabel.setFont(new Font("微軟正黑體", Font.BOLD, 17));
		areaLabel.setBounds(52, 122, 96, 25);
		panel.add(areaLabel);

		areaText = new JTextField();
		areaText.setFont(new Font("\u5FAE\u8EDF\u6B63\u9ED1\u9AD4", Font.PLAIN, 14));
		areaText.setColumns(10);
		areaText.setBackground(Color.LIGHT_GRAY);
		areaText.setBounds(177, 122, 116, 25);
		panel.add(areaText);

		nameLabel = new JLabel("合作商家名稱");
		nameLabel.setForeground(Color.RED);
		nameLabel.setFont(new Font("微軟正黑體", Font.BOLD, 17));
		nameLabel.setBounds(52, 168, 115, 25);
		panel.add(nameLabel);

		nameText = new JTextField();
		nameText.setFont(new Font("\u5FAE\u8EDF\u6B63\u9ED1\u9AD4", Font.PLAIN, 14));
		nameText.setColumns(10);
		nameText.setBackground(Color.LIGHT_GRAY);
		nameText.setBounds(177, 168, 116, 25);
		panel.add(nameText);

		addressLabel = new JLabel("合作商家地址");
		addressLabel.setForeground(Color.RED);
		addressLabel.setFont(new Font("微軟正黑體", Font.BOLD, 17));
		addressLabel.setBounds(52, 216, 115, 25);
		panel.add(addressLabel);

		addressText = new JTextField();
		addressText.setFont(new Font("\u5FAE\u8EDF\u6B63\u9ED1\u9AD4", Font.PLAIN, 14));
		addressText.setColumns(10);
		addressText.setBackground(Color.LIGHT_GRAY);
		addressText.setBounds(177, 216, 116, 25);
		panel.add(addressText);

		phoneLabel = new JLabel("連絡電話");
		phoneLabel.setForeground(Color.RED);
		phoneLabel.setFont(new Font("微軟正黑體", Font.BOLD, 17));
		phoneLabel.setBounds(52, 265, 96, 25);
		panel.add(phoneLabel);

		phoneText = new JTextField();
		phoneText.setFont(new Font("\u5FAE\u8EDF\u6B63\u9ED1\u9AD4", Font.PLAIN, 14));
		phoneText.setColumns(10);
		phoneText.setBackground(Color.LIGHT_GRAY);
		phoneText.setBounds(177, 265, 116, 25);
		panel.add(phoneText);

		outPut = new JTextArea();
		outPut.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		outPut.setBackground(new Color(192, 192, 192));
		outPut.setForeground(new Color(0, 0, 0));
		outPut.setBounds(52, 355, 731, 123);
		// panel.add(outPut);
		outPut.setEditable(false);

		outPutScrollPane = new JScrollPane(outPut);
		outPutScrollPane.setBounds(52, 334, 731, 144);
		panel.add(outPutScrollPane);

		searchLabel = new JLabel("編號或關鍵字查詢/刪除/載入");
		searchLabel.setForeground(new Color(0, 255, 0));
		searchLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		searchLabel.setBounds(474, 162, 265, 35);
		panel.add(searchLabel);

		keyWordText = new JTextField();
		keyWordText.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		keyWordText.setColumns(10);
		keyWordText.setBackground(Color.LIGHT_GRAY);
		keyWordText.setBounds(460, 210, 308, 35);
		panel.add(keyWordText);

		asAddBtn = new JButton("新增資料");
		asAddBtn.setBounds(341, 70, 96, 39);
		asAddBtn.setFont(new Font("新細明體", Font.BOLD, 14));
		asAddBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				as = new AnimalShelter();
				asDao.createConn();
				if (isNumeric(idText.getText())) {
					int nid = Integer.parseInt(idText.getText());
					String narea = areaText.getText();
					String nName = nameText.getText();
					String naddress = addressText.getText();
					String nphone = phoneText.getText();

					if (asDao.findById(nid) != null) {
						outPut.setText("此ID已存在，請重新輸入");
						idText.setText("");

					} else {
						as.setId(nid);
						as.setArea(narea);
						as.setShelterName(nName);
						as.setAddress(naddress);
						as.setPhone(nphone);
						try {

							asDao.shelterAdd(as);

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						outPut.setText("新增成功");
						idText.setText("");
						areaText.setText("");
						nameText.setText("");
						addressText.setText("");
						phoneText.setText("");
					}
				} else {
					outPut.setText(idText.getText() + "是無效的值");
					idText.setText("");
				}
				asDao.closeConn();
			}
		});
		panel.add(asAddBtn);

		searchBtn = new JButton("查詢資料");
		searchBtn.setFont(new Font("新細明體", Font.BOLD, 14));
		searchBtn.setBounds(434, 262, 96, 39);
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String keyWord = keyWordText.getText();
				asDao.createConn();
				if (isNumeric(keyWord)) {
					// 如果輸入是數字，轉換成整數
					int id = Integer.parseInt(keyWord);

					// 檢查 ID 是否存在
					if (asDao.findById(id) != null) {
						// 存在，印出資料
						as = asDao.findById(id);
						outPut.setText("編號:" + as.getId() + " 地區:" + as.getArea() + " 合作商家:" + as.getShelterName()
								+ " 地址:" + as.getAddress() + " 連絡電話:" + as.getPhone());

					} else {
						// 不存在，將數字轉換成字串，並使用 deleteShelterByKeyWord 方法
						String idAsString = Integer.toString(id);
						if (asDao.findByKeyWord(idAsString).isEmpty()) {
							outPut.setText("查無關於 " + keyWord + "的資料");

						} else {
							aslist = asDao.findByKeyWord(idAsString);
							outPut.setText("");
							for (AnimalShelter asl : aslist) {

								outPut.append("編號:" + asl.getId() + " 地區:" + asl.getArea() + " 合作商家:"
										+ asl.getShelterName() + " 地址:" + asl.getAddress() + " 連絡電話:" + asl.getPhone());
							}
						}

					}

				} else {
					if (keyWord.equals("")) {
						aslist = asDao.findAll();
						outPut.setText("");
						for (AnimalShelter asl : aslist) {
							outPut.append("編號:" + asl.getId() + " 地區:" + asl.getArea() + " 合作商家:" + asl.getShelterName()
									+ " 地址:" + asl.getAddress() + " 連絡電話:" + asl.getPhone() + "\n");
						}
					} else if (asDao.findByKeyWord(keyWord).isEmpty()) {
						outPut.setText("查無關於 " + keyWord + " 的資料");
					} else {

						aslist = asDao.findByKeyWord(keyWord);
						outPut.setText("");
						for (AnimalShelter asl : aslist) {
							outPut.append("編號:" + asl.getId() + " 地區:" + asl.getArea() + " 合作商家:" + asl.getShelterName()
									+ " 地址:" + asl.getAddress() + " 連絡電話:" + asl.getPhone() + "\n");
						}
					}
				}
				asDao.closeConn();
			}

		});
		panel.add(searchBtn);

		updateBtn = new JButton("修改資料");
		updateBtn.setFont(new Font("新細明體", Font.BOLD, 14));
		updateBtn.setBounds(341, 146, 96, 39);
		updateBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				asDao.createConn();
				if (isNumeric(idText.getText())) {
					int nid = Integer.parseInt(idText.getText());
					String narea = areaText.getText();
					String nName = areaText.getText();
					String naddress = areaText.getText();
					String nphone = areaText.getText();

					if (asDao.findById(nid) != null) {
						as.setId(nid);
						as.setArea(narea);
						as.setShelterName(nName);
						as.setAddress(naddress);
						as.setPhone(nphone);
						try {

							asDao.updateShelter(as);

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						outPut.setText("修改成功");

					} else {

						outPut.setText("此ID不存在，請重新輸入");
						idText.setText("");
					}
				} else {
					outPut.setText(idText.getText() + "是無效的值");
					idText.setText("");
				}
				asDao.closeConn();

			}
		});
		panel.add(updateBtn);

		deleteBtn = new JButton("刪除資料");
		deleteBtn.setFont(new Font("新細明體", Font.BOLD, 14));
		deleteBtn.setBounds(561, 262, 96, 39);
		deleteBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				asDao.createConn();
				String keyWord = keyWordText.getText();
				if (isNumeric(keyWord)) {
					// 如果輸入是數字，轉換成整數
					int did = Integer.parseInt(keyWord);

					// 檢查 ID 是否存在
					if (asDao.findById(did) != null) {
						// 存在，執行刪除
						asDao.deleteShelterById(did);
						outPut.setText("已刪除商家編號(ID): " + did);
						keyWordText.setText("");
					} else {
						// 不存在，將數字轉換成字串，並使用 deleteShelterByKeyWord 方法
						String idAsString = Integer.toString(did);
						if (asDao.findByKeyWord(idAsString).isEmpty()) {
							outPut.setText("查無關於 " + keyWord + " 的資料");
							keyWordText.setText("");

						} else {
							asDao.deleteShelterByKeyWord(idAsString);
							outPut.setText("已刪除關於:" + idAsString + "的資料");
							keyWordText.setText("");
						}
					}
				} else {
					if (keyWord.equals("")) {
						outPut.setText("是無效的值");
					}
					// 如果輸入是文字，使用 deleteShelterByKeyWord 方法
					else if (asDao.findByKeyWord(keyWord).isEmpty()) {
						outPut.setText("查無關於 " + keyWord + " 的資料");

					} else {
						asDao.deleteShelterByKeyWord(keyWord);
						outPut.setText("已刪除關於 " + keyWord + " 的資料");
					}
				}
				asDao.closeConn();
			}
		});
		panel.add(deleteBtn);

		storeFileBtn = new JButton("載入資料");
		storeFileBtn.setFont(new Font("新細明體", Font.BOLD, 14));
		storeFileBtn.setBounds(686, 262, 96, 39);
		storeFileBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				asDao.createConn();
				String keyWord = keyWordText.getText();
				if (new File(keyWord).exists()) {
					asDao.storeFile(keyWord);
					outPut.setText("新增成功");
					keyWordText.setText("");
				} else {
					outPut.setText("輸入錯誤");
				}
				asDao.closeConn();
			}
		});
		panel.add(storeFileBtn);

		logOut = new JButton("帳號登出");
		logOut.setFont(new Font("新細明體", Font.BOLD, 14));
		logOut.setBounds(686, 59, 96, 39);
		logOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(Mission1_main.this, "確定要登出?", "提示",
						JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					Mission1GUI m1 = new Mission1GUI();
					m1.setVisible(true);
					dispose();
				} else {

				}
			}
		});
		panel.add(logOut);

		// 時間標籤
		timeLabel = new JLabel("");
		timeLabel.setFont(new Font("微軟正黑體 Light", Font.BOLD, 16));
		timeLabel.setForeground(new Color(0, 255, 255));
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setBackground(Color.LIGHT_GRAY);
		timeLabel.setBounds(195, 10, 184, 36);
		panel.add(timeLabel);
		updateDateTime();
		// 背景圖片
		backGroundPicture = new JLabel("New label");
		img = new ImageIcon(this.getClass().getResource("/bg.jpg")).getImage();
		backGroundPicture.setIcon(new ImageIcon(img));
		backGroundPicture.setBounds(0, 0, 836, 513);
		panel.add(backGroundPicture);

	}

	private void updateDateTime() {
		Thread updateThread = new Thread(() -> {
			while (true) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateTime = dateFormat.format(new Date());
				SwingUtilities.invokeLater(() -> timeLabel.setText(dateTime));
				try {
					Thread.sleep(1000); // 每秒更新一次
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		updateThread.start();

	}

	private boolean isNumeric(String keyWord) {
		try {
			Integer.parseInt(keyWord);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
