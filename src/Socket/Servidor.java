package Socket;

import java.net.ServerSocket;

public class Servidor {

	public static void main(String[] args) {
		try {
		ServerSocket servidor = new ServerSocket(7000);
		System.out.println("Servidor iniciado ...");
		
		while(true) {
			
			RespostaCliente ac = new RespostaCliente(servidor.accept());
			ac.start();
		}
		
		
		
		}
		catch(Exception e) {
			
		}
		

	}

}
