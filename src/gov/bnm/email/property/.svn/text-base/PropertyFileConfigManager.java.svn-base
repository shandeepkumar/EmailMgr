package gov.bnm.email.property;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * @author Ramakrishna Metla
 *	
 */
public class PropertyFileConfigManager {
	private static Logger logger=Logger.getLogger(PropertyFileConfigManager.class);
	
	private static PropertyFileConfigManager instance;
	
	public static PropertyFileConfigManager getInstance() {
		if (instance == null) {
			instance = new PropertyFileConfigManager();
		}
		return instance;
	}
	
	public  Properties getConfigFile(String filename) {
		Properties result = new Properties();

		InputStream input = null;
		try {
			input = new FileInputStream(filename);

			result = new Properties();
			result.load(input);

		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {

			try {
				if (input != null)
					input.close();
			} catch (IOException ex) {
				logger.error(ex.getMessage(), ex);
				logger.error("Cannot close properties file.");
			}

		}

		return result;

	}

	
}
