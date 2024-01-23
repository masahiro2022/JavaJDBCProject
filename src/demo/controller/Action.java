package demo.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;
import demo.dao.AnimalShelterDao;
import demo.dao.AnimalshelterDaoFactory;
import demo.dao.ImageFileDao;
import demo.model.AnimalShelter;

public class Action {

	public static void main(String[] args) throws Exception {
		AnimalShelterDao asDao = AnimalshelterDaoFactory.createAnimalShelterDaoFactory();
		ImageFileDao ifd = AnimalshelterDaoFactory.createImageFileDaoFactory();
		List<AnimalShelter> aslist;
		AnimalShelter as;
		asDao.createConn();
		ifd.createConn();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("請選擇功能:");
			System.out.println("(1)載入資料");
			System.out.println("(2)查詢合作商家的資料");
			System.out.println("(3)新增合作商家的資料");
			System.out.println("(4)修改合作商家的資料");
			System.out.println("(5)刪除合作商家的資料");
			System.out.println("(6)載入圖檔");
			System.out.println("(7)匯出圖檔");
			System.out.println("(8)退出程式");
			if (sc.hasNext()) {
				if (sc.hasNextInt()) {
					int choise = sc.nextInt();
					switch (choise) {
					case 1:// 載入資料
						System.out.println("請輸入檔案的位置/名稱.副檔名");
						String url = sc.next();
						if (new File(url).exists()) {
							asDao.storeFile(url);
						} else {
							System.out.println("輸入錯誤");
						}
						break;
					case 2:// 查詢合作商家的資料
						System.out.println("請輸入欲查詢的合作商家編號(ID)或關鍵字或all");
						String keyWord = sc.next();
						if (isNumeric(keyWord)) {
							// 如果輸入是數字，轉換成整數
							int id = Integer.parseInt(keyWord);

							// 檢查 ID 是否存在
							if (asDao.findById(id) != null) {
								// 存在，印出資料
								as = asDao.findById(id);

								BufferedWriter bw = new BufferedWriter(
										new FileWriter("C:\\Users\\Public\\Desktop\\" + id + ".txt"));
								if (as != null) {
									bw.write("編號:" + as.getId() + " 地區:" + as.getArea() + " 合作商家:" + as.getShelterName()
											+ " 地址:" + as.getAddress() + " 連絡電話:" + as.getPhone());
								}
								bw.close();

								System.out.println("編號:" + as.getId() + " 地區:" + as.getArea() + " 合作商家:"
										+ as.getShelterName() + " 地址:" + as.getAddress() + " 連絡電話:" + as.getPhone());
							} else {
								// 不存在，將數字轉換成字串，並使用 deleteShelterByKeyWord 方法
								String idAsString = Integer.toString(id);
								if (asDao.findByKeyWord(idAsString).isEmpty()) {
									System.out.println("查無關於 " + keyWord + "的資料");

								} else {
									aslist = asDao.findByKeyWord(idAsString);
									for (AnimalShelter as1 : aslist) {
										System.out.println("編號:" + as1.getId() + " 地區:" + as1.getArea() + " 合作商家:"
												+ as1.getShelterName() + " 地址:" + as1.getAddress() + " 連絡電話:"
												+ as1.getPhone());
									}
								}

							}

						} else {
							if (keyWord.equals("all") || keyWord.equals("ALL")) {
								aslist = asDao.findAll();
								for (AnimalShelter as1 : aslist) {
									System.out.println("編號:" + as1.getId() + " 地區:" + as1.getArea() + " 合作商家:"
											+ as1.getShelterName() + " 地址:" + as1.getAddress() + " 連絡電話:"
											+ as1.getPhone());
								}
							} else if (asDao.findByKeyWord(keyWord).isEmpty()) {
								System.out.println("查無關於 " + keyWord + " 的資料");

							} else {
								aslist = asDao.findByKeyWord(keyWord);
								for (AnimalShelter as1 : aslist) {
									System.out.println("編號:" + as1.getId() + " 地區:" + as1.getArea() + " 合作商家:"
											+ as1.getShelterName() + " 地址:" + as1.getAddress() + " 連絡電話:"
											+ as1.getPhone());
								}
							}
						}
						break;
					case 3:// 新增合作商家的資料
						as = new AnimalShelter();
						int nid;
						System.out.println("請輸入欲新增的ID");
						while (true) {

							if (sc.hasNext()) {
								if (sc.hasNextInt()) {
									nid = sc.nextInt();

									if (asDao.findById(nid) != null) {
										System.out.println("此ID已存在，請重新輸入");
									} else {
										as.setId(nid);
										break;
									}
								} else {
									System.out.println("輸入有誤,請再次輸入有效的值");
									sc.next();
								}
							}
						}
						System.out.println("請輸入欲新增的區域");
						String area = sc.next();
						as.setArea(area);
						System.out.println("請輸入欲新增的商家名稱");
						String name = sc.next();
						as.setShelterName(name);
						System.out.println("請輸入欲新增的商家地址");
						String address = sc.next();
						as.setAddress(address);
						System.out.println("請輸入欲新增的連絡電話");
						String phone = sc.next();
						as.setPhone(phone);
						asDao.shelterAdd(as);
						break;
					case 4:// 修改合作商家的資料
//				as = new AnimalShelter();
						int uid;
						System.out.println("請輸入欲修改的合作商家編號(ID)");
						while (true) {

							if (sc.hasNext()) {
								if (sc.hasNextInt()) {
									uid = sc.nextInt();

									if (asDao.findById(uid) != null) {
										as = asDao.findById(uid);
										break;
									} else {
										System.out.println("此ID不存在，請重新輸入");
									}
								} else {
									System.out.println("輸入有誤,請再次輸入有效的值");
									sc.next();
								}
							}
						}
						System.out.println("請輸入更新後的區域");
						String uarea = sc.next();
						as.setArea(uarea);
						System.out.println("請輸入更新後的商家名稱");
						String uname = sc.next();
						as.setShelterName(uname);
						System.out.println("請輸入更新後的商家地址");
						String uaddress = sc.next();
						as.setAddress(uaddress);
						System.out.println("請輸入更新後的連絡電話");
						String uphone = sc.next();
						as.setPhone(uphone);
						asDao.updateShelter(as);
						break;

					case 5:// 刪除合作商家的資料
						System.out.println("請輸入欲刪除的商家編號(ID)或關鍵字");
						keyWord = sc.next();
						if (isNumeric(keyWord)) {
							// 如果輸入是數字，轉換成整數
							int did = Integer.parseInt(keyWord);

							// 檢查 ID 是否存在
							if (asDao.findById(did) != null) {
								// 存在，執行刪除
								asDao.deleteShelterById(did);
								System.out.println("已刪除商家編號(ID): " + did);
							} else {
								// 不存在，將數字轉換成字串，並使用 deleteShelterByKeyWord 方法
								String idAsString = Integer.toString(did);
								if (asDao.findByKeyWord(idAsString).isEmpty()) {
									System.out.println("查無關於 " + keyWord + " 的資料");

								} else {
									asDao.deleteShelterByKeyWord(idAsString);
									System.out.println("已刪除關於:" + idAsString + "的資料");
								}
							}
						} else {
							// 如果輸入是文字，使用 deleteShelterByKeyWord 方法
							if (asDao.findByKeyWord(keyWord).isEmpty()) {
								System.out.println("查無關於 " + keyWord + " 的資料");

							} else {
								asDao.deleteShelterByKeyWord(keyWord);
								System.out.println("已刪除關於 " + keyWord + " 的資料");
							}
						}
						break;
					case 6:// 載入圖檔
						System.out.println("請輸入圖檔的位置/名稱.副檔名");
						url = sc.next();
						if (new File(url).exists()) {
							ifd.imageStoreProcess(url);
						} else {
							System.out.println("輸入錯誤");
						}
						break;
					case 7:// 寫出圖檔
						System.out.println("請輸入欲匯出圖檔的ID");
						while (true) {
							if (sc.hasNext()) {
								if (sc.hasNextInt()) {
									int pid = sc.nextInt();
									if (asDao.findById(pid) != null)
										ifd.imageRetrieveProcess(pid);
									else
										System.out.println("無此ID");
									break;
								} else {
									System.out.println("輸入有誤,請再次輸入有效的值");
									sc.next();
								}
							}
						}
						break;
					case 8:
						System.out.println("程式結束");
						System.exit(8);
						asDao.closeConn();
						ifd.closeConn();
						break;
					default:
						System.out.println("輸入有誤,請再次輸入有效的選項");
					}
				} else {
					System.out.println("輸入有誤,請再次輸入有效的選項");
					sc.next();
				}
			}
			System.out.println();
		}
	}

	private static boolean isNumeric(String keyword) {
		try {
			Integer.parseInt(keyword);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
