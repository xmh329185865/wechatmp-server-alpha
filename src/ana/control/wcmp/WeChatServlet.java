package ana.control.wcmp;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ana.model.wcmp.handle.Handler;
import ana.model.wcmp.opendev.DevModeCertification;

/**
 * Servlet implementation class MainProgram
 * ΢�ſ�����ģʽ������
 * 
 * ������ģʽ��֤����Ϊ��΢�ŷ������򿪷��߷���������һ��http��GET�������а������ݣ�
 * ����ǩ����signature     ʱ�����timestamp    �����:nonce     ����ַ���:echostr
 * ���м�����������Ԥ����token��������ƴ�ӣ���sha1���ܺ���ǩ���Աȣ�����ͬ����ȷ����������΢�ŷ�����������echostr�����֤
 */
@WebServlet(name="Wechatmp" ,urlPatterns={"/wechatmp"})  //�°��web.xml���
public class WeChatServlet extends HttpServlet {
	
	public static ServletContext serc;
	
	private static final long serialVersionUID = 1L;
    /**
     * Default constructor. 
     */
    public WeChatServlet() {
    	System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+" "+"΢�Ź���ƽ̨�������������");
    }
    
    //Servlet�ĳ�ʼ�����������ν���ʱ���ò�����һ��
    public void init() {
    	System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+" "+"΢�Ź���ƽ̨������ѳ�ʼ����");
    }   
        	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * ��������΢�ŷ�������GET����
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ����
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = response.getWriter();
		
		if (DevModeCertification.checkSig(signature, timestamp, nonce)){
			out.print(echostr);
		}		//�жϴ������Ϣ�Ƿ��ͬ�ڼ���ǩ����������֤ͨ��������Ԥ����echostr��΢�ŷ����������֤��
		out.close();
		out = null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * ��Ϣ�Ľ��ա�������Ӧ
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//��ֹ���룬����������Ӧ�����ı����ΪUTF-8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//������֤������ȡ��ͬ��������֤һ����Ҫȷ����������΢�ŷ��������ܶ���
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		
		String respXml = null;
		//��֤�ɹ����򷵻���Ϣ
		if (DevModeCertification.checkSig(signature, timestamp, nonce)){
			Handler processCore= new Handler();
			respXml = processCore.processReq(request); //���ö���õ�Process�ദ����Ϣ
		}
		
		PrintWriter out = response.getWriter();
		out.print(respXml);
		out.close();
		out = null;
	}
	
}
