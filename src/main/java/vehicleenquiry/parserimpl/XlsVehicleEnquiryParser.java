package vehicleenquiry.parserimpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import vehicleenquiry.config.ConfigEnum;
import vehicleenquiry.domain.SupportedFileExten;
import vehicleenquiry.domain.SupportedMIMEType;
import vehicleenquiry.domain.VehicleDetails;
import vehicleenquiry.file.VehicleFileFilter;
import vehicleenquiry.file.VehicleFileMetaData;
import vehicleenquiry.parser.VehicleEnquiryFileParser;

public class XlsVehicleEnquiryParser implements VehicleEnquiryFileParser {
	Logger logger = LogManager.getLogger(XlsVehicleEnquiryParser.class);

	@Override
	public List<VehicleDetails> parse(VehicleFileMetaData vMetaData) {
		List<VehicleDetails> vhdetails = new ArrayList<VehicleDetails>();
		VehicleFileFilter fileFilter = new VehicleFileFilter(ConfigEnum.INSTANCE.getTestDataFolder());
		File file = fileFilter.filter(vMetaData.getFileName());
		logger.debug(file.getName());
		try {
			Workbook workbook = WorkbookFactory.create(file);
			Iterator<Sheet> sheetIterator = workbook.sheetIterator();
			if (sheetIterator.hasNext()) {
				Sheet sheet = sheetIterator.next();
				sheet.rowIterator().forEachRemaining(row -> {
					if (row.getRowNum() != 0) {
						vhdetails.add(mapVehicleDetails.apply(row));
					}
				});
			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

		return vhdetails;
	}

	@Override
	public Boolean canParse(VehicleFileMetaData vMetaData) {
		return SupportedMIMEType.APPXLS.getMIMETypeText().equalsIgnoreCase(vMetaData.getMIMEType())
				&& SupportedFileExten.XLS.toString().equalsIgnoreCase(vMetaData.getExtension());
	}

	private Function<Row, VehicleDetails> mapVehicleDetails=new Function<Row,VehicleDetails>(){

	public VehicleDetails apply(Row row){VehicleDetails vehicleDetails=new VehicleDetails();vehicleDetails.setVrn(row.getCell(0).toString());vehicleDetails.setModel(row.getCell(1).toString());vehicleDetails.setColour(row.getCell(2).toString());return vehicleDetails;}};

}
