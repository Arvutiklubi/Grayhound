import java.io.*;
import java.net.*;

public class Client {
    InetAddress hostIP;
    InetAddress hostPort;

    public Client(){
        try {
            System.out.print("Enter the hosts IP address : ");
            BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));

            System.out.println(inFromUser.readLine());
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void receive() {

    }
}
