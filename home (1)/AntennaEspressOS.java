public class AntennaEspressOS extends Antenna {
	protected boolean isConnected;
	protected int signalStrength;
	
	public AntennaEspressOS(){
		this.isConnected = false;
		this.signalStrength = 0;
	}
	
	public boolean isConnected(){
		return isConnected;
	}
	// if the signal strength was previously 0 and setting network to on
	// signal strength will be changed to 1
    public void setNetwork(boolean isConnected){
		this.isConnected = isConnected;
		if (isConnected == false){
			this.signalStrength = 0;
		}
		if (isConnected == true && this.signalStrength == 0){
			this.signalStrength = 1;
		}
	}

    public int getSignalStrength(){
	
		return signalStrength;
	}
	// will not update if outside of range [0,5]
    public void setSignalStrength(int n){

		if (n <= 5 && n >= 0){
			this.signalStrength = n;
		}
		if (n == 0){
			this.isConnected = false;
		}
		else if (n > 0 && n <= 5) {
			this.isConnected = true;
		}
		
	}

}
