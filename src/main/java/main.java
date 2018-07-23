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
            for (int k = 22; k <= 30; k ++){
                downloads.baixarDia("2018", "06", k);
                }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
