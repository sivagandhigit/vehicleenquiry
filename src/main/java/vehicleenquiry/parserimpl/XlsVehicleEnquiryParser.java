package vehicleenquiry.parserimpl;

import java.util.List;

import vehicleenquiry.domain.SupportedFileExten;
import vehicleenquiry.domain.SupportedMIMEType;
import vehicleenquiry.domain.VehicleDetails;
import vehicleenquiry.file.VehicleFileMetaData;
import vehicleenquiry.parser.VehicleEnquiryFileParser;

public class XlsVehicleEnquiryParser implements VehicleEnquiryFileParser {

	@Override
	public List<VehicleDetails> parse(VehicleFileMetaData vMetaData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean canParse(VehicleFileMetaData vMetaData) {
		return SupportedMIMEType.APPXLS.getMIMETypeText().equalsIgnoreCase(vMetaData.getMIMEType())
				&& SupportedFileExten.XLS.toString().equalsIgnoreCase(vMetaData.getExtension());
	}

}
