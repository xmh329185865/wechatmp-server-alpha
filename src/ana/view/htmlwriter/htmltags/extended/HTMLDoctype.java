package ana.view.htmlwriter.htmltags.extended;

import ana.view.htmlwriter.htmltags.HTMLTag;

public class HTMLDoctype extends HTMLTag{
	
	private String doctypecontent;

	@Override
	public String getHTMLBlock(){
		StringBuffer rstring = new StringBuffer("<!DOCTYPE ");
		rstring.append(this.doctypecontent);
		rstring.append(">");
		return rstring.toString();
	}
	
	public String getDoctypecontent() {
		return doctypecontent;
	}

	public void setDoctypecontent(String doctypecontent) {
		this.doctypecontent = doctypecontent;
	}

}
