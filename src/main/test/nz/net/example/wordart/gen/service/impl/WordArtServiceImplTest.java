package nz.net.example.wordart.gen.service.impl;

import nz.net.example.wordart.gen.domain.Job;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.EnumSet;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WordArtServiceImplTest {

    @InjectMocks
    private WordArtServiceImpl wordArtService;

    @Mock
    private ImageMagickGenerator imageMagickGenerator;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void generateWordArtImage_success() {

        final String text = "Hello World!";
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());

        Job job = new Job(Job.Format.PNG, text,
                EnumSet.of(Job.Effect.SHADOW_REFLECT), Job.Colour.RED, Job.Font.ARIAL);

        when(imageMagickGenerator.generateImage(job)).thenReturn(inputStream);

        byte[] actualResult = wordArtService.generateWordArtImage(job);

        assertEquals(new String(text.getBytes()), new String(actualResult));
    }
}