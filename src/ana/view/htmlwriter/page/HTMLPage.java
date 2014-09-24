package ana.view.htmlwriter.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ana.view.htmlwriter.exceptions.CharsetNotFoundException;
import ana.view.htmlwriter.exceptions.NoSuchTagException;
import ana.view.htmlwriter.exceptions.NoTitleFoundException;
import ana.view.htmlwriter.exceptions.UnsetAttributeException;
import ana.view.htmlwriter.htmltags.HTMLTag;
import ana.view.htmlwriter.htmltags.extended.HTMLScript;

public class HTMLPage {
	
	private HTMLTag rootTag = new HTMLTag("html");
	private List<HTMLTag> rootStructure = new ArrayList<HTMLTag>();
	private List<HTMLTag> bodyContent = new ArrayList<HTMLTag>();
	
	public HTMLPage(){
		HTMLTag head = new HTMLTag("head");
		HTMLTag meta = new HTMLTag("meta");
		meta.addOrSetAttr("charset", "utf-8");
		head.addSubTag(meta);
		head.addSubTag(new HTMLTag("title"));
		try {
			head.getSubTag("title").setInnerHTML("Insert a title here");
		} catch (NoSuchTagException e) {
			e.printStackTrace();
		}
		HTMLTag body = new HTMLTag("body");
		body.setSubTags(this.bodyContent);
		this.rootStructure.add(head);
		this.rootStructure.add(body);
		this.rootTag.setSubTags(this.rootStructure);
	}
	
	public String outputHTMLPage(){
		try {
			return this.rootTag.getHTMLBlock();
		} catch (UnsetAttributeException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addMeta(HTMLTag meta){
		try {
			HTMLTag head = this.getHead();
			head.addSubTag(meta);
		} catch (NoSuchTagException e) {
			e.printStackTrace();
		}
	}
	
	public List<HTMLTag> getMetas(){
		try{
			HTMLTag head = this.getHead();
			List<HTMLTag> metas = new ArrayList<HTMLTag>();
			for (Iterator<HTMLTag> i = head.getSubTags().iterator();i.hasNext();){
				HTMLTag temptag = i.next();
				if (temptag.getTagName().equalsIgnoreCase("meta")) metas.add(temptag);
			}
			return metas;
		} catch (NoSuchTagException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String getCharset() 
			throws CharsetNotFoundException{
		
		List<HTMLTag> metas = this.getMetas();
		for (Iterator<HTMLTag> i = metas.iterator();i.hasNext();){
			HTMLTag temptag = i.next();
			HashMap<String, String> attrs = temptag.getAttrlist();
			Set<String> attrnames = attrs.keySet();
			for (Iterator<String> j = attrnames.iterator();i.hasNext();){
				String attrname = j.next();
				if(attrname.equalsIgnoreCase("charset")) return attrs.get(attrname);
			}
		}
		throw new CharsetNotFoundException();
	}
	
	public void setCharset(String charset){
		try{
			List<HTMLTag> subtags = this.getHead().getSubTags();
			for (Iterator<HTMLTag> i = subtags.iterator();i.hasNext();){
				HTMLTag temptag = i.next();
				if (temptag.getTagName().equalsIgnoreCase("meta")){
					HashMap<String, String> attrs = temptag.getAttrlist();
					Set<String> attrnames = attrs.keySet();
					for (Iterator<String> j = attrnames.iterator();j.hasNext();){
						String attrname = j.next();
						if(attrname.equalsIgnoreCase("charset")) temptag.addOrSetAttr("charset", charset);
					}
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public HTMLTag getHead() throws NoSuchTagException{
		for (Iterator<HTMLTag> i = this.rootStructure.iterator(); i.hasNext();){
			HTMLTag temptag = i.next();
			if(temptag.getTagName().equalsIgnoreCase("head")) return temptag;
		}
		throw new NoSuchTagException();
	}
	
	public void setHead(HTMLTag head){
		int tagamount = this.rootStructure.size();
		for (int i = 0; i < tagamount; i++){
			if(this.rootStructure.get(i).getTagName().equalsIgnoreCase("head")) {
				this.rootStructure.set(i, head);
				return;
			}
		}
		this.rootStructure.add(0, head);
	}
	
	public String getTitle() 
			throws NoTitleFoundException{
		try {
			HTMLTag head = this.getHead();
			for (Iterator<HTMLTag> i = head.getSubTags().iterator(); i.hasNext();){
				HTMLTag temptag = i.next();
				if (temptag.getTagName().equalsIgnoreCase("title")) return temptag.getInnerHTML();
			}
			throw new NoTitleFoundException();
		} catch (NoSuchTagException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void setTitle(String title){
		try {
			HTMLTag head = this.getHead();
			for (Iterator<HTMLTag> i = head.getSubTags().iterator(); i.hasNext();){
				HTMLTag temptag = i.next();
				if (temptag.getTagName().equalsIgnoreCase("title")) {
					temptag.setInnerHTML(title);
					return;
				}
			}
			head.addSubTag(new HTMLTag("title",title));
		} catch (NoSuchTagException e) {
			e.printStackTrace();
		}
	}
	
	public void addImportedCss(String csspath){
		try {
			HTMLTag head = this.getHead();
			HTMLTag csslink = new HTMLTag("link");
			csslink.addOrSetAttr("href", csspath);
			csslink.addOrSetAttr("type", "text/css");
			csslink.addOrSetAttr("rel", "stylesheet");
			head.addSubTag(csslink);
		} catch (NoSuchTagException e) {
			e.printStackTrace();
		}
	}
	
	public void addImportedScript(String scriptpath, String scripttype){
		try {
			HTMLTag head = this.getHead();
			HTMLScript script = new HTMLScript();
			script.addOrSetAttr("src", scriptpath);
			script.addOrSetAttr("type", scripttype);
			head.addSubTag(script);
		} catch (NoSuchTagException e) {
			e.printStackTrace();
		}
	}
	
	public void addImportedScript(String scriptpath, String scripttype, String charset){
		try {
			HTMLTag head = this.getHead();
			HTMLScript script = new HTMLScript();
			script.addOrSetAttr("src", scriptpath);
			script.addOrSetAttr("type", scripttype);
			script.addOrSetAttr("charset", charset);
			head.addSubTag(script);
		} catch (NoSuchTagException e) {
			e.printStackTrace();
		}
	}
	
	public HTMLTag getRootTag() {
		return rootTag;
	}

	public void setRootTag(HTMLTag rootTag) {
		this.rootTag = rootTag;
	}

	public List<HTMLTag> getRootStructure() {
		return rootStructure;
	}

	public void setRootStructure(List<HTMLTag> rootStructure) {
		this.rootStructure = rootStructure;
	}

	public List<HTMLTag> getBodyContent() {
		return this.bodyContent;
	}

	public void setBodyContent(List<HTMLTag> bodyContent) {
		this.bodyContent = bodyContent;
	}

}
