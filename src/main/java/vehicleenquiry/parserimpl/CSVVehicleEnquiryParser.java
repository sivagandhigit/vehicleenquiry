package vehicleenquiry.parserimpl;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import vehicleenquiry.config.ConfigEnum;
import vehicleenquiry.domain.SupportedFileExten;
import vehicleenquiry.domain.SupportedMIMEType;
import vehicleenquiry.domain.VehicleDetails;
import vehicleenquiry.file.VehicleFileFilter;
import vehicleenquiry.file.VehicleFileMetaData;
import vehicleenquiry.parser.VehicleEnquiryFileParser;

public class CSVVehicleEnquiryParser implements VehicleEnquiryFileParser {
	Logger logger = LogManager.getLogger(CSVVehicleEnquiryParser.class);

	@Override
	public List<VehicleDetails> parse(VehicleFileMetaData vMetaData) {
		List<VehicleDetails> vhdetails = new ArrayList<VehicleDetails>();
		VehicleFileFilter fileFilter = new VehicleFileFilter(ConfigEnum.INSTANCE.getTestDataFolder());
		File file = fileFilter.filter(vMetaData.getFileName());
		logger.debug(file.getName());
		Reader reader;
		try {
			reader = Files.newBufferedReader(file.toPath());
			CSVParser csvParser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
			csvParser.forEach(csvRecord -> {
				vhdetails.add(mapVehicleDetails.apply(csvRecord));
			});
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return vhdetails;
	}

	@Override
	public Boolean canParse(VehicleFileMetaData vMetaData) {
		return SupportedMIMEType.TXTCSV.getMIMETypeText().equalsIgnoreCase(vMetaData.getMIMEType())
				&& SupportedFileExten.CSV.toString().equalsIgnoreCase(vMetaData.getExtension());
	}

	private Function<CSVRecord, VehicleDetails> mapVehicleDetails = new Function<CSVRecord, VehicleDetails>() {

		public VehicleDetails apply(CSVRecord csv) {
			VehicleDetails vehicleDetails = new VehicleDetails();
			vehicleDetails.setVrn(csv.get("VRN"));
			vehicleDetails.setModel(csv.get("Model"));
			vehicleDetails.setColour(csv.get("Colour"));
			return vehicleDetails;
		}
	};
}
