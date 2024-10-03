import java.util.LinkedList;
import java.io.*;
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
	private int tripLength(Station searchPoint, LinkedList<TransferStation> seenTransfers,boolean fw){
		System.out.println("checking "+searchPoint.name);
		if(searchPoint.equals(this))
			return(0);
		if(searchPoint instanceof EndStation){
			if(fw){
				if((searchPoint.equals(searchPoint.next.next)))
					if(!(searchPoint.next.equals(searchPoint)))
						return(-1);
			}
			else{
				if((searchPoint.equals(searchPoint.prev.prev)))
					if(!(searchPoint.prev.equals(searchPoint)))
						return(-1);
			}
		}
		if(searchPoint instanceof TransferStation){
			for(int i=0;i<seenTransfers.size();i++)
				if(seenTransfers.get(i)==searchPoint)
					return(-1);

			seenTransfers.add((TransferStation)searchPoint);
			int possibleReturn;
			if(fw){
				if(searchPoint.next==null)
					possibleReturn=-1;
				else possibleReturn=tripLength(searchPoint.next,seenTransfers,fw);
			}else{
				if(searchPoint.prev==null)
					possibleReturn=-1;
				else possibleReturn=(tripLength(searchPoint.prev,seenTransfers,fw));
			}
			System.out.println("Other station size: "+((TransferStation)searchPoint).otherStations.size()+"prev Possible Return"+possibleReturn);
			for(int i=0;possibleReturn<0&&i<((TransferStation)searchPoint).otherStations.size();i++){
				Station nextSt=((TransferStation)searchPoint).otherStations.get(i);
				LinkedList<TransferStation> attemptSeen=new LinkedList<TransferStation>();	
				for(int j=0;j<seenTransfers.size();j++){
					attemptSeen.add(seenTransfers.get(j));
				}
				possibleReturn=tripLength(nextSt,attemptSeen,nextSt.prev==searchPoint);
				System.out.println(nextSt.name+" returns "+possibleReturn);
			}
			if(possibleReturn==-1)
				return(-1);
			return(possibleReturn+1);
		}
		int possibleReturn=0;
		if(fw)
			possibleReturn=tripLength(searchPoint.next,seenTransfers,fw);
		else
			possibleReturn=tripLength(searchPoint.prev,seenTransfers,fw);
		if(possibleReturn==-1)
			return(-1);
		return(possibleReturn+1);
		
	}
	public int tripLength(Station endPoint){
		System.out.println("calculating distance between "+endPoint.name+" and "+name);
		int possibleReturn=tripLength(endPoint,new LinkedList<TransferStation>(),true);
		if(possibleReturn>-1){
			System.out.println("returning "+possibleReturn+"\n");	
			return(possibleReturn);
		}
		possibleReturn=tripLength(endPoint,new LinkedList<TransferStation>(),false);
		System.out.println("returning "+possibleReturn+"\n");
		return(possibleReturn);
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
