// javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar AntennaTestCases.java

// java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore AntennaTestCases

import org.junit.Assert;
import org.junit.Test;

public class AntennaTestCases{
	//Check the AntennaEspressOS class
	@Test 
	public void checkDefault(){
		AntennaEspressOS a = new AntennaEspressOS();
		Assert.assertFalse(a.isConnected());
	}
	@Test
	public void checkDefault2(){
		AntennaEspressOS a = new AntennaEspressOS();
		Assert.assertEquals(0, a.getSignalStrength());
	}
	@Test 
	public void checkSetNetwork(){
		AntennaEspressOS a = new AntennaEspressOS();
		a.setNetwork(true);
		Assert.assertTrue(a.isConnected());
	}
	@Test 
	public void checkSetNetwork2(){
		AntennaEspressOS a = new AntennaEspressOS();
		a.setNetwork(true);
		Assert.assertEquals(1, a.getSignalStrength());
	}
	@Test 
	public void checkSetNetwork3(){
		AntennaEspressOS a = new AntennaEspressOS();
		a.setNetwork(true);
		a.setSignalStrength(5);
		Assert.assertEquals(5, a.getSignalStrength());
	}
	
	@Test
	//set signal outside of top range
	//should be unsuccesful and signal remain at 1
	public void checkSignalStrength1(){
		AntennaEspressOS a = new AntennaEspressOS();
		a.setNetwork(true);
		a.setSignalStrength(6);
		Assert.assertEquals(1, a.getSignalStrength());
	}
	@Test
	//set signal outside of bottom range
	//should be unsuccesful and signal remain at 1
	public void checkSignalStrength2(){
		AntennaEspressOS a = new AntennaEspressOS();
		a.setNetwork(true);
		a.setSignalStrength(-1);
		Assert.assertEquals(1, a.getSignalStrength());
	}
	@Test
	//set signal to 0, network should no longer be connected
	public void checkSignalStrength3(){
		AntennaEspressOS a = new AntennaEspressOS();
		a.setNetwork(true);
		a.setSignalStrength(0);
		Assert.assertEquals(0, a.getSignalStrength());
	}
	@Test
	//set signal to 0, network should no longer be connected
	public void checkSignalStrength4(){
		AntennaEspressOS a = new AntennaEspressOS();
		a.setNetwork(true);
		a.setSignalStrength(0);
		Assert.assertFalse(a.isConnected());
	}
	@Test
	//set signal to 3, within range, should be connected now
	public void checkSignalStrength5(){
		AntennaEspressOS a = new AntennaEspressOS();
		a.setSignalStrength(3);
		Assert.assertEquals(3, a.getSignalStrength());
	}
	@Test
	//set signal to 3, within range, should be connected now
	public void checkSignalStrength6(){
		AntennaEspressOS a = new AntennaEspressOS();
		a.setSignalStrength(3);
		Assert.assertTrue(a.isConnected());
	}
	
	//Check usage with EspressOSMobile
	
