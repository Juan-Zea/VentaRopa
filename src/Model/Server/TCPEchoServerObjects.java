package Model.Server;

import Model.Entity.Inventario;
import Model.Entity.InventarioDao;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class TCPEchoServerObjects {

    private              ServerSocket servSock;
    private static final int          PORT = 1234;

    public static void main(String[] args) {
        new TCPEchoServerObjects();
    }

    public TCPEchoServerObjects() {
        try {
            servSock = new ServerSocket(PORT);
            System.out.println("ServerSocket created");
        } catch (SocketException e) {
            System.out.println("Error attach Port!!!!");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error create Socket");
            System.exit(1);
        }
        do {
            run();
        } while (true);
    }

    private void run() {
        Socket sock = null;
        try {
            sock = servSock.accept();
            System.out.println("Client Connected");
            OutputStream outputStream = sock.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outputStream);

            InputStream inputStream = sock.getInputStream();
            ObjectInputStream in = new ObjectInputStream(inputStream);

            List<Inventario> inventarioList = (List<Inventario>) in.readObject();
            inventarioList.forEach( inventario-> {
                System.out.println(inventario);
                inventario.setMessage("Processed by server");
            });
            InventarioDao inventarioDao = new InventarioDao();
            inventarioList.forEach( inventario -> {
                inventarioDao.insert(inventario);
            });
            System.out.println("Send students===>");
            out.writeObject(inventarioList);

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
