package demo.dao;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageFileDaoImpl implements ImageFileDao {

	private Connection conn;

	@Override
	public void imageStoreProcess(String url) {
		String SQL = "insert into Gallery(imageName,imageFile) values(?,?)";

		// 使用 Paths 取得 Path 物件
		Path path = Paths.get(url);

		// 取得原檔名（不包含路徑和副檔名）
		String originalFileName = path.getFileName().toString();

		// 去掉副檔名
		int dotIndex = originalFileName.lastIndexOf('.');
		if (dotIndex > 0) {
			originalFileName = originalFileName.substring(0, dotIndex);
		}

		try {
			FileInputStream fis = new FileInputStream(url);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, originalFileName);
			ps.setBinaryStream(2, fis);
			ps.executeUpdate();
			ps.close();
			fis.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void imageRetrieveProcess(int id) {
		String SQL = "select * from Gallery where galleryId=?";

		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();			
			if (rs.next()) {
				//System.out.println(rs.getString(1));
				Blob blob = rs.getBlob(3);
				int length = (int) blob.length();
				//System.out.println("length:" + length);

				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("C:\\Temp\\"+rs.getString("imageName")+".jpg"));
				bos.write(blob.getBytes(1, length));
				bos.flush();
				bos.close();
				rs.close();				
			}else {
				System.out.println("無此ID");
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void createConn() {
		String URL = "jdbc:sqlserver://localhost:1433;"
				+ "database=LabDB2;user=watcher;password=P@ssw0rd;encrypt=true;trustServerCertificate=true;";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(URL);
			//System.out.println("Connection Status:" + !conn.isClosed());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void closeConn() {
		if (conn != null) {
			try {
				conn.close();
				//System.out.println("Connection closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
		
}
