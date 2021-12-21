/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Buloto
 */
public class UDPServer {

    /**
     * @param args the command line arguments
     */
    static final int PORT = 1234;
    private DatagramSocket socket = null;
    public UDPServer()
    {
        try
        {
            socket = new DatagramSocket(PORT);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }    
    
    public void action()
    {
        InetAddress host = null;
        int port;
        String outputstring = "";
        try
        {
            System.out.println("Server is waitting");
            while(true)
            {
                DatagramPacket packet = receive();
                host = packet.getAddress();
                port = packet.getPort();
                outputstring = new String(packet.getData()).trim();
                outputstring = outputstring.toUpperCase();
                if(!outputstring.equals(""))
                {
                    send(outputstring, host, port);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            socket.close();
        }
    }
    
    private DatagramPacket receive() throws IOException {
        byte[] buffer = new byte[65507];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        return packet;
    }

    private void send(String outputstring, InetAddress host, int port) throws IOException {
        byte[] buffer = outputstring.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, host, port);
        socket.send(packet);
    }
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        new UDPServer().action();
    }

    
    
}
