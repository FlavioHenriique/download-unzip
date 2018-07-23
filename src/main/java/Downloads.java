import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Downloads {

    private URL url;
    private String link;
    private File pasta;

    public Downloads() throws IOException {

        this.link = "http://www.portaltransparencia.gov.br/download-de-dados" +
                "/despesas/";
    }

    public void baixarDia(String ano, String mes, int dia) {

        pasta = new File("../dados/" + ano + mes);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        String concatenar = ano + mes;
        concatenar += (dia < 10) ? "0" + dia : dia;

        try {
            this.url = new URL(link + concatenar);
            ReadableByteChannel chanel = Channels.newChannel(url.openStream());

            String nome = pasta.getPath() + "/" + concatenar + ".zip";
            System.out.println("arquivo: " + nome);

            FileOutputStream stream = new FileOutputStream(nome);
            stream.getChannel().transferFrom(chanel, 0, Long.MAX_VALUE);
            System.out.println("Download concluido");

            extrair(nome, pasta.getPath() + "/" + concatenar);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getMessage();
            this.baixarDia(ano, mes, dia + 1);
        }


    }

    private void extrair(String nome, String pasta) throws IOException {

        byte[] bytes = new byte[1024];
        File file = new File(pasta);
        if (!file.exists()) {
            file.mkdir();
        }

        ZipInputStream zip = new ZipInputStream(new FileInputStream(nome));
        ZipEntry entry = zip.getNextEntry();

        // usando if para pegar apenas o primeiro arquivo (empenho), utiliza-se while caso queira todos
        if (entry != null) {

            String filename = entry.getName();
            File arquivo = new File(pasta + File.separator + filename);
            new File(arquivo.getParent()).mkdirs();

            FileOutputStream out = new FileOutputStream(arquivo);
            int len;

            while ((len = zip.read(bytes)) > 0) {
                out.write(bytes, 0, len);
            }
            out.close();
            entry = zip.getNextEntry();
        }
        zip.closeEntry();
        zip.close();

        System.out.println("Extraído "+ nome);

        File arquivoExcluir = new File(nome);
        arquivoExcluir.delete();
    }
}
