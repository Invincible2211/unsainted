package de.prog2.dungeontop.control.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTunnel extends Thread{
    private String remoteHost;
    private int remotePort;

    InputStream inputStream;
    OutputStream outputStream;

    private ServerSocket listener;

    public TCPTunnel(int localPort, String remoteHost, int remotePort, InputStream inputStream, OutputStream outputStream){
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
        try {
            this.listener = new ServerSocket(localPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try
        {
            System.err.println("TCPTunnel listening...");
            SocketTunnel tunnel = new SocketTunnel(listener.accept(), remoteHost, remotePort);
            tunnel.start();
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
            System.err.println("TCPTunnel stopped");
        }
    }

    private class SocketTunnel extends Thread {

        private Socket remoteSock, bounceSock;
        private String bounceHost;
        private int    bouncePort;
        public SocketTunnel(Socket remoteSock, String bounceHost, int bouncePort)
        {
            this.remoteSock = remoteSock;
            this.bounceHost = bounceHost;
            this.bouncePort = bouncePort;
        }

        public void run() {
            try
            {
                bounceSock = new Socket(bounceHost, bouncePort);
            }
            catch(Exception e){

            }
        }

    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
