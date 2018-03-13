package vehicleenquiry.file;

public class VehicleFileMetaData {
	private String fileName;
	private String extension;
	private String MIMEType;
	private double fileSize;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtention(String extension) {
		this.extension = extension;
	}
	public String getMIMEType() {
		return MIMEType;
	}
	public void setMIMEType(String mIMEType) {
		MIMEType = mIMEType;
	}
	public double getFileSize() {
		return fileSize;
	}
	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}
}
