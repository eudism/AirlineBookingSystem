package cs.cs489.project.airlinebookingsystem.exception;

public class ScheduledFlightAlreadyBookedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ScheduledFlightAlreadyBookedException(String str) {
		super(str);
	}

}
