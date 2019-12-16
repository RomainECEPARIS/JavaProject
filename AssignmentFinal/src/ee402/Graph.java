package ee402;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.util.concurrent.TimeUnit;

public class Graph implements ActionListener, ItemListener  {
	
	private static JPanel z;
	private final int echelle = 3;
	private final int width = 700;
	private final int height = 700;

	
	// 1st graph - static
	double listDate[] = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190};
	double listTemperature[] = {20, 60, 40, 10, 60, 30, 15, 20, 30, 90, 0, 45, 60, 34, 67, 40, 32, 21, 37, 54};
	
	//2nd graph - random temperature (we initialize at 0)
	double listDate2[] = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95} ;
	double listTemperature2[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0} ;
	



	
	
	/// We generate random temperatures
	public void aleatoire()
	{
		for (int i=0; i<listDate2.length; i++)
		{
			int j = genererInt(0,90);
			System.out.println("Un number j has been generate: " + j);
			listTemperature2[i] = j ;
		}
		// display
		for (int i=0; i<listDate2.length; i++)
		{
			System.out.println(" " + listTemperature2[i]);
		}

	}
	
	// delete the first point and add a new point at the end
	public void displacement(int add)
	{
		int j = genererInt(0,90);
		System.out.println("Un number j has been generate: " + j);
		

		double k = listDate2[19] + add;
		
		for (int i=1; i<listDate2.length; i++)
		{
			listTemperature2[i-1] = listTemperature2[i] ;
			listDate2[i-1] = listDate2[i] ;
			listTemperature2[i] = j ;
			listDate2[i] = k ;
		}
		
	
		for (int i=0; i<listDate2.length; i++)
		{
			System.out.println(" " + listTemperature2[i]);
		}

	}
	
	
	/// We generate random temperatures
	int genererInt(int borneInf, int borneSup)
	{
		   Random random = new Random();
		   int nb;
		   nb = borneInf+random.nextInt(borneSup-borneInf);
		   return nb;
	}
			
	
	
	
	
	
	
	
	
	
    public JMenuBar MenuBar() 
    {
        JMenuBar Bar;
        JMenu file, edit;
        JMenuItem Item;

        //Menu Bar
        Bar = new JMenuBar();

        //Menu Button
        file = new JMenu("Menu");
        file.setMnemonic(KeyEvent.VK_A);
        Bar.add(file);

        Item = new JMenuItem("Close",KeyEvent.VK_B);
        Item.addActionListener(this);
        file.add(Item);     

        
        //Client 1 Button
        edit = new JMenu("Client 1");
        edit.setMnemonic(KeyEvent.VK_C);
        Bar.add(edit);

        Item = new JMenuItem("draw 1",KeyEvent.VK_D);
        Item.addActionListener(this);
        edit.add(Item);
        
        
      //Client 2 Button
        edit = new JMenu("Client 2");
        edit.setMnemonic(KeyEvent.VK_E);
        Bar.add(edit);
        
        Item = new JMenuItem("draw 2 - every 1 second",KeyEvent.VK_F);
        Item.addActionListener(this);
        edit.add(Item);
        
        Item = new JMenuItem("draw 2 - every 3 seconds",KeyEvent.VK_G);
        Item.addActionListener(this);
        edit.add(Item);
        
        
        //Client 3 Button
        edit = new JMenu("Client 3");
        edit.setMnemonic(KeyEvent.VK_H);
        Bar.add(edit);
        

        // Reset Button      
        edit = new JMenu("Reset curve");
        edit.setMnemonic(KeyEvent.VK_J);
        Bar.add(edit);
        
        Item = new JMenuItem("Reset",KeyEvent.VK_I);
        Item.addActionListener(this);
        edit.add(Item);
        

        
        
        return Bar;
    }

    
    /// MAIN GARDE
    private static void Page() 
    {
        JFrame frame = new JFrame("Java Project - Romain GOBERT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Graph smenu = new Graph();
        frame.setJMenuBar(smenu.MenuBar());
        
		z = new JPanel();
		z.setBackground(Color.red);
		frame.getContentPane().add(z, BorderLayout.CENTER);
        

        /* show frame */
        frame.pack();
        frame.setSize(700,700);
        frame.setVisible(true);
        
        frame.repaint();
    }

    
    // MAIN
    public static void main(String[] args) 
    {
    	Page();
    }

    
    
    public void itemStateChanged(ItemEvent e) { }

    public void actionPerformed(ActionEvent e) 
    {

        JMenuItem jmi = (JMenuItem)e.getSource();
        System.out.println("menu item clicked: " + jmi.getText());
        
        
        // If you click on "close"
        if (jmi.getText().equalsIgnoreCase("Close")) 
        {
            System.exit(0);
        }
        
        
        // If you click on "draw 1"
        if (jmi.getText().equalsIgnoreCase("draw 1")) 
        {
        	graphique(2);
        }
        
        
        // If you click on "draw 2 - every 1 second"
        if (jmi.getText().equalsIgnoreCase("draw 2 - every 1 second")) 
        {
        	int stop = 0;
        	aleatoire();

        	do
        	{
        		int counter = 0;
        		counter = counter + 7;
        		graphique(3);
        		displacement(counter);	

        		stop ++ ; 
        		
        	}while(stop < 10 );        	
        }
        
        
        // If you click on "draw 2 - every 3 seconds"
        if (jmi.getText().equalsIgnoreCase("draw 2 - every 3 seconds")) 
        {
        	int stop = 0;
        	aleatoire();

        	do
        	{
        		int counter = 0;
        		counter = counter + 7;
        		graphique(4);
        		displacement(counter);

        		stop ++ ; 
        		
        	}while(stop < 5 );
        	
        }
        
        // If you click on "Reset"
        if (jmi.getText().equalsIgnoreCase("Reset")) 
        {
        	graphique(5);
        }
    }
    

	
    public void graphique(int choix) 
    {

    	Map<Double, Double> map = getCurve();
    	Map<Double, Double> map1 = getCurve1();
    	
    	// If you click on "draw 1"     
        if (choix==2)
        {	
        	// we clear the previous graph
            Graphics h = z.getGraphics();
    		clearCurve(map1, h, 2);
    		
    		//display the graph
            Graphics g = z.getGraphics();
    		drawCurve(map, g, 1);
        }
        
        
        // If you click on "draw 2 - every 1 second"
        if (choix==3)
        {
        	
        	// we clear the previous graph
            Graphics h = z.getGraphics();
    		clearCurve(map, h,1);
    		
 			// we display the graph
    		Graphics c = z.getGraphics();
        	drawCurve(map1, c, 2);
        	
        	// 1 second break
        	try 
        	{
				TimeUnit.SECONDS.sleep(1);
			} 
        	
        	catch (InterruptedException e1) 
        	{
					e1.printStackTrace();
			}
        		
        	// we clear the graph	
        	Graphics m = z.getGraphics();
        	clearCurve(map1, m, 2);
        }
        
        
        // If you click on "draw 2 - every 3 seconds"
        if (choix==4)
        {
        	
        	// we clear the previous graph
            Graphics h = z.getGraphics();
    		clearCurve(map, h,1);
    		
 			
    		// display the graph
        	Graphics c = z.getGraphics();
        	drawCurve(map1, c, 2);
        	
        	// 3 seconds break 
        	try 
        	{
        		TimeUnit.SECONDS.sleep(3);
			} 
        	catch (InterruptedException e1) 
        	{
				e1.printStackTrace();
			}
        		
        	// We clear the graph	
        	Graphics m = z.getGraphics();
        	clearCurve(map1, m, 2);
        }
        
        // If you click on "Reset"
        if (choix==5)
        {
        	// we clear the previous graph
            Graphics h = z.getGraphics();
    		clearCurve(map1, h, 2);
    		
            Graphics A = z.getGraphics();
    		clearCurve(map, A, 1);
    		
    		listDate2[0] = 0 ;
    		listDate2[1] = 5 ;
    		listDate2[2] = 10 ;
    		listDate2[3] = 15 ;
    		listDate2[4] = 20 ;
    		listDate2[5] = 25 ;
    		listDate2[6] = 30 ;
    		listDate2[7] = 35 ;
    		listDate2[8] = 40 ;
    		listDate2[9] = 45 ;
    		listDate2[10] = 50 ;
    		listDate2[11] = 55 ;
    		listDate2[12] = 60 ;
    		listDate2[13] = 65 ;
    		listDate2[14] = 70 ;
    		listDate2[15] = 75 ;
    		listDate2[16] = 80 ;
    		listDate2[17] = 85 ;
    		listDate2[18] = 90 ;
    		listDate2[19] = 95 ;
			

        }
        
    }
    
    

    
    
    
    
    
    

    
	/// add points in a Map (client1)   
	private Map<Double, Double> getCurve() 
	{
		Map<Double, Double> map = new LinkedHashMap<Double, Double>();
		
		
		for (int i=0; i<listDate.length ; i++) 
		{
			double x = listDate[i];
			double y = listTemperature[i];
			map.put(x, y);
		}
		return map;
	}

	
	/// add points in a Map (client2)   
	private Map<Double, Double> getCurve1() 
	{
		
		Map<Double, Double> map = new LinkedHashMap<Double, Double>();
		
		for (int i=0; i<listDate2.length ; i++) 
		{
			double x = listDate2[i];
			double y = listTemperature2[i];
			map.put(x, y);
		}
		return map;
	}

	
	// We draw the curve
	private void drawCurve(final Map<Double, Double> map, final Graphics g, int guest) 
	{
		int x = width/2;
		int y = height/2;
		g.translate(x -250, y);		// we determine the origin
 
		g.setColor(Color.BLACK);
		
		g.drawLine(-800, 0 , 800, 0);		// we draw the axes
		g.drawLine(-0, -500 , -0, 500);
 
		Set<Double> it = map.keySet();
		Double X = null;
		Double Y = null;
		for (Double alpha : it) 
		{
			Double value = map.get(alpha);
			if (X != null && Y != null) 
			{
				// we connect the points together 
				g.drawLine(X.intValue() * echelle, Y.intValue() * -1 * echelle, 
						alpha.intValue() * echelle, value.intValue() * -1 * echelle);
				
				// we indicate the date of each point
				g.drawString("d:" + X, 
						     X.intValue() * echelle, Y.intValue() * -1 * echelle);
				
				// we indicate the date of each point
				g.drawString("T:" + Y , 
					     X.intValue() * echelle, (Y.intValue() -5) * -1 * echelle);	
			}
			
			X = alpha;
			Y = value;

		}
		
		g.drawString("Temperature °C", 0, 100 * -1 * echelle);
		g.drawString("Date", 60 * echelle, -10 *-1 * echelle);
		
		
		double maxVal = Double.MAX_VALUE;
	    double minVal = Double.MIN_VALUE;
	    double moyenne = 0;
		
	    if (guest == 1)
		{	
	    	// MIN MAX
	    	for(int i = 0; i < listTemperature.length; i++)
	    	{
	    		if(listTemperature[i] < maxVal)
	    			maxVal = listTemperature[i];
	    		if(listTemperature[i] > minVal)
	    	   	minVal = listTemperature[i];
	    	}
	    	
	    	// AVERAGE    
	         int somme = 0;
	         for(int i = 0; i < listTemperature.length; i++)
	         {
	            somme += listTemperature[i];
	         }
	         moyenne = (double) somme / listTemperature.length;
	    
	         System.out.print("\nMoyenne = "+moyenne);
		}
	    
	    
	    
	    if (guest == 2)
		{
	    	// MIN MAX
	    	for(int i = 0; i < listTemperature2.length; i++)
	    	{
	    		if(listTemperature2[i] < maxVal)
	    			maxVal = listTemperature2[i];
	    		if(listTemperature2[i] > minVal)
	    	   	minVal = listTemperature2[i];
	    	}
	    	
	    	// AVERAGE    
	         int somme = 0;
	         for(int i = 0; i < listTemperature2.length; i++)
	         {
	            somme += listTemperature2[i];
	         }
	         moyenne = (double) somme / listTemperature2.length;
	    
	         System.out.print("\nMoyenne = "+moyenne);
		}
	    
	    
		
		System.out.print("\nMinimal value = "+maxVal);
	    System.out.print("\nMaximal value = "+minVal);
		
	    int k = (int)maxVal;
	    int l = (int)minVal;
	    int m = (int)moyenne;
	    
	    g.setColor(Color.GREEN);
	    //MIN
		g.drawLine(-10 *echelle, k *echelle *-1, 
				    300 *echelle, k *echelle *-1);
		g.drawString("Minimum", -30 * echelle, k *-1 * echelle);
		g.drawString("      " + k, -30 * echelle, (k-5) * -1  * echelle);
		
		//MAX
		g.drawLine(-10 *echelle, l *echelle *-1, 
			        300 *echelle, l *echelle *-1);
		g.drawString("Maximum", -30 * echelle, l *-1 * echelle);
		g.drawString("      " + l, -30 * echelle, (l-5) * -1  * echelle);
		
		//AVERAGE
		g.drawLine(-10 *echelle, m *echelle *-1, 
			        300 *echelle, m *echelle *-1);
		g.drawString("Average", -30 * echelle, m *-1 * echelle);
		g.drawString("      " + m, -30 * echelle, (m-5) * -1  * echelle);
		
	}
	
	
	
	private void clearCurve(final Map<Double, Double> map, final Graphics g, int guest) 
	{
		int x = width / 2;
		int y = height / 2;
		g.translate(x -250, y);
		 
		g.setColor(Color.RED);
		
		g.drawLine(-800, 0 , 800, 0);
		g.drawLine(-0, -500 , -0, 500);
 
 
		Set<Double> it = map.keySet();
		Double X = null;
		Double Y = null;
		for (Double alpha : it) 
		{
			Double value = map.get(alpha);
			if (X != null && Y != null) 
			{
				g.drawLine(X.intValue() * echelle, Y.intValue() * -1 * echelle, 
						alpha.intValue() * echelle, value.intValue() * -1 * echelle);
			
				g.drawString("d:" + X, 
						     X.intValue() * echelle, Y.intValue() * -1 * echelle);
				
				g.drawString("T:" + Y , 
					     X.intValue() * echelle, (Y.intValue() -5) * -1 * echelle);
				
			}
			X = alpha;
			Y = value;
		}
	
		g.drawString("Temperature °C", 0, 100 * -1 * echelle);
		g.drawString("Date", 60 * echelle, -10 *-1 * echelle);
		
		
		double maxVal = Double.MAX_VALUE;
	    double minVal = Double.MIN_VALUE;
	    double moyenne = 0;
		
	    if (guest == 1)
		{	
	    	// MIN MAX
	    	for(int i = 0; i < listTemperature.length; i++)
	    	{
	    		if(listTemperature[i] < maxVal)
	    			maxVal = listTemperature[i];
	    		if(listTemperature[i] > minVal)
	    	   	minVal = listTemperature[i];
	    	}
	    	
	    	// AVERAGE    
	         int somme = 0;
	         for(int i = 0; i < listTemperature.length; i++)
	         {
	            somme += listTemperature[i];
	         }
	         moyenne = (double) somme / listTemperature.length;
	    
	         System.out.print("\nMoyenne = "+moyenne);
		}
	    
	    
	    
	    if (guest == 2)
		{
	    	// MIN MAX
	    	for(int i = 0; i < listTemperature2.length; i++)
	    	{
	    		if(listTemperature2[i] < maxVal)
	    			maxVal = listTemperature2[i];
	    		if(listTemperature2[i] > minVal)
	    	   	minVal = listTemperature2[i];
	    	}
	    	
	    	// AVERAGE    
	         int somme = 0;
	         for(int i = 0; i < listTemperature2.length; i++)
	         {
	            somme += listTemperature2[i];
	         }
	         moyenne = (double) somme / listTemperature2.length;
	    
	         System.out.print("\nMoyenne = "+moyenne);
		}
	    
	    
		
		System.out.print("\n minimal value = "+maxVal);
	    System.out.print("\n maximal value = "+minVal);
		
	    int k = (int)maxVal;
	    int l = (int)minVal;
	    int m = (int)moyenne;
	    
	    g.setColor(Color.RED);
	    
	    //MIN
		g.drawLine(-10 *echelle, k *echelle *-1, 
				    300 *echelle, k *echelle *-1);
		g.drawString("Minimum", -30 * echelle, k *-1 * echelle);
		g.drawString("      " + k, -30 * echelle, (k-5) * -1  * echelle);
		
		//MAX
		g.drawLine(-10 *echelle, l *echelle *-1, 
			        300 *echelle, l *echelle *-1);
		g.drawString("Maximum", -30 * echelle, l *-1 * echelle);
		g.drawString("      " + l, -30 * echelle, (l-5) * -1  * echelle);
		
		//AVERAGE
		g.drawLine(-10 *echelle, m *echelle *-1, 
			        300 *echelle, m *echelle *-1);
		g.drawString("Average", -30 * echelle, m *-1 * echelle);
		g.drawString("      " + m, -30 * echelle, (m-5) * -1  * echelle);

		
	}    
}