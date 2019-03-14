public class GeneralBattery extends Battery {
	protected int level;
	
	//default battery set at 25
	public GeneralBattery(){
		this.level = 25;
	}
    
	public void setLevel(int value){
		this.level = value;

	}
    public int getLevel(){
		return this.level;
	}

}
