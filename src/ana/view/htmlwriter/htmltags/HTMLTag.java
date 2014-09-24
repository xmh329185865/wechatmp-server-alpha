package ana.view.htmlwriter.htmltags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ana.view.htmlwriter.exceptions.AttrNotFoundException;
import ana.view.htmlwriter.exceptions.TagnumOutOfBoundsException;
import ana.view.htmlwriter.exceptions.NoSuchTagException;
import ana.view.htmlwriter.exceptions.UnsetAttributeException;

public class HTMLTag {
	
	private String tagName;
	private HashMap<String,String> attrlist = new HashMap<String,String>();
	private List<HTMLTag> subTags = new ArrayList<HTMLTag>();
	private String innerHTML = "";

	public HTMLTag(){
	}
	
	public HTMLTag(String tagName){
		this.tagName = tagName;
	}
	
	public HTMLTag(String tagName, List<HTMLTag> subTags){
		this.tagName = tagName;
		this.subTags = subTags;
	}
	
	public HTMLTag(String tagName, HashMap<String,String> attrlist, String innerHTML){
		this.tagName = tagName;
		this.attrlist = attrlist;
		this.innerHTML = innerHTML;
	}
	
	public HTMLTag(String tagName, String innerHTML){
		this.tagName = tagName;
		this.innerHTML = innerHTML;
	}
	
	public HTMLTag(String tagName, HashMap<String,String> attrlist){
		this.tagName = tagName;
		this.attrlist = attrlist;
	}
	
	public String getTagName(){
		return this.tagName;
	}
	
	public void setTagName(String tagName){
		this.tagName = tagName;
	}
	
	public HashMap<String,String> getAttrlist(){
		return this.attrlist;
	}
	
	public void setAttrlist(HashMap<String,String> attrlist){
		this.attrlist = attrlist;
	}
	
	public String getAttr(String attrname){
		return this.attrlist.get(attrname);
	}	
	
	public void addOrSetAttr(String attrname, String attrvalue){
		this.attrlist.put(attrname, attrvalue);
	}
	
	public String getInnerHTML(){
		return this.innerHTML;
	}
	
	public void setInnerHTML(String innerHTML){
		this.innerHTML = innerHTML;
	}
	
	public void appendInnerHTML(String append){
		this.innerHTML += append;
	}
	
	public HTMLTag getSubTag(String tagName) throws NoSuchTagException{
		for (Iterator<HTMLTag> i = this.subTags.iterator();i.hasNext();){
			HTMLTag temptag = i.next();
			if (temptag.getTagName().equalsIgnoreCase(tagName)){
				return temptag;
			}
		}
		throw new NoSuchTagException();
	}
	
	public HTMLTag getSubTagByID(String tagID) throws NoSuchTagException{
		for (Iterator<HTMLTag> i = this.subTags.iterator();i.hasNext();){
			HTMLTag temptag = i.next();
			if (temptag.getAttr("id") != null && temptag.getAttr("id").equalsIgnoreCase(tagID)){
				return temptag;
			}
		}
		throw new NoSuchTagException();
	}
	
	public void addSubTag(HTMLTag subtag){
		this.subTags.add(subtag);
	}
	
	public List<HTMLTag> getSubTags(){
		return this.subTags;
	}

	public void setSubTags(List<HTMLTag> subTags){
		this.subTags = subTags;
	}
	
	public void deleteSubTag(int tagnum) 
			throws TagnumOutOfBoundsException {
		try{
			this.subTags.remove(tagnum);
		} catch (IndexOutOfBoundsException e){
			throw new TagnumOutOfBoundsException();
		}
		
	}
	
	public String getStartTag() 
			throws UnsetAttributeException {
		StringBuffer backstring = new StringBuffer("<");
		backstring.append(this.tagName);
		Set<String> attrnames = this.attrlist.keySet();
		for (Iterator<String> i = attrnames.iterator();i.hasNext();){
			String attrname = i.next();
			String attrvalue = this.attrlist.get(attrname);
			if (attrvalue==null) throw new UnsetAttributeException();
			
			backstring.append(" ");
			backstring.append(attrname);
			backstring.append("=\"");
			backstring.append(attrvalue);
			backstring.append("\"");
		}
		backstring.append(" >");
		return backstring.toString();
	}
	
	public String getEndTag(){
		return "</"+this.tagName+">";
	}
	
	public String getEmptyTag(){
		StringBuffer backstring = new StringBuffer("<");
		backstring.append(this.tagName);
		Set<String> attrnames = this.attrlist.keySet();
		for (Iterator<String> i = attrnames.iterator();i.hasNext();){
			String attrname = i.next();
			backstring.append(" ");
			backstring.append(attrname);
			backstring.append("=\"");
			backstring.append(this.attrlist.get(attrname));
			backstring.append("\"");
		}
		backstring.append(" />");
		return backstring.toString();
	}
	
	public String getHTMLBlock() throws UnsetAttributeException{
		StringBuffer backstring = new StringBuffer(this.getStartTag());
		if(this.isInnerHTMLEmpty()){
			if(this.subTags.isEmpty()) return this.getEmptyTag();
		} else {
			backstring.append(this.innerHTML);
		}
		for (Iterator<HTMLTag> i = this.subTags.iterator();i.hasNext();){
			HTMLTag temptag = i.next();
//			if ((temptag.getSubTags() == null || temptag.getSubTags().isEmpty()) && temptag.isInnerHTMLEmpty()){
//				backstring.append(temptag.getEmptyTag());
//			} else {
			backstring.append(temptag.getHTMLBlock());
//			}	
		}
		backstring.append(this.getEndTag());
		return backstring.toString();
	}
	
	public boolean isInnerHTMLEmpty(){
		if(this.innerHTML==null||this.innerHTML.equals("")) return true;
		return false;
	}
	
	public String getTagClass() throws AttrNotFoundException{
		if (this.attrlist.get("class")!=null){
			String tagClass = this.attrlist.get("class");
			return tagClass;
		} else {
			throw new AttrNotFoundException();
		}
	}
	
	public void setTagClass(String tagclass) {
		this.attrlist.put("class", tagclass);
	}
	
}
