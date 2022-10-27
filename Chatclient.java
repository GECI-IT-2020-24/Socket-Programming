import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Chatclient
{
    public static void main(String args[])
    {
        new Chatclient(args[0]);
    }
    public Chatclient(String host)
    {
        Socket socket;
        try{
            socket = new Socket(host,Chatserver.PORT_NUMBER);
            System.out.println("Connected to server");
        }
        catch(UnknownHostException ex)
        {
            System.out.println(host+" is not a valid host name");
            return;
        }
        catch(IOException ex)
        {
            System.out.println("error communicating with "+host);
            return;
        }
        InputStream in = null;
        OutputStream out = null;
        try{
            Scanner cscn = new Scanner(System.in);
            in = socket.getInputStream();
            out = socket.getOutputStream();
            DataInputStream din = new DataInputStream(in);
            DataOutputStream dout = new DataOutputStream(out);
            while(true)
            {
                String inputc = cscn.nextLine();
                dout.writeUTF(inputc);
                System.out.println("enter msg :");
                String sr = din.readUTF();
                System.out.println(sr);
            }
        }
        catch(IOException ex)
        {

        }
        finally 
        {
            try{
                in.close();
                out.close();
                socket.close();
            }
            catch(IOException ex)
            {
                
            }
        }
    }
}