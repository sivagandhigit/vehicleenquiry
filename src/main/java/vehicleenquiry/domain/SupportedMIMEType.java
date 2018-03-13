package vehicleenquiry.domain;

public enum SupportedMIMEType {
	APPXLS("application/vnd.ms-excel"), TXTCSV("text/csv");
	String MIMETypeText;

	SupportedMIMEType(String inMIMETypeText) {
		MIMETypeText = inMIMETypeText;
	}

	public String getMIMETypeText() {
		return MIMETypeText;
	}

}
