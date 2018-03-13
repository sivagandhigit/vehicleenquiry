package vehicleenquiry.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

public class VehicleFileFilterTest {

	@Test
	public void evaluatesFilter() {
		VehicleFileFilter fileFilter = new VehicleFileFilter("./testdata");
		List<VehicleFileMetaData> metaDataList = fileFilter.filter();
		assertTrue("File Metadata List is Empty", metaDataList.size()>=2);
		assertTrue("Unexpected file in filter", !metaDataList.stream().anyMatch(p->p.getExtension().equals("text")));
	}

	@Test
	public void evaluatesFilterFileName() {
		VehicleFileFilter fileFilter = new VehicleFileFilter("./testdata");
		File file = fileFilter.filter("Exisit_vehicle_enquiry_Excel.xls");
		assertEquals("File is not in folder ", "Exisit_vehicle_enquiry_Excel.xls", file.getName());
	}

	@Test
	public void evaluatesFileMetaDataXls() {
		VehicleFileFilter fileFilter = new VehicleFileFilter("./testdata");
		File file = fileFilter.filter("Exisit_vehicle_enquiry_Excel.xls");
		VehicleFileMetaData metadata = fileFilter.mapVehicleMetaData.apply(file.toPath());
		System.out.println(metadata.getMIMEType());
		assertEquals("Unexpected  Mime Type ", "application/msword", metadata.getMIMEType());
		assertEquals("Unexpected  Extension ", "xls", metadata.getExtension());
		assertTrue("Unexpected  size ", metadata.getFileSize() > 0);
	}

	@Test
	public void evaluatesFileMetaDataCsv() {
		VehicleFileFilter fileFilter = new VehicleFileFilter("./testdata");
		File file = fileFilter.filter("Exisit_vehicle_enquiry.csv");
		VehicleFileMetaData metadata = fileFilter.mapVehicleMetaData.apply(file.toPath());
		assertEquals("Unexpected  Mime Type ", "text/csv", metadata.getMIMEType());
		assertEquals("Unexpected  Extension ", "csv", metadata.getExtension());
		assertTrue("Unexpected  size", metadata.getFileSize() > 0);
	}
}
