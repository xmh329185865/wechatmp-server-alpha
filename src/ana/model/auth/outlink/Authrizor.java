package ana.model.auth.outlink;

import java.text.SimpleDateFormat;
import java.util.Date;

import ana.util.encrypt.StringEncrypt;

public class Authrizor {

	private static final String link_token = "xjtunoc@xjtuana";
	
	public static String getLinkCode(){
		String datetime = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date());
		String raw = datetime + link_token;
		return StringEncrypt.doEnc(raw, "sha1");
	}
}
