package edu.msg.flightmanager.backend.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.msg.flightmanager.backend.exception.CsvException;

public class CsvFileWriter {

	private static final Logger LOGGER = LoggerFactory.getLogger(CsvFileWriter.class);

	private static final String WORDS_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	public static void writeCsvFile(FileWriter fileWriter, String fileHeader, List<List<String>> entities) throws CsvException {
		//each list from inside values represents the fields of an entity
		try {
			LOGGER.info("Try to write to a csv file.");
			// write the header of the csv file
			fileWriter.append(fileHeader);
			// write a new line after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			// write the entities to the csv file
			for(List<String> entity : entities) {
				// for the fields from 0 to size-2 write the value followed by ,
				for(int i = 0; i <= entity.size()-2; i++) {
					fileWriter.append(entity.get(i));
					fileWriter.append(WORDS_DELIMITER);
				}
				// for the last field, write its value followed by \n
				fileWriter.append(entity.get(entity.size()-1));
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			LOGGER.info("Csv file successfully written.");
		} catch (IOException e) {
			LOGGER.error("Could not write to csv file.", e);
			throw new CsvException("Could not write to csv file.");
		} finally {
			try {
				LOGGER.info("Try to close the writer of the csv file.");
				fileWriter.flush();
				fileWriter.close();
				LOGGER.info("The writer of the csv file successfully closed.");
			} catch (IOException e) {
				LOGGER.error("Could not close the writer of the csv file.", e);
				throw new CsvException("Could not close the writer of the csv file.");
			}
		}
	}

}
