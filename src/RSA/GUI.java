/*
    Group 58:
    Michael Irizarry (miriza6@uic.edu)
    Wenkan Zhu (wzhu24@uic.edu)

    CS 342 - Project 3
    RSA
*/

package RSA;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class GUI extends JFrame implements ActionListener
{
	
	private JButton keyCreation;		//To create new keys
	private JButton bFile;				//To block a file
	private JButton ubFile;				//To unblock a file
	private JButton encrypt;			//To encrypt a file
	private JButton decrypt;			//To decrpyt a file
	
	public GUI()
	{
		super("RSA program");
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		keyCreation = new JButton("Key Creation");
		keyCreation.addActionListener(this);
		c.add(keyCreation);
		
		bFile = new JButton("Block a file");
		bFile.addActionListener(this);
		c.add(bFile);
		
		ubFile = new JButton("Unblock a file");
		ubFile.addActionListener(this);
		c.add(ubFile);
		
		encrypt = new JButton("Encrypt");
		encrypt.addActionListener(this);
		c.add(encrypt);
		
		decrypt = new JButton("Decrypt");
		decrypt.addActionListener(this);
		c.add(decrypt);
		
		setSize(220,180);
		show();
	}
	
	public void actionPerformed (ActionEvent e)
	{
		if(e.getSource()==keyCreation)
		//Create keys
		{			
			
			BigInt p;
			BigInt q;
			RSAKeyGen keygen;
			
			int choice = JOptionPane.showConfirmDialog(null, "Do you want to input your own prime numbers?");
			
			if(choice == JOptionPane.YES_OPTION)
			//If user want to input itself
			{
				//Get input strings
				String p0 = JOptionPane.showInputDialog("Please input a prime number (at least 5 digits):");
				String q0 = JOptionPane.showInputDialog("Please input another prime number (at least 5 digits):");
				
				//Parse into BigInt
				p = new BigInt(p0);
				q = new BigInt(q0);
				
				if(!BigInt.isPrime(p) || !BigInt.isPrime(q))
				//Check if input values are prime
				{
					JOptionPane.showMessageDialog(null, "Your input is not prime, please try again.");
					return;
				}
				
			}
			else if(choice == JOptionPane.NO_OPTION)
			//If user select random picking
			{
				File primes = new File("primes.txt");	//Where we store the primes
				String[] vals = new String[20];
				
				if(primes.exists())
				//Read the file
				{
					Scanner reader;
					try 
					{
						reader = new Scanner(new FileInputStream(primes));
					} 
					catch (FileNotFoundException e1) 
					{
						JOptionPane.showMessageDialog(null, "Prime values not found.");
						return;
					}
					for(int i=0;i<20;i++)
					{
						vals[i] = reader.nextLine();
					}
				}
				
				//Randomly pick to indices
				int p1 = (int)(Math.random()*20);
				int a1;
				do
				{
					a1 = (int)(Math.random()*20);
				}
				while( a1 == p1);
				
				//Generate p and q
				p = new BigInt(vals[p1]);
				q = new BigInt(vals[a1]);
			}
			else
			//If user cancelled
			{
				return;
			}
			
			try
			//Generate keys using p and q
			{
				keygen = new RSAKeyGen(p,q);
			} 
			catch (Exception e1)
			{
				JOptionPane.showMessageDialog(null, "Invalid input, please try again.");
				return;
			}
			
			//Get the path to save
			String publicPath = JOptionPane.showInputDialog("Please enter the public key saving path:");
			String privatePath = JOptionPane.showInputDialog("Please enter the private key saving path:");
			
			try 
			//Save into xml files
			{
				RSAFileOp.savePublicKey(publicPath, keygen.publicKey());
				RSAFileOp.savePrivateKey(privatePath, keygen.privateKey());
			} 
			catch (TransformerConfigurationException e1) 
			{
				JOptionPane.showMessageDialog(null, "Invalid input, please try again.");
				return;
			} 
			catch (ParserConfigurationException e1)
			{
				JOptionPane.showMessageDialog(null, "Invalid input, please try again.");
				return;
			}
			catch (TransformerException e1)
			{
				JOptionPane.showMessageDialog(null, "Invalid input, please try again.");
				return;
			}
			catch (IOException e1)
			{
				JOptionPane.showMessageDialog(null, "Invalid input, please try again.");
				return;
			}
			
		}
		else if(e.getSource()==bFile)
		//Block a file
		{
			String inPath = JOptionPane.showInputDialog("Please enter the file input path:");
			String outPath = JOptionPane.showInputDialog("Please enter the file output path:");
			String size = JOptionPane.showInputDialog("Please enter the file block size:");
			int bsize = Integer.parseInt(size);
			try 
			{
				RSAFileOp.blockFile(inPath, outPath, bsize);
			} 
			catch (FileNotFoundException e1) 
			{
				JOptionPane.showMessageDialog(null, "Invalid input, please try again.");
				return;
			} 
			catch (IOException e1) 
			{
				JOptionPane.showMessageDialog(null, "Invalid input, please try again.");
				return;
			} 
			catch (Exception e1) 
			{
				JOptionPane.showMessageDialog(null, "Invalid input, please try again.");
				return;
			}
		}
		else if(e.getSource()==ubFile)
		//Unblock a file
		{
			String inPath = JOptionPane.showInputDialog("Please enter the file input path:");
			String outPath = JOptionPane.showInputDialog("Please enter the file output path:");
			try 
			{
				RSAFileOp.unblockFile(inPath, outPath);
			} 
			catch (FileNotFoundException e1) 
			{
				JOptionPane.showMessageDialog(null, "Invalid input, please try again.");
				return;
			} 
			catch (IOException e1) 
			{
				JOptionPane.showMessageDialog(null, "Invalid input, please try again.");
				return;
			} 
			catch (Exception e1) 
			{
				JOptionPane.showMessageDialog(null, "Invalid input, please try again.");
				return;
			}
		}
		else if(e.getSource()==encrypt)
		//Encrypt a file
		{
			String keyPath = JOptionPane.showInputDialog("Please enter the public key path:");
			String inPath = JOptionPane.showInputDialog("Please enter the file input path:");
			String outPath = JOptionPane.showInputDialog("Please enter the file output path:");
			
			
			RSAPublicKey publicKey;
			try
			{
				publicKey = RSAFileOp.loadPublicKey(keyPath);
				RSAFileOp.encryptFile(inPath, outPath, publicKey);
			} 
			catch (ParserConfigurationException e1) 
			{
				JOptionPane.showMessageDialog(null, "Invalid input, please try again.");
				return;
			} 
			catch (SAXException e1) 
			{
				JOptionPane.showMessageDialog(null, "Invalid input, please try again.");
				return;
			} 
			catch (IOException e1)
			{
				JOptionPane.showMessageDialog(null, "Invalid input, please try again.");
				return;
			}						
		}
		else if(e.getSource()==decrypt)
		//Decrpyt a file
		{
			String keyPath = JOptionPane.showInputDialog("Please enter the private key path:");
			String inPath = JOptionPane.showInputDialog("Please enter the file input path:");
			String outPath = JOptionPane.showInputDialog("Please enter the file output path:");
			
			
			RSAPrivateKey privateKey;
			try
			{
				privateKey = RSAFileOp.loadPrivateKey(keyPath);
				RSAFileOp.decryptFile(inPath, outPath, privateKey);
			} 
			catch (ParserConfigurationException e1) 
			{
				JOptionPane.showMessageDialog(null, "Invalid input, please try again.");
				return;
			} 
			catch (SAXException e1) 
			{
				JOptionPane.showMessageDialog(null, "Invalid input, please try again.");
				return;
			} 
			catch (IOException e1)
			{
				JOptionPane.showMessageDialog(null, "Invalid input, please try again.");
				return;
			}	
		}
	}
	
	public static void main(String[] args) 
	{
		GUI app = new GUI();
		
		app.addWindowListener(new WindowAdapter()
		{
			public void windowClosing (WindowEvent e)
			{
				System.exit(0);
			}
		});

	}
}
