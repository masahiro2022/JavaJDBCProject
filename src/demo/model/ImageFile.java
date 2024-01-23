package demo.model;

public class ImageFile {
	private int galleryId;
	private String fileName;
	private byte[] imageFile;

	public ImageFile() {
	}

	public ImageFile(String fileName, byte[] storePath) {
		this.fileName = fileName;
		this.imageFile = storePath;
	}

	public int getId() {
		return galleryId;
	}

	public void setId(int id) {
		this.galleryId = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getStorePath() {
		return imageFile;
	}

	public void setStorePath(byte[] storePath) {
		this.imageFile = storePath;
	}

}
