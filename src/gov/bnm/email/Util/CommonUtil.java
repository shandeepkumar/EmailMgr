package gov.bnm.email.Util;

/**
 * @author Ramakrishna Metla
 *	
 */

public class CommonUtil {
	
	public static final String EMPTY_STRING = "";
	public static final String[] EMPTY_STRING_ARRAY = {};
    public static final int ZERO = 0;
   
	public static String getStr(Object obj) {
        if (obj != null) {
            return obj.toString().trim();
        } else {
            return EMPTY_STRING;
        }
    }

	
	public static Integer getInt(Object obj) {
        if (obj != null && !obj.equals("")) {
            return Integer.parseInt(obj.toString().trim());
        } else {
            return ZERO;
        }
    }
}
