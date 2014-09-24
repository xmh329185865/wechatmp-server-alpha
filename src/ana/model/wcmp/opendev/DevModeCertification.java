package ana.model.wcmp.opendev;


import java.util.Arrays;

import ana.util.encrypt.StringEncrypt;

/**
 * 验证阶段GET请求的信息处理
 * 
 * @author Godrick
 *	Referring to <WeChat Public Platform Application Developing> (by Liufeng) 
 */
public class DevModeCertification {
	
	private static String token = "iLeft";//预留信息，此处应与网站上开发者中心中预留的信息一致。
	
	//处理信息，并判断与签名是否等同
	public static boolean checkSig(String signature, String timestamp, String nonce){
		String[] strArr = new String[] { token, timestamp, nonce };
		Arrays.sort(strArr);	//排序
		String AfterProcess = strArr[0].concat(strArr[1]).concat(strArr[2]);  //拼接
		String Encrypted = StringEncrypt.doEnc(AfterProcess, "SHA-1");
		
		return Encrypted != null ? Encrypted.equals(signature.toUpperCase()) : false;	//判断并返回一个布尔值
	}

}
