import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client implements Runnable{
    InetAddress hostIP;
    int hostPort;

    private ArrayList<PhysicsObject2D> gameObjects;

    public Client(){
        try {
            System.out.print("Enter the hosts IP address : ");
            BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));

            hostIP = InetAddress.getByName(inFromUser.readLine());

            System.out.print("Enter the hosts port : ");
            inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));

            hostPort = Integer.parseInt(inFromUser.readLine());

            System.out.println(hostIP.getHostAddress());
            System.out.println(hostPort);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void run() {
        try {
            byte[] receiveData = new byte[1024];
            byte[] sendData;

            DatagramSocket clientSocket = new DatagramSocket();

            sendData = handleSend();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, hostIP, hostPort);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            handleReceive(receivePacket.getData());

            clientSocket.close();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return;
    }

    public void handleReceive(byte[] data) throws Exception{
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInput in = null;
        ArrayList<double[]> inputData;
        try {
            in = new ObjectInputStream(bis);
            inputData = (ArrayList<double[]>) in.readObject();

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }

        for (int i = 0; i < inputData.size(); i++){
            if (i != 1) {
                gameObjects.get(i).setTransmitData(inputData.get(i));
            }
        }

    }

    public byte[] handleSend() throws Exception{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] byteData;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(gameObjects.get(1).getTransmitData());
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

    public void setGameObjects(ArrayList<PhysicsObject2D> objects){
        gameObjects = objects;
    }
}
