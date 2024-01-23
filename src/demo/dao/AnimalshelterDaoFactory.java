package demo.dao;

public class AnimalshelterDaoFactory {
    public static AnimalShelterDao createAnimalShelterDaoFactory() {
    	return new AnimalShelterDaoImpl();
    }
    public static ImageFileDao createImageFileDaoFactory() {
    	return new ImageFileDaoImpl();
    }
}
