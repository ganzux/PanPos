package com.ganzux.urjc.ssdd.pandemonium;

import java.util.List;
import java.util.Random;

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

import com.ganzux.urjc.ssdd.pandemonium.dto.ReturnedTextDto;
import com.ganzux.urjc.ssdd.pandemonium.model.Text;

/**
 * REST Controller for knowledge of Texts
 * @author ganzux
 */
@RestController
public class TextController {

	///////////////////////////////////////////////////////////////
	//                        Attributes                         //
	///////////////////////////////////////////////////////////////

	public static final String PATH_TEXTS = "/message";
	
	private static Log log = LogFactory.getLog(TextController.class);

	@Autowired
	private TextRepository textRepository;
	///////////////////////////////////////////////////////////////
	//                        /Attributes                        //
	///////////////////////////////////////////////////////////////



	///////////////////////////////////////////////////////////////
	//                       Public Methods                      //
	///////////////////////////////////////////////////////////////

	/**
	 * Method that check if a Text object has a virus
	 * @param text The Text Object to check out
	 * @return ReturnedTextDto Object that encapsulates the company,
	 * if the Text has a virus and the analysis time that it take of
	 */
	@RequestMapping(value = PATH_TEXTS, method = RequestMethod.POST)
	public ResponseEntity<ReturnedTextDto> checkText(@RequestBody Text text) {
		log.info("IN checkText - " + text);
		
		boolean isVirus = isVirus(text);
		int time = analTime();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		if (!isVirus){
			log.info("checkText No tiene virus, almacenamos...");
			textRepository.save(text);
			status = HttpStatus.CREATED;
			log.info("checkText No tiene virus, almacenamos OK");
		}
		
		ReturnedTextDto dto = new ReturnedTextDto(text.getCallId(),
				text.getCompany(), isVirus, time);
		
		
		log.info("OUT checkText - " + text);
		return new ResponseEntity<ReturnedTextDto>(dto, status);
	}

	/**
	 * Method that find the texts of a concrete company
	 * @param company the company to use as filter
	 * @return a List with the texts founds
	 */
	@RequestMapping(value = PATH_TEXTS + "/{company}", method = RequestMethod.GET)
	public List<Text> getNote(@PathVariable("company") String company) {
		log.info("IN getNote - " + company);
		List<Text> texts =  textRepository.findByCompany(company);
		log.info("OUT getNote - " + company);
		return texts;
	}
	///////////////////////////////////////////////////////////////
	//                      /Public Methods                      //
	///////////////////////////////////////////////////////////////




	///////////////////////////////////////////////////////////////
	//                      Private Methods                      //
	///////////////////////////////////////////////////////////////

	/**
	 * Method that determine if the parameter is a virus 
	 * @return a boolean that says if the object is a virus or not
	 */
	private boolean isVirus(Object possibleVirus){
		Random r = new Random(System.currentTimeMillis());
		return r.nextBoolean();
	}

	/**
	 * Method that get the analysis time
	 * @return the seconds that takes the analysis
	 */
	private int analTime(){
		Random r = new Random(System.currentTimeMillis());
		return r.nextInt(1001); // Genera un valor entre 0 y 1000 al azar
	}
	///////////////////////////////////////////////////////////////
	//                      /Private Methods                     //
	///////////////////////////////////////////////////////////////
}