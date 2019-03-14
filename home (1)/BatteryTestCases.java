// javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar BatteryTestCases.java

// java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore BatteryTestCases

import org.junit.Assert;
import org.junit.Test;

public class BatteryTestCases{
	@Test
	// test default
	public void testDefault(){
		BatteryEspressOS b = new BatteryEspressOS();
		Assert.assertEquals(25, b.getLevel());
	}
	// testing the BatteryEspressOS class itself
	@Test
	public void testSetLevel1(){
		BatteryEspressOS b = new BatteryEspressOS();
		b.setLevel(30);
		Assert.assertEquals(30, b.getLevel());
	}
	@Test
	public void testSetLevel2(){
		BatteryEspressOS b = new BatteryEspressOS();
		b.setLevel(-1);
		Assert.assertEquals(0, b.getLevel());
	}
	@Test
	public void testSetLevel3(){
		BatteryEspressOS b = new BatteryEspressOS();
		b.setLevel(101);
		Assert.assertEquals(100, b.getLevel());
	}
	@Test
	public void testSetLevel4(){
		BatteryEspressOS b = new BatteryEspressOS();
		b.setLevel(100);
		Assert.assertEquals(100, b.getLevel());
	}
	@Test
	public void testSetLevel5(){
		BatteryEspressOS b = new BatteryEspressOS();
		b.setLevel(0);
		Assert.assertEquals(0, b.getLevel());
	}
	
