package nz.net.example.wordart.gen.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InputStreamConverterTest {

    @Mock
    private InputStream inputStream;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void readInputStream_success() throws IOException {

        final String testStr = "Hello World!";
        InputStream anyInputStream = new ByteArrayInputStream(testStr.getBytes());

        byte[] result = InputStreamConverter.readInputStream(anyInputStream);

        assertEquals(new String(result), testStr);
    }

    @Test
    public void inputSteamReadShouldThrowIOException() throws IOException {
        when(inputStream.read(any(), anyInt(), anyInt())).thenThrow(new IOException());
        exception.expect(IOException.class);

        InputStreamConverter.readInputStream(inputStream);
    }

    @Test
    public void inputStreamCloseThrowsIOException() throws IOException {
        when(inputStream.read(any(), anyInt(), anyInt())).thenThrow(new IOException());
        doThrow(IOException.class).when(inputStream).close();
        exception.expect(IOException.class);

        InputStreamConverter.readInputStream(inputStream);
    }
}