public class Station{
	protected String line;
	protected String name;
	protected boolean inService;
	public Station prev;
	public Station next;
	public Station(String line, String name){
		this.line=line;
		this.name=name;
		inService=true;
	}
	public Station(){
		this.name="";
		this.line="";
		inService=true;
	}
	public boolean equals(Station st){
		return(this.name==st.name&&this.line==st.line);
	}
	public String toString(){
		String out="STATION "+name+": "+line+" line, in service: "+String.valueOf(inService)+", previous station: ";
		if(prev==null)out+="none";
		else out+=prev.name;
		out+=", next station: ";
		if(next==null)out+="none";
		else out+=next.name;
		return(out);
	}
	public void addNext(Station nextSt){
		this.next=nextSt;
		nextSt.prev=this;
	}
	public void addPrev(Station prevSt){
		this.prev=prevSt;
		prevSt.next=this;
	}
	public int tripLength(Station endPoint){
		return(-1);
	}
	public void connect(Station next){
		this.addNext(next);
	}
	public boolean isAvailable(){
		return(inService);
	}
	public void switchAvailable(){
		inService=!inService;
	}
}
