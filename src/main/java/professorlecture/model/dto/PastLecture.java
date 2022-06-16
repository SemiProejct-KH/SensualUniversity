package professorlecture.model.dto;

public class PastLecture {
	
	private String PastLecture;

	public PastLecture() {
		super();
	}

	public PastLecture(String pastLecture) {
		super();
		PastLecture = pastLecture;
	}

	public String getPastLecture() {
		return PastLecture;
	}

	public void setPastLecture(String pastLecture) {
		PastLecture = pastLecture;
	}

	@Override
	public String toString() {
		return "PastLecture [PastLecture=" + PastLecture + "]";
	}
	
}
