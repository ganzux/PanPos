package com.ganzux.urjc.ssdd.postealo;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.ganzux.urjc.ssdd.postealo.dto.Text;
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

	@Value("${pande.url}")
	private String pandeUrl;

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
		
		HttpStatus status = HttpStatus.CREATED;
		Boolean returned = true;

		// We look if the text has a virus, via Pandemonium REST Service
		try{
			String uri = "http://" + getPandeUrl() + "/message";
			RestTemplate restTemplate = new RestTemplate();
			
			String callId = System.currentTimeMillis() + "_node1";
			
			Text text = new Text(callId, note.getAuthor(), note.getText());
			Object response = 
					restTemplate.postForObject(uri, text, Object.class);
			Map<String, Object> responseJSonMap = (Map<String, Object>) response;

			String call = (String) responseJSonMap.get("call");
			String company = (String) responseJSonMap.get("company");
			Boolean viruses = (Boolean) responseJSonMap.get("viruses");
			Integer analysis_time = (Integer) responseJSonMap.get("analysis_time");

			log.info("addNote - " + call + " : " + company + " : " + viruses + " : " + analysis_time);

			// Not necessary, only for concurrent threads
			if (callId.equals(call)){
				if (viruses != null && !viruses){
					log.debug("addNote - No virus found, tryin to save...");
					noteRepository.save(note);
					log.debug("addNote - save complete");
				} else {
					status = HttpStatus.BAD_REQUEST;
					returned = false;
					log.info("addNote - Virus found for " + note.getAuthor() +
							" in text " + note.getText());
				}
			} else {
				status = HttpStatus.BAD_REQUEST;
				returned = false;
				log.info("addNote - other thread");
			}

		} catch (ResourceAccessException e){
			log.error("addNote - " + e.getMessage());
			status = HttpStatus.SERVICE_UNAVAILABLE;
			returned = false;
		} catch (Exception e){
			log.error("addNote - " + e.getMessage());
			status = HttpStatus.BAD_REQUEST;
			returned = false;
		}
		
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

		log.info("OUT addNote - " + note + " : " + returned + " : " + status);
		return new ResponseEntity<Boolean>(returned, status);
	}

	/**
	 * Method to remove a Note from the Repository
	 * @param idNote yhe unique identifier of the Note to remove
	 * @return HttpStatus.ACCEPTED if all is correct
	 */
	@RequestMapping(value = PATH_NOTES + "/{idNote}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteNote(@PathVariable("idNote") Long idNote) {
		log.info("IN deleteNote - " + idNote);

		HttpStatus status = HttpStatus.OK;
		Boolean returned = true;

		try{
			boolean thereIsNote = noteRepository.exists(idNote);
			
			if (thereIsNote){
				log.debug("deleteNote - Note found, tryin to remove...");
				noteRepository.delete(idNote);
				log.debug("deleteNote - remove complete");
			} else {
				status = HttpStatus.NOT_FOUND;
				returned = false;
			}
		} catch (Exception e){
			log.error("deleteNote - " + e.getMessage());
			status = HttpStatus.SERVICE_UNAVAILABLE;
			returned = false;
		}

		log.info("OUT deleteNote - " +idNote + " : " + returned + " : " + status);

		return new ResponseEntity<Boolean>(returned, status);
	}

	/**
	 * Method to remove all the Notes from the Repository
	 * @return HttpStatus.ACCEPTED if all is correct
	 */
	@RequestMapping(value = PATH_NOTES, method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteAll() {
		log.info("IN deleteAll");
		
		HttpStatus status = HttpStatus.OK;
		Boolean returned = true;

		try{
			noteRepository.deleteAll();
		} catch (Exception e){
			log.error("deleteAll - " + e.getMessage());
			status = HttpStatus.SERVICE_UNAVAILABLE;
			returned = false;
		}

		log.info("OUT deleteAll - " + returned + " : " + status);

		return new ResponseEntity<Boolean>(returned, status);
	}

	/**
	 * Method to recovery all the Notes from the Repository
	 * @return The Notes List
	 */
	@RequestMapping(value = PATH_NOTES, method = RequestMethod.GET)
	public ResponseEntity<List<Note>> getNotes() {
		log.info("IN getNotes");

		List<Note> notes = null;
		HttpStatus status = HttpStatus.OK;

		try{
			notes = noteRepository.findAll();
		} catch (Exception e){
			status = HttpStatus.SERVICE_UNAVAILABLE;
			log.error("getNotes - " + e.getMessage());
		}

		log.info("OUT getNotes");
		return new ResponseEntity<List<Note>>(notes, status);
	}

	/**
	 * Method to get the Notes from the repository by an author.
	 * @param author The name of the author
	 * @return The Notes List that match with the parameter
	 */
	@RequestMapping(value = PATH_NOTES + "/{author}", method = RequestMethod.GET)
	public ResponseEntity<List<Note>> getNote(@PathVariable("author") String author) {
		log.info("IN getNote - " + author);

		List<Note> notes = null;
		HttpStatus status = HttpStatus.OK;

		try{
			notes = noteRepository.findByAuthor(author);
		} catch (Exception e){
			status = HttpStatus.SERVICE_UNAVAILABLE;
			log.error("getNote - " + author + " : " + e.getMessage());
		}

		log.info("OUT getNote - " + author);
		return new ResponseEntity<List<Note>>(notes, status);
	}
	///////////////////////////////////////////////////////////////
	//                      /Public Methods                      //
	///////////////////////////////////////////////////////////////




	///////////////////////////////////////////////////////////////
	//                      Private Methods                      //
	///////////////////////////////////////////////////////////////
	private String getPandeUrl(){
		if (pandeUrl == null){
			pandeUrl = "pandemoniumurjc.cloudapp.net:80";
		}
		return pandeUrl;
	}
	///////////////////////////////////////////////////////////////
	//                      /Private Methods                     //
	///////////////////////////////////////////////////////////////

}
