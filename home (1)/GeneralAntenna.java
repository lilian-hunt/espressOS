public class GeneralAntenna extends Antenna {
	protected boolean isConnected;
	protected int signalStrength;
	
	public GeneralAntenna(){
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
	// allows to be set in any range
    public void setSignalStrength(int n){
		this.signalStrength = n;
		if (n<1){
			this.isConnected = false;

		}
		else {
			this.isConnected = true;
		}	
	}
}
