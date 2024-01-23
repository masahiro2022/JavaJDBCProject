package demo.dao;

public interface ImageFileDao {
	
	public void imageStoreProcess(String url);

	public void imageRetrieveProcess(int id);
	
	public void createConn();
	
	public void closeConn();
}
