package Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor implements Runnable {

    private ArrayList<ConnectionHandler> connections;
    private ServerSocket server;
    private boolean status;
    private ExecutorService pool;

    public Servidor() {
        connections = new ArrayList<>();
        status = false;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(9999);

            pool = Executors.newCachedThreadPool();
            BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
            while (!status) {
                Socket client = server.accept();

                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);

                pool.execute(handler);
            }
        } catch (IOException e) {
             
        }
    }

    public void broadcast(String message) {
        for (ConnectionHandler ch: connections) {
            if (ch != null) {
                ch.sendMessage(message);

            }
        }
    }



    class ConnectionHandler implements Runnable {

        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private String nickname;
        
        @Override
        public void run() {
            try {
	                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	                out = new PrintWriter(client.getOutputStream(), true);
	
	                out.println("Insira seu nome: ");
	                nickname = in.readLine();
	
	                System.out.println(nickname + " conectado!");
	                broadcast(nickname + " conectou no chat!");
	
	                String message;
	                while ((message = in.readLine()) != null) {  
	                		broadcast(nickname + ": " + message);                 
	                }
            	} catch (Exception e) {
                
            	}
        }
        
        public ConnectionHandler(Socket client) {
            this.client = client;
        }

        public void sendMessage(String message) {
            out.println(message);
        }


        
    }

    public static void main(String[] args) {
        Servidor server = new Servidor();
        server.run();
    }
}

