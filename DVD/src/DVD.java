
public class DVD {
	int mpaa;
	
	String title;
	String releaseDate;
	String director;
	String studio;
	String notes;
	
	DVD(String nTitle, String nRelease, int rating, String nDirector, String nStudio, String nNotes) {
		this.title = nTitle;
		this.releaseDate = nRelease;
		this.mpaa = rating;
		this.director = nDirector;
		this.studio = nStudio;
		this.notes=nNotes;
	}
	
	
}