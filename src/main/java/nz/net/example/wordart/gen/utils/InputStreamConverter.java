package nz.net.example.wordart.gen.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamConverter {

    private static final int DEFAULT_BUFFER_SIZE = 3000;

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        final int bufferLength = buffer.length;
        int startOffset = 0;
        int bytesRead;

        IOException exception = null;
        try {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                while ((bytesRead = inputStream.read(buffer, startOffset, bufferLength)) != -1)
                    outputStream.write(buffer, startOffset, bytesRead);

                return outputStream.toByteArray();
            }
        } catch (IOException e) {
            exception = e;
            throw e;
        } finally {
            if (exception == null)
                inputStream.close();
            else try {
                inputStream.close();
            } catch (IOException e) {
                exception.addSuppressed(e);
            }
        }
    }
}