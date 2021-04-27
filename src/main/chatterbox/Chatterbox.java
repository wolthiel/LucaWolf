import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Chatterbox {

    public static void main (String args[]) throws IOException {

        Prelab reader = new Prelab();

        Socket socket = new Socket();

        //System.out.println(socket = reader.createNewConnection("192.168.64.2", 80));
        // System.out.println(reader.readFromConnection(socket));
        //System.out.println(reader.writeToConnection(socket));

        System.out.println(reader.setupLocalServer(80));


        /*
                ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(client.readMessageRunnable, 0, 200,
                TimeUnit.MILLISECONDS);
        Runnable readMessageRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (bufferedReader.ready()) {
                        String message = readMessage();
                        if (!message.isEmpty() && message != null) {
                            System.out.println(message);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
         */

        socket.close();
    }

    /**
     * Creates a new connection to given host, port
     * @param host
     * @param port
     *
     * @return Socket, if connection was successful
     * @return null if connection was not successful
     */
    public Socket createNewConnection(String host, int port) {

        try {
            System.out.println("Create new connection, please wait...");
            Socket socket = new Socket(host, port);
            System.out.println("New connection created...");

            return socket;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * Reads a output from a given host.
     * @param socket a socket object to read
     * @throws IOException
     * @return true if writing was successful
     */
    public boolean readFromConnection(Socket socket) {

        try {

            InputStream inStream = socket.getInputStream();
            Scanner scanner = new Scanner(inStream);

            System.out.println("Response: " + scanner.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }

    /**
     * Writes a output to given host.
     * @param socket a socket object to write
     * @throws IOException
     * @return true if writing was successful
     */
    public boolean writeToConnection(Socket socket) {

        try{

            OutputStream outStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outStream);

            String test = "Hello";

            System.out.println("Sending:" + test);
            out.print(test + "\n");
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }


    /**
     * Creates a local server
     * @param port
     * @return
     * @throws IOException
     */
    public boolean setupLocalServer(int port) throws IOException {

        ServerSocket server = new ServerSocket(port);
        System.out.println("Waiting for clients to connect...");

        while (true)
        {
            Socket s = server.accept();
            System.out.println("Client connected.");


        }

    }


    /**
     * writes a message to server
     * @param socket
     * @param line
     */
    public void writeMessage(Socket socket, String line) {

    }

    /**
     * waits for input from server
     * @param socket
     */
    void waitForInput(Socket socket) {
        System.out.println("Use the console to send messages.");
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                String line = scanner.nextLine();
                try {
                    writeMessage(socket, line);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (IllegalStateException e) {
            // System.in has been closed
            e.printStackTrace();
        }
    }




}
