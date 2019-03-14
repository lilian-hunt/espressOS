/**
 * EspressOS Mobile Phone Class.
 *
 *
 * EspressOSMobile
 * In this assignment you will be creating an EspressOS Mobile Phone as part of a simulation.
 * The Mobile phone includes several attributes unique to the phone and has simple functionality.
 * You are to complete 2 classes. EspressOSMobile and EspressOSContact
 *
 * The phone has data
 *  Information about the phone state. 
 *    If it is On/Off
 *    Battery level 
 *    If it is connected to network. 
 *    Signal strength when connected to network
 *  Information about the current owner saved as contact information. 
 *    First name
 *    Last name
 *    Phone number
 *  A list of 10 possible contacts.
 *    Each contact stores first name, last name, phone number and chat history up to 20 messages
 *  
 * The phone has functionality
 *  Turning on the phone
 *  Charging the phone. Increase battery level
 *  Change battery (set battery level)
 *  Use phone for k units of battery (decreases battery level by k)
 *  Search/add/remove contacts
 *
 * Attribute features
 *  if the phone is off. It is not connected. 
 *  if the phone is not connected there is no signal strength
 *  the attribute for battery life has valid range [0,100]. 0 is flat, 100 is full.
 *  the attribute for signal strength has a valid range [0, 5]. 0 is no signal, 5 is best signal.
 * 
 * Please implement the methods provided, as some of the marking is
 * making sure that these methods work as specified.
 *
 *
 */
import java.util.ArrayList;
import java.util.List;
public class EspressOSMobile {
	public static final int MAXIMUM_CONTACTS = 10;
	
	/* Use this to store contacts. Do not modify. */
	protected EspressOSContact[] contacts;
	protected boolean isOn;
	protected EspressOSContact ownerContact;
	protected List<App> runningBackgroundApps;
	protected App runningForegroundApp;
	protected List<App> installedApps;
	protected List<App> backgroundApps;	
	protected List<App> notificationApps;
	protected ArrayList<String> notificationsArrayList;
	protected Antenna antenna;
	protected Battery battery;

	/* Every phone manufactured has the following attributes
	 * 
	 * the phone is off
	 * the phone has battery life 25
	 * the phone is not connected
	 * the phone has signal strength 0
	 * Each of the contacts stored in the array contacts has a null value
	 * 
	 * the owner first name "EspressOS"
	 * the owner last name is "Incorporated"
	 * the owner phone number is "180076237867"
	 * the owner chat message should have only one message 
	 *         "Thank you for choosing EspressOS products"
	 *
	 */
	public EspressOSMobile() {
		/* given */
		contacts = new EspressOSContact[10];
		this.antenna = new AntennaEspressOS();
		this.battery = new BatteryEspressOS();
		this.isOn = false;
		this.ownerContact = new EspressOSContact("EspressOS", "Incorporated","180076237867");
		this.ownerContact.addChatMessage("EspressOS", "Thank you for choosing EspressOS products");
		this.ownerContact.setIsOwner(true);	
		installedApps = new ArrayList<App>();
		backgroundApps = new ArrayList<App>();
		notificationApps = new ArrayList<App>();
		notificationsArrayList = new ArrayList<String>();
		runningBackgroundApps = new ArrayList<App>();
	
	}

	/* returns a copy of the owner contact details
	 * return null if the phone is off
	 */
	public EspressOSContact getCopyOfOwnerContact() {
		if (this.isOn == false){
			return null;
		}
		else {
			return this.ownerContact.copy();
		}
	}


