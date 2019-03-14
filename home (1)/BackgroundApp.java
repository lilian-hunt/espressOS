public class BackgroundApp extends App implements BackgroundAppInterface {
	protected BackgroundThread thread;
	public BackgroundApp(String name){
		super(name);
		this.isBackgroundApp = true;
		
	}
	//Background applications provide an interface
	// that allows other applications and the operating
	// 	system to extract data from. Your background 
	// 	app will provide a getData method that will
	// 	return an Object and allows an Object to be 
	// 	passed to it.
	
	public Object getData(Object o){
		return o;
	}
	
	//.start() is typically invoked by operating system. 
	//Since the .start() methd will be reserved for 
	//	setting a BackgroundThread, your application's 
	//	start method will be in .backgroundStart().
	public void backgroundStart(){
		this.isRunning = true;
		// this.thread = new BackgroundThread(this);
	}
	
	public void start(){
		this.thread = new BackgroundThread(this);
		this.thread.start();
	}
	public int exit(){
		this.isRunning = false;
		this.thread.exit();
		return 99;

	}

	
}
