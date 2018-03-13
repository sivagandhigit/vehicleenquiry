package vehicleenquiry.parserimpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import vehicleenquiry.domain.SupportedFileExten;
import vehicleenquiry.domain.SupportedMIMEType;
import vehicleenquiry.domain.VehicleDetails;
import vehicleenquiry.file.VehicleFileMetaData;

public class CSVVehicleEnquiryParserTest {

	@Test
	public void evaluateCSVCanParse() {
		VehicleFileMetaData vMetaData = new VehicleFileMetaData();
		vMetaData.setMIMEType(SupportedMIMEType.TXTCSV.getMIMETypeText());
		vMetaData.setExtention(SupportedFileExten.CSV.toString());
		assertTrue("Error in can Parse Logic", new CSVVehicleEnquiryParser().canParse(vMetaData));
	}

	@Test
	public void evaluateCSVEmptyParseFile() {
		VehicleFileMetaData vMetaData = new VehicleFileMetaData();
		vMetaData.setMIMEType(SupportedMIMEType.TXTCSV.getMIMETypeText());
		vMetaData.setExtention(SupportedFileExten.CSV.toString());
		assertTrue("Error in can Parse Logic", new CSVVehicleEnquiryParser().parse(vMetaData).isEmpty());
	}

	@Test
	public void evaluateCSVParseFile() {
		VehicleFileMetaData vMetaData = new VehicleFileMetaData();
		vMetaData.setMIMEType(SupportedMIMEType.TXTCSV.getMIMETypeText());
		vMetaData.setFileName("Exisit_vehicle_enquiry_csv.csv");
		vMetaData.setExtention(SupportedFileExten.CSV.toString());
		List<VehicleDetails> vehDetailsList =  new CSVVehicleEnquiryParser().parse(vMetaData);
		assertFalse("Error in can Parse Logic",vehDetailsList.isEmpty());
		assertTrue("Error in reading Details",vehDetailsList.stream().anyMatch(vd ->"AB 12".equals(vd.getVrn())));
	}
}
