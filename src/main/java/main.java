import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class main {

    public static void main(String[] args) {

        try {
            Downloads downloads = new Downloads();
            downloads.baixarTodos("2018","05",1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
