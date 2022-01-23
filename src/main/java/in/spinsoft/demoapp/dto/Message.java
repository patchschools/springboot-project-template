package in.spinsoft.demoapp.dto;

public class Message {
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public Message(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
}
