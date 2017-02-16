package ulink.constructor;

public class EmailSend {
	String clientName;
	String screeningName;
	String dateTime;
	
	
	public EmailSend(String clientName, String screeningName, String dateTime) {
		super();
		this.clientName = clientName;
		this.screeningName = screeningName;
		this.dateTime = dateTime;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getScreeningName() {
		return screeningName;
	}
	public void setScreeningName(String screeningName) {
		this.screeningName = screeningName;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	
}
