import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Chatserver extends Thread
{
    public static final int PORT_NUMBER = 45435;
    protected Socket socket;

    public static void main(String args[])
    {
        ServerSocket server = null;
        try
        {
            server  = new ServerSocket(PORT_NUMBER);
            while(true)
            {
                new Chatserver(server.accept());
                System.out.println("Client connected");
            }
        }
        catch(IOException ex)
        {
            System.out.println("Unable to start server or accept connections");
            System.exit(1);
        }
        finally
        {
            try{
                server.close();
            }
            catch(IOException ex)
            {

            }
        }
    }
    private Chatserver(Socket socket)
    {
        this.socket = socket;
        start();
    }
    public void run()
    {
        InputStream in = null;
        OutputStream out = null;
        try{
            Scanner sscn = new Scanner(System.in);
            in = socket.getInputStream();
            out =  socket.getOutputStream();
            DataInputStream din = new DataInputStream(in);
            DataOutputStream dout = new DataOutputStream(out);
            while(true){
                String s = din.readUTF();
                System.out.println(s);
                System.out.println("enter text: ");
                String inputs = sscn.nextLine();
                dout.writeUTF(inputs);
            }
        }
        catch(IOException ex)
        {
            System.out.println("unable to get streams from client");
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