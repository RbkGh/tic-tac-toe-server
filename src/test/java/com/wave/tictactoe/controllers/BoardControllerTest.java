package com.wave.tictactoe.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;


/**
 * author: acerbk
 * Date: 4/2/21
 * Time: 4:36 AM
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void should_expect_correct_processing_of_optimal_move() throws Exception {

        String expectedResult = "oxxo  o  ";

        String requestData = "+xxo++o++";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/?board=" + requestData)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        Assert.isTrue(result.equals(expectedResult), "");
    }

    @Test
    void should_expect_400_error_when_request_param_exceeds_9_characters() throws Exception {

        String requestData = "+xxo++o++x";

        mockMvc.perform(MockMvcRequestBuilders
                .get("/?board=" + requestData)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void should_expect_another_correct_processing_of_optimal_move() throws Exception {

        String expectedResult = "xo oo  x ";

        String requestData = "xo+o+++x+";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/?board=" + requestData)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        Assert.isTrue(result.equals(expectedResult), "");
    }

    @Test
    void should_expect_400_error_when_o_values_are_more_than_x_values() throws Exception {

        String requestData = "xo+oo++x+";

        mockMvc.perform(MockMvcRequestBuilders
                .get("/?board=" + requestData)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void should_expect_400_error_when_character_is_unknown() throws Exception {

        String requestData = "LLL0o74ye";

        mockMvc.perform(MockMvcRequestBuilders
                .get("/?board=" + requestData)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        requestData = "LLL0i74ye"; //remove the o which is a legal character and still expect a bad request as response
        mockMvc.perform(MockMvcRequestBuilders
                .get("/?board=" + requestData)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    void should_expect_o_with_8_spaces_after_it() throws Exception {

        String expectedResult = "o        ";

        String requestData = "+++++++++";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/?board=" + requestData)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        Assert.isTrue(result.equals(expectedResult), "");
    }

    @Test
    void should_expect_opposite_corner_as_optimal_choice() throws Exception {

        String expectedResult = "x o      ";

        String requestData = "x++++++++";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/?board=" + requestData)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        Assert.isTrue(result.equals(expectedResult), "");
    }

    @Test
    void should_expect_win_as_optimal_choice() throws Exception {

        String expectedResult = "ooox x   ";

        String requestData = "oo+x+x+++";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/?board=" + requestData)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        Assert.isTrue(result.equals(expectedResult), "");
    }

    @Test
    void something_here() throws Exception {

        String expectedResult = "o  o xo x";

        String requestData = "o++o+x++x";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/?board=" + requestData)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        Assert.isTrue(result.equals(expectedResult), "");
    }

    @Test
    void should_win() throws Exception {

        String expectedResult = "xx ooo   ";

        String requestData = "xx+o+o+++";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/?board=" + requestData)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        Assert.isTrue(result.equals(expectedResult), "");
    }
}
