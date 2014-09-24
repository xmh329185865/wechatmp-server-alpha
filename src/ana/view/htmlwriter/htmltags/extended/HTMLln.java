package ana.view.htmlwriter.htmltags.extended;

import ana.view.htmlwriter.htmltags.HTMLTag;

public class HTMLln extends HTMLTag{

	public HTMLln(){
		super();
	}
	
	@Override
	public String getHTMLBlock(){
		return "<br>";
	}
	
	@Override
	public String getEmptyTag(){
		return "<br>";
	}
}
