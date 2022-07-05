package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.resources.NetworkingConstants;
import de.prog2.dungeontop.utils.GlobalLogger;

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
            // TODO : Remove magic?
            Socket socket = new Socket();
            GlobalLogger.log(NetworkingConstants.SOCKET_CREATED);
            socket.bind(new InetSocketAddress(12345));
            GlobalLogger.log(NetworkingConstants.BIND_SOCKET);
            socket.connect(new InetSocketAddress(remoteHost,12345));
            GlobalLogger.log(NetworkingConstants.SOCKET_CONNECTED);
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
                    GlobalLogger.log(NetworkingConstants.PING_MESSAGE);
                }
            };
            timer.scheduleAtFixedRate(timerTask, 0L,1000L);
            while (true){
                try {
                    GlobalLogger.log(reader.readLine());
                } catch (IOException e) {
                    GlobalLogger.log(e.getMessage());
                }
            }
        }
        catch(Exception e)
        {
            // TODO : ADD LOGGER?
            e.printStackTrace(System.err);
            GlobalLogger.warning(NetworkingConstants.TCP_TUNNEL_STOPPED);
        }
    }

    // TODO : REMOVE MAGIC
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
                GlobalLogger.warning(e.getMessage());
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
