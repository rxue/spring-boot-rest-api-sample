package org.apche.restaurant.controller;

import java.io.IOException;

import org.apche.restaurant.AbstractTest;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
public abstract class AbstractControllerTest extends AbstractTest {
	protected MockMvc mvc;
	@Autowired
	protected WebApplicationContext applicationContext;
	@Autowired
	private ObjectMapper objectMapper;
	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}
	protected String mapToJson(Object obj) throws JsonProcessingException {
		return this.objectMapper.writeValueAsString(obj);
	}
	protected <T> T mapFromJson(String jsonString, Class<T> klass) throws JsonParseException, JsonMappingException, IOException {
		return this.objectMapper.readValue(jsonString, klass);
	}

}
