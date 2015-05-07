package com.ganzux.urjc.ssdd.postealo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.ganzux.urjc.ssdd.postealo.model.Note;


@EnableAutoConfiguration
@SpringBootApplication
public class PostealoTest {

	private static final String urlPath = "http://localhost:8080";
	private static final String urlNotes = urlPath + NoteController.PATH_NOTES;
	private static final String urlTags = urlPath + TagController.PATH_TAGS;
	
	@Before
	public void config(){
		try{
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.delete(urlNotes);
			assertTrue(true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testPost(){
		try{
			RestTemplate restTemplate = new RestTemplate();

			// GET ALL
			List notes = restTemplate.getForObject(urlNotes, List.class);
			int size = notes.size();
			assertEquals(size, 0);

			// PUT
			Note note = new Note();
			note.setAuthor("Autor1");
			note.setText("texto de prueba 1");
			note.addTag("tag 1");
			note.addTag("tag 2");
			note.addTag("tag 3");
			note.addTag("tag 4");
			note.addTag("tag 5");
			Boolean response = restTemplate.postForObject(urlNotes, note, Boolean.class);
			
			assertTrue(response);
			
			notes = restTemplate.getForObject(urlNotes, List.class);
			notes.size();
			
			assertEquals(size + 1, notes.size());
			
			
			note = new Note();
			note.setAuthor("Autor2");
			note.setText("texto de prueba 2");
			note.addTag("tag 1");
			note.addTag("tag 2");
			note.addTag("tag 3");
			response = restTemplate.postForObject(urlNotes, note, Boolean.class);
			
			assertTrue(response);
			
			notes = restTemplate.getForObject(urlNotes, List.class);
			notes.size();
			
			assertEquals(size + 2, notes.size());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testTags(){
		try{
			RestTemplate restTemplate = new RestTemplate();

			// GET ALL
			List allTags = restTemplate.getForObject(urlTags, List.class);
			int size = allTags.size();

			// PUT
			Note note = new Note();
			note.setAuthor("AutorTest");
			note.setText("texto de test XXX");
			note.addTag("tagTag new 1");	// tags + 1
			note.addTag("tagTag new 2");	// tags + 2
			Boolean response = restTemplate.postForObject(urlNotes, note, Boolean.class);

			assertTrue(response);

			allTags = restTemplate.getForObject(urlTags, List.class);
			assertEquals(size + 2, allTags.size());

			Note note2 = new Note();
			note2.setAuthor("AutorTest 2");
			note2.setText("texto de prueba 22");
			note2.addTag("new tag 3rd");		// tags + 3
			note2.addTag("tagTag new 2");// cloned tag
			response = restTemplate.postForObject(urlNotes, note2, Boolean.class);
			
			assertTrue(response);
			
			List allTheTags = restTemplate.getForObject(urlTags, List.class);

			assertEquals(size + 4, allTheTags.size());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void testGets(){
		try{
			RestTemplate restTemplate = new RestTemplate();

			// GET ALL
			List<Map> notes = restTemplate.getForObject(urlNotes, List.class);
			
			for (Map note : notes){
				// GET BY AUTHOR
				List<Map> notesByAuthor = restTemplate.getForObject(urlNotes + "/" + note.get("author"), List.class);

				for (Map noteByAuthor : notesByAuthor){
					assertEquals(note.get("author"), noteByAuthor.get("author"));
				}

			}
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@org.junit.After
	public void testDelete(){
		try{
			RestTemplate restTemplate = new RestTemplate();

			// GET ALL
			List<Map> notes = restTemplate.getForObject(urlNotes, List.class);
			
			for (Map note : notes){
				// DELETE BY ID
				restTemplate.delete(urlNotes + "/" + note.get("id"));
				
				// GET BY AUTHOR MUST BE ZERO
				List<Map> notesByAuthor = restTemplate.getForObject(urlNotes + "/" + note.get("author"), List.class);
				assertEquals(notesByAuthor.size(), 0);
			}
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
