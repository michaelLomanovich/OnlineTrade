package com.lomrom.store.rest;

import com.lomrom.store.ASpringIntegrationTest;
import com.lomrom.store.dto.city.PlaceAddDto;
import com.lomrom.store.enums.PlaceStatus;
import com.lomrom.store.model.Place;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class StoreApplicationTests extends ASpringIntegrationTest {

    @Test
    public void testAddPlaceReturnPlace() throws Exception {
        PlaceAddDto dtoToAdd = PlaceAddDto.builder().name("123").build();
        String json = mapper.writeValueAsString(dtoToAdd);
        MvcResult result = this.mockMvc.perform(post("/rest/place").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
        Place place = mapper.readValue(result.getResponse().getContentAsString(), Place.class);
        assertAll(() -> {
            assertNotNull(place.getId());
            assertEquals(PlaceStatus.CREATED, place.getStatus());
            assertEquals("123", place.getName());
        });
    }

}
