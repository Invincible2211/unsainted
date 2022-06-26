package de.prog2.dungeontop.control.network;

import java.io.*;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTunnel extends Thread{
    private InputStream inputStream;
    private OutputStream outputStream;

    private int localPort;
    private int remotePort;
    private String remoteHost;
    private ServerSocket serverSocket;
    private Socket remote;

    public TCPTunnel(int localPort, String remoteHost, int remotePort){
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
        this.localPort = localPort;
        this.start();
    }

    @Override
    public void run() {
        try
        {
            TCPHole tcpHole = new TCPHole();
            tcpHole.start();
            System.out.println("Listening on local Port "+localPort);
            serverSocket = new ServerSocket(localPort);
            Socket clientConnection = serverSocket.accept();
            System.out.println("Accepted Connection");
            inputStream = clientConnection.getInputStream();
            outputStream = clientConnection.getOutputStream();
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
            System.err.println("TCPTunnel stopped");
        }
    }

    private class TCPHole extends Thread{

        @Override
        public void run() {
            try {
                System.out.println("Create RemoteSocket to "+ remoteHost +" : "+ remotePort);
                remote = new Socket(remoteHost,remotePort);
                PrintStream printStream = new PrintStream(remote.getOutputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(remote.getInputStream()));
                while (true){
                    printStream.println("PING");
                    String line = reader.readLine();
                    while (line!=null){
                        System.out.println(line);
                        line = reader.readLine();
                    }
                    sleep(1000);
                }
            } catch (IOException | InterruptedException e) {
                //e.printStackTrace();
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
