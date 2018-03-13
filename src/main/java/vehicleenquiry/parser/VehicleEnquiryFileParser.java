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
	 *  Parse the meta data and walk through the dir and fix the appropriate files. 
	 * each row in ht eretrived files are mapped to VehicleDetails
	 * @param vMetaData
	 * @return List<VehicleDetails>
	 */
	public List<VehicleDetails> parse(VehicleFileMetaData vMetaData);
	
	/**
	 * Method used the determine which parser to be used VehicleFileMetaData
	 * @param vMetaData
	 * 
	 * @return
	 */
	public Boolean canParse(VehicleFileMetaData vMetaData);
}
