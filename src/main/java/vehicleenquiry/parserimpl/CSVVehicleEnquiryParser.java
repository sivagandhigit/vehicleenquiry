package vehicleenquiry.parserimpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import vehicleenquiry.config.ConfigEnum;
import vehicleenquiry.domain.SupportedFileExten;
import vehicleenquiry.domain.SupportedMIMEType;
import vehicleenquiry.domain.VehicleDetails;
import vehicleenquiry.file.VehicleFileFilter;
import vehicleenquiry.file.VehicleFileMetaData;
import vehicleenquiry.parser.VehicleEnquiryFileParser;

public class CSVVehicleEnquiryParser implements VehicleEnquiryFileParser {

	@Override
	public List<VehicleDetails> parse(VehicleFileMetaData vMetaData) {
		List<VehicleDetails> vhdetails= new ArrayList<VehicleDetails>();
		VehicleFileFilter fileFilter = new VehicleFileFilter(ConfigEnum.INSTANCE.getTestDataFolder());
		File file = fileFilter.filter(vMetaData.getFileName());
		return vhdetails;
	}

	@Override
	public Boolean canParse(VehicleFileMetaData vMetaData) {
		return SupportedMIMEType.TXTCSV.getMIMETypeText().equalsIgnoreCase(vMetaData.getMIMEType())
				&& SupportedFileExten.CSV.toString().equalsIgnoreCase(vMetaData.getExtension());
	}

}
