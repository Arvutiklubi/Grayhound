import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Host implements Runnable {
    InetAddress hostIP;
    int hostPort;

    private ArrayList<PhysicsObject2D> gameObjects;

    public Host(){
        try {
            System.out.print("Enter your IP address : ");
            BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));

            hostIP = InetAddress.getByName(inFromUser.readLine());

            System.out.print("Enter your port : ");
            inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));

            hostPort = Integer.parseInt(inFromUser.readLine());

            System.out.println(hostIP.getHostAddress());
            System.out.println(hostPort);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void run(){
        try {
            DatagramSocket serverSocket = new DatagramSocket(hostPort, hostIP);
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

    public void handleReceive(byte[] data) throws Exception{
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInput in = null;
        double[] inputData;
        try {
            in = new ObjectInputStream(bis);
            inputData = (double[]) in.readObject();

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }

        gameObjects.get(1).setTransmitData(inputData);

    }

    public byte[] handleSend() throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] byteData;

        ArrayList<double[]> data = new ArrayList<>();
        for (PhysicsObject2D p : gameObjects) {
            data.add(p.getTransmitData());
        }

        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(data);
            out.flush();
            byteData = bos.toByteArray();

        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        return byteData;
    }

    public void setGameObjects(ArrayList<PhysicsObject2D> objects) {
        gameObjects = objects;
    }

}
