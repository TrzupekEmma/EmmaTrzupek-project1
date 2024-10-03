import java.util.ArrayList;
class TransferStation extends Station{
	ArrayList<Station> otherStations = new ArrayList<Station>();
	public TransferStation(String line, String name){
		super(line,name);
	}
	public void addTransferStationPrev(Station prevSt){
		prevSt.next=this;
		otherStations.add(prevSt);
	}
	public void addTransferStationNext(Station nextSt){
		nextSt.prev=this;
		otherStations.add(nextSt);
	}
	public String toString(){
		return("TRANSFER"+super.toString()+"Transfers: ");
	}
}
