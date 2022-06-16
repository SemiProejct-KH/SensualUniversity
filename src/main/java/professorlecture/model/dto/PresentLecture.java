package professorlecture.model.dto;

public class PresentLecture {
	
	private String PresentLecture;

	public PresentLecture() {
		super();
	}

	public PresentLecture(String presentLecture) {
		super();
		PresentLecture = presentLecture;
	}

	public String getPresentLecture() {
		return PresentLecture;
	}

	public void setPresentLecture(String presentLecture) {
		PresentLecture = presentLecture;
	}

	@Override
	public String toString() {
		return "PresentLecture [PresentLecture=" + PresentLecture + "]";
	}
	
}
