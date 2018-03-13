package vehicleenquiry.parserimpl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import vehicleenquiry.domain.SupportedFileExten;
import vehicleenquiry.domain.SupportedMIMEType;
import vehicleenquiry.file.VehicleFileMetaData;	

public class XlsVehicleEnquiryParserTest {

	@Test
	public void evaluateXlsCanParse() {
		VehicleFileMetaData vMetaData = new VehicleFileMetaData();
		vMetaData.setMIMEType(SupportedMIMEType.APPXLS.getMIMETypeText());
		vMetaData.setExtention(SupportedFileExten.XLS.toString());
		assertTrue("Error in can Parse Logic", new XlsVehicleEnquiryParser().canParse(vMetaData));

	}

}
