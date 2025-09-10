package view;

import java.util.Scanner;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		RedesController redes = new RedesController();
		Scanner in = new Scanner(System.in);
		int escolha = 0;
		
		System.out.println("Escolha a opção desejada: "
				+ "\n1- Ver IPv4 e Adaptador"
				+ "\n2- Ver teste de ping"
				+ "\n3- Finalizar iteração\n");
		do {
		escolha = in.nextInt();
		
			switch (escolha) {
			case 1: 
				redes.ip();
				break;
			case 2: 
				redes.ping();
				break;
			case 3: 
				System.out.println("Finalizando...");
				break;
			default:
				System.out.println("Escolha invalida!");
				break;
			}
			
			if(escolha != 3) {
				System.out.println("\nDeseja ver mais alguma opção? (se nn, digite opção 3)");
			}
		} while(escolha != 3);
	}

}
