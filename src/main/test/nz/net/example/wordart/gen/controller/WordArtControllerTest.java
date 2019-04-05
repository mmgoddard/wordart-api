package nz.net.example.wordart.gen.controller;

import nz.net.example.wordart.gen.service.WordArtService;
import nz.net.example.wordart.gen.utils.ServiceResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = WordArtController.class)
public class WordArtControllerTest {

    private final static String BASE_URL = "/wordart?text=text&format=PNG&fontColour=RED&font=ARIAL&effects=SHADOW_HARD";
    private final static String TEXT = "text";
    private final static String CONTENT_LENGTH = "Content-Length";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WordArtService wordArtService;

    @Test
    public void generateWordArtImage_success() throws Exception {

        when(wordArtService.generateWordArtImage(any())).thenReturn(TEXT.getBytes());

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(header().string(ServiceResponse.CONTENT_DISPOSITION_HEADER, "attachment;filename=text.png"))
                .andExpect(header().string(CONTENT_LENGTH, String.valueOf(TEXT.length())))
                .andExpect(content().contentType(MediaType.IMAGE_PNG))
                .andExpect(content().string(TEXT));
    }

    @Test
    public void generateWordArtImage_endpointNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/worda"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void generateWordArtImage_textFieldRequired() throws Exception {

        String BASE_URL = "/wordart?format=PNG&fontColour=RED&font=ARIAL&effects=SHADOW_HARD";

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andExpect(status().isBadRequest());
    }
}