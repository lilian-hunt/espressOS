public class NotifyBackgroundApp extends App implements BackgroundAppInterface, NotifyInterface {	
	private BackgroundApp backgroundApp;
	private NotifyApp notifyApp;
	public NotifyBackgroundApp(String name){
		super(name);
		notifyApp = new NotifyApp(name);
		backgroundApp = new BackgroundApp(name);
		this.isBackgroundApp = true;
		this.isNotifyApp = true;
	}
	
	public int exit(){
		this.isRunning = false;
		this.backgroundApp.exit();
		return 99;	
	}
	public void notifyOS(){
		if (this.isInstalled && this.isRunning && this.OSSystem.isPhoneOn()){
			String notification = ("You have a notifcaion from: " + this.getName());
			OSSystem.addNotification(notification);
		}
		else 
			throw new NotifyOSFailed();
	}
	
	public Object getData(Object o){
		return o;
	}
	
	public void backgroundStart(){
		this.isRunning = true;
	}
	public void start(){
		this.isRunning = true;
		this.backgroundApp.start();
	}
	public void setInnerClassesStart(){
		this.backgroundApp.start();
		this.notifyApp.start();
	}
	public void setInnerClassesExit(){
		this.backgroundApp.exit();
		this.notifyApp.exit();
	}
	public void setInnerClassesInstalled(boolean isInstalled){
		this.backgroundApp.setIsInstalled(isInstalled);
		this.notifyApp.setIsInstalled(isInstalled);
	}
	
}
