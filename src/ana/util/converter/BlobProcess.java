package ana.util.converter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Blob;

public class BlobProcess {

	
	private static String EncodingCharset="utf-8";
	
	/** 转换blob对象到String
	 * @author 谢慕寒  2014-08-01
	 * @param Blob b - 需要处理的blob对象
	 * */
	public static String blobToStr(Blob b, String charset){
		if (b == null) return null;
		
		if(charset != null) EncodingCharset=charset;
		StringBuffer bloboutput = new StringBuffer("");
		try {
			String temp;
			BufferedReader inb = new BufferedReader(new InputStreamReader(b.getBinaryStream(),EncodingCharset));
			while ((temp=inb.readLine()) != null){
				bloboutput.append(temp);
			}
			inb.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return bloboutput.toString();
	}
	
}
