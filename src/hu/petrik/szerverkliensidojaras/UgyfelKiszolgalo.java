package hu.petrik.szerverkliensidojaras;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class UgyfelKiszolgalo implements Runnable {
    private Socket kapcsolat;
    private HashMap<String, Idojaras> elorejelzesek;
    private HashMap<String, Idojaras> legalacsonyabbMaiNulla;
    private HashMap<String, Idojaras> holnapNapsutes;

    public UgyfelKiszolgalo(Socket kapcsolat) {
        this.kapcsolat = kapcsolat;
        elorejelzesek = new HashMap<>();
        legalacsonyabbMaiNulla = new HashMap<>();
        holnapNapsutes = new HashMap<>();
    }

    @Override
    public void run() {
        try {
            DataInputStream ugyfeltol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream ugyfelnek = new DataOutputStream(kapcsolat.getOutputStream());

            int menu;
            beolvas();

            do {
                menu = ugyfeltol.readInt();

                switch (menu){
                    case 0: ugyfelnek.writeUTF("Kilépett!");
                        break;
                    case 1: ugyfelnek.writeUTF(kiir1());
                        break;
                    case 2: ugyfelnek.writeUTF(kiir2());
                        break;
                    case 3: ugyfelnek.writeUTF(kiir3());
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                }

                ugyfelnek.flush();
            } while (menu != 0);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void beolvas() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("weather.txt"));

            br.readLine();
            String sor = br.readLine();

            while (sor != null) {
                Idojaras i = new Idojaras(sor);
                String megye = i.getMegye();
                elorejelzesek.put(megye, i);

                if (i.getMai().getMin() == 0) {
                    legalacsonyabbMaiNulla.put(megye, i);
                }

                if (i.getHolnapi().getSzovegesElorejelzes() == "Sunny") {
                    holnapNapsutes.put(megye, i);
                }

                sor = br.readLine();
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String kiir1() {
        String s = "";
        for (Map.Entry<String, Idojaras> entry: elorejelzesek.entrySet()) {
            s += entry.getValue() + "\n";
        }
        return s;
    }

    public String kiir2() {
        String s = "";
        for (Map.Entry<String, Idojaras> entry: legalacsonyabbMaiNulla.entrySet()) {
            s += entry.getValue() + "\n";
        }
        return s;
    }

    public String kiir3() {
        String s = "";
        for (Map.Entry<String, Idojaras> entry: holnapNapsutes.entrySet()) {
            s += entry.getValue() + "\n";
        }
        return s;
    }
}
