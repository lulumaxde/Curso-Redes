package Socket;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		try {
			//Declarando e criando o socket com a porta desejada
			Socket cliente = new Socket("localhost", 7000);
			
			Scanner teclado = new Scanner(System.in);
			Scanner chegada = new Scanner(cliente.getInputStream());
			
			//Declarando e criando  o fluxo de dados de saida
			PrintStream saida = new PrintStream(cliente.getOutputStream());
			
			String msg = "";
			do {
				
				System.out.println("Informe a mensagem a ser enviada: ");
				msg = teclado.nextLine();
				saida.println(msg);
				// Mandei A mensagem para o servidor
				String resposta = chegada.nextLine();
				System.out.println("Cliente Mensagem: ("+ msg +")");
				System.out.println("Servidor Mensagem Resposta:  ("+ resposta +")");
				System.out.println("/------------------------------------------/ ");
				
			}while (msg.length() != 0 );
			
			
			cliente.close();
			
		}
		catch(Exception e) {
			System.out.println("Problema na criacao de conexão com o servidor");
		}

	}

}