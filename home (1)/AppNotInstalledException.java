public class AppNotInstalledException extends Exception {
	public AppNotInstalledException(){
		super("This app is not installed.");
	}
}
