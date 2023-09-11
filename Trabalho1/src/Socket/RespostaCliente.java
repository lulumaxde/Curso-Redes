package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class RespostaCliente extends Thread{

	Socket cliente;
	
	RespostaCliente(Socket cli){
		this.cliente = cli;
		
	}
	
	@Override
	public void run() {
		System.out.println("Cliente conectado com thread ("+this.getId()+ ") : " + cliente.getInetAddress());
		Scanner teclado = new Scanner(System.in);
		Scanner chegada;// cliente esta digitando ou vai digitar
		
		
		
		InputStreamReader fluxoDados;
		
		try {
			
			chegada = new Scanner(cliente.getInputStream());
			PrintStream saida = new PrintStream(cliente.getOutputStream());
			
			while(chegada.hasNextLine()) {
					String msgChegadaCliente = chegada.nextLine();
					System.out.println("Mensagem Cliente: ("+msgChegadaCliente+") ");
					System.out.println("Servidor:");
					String msgResposta = teclado.nextLine();
					saida.println(msgResposta);
					System.out.println("/------------------------------------------/ ");
					
			}
			
			
			System.out.println("Cliente finalizado: " + cliente.getInetAddress() + " da ");
			
			cliente.close();
			
			
		} catch(IOException e) {
			e.printStackTrace();
		} 
		
	}
	
}