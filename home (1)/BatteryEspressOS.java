public class BatteryEspressOS extends Battery {
	protected int level;
	
	//default battery set at 25
	public BatteryEspressOS(){
		this.level = 25;
	}
    
	public void setLevel(int value){
		this.level = value;

		if (this.level < 0){
			this.level = 0;
		}
		if (this.level > 100){
			this.level = 100;
		}
	}
    public int getLevel(){
		return this.level;
	}

}
