package ana.view.htmlwriter.htmltags;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import ana.view.htmlwriter.exceptions.NoSuchTagException;

@SuppressWarnings({"rawtypes"})
public class HTMLTagFactory {
	
	private List<HTMLTag> preloadedTags = new ArrayList<HTMLTag>();
	
	public HTMLTagFactory(){
		String filepath = HTMLTagFactory.class.getClass().getResource("/").getPath();
		filepath += "PreloadHTMLTagList.properties";
		InputStream fileinputstream = null;
		try {
			fileinputstream = new FileInputStream(filepath);
			Properties props = new Properties();
			props.load(fileinputstream);
			Set keyset = props.keySet();
			for(Iterator i = keyset.iterator(); i.hasNext();){
				String keyname = i.next().toString();
				this.preloadedTags.add(new HTMLTag(props.getProperty(keyname)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("不能读取属性文件！请确保"+filepath+"存在！");
		} finally {
			if (fileinputstream != null) {
				try {
					fileinputstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public HTMLTag getPreloadedTag(String tagName) throws NoSuchTagException{
		for (Iterator<HTMLTag> i = this.preloadedTags.iterator(); i.hasNext();){
			HTMLTag temptag = i.next();
			if(temptag.getTagName().equalsIgnoreCase(tagName)){
				return temptag;
			}
		}
		throw new NoSuchTagException();
	}

}