	/* only works if phone is on
	 * will add the contact in the array only if there is space and does not exist
	 * The method will find an element that is null and set it to be the contact
	 */
	public boolean addContact(EspressOSContact contact) {
		if (this.isOn){
			boolean containsContact = false;
			for (EspressOSContact c: contacts) {
				if (c!= null){
					if (c.equals(contact))
					containsContact = true;
				}
        		
			}
			if (!containsContact){
				for (int i = 0; i < contacts.length; i++) {
        			if (contacts[i] == null){
						contacts[i] = contact;
						return true;
					}
				}
			}
		}
		return false;

	}
	/* only works if phone is on
	 * find the object and set the array element to null
 	 * return true on successful remove
	 */
	public boolean removeContact(EspressOSContact contact) {
		if (this.isOn){
			for (int i = 0; i<contacts.length; i++) {
				if (contact != null){
					if (contact.equals(contacts[i])){
						contacts[i] = null;
						return true;
					}
				}
				
			}
		}
		return false;			
	}

	/* only works if phone is on
	 * return the number of contacts, or -1 if phone is off
	 */
	public int getNumberOfContacts() {
		int count = 0;
		if (this.isOn){
			for (int i = 0; i<contacts.length; i++) {
        		if (contacts[i] != null) {
					count ++;
				}
			}
			return count;
		}
		return -1;
	}

	/* only works if phone is on
	 * returns all contacts that match firstname OR lastname
	 * if phone is off, or no results, null is returned
	 */
	public EspressOSContact[] searchContact(String name) {
		int count = 0;
		ArrayList<EspressOSContact> found = new ArrayList<EspressOSContact>();
		if (this.isOn && contacts!=null){
			for (EspressOSContact c: contacts){
				if (c != null){
					if (c.getFirstName().equalsIgnoreCase(name) || c.getLastName().equalsIgnoreCase(name)){
						found.add(c);
					}
				}
			}
			EspressOSContact[] array = found.toArray(new EspressOSContact[found.size()]);
			if (array.length != 0){
				return array;
			}
		}
		return null;
	}

	/* returns true if phone is on
	 */
	public boolean isPhoneOn() {
		return this.isOn;
	}

	/* when phone turns on, it costs 5 battery for startup. network is initially disconnected
	 * when phone turns off it costs 0 battery, network is disconnected
	 * turning the phone off stops the apps running
	 * always return true if turning off
	 * return false if do not have enough battery level to turn on
	 * return true otherwise
	 */
	// changing "on" to "turnOn"
	 public boolean setPhoneOn(boolean turnOn) {
		if (turnOn && battery != null){
			if (battery.getLevel() < 6){
				return false;
			}
			else {
				this.isOn = true;
				this.usePhone(5);
				return true;
			}
		}
		else {
			antenna.setSignalStrength(0);
			if (runningForegroundApp != null){
				runningForegroundApp.exit();
				runningForegroundApp = null;
			}
			
			for (App app : runningBackgroundApps){
				app.exit();
			}
			runningBackgroundApps = new ArrayList<App>();
			this.isOn = false;
			return true;
		}
	}
	
	/* Return the battery life level. if the phone is off, zero is returned.
	 * If battery is null return 0.
	 */
	public int getBatteryLife() {
		if (this.isOn && battery != null){
			return battery.getLevel();
		}
		return 0;
		
	}
	
	/* Change battery of phone.
	 * On success. The phone is off and new battery level adjusted and returns true
	 * If newBatteryLevel is outside manufacturer specification of [0,100], then 
	 * no changes occur and returns false.
	 * If the newBattery is null, do not update and return false.
	 */
	public boolean changeBattery(Battery newBattery) {
		if (newBattery == null){
			return false;
		}
		if (newBattery.getLevel() <= 100 && newBattery.getLevel() >=0){
			this.battery = newBattery;
			if (this.battery.getLevel() == 0){
				this.disconnectNetwork();
			}
			this.isOn = false;
			return true;
		}
		return false;
	}
	
	/* only works if phone is on. 
	 * returns true if the phone is connected to the network
	 */
	public boolean isConnectedNetwork() {
		if (!this.isOn){
			return false;
		}
		if (antenna == null){
			return false;
		}
		return antenna.isConnected();
	}
	
