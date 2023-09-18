package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean status;

    @Override
    public void run() {
        try {
            client = new Socket("localhost", 9999);

            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            InputHandler inHandler = new InputHandler();
            Thread t = new Thread(inHandler);
            t.start();

            String inMessage;
            while ((inMessage = in.readLine()) != null) {
                System.out.println(inMessage);
            }
        } catch (IOException e) {
        	System.out.println("Problema na criação da conexao com o servidor");
        }
    }


    class InputHandler implements Runnable {

        @Override
        public void run() {
            try {
                	BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));

	                while (!status) {
	                    String message = inReader.readLine();
	                    out.println(message);                    
	                }
            	} catch (IOException e) {
               
            	}
        }
    }

    public static void main(String[] args) {
        Cliente client = new Cliente();
        client.run();
    }
}

