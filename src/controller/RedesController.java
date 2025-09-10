package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}

	private String os() {
		return System.getProperty("os.name");
	}
	

	public void ip() {
		String name = os();
		String ip, adapt;
		
		if(name.contains("Linux")) {
			String[] commandLin = {"ifconfig"};
			try {
				Process p = Runtime.getRuntime().exec(commandLin); //Interação com o SO e execução de um comando
				InputStream stream = p.getInputStream();
				InputStreamReader rd =  new InputStreamReader(stream);
				BufferedReader buffer = new BufferedReader(rd);
				
				String line = buffer.readLine();
				
				while(line != null) {
					if(line.contains("flags")) {
						adapt = line.trim().split("<") [0];
						
						if(!line.contains("LOOPBACK")) {
							System.out.println(adapt);
						}
					}
					
					if(line.contains("inet ")) {
						ip = line.trim().split(" ") [1];
						if(!ip.startsWith("127")) {
							
							System.out.println("inet4: "+ip);
						}
					}
					
					line = buffer.readLine();
				}
				
				stream.close();
				rd.close();
				buffer.close();
				
			} catch (IOException e) {
				String err = e.getMessage();
				System.err.println(err);
				
				if(err.contains("2")) {
					//Mostra todas as linhas de um linux que utiliza ip addr!
					String[] commandLin2 = {"ip addr"};
					try {
						Process p = Runtime.getRuntime().exec(commandLin2);
						InputStream stream = p.getInputStream();
						InputStreamReader rd =  new InputStreamReader(stream);
						BufferedReader buffer = new BufferedReader(rd);
						
						String line = buffer.readLine();
						
						while(line != null) {
							System.out.println(line);
							line = buffer.readLine();
						}
						
						stream.close();
						rd.close();
						buffer.close();
						
					} catch (Exception e1) {
						System.err.println(e.getMessage());
					}
				}
				
			}
			
		} else if(name.contains("Windows")) {
			String[] commandWin = {"ipconfig"};
			try {
				Process p = Runtime.getRuntime().exec(commandWin);
				InputStream stream = p.getInputStream();
				InputStreamReader rd = new InputStreamReader(stream);
				BufferedReader buffer = new BufferedReader(rd);
				
				String line = buffer.readLine();
				
				while(line != null) {
					if(line.contains("Adaptador")) {
						adapt = line.trim();
					}
					
					if(line.contains("IPv4")) {
						ip = line.trim().split(": ") [1];
						
						System.out.println("IPv4: " + ip);
					}
					line = buffer.readLine();
				}
				
				stream.close();
				rd.close();
				buffer.close();
				
			} catch (IOException e2) {
				System.err.println(e2.getMessage());
			}
			
		} else
			System.out.println("Não identificado");
	}
	
}
