/******************************************************************************/
/*** Date : Dec, 12th, 2011                                                 ***/
/*** Description: This is the UI that gives user options for generate random***/
/***              number, test a prime number, generate the next prime      ***/
/***              number and generate a set of prime number                 ***/
/******************************************************************************/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.*;
import java.util.*;

public class ASFrame extends JFrame implements ActionListener
{
	BlumBlum blumBlum;
	AES aes;
	JTextArea msgBox;
	JTextField enterField;
	JButton testPrime;
	JButton generateNextPrime;
	JButton generateRandom1;
	JButton generateRandom2;
	JButton generateSetPrime;
	
	public ASFrame (String title)
	{
		setTitle(title);
		setSize(900, 400);
		setLocation(100, 100);
		setResizable(false);
		setContentPane(createContentPane());
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setVisible(true);
		
		// initailize BlumBlum and AES class
		blumBlum = new BlumBlum();
		aes = new AES();
		
	}
	
	// content pane for the main frame
	private Container createContentPane()
	{
		JPanel pane = new JPanel();
		JLabel msgBoxLabel = new JLabel ("Message Box:");
		msgBoxLabel.setFont(new java.awt.Font("Dialog", 1, 18));
		msgBoxLabel.setBounds(30, 20, 200, 20);
		pane.add(msgBoxLabel);
		
		msgBox = new JTextArea();
		msgBox.setBounds(30, 50, 550, 300);
		msgBox.setEditable(false);
		pane.add(msgBox);
		
		JLabel enterFieldLabel = new JLabel ("Enter Field:");
		enterFieldLabel.setFont(new java.awt.Font("Dialog", 1, 18));
		enterFieldLabel.setBounds(600, 20, 200, 20);
		pane.add(enterFieldLabel);
		
		enterField = new JTextField();
		enterField.setBounds(600, 50, 250, 20);
		pane.add(enterField);
		
		testPrime = new JButton("Test Prime");
		testPrime.addActionListener(this);
		testPrime.setBounds(600, 120, 250, 30);
		pane.add(testPrime);
		
		generateNextPrime = new JButton ("Generate Next Prime");
		generateNextPrime.addActionListener(this);
		generateNextPrime.setBounds(600, 160, 250, 30);
		pane.add(generateNextPrime);
		
		
		generateRandom1 = new JButton ("Generate Random Number 1");
		generateRandom1.addActionListener(this);
		generateRandom1.setBounds(600, 240, 250, 30);
		pane.add(generateRandom1);
		
		generateRandom2 = new JButton ("Generate Random Number 2");
		generateRandom2.addActionListener(this);
		generateRandom2.setBounds(600, 280, 250, 30);
		pane.add(generateRandom2);
		
		generateSetPrime = new JButton ("Generate Set Large Prime");
		generateSetPrime.addActionListener(this);
		generateSetPrime.setBounds(600, 320, 250, 30);
		pane.add(generateSetPrime);
		
		return pane;
	}
	
	public void actionPerformed (ActionEvent e)
	{
		/* press button Generate Random Number 1 calls 
		   Blum Blum Slub function to generate a random number */
		if(e.getSource() == generateRandom1)
		{
			msgBox.append(blumBlum.generateRondomNumber().toString());
			msgBox.append("\n");
		}
		
		/* press button Generate Random Number 2 calls 
		   AES to generate a random number */
		else if(e.getSource() == generateRandom2)
		{
			msgBox.append(aes.generateRondomNumber2().toString());
			msgBox.append("\n");
		}
		
		/* press button Test Prime calls Miller-Rabin function
		   to check if the number in text field is a prime number */
		else if(e.getSource() == testPrime)
		{
			// check if there is a number in the text field
			if(!enterField.getText().equals(""))
			{
				BigInteger test = new BigInteger(enterField.getText());
				
				if(MillerRabin.PrimeTest(test))
				{
					msgBox.append("Yes, it is a Prime Number");
					msgBox.append("\n");
				}
				
				else 
				{
					msgBox.append("No, it is not a Prime Number");
					msgBox.append("\n");
				}
			}
		}
		
		// press button to generate the next prime number
		else if(e.getSource() == generateNextPrime)
		{		
			// check if there is a number in the text field
			if(!enterField.getText().equals(""))
			{
				BigInteger test = new BigInteger(enterField.getText());
				BigInteger nextPrime = test.nextProbablePrime();
				msgBox.append(nextPrime.toString());
				msgBox.append("\n");
			}
		}
		
		// generate a set of prime number
		else if(e.getSource() == generateSetPrime)
		{
			BigInteger temp = blumBlum.generateRondomNumber();
			for(int i = 0;i < 4;i++)
			{
				temp = temp.nextProbablePrime();
				msgBox.append(temp.toString());
				msgBox.append("\n");
			}
		}
	}
}
















