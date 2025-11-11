
import java.io.*;  
import java.net.*;   
import java.util.Scanner;

	  public class MultiEchoServer  
	   { 
	     private static ServerSocket serverSocket; 
	     private static final int PORT = 1234;    
	     
	     public static void main(String[] args) throws IOException 
	     { 
	    	 try 
	    	 { 
	    		 serverSocket = new ServerSocket(PORT); 
	    	 } 
	    	 catch (IOException ioEx) 
	    	 { 
	    		 System.out.println("\nTidak bisa men-set port!"); 
	    		 System.exit(1); 
	    	 }    
	    	 do 
	    	 { 
	    		 //menunggu client… 
	    		 Socket client = serverSocket.accept();    
	    		 System.out.println("\nClient baru diterima\n");   
	    		 //Membuat sebuah thread untuk menangani komunikasi dengan
	    		 //client ini dan melewatkan constructor untuk thread ini
	    		 //sebuah reference untuk socket yang relevan… 
	    		 ClientHandler handler = new ClientHandler(client); 
	    		 handler.start();//Seperti biasa, method memanggil  run  . 
	    	 } while (true); 
	     } 
	   }    
	  		class ClientHandler extends Thread 
	  		{ 
	  				private Socket client; 
	  				private Scanner input; 
	  				private PrintWriter output;    

	  				public ClientHandler(Socket socket) 
	  				{ 
	  					//Set up reference ke socket yang terkait… 
	  					client = socket;    
	  					try 
	  					{ 
	  						input = new Scanner(client.getInputStream()); 
	  						output = new PrintWriter(client.getOutputStream(),true); 
	  					} 
	  					catch(IOException ioEx)	     
	  					{ 
	  						ioEx.printStackTrace(); 
	  					} 
	  		     } 
	  				
	  		     public void run() 
	  		     { 
	  		        String received;    
	  		        do 
	  		        { 
	  		           //menerima pesan dari client melalui
	  		           //input stream socket… 
	  		           received = input.nextLine();    
	  		           //Mengembalikan pesan ke client melalui
	  		           //output stream socket
	  		           output.println("ECHO: " + received);    
	  		           //Mengulang di atas sampai 'QUIT' dikirimkan oleh client… 
	  		        }while (!received.equals("QUIT"));    
	  		        try 
	  		        { 
	  		           if (client!=null) 
	  		           { 
	  		              System.out.println( 
	  		                          "Closing down connection…"); 
	  		              client.close(); 
	  		           } 
	  		        } 
	  		        catch(IOException ioEx) 
	  		        { 
	  		           System.out.println("Unable to disconnect!"); 
	  		        } 
	  		     } 
	  		 }

