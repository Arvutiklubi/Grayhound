import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Host implements Runnable {
    private ArrayList<PhysicsObject2D> gameObjects;

    public void run(){
        try {
            DatagramSocket serverSocket = new DatagramSocket(35000);
            byte[] receiveData = new byte[1024];
            byte[] sendData;

            while (true) {
                // Receive a packet
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                // Get recipient's address and port
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                // Handle received data (add to game object list)

                handleReceive(receivePacket.getData());

                sendData = handleSend();
                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);

        }
    }

    public void handleReceive(byte[] data) {

    }

    public byte[] handleSend() {
        return new byte[1024];
    }

    public void setGameObjects(ArrayList<PhysicsObject2D> objects) {
        gameObjects = objects;
    }

}
