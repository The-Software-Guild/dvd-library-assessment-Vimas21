package dvd;

/**
 *
 * @author Sam Cryan
 */
public class Disc {
	String title;
        String mpaa;
	String releaseDate;
	String director;
	String studio;
	String notes;

	Disc(String nTitle, String nRelease, String rating, String nDirector, String nStudio, String nNotes) {
		this.title = nTitle;
		this.releaseDate = nRelease;
		this.mpaa = rating;
		this.director = nDirector;
		this.studio = nStudio;
		this.notes=nNotes;
	}
}
