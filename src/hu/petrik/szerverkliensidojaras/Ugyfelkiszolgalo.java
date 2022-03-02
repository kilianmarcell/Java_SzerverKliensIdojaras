package hu.petrik.szerverkliensidojaras;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Ugyfelkiszolgalo implements Runnable {
    private HashMap<String, Idojaras> elorejelzesek;

    public Ugyfelkiszolgalo() {
        elorejelzesek = new HashMap<>();
    }

    @Override
    public void run() {

    }

    public void beolvas() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("weather.txt"));

            String sor = br.readLine();
            while (sor != null) {
                Idojaras i = new Idojaras(sor);
                String megye = i.getMegye();
                elorejelzesek.put(megye, i);

                br.readLine();
            }

            for (Map.Entry<String, Idojaras> entry: elorejelzesek.entrySet()) {
                System.out.println(entry.getValue());
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
