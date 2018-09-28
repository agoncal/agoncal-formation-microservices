package org.bookstore.generator.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the NumberGeneratorResource REST controller.
 *
 * @see NumberGeneratorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NumberGeneratorResourceIntTest {

    private MockMvc restNumberGeneratorMockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NumberGeneratorResource numberGeneratorResource = new NumberGeneratorResource();
        this.restNumberGeneratorMockMvc = MockMvcBuilders.standaloneSetup(numberGeneratorResource)
                .build();
    }

    @Test
    public void generateNumber() throws Exception {

        // Get all the bookList
        MvcResult result = restNumberGeneratorMockMvc.perform(get("/api/numbers"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString().startsWith("BK-"));
    }

    @Test
    public void checkHealth() throws Exception {

        // Checks Health
        restNumberGeneratorMockMvc.perform(get("/api/numbers/health"))
                .andExpect(status().isOk());
    }
}
