class EndStation extends Station{
	public EndStation(String line,String name){
		super(line, name);
	}
	public void makeEnd(){
		if(prev==null){
			prev=next;
			return;
		}
		if(next==null){
			next=prev;
		}
	}
	public String toString(){
		return("END"+super.toString());
	}
}

