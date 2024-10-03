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
		String out="TRANSFER"+super.toString()+"\n\tTransfers: \n";
		for(int i=0;i<otherStations.size();i++){
			out+="\t"+otherStations.get(i).toString()+"\n";
		}
		return(out);
	}
}
