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
		File file = fileFilter.filter("VRNExcel_1.xls");
		assertEquals("File is not in folder ", "VRNExcel_1.xls", file.getName());
	}

	@Test
	public void evaluatesFileMetaDataXls() {
		VehicleFileFilter fileFilter = new VehicleFileFilter("./testdata");
		File file = fileFilter.filter("VRNExcel_1.xls");
		VehicleFileMetaData metadata = fileFilter.mapVehicleMetaData.apply(file.toPath());
		assertEquals("Unexpected  Mime Type ", "application/vnd.ms-excel", metadata.getMIMEType());
		assertEquals("Unexpected  Extension ", "xls", metadata.getExtension());
		assertTrue("Unexpected  size ", metadata.getFileSize() > 0);
	}

	@Test
	public void evaluatesFileMetaDataCsv() {
		VehicleFileFilter fileFilter = new VehicleFileFilter("./testdata");
		File file = fileFilter.filter("Exisit_vehicle_enquiry_csv.csv");
		VehicleFileMetaData metadata = fileFilter.mapVehicleMetaData.apply(file.toPath());
		assertEquals("Unexpected  Mime Type ", "text/csv", metadata.getMIMEType());
		assertEquals("Unexpected  Extension ", "csv", metadata.getExtension());
		assertTrue("Unexpected  size", metadata.getFileSize() > 0);
	}
}
