<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<%@ page import="java.util.*"%>
<%@	page import="ana.model.auth.outlink.Authrizor" %>
<%@ page import="ana.util.encrypt.*" %>
<%@	page import="org.apache.http.*" %>
<%@	page import="org.apache.http.client.*" %>
<%@	page import="org.apache.http.client.entity.*" %>
<%@	page import="org.apache.http.client.methods.*" %>
<%@	page import="org.apache.http.entity.*" %>
<%@	page import="org.apache.http.entity.mime.*" %>
<%@	page import="org.apache.http.impl.client.*" %>
<%@	page import="org.apache.http.message.*" %>
<%@	page import="org.apache.http.util.*" %>

<%	//首先进行身份验证，通过则颁发浮动秘钥至cookie
	request.setCharacterEncoding("gbk");
	response.setCharacterEncoding("gbk");

	String pagepointer = request.getParameter("page") == null ? "1":request.getParameter("page").toString();
	String pagelim = request.getParameter("pagelim") == null ? "10":request.getParameter("pagelim").toString();
	String search_id = request.getParameter("search_id") == null ? "":request.getParameter("search_id").toString();
	String search_keyword = request.getParameter("search_keyword") == null ? "":request.getParameter("search_keyword").toString();
	String search_timestamp_start = request.getParameter("search_timestamp_start") == null ? "":request.getParameter("search_timestamp_start").toString().replace("T", " ");
	String search_timestamp_end = request.getParameter("search_timestamp_end") == null ? "":request.getParameter("search_timestamp_end").toString().replace("T", " ");
	String search_resvtime_start = request.getParameter("search_resvtime_start") == null ? "":request.getParameter("search_resvtime_start").toString().replace("T", " ");
	String search_resvtime_end = request.getParameter("search_resvtime_end") == null ? "":request.getParameter("search_resvtime_end").toString().replace("T", " ");
	String search_status = request.getParameter("search_status") == null ? "":request.getParameter("search_status").toString();
	
	CloseableHttpClient httpclient = HttpClients.createDefault(); 
	HttpPost httppost = new HttpPost("http://127.0.0.1/AdminOrder");//社团服务器地址
	List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();  
    formparams.add(new BasicNameValuePair("sig", Authrizor.getLinkCode()));  
    formparams.add(new BasicNameValuePair("url", request.getRequestURL().toString())); 
    formparams.add(new BasicNameValuePair("page", pagepointer)); 
    formparams.add(new BasicNameValuePair("pagelim", pagelim)); 
    formparams.add(new BasicNameValuePair("search_id", search_id)); 
    formparams.add(new BasicNameValuePair("search_keyword", search_keyword));
    formparams.add(new BasicNameValuePair("search_timestamp_start", search_timestamp_start)); 
    formparams.add(new BasicNameValuePair("search_timestamp_end", search_timestamp_end));
    formparams.add(new BasicNameValuePair("search_resvtime_start", search_resvtime_start)); 
    formparams.add(new BasicNameValuePair("search_resvtime_end", search_resvtime_end));
    formparams.add(new BasicNameValuePair("search_status", search_status));
    UrlEncodedFormEntity uefEntity; 
    try {  
        uefEntity = new UrlEncodedFormEntity(formparams, "gbk");  
        httppost.setEntity(uefEntity);   
        CloseableHttpResponse responses = httpclient.execute(httppost);  
        try {  
            HttpEntity entity = responses.getEntity();  
            if (entity != null) {  
                response.getWriter().print((EntityUtils.toString(entity, "gbk"))); 
            }  
        } finally {  
            responses.close();  
        }  
    }  catch (Exception e) {  
        e.printStackTrace();  
    } finally {  
        // 关闭连接,释放资源    
        try {  
            httpclient.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
%>