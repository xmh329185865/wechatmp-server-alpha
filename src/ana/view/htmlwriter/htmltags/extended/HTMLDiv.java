package ana.view.htmlwriter.htmltags.extended;

import java.util.Iterator;

import ana.view.htmlwriter.exceptions.UnsetAttributeException;
import ana.view.htmlwriter.htmltags.HTMLTag;

public class HTMLDiv extends HTMLTag{

	public HTMLDiv(){
		super();
		this.setTagName("div");
	}
	
	@Override
	public String getHTMLBlock() throws UnsetAttributeException{
		StringBuffer backstring = new StringBuffer(this.getStartTag());
		backstring.append(this.getInnerHTML());
		for (Iterator<HTMLTag> i = this.getSubTags().iterator();i.hasNext();){
			HTMLTag temptag = i.next();
			backstring.append(temptag.getHTMLBlock());	
		}
		backstring.append(this.getEndTag());
		return backstring.toString();
	}
	
}