	// testing Battery implementation within EspressOS class 
	@Test
	//initially should be 0 because phone is off
	public void testDefaultOSClass(){
		EspressOSMobile testOS = new EspressOSMobile();
		Assert.assertEquals(0, testOS.getBatteryLife());
	}
	@Test
	//initially battery should be 25 
	//turn on phone costs 5
	public void testDefaultOSClass2(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		Assert.assertEquals(20, testOS.getBatteryLife());
	}
	@Test
	public void testChangeBattery(){
		EspressOSMobile testOS = new EspressOSMobile();
		Battery newBattery = new BatteryEspressOS();
		testOS.setPhoneOn(true);
		newBattery.setLevel(50);
		testOS.changeBattery(newBattery);
		testOS.setPhoneOn(true);
		Assert.assertEquals(45, testOS.getBatteryLife());
	}
	@Test
	public void testChangeBattery2(){
		EspressOSMobile testOS = new EspressOSMobile();
		Battery newBattery = new BatteryEspressOS();
		testOS.setPhoneOn(true);
		newBattery.setLevel(50);
		testOS.changeBattery(newBattery);
		Assert.assertFalse(testOS.isPhoneOn());
	}
	@Test
	public void testChangeBattery3(){
		EspressOSMobile testOS = new EspressOSMobile();
		Battery newBattery = new BatteryEspressOS();
		newBattery.setLevel(100);
		testOS.changeBattery(newBattery);
		testOS.setPhoneOn(true);
		Assert.assertEquals(95, testOS.getBatteryLife());
	}
	@Test
	public void testChangeBattery4(){
		EspressOSMobile testOS = new EspressOSMobile();
		Battery newBattery = new BatteryEspressOS();
		newBattery.setLevel(0);
		testOS.changeBattery(newBattery);
		Assert.assertEquals(0, testOS.getBatteryLife());
	}
	@Test
	//battery does not allow value outside of range [0,100]
	//so change of battery will be possible
	public void testChangeBattery5(){
		EspressOSMobile testOS = new EspressOSMobile();
		Battery newBattery = new BatteryEspressOS();
		newBattery.setLevel(110);
		testOS.changeBattery(newBattery);
		testOS.setPhoneOn(true);
		Assert.assertEquals(95, testOS.getBatteryLife());
	}
	@Test
	//battery does not allow value outside of range [0,100]
	//so change of battery will be possible
	public void testChangeBattery6(){
		EspressOSMobile testOS = new EspressOSMobile();
		Battery newBattery = new BatteryEspressOS();
		newBattery.setLevel(-10);
		testOS.changeBattery(newBattery);
		Assert.assertEquals(0, testOS.getBatteryLife());
	}
	//created General Battery class to test Batteries outside range [0,100]
	@Test
	public void testChangeBattery7(){
		EspressOSMobile testOS = new EspressOSMobile();
		Battery newBattery = new GeneralBattery();
		newBattery.setLevel(-10);
		Assert.assertFalse(testOS.changeBattery(newBattery));
	}
	@Test
	public void testChangeBattery8(){
		EspressOSMobile testOS = new EspressOSMobile();
		Battery newBattery = new GeneralBattery();
		newBattery.setLevel(0);
		Assert.assertTrue(testOS.changeBattery(newBattery));
	}
	@Test
	public void testChangeBattery9(){
		EspressOSMobile testOS = new EspressOSMobile();
		Battery newBattery = new GeneralBattery();
		newBattery.setLevel(100);
		Assert.assertTrue(testOS.changeBattery(newBattery));
	}
	@Test
	public void testChangeBattery10(){
		EspressOSMobile testOS = new EspressOSMobile();
		Battery newBattery = new GeneralBattery();
		newBattery.setLevel(101);
		Assert.assertFalse(testOS.changeBattery(newBattery));
	}
	@Test
	//battery loss of 5 to turn phone on
	public void testSetPhoneOn(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		Assert.assertEquals(20, testOS.getBatteryLife());

	}
	@Test
	//insufficient battery to turn phone on
	public void testSetPhoneOn2(){
		EspressOSMobile testOS = new EspressOSMobile();
		Battery newBattery = new BatteryEspressOS();
		newBattery.setLevel(5);
		testOS.changeBattery(newBattery);
		Assert.assertFalse(testOS.setPhoneOn(true));

	}
	@Test
	public void testChargeBattery1(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.chargePhone();
		Assert.assertEquals(30, testOS.getBatteryLife());

	}
	@Test
	//charging battery from 95
	public void testChargeBattery2(){
		EspressOSMobile testOS = new EspressOSMobile();
		Battery newBattery = new BatteryEspressOS();
		testOS.setPhoneOn(true);
		newBattery.setLevel(95);
		testOS.changeBattery(newBattery);
		testOS.setPhoneOn(true);
		testOS.chargePhone();
		Assert.assertEquals(100, testOS.getBatteryLife());

	}
	@Test
	//attempting to charge full battery
	public void testChargeBattery3(){
		EspressOSMobile testOS = new EspressOSMobile();
		Battery newBattery = new BatteryEspressOS();
		testOS.setPhoneOn(true);
		newBattery.setLevel(100);
		testOS.changeBattery(newBattery);
		Assert.assertFalse(testOS.chargePhone());

	}
	@Test
	//attempt to use off phone
	public void testUsePhone(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.usePhone(5);
		Assert.assertEquals(0, testOS.getBatteryLife());
	}
	@Test
	public void testUsePhone2(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.usePhone(5);
		Assert.assertEquals(15, testOS.getBatteryLife());
	}
	@Test
	//use phone to 0 battery should power off
	public void testUsePhone3(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.usePhone(20);
		Assert.assertEquals(0, testOS.getBatteryLife());
	}
	@Test
	//use phone to 0 battery should power off
	public void testUsePhone4(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.usePhone(20);
		Assert.assertEquals(false, testOS.isPhoneOn());
	}
	@Test
	//use phone to 0, network should be disconnected
	public void testUsePhone5(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.connectNetwork();
		testOS.usePhone(20);
		Assert.assertEquals(false, testOS.isConnectedNetwork());
	}
	@Test(expected = RuntimeException.class)
	// input negative number should raise RuntimeException
	public void testUsePhone6(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.usePhone(-5);
		
	}
	@Test
	public void testNullBatteryChange(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		Assert.assertFalse(testOS.changeBattery(null));
	}
	@Test 
	// test battery level remains constant
	// after unsuccesful attepmt to change battery
	public void testNullBatteryChange2(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.changeBattery(null);
		Assert.assertEquals(20, testOS.getBatteryLife());
	}
	@Test
	// test connect network, reduce battery by 2
	public void testConnectNetwork(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.connectNetwork();
		Assert.assertEquals(18, testOS.getBatteryLife());
	}
	@Test
	// test connect network, but not enough battery
	public void testConnectNetwork2(){
		EspressOSMobile testOS = new EspressOSMobile();
		testOS.setPhoneOn(true);
		testOS.usePhone(18);
		Assert.assertFalse(testOS.connectNetwork());
	}
}

