package com.ganzux.urjc.ssdd.pandemonium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.ganzux.urjc.ssdd.pandemonium.dto.ReturnedTextDto;
import com.ganzux.urjc.ssdd.pandemonium.model.Text;


@EnableAutoConfiguration
@SpringBootApplication
public class PandemoniumTest {

	private static final String urlPath = "http://localhost:80";
	private static final String url = urlPath + TextController.PATH_TEXTS;

	@Test
	public void testPostAndGet(){
		try{
			RestTemplate restTemplate = new RestTemplate();

			// PUT
			Text text = new Text("test_" + System.currentTimeMillis(), "company1", "text 1");
			ReturnedTextDto response = 
					restTemplate.postForObject(url, text, ReturnedTextDto.class);
			
			assertEquals(response.getCompany(), text.getCompany());
			assertEquals(response.getCall(), text.getCallId());

			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
