package ana.util.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import ana.server.properties.ServerProperties;

@SuppressWarnings({"rawtypes","unchecked"})
public class XMLConfLoader {
	
	public static List<HashMap<String,String>> getDBMappingList(String xmlname){
		InputStream fileinputstream = null;
		String fieldspath = ServerProperties.base_path+ServerProperties.conf_path+"/"+xmlname;
		try {
			fileinputstream = new FileInputStream(fieldspath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		SAXReader reader = new SAXReader();
		Element root = null;
		try {
			Document document = reader.read(fileinputstream);
			root = document.getRootElement();	//获得根元素
		} catch (DocumentException e) {
			System.err.println("不能读取属性文件. " + "请确保"+xmlname+"在路径"+fieldspath+"中");
			e.printStackTrace();
		}	//读取输入流
		
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
		List<Element> allelement = root.elements();	//得到根元素的所有子节点
		for (Iterator<Element> i = allelement.iterator(); i.hasNext();){
			Element element = i.next();
			
			List<Attribute> attrtemp = element.attributes();
			HashMap temp = new HashMap();
			for (Iterator<Attribute> ii = attrtemp.iterator();ii.hasNext();){
				Attribute a = ii.next();
				
				temp.put(a.getName(), a.getValue());
			}
			result.add(temp);
		}
		return result;
	}

}
