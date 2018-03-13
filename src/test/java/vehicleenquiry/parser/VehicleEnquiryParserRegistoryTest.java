package vehicleenquiry.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import vehicleenquiry.domain.SupportedFileExten;
import vehicleenquiry.domain.SupportedMIMEType;
import vehicleenquiry.file.VehicleFileMetaData;
import vehicleenquiry.parserimpl.CSVVehicleEnquiryParser;
import vehicleenquiry.parserimpl.XlsVehicleEnquiryParser;

public class VehicleEnquiryParserRegistoryTest {

	@Test
	public void evaluateNullGetVehicleFileParser() {
		VehicleFileMetaData vMetaData = new VehicleFileMetaData();
		assertNull(VehicleEnquiryParserRegistory.getVehicleFileParser(vMetaData));
	}

	@Test
	public void evaluateCSVGetVehicleFileParser() {
		VehicleFileMetaData vMetaData = new VehicleFileMetaData();
		vMetaData.setMIMEType(SupportedMIMEType.TXTCSV.getMIMETypeText());
		vMetaData.setExtention(SupportedFileExten.CSV.toString());
		assertNotNull(VehicleEnquiryParserRegistory.getVehicleFileParser(vMetaData));
		assertEquals("Wrong Parser", CSVVehicleEnquiryParser.class,
				VehicleEnquiryParserRegistory.getVehicleFileParser(vMetaData).getClass());
	}
	
	@Test
	public void evaluateXlsGetVehicleFileParser() {
		VehicleFileMetaData vMetaData = new VehicleFileMetaData();
		vMetaData.setMIMEType(SupportedMIMEType.APPXLS.getMIMETypeText());
		vMetaData.setExtention(SupportedFileExten.XLS.toString());
		assertNotNull(VehicleEnquiryParserRegistory.getVehicleFileParser(vMetaData));
		assertEquals("Wrong Parser", XlsVehicleEnquiryParser.class,
				VehicleEnquiryParserRegistory.getVehicleFileParser(vMetaData).getClass());
	}	
}
