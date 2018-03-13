package vehicleenquiry.parser;

import vehicleenquiry.parserimpl.XlsVehicleEnquiryParser;

import java.util.Arrays;
import java.util.Optional;

import vehicleenquiry.file.VehicleFileMetaData;
import vehicleenquiry.parserimpl.CSVVehicleEnquiryParser;

public enum VehicleEnquiryParserRegistory {
	XLSParser(new XlsVehicleEnquiryParser()), CSVParser(new CSVVehicleEnquiryParser());
	VehicleEnquiryFileParser parser;

	VehicleEnquiryParserRegistory(VehicleEnquiryFileParser inParser) {
		parser = inParser;
	}

	public VehicleEnquiryFileParser getParser() {
		return parser;
	}

	public static VehicleEnquiryFileParser getVehicleFileParser(VehicleFileMetaData vMetaData) {
		Optional<VehicleEnquiryParserRegistory> optional = Arrays.asList(values()).stream().filter(p -> p.parser.canParse(vMetaData)).findFirst();
		if(optional.isPresent()) {
			return optional.get().getParser();
		}
		return null;
	}

}
