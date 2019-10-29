package gov.bnm.email.Util;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Ramakrishna Metla
 *	Utility worker class to serve any IO common functions
 */
public class FileUtil {
	
	 public static final String BASE_LOCATION = "D:";
	 public static final String EAPPS_LOCATION = BASE_LOCATION + File.separator + "eApps" + File.separator;
	 public static final String EAPPS_PROP_FILE="eAppsConfig.properties";
	 public static final String RH_LOCATION = "D://RH//";
	 public static final String RH_PROP_FILE="RHConfig.properties";
	 
	public FileUtil() {
	}

	/**
	   * save bytes to file
	   * @param fileName the file to write the supplied bytes
	   * @param theBytes the bytes to write to file
	   * @throws java.io.IOException reports problems saving bytes to file
	*/	
	// TODO [ASHID] Auto-create the folder if not exist
	// TODO [ASHID] We need to check that the file alredy exist
	public static void saveFile(String fileName, byte[] bytez) throws IOException
	{
		saveBytesToStream( new java.io.FileOutputStream( fileName ), bytez);
	}
	
	  /**
	   * save bytes to output stream and close the output stream on success and
	   * on failure.
	   * @param out the output stream to write the supplied bytes
	   * @param theBytes the bytes to write out
	   * @throws java.io.IOException reports problems saving bytes to output stream
	   */
	public static void saveBytesToStream( java.io.OutputStream out, byte[] theBytes) throws IOException {
		try {
			out.write( theBytes );
		}
		finally {
			out.flush();
		    out.close();
		    out = null;
		}
	}
	  	  
	public static String scanFile(String fileName) throws FileNotFoundException{
		String contents = "";
		Scanner scanner = new Scanner(new File(fileName),"UTF-8").useDelimiter("\\Z");
		if (scanner.hasNext()){
		 contents = scanner.next();
		}
		System.out.println("contents::"+contents);
		scanner.close();
		return contents;
	}
	
	public static String scanFile(File file) throws FileNotFoundException{
		Scanner scanner = new Scanner(file).useDelimiter("\\Z");
		String contents = scanner.next();
		scanner.close();
		return contents;
	}
}
