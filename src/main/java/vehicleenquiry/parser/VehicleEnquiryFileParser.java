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
	 *  1. Walk through the configured dir and pickup the appropriate file based on meta data. 
	 *  2. Pickup the right parser based on file meta data 
	 *  3. Parse the file and  for each row map VehicleDetails
	 *  
	 * @param vMetaData
	 * @return List<VehicleDetails>
	 */
	public List<VehicleDetails> parse(VehicleFileMetaData vMetaData);
	
	/**
	 * Method used the filter the right parser based on VehicleFileMetaData
	 * @param vMetaData
	 * 
	 * @return
	 */
	public Boolean canParse(VehicleFileMetaData vMetaData);
}
