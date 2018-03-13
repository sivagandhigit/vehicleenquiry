package vehicleenquiry.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import vehicleenquiry.config.ConfigEnum;

/**
 * 
 * Filter the configured  exten  files form the given directory 
 *
 */
public class VehicleFileFilter {
	Logger logger = LogManager.getLogger(VehicleFileFilter.class);
	String dirPath;
	String[] supportedExtensions;
	Path folder;

	public VehicleFileFilter(String inDirPath) {
		dirPath = inDirPath;
		URL url = ClassLoader.getSystemResource(dirPath);
		try {
			folder = Paths.get(url.toURI());
		} catch (URISyntaxException e) {
		}
		supportedExtensions = ConfigEnum.INSTANCE.getSupportedFileTypes().split(",");
	}

	
	/**
	 * Filter the files form given directory and extracts required meta data 
	 * @return List<VehicleFileMetaData>
	 */
	public List<VehicleFileMetaData> filter() {
		List<VehicleFileMetaData> vehicleList = new ArrayList<VehicleFileMetaData>();
		try {
			List<Path> collect = Files.walk(folder)
					.filter(p -> Arrays.asList(supportedExtensions)
							.contains(FilenameUtils.getExtension(p.getFileName().toString())))
					.collect(Collectors.toList());
			collect.forEach(p -> {
				logger.info(()-> p.getFileName());
				vehicleList.add(mapVehicleMetaData.apply(p));
			});
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return vehicleList;
	}
	/**
	 * Filter the  given file from the folder path.
	 * @return File
	 */
	public File filter(String filename) {
		File file = null;
		try {
			file = Files.walk(folder).filter(p -> filename.equals(p.getFileName().toString())).findFirst().get()
					.toFile();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return file;
	}


	/**
	 * function mapping to extract the meta data from the files 
	 */
	public Function<Path, VehicleFileMetaData> mapVehicleMetaData = new Function<Path, VehicleFileMetaData>() {

		public VehicleFileMetaData apply(Path path) {
			VehicleFileMetaData fileMetaData = new VehicleFileMetaData();
			File file = path.toFile();
			fileMetaData.setFileName(file.getName());
			fileMetaData.setExtention(FilenameUtils.getExtension(file.getName()));
			fileMetaData.setFileSize(FileUtils.sizeOf(file));
			fileMetaData.setMIMEType(getMIMEType(file));
			return fileMetaData;
		}
	};


	/**
	 * Used tick framework to get the right mime type based on content. 
	 * Other methods are not accurate , it would  work based file name extension
	 * 
	 * @param file
	 * @return content mime type
	 */
	public String getMIMEType(File file) {
		FileInputStream is;
		String MIMEType = null;
		try {
			is = new FileInputStream(file);
			ContentHandler contenthandler = new BodyContentHandler();
			Metadata metadata = new Metadata();
			metadata.set(Metadata.RESOURCE_NAME_KEY, file.getName());
			Parser parser = new AutoDetectParser();
			// OOXMLParser parser = new OOXMLParser();
			parser.parse(is, contenthandler, metadata);
			MIMEType = metadata.get(Metadata.CONTENT_TYPE);
			logger.info(file.getName()+MIMEType);
		} catch (IOException | SAXException | TikaException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return MIMEType;
	}
}
