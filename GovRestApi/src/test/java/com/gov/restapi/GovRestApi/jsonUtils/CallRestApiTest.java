package com.gov.restapi.GovRestApi.jsonUtils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CallRestApiTest {
	
	@Autowired
	private JsonApi jsonApi;
	
	@Test
	public void callRestApi() {
		
		jsonApi.callJsonAPi();
		
	}

}
