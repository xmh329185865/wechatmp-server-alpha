package ana.server.properties;

import java.util.Properties;

import ana.util.loader.PropertiesLoader;

public class ServerProperties {
	
	public static String webrootpath;
	public static String orderhandin_path;
	public static String orderqrcercode_path;
	public static String base_path;
	public static String conf_path;
	public static String server_rootpath;
	public static String order_processor;
	
	static{
		base_path = Thread.currentThread().getContextClassLoader().getResource("").toString().replaceFirst("file:/", "");
		base_path = base_path.substring(0,base_path.length()-1);
		
		PropertiesLoader pu = new PropertiesLoader();
		Properties props = pu.getProperties(base_path+"/"+"ServerProperties.properties");
		
		webrootpath = props.getProperty("webrootpath");
		orderhandin_path = props.getProperty("orderhandin_path");
		orderqrcercode_path = props.getProperty("orderqrcercode_path");
		conf_path = props.getProperty("conf_path");
		server_rootpath = props.getProperty("server_rootpath");
		order_processor = props.getProperty("order_processor");
	}
}
