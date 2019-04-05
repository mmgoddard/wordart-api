package nz.net.example.wordart.gen.service.impl;

import nz.net.example.wordart.gen.domain.Job;
import nz.net.example.wordart.gen.domain.Command;
import nz.net.example.wordart.gen.exception.WAInternalServiceException;
import nz.net.example.wordart.gen.service.Generator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static nz.net.example.wordart.gen.domain.Job.Effect.*;

/**
 * A {@link Generator} implementation that uses the image magick library's
 * convert command (expected to be on the system's path) to render images from
 * text.
 */
@Component
public class ImageMagickGenerator implements Generator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageMagickGenerator.class);

    @Override
    public InputStream generateImage(Job job) {

        Command.CommandBuilder builder = new Command.CommandBuilder(job.getFont());

        if (job.getEffects().contains(SHADOW_HARD))
            builder.shadowHard(job.getText());

        if (job.getEffects().contains(SHADOW_REFLECT))
            builder.shadowReflect(job.getText());

        if (job.getEffects().contains(OUTLINE)) {
            builder.outline();
        }

        if (job.getEffects().contains(GRADIENT)) {
            builder.gradient(job.getFontColour());
        } else {
            builder.noGradient(job.getFontColour());
        }

        List<String> command = builder.build(job.getText(), job.getFormat()).getCommands();

        LOGGER.info("Image Magick Command: '{}", command);

        ProcessBuilder processBuilder = new ProcessBuilder(command);

        Process process;
        try {
            process = processBuilder.start();
        } catch (IOException e) {
             throw new WAInternalServiceException();
        }

        return process.getInputStream();
    }
}