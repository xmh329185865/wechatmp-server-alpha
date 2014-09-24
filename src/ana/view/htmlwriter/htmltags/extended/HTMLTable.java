package ana.view.htmlwriter.htmltags.extended;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ana.view.htmlwriter.exceptions.ColumnNotMatchedException;
import ana.view.htmlwriter.exceptions.InputValueNotMatchedException;
import ana.view.htmlwriter.exceptions.InvalidRowException;
import ana.view.htmlwriter.exceptions.NoColsRowException;
import ana.view.htmlwriter.exceptions.NoRowDetectedException;
import ana.view.htmlwriter.exceptions.TagnumOutOfBoundsException;
import ana.view.htmlwriter.htmltags.HTMLTag;

public class HTMLTable extends HTMLTag{
	
	private HTMLTag tbody = new HTMLTag("tbody");
	private List<HTMLTag> trs = this.tbody.getSubTags();
	private int colsamt;
	
	public HTMLTable(String[] tableHead){
		super();
		this.setTagName("table");
		this.addSubTag(this.tbody);
		this.colsamt = tableHead.length;
		try {
			this.setColNames(tableHead);
		} catch (ColumnNotMatchedException e) {
			e.printStackTrace();
		}
	}
	
	public HTMLTable(int colsamt){
		super();
		this.setTagName("table");
		this.addSubTag(this.tbody);
		this.colsamt = colsamt;
	}
	
	public HTMLTag getRow(int rownum) 
			throws TagnumOutOfBoundsException{
		try {
			return this.trs.get(rownum);
		} catch (IndexOutOfBoundsException e){
			throw new TagnumOutOfBoundsException();
		}
	}
	
	public void insertRowFH(){
		this.trs.add(0, new HTMLTag("tr"));
	}
	
	public void insertRow(int rownum){
		this.trs.add(rownum, new HTMLTag("tr"));
	}
	
	public void addRow(){
		this.tbody.addSubTag(new HTMLTag("tr"));
	}
	
	public void addRow(String[] colcontent, String rowclass) 
			throws ColumnNotMatchedException {
		if (colcontent.length != this.colsamt) throw new ColumnNotMatchedException();
		List<HTMLTag> newrow = new ArrayList<HTMLTag>();
		for (int i = 0; i < colcontent.length; i++){
			newrow.add(new HTMLTag("td",colcontent[i]));
		}
		HTMLTag rowhead = new HTMLTag("tr",newrow);
		if(rowclass != null) rowhead.setTagClass(rowclass);
		this.tbody.addSubTag(rowhead);
	}
	
	public void addRow(String[] colcontent, int[] colspan, String rowclass) 
			throws ColumnNotMatchedException,InputValueNotMatchedException {
		if (colcontent.length != colspan.length) throw new InputValueNotMatchedException();
		int colamt = 0;
		for (int i = 0; i < colspan.length; i++){
			colamt += colspan[i];
		}
		if (colamt != this.colsamt) throw new ColumnNotMatchedException();
		List<HTMLTag> newrow = new ArrayList<HTMLTag>();
		for (int i = 0; i < colcontent.length; i++){
			HTMLTag thistd = new HTMLTag("td",colcontent[i]);
			if(colspan[i] > 1) thistd.addOrSetAttr("colspan", colspan[i]+"");
			newrow.add(thistd);
		}
		HTMLTag rowhead = new HTMLTag("tr",newrow);
		if(rowclass != null) rowhead.setTagClass(rowclass);
		this.tbody.addSubTag(rowhead);
	}
	
	public void addRow(List<HTMLTag> tds, String rowclass) 
			throws ColumnNotMatchedException,InputValueNotMatchedException {
		int colamt = 0;
		for (Iterator<HTMLTag> i = tds.iterator(); i.hasNext();){
			HTMLTag currenttd = i.next();
			if (!currenttd.getTagName().equalsIgnoreCase("td")) throw new InputValueNotMatchedException();
			if (currenttd.getAttr("colspan") != null){
				colamt += Integer.parseInt(currenttd.getAttr("colspan"));
			} else {
				colamt++;
			}
		}
		if (colamt != this.colsamt) throw new ColumnNotMatchedException();

		HTMLTag rowhead = new HTMLTag("tr",tds);
		if(rowclass != null) rowhead.setTagClass(rowclass);
		this.tbody.addSubTag(rowhead);
	}
	
	public void addRow(HTMLTag tr, int rownum) 
			throws InvalidRowException,ColumnNotMatchedException,TagnumOutOfBoundsException{
		if (!tr.getTagName().equalsIgnoreCase("tr")) throw new InvalidRowException();
		int colamt = 0;
		for (Iterator<HTMLTag> i = tr.getSubTags().iterator(); i.hasNext();){
			HTMLTag currenttd = i.next();
			if (!currenttd.getTagName().equalsIgnoreCase("td")) throw new ColumnNotMatchedException();
			if (currenttd.getAttr("colspan") != null){
				colamt += Integer.parseInt(currenttd.getAttr("colspan"));
			} else {
				colamt++;
			}
		}
		if (colamt != this.colsamt) throw new ColumnNotMatchedException();
		if (tr.getSubTags().size()<=rownum) throw new TagnumOutOfBoundsException();
		this.trs.add(rownum, tr);
	}
	
	public void deleteRow(int rownum) 
			throws NoRowDetectedException {
		int sizecursor = this.trs.size()-1;
		while(sizecursor >= 0){
			if(this.trs.get(sizecursor).getTagName().equalsIgnoreCase("tr")) {
				try {
					this.tbody.deleteSubTag(sizecursor);
				} catch (TagnumOutOfBoundsException e) {
					e.printStackTrace();
				}
			}
			sizecursor--;
		}
		throw new NoRowDetectedException();
	}
	
	public void setColNames(String[] colnames) 
			throws ColumnNotMatchedException{
		if (colnames.length != this.colsamt) throw new ColumnNotMatchedException();
		List<HTMLTag> newcols = new ArrayList<HTMLTag>();		
		for (int i = 0; i < colnames.length; i++){
			newcols.add(new HTMLTag("th",colnames[i]));
		}
		try{
			if (this.getRow(0).getSubTags().get(0).getTagName().equalsIgnoreCase("th")){
				this.getRow(0).setSubTags(newcols);;
			} else {
				this.insertRowFH();
				this.trs.get(0).setSubTags(newcols);
			}
		} catch (TagnumOutOfBoundsException e){
			this.insertRowFH();
			this.trs.get(0).setSubTags(newcols);
		}
	}

	public String[] getColNames() throws NoColsRowException {
		if (this.trs.get(0).getSubTags().get(0).getTagName().equalsIgnoreCase("th")){
			List<String> colnames = new ArrayList<String>();
			try{
				for (Iterator<HTMLTag> i = this.getRow(0).getSubTags().iterator(); i.hasNext();){
					HTMLTag nowth = i.next();
					colnames.add(nowth.getInnerHTML());
				}
			} catch (TagnumOutOfBoundsException e){
				throw new NoColsRowException();
			}
			return colnames.toArray(new String[0]);
		} else {
			throw new NoColsRowException();
		}
	}
	
	public void setID(String ID){
		this.addOrSetAttr("id", ID);
	}
	
	public String getID(){
		return this.getAttr("id");
	}
	
	public void setAlign(String align){
		this.addOrSetAttr("align", align);
	}
	
	public String getAlign(){
		return this.getAttr("align");
	}

	public List<HTMLTag> getTrs() {
		return trs;
	}

	public void setTrs(List<HTMLTag> trs) {
		this.trs = trs;
	}
	
}