	@Test
	//phone off --> disconnected
	public void checkOSDefaults1(){
		EspressOSMobile testOS = new EspressOSMobile();
		Assert.assertFalse(testOS.isConnectedNetwork());
	}
	@Test
	//phone on but initially disconnected
	public void checkOSDefaults2(){
		EspressOSMobile testOS = new EspressOSMobile();
		Assert.assertFalse(testOS.isConnectedNetwork());
	}
	@Test
	//phone off --> disconnected --> signal strength 0 
	public void checkOSDefaults3(){
		EspressOSMobile testOS = new EspressOSMobile();
		Assert.assertEquals(0, testOS.getSignalStrength());
	}
	@Test
	//attempt to connect when phone is off
	public void connectNetwork1(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.connectNetwork();
		Assert.assertFalse(testOS.isConnectedNetwork());
	}
	@Test
	//turn on phone --> connect
	public void connectNetwork2(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.connectNetwork();
		Assert.assertTrue(testOS.isConnectedNetwork());
	}
	@Test
	//turn on phone --> connect, signal strength now 1
	public void connectNetwork3(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.connectNetwork();
		Assert.assertEquals(1, testOS.getSignalStrength());
	}
	@Test 
	// cost 2 battery 
	public void connectNetwork4(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.connectNetwork();
		Assert.assertEquals(18, testOS.getBatteryLife());
	}
	@Test
	// should not connect if battery is less than 3
	public void connectNetwork5(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.usePhone(19);
		testOS.connectNetwork();
		Assert.assertFalse(testOS.isConnectedNetwork());
	}
	@Test
	public void testDisconnectNetwork1(){
		EspressOSMobile testOS = new EspressOSMobile();	
		testOS.setPhoneOn(true);
		testOS.connectNetwork();
		testOS.disconnectNetwork();
		Assert.assertFalse(testOS.isConnectedNetwork());
	}
	@Test
	public void testDisconnectNetwork2(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.connectNetwork();
		testOS.disconnectNetwork();
		Assert.assertEquals(0, testOS.getSignalStrength());
	}
	@Test
	public void testSetSignalStrength(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.setSignalStrength(3);
		Assert.assertTrue(testOS.isConnectedNetwork());
	}
	@Test
	public void testSetSignalStrength2(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.setSignalStrength(3);
		Assert.assertEquals(3, testOS.getSignalStrength());
	}
	@Test
	// outside top of range
	public void testSetSignalStrength3(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.setSignalStrength(6);
		Assert.assertEquals(0, testOS.getSignalStrength());
	}
	@Test
	// outside bottom of range
	public void testSetSignalStrength4(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.setSignalStrength(-1);
		Assert.assertEquals(0, testOS.getSignalStrength());
	}
	@Test
	// doesn't work if the phone is not on
	public void testSetSignalStrength5(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setSignalStrength(5);
		Assert.assertEquals(0, testOS.getSignalStrength());
	}
	@Test 
	// test set to null, can never connect again
	public void testChangeAntenna(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.changeAntenna(null);
		testOS.setPhoneOn(true);
		Assert.assertFalse(testOS.connectNetwork());
	}
	@Test 
	// test set to null, can never connect again
	public void testChangeAntenna2(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.changeAntenna(null);
		testOS.setPhoneOn(true);
		Assert.assertFalse(testOS.setSignalStrength(1));
	}
	@Test 
	// test set to null, checking other methods
	public void testChangeAntenna3(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.changeAntenna(null);
		testOS.setPhoneOn(true);
		Assert.assertEquals(0,testOS.getSignalStrength());
	}
	@Test 
	// test set to null, checking other methods
	public void testChangeAntenna4(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.changeAntenna(null);
		testOS.setPhoneOn(true);
		Assert.assertFalse(testOS.isConnectedNetwork());
	}
	@Test 
	// test set to null, checking other methods
	public void testChangeAntenna5(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.changeAntenna(null);
		testOS.setPhoneOn(true);
		Assert.assertFalse(testOS.isConnectedNetwork());
	}
	@Test 
	// test set to null, check no error
	public void testChangeAntenna6(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.changeAntenna(null);
		testOS.setPhoneOn(true);
		testOS.disconnectNetwork();
		Assert.assertFalse(testOS.isConnectedNetwork());
	}
	@Test
	// change to connected antenna when phone is off will 
	// still not connect the phone to the network
	public void testChangeAntenna7(){
		EspressOSMobile testOS = new EspressOSMobile();
		AntennaEspressOS newAntenna = new AntennaEspressOS();
		newAntenna.setSignalStrength(3);
		testOS.changeAntenna(newAntenna);
		Assert.assertFalse(testOS.isConnectedNetwork());
	}
	@Test
	// change to connected Antenna at strength 3
	public void testChangeAntenna8(){
		EspressOSMobile testOS = new EspressOSMobile();
		AntennaEspressOS newAntenna = new AntennaEspressOS();
		testOS.setPhoneOn(true);
		newAntenna.setSignalStrength(3);
		testOS.changeAntenna(newAntenna);
		Assert.assertTrue(testOS.isConnectedNetwork());
	}
	@Test
	// change to connected Antenna at strength 3
	// phone initially disconnected
	public void testChangeAntenna9(){
		EspressOSMobile testOS = new EspressOSMobile();
		AntennaEspressOS newAntenna = new AntennaEspressOS();
		testOS.setPhoneOn(true);
		newAntenna.setSignalStrength(3);
		testOS.changeAntenna(newAntenna);
		Assert.assertEquals(3, testOS.getSignalStrength());
	}
	@Test
	// change to connected Antenna at strength 3
	// phone initially connected at strength 1
	public void testChangeAntenna10(){
		EspressOSMobile testOS = new EspressOSMobile();
		AntennaEspressOS newAntenna = new AntennaEspressOS();
		testOS.setPhoneOn(true);
		testOS.connectNetwork();
		newAntenna.setSignalStrength(3);
		testOS.changeAntenna(newAntenna);
		Assert.assertEquals(3, testOS.getSignalStrength());
	}
	@Test
	// change to connected Antenna at strength 3
	// phone initially connected at strength 1
	public void testChangeAntenna11(){
		EspressOSMobile testOS = new EspressOSMobile();
		AntennaEspressOS newAntenna = new AntennaEspressOS();
		testOS.setPhoneOn(true);
		testOS.connectNetwork();
		newAntenna.setSignalStrength(3);
		testOS.changeAntenna(newAntenna);
		Assert.assertEquals(true, testOS.isConnectedNetwork());
	}
	@Test
	// antenna will not change if outside fail to replace the antenna
	public void testChangeAntenna12(){
		EspressOSMobile testOS = new EspressOSMobile();
		GeneralAntenna newAntenna = new GeneralAntenna();
		testOS.setPhoneOn(true);
		testOS.connectNetwork();
		newAntenna.setSignalStrength(6);
		testOS.changeAntenna(newAntenna);
		Assert.assertFalse(testOS.changeAntenna(newAntenna));
	}
	@Test
	// antenna will not change if outside fail to replace the antenna
	public void testChangeAntenna13(){
		EspressOSMobile testOS = new EspressOSMobile();
		GeneralAntenna newAntenna = new GeneralAntenna();
		testOS.setPhoneOn(true);
		testOS.connectNetwork();
		newAntenna.setSignalStrength(-1);
		testOS.changeAntenna(newAntenna);
		Assert.assertFalse(testOS.changeAntenna(newAntenna));
	}
}

	
