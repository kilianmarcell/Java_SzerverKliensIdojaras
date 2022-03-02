package hu.petrik.szerverkliensidojaras;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Szerver {
    public static void main(String[] args) {
        ExecutorService exe = Executors.newCachedThreadPool();

        System.out.println("A szerver elindult");

        try {
            ServerSocket socket = new ServerSocket(8080);

            while (true) {
                Socket kapcsolat = socket.accept();
                InetAddress ugyfel = kapcsolat.getInetAddress();

                System.out.println("Ügyfél címe: " + ugyfel.getHostAddress());
                UgyfelKiszolgalo uk = new UgyfelKiszolgalo(kapcsolat);

                exe.submit(uk);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
