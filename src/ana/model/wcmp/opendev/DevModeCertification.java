package ana.model.wcmp.opendev;


import java.util.Arrays;

import ana.util.encrypt.StringEncrypt;

/**
 * ��֤�׶�GET�������Ϣ����
 * 
 * @author Godrick
 *	Referring to <WeChat Public Platform Application Developing> (by Liufeng) 
 */
public class DevModeCertification {
	
	private static String token = "iLeft";//Ԥ����Ϣ���˴�Ӧ����վ�Ͽ�����������Ԥ������Ϣһ�¡�
	
	//������Ϣ�����ж���ǩ���Ƿ��ͬ
	public static boolean checkSig(String signature, String timestamp, String nonce){
		String[] strArr = new String[] { token, timestamp, nonce };
		Arrays.sort(strArr);	//����
		String AfterProcess = strArr[0].concat(strArr[1]).concat(strArr[2]);  //ƴ��
		String Encrypted = StringEncrypt.doEnc(AfterProcess, "SHA-1");
		
		return Encrypted != null ? Encrypted.equals(signature.toUpperCase()) : false;	//�жϲ�����һ������ֵ
	}

}
