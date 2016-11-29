package edu.msg.flightmanager.backend.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.msg.flightmanager.backend.exception.CsvException;

public class CsvFileReader {

	private static final Logger LOGGER = LoggerFactory.getLogger(CsvFileReader.class);

	private static final String WORDS_DELIMITER = ",";

	public static List<List<String>> readCsvFile(InputStream inputStream) throws CsvException {
		// a buffered reader is used to read from the file
		// return a List<List<String>> entities
		// each list from inside entities represents the fields of an entity
		BufferedReader bufferedReader = null;
		try {
			LOGGER.info("Try to read from a csv file.");
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			List<List<String>> entities = new ArrayList<>();

			// read the header in order to append it as the first list of
			// strings
			String[] header = bufferedReader.readLine().split(WORDS_DELIMITER);
			List<String> headerList = new ArrayList<>();
			for (int i = 0; i < header.length; i++) {
				headerList.add(header[i]);
			}
			entities.add(headerList);

			// read the file line by line
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				// separate the values
				String[] lineValues = line.split(WORDS_DELIMITER);
				List<String> entity = new ArrayList<>();
				for (int i = 0; i < lineValues.length; i++) {
					entity.add(lineValues[i]);
				}
				// add the entity to the list of entities
				entities.add(entity);
			}
			LOGGER.info("The csv file was successfully read.");
			return entities;
		} catch (IOException e) {
			LOGGER.error("Could not read from csv file.", e);
			throw new CsvException("Could not read from csv file.");
		} finally {
			try {
				LOGGER.info("Try to close the reader of the csv file.");
				bufferedReader.close();
				LOGGER.info("The reader of the csv file successfully closed.");
			} catch (IOException e) {
				LOGGER.error("Could not close the reader of the csv file.", e);
				throw new CsvException("Could not close the reader of the csv file.");
			}
		}
	}
}