	/* only works if phone is on. 
	 * when disconnecting, the signal strength becomes zero
	 */
	public void disconnectNetwork() {
		if (this.isOn){
			if (this.antenna != null){
				antenna.setSignalStrength(0);
			}
		}
	}
	
	/* only works if phone is on. 
	 * Connect to network
	 * if already connected do nothing
	 * if connecting: 
	 *  1) signal strength is set to 1 if it was 0
	 *  2) signal strength will be the previous value if it is not zero
	 *  3) it will cost 2 battery life to do so
	 * returns the network connected status
	 */
	public boolean connectNetwork() {
		if (this.antenna == null){
			return false;
		}
		if (this.isOn){
			if (!antenna.isConnected()){
				this.usePhone(2);
			
				if (this.isOn){
					antenna.setNetwork(true);
					if (antenna.getSignalStrength() == 0){
						antenna.setSignalStrength(1);	
					}
				}	
			}	
		}
		return antenna.isConnected();
	}
	
	/* only works if phone is on. 
	 * returns a value in range [1,5] if connected to network
	 * otherwise returns 0
	 * null check for antenna
	 */
	public int getSignalStrength() {	
		if (this.isOn) {
			if (antenna!=null){
				return antenna.getSignalStrength();
			}
		}
		return 0;
	}

	/* only works if phone is on. 
	 * sets the signal strength and may change the network connection status to on or off
	 * signal of 0 disconnects network
	 * signal [1,5] can connect to network if not already connected
	 * if the signal is set outside the range [0,5], nothing will occur and will return false
	 */
	public boolean setSignalStrength(int x) {
		if (this.antenna == null){
			return false;
		}
		if (this.isOn){
			if (x == 0){
				antenna.setSignalStrength(0);
				return true;
			}
			else if (x >= 1 && x <= 5){
				antenna.setSignalStrength(x);
				return true;
			}
		}
		return false;
    }
	
	/* changes the antenna object
	 * signal strength is set to default and is not connected to a network
	 * if this constraint is violated then the antenna should not be changed.
	 * return true if antenna is changed.
	 */
	public boolean changeAntenna(Antenna antenna) {
		if (antenna == null){
			this.antenna = null;
			return true;
		}
		if (antenna.getSignalStrength() == 0 && !antenna.isConnected()){
			this.antenna = null;
			return true;
		}
		if (antenna.getSignalStrength() >= 1 && antenna.getSignalStrength()<=5){
			this.antenna = antenna;
			this.antenna.setNetwork(true);
			return true;
		}	
		return false;
	}
	
	/* each charge increases battery by 10
	 * the phone has overcharge protection and cannot exceed 100
	 * returns true if the phone was charged by 10
	 */
	public boolean chargePhone() {
		if (battery == null){
			return false;
		}
		if (battery.getLevel() >= 100){
			return false;
		}
		else if (battery.getLevel() <100){
			battery.setLevel(battery.getLevel()+10);
			return true;
		}
		else {
			return false;
		}
	}
	
	/* Use the phone which costs k units of battery life.
	 * if the activity exceeds the battery life, the battery automatically 
	 * becomes zero and the phone turns off.
	 */
	public void usePhone(int k) {	
		
		if (this.isOn && k>=0 && battery!=null){
			battery.setLevel(battery.getLevel() -k);
			if (battery.getLevel() <=0){
				// this.disconnectNetwork();
				isOn = false;
			}
		}
		if (k<0){
			throw new RuntimeException("Invalid k units of battery life.");
		}
	}
	// Installs an app on the operating system. 
	// 	If the object passed is null then an app 
	// 	will not be installed. If the app has been 
	// 	installed already, then the app will not be installed. 
	// 	The method returns the value true if the app has 
	// 	been successfully installed, otherwise false.
	// 
	public boolean install(App app){
		if (!this.isOn){
			return false;
		}
		if (app != null){
			for (App a : installedApps){
				if (app.getName().equals(a.getName())){
					return false;
				}
			}
			if (!installedApps.contains(app)){
				installedApps.add(app);
				app.setOSSystem(this);
				app.setIsInstalled(true);
				if (app.isNotifyApp()){
				notificationApps.add(app);
				}
				if (app.isBackgroundApp()){
					backgroundApps.add(app);
				}
				return true;
			}
		}
		return false;
	}
	
