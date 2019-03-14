import java.util.List;
public class App {
	protected String name;
	protected boolean isRunning;
	protected boolean isInstalled;
	protected boolean isBackgroundApp;
	protected boolean isNotifyApp;
	protected EspressOSMobile OSSystem;

	public App(String name){
		this.name = name;	
		this.isBackgroundApp = false;
		this.isNotifyApp = false;
	}
	public void setOSSystem(EspressOSMobile OSSystem){
		this.OSSystem = OSSystem;
	}
	public void start(){
		if (this.isInstalled){
			this.isRunning = true;
			if (this.isBackgroundApp && this.isNotifyApp){
					NotifyBackgroundApp t = (NotifyBackgroundApp) this;
					t.setInnerClassesStart();
					
			}
		
		}
	}
	public int exit(){
		this.isRunning = false;
		if (this.isBackgroundApp && this.isNotifyApp){
			NotifyBackgroundApp t = (NotifyBackgroundApp) this;
			t.setInnerClassesExit();
		}
		return 99;
	}
	public void setIsInstalled(boolean isInstalled){
		this.isInstalled = isInstalled;
		if (this.isBackgroundApp && this.isNotifyApp){
			NotifyBackgroundApp t = (NotifyBackgroundApp) this;
			t.setInnerClassesInstalled(isInstalled);
		}

	}
	public String getName(){
		return name;
	}
	public boolean isBackgroundApp(){
		return isBackgroundApp;
	}
	public boolean isNotifyApp(){
		return isNotifyApp;
	}
	public boolean isInstalled(){
		List<App> installedApps = this.OSSystem.getInstalledApps();
		if (installedApps.contains(this)||this.isInstalled){
			return true;
		}
		return false;
	}
	public boolean isRunning(){
		return isRunning;
	}
	public void test(){
		System.out.println("SUPER");
	}
}
