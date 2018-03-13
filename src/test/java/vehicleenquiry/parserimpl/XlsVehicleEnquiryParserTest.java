package vehicleenquiry.parserimpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import vehicleenquiry.domain.SupportedFileExten;
import vehicleenquiry.domain.SupportedMIMEType;
import vehicleenquiry.domain.VehicleDetails;
import vehicleenquiry.file.VehicleFileMetaData;	

public class XlsVehicleEnquiryParserTest {

	@Test
	public void evaluateXlsCanParse() {
		VehicleFileMetaData vMetaData = new VehicleFileMetaData();
		vMetaData.setMIMEType(SupportedMIMEType.APPXLS.getMIMETypeText());
		vMetaData.setExtention(SupportedFileExten.XLS.toString());
		assertTrue("Error in can Parse Logic", new XlsVehicleEnquiryParser().canParse(vMetaData));

	}
	
	@Test
	public void evaluateCSVParseFile() {
		VehicleFileMetaData vMetaData = new VehicleFileMetaData();
		vMetaData.setMIMEType(SupportedMIMEType.APPXLS.getMIMETypeText());
		vMetaData.setFileName("VRNExcel_1.xls");
		vMetaData.setExtention(SupportedFileExten.XLS.toString());
		List<VehicleDetails> vehDetailsList =  new XlsVehicleEnquiryParser().parse(vMetaData);
		assertFalse("Error in can Parse Logic",vehDetailsList.isEmpty());
		assertTrue("Error in reading Details",vehDetailsList.stream().anyMatch(vd ->"NK16RTU".equals(vd.getVrn())));
	}	

}
