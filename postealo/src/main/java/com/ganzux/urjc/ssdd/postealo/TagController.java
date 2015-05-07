package com.ganzux.urjc.ssdd.postealo;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ganzux.urjc.ssdd.postealo.model.Tag;

/**
 * REST Controller for knowledge of Tags
 * @author ganzux
 */
@RestController
public class TagController {

	///////////////////////////////////////////////////////////////
	//                        Attributes                         //
	///////////////////////////////////////////////////////////////

	public static final String PATH_TAGS = "/tags";
	
	private static Log log = LogFactory.getLog(TagController.class);

	@Autowired
	private TagRepository tagRepository;
	///////////////////////////////////////////////////////////////
	//                        /Attributes                        //
	///////////////////////////////////////////////////////////////



	///////////////////////////////////////////////////////////////
	//                       Public Methods                      //
	///////////////////////////////////////////////////////////////

	/**
	 * Method to recovery all the Tags from the Repository
	 * @return The Tags List
	 */
	@RequestMapping(value = PATH_TAGS, method = RequestMethod.GET)
	public List<Tag> getTags() {
		log.info("IN getTags");
		List<Tag> tags = tagRepository.findAll();
		log.info("OUT getTags");
		return tags;
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
