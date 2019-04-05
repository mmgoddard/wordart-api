package nz.net.example.wordart.gen.service.impl;

import nz.net.example.wordart.gen.domain.Job;
import nz.net.example.wordart.gen.utils.InputStreamConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.EnumSet;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ImageMagickGeneratorTest {

    //TODO:Needs more work to cover all cases.

    private ImageMagickGenerator generator = new ImageMagickGenerator();

    @Test
    public void generateImage_success() throws IOException {

        final String text = "Hello World!";

        Job job = new Job(Job.Format.PNG, text,
                EnumSet.of(Job.Effect.SHADOW_REFLECT), Job.Colour.RED, Job.Font.ARIAL);

        InputStream inputStream = generator.generateImage(job);
        byte[] expectedResult = Base64.getEncoder().encode(InputStreamConverter.readInputStream(inputStream));
        assertNotNull(expectedResult);
    }
}