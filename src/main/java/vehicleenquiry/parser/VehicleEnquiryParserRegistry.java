package vehicleenquiry.parser;

import vehicleenquiry.parserimpl.XlsVehicleEnquiryParser;

import java.util.Arrays;
import java.util.Optional;

import vehicleenquiry.file.VehicleFileMetaData;
import vehicleenquiry.parserimpl.CSVVehicleEnquiryParser;

public enum VehicleEnquiryParserRegistry {
	XLSParser(new XlsVehicleEnquiryParser()), CSVParser(new CSVVehicleEnquiryParser());
	VehicleEnquiryFileParser parser;

	VehicleEnquiryParserRegistry(VehicleEnquiryFileParser inParser) {
		parser = inParser;
	}

	public VehicleEnquiryFileParser getParser() {
		return parser;
	}

	
	/**
	 * Based on VehicleFileMetaData return the matching first parser form the registry
	 * @param vMetaData
	 * @return
	 */
	public static VehicleEnquiryFileParser getVehicleFileParser(VehicleFileMetaData vMetaData) {
		Optional<VehicleEnquiryParserRegistry> optional = Arrays.asList(values()).stream().filter(p -> p.parser.canParse(vMetaData)).findFirst();
		if(optional.isPresent()) {
			return optional.get().getParser();
		}
		return null;
	}

}
