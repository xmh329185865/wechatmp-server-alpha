package ana.view.htmlwriter.htmltags.extended;

import java.util.Iterator;

import ana.view.htmlwriter.exceptions.UnsetAttributeException;
import ana.view.htmlwriter.htmltags.HTMLTag;

public class HTMLif extends HTMLTag{
	
	private String ifexpr;

	@Override
	public String getStartTag(){
		StringBuffer stt = new StringBuffer("<!--[if ");
		stt.append(this.ifexpr);
		stt.append("]>");
		return stt.toString();
	}
	
	@Override
	public String getEndTag(){
		return "<![endif]-->";
	}
	
	@Override
	public String getHTMLBlock(){
		StringBuffer stt = new StringBuffer(this.getStartTag());
		if(!this.isInnerHTMLEmpty()){
			stt.append(this.getInnerHTML());
		}
		for (Iterator<HTMLTag> i = this.getSubTags().iterator();i.hasNext();){
			HTMLTag temptag = i.next();
//			if ((temptag.getSubTags() == null || temptag.getSubTags().isEmpty()) && temptag.isInnerHTMLEmpty()){
//				backstring.append(temptag.getEmptyTag());
//			} else {
			try {
				stt.append(temptag.getHTMLBlock());
			} catch (UnsetAttributeException e) {
				e.printStackTrace();
			}
		}
		stt.append(this.getEndTag());
		return stt.toString();
	}
	
	public String getIfexpr() {
		return ifexpr;
	}

	public void setIfexpr(String ifexpr) {
		this.ifexpr = ifexpr;
	}
	
}
