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

import javax.swing.*;

public class GUI extends JFrame implements ActionListener
{
	
	private JButton keyCreation;
	private JButton bFile;
	private JButton ubFile;
	private JButton crypt;
	
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
		
		crypt = new JButton("Encrypt/Decrypt");
		crypt.addActionListener(this);
		c.add(crypt);
		
		setSize(200,180);
		show();
	}
	
	public void ActionPerformed (ActionEvent e)
	{
		if(e.getSource()==keyCreation)
		{
			
		}
		else if(e.getSource()==bFile)
		{
			
		}
		else if(e.getSource()==ubFile)
		{
			
		}
		else
		{
			
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