	// Given a name of an app, it will find the app and
	// 	remove it from the operating system. If 
	// 	the app exists and has been uninstalled 
	// 	it will return true, otherwise the method 
	// 	returns false.
	public boolean uninstall(String appName){
		if (!this.isOn){
			return false;
		}
		for (App a : installedApps){
			if (a!= null){
				if (a.getName().equals(appName)){
					installedApps.remove(a);
					a.setIsInstalled(false);
					if (a.isNotifyApp()){
						notificationApps.remove(a);
					}
					if (a.isBackgroundApp()){
						backgroundApps.remove(a);
						runningBackgroundApps.remove(a);
					}
					else {
						runningForegroundApp = null;
					}
					return true;
				}
			}
		}
		return false;
	}
	// Returns the one running foreground app + the running background apps
	// 	applications on the operating system.\
	// if the phone is off, there will be no running apps
	public List<App> getRunningApps(){
		if (!this.isOn){
			return null;
		}
		List<App> runningApps = new ArrayList<App>();

		for (App a : runningBackgroundApps){
			if (a!=null){
				runningApps.add(a);
			}
		}
		if (runningForegroundApp!=null){
			runningApps.add(runningForegroundApp);
		}
		return runningApps;
	}
	///Returns a List of all installed 
	//applications on the operating system.
	
	public List<App> getInstalledApps(){
		if (!this.isOn){
			return null;
		}
		return installedApps;
	}
	// Returns a List of all Background 
	// 	applications on the operating system.
	public List<App> getBackgroundApps(){
		if (!this.isOn){
			return null;
		}
		return backgroundApps;	
	}
	//Returns a List of all Notify applications on 
	//the operating system.
	public List<App> getNotificationApp(){
		if (!this.isOn){
			return null;
		}
		return notificationApps;
	}
	
	// Helper method, called by app in notifyOS()
	public void addNotification(String notification){
		if (this.isOn){
			notificationsArrayList.add(notification);
		}
	}
	/* Returns a List of all notifications that 
	*  have been created and sent to the operating 
	*  systems by Notify apps.
	*/
	public List<String> getNotifications(){
		if (!this.isOn){
			return null;
		}
		return notificationsArrayList;
	}
	//Given an application name, it will find the 
	// application and invoke the .start() method. 
	// 	If the application exists, the method will 
	// 	invoke the .start() method and return true. 
	// 	Otherwise the the method returns false.
	public boolean run(String appName){
		if (!this.isOn){
			return false;
		}
		for (App a : installedApps){
			if (a.getName().equals(appName)){
				a.start();
				if (a.isBackgroundApp()){
					runningBackgroundApps.add(a);
				}
				else {
					if (runningForegroundApp!= null){
						runningForegroundApp.exit();
					}
					runningForegroundApp = a;
				}
				return true;
			}
		}
		return false;
	}
	//Given an application name, it will find 
	// the application that is currently running 
	// 	and invoke the .exit() method. This 
	// 	method is commonly associated with Background
	// 	apps as they will have asynchronous execution.
	// only remove from the running list 
	public void close(String appName){
		if (this.isOn){
			if (appName!=null){
				for (App a : installedApps){
					if (a.getName().equals(appName)){
						a.exit();
						break;
					}
				}
				for (App a : runningBackgroundApps){
					if (a.getName().equals(appName)){
						runningBackgroundApps.remove(a);
						break;
					}
				}
				if (runningForegroundApp!= null){
					if (appName.equals(runningForegroundApp.getName())){
						runningForegroundApp = null;
					}
				}
			}
		}
	}
	
	public static void main(String[] args){

		

	 }
}
