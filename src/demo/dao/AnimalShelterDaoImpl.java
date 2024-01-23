package demo.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import demo.model.AnimalShelter;

public class AnimalShelterDaoImpl implements AnimalShelterDao {

	private Connection conn;
	private ArrayList<AnimalShelter> asList;

	@Override
	public void shelterAdd(AnimalShelter as) {
		String SQL = "insert into AnimalShelters(id,area,shelterName,address,phone)values(?,?,?,?,?)";

		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, as.getId());
			ps.setString(2, as.getArea());
			ps.setString(3, as.getShelterName());
			ps.setString(4, as.getAddress());
			ps.setString(5, as.getPhone());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateShelter(AnimalShelter as) {
		String SQL = "update AnimalShelters set area=?,shelterName=?,address=?,phone=? where id=? ";

		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, as.getArea());
			ps.setString(2, as.getShelterName());
			ps.setString(3, as.getAddress());
			ps.setString(4, as.getPhone());
			ps.setInt(5, as.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteShelterById(int id) {
		String SQL = "delete from AnimalShelters where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public AnimalShelter findById(int id) {
		String SQL = "select * from AnimalShelters where id=?";
		AnimalShelter as = null;
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				as = new AnimalShelter();
				as.setId(rs.getInt(1));
				as.setArea(rs.getString(2));
				as.setShelterName(rs.getString(3));
				as.setAddress(rs.getString(4));
				as.setPhone(rs.getString(5));
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return as;
	}

	@Override
	public void createConn() {
		String URL = "jdbc:sqlserver://localhost:1433;"
				+ "database=LabDB2;user=watcher;password=P@ssw0rd;encrypt=true;trustServerCertificate=true;";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(URL);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println("Connection Status:" + !conn.isClosed());
	}

	@Override
	public void closeConn() {
		if (conn != null) {
			try {
				conn.close();
				// System.out.println("Connection closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void storeFile(String url) {
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(url)), StandardCharsets.UTF_8));
			String line;
			br.readLine();

			while ((line = br.readLine()) != null) {
				String[] shelterData = line.split(",");
				if (line.trim().isEmpty()) {
					continue;
				}
				if (shelterData.length <= 5) {
					int id = Integer.parseInt(shelterData[0]);
					String area = shelterData.length > 1 ? shelterData[1] : "";
					String name = shelterData.length > 1 ? shelterData[2] : "";
					String address = shelterData.length > 1 ? shelterData[3] : "";
					String phone = shelterData.length > 4 ? shelterData[4] : "";
					AnimalShelter as = new AnimalShelter(id, area, name, address, phone);
					shelterAdd(as);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteShelterByKeyWord(String str) {
		String SQL = "delete from AnimalShelters where id like ? or area like ? or shelterName like ? or address like ? or phone like ?";
		String kw = "%" + str + "%";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, kw);
			ps.setString(2, kw);
			ps.setString(3, kw);
			ps.setString(4, kw);
			ps.setString(5, kw);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<AnimalShelter> findByKeyWord(String keyWord) {
		String SQL = "select * from AnimalShelters where id like ? or area like ? or shelterName like ? or address like ?";
		asList = new ArrayList<AnimalShelter>();
		String kw = "%" + keyWord + "%";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, kw);
			ps.setString(2, kw);
			ps.setString(3, kw);
			ps.setString(4, kw);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AnimalShelter as = new AnimalShelter();
				as.setId(rs.getInt("id"));
				as.setArea(rs.getString("area"));
				as.setShelterName(rs.getString("shelterName"));
				as.setAddress(rs.getString("address"));
				as.setPhone(rs.getString("phone"));
				asList.add(as);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return asList;
	}

	@Override
	public void storeFileFromURL(String url) {
		try {
			URL data = new URL(url);

			URL dataUrl = new URL(url);
			
			dataUrl.openConnection();
		

			BufferedReader br = new BufferedReader(new InputStreamReader(data.openStream(),StandardCharsets.UTF_8));
			String line;
			br.readLine();

			while ((line = br.readLine()) != null) {
				String[] shelterData = line.split(",");
				if (line.trim().isEmpty()) {
					continue;
				}
				if (shelterData.length <= 5) {
					int id = Integer.parseInt(shelterData[0]);
					String area = shelterData.length > 1 ? shelterData[1] : "";
					String name = shelterData.length > 1 ? shelterData[2] : "";
					String address = shelterData.length > 1 ? shelterData[3] : "";
					String phone = shelterData.length > 4 ? shelterData[4] : "";
												
					AnimalShelter as = new AnimalShelter(id, area, name, address, phone);
					shelterAdd(as);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		AnimalShelterDaoImpl jdbc = new AnimalShelterDaoImpl();
		jdbc.createConn();
		jdbc.storeFileFromURL(
				"https://data.taipei/api/dataset/d47a2569-178a-4992-8d70-5feb0197797f/resource/9c1a7bc7-20f8-43ab-b547-106565854cfc/download");
		jdbc.closeConn();
	}

	@Override
	public List<AnimalShelter> findAll() {
		String SQL = "select * from AnimalShelters";
		asList = new ArrayList<AnimalShelter>();
		 
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AnimalShelter as = new AnimalShelter();
				as.setId(rs.getInt("id"));
				as.setArea(rs.getString("area"));
				as.setShelterName(rs.getString("shelterName"));
				as.setAddress(rs.getString("address"));
				as.setPhone(rs.getString("phone"));
				asList.add(as);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return asList;
	}
}
