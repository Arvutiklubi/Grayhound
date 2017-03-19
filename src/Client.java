import java.io.*;
import java.net.*;

public class Client implements Runnable{
    InetAddress hostIP;
    int hostPort;

    private PhysicsObject2D playerObject;

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

    public void handleReceive(byte[] data) {
    }

    public byte[] handleSend() throws Exception{
        return new byte[1024];
    }

    public void setPlayerObject(PhysicsObject2D player){
        playerObject = player;
    }
}
