
import java.util.ArrayList;
public class NotifyApp extends App implements NotifyInterface {
	protected ArrayList<String> notifications;
	
	public NotifyApp(String name){
		super(name);
		this.isNotifyApp = true;
		this.notifications = new ArrayList<String>();
	}
	//only works if the app is installed, the phone is on and the app is running
	public void notifyOS(){
		if (this.isInstalled && this.isRunning && this.OSSystem.isPhoneOn()){
			String notification = ("You have a notifcaion from: " + this.getName());
			OSSystem.addNotification(notification);
		}
		else 
			throw new NotifyOSFailed();
	}
	public void test(){
		System.out.println("SUB");
	}
}
