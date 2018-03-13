package vehicleenquiry.parser;

import java.util.List;

import vehicleenquiry.domain.VehicleDetails;
import vehicleenquiry.file.VehicleFileMetaData;

/**
 * @author User
 *
 */
public interface VehicleEnquiryFileParser {
	/**
	 * @param vMetaData
	 * @return
	 */
	public List<VehicleDetails> parse(VehicleFileMetaData vMetaData);
	
	/**
	 * @param vMetaData
	 * @return
	 */
	public Boolean canParse(VehicleFileMetaData vMetaData);
}
