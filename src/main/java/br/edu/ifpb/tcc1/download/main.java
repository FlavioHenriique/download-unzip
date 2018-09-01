    package br.edu.ifpb.tcc1.download;

import java.io.IOException;
import java.time.Month;
import java.time.YearMonth;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {

    public static void main(String[] args) {

        YearMonth yearMonth;
        int ano = 2013;
        try {
            DownloadCSV downloads = new DownloadCSV();

            for (int k = 1; k <= 12; k++) {

                yearMonth = YearMonth.of(ano, k);
                String mes = (k < 10) ? "0" + k : "" + k;

                for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
                    downloads.baixarDia(ano + "", mes, i);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
