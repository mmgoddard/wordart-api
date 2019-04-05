package nz.net.example.wordart.gen.service;

import nz.net.example.wordart.gen.domain.Job;

/**
 * A {@link WordArtService} reads input stream and puts in
 * appropriate format required
 */
public interface WordArtService {

    /**
     * @param job the job parameters that define the image to be produced.
     * @return a byte array that can be returned to the client
     */
    byte[] generateWordArtImage(Job job);
}