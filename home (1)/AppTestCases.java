// javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar AppTestCases.java

// java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore AppTestCases
import java.util.List;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class AppTestCases {
	@Test
	public void testConstructor(){
		App a = new App("Facebook");
		Assert.assertEquals(a.getName(),"Facebook");
	}
	@Test
	public void testisBackgroundApp(){
		App a = new App("Facebook");
		Assert.assertFalse(a.isBackgroundApp());
	}
	
	@Test
	public void testisBackgroundApp2(){
		NotifyApp a = new NotifyApp("Facebook");
		Assert.assertFalse(a.isBackgroundApp());
	}
	@Test
	public void testisBackgroundApp3(){
		NotifyBackgroundApp a = new NotifyBackgroundApp("Facebook");
		Assert.assertTrue(a.isBackgroundApp());
	}
	@Test
	public void testisBackgroundApp4(){
		BackgroundApp a = new BackgroundApp("Facebook");
		Assert.assertTrue(a.isBackgroundApp());
	}
	@Test
	public void testisNotifyApp(){
		App a = new App("Facebook");
		Assert.assertFalse(a.isNotifyApp());
	}
	@Test
	public void testisNotifyApp2(){
		NotifyApp a = new NotifyApp("Facebook");
		Assert.assertTrue(a.isNotifyApp());
	}
	@Test
	public void testisNotifyApp3(){
		NotifyBackgroundApp a = new NotifyBackgroundApp("Facebook");
		Assert.assertTrue(a.isNotifyApp());
	}
	@Test
	public void testisNotifyApp4(){
		BackgroundApp a = new BackgroundApp("Facebook");
		Assert.assertFalse(a.isNotifyApp());
	}
	@Test
	//test start without installing app on OS first
	public void testRunApp(){
		App a = new App("Facebook");
		EspressOSMobile OS = new EspressOSMobile();		
		OS.setPhoneOn(true);
		OS.run("Facebook");
		Assert.assertFalse(a.isRunning());
	}
	@Test
	//test install and then start app
	public void testRunApp2(){
		App a = new App("Facebook");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Facebook");
		Assert.assertTrue(a.isRunning());
	}
	@Test
	// test run app that isn't installed
	public void testRunApp3(){
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		Assert.assertFalse(OS.run("Messenger"));
	}
	@Test
	public void testRunNull(){
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		Assert.assertFalse(OS.run(null));
	}
	
	@Test
	public void runAppPhoneNotOn(){
		EspressOSMobile OS = new EspressOSMobile();
		App a = new App("Facebook");
		OS.install(a);
		Assert.assertFalse(OS.run("Facebook"));
	}
	
	@Test
	//test install and then start app
	public void testCloseApp(){
		App a = new App("Facebook");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Facebook");
		OS.close("Facebook");
		Assert.assertFalse(a.isRunning());
	}
		@Test
	//test install and then start app
	public void testCloseApp2(){
		BackgroundApp a = new BackgroundApp("Facebook");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Facebook");
		OS.close("Facebook");
		Assert.assertFalse(a.isRunning());
	}
		@Test
	//test install and then start app
	public void testCloseApp3(){
		NotifyBackgroundApp a = new NotifyBackgroundApp("Facebook");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Facebook");
		OS.close("Facebook");
		Assert.assertFalse(a.isRunning());
	}
		@Test
	//test install and then start app
	public void testCloseApp4(){
		NotifyApp a = new NotifyApp("Facebook");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Facebook");
		OS.close("Facebook");
		Assert.assertFalse(a.isRunning());
	}
	@Test
	// only one foreground app running 
	public void testRunningApps(){
		App a = new App("Facebook");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Facebook");
		App b = new App("Instagram");
		OS.install(b);
		OS.run("Instagram");
		List<String> expected = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		expected.add("Instagram");
		for (App app : OS.getRunningApps() ){
			result.add(app.getName());
		}
		Assert.assertEquals(expected,result);
	}
	@Test
	//test run and close app
	public void testRunningApps2(){
		App a = new App("Facebook");
		EspressOSMobile OS = new EspressOSMobile();
		OS.install(a);
		OS.setPhoneOn(true);
		OS.run("Facebook");
		App b = new App("Instagram");
		OS.install(b);
		OS.run("Instagram");
		OS.close("Facebook");
		List<String> expected = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		expected.add("Instagram");
		for (App app : OS.getRunningApps() ){
			result.add(app.getName());
		}
		Assert.assertEquals(expected,result);
	}
	@Test 
	// test run 2 foreground apps, first one should be closed
	public void testRunningApps3(){
		App a = new App("Facebook");
		BackgroundApp b = new BackgroundApp("Siri");
		NotifyBackgroundApp c = new NotifyBackgroundApp("Messenger");
		NotifyApp d = new NotifyApp("WhatsApp");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Facebook");
		OS.install(b);
		OS.run("Siri");
		OS.install(c);
		OS.run("Messenger");
		OS.install(d);
		OS.run("WhatsApp");
		List<String> expected = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		expected.add("Siri");
		expected.add("Messenger");
		expected.add("WhatsApp");
		for (App app : OS.getRunningApps()){
			result.add(app.getName());
		}
		Assert.assertEquals(expected,result);
	}
	@Test 
	// test run 2 foreground apps, first one should be closed
	public void testRunningApps4(){
		App a = new App("Facebook");
		BackgroundApp b = new BackgroundApp("Siri");
		NotifyBackgroundApp c = new NotifyBackgroundApp("Messenger");
		NotifyApp d = new NotifyApp("WhatsApp");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Facebook");
		OS.install(b);
		OS.run("Siri");
		OS.install(c);
		OS.run("Messenger");
		OS.install(d);
		OS.run("WhatsApp");
		Assert.assertFalse(a.isRunning());
	}
	
	@Test
	public void testInstallNull(){
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		Assert.assertFalse(OS.install(null));
	}
	@Test
	//test install app already installed 
	public void testInstall(){
		EspressOSMobile OS = new EspressOSMobile();
		App a = new App("Facebook");
		OS.setPhoneOn(true);
		OS.install(a);
		Assert.assertFalse(OS.install(a));
	}
	@Test
	// install, uninstall and install again
	public void testInstall2(){
		EspressOSMobile OS = new EspressOSMobile();
		App a = new App("Facebook");
		OS.setPhoneOn(true);
		OS.install(a);
		OS.uninstall("Facebook");
		Assert.assertTrue(OS.install(a));
	}
	
	@Test 
	// cannot install app with the same name
	public void testInstall3(){
		EspressOSMobile OS = new EspressOSMobile();
		App a = new App("Facebook");
		OS.setPhoneOn(true);
		OS.install(a);
		App b = new App("Facebook");
		Assert.assertFalse(OS.install(b));
	}
	@Test
	public void testInstalledAppsList(){
		App a = new App("Facebook");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		App b = new App("Instagram");
		OS.install(b);
		List<String> expected = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		expected.add("Facebook");
		expected.add("Instagram");
		for (App app : OS.getInstalledApps() ){
			result.add(app.getName());
		}
		Assert.assertEquals(expected,result);
	}
	@Test
	// test all 4 types of apps
	public void testInstalledAppsList2(){
		App a = new App("Facebook");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		App b = new App("Instagram");
		OS.install(b);
		BackgroundApp c = new BackgroundApp("Siri");
		OS.install(c);
		NotifyApp d = new NotifyApp("Twitter");
		OS.install(d);
		NotifyBackgroundApp e = new NotifyBackgroundApp("WhatsApp");
		OS.install(e);
		List<String> expected = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		expected.add("Facebook");
		expected.add("Instagram");
		expected.add("Siri");
		expected.add("Twitter");
		expected.add("WhatsApp");
		for (App app : OS.getInstalledApps() ){
			result.add(app.getName());
		}
		Assert.assertEquals(expected,result);
	}
	
	@Test
	public void testGetBackgroundApps(){
		App a = new App("Facebook");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		App b = new App("Instagram");
		OS.install(b);
		BackgroundApp c = new BackgroundApp("Siri");
		OS.install(c);
		NotifyApp d = new NotifyApp("Twitter");
		OS.install(d);
		NotifyBackgroundApp e = new NotifyBackgroundApp("WhatsApp");
		OS.install(e);
		List<String> expected = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		expected.add("Siri");
		expected.add("WhatsApp");
		for (App app : OS.getBackgroundApps()){
			result.add(app.getName());
		}
		Assert.assertEquals(expected,result);
	}
	
	@Test
	// test no duplicates added
	public void testGetBackgroundApps2(){
		App a = new App("Facebook");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		App b = new App("Instagram");
		OS.install(b);
		BackgroundApp c = new BackgroundApp("Siri");
		OS.install(c);
		OS.install(c);
		NotifyApp d = new NotifyApp("Twitter");
		OS.install(d);
		NotifyBackgroundApp e = new NotifyBackgroundApp("WhatsApp");
		OS.install(e);
		OS.install(e);
		List<String> expected = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		expected.add("Siri");
		expected.add("WhatsApp");
		for (App app : OS.getBackgroundApps()){
			result.add(app.getName());
		}
		Assert.assertEquals(expected,result);
	}
	@Test
	public void testGetNotifyApps(){
		App a = new App("Facebook");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		App b = new App("Instagram");
		OS.install(b);
		BackgroundApp c = new BackgroundApp("Siri");
		OS.install(c);
		NotifyApp d = new NotifyApp("Twitter");
		OS.install(d);
		NotifyBackgroundApp e = new NotifyBackgroundApp("WhatsApp");
		OS.install(e);
		List<String> expected = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		expected.add("Twitter");
		expected.add("WhatsApp");
		for (App app : OS.getNotificationApp()){
			result.add(app.getName());
		}
		Assert.assertEquals(expected,result);
	}
	@Test
	// test no duplicates
	public void testGetNotifyApps2(){
		App a = new App("Facebook");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		App b = new App("Instagram");
		OS.install(b);
		BackgroundApp c = new BackgroundApp("Siri");
		OS.install(c);
		NotifyApp d = new NotifyApp("Twitter");
		OS.install(d);
		OS.install(d);
		NotifyBackgroundApp e = new NotifyBackgroundApp("WhatsApp");
		OS.install(e);
		OS.install(e);
		List<String> expected = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		expected.add("Twitter");
		expected.add("WhatsApp");
		for (App app : OS.getNotificationApp()){
			result.add(app.getName());
		}
		Assert.assertEquals(expected,result);
	}
	@Test
	public void testGetNotifyApps3(){
		App a = new App("Facebook");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		App b = new App("Instagram");
		OS.install(b);
		BackgroundApp c = new BackgroundApp("Siri");
		OS.install(c);
		NotifyApp d = new NotifyApp("Twitter");
		OS.install(d);
		NotifyBackgroundApp e = new NotifyBackgroundApp("WhatsApp");
		OS.install(e);
		OS.run("WhatsApp");
		OS.uninstall("WhatsApp");
		List<String> expected = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		expected.add("Twitter");
		for (App app : OS.getNotificationApp()){
			result.add(app.getName());
		}
		Assert.assertEquals(expected,result);
	}
	@Test 
	// check also removes off running app list
	public void testUninstall(){
		App a = new App("Facebook");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		App b = new App("Instagram");
		OS.install(b);
		BackgroundApp c = new BackgroundApp("Siri");
		OS.install(c);
		NotifyApp d = new NotifyApp("Twitter");
		OS.install(d);
		NotifyBackgroundApp e = new NotifyBackgroundApp("WhatsApp");
		OS.install(e);
		OS.run("WhatsApp");
		OS.uninstall("WhatsApp");
		List<String> expected = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		for (App app : OS.getRunningApps()){
			result.add(app.getName());
		}
		Assert.assertEquals(expected,result);
	}
	
	@Test
	public void uninstallNull(){
		EspressOSMobile OS = new EspressOSMobile();
		Assert.assertFalse(OS.uninstall(null));
	}

	@Test
	// attempt to uninstall app that is not initially installed
	public void testUninstall2(){
		EspressOSMobile OS = new EspressOSMobile();
		App a = new App("Facebook");
		Assert.assertFalse(OS.uninstall("Facebook"));
	}
	
	@Test
	// check that app is uninstalled properly
	public void testUninstall3(){
		EspressOSMobile OS = new EspressOSMobile();
		App a = new App("Facebook");
		OS.setPhoneOn(true);
		OS.install(a);
		OS.uninstall("Facebook");
		Assert.assertFalse(a.isInstalled());
	}
	
	@Test
	public void testNotifyAppConstructor(){
		NotifyApp a = new NotifyApp("Messenger");
		Assert.assertEquals("Messenger", a.getName());
	}
	@Test
	public void testNotifyAppConstructor2(){
		NotifyApp a = new NotifyApp("Messenger");
		Assert.assertTrue(a.isNotifyApp());
	}
	// try to notifyOS without being installed
	@Test(expected = NotifyOSFailed.class) 
	public void testNotifyOS() { 
     	NotifyApp a = new NotifyApp("Messenger");
		a.notifyOS();
	}
	// try to notifyOS without app running
	@Test(expected = NotifyOSFailed.class) 
	public void testNotifyOS2() { 
     	NotifyApp a = new NotifyApp("Messenger");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		a.notifyOS();
	}
	// should be succesful 
	@Test
	public void testNotifyOS3() { 
     	NotifyApp a = new NotifyApp("Messenger");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Messenger");
		a.notifyOS();
		List<String> notifs = OS.getNotifications();
		List<String> expected = new ArrayList<String>();
		expected.add("You have a notifcaion from: Messenger");
		Assert.assertEquals(expected,notifs);
	}
	// attempt to notify a phone that is off
	@Test(expected = NotifyOSFailed.class)
	public void testNotifyOS4() { 
     	NotifyApp a = new NotifyApp("Messenger");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Messenger");
		OS.setPhoneOn(false);
		a.notifyOS();
	}
	// attempt to notify a phone that is off
	// from App that is both notify and background 
	@Test(expected = NotifyOSFailed.class)
	public void testNotifyOS5() { 
     	NotifyBackgroundApp a = new NotifyBackgroundApp("Messenger");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Messenger");
		OS.setPhoneOn(false);
		a.notifyOS();
	}
	@Test
	public void testNotifyOS6() { 
     	NotifyBackgroundApp a = new NotifyBackgroundApp("Messenger");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Messenger");
		a.notifyOS();
		NotifyApp b = new NotifyApp("Facebook");
		OS.install(b);
		OS.run("Facebook");
		b.notifyOS();
		List<String> expected = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		expected.add("You have a notifcaion from: Messenger");
		expected.add("You have a notifcaion from: Facebook");
		Assert.assertEquals(expected,OS.getNotifications());
	}
	
	//test sending multiple notifications
	@Test
	public void testNotifyOS7() { 
     	NotifyBackgroundApp a = new NotifyBackgroundApp("Messenger");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Messenger");
		a.notifyOS();
		NotifyApp b = new NotifyApp("Facebook");
		OS.install(b);
		OS.run("Facebook");
		b.notifyOS();
		a.notifyOS();
		a.notifyOS();
		b.notifyOS();
		List<String> expected = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		expected.add("You have a notifcaion from: Messenger");
		expected.add("You have a notifcaion from: Facebook");
		expected.add("You have a notifcaion from: Messenger");
		expected.add("You have a notifcaion from: Messenger");
		expected.add("You have a notifcaion from: Facebook");
		Assert.assertEquals(expected,OS.getNotifications());
	}
	
	@Test
	public void testBackgroundStart(){
		NotifyBackgroundApp a = new NotifyBackgroundApp("Messenger");
		BackgroundApp b = new BackgroundApp("Siri");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Messenger");
		OS.install(b);
		OS.run("Siri");
		OS.getRunningApps();
		List<String> expected = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		expected.add("Messenger");
		expected.add("Siri");
		for (App app : OS.getRunningApps()){
			result.add(app.getName());
		}
		Assert.assertEquals(expected,result);

	}
	
	@Test
	public void testGetData(){
		NotifyBackgroundApp a = new NotifyBackgroundApp("Messenger");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Messenger");
		Assert.assertEquals("Data", a.getData("Data"));
	}
	@Test
	public void testGetData2(){
		NotifyBackgroundApp a = new NotifyBackgroundApp("Messenger");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Messenger");
		Assert.assertEquals(null, a.getData(null));
	}
	@Test
	public void testGetData3(){
		BackgroundApp a = new BackgroundApp("Messenger");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Messenger");
		Assert.assertEquals("Data", a.getData("Data"));
	}
	@Test
	public void testGetData4(){
		BackgroundApp a = new BackgroundApp("Messenger");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Messenger");
		Assert.assertEquals(null, a.getData(null));
	}
	
	// phone must be on to return lists of applications
	
	@Test 
	public void testPhoneIsOff1(){
		EspressOSMobile OS = new EspressOSMobile();
		App a = new App("Facebook");
		Assert.assertFalse(OS.install(a));
	}
	@Test 
	public void testPhoneIsOff2(){
		EspressOSMobile OS = new EspressOSMobile();
		App a = new App("Facebook");
		Assert.assertFalse(OS.uninstall("Facebook"));
	}
	@Test 
	public void testPhoneIsOff3(){
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		App a = new App("Facebook");
		OS.install(a);
		OS.run("Facebook");
		OS.setPhoneOn(false);
		Assert.assertNull(OS.getRunningApps());
	}
	
	@Test 
	public void testPhoneIsOff4(){
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		App a = new App("Facebook");
		OS.install(a);
		OS.run("Facebook");
		OS.setPhoneOn(false);
		Assert.assertNull(OS.getInstalledApps());
	}
	
	@Test 
	public void testPhoneIsOff5(){
		EspressOSMobile OS = new EspressOSMobile();
		BackgroundApp a = new BackgroundApp("A");
		NotifyBackgroundApp	b = new NotifyBackgroundApp("B");
		OS.setPhoneOn(true);
		OS.install(a);
		OS.install(b);
		OS.setPhoneOn(false);
		Assert.assertNull(OS.getBackgroundApps());
	}
	@Test 
	public void testPhoneIsOff6(){
		EspressOSMobile OS = new EspressOSMobile();
		NotifyApp a = new NotifyApp("A");
		NotifyBackgroundApp	b = new NotifyBackgroundApp("B");
		OS.setPhoneOn(true);
		OS.install(a);
		OS.install(b);
		OS.setPhoneOn(false);
		Assert.assertNull(OS.getNotificationApp());
	}

	@Test
	public void testPhoneIsOff7() { 
     	NotifyBackgroundApp a = new NotifyBackgroundApp("Messenger");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("Messenger");
		a.notifyOS();
		NotifyApp b = new NotifyApp("Facebook");
		OS.install(b);
		OS.run("Facebook");
		b.notifyOS();
		a.notifyOS();
		a.notifyOS();
		b.notifyOS();
		OS.setPhoneOn(false);
		Assert.assertNull(OS.getNotifications());
	}
	@Test
	public void testPhoneIsOff8(){
		NotifyBackgroundApp a = new NotifyBackgroundApp("A");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.setPhoneOn(false);
		Assert.assertFalse(OS.run("A"));
	}
	@Test
	public void testTurnOffPhone(){
		NotifyBackgroundApp a = new NotifyBackgroundApp("A");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("A");
		OS.setPhoneOn(false);
		Assert.assertFalse(a.isRunning());
	}
	@Test
	// no apps should be running after phone has been switched on and off
	public void testTurnOffPhone2(){
		NotifyBackgroundApp a = new NotifyBackgroundApp("A");
		EspressOSMobile OS = new EspressOSMobile();
		OS.setPhoneOn(true);
		OS.install(a);
		OS.run("A");
		App b = new App("B");
		OS.install(b);
		OS.run("B");
		NotifyApp c = new NotifyApp("C");
		OS.install(c);
		OS.run("C");
		BackgroundApp d = new BackgroundApp("D");
		OS.install(d);
		OS.run("D");
		OS.setPhoneOn(false);
		OS.setPhoneOn(true);
		Assert.assertEquals(new ArrayList<App>(),OS.getRunningApps());
	}
	@Test
	public void testTurnOnBackgroundApp(){
		EspressOSMobile OS = new EspressOSMobile();
		NotifyBackgroundApp a = new NotifyBackgroundApp("A");
		BackgroundApp b = new BackgroundApp(("B"));
		OS.setPhoneOn((true));
		OS.install(a);
		OS.install(b);
		OS.run("A");
		OS.run("B");
		ArrayList<App> expected = new ArrayList<App>();
		expected.add(a);
		expected.add(b);
		Assert.assertEquals(expected, OS.getRunningApps());
	}
}
