public class NotifyOSFailed extends RuntimeException {
	public NotifyOSFailed(){
		super("App was unable to Notify OS");
	}
}
