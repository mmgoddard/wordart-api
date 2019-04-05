package nz.net.example.wordart.gen.service;

import nz.net.example.wordart.gen.domain.Job;

import java.io.InputStream;

/**
 * A {@link Generator} can produce a stream of bytes for an image, according
 * to the specifics of the given job.
 */
public interface Generator {

    /**
     * @param job the job parameters that define the image to be produced.
     * @return an input stream that yields the bytes that form the image
     */
    InputStream generateImage(Job job);
}