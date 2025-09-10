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
	
	//Maquina teste (LINUX MINT)
	public void ip() {
		String name = os();
		String ip, adapt;
		String[] command = new String[1];
		
		if(name.contains("Linux")) {
			command[0] = "ifconfig";
			try {
				Process p = Runtime.getRuntime().exec(command); //Interação com o SO e execução de um comando
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
					command[0] = "ip addr";
					try {
						Process p = Runtime.getRuntime().exec(command);
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
			command[0] = "ipconfig";
			try {
				Process p = Runtime.getRuntime().exec(command);
				InputStream stream = p.getInputStream();
				InputStreamReader rd = new InputStreamReader(stream);
				BufferedReader buffer = new BufferedReader(rd);
				
				String line = buffer.readLine();
				
				while(line != null) {
					if(line.contains("Adaptador")) {
						adapt = line.trim();
						
						System.out.println(adapt);
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
	
	
	public void ping() {
		String name = os();
		String media = " ";
		
		if(name.contains("Windows")) {
			String[] commandPingWin = {"ping", " ", "-4", " ", "-n", " ", "10", " ", "www.google.com.br"};
			
			try {
				Process p = Runtime.getRuntime().exec(commandPingWin);
				InputStream stream = p.getInputStream();
				InputStreamReader rd = new InputStreamReader(stream);
				BufferedReader buffer = new BufferedReader(rd);
				
				String line = buffer.readLine();
				
				while(line != null) {
					if(line.contains(", ")) {
						media = line.trim().split(", ")[2];
						media = media.split("= ")[1];
						System.out.println("Ping no www.google.com.br");
						System.out.println("A media foi de: " + media);
					}
					
					line = buffer.readLine();
				}
				
				stream.close();
				rd.close();
				buffer.close();
				
			} catch (IOException err) {
				System.err.println(err.getMessage());
			}
			
		} else if(name.contains("Linux")) {
			String[] commandPingLin = {"ping", "-4", "-c", "10", "www.google.com.br"};
			
			try {
				Process p = Runtime.getRuntime().exec(commandPingLin);
				InputStream stream = p.getInputStream();
				InputStreamReader rd = new InputStreamReader(stream);
				BufferedReader buffer = new BufferedReader(rd);
				
				String line = buffer.readLine();
				
				while(line != null) {
					if(line.contains("avg")) {
						media = line.trim().split("= ")[1];
						media = media.split("/")[1];
						System.out.println("Ping no www.google.com.br");
						System.out.println("A media foi de: " + media + " ms");
					}
					
					line = buffer.readLine();
				}
				
				stream.close();
				rd.close();
				buffer.close();
				
			} catch (IOException err2) {
				System.err.println(err2.getMessage());
			}
			
		} else {
			System.out.println("Sistema não identificado!");
		}
	}
}
