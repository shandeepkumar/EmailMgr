package gov.bnm.email.test;



import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import gov.bnm.email.Util.FileUtil;
import gov.bnm.email.adapter.EmailManager;
import gov.bnm.email.bean.EmailDataBean;
import gov.bnm.email.property.PropertyFileConfigManager;

public class TestEmailManager {

	private EmailDataBean emailBean=null;
	
//	@Before
	public TestEmailManager(){
		emailBean=new EmailDataBean();
		emailBean.setSenderEmail("mramu2004@gmail.com");
		
		String[] strEmail = new String[1];
		strEmail[0] = "mramu2004@gmail.com";
		
		emailBean.setReceiverEmails(strEmail);
        emailBean.setTemplateId("ApprovalForAccessSuppDocs");
        List<File> attachments =new ArrayList<File>();
        attachments.add(new File("C://RH//test.txt"));
        attachments.add(new File("C://RH//search.properties"));
        
        emailBean.setAttachments(attachments);
        Map<String,String> data = new HashMap<String, String>();
        data.put("<%Requester Name%>", "abc");
        data.put("<%supporting doc name%>", "2013");
        data.put("<%policy document title%>", "EA51J22050");
		emailBean.setDataMap(data);
		testEmailMgr();
	}

//	@Test
	public void testEmailMgr(){
		
		EmailManager em=new EmailManager();
		em.sendEmail(emailBean);
		

	
	}
	 
	public static final String decodeSpecialCharacters(String str){		

		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("&#38;", "&");
		str = str.replaceAll("&#40;", "(");
		str = str.replaceAll("&#41;", ")");
		str = str.replaceAll("&#35;", "#");
		
		return str;
		//		StringBuffer sb = new StringBuffer();
		
//		sb.replace
//		
//		return sb.toString();
	}
	  public static String getStr(Object obj) {
          if (obj != null) {
              return obj.toString().trim();
          } else {
              return "";
          }
      }
  public static Integer getInt(Object obj) {
          if (obj != null && !obj.equals("")) {
              return Integer.parseInt(obj.toString().trim());
          } else { return 0; }
      }
	public static void main(String[] args){
		new TestEmailManager();
	}
}
