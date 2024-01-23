package demo.dao;

import java.sql.SQLException;
import java.util.List;

import demo.model.AnimalShelter;

public interface AnimalShelterDao {
    
	public void shelterAdd(AnimalShelter as)throws SQLException;

	public void updateShelter(AnimalShelter as);

	public void deleteShelterById(int id);

	public AnimalShelter findById(int id);

	public void createConn();

	public void closeConn();
	
	public void storeFile(String url);
	
	public void storeFileFromURL(String url);
	
	public void deleteShelterByKeyWord(String keyWord);
	
	public List<AnimalShelter> findByKeyWord(String keyWord);
	
	public List<AnimalShelter> findAll(); 
	
}
