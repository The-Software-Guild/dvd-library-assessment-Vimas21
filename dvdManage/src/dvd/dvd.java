/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd;

/**
 *
 * @author Samma
 */
public class dvd {
	

	String title;
        String mpaa;
	String releaseDate;
	String director;
	String studio;
	String notes;

	dvd(String nTitle, String nRelease, String rating, String nDirector, String nStudio, String nNotes) {
		this.title = nTitle;
		this.releaseDate = nRelease;
		this.mpaa = rating;
		this.director = nDirector;
		this.studio = nStudio;
		this.notes=nNotes;
	}


} 
