package com.openclassrooms.mddapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.mddapi.dto.SubscriptionDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SubscriptionControllerIT {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final SubscriptionDto subscriptionDto = new SubscriptionDto(null, 1L, 1L);

    @Test
    @DisplayName("Test Subscribe")
    @WithMockUser(username = "yoga@studio.com")
    void testSubscribe() throws Exception {

        MockHttpServletResponse result = mvc
                .perform(post("/api/subscription/sub")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subscriptionDto)))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

    @Test
    @DisplayName("Test UnSubscribe")
    @WithMockUser(username = "yoga@studio.com")
    void testUnSubscribe() throws Exception {

        MockHttpServletResponse result = mvc
                .perform(post("/api/subscription/unsub")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subscriptionDto)))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }
}
