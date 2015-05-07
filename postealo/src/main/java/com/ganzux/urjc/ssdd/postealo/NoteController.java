package com.ganzux.urjc.ssdd.postealo;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ganzux.urjc.ssdd.postealo.model.Note;

/**
 * REST Controller for knowledge of Notes
 * @author ganzux
 */
@RestController
public class NoteController {

	///////////////////////////////////////////////////////////////
	//                        Attributes                         //
	///////////////////////////////////////////////////////////////

	public static final String PATH_NOTES = "/notes";

	private static Log log = LogFactory.getLog(NoteController.class);

	@Autowired
	private NoteRepository noteRepository;

	/*@Autowired
	private TagRepository tagRepository;*/
	///////////////////////////////////////////////////////////////
	//                        /Attributes                        //
	///////////////////////////////////////////////////////////////



	///////////////////////////////////////////////////////////////
	//                       Public Methods                      //
	///////////////////////////////////////////////////////////////

	/**
	 * Method that store a Note with all the tags in the repository
	 * @param note The note to store
	 * @return HttpStatus.CREATED if all is correct
	 */
	@RequestMapping(value = PATH_NOTES, method = RequestMethod.POST)
	public ResponseEntity<Boolean> addNote(@RequestBody Note note) {
		log.info("IN addNote - " + note);
		
		/*Collection<String> tagsStr = new ArrayList<String>();
		if (note.getTags() != null){
			for (Tag tag : note.getTags()){
				tagsStr.add(tag.getText());
			}

			Collection<Tag> tagsRecovered = tagRepository.findByTextIn(tagsStr);

			for (Tag tag : note.getTags()){
				for (Tag tag2 : tagsRecovered){
					if (tag2.getText().equals(tag.getText())){
						tag = tag2;
						break;
					}
				}
			}
		}*/

		noteRepository.save(note);
		log.info("OUT addNote - " + note);
		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
	}

	/**
	 * Method to remove a Note from the Repository
	 * @param idNote yhe unique identifier of the Note to remove
	 * @return HttpStatus.ACCEPTED if all is correct
	 */
	@RequestMapping(value = PATH_NOTES + "/{idNote}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteNote(@PathVariable("idNote") Long idNote) {
		log.info("IN deleteNote - " + idNote);
		noteRepository.delete(idNote);
		log.info("OUT deleteNote - " + idNote);
		return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
	}

	/**
	 * Method to remove all the Notes from the Repository
	 * @return HttpStatus.ACCEPTED if all is correct
	 */
	@RequestMapping(value = PATH_NOTES, method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteAll() {
		log.info("IN deleteAll");
		noteRepository.deleteAll();
		log.info("OUT deleteAll");
		return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
	}

	/**
	 * Method to recovery all the Notes from the Repository
	 * @return The Notes List
	 */
	@RequestMapping(value = PATH_NOTES, method = RequestMethod.GET)
	public List<Note> getNotes() {
		log.info("IN getNotes");
		List<Note> notes = noteRepository.findAll();
		log.info("OUT getNotes");
		return notes;
	}

	/**
	 * Method to get the Notes from the repository by an author.
	 * @param author The name of the author
	 * @return The Notes List that match with the parameter
	 */
	@RequestMapping(value = PATH_NOTES + "/{author}", method = RequestMethod.GET)
	public List<Note> getNote(@PathVariable("author") String author) {
		log.info("IN getNote - " + author);
		List<Note> notes =  noteRepository.findByAuthor(author);
		log.info("OUT getNote - " + author);
		return notes;
	}
	///////////////////////////////////////////////////////////////
	//                      /Public Methods                      //
	///////////////////////////////////////////////////////////////




	///////////////////////////////////////////////////////////////
	//                      Private Methods                      //
	///////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////
	//                      /Private Methods                     //
	///////////////////////////////////////////////////////////////

}
