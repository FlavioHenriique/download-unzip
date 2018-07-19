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

    public Downloads() throws IOException {

        this.link = "http://www.portaltransparencia.gov.br/download-de-dados" +
                "/despesas/";

    }

    public  void baixarTodos(String ano, String mes, int dias) throws IOException {


        for(int k =1; k <= dias; k ++){

            String concatenar =  ano + mes + "0"+ k;
            System.out.println(link + concatenar);
            this.url = new URL(link + concatenar);

            ReadableByteChannel chanel = Channels.newChannel(url.openStream());

            String nome = "dados/"+ano+mes+ "0" + k+".zip";
            System.out.println("nome: "+ nome);
            FileOutputStream stream = new FileOutputStream(nome);
            stream.getChannel().transferFrom(chanel, 0, Long.MAX_VALUE);

            System.out.println("Download concluido");

            extrairTodos(nome, "dados/"+concatenar);
        }

    }

    public void extrairTodos(String nome, String pasta) throws IOException {

        byte[] bytes = new byte[1024];
        File file = new File("dados/");
        if(!file.exists()){
            file.mkdir();
        }

        ZipInputStream zip = new ZipInputStream(new FileInputStream(nome));
        ZipEntry entry = zip.getNextEntry();

        while(entry != null){
            String filename = entry.getName();
            File arquivo = new File(pasta + File.separator + filename);
            new File(arquivo.getParent()).mkdirs();

            FileOutputStream out = new FileOutputStream(arquivo);
            int len;

            while((len = zip.read(bytes)) > 0){
                out.write(bytes,0,len);
            }
            out.close();
            entry = zip.getNextEntry();
        }
        zip.closeEntry();
        zip.close();

        System.out.println("Extraído");

        File arquivoExcluir = new File(nome);
        arquivoExcluir.delete();
    }
}
