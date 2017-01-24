package org.apche.restaurant.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apche.restaurant.model.Restaurant;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class RestaurantControllerTest extends AbstractControllerTest {
  @Before
  public void setUp() {
    super.setUp();
  }
  @Test
  public void testGetRestaurantWithPizzaMenu() throws Exception {
    String uri = "/rest/restaurant/full/1";
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
    assertEquals("Test on expected http response status", HttpStatus.FOUND.value(), mvcResult.getResponse().getStatus());
    String jsonStringContent = mvcResult.getResponse().getContentAsString();
    assertTrue("Test on expected http response body is not empty", jsonStringContent.trim().length() > 0);
    Restaurant restaurant = super.mapFromJson(jsonStringContent, Restaurant.class);
    assertThat(restaurant).isNotNull();
  }

//  @Test
//  @WithMockUser(username="admin", password="admin", roles={"ADMIN"})//Questionable currently
//  public void testCreateRestaurant() throws Exception {
//    String uri = "/rest/private/restaurant";
//    Restaurant restaurant = new Restaurant();
//    restaurant.setName("test");
//    String jsonInputStr = super.mapToJson(restaurant);
//    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//        .contentType(MediaType.APPLICATION_JSON)
//        .accept(MediaType.APPLICATION_JSON)
//        .content(jsonInputStr))
//        .andReturn();
//    assertEquals("With authentication expected http response status should be CREATED - 201", HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
//  }
}
