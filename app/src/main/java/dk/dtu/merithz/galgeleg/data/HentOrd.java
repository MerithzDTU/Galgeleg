package dk.dtu.merithz.galgeleg.data;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HentOrd {

    public static String hentUrl(String url) throws IOException {
        System.out.println("Henter data fra " + url);
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        StringBuilder sb = new StringBuilder();
        String linje = br.readLine();
        while (linje != null) {
            sb.append(linje + "\n");
            linje = br.readLine();
        }
        return sb.toString();
    }

    /**
     * Hent ord og sværhedsgrad fra et online regneark. Du kan redigere i regnearket, på adressen
     * https://docs.google.com/spreadsheets/d/1RnwU9KATJB94Rhr7nurvjxfg09wAHMZPYB3uySBPO6M/edit?usp=sharing
     *
     * @param sværhedsgrader en streng med de tilladte sværhedsgrader - f.eks "3" for at medtage kun svære ord, eller "12" for alle nemme og halvsvære ord
     * @throws Exception
     */

    public List<String> hentOrdFraRegneark(String sværhedsgrader) throws Exception {
        String id = "1RnwU9KATJB94Rhr7nurvjxfg09wAHMZPYB3uySBPO6M";

        System.out.println("Henter data som kommasepareret CSV fra regnearket https://docs.google.com/spreadsheets/d/" + id + "/edit?usp=sharing");

        String data = hentUrl("https://docs.google.com/spreadsheets/d/" + id + "/export?format=csv&id=" + id);
        int linjeNr = 0;
        List<String> muligeOrd = new ArrayList<>();

        for (String linje : data.split("\n")) {
            if (linjeNr < 20)
                System.out.println("Læst linje = " + linje); // udskriv de første 20 linjer
            if (linjeNr++ < 1) continue; // Spring første linje med kolonnenavnene over
            String[] felter = linje.split(",", -1);// -1 er for at beholde tomme indgange, f.eks. bliver ",,," splittet i et array med 4 tomme strenge
            String sværhedsgrad = felter[0].trim();
            String ordet = felter[1].trim().toLowerCase();
            if (sværhedsgrad.isEmpty() || ordet.isEmpty())
                continue; // spring over linjer med tomme ord
            if (!sværhedsgrader.contains(sværhedsgrad)) continue; // filtrér på sværhedsgrader
            System.out.println("Tilføjer " + ordet + ", der har sværhedsgrad " + sværhedsgrad);
            muligeOrd.add(ordet);
        }
        return muligeOrd;
    }
}
