package ana.util.converter;

import java.lang.reflect.Field;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings({"rawtypes","unchecked"})
public class BeanToHashMap {
	
	public static HashMap directPack(Object o){
		Class oclass = o.getClass();
		Field[] f = oclass.getDeclaredFields();
		HashMap s = new HashMap();
		for (int i = 0; i < f.length; i++){
			boolean acc = f[i].isAccessible();
			f[i].setAccessible(true);
			try {
				s.put(f[i].getName(), f[i].get(o));
			} catch (Exception e) {
				e.printStackTrace();
			}
			f[i].setAccessible(acc);
		}
		return s;
	}

	public static HashMap<String,String> anyToString(HashMap m){
		HashMap<String,String> converted = new HashMap<String,String>();
		Set keys = m.keySet();
		for(Iterator i = keys.iterator();i.hasNext();){
			String keyname = i.next().toString();
			String val = m.get(keyname)==null?null:valToString(m.get(keyname));
			converted.put(keyname, val);
		}
		return converted;
	}

	private static String valToString(Object object) {
		String type = object.getClass().getName();
		if (type.equalsIgnoreCase("java.lang.String") || type.equalsIgnoreCase("java.lang.Integer")
			|| type.equalsIgnoreCase("java.lang.Long")) {
			
			try{
				
				return (String) Class.forName(type)
									.getDeclaredMethod("toString", new Class[0])
										.invoke(object, new Object[0]);
				
			} catch(Exception e){
				e.printStackTrace();
				return "Convert Err!";
			}
			
			
		} else if(type.equalsIgnoreCase("java.sql.Timestamp") || type.equalsIgnoreCase("java.util.Calendar")) {
			
			try{
				
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
						Class.forName(type)
							.getDeclaredMethod("getTime", new Class[0])
								.invoke(object, new Object[0]));
				
			} catch(Exception e){
				e.printStackTrace();
				return "Convert Err!";
			}
			
		} else if(type.equalsIgnoreCase("java.util.GregorianCalendar")) {
			
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(((GregorianCalendar) object).getTime());
			
			
		} else if(type.equalsIgnoreCase("java.sql.Blob")) {

			return BlobProcess.blobToStr((Blob) object, "utf-8");
			
		} else if(type.contains("$Proxy")){
			String realtype = object.toString().trim().replaceFirst("@.*", "");
			
			if(realtype.equalsIgnoreCase("java.sql.Blob") || realtype.equalsIgnoreCase("com.mysql.jdbc.Blob")){
				
				return BlobProcess.blobToStr((Blob) object, "utf-8");
				
			} else {
				
				return object.toString();
				
			}
			
			
		} else {
			System.out.println(type);
			return object.toString();
			
		}
	}

}
