package de.prog2.dungeontop.resources;

public interface NetworkingConstants
{
    String NETWORK_CONNECTING_TO = "Connecting to %s...";
    String TEST_PUBLIC_ADDRESS_URL = "http://checkip.amazonaws.com";
    String TEST_PUBLIC_DNS_IP = "8.8.8.8";
    int TEST_PUBLIC_DNS_PORT = 10002;

    int WAITING_TIME_FOR_CONNECTION = 1000;
    String WAITING_FOR_CONNECTION = "Waiting for connection...";
    String CONNECTED = "Connected!";
    String SENDS_HELL_DATA = "Sending hell data...";

    String SENDING = "Sending %d";

    String START_INTERPRETING = "Start interpreting...";
    String WAITING_FOR_DATA = "Waiting for data...";
    String BYTES_TO_READ = "Bytes to read: %d";
    String RECEIVED_DATA = "Received %d bytes.";
    String INTERPRETING_DATA = "Interpreting data...";

    String BYTES_TO_INTERPRET = "Bytes to interpret: %d";

    String WAITTNG = "Waiting...";

    String SOCKET_CREATED = "Socket created.";
    String BIND_SOCKET = "Bind socket.";
    String SOCKET_CONNECTED = "Socket connected.";
    String PING_MESSAGE = "PING.";
    String TCP_TUNNEL_STOPPED = "TCP tunnel stopped.";

    String SETTINGS_IP_LABEL = "IPAdresse: %s";
}
