package ana.view.htmlwriter.htmltags.extended;

import ana.view.htmlwriter.exceptions.UnsetAttributeException;
import ana.view.htmlwriter.htmltags.HTMLTag;

public class HTMLScript extends HTMLTag {
	
	private String scripts = "";
	
	public HTMLScript(){
		super();
		this.setTagName("script");
		this.setInnerHTML("<!--script-->");
	}
	
	@Override
	public String getHTMLBlock() throws UnsetAttributeException{
		StringBuffer backstring = new StringBuffer(this.getStartTag());
		if(!this.isScriptsEmpty()){
			backstring.append(this.getScripts());
		}
		backstring.append(this.getEndTag());
		return backstring.toString();
	}

	private boolean isScriptsEmpty() {
		if (this.getScripts() == null || this.getScripts().equals("")) return true;
		return false;
	}

	public String getScripts() {
		return scripts;
	}

	public void setScripts(String scripts) {
		this.scripts = scripts;
	}

}
