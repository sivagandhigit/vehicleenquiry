package vehicleenquiry.parserimpl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import vehicleenquiry.domain.SupportedFileExten;
import vehicleenquiry.domain.SupportedMIMEType;
import vehicleenquiry.file.VehicleFileMetaData;

public class CSVVehicleEnquiryParserTest {

	@Test
	public void evaluateCSVCanParse() {
		VehicleFileMetaData vMetaData = new VehicleFileMetaData();
		vMetaData.setMIMEType(SupportedMIMEType.TXTCSV.getMIMETypeText());
		vMetaData.setExtention(SupportedFileExten.CSV.toString());
		assertTrue("Error in can Parse Logic", new CSVVehicleEnquiryParser().canParse(vMetaData));
	}
}
