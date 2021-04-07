package Model.Client;

import Model.Entity.Inventario;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class TCPEchoClientObject {

    private              InetAddress host;
    private static final int         PORT = 1234;

    public static void main(String[] args) {
        new TCPEchoClientObject();
    }

    public TCPEchoClientObject() {
        System.out.println("Opening port");
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println("Host not found!");
            System.exit(1);
        }
        run();
    }

    private void run() {
        Socket sock = null;
        try {
            sock = new Socket(host, PORT);
            OutputStream outputStream = sock.getOutputStream();
            ObjectOutputStream       out          = new ObjectOutputStream(outputStream);

            InputStream inputStream = sock.getInputStream();
            ObjectInputStream in = new ObjectInputStream(inputStream);

            List<Inventario> inventarioList= List.of(
                new Inventario(3l,"Blusa",200,"Poner en saco","XXl"),
                new Inventario(4l,"Blusa",200,"Poner en saco","XXl"),
                new Inventario(5l,"Blusa",200,"Poner en saco","XXl")
            );

            System.out.println("Sending students");
            out.writeObject(inventarioList);
            inventarioList = (List<Inventario>) in.readObject();
            inventarioList.forEach(System.out::println);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
