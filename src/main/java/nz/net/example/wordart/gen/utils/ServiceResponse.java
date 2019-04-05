package nz.net.example.wordart.gen.utils;

import nz.net.example.wordart.gen.domain.Job;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class ServiceResponse {

    public final static String CONTENT_DISPOSITION_HEADER = "Content-Disposition";

    public ResponseEntity<Object> getServiceResponse(Job job, byte[] response) {
        return ResponseEntity.ok()
                .headers(constructHttpHeaders(job.getText(), job.getFormat()))
                .contentLength(response.length)
                .contentType(job.getFormat().getMediaType())
                .body(response);
    }

    private HttpHeaders constructHttpHeaders(String text, Job.Format format) {
        final String contentDispositionHeaderValue = "attachment;"+String.format("filename=%s.%s", text, format.getFormat());

        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_DISPOSITION_HEADER, contentDispositionHeaderValue);

        return headers;
    }
}