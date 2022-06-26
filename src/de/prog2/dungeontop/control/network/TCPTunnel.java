package de.prog2.dungeontop.control.network;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

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
    }

    @Override
    public void run() {
        try
        {
            Socket socket = new Socket();
            System.out.println("Created socket");
            socket.bind(new InetSocketAddress(12345));
            System.out.println("Bind Socket");
            socket.connect(new InetSocketAddress(remoteHost,12345));
            System.out.println("Connected Socket");
            /*
            TCPHole tcpHole = new TCPHole();
            tcpHole.start();
            serverSocket = new ServerSocket(localPort);
            System.out.println("Listening on local Port "+localPort);
            Socket clientConnection = serverSocket.accept();
            System.out.println("Accepted Connection");
            inputStream = clientConnection.getInputStream();
            outputStream = clientConnection.getOutputStream();
             */
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            PrintStream printStream = new PrintStream(outputStream);
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    printStream.println("PING");
                }
            };
            timer.scheduleAtFixedRate(timerTask, 0L,1000L);
            while (true){
                try {
                    System.out.println(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
                remote = new Socket(remoteHost,remotePort);
                System.out.println("Create RemoteSocket to "+ remoteHost +" : "+ remotePort);
                PrintStream printStream = new PrintStream(remote.getOutputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(remote.getInputStream()));
                if (remote!=null){
                    while (true){
                        printStream.println("PING");
                        String line = reader.readLine();
                        while (line!=null){
                            System.out.println(line);
                            line = reader.readLine();
                        }
                        sleep(1000);
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
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
