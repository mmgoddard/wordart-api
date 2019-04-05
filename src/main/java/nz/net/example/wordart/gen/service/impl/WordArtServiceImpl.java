package nz.net.example.wordart.gen.service.impl;

import nz.net.example.wordart.gen.domain.Job;
import nz.net.example.wordart.gen.exception.WAInternalServiceException;
import nz.net.example.wordart.gen.service.WordArtService;
import nz.net.example.wordart.gen.utils.InputStreamConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class WordArtServiceImpl implements WordArtService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordArtServiceImpl.class);

    @Autowired
    private ImageMagickGenerator imageMagickGenerator;

    @Override
    public byte[] generateWordArtImage(Job job) {

        LOGGER.info("Generating word art image with parameters: '{}'", job);

        InputStream inputStream = imageMagickGenerator.generateImage(job);

        try {
            return InputStreamConverter.readInputStream(inputStream);
        } catch (IOException e) {
            LOGGER.error("WordArtImage Processsing has failed: '{}'", e);
            throw new WAInternalServiceException("WordArtImage Processing has failed.", e);
        }
    }
}