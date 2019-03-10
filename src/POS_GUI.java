import java.awt.*;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;


import javax.swing.*;
import javax.swing.border.LineBorder;


 
@SuppressWarnings("serial")
public class POS_GUI extends JFrame{
	//receipt of current order
	//order = quantity pass this to dyanmics to process
	
	private POS_Dynamics dynamics;
	//controls

	int spacer = 0;
	//graphics bg
	private JLabel BT;
	private JLabel PT;
	private JLabel ST;
	private JLabel DT;
	private JLabel DET;
	
	//current item price param passed to generic code and used to get price /utility
	private double currentPrice;
	
	private boolean enabled;
	
	//panels and frame
	private JFrame frame;
	private JPanel central;
	private JPanel leftContent;
	private JPanel leftPanels;

	private JPanel itemArea;
	private JPanel topbanner;
	private JPanel topspacer;

	//food items (JButtons) selection on left panel.
	private JButton Burger;
	private JButton Pizza;
	private JButton Sides;
	private JButton Drinks;
	private JButton Desserts;
	private JButton Extra;
	

	//jbuttons for burger area 
	private JButton BeefCheese;
	private JButton BeefBacon2;
	private JButton BeefCheeseBacon;
	private JButton Chicken;
	private JButton ChickenBacon;
	private JButton ChickenLettuceT;
	private JButton Alldaybrek;
	private JButton sausageEgg;
	private JButton sausageB;
	
	//jbuttons pizzas
	private JButton Marg;
	private JButton Mush;
	private JButton MushHam;
	private JButton Hawaiiii;
	private JButton Meaty;
	private JButton veggie;
	private JButton lilfish;
	private JButton baconpeper;
	private JButton special;
	
	//jbutton sides
	private JButton RChicken;
	private JButton BChicken;
	private JButton SChicken;
	private JButton Fries;
	private JButton Beans;
	private JButton BRibs;
	private JButton SRibs;
	private JButton GBread;
	private JButton CBread;
	
	//drinks jbuttons
	private JButton colaC;
	private JButton lemonC;
	private JButton orangeC;
	private JButton colaB;
	private JButton lemonB;
	private JButton orangeB;
	private JButton d1;
	private JButton d2;
	private JButton d3;
	
	//icecream jbuttons
	private JButton vanilla;
	private JButton strawberry;
	private JButton chocolate;
	private JButton mint;
	private JButton blueberry;
	private JButton cherry;
	private JButton d4;
	private JButton d5;
	private JButton d6;
	
	
	//images and graphics burgers

	private static Image CB;
	private static Image BB;
	private static Image BCC;
	private static Image C;
	private static Image CBA;
	private static Image CLT;
	private static Image ADB;
	private static Image SE;
	private static Image SB;
	private static Image BurgerT;
	private static Image BurgerL;
	
	//images pizzas
	private static Image MA;
	private static Image MU;
	private static Image MH;
	private static Image HA;
	private static Image ME;
	private static Image VE;
	private static Image FI;
	private static Image BP;
	private static Image SP;
	private static Image PizzaT;
	private static Image PizzaL;
	
	//images sides 
	private static Image Chick;
	private static Image BChick;
	private static Image SChick;
	private static Image Frie;
	private static Image BBQR;
	private static Image SRib;
	private static Image GBreads;
	private static Image Cbreads;
	private static Image Beanss;
	private static Image SidesT;
	private static Image SidesL;
	
	//images drinks
	private static Image cC;
	private static Image lC;
	private static Image oC;
	private static Image cB;
	private static Image lB;
	private static Image oB;
	private static Image DrinksT;
	private static Image DrinksL;
	
	//images icercream
	private static Image va;
	private static Image sb;
	private static Image ch;
	private static Image mi;
	private static Image bl;
	private static Image cc;
	private static Image DessertsT;
	private static Image DessertsL;
	
	//image help
	private static Image pay;
	private static Image add;
	private static Image can;
	private static Image ot;
	private static Image CO;
	private static Image confirm;
	private static Image comp;
	private static Image home;
	private static Image filler;
	private static Image payment;
	
	//home 
	private JPanel homescreen;
	private JLabel homeImg;
	private JLabel fill;
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		POS_GUI run = new POS_GUI();
	}
	
	
	public POS_GUI() {
		dynamics = new POS_Dynamics();
		homescreen = new JPanel();
		homeImg = new JLabel();
		fill = new JLabel();
		
		enabled = false;
		//make all optionpanes have this background etc
		
		//you need both to color correctly
		UIManager.put("Panel.background", new Color(231,230,230));
		UIManager.put("OptionPane.background", new Color(231,230,230));
		
		currentPrice = 0.00;
		ImageSetup();
		createButtons();
		homeImg.setIcon(new ImageIcon(home));
		fill.setIcon(new ImageIcon(filler));
		homescreen.add(homeImg);
		

		GUIFrameSetup();		
		GUILayoutSetup();	
		GUI_Refresh();
		
		
	}
	
	public void homeScreen() {	
		GUI_Delete();		
		central.removeAll();
		JButton start = new JButton();
		start.setIcon(new ImageIcon(home));
		start.setPreferredSize(new Dimension(368, 562));
		start.setFocusable(false);
		start.setBorderPainted(false);
		central.add(start);
		start.addActionListener(e -> afterHome());
		GUI_Refresh();
	}
	
	public void cardScreen(JTextArea a, JFrame f) {	
		GUI_Delete();		
		central.removeAll();
		JButton start = new JButton();
		start.setIcon(new ImageIcon(payment));
		start.setPreferredSize(new Dimension(368, 562));
		start.setFocusable(false);
		start.setBorderPainted(false);
		central.add(start);
		//create inner class implemnet run class and  then go back to homescreen 
				class Task1 extends TimerTask{

					@Override
					public void run() {
						orderComplete(f, a);
					}
					
				}
				//the timer that waits for 3000 mili seconds before it runs the code 
				Timer ti1  = new Timer();
		ti1.schedule(new Task1(), 2000);
				
		GUI_Refresh();
	}
	
	public void afterHome() {
		central.removeAll();
		central.add(topbanner);
	    central.add(topspacer);
	    central.add(itemArea);	 
	    itemArea.setPreferredSize(new Dimension(340,420));
	    topspacer.setPreferredSize(new Dimension(340,12));
	    topbanner.setPreferredSize(new Dimension(340, 97));	    
	    GUI_ItemDisplayBurger();

	}
	
	
	
	public void Generic_GUI_itemSelection(String item, ImageIcon i, double price) {
		if(!enabled) {
		enabled = true;
		JFrame tempF = new JFrame ("Add to order?");
		
		tempF.setAlwaysOnTop(true);
		tempF.setUndecorated(true);
		currentPrice = price;
		
		//create temp label for each button press depends on the one pressed take image and use it
		Font font = new Font("Calibri", Font.BOLD, 20);
		//string format keeps the decimals i put e.g 4.50 as oppose to default showing 4.5
		JLabel temp = new JLabel("   £ " + String.format("%.2f", currentPrice) + "  ", SwingConstants.CENTER);
		
		temp.setIcon(i);
		temp.setFont(font);
				
		JSlider slider = new JSlider(0, 1, 10, 1);
		slider.setMajorTickSpacing(1);
		slider.setPaintLabels(true);	
		slider.setPaintTicks(true);
		
		JPanel title = new JPanel();
		JLabel t = new JLabel();
		t.setIcon(new ImageIcon(ot));
		title.add(t);
		
		JPanel sholder = new JPanel();	
		sholder.setLayout(new BorderLayout());
		
		JPanel confirm = new JPanel();
		JButton yes = new JButton("Add");
		JButton cancel = new JButton("Cancel");
		
		confirm.setLayout(new GridLayout(2, 0, 0 ,5));
		confirm.add(yes);
		confirm.add(cancel);
		yes.setIcon(new ImageIcon(add));
		cancel.setIcon(new ImageIcon(can));
		confirm.setPreferredSize(new Dimension(80,20));
			
		sholder.add(slider, BorderLayout.CENTER);

		//spin.setPreferredSize(new Dimension(80,40));
		
		
		//setting the frame sizes etc 
		tempF.setSize(260,200);
		
		tempF.setLocation(870,400);
		
		tempF.setVisible(true);
		tempF.setResizable(false);
		tempF.setLayout(new BorderLayout());

		tempF.add(title, BorderLayout.NORTH);
		title.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
		
		tempF.add(temp, BorderLayout.CENTER);
		temp.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
		
		sholder.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
		tempF.add(sholder, BorderLayout.SOUTH);
		
		confirm.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		tempF.add(confirm, BorderLayout.EAST);
		
		tempF.pack();
			
		cancel.addActionListener(e -> closeUsedTabs(tempF));
		yes.addActionListener(e -> supplyHashMap(tempF, item, slider.getValue()) );
		
		
		//my msg to you
		//JOptionPane.showMessageDialog(frame, temp  , "Add item to order?", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public void GUI_checkout() {
		if(!(dynamics.getOrder().keySet().isEmpty())) {
		JFrame tempF = new JFrame ("Add to order?");		
		tempF.setAlwaysOnTop(true);
		tempF.setUndecorated(true);
		tempF.setSize(260,200);
		
		tempF.setLocation(870,400);
		
		tempF.setVisible(true);
		tempF.setResizable(false);
		tempF.setLayout(new BorderLayout());
		
		//title
		JPanel title = new JPanel();
		JLabel t = new JLabel();
		t.setIcon(new ImageIcon(CO));
		title.add(t);
		
		//buttons
		JPanel sholder = new JPanel();	
		sholder.setLayout(new BorderLayout());
		
		JPanel conf = new JPanel();
		JButton yes = new JButton("Confirm");
		JButton cancel = new JButton("Cancel");
		
		conf.setLayout(new GridLayout(2, 0, 0 ,5));
		conf.add(yes);
		conf.add(cancel);
		yes.setIcon(new ImageIcon(confirm));
		cancel.setIcon(new ImageIcon(can));
		conf.setPreferredSize(new Dimension(80,20));
			
		//receipt
		JPanel main = new JPanel();
		JTextArea receipt = new JTextArea();
		int firstT = 0;
		//for all the keys the item name of the persons order set text on receipt
		
		for(String s : dynamics.getOrder().keySet()) {
			
			if(firstT == 0) {
				//spacer = s.length() + 8;
				receipt.setText("---------------------------------------------- \n");
				receipt.append(dynamics.addToReceipt(s, 6));
			}
			else {
				
				receipt.append(dynamics.addToReceipt(s,6));
			}
						
			firstT = 1;
		}
		
		receipt.append("---------------------------------------------- \n");
		receipt.append("Order Total: " +  " £ "+ String.format("%.2f", dynamics.getTotal()) + "\n");
		
		//Jpanel
		JPanel bot = new JPanel();
		
		
		
		receipt.setEditable(false);
		main.add(receipt);
		
		tempF.add(title, BorderLayout.NORTH);
		title.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
		
		conf.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		tempF.add(conf, BorderLayout.EAST);
		
		main.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
		tempF.add(main, BorderLayout.CENTER);
		
		bot.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
		tempF.add(bot, BorderLayout.SOUTH);
		
		
		cancel.addActionListener(e -> closeUsedTabs(tempF));
		yes.addActionListener(e -> cardScreen( receipt, tempF));
		
		tempF.pack();
		}
		
		
	}
	
	
	
	public void  orderComplete(JFrame f, JTextArea a) {
		
		f.dispose();
	
		
		
		JFrame tempF = new JFrame ();		
		tempF.setAlwaysOnTop(true);
		tempF.setUndecorated(true);
		tempF.setSize(260,200);
		
		tempF.setLocation(870,400);
		
		tempF.setVisible(true);
		tempF.setResizable(false);
		tempF.setLayout(new BorderLayout());
		
		//title
		JPanel title = new JPanel();
		JLabel t = new JLabel();
		t.setIcon(new ImageIcon(comp));
		title.add(t);
		
		//buttons
		JPanel sholder = new JPanel();	
		sholder.setLayout(new BorderLayout());
		
		JPanel conf = new JPanel();
		JButton yes = new JButton("Confirm");
		yes.setVisible(false);
		JButton cancel = new JButton("Cancel");
		cancel.setVisible(false);
		
		conf.setLayout(new GridLayout(2, 0, 0 ,5));
		conf.add(yes);
		conf.add(cancel);
		conf.setPreferredSize(new Dimension(80,20));
		Font orderF =new Font("Calibri", Font.BOLD, 20);
		//receipt
		JPanel main = new JPanel();
		JTextArea receipt = new JTextArea();
		receipt.setFont(orderF);

		
	
		Random rand = new Random();
			
		
		receipt.append("Your Order is Number:  " + rand.nextInt(10000000) + "\n");
		receipt.append("---------------------------------------------- \n ");
		//Jpanel
		JPanel bot = new JPanel();
		
		JTextArea printOne = new JTextArea();
		printOne.setText(a.getText());
		printOne.append(receipt.getText());
		
		receipt.setEditable(false);
		main.add(receipt);
		
		tempF.add(title, BorderLayout.NORTH);
		title.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
		
	
		main.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
		tempF.add(main, BorderLayout.CENTER);
		
		bot.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
		tempF.add(bot, BorderLayout.SOUTH);
		
		tempF.pack();
		//uncomment to print

		try {
			
			boolean b = printOne.print(null,null,true,null,null,false);
			frame.toBack();
			GUI_Refresh();
			if(b) {
				
				System.out.println("printed");
			}
			
		
		}
		catch(Exception e) {
			System.out.println("Problem ran");
			e.printStackTrace();
		}
		

		
		
		//create inner class implemnet run class and  then go back to homescreen 
		class Task extends TimerTask{

			@Override
			public void run() {
		
				tempF.dispose();
				homeScreen();
				dynamics.reset();
			}
			
		}
		//the timer that waits for 3000 mili seconds before it runs the code 
		Timer ti  = new Timer();
		ti.schedule(new Task(), 3000);
		System.out.println("complete");
		
		
		
	}
	

	
	public void closeUsedTabs(JFrame f) {
			f.dispose();
			enabled = false;
	}
	
	public void supplyHashMap (JFrame f ,String item, int quant) {
		    System.out.println("item: " + item + "  int amount: " + quant);
			dynamics.addItem(item, quant);
			f.dispose();
			enabled = false;
			
			
	}
	
	public void ImageSetup() {
		// burger images
		BT = new JLabel();
		java.net.URL img1 = getClass().getResource("/CB.PNG");
		CB = Toolkit.getDefaultToolkit().getImage(img1);
		java.net.URL img2 = getClass().getResource("/BB.PNG");
		BB = Toolkit.getDefaultToolkit().getImage(img2);
		java.net.URL img3 = getClass().getResource("/BCC.PNG");
		BCC = Toolkit.getDefaultToolkit().getImage(img3);
		java.net.URL img4 = getClass().getResource("/C.PNG");
		C = Toolkit.getDefaultToolkit().getImage(img4);
		java.net.URL img5 = getClass().getResource("/CBA.PNG");
		CBA = Toolkit.getDefaultToolkit().getImage(img5);
		java.net.URL img6 = getClass().getResource("/CLT.PNG");
		CLT = Toolkit.getDefaultToolkit().getImage(img6);
		java.net.URL img7 = getClass().getResource("/ADB.PNG");
		ADB = Toolkit.getDefaultToolkit().getImage(img7);
		java.net.URL img8 = getClass().getResource("/SE.PNG");
		SE = Toolkit.getDefaultToolkit().getImage(img8);
		java.net.URL img9 = getClass().getResource("/SB.PNG");
		SB = Toolkit.getDefaultToolkit().getImage(img9);
		java.net.URL img10 = getClass().getResource("/BurgersT.PNG");
		BurgerT = Toolkit.getDefaultToolkit().getImage(img10);
		java.net.URL img11 = getClass().getResource("/BurgerL.PNG");
		BurgerL = Toolkit.getDefaultToolkit().getImage(img11);
	
		
		//pizza images
		PT = new JLabel();
		java.net.URL img12 = getClass().getResource("/MA.PNG");
		MA = Toolkit.getDefaultToolkit().getImage(img12);
		java.net.URL img13 = getClass().getResource("/MU.PNG");
		MU = Toolkit.getDefaultToolkit().getImage(img13);
		java.net.URL img14 = getClass().getResource("/MH.PNG");
		MH = Toolkit.getDefaultToolkit().getImage(img14);
		java.net.URL img15 = getClass().getResource("/HA.PNG");
		HA = Toolkit.getDefaultToolkit().getImage(img15);
		java.net.URL img16 = getClass().getResource("/ME.PNG");
		ME = Toolkit.getDefaultToolkit().getImage(img16);
		java.net.URL img17 = getClass().getResource("/VE.PNG");
		VE = Toolkit.getDefaultToolkit().getImage(img17);
		java.net.URL img18 = getClass().getResource("/FI.PNG");
		FI = Toolkit.getDefaultToolkit().getImage(img18);
		java.net.URL img19 = getClass().getResource("/BP.PNG");
		BP = Toolkit.getDefaultToolkit().getImage(img19);
		java.net.URL img20 = getClass().getResource("/SP.PNG");
		SP = Toolkit.getDefaultToolkit().getImage(img20);
		java.net.URL img21 = getClass().getResource("/PizzaT.PNG");
		PizzaT = Toolkit.getDefaultToolkit().getImage(img21);
		java.net.URL img22 = getClass().getResource("/PizzaL.PNG");
		PizzaL = Toolkit.getDefaultToolkit().getImage(img22);
		
	
		//sides
		ST = new JLabel();
		java.net.URL img23 = getClass().getResource("/Chicken.PNG");
		Chick = Toolkit.getDefaultToolkit().getImage(img23);
		java.net.URL img24 = getClass().getResource("/BBQ.PNG");
		BChick = Toolkit.getDefaultToolkit().getImage(img24);
		java.net.URL img25 = getClass().getResource("/SChicken.PNG");
		SChick = Toolkit.getDefaultToolkit().getImage(img25);
		java.net.URL img26 = getClass().getResource("/Fries.PNG");
		Frie = Toolkit.getDefaultToolkit().getImage(img26);
		java.net.URL img27 = getClass().getResource("/BBQR.PNG");
		BBQR = Toolkit.getDefaultToolkit().getImage(img27);
		java.net.URL img28 = getClass().getResource("/SpicyRib.PNG");
		SRib = Toolkit.getDefaultToolkit().getImage(img28);
		java.net.URL img29 = getClass().getResource("/Beans.PNG");
		Beanss = Toolkit.getDefaultToolkit().getImage(img29);		
		java.net.URL img30 = getClass().getResource("/GB.PNG");
		GBreads = Toolkit.getDefaultToolkit().getImage(img30);		
		java.net.URL img31 = getClass().getResource("/CBread.PNG");
		Cbreads = Toolkit.getDefaultToolkit().getImage(img31);
		java.net.URL img32 = getClass().getResource("/SidesT.PNG");
		SidesT = Toolkit.getDefaultToolkit().getImage(img32);
		java.net.URL img33 = getClass().getResource("/SidesL.PNG");
		SidesL = Toolkit.getDefaultToolkit().getImage(img33);

		//drinks images
		DT = new JLabel();
		java.net.URL img34 = getClass().getResource("/cC.PNG");
		cC = Toolkit.getDefaultToolkit().getImage(img34);
		java.net.URL img35 = getClass().getResource("/lC.PNG");
		lC = Toolkit.getDefaultToolkit().getImage(img35);
		java.net.URL img36 = getClass().getResource("/oC.PNG");
		oC = Toolkit.getDefaultToolkit().getImage(img36);
		java.net.URL img37 = getClass().getResource("/ccB.PNG");
		cB = Toolkit.getDefaultToolkit().getImage(img37);
		java.net.URL img38 = getClass().getResource("/lB.PNG");
		lB = Toolkit.getDefaultToolkit().getImage(img38);
		java.net.URL img39 = getClass().getResource("/oB.PNG");
		oB = Toolkit.getDefaultToolkit().getImage(img39);		
		java.net.URL img40 = getClass().getResource("/DrinksT.PNG");
		DrinksT = Toolkit.getDefaultToolkit().getImage(img40);
		java.net.URL img41 = getClass().getResource("/DrinksL.PNG");
		DrinksL = Toolkit.getDefaultToolkit().getImage(img41);
		

		
		//images for desserts
		DET = new JLabel();
		java.net.URL img42 = getClass().getResource("/va.PNG");
		va = Toolkit.getDefaultToolkit().getImage(img42);
		java.net.URL img43 = getClass().getResource("/sbb.PNG");
		sb = Toolkit.getDefaultToolkit().getImage(img43);
		java.net.URL img44 = getClass().getResource("/che.PNG");
		ch = Toolkit.getDefaultToolkit().getImage(img44);
		java.net.URL img45 = getClass().getResource("/mi.PNG");
		mi = Toolkit.getDefaultToolkit().getImage(img45);
		java.net.URL img46 = getClass().getResource("/bl.PNG");
		bl = Toolkit.getDefaultToolkit().getImage(img46);
		java.net.URL img47 = getClass().getResource("/ch.PNG");
		cc = Toolkit.getDefaultToolkit().getImage(img47);
		java.net.URL img48 = getClass().getResource("/DessertsT.PNG");
		DessertsT = Toolkit.getDefaultToolkit().getImage(img48);
		java.net.URL img49 = getClass().getResource("/DessertsL.PNG");
		DessertsL = Toolkit.getDefaultToolkit().getImage(img49);
		
		
		
		//help etc
		java.net.URL img50 = getClass().getResource("/PayL.PNG");
		pay = Toolkit.getDefaultToolkit().getImage(img50);
		java.net.URL img51 = getClass().getResource("/ADD.PNG");
		add = Toolkit.getDefaultToolkit().getImage(img51);
		java.net.URL img52 = getClass().getResource("/CAN.PNG");
		can = Toolkit.getDefaultToolkit().getImage(img52);
		java.net.URL img53 = getClass().getResource("/OT.PNG");
		ot = Toolkit.getDefaultToolkit().getImage(img53);
		java.net.URL img54 = getClass().getResource("/CO.PNG");
		CO = Toolkit.getDefaultToolkit().getImage(img54);
		java.net.URL img55 = getClass().getResource("/confirm.PNG");
		confirm = Toolkit.getDefaultToolkit().getImage(img55);
		java.net.URL img56 = getClass().getResource("/comp.PNG");
		comp = Toolkit.getDefaultToolkit().getImage(img56);
		java.net.URL img57 = getClass().getResource("/home.PNG");
		home = Toolkit.getDefaultToolkit().getImage(img57);
		java.net.URL img58 = getClass().getResource("/filler.PNG");
		filler = Toolkit.getDefaultToolkit().getImage(img58);
		java.net.URL img59 = getClass().getResource("/pay.PNG");
		payment = Toolkit.getDefaultToolkit().getImage(img59);
	
		

	}
	
	public void GUIFrameSetup() {
		//Frame setup...
		frame = new JFrame("Point of Sale Interface");
		frame.setAlwaysOnTop(true);
		frame.setUndecorated(true);
		frame.setSize(480,575);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		//JPanels setup...
		central = new JPanel();
		leftContent = new JPanel();
		leftPanels = new JPanel();
	
	    itemArea = new JPanel();
	    topbanner=  new JPanel();
	    topspacer = new JPanel();
	    //GUI_ItemDisplayBurger();
	    //JButtons for item selection setup...
	    Burger = new JButton();
	    Pizza = new JButton();
	    Sides = new JButton();
	    Drinks = new JButton();
	    Desserts = new JButton();
	    Extra = new JButton();
	    //items
	    //GUI_ItemDisplayBurger();
	}
	
	//sets the colors and borders of jbuttons will use images later
	public void tempColors() {
		Burger.setIcon(new ImageIcon(BurgerL));
		Burger.addActionListener(e -> GUI_ItemDisplayBurger());
		
		Pizza.setIcon(new ImageIcon(PizzaL));		
		Pizza.addActionListener(e -> GUI_ItemDisplayPizza());
		
		Sides.setIcon(new ImageIcon(SidesL));	
		Sides.addActionListener(e -> GUI_ItemDisplaySides());
	

		Drinks.setIcon(new ImageIcon(DrinksL));
		Drinks.addActionListener(e -> GUI_ItemDisplayDrinks());
		
		Desserts.setIcon(new ImageIcon(DessertsL));
		Desserts.addActionListener(e -> GUI_ItemDisplayDesserts());
		
		Extra.setIcon(new ImageIcon(pay));	
		Extra.addActionListener(e -> GUI_checkout());
	}
	
	public void createButtons() {
		//burgers
		BeefCheese = new JButton();
		BeefBacon2 = new JButton();
		BeefCheeseBacon = new JButton();
		Chicken = new JButton();
		ChickenBacon= new JButton();
		ChickenLettuceT= new JButton();
		Alldaybrek= new JButton();
	    sausageEgg= new JButton();
	    sausageB= new JButton();	
	    
	    //burger images
	    BT.setIcon(new ImageIcon(BurgerT));
	    BeefCheese.setIcon(new ImageIcon(CB));
	    BeefCheese.addActionListener(e -> Generic_GUI_itemSelection("BeefCheese", new ImageIcon(CB), 4.50));
	    BeefBacon2.setIcon(new ImageIcon(BB));
	    BeefBacon2.addActionListener(e -> Generic_GUI_itemSelection("BeefBacon",new ImageIcon(BB), 5.50));
	    BeefCheeseBacon.setIcon(new ImageIcon(BCC));
	    BeefCheeseBacon.addActionListener(e -> Generic_GUI_itemSelection("BeefCheeseBacon",new ImageIcon(BCC), 6.00));
	    Chicken.setIcon(new ImageIcon(C));
	    Chicken.addActionListener(e -> Generic_GUI_itemSelection("Chicken",new ImageIcon(C), 4.50));
	    ChickenBacon.setIcon(new ImageIcon(CBA));
	    ChickenBacon.addActionListener(e -> Generic_GUI_itemSelection("ChickenBacon",new ImageIcon(CBA), 5.50));
	    ChickenLettuceT.setIcon(new ImageIcon(CLT));
	    ChickenLettuceT.addActionListener(e -> Generic_GUI_itemSelection("ChickenLettuceT",new ImageIcon(CLT), 6.00));
	    Alldaybrek.setIcon(new ImageIcon(ADB));
	    Alldaybrek.addActionListener(e -> Generic_GUI_itemSelection("Veggie",new ImageIcon(ADB), 5.50));
	    sausageEgg.setIcon(new ImageIcon(SE));
	    sausageEgg.addActionListener(e -> Generic_GUI_itemSelection("SausageB",new ImageIcon(SE), 5.50));
	    sausageB.setIcon(new ImageIcon(SB));
	    sausageB.addActionListener(e -> Generic_GUI_itemSelection("SausageEgg",new ImageIcon(SB), 5.50));
	       	    
	    //jbutton declarations etc pizzas
	    Marg = new JButton();
	    Marg.addActionListener(e -> Generic_GUI_itemSelection("Marg", new ImageIcon(MA), 6.50));
		Mush = new JButton();
		Mush.addActionListener(e -> Generic_GUI_itemSelection("Mush", new ImageIcon(MU), 7.50));
		MushHam = new JButton();
		MushHam.addActionListener(e -> Generic_GUI_itemSelection("MushHam", new ImageIcon(MH), 8.50));
		Hawaiiii = new JButton();
		Hawaiiii.addActionListener(e -> Generic_GUI_itemSelection("Hawaiiii", new ImageIcon(HA), 8.50));
		Meaty = new JButton();
		Meaty.addActionListener(e -> Generic_GUI_itemSelection("Meaty", new ImageIcon(ME), 9.50));
		veggie = new JButton();
		veggie.addActionListener(e -> Generic_GUI_itemSelection("VeggieP", new ImageIcon(VE), 7.50));
		lilfish = new JButton();
		lilfish.addActionListener(e -> Generic_GUI_itemSelection("Fish", new ImageIcon(FI), 7.50));
		baconpeper= new JButton();
		baconpeper.addActionListener(e -> Generic_GUI_itemSelection("BaconPepper", new ImageIcon(BP), 8.50));
		special = new JButton();
		special.addActionListener(e -> Generic_GUI_itemSelection("Special", new ImageIcon(SP), 10.50));
		
		//pizza images	
		PT.setIcon(new ImageIcon(PizzaT));
	    Marg.setIcon(new ImageIcon(MA));	   
	    Mush.setIcon(new ImageIcon(MU));	  
	    MushHam.setIcon(new ImageIcon(MH));	   
	    Hawaiiii.setIcon(new ImageIcon(HA));	
	    Meaty.setIcon(new ImageIcon(ME));	  
	    veggie.setIcon(new ImageIcon(VE));	
	    lilfish.setIcon(new ImageIcon(FI));	  
	    baconpeper.setIcon(new ImageIcon(BP));
	    special.setIcon(new ImageIcon(SP));
	    
	    //jbuttons 
	    RChicken = new JButton();
	    RChicken.addActionListener(e -> Generic_GUI_itemSelection("RChicken", new ImageIcon(Chick), 1.50));
	    BChicken = new JButton();
	    BChicken.addActionListener(e -> Generic_GUI_itemSelection("BChicken", new ImageIcon(BChick), 2.00));
	    SChicken = new JButton();
	    SChicken.addActionListener(e -> Generic_GUI_itemSelection("SChicken", new ImageIcon(SChick), 2.00));
	    Fries = new JButton();
	    Fries.addActionListener(e -> Generic_GUI_itemSelection("Fries", new ImageIcon(Frie), 1.50));
	    Beans = new JButton();
	    Beans.addActionListener(e -> Generic_GUI_itemSelection("Beans", new ImageIcon(Beanss), 1.00));
	    BRibs = new JButton();
	    BRibs.addActionListener(e -> Generic_GUI_itemSelection("BRibs", new ImageIcon(BBQR), 2.50));
	    SRibs = new JButton();
	    SRibs.addActionListener(e -> Generic_GUI_itemSelection("SRibs", new ImageIcon(SRib), 2.50));
	    GBread = new JButton();
	    GBread.addActionListener(e -> Generic_GUI_itemSelection("GBread", new ImageIcon(GBreads), 1.50));
	    CBread = new JButton();
	    CBread.addActionListener(e -> Generic_GUI_itemSelection("CBread", new ImageIcon(Cbreads), 1.50));
	    
	    //jbutton images icon
	    ST.setIcon(new ImageIcon(SidesT));
	    RChicken.setIcon(new ImageIcon(Chick));
	    BChicken.setIcon(new ImageIcon(BChick));	   
	    SChicken.setIcon(new ImageIcon(SChick));	  
	    Fries.setIcon(new ImageIcon(Frie));	   
	    Beans.setIcon(new ImageIcon(Beanss));	
	    BRibs.setIcon(new ImageIcon(BBQR));	  
	    SRibs.setIcon(new ImageIcon(SRib));	
	    GBread.setIcon(new ImageIcon(GBreads));	  
	    CBread.setIcon(new ImageIcon(Cbreads));
	 
	    //jbuttons drinks
	    colaC = new JButton();
	    colaC.addActionListener(e -> Generic_GUI_itemSelection("ColaC", new ImageIcon(cC), 1.00));
		lemonC = new JButton();
		lemonC.addActionListener(e -> Generic_GUI_itemSelection("LemonC", new ImageIcon(lC), 1.00));
	    orangeC = new JButton();
	    orangeC.addActionListener(e -> Generic_GUI_itemSelection("OrangeC", new ImageIcon(oC), 1.00));
		colaB = new JButton();
		colaB.addActionListener(e -> Generic_GUI_itemSelection("ColaB", new ImageIcon(cB), 1.50));
	    lemonB = new JButton();
	    lemonB.addActionListener(e -> Generic_GUI_itemSelection("LemonB", new ImageIcon(lB), 1.50));
		orangeB =  new JButton();
		orangeB.addActionListener(e -> Generic_GUI_itemSelection("OrangeB", new ImageIcon(oB), 1.50));
		d1 = new JButton();
		d2 = new JButton();
		d3 = new JButton();
		
		DT.setIcon(new ImageIcon(DrinksT));
		colaC.setIcon(new ImageIcon(cC));
		lemonC.setIcon(new ImageIcon(lC));
		orangeC.setIcon(new ImageIcon(oC));
		colaB.setIcon(new ImageIcon(cB));
		lemonB.setIcon(new ImageIcon(lB));
		orangeB.setIcon(new ImageIcon(oB));
		
		//jbuttons icecream
		vanilla = new JButton();
		vanilla.addActionListener(e -> Generic_GUI_itemSelection("Vanilla", new ImageIcon(va), 2.00));
		strawberry = new JButton();
		strawberry.addActionListener(e -> Generic_GUI_itemSelection("Strawberry", new ImageIcon(sb), 2.00));
		chocolate= new JButton();
		chocolate.addActionListener(e -> Generic_GUI_itemSelection("Cherry", new ImageIcon(ch), 2.00));
	    mint= new JButton();
	    mint.addActionListener(e -> Generic_GUI_itemSelection("Mint", new ImageIcon(mi), 2.00));
		blueberry= new JButton();
		blueberry.addActionListener(e -> Generic_GUI_itemSelection("Blueberry", new ImageIcon(bl), 2.00));
		cherry= new JButton();
		cherry.addActionListener(e -> Generic_GUI_itemSelection("Chocolate", new ImageIcon(cc), 2.00));
		d4= new JButton();
		d5= new JButton();
		d6= new JButton();
		
		DET.setIcon(new ImageIcon(DessertsT));
		vanilla.setIcon(new ImageIcon(va));
		strawberry.setIcon(new ImageIcon(sb));
		chocolate.setIcon(new ImageIcon(ch));
		mint.setIcon(new ImageIcon(mi));
		blueberry.setIcon(new ImageIcon(bl));
		cherry.setIcon(new ImageIcon(cc));
		
	}
	

	//removes all items from item area rdy for new buttons 
	public void GUI_Delete() {	
		topbanner.removeAll();
		itemArea.removeAll();			
	}
	
	//refresh page to see new jbuttons
	public void GUI_Refresh() {
		frame.setVisible(true);
		frame.repaint();
	}
	
	//display burgers
	public void GUI_ItemDisplayBurger() {							
		GUI_Delete();
		itemArea.setLayout(new GridLayout(3,3,7,7 ));
		topbanner.add(BT);
	    itemArea.add(BeefCheese);
	    itemArea.add(BeefBacon2);
	    itemArea.add(BeefCheeseBacon);	 
	    itemArea.add(Chicken);
	    itemArea.add(ChickenBacon);
	    itemArea.add(ChickenLettuceT);
	    itemArea.add(Alldaybrek);
	    itemArea.add(sausageEgg);
	    itemArea.add(sausageB);
	    GUI_Refresh();	
	}
	//display pizzas
	public void GUI_ItemDisplayPizza() {		
        GUI_Delete();	
        itemArea.setLayout(new GridLayout(3,3,7,7 ));
        topbanner.add(PT);
	    itemArea.add(Marg);
	    itemArea.add(Mush);
	    itemArea.add(MushHam);
	    itemArea.add(Hawaiiii);
	    itemArea.add(Meaty);
	    itemArea.add(veggie);
	    itemArea.add(lilfish);
	    itemArea.add(baconpeper);
	    itemArea.add(special);
	    GUI_Refresh();					
	}
	
	public void GUI_ItemDisplaySides(){
		 GUI_Delete();
		 itemArea.setLayout(new GridLayout(3,3,7,7 ));
		 topbanner.add(ST);
	     itemArea.add(RChicken);
		 itemArea.add(BChicken);
		 itemArea.add(SChicken);
		 itemArea.add(BRibs);
		 itemArea.add(SRibs);
		 itemArea.add(Fries);
		 itemArea.add(Beans);
		 itemArea.add(GBread);
		 itemArea.add(CBread);
		 GUI_Refresh();	
	}
	
	public void GUI_ItemDisplayDrinks(){
		 GUI_Delete();
		 itemArea.setLayout(new GridLayout(3,3,7,7 ));				
		 topbanner.add(DT);
	     itemArea.add(colaC);
		 itemArea.add(lemonC);
		 itemArea.add(orangeC);
		 itemArea.add(colaB);
		 itemArea.add(lemonB);
		 itemArea.add(orangeB);		
		 itemArea.add(d1);
		 itemArea.add(d2);
		 itemArea.add(d3);
		 d1.setVisible(false);
		 d2.setVisible(false);
		 d3.setVisible(false);
		 GUI_Refresh();	
	}
	
	public void GUI_ItemDisplayDesserts(){
		 GUI_Delete();
		 itemArea.setLayout(new GridLayout(3,3,7,7 ));				
		 topbanner.add(DET);
	     itemArea.add(vanilla);
		 itemArea.add(strawberry);
		 itemArea.add(chocolate);
		 itemArea.add(mint);
		 itemArea.add(blueberry);
		 itemArea.add(cherry);		
		 itemArea.add(d4);
		 itemArea.add(d5);
		 itemArea.add(d6);
		 d4.setVisible(false);
		 d5.setVisible(false);
		 d6.setVisible(false);
		 GUI_Refresh();	
	}
	
	//main color scheme
	public void GUIMainAreaColor() {		
		topspacer.setBackground(Color.LIGHT_GRAY);
	    itemArea.setBackground(Color.LIGHT_GRAY);
        central.setBackground(Color.LIGHT_GRAY);
	}
	
	public void GUILayoutSetup() {
		
	    frame.setLayout(new BorderLayout());
	    central.setBorder(new LineBorder(Color.BLACK, 1));	
	    central.setLayout(new FlowLayout());
	   
	    //everything will be added to frame all layout managers and jpanels etc...
	
	    //will contain the food items...
	    leftContent.setBorder(new LineBorder(Color.BLACK));
	    leftContent.setBackground(Color.WHITE);
	    //will be left of screen WEST.
	    frame.add(leftContent, BorderLayout.WEST);
	    //set preffered dimension.
	    leftContent.setPreferredSize(new Dimension(110, 575));
	    leftPanels.setBorder(new LineBorder(Color.BLACK));
	    leftPanels.setLayout(new GridLayout(6,1));
	    //all our buttons...
	    tempColors();
	    leftPanels.add(Burger);
	    leftPanels.add(Pizza);
	    leftPanels.add(Sides);
	    leftPanels.add(Drinks);
	    leftPanels.add(Desserts);
	    leftPanels.add(Extra);
	    itemArea.setLayout(new GridLayout(3,3,7,7 ));
	    
	    //add all leftpanel stuff to leftcontent container.
	    leftPanels.setPreferredSize(new Dimension(92, 560));
	    leftContent.add(leftPanels);
	   	   
	    //itea panel, border and layoutmgr		
	    //vheight and vwidth
	  	
	    //GUI_ItemDisplayBurger();
	    //central...
	    central.add(topbanner);
	    central.add(topspacer);
	    central.add(itemArea);	 
	    
	   
	    //GUIMainAreaColor();
	    
	    //set our sizes...
	    itemArea.setPreferredSize(new Dimension(340,420));
	    topspacer.setPreferredSize(new Dimension(340,12));
	    topbanner.setPreferredSize(new Dimension(340, 97));
	    
	    homeScreen();
	    //add all our stuff to the frame so it can be seen.
	    frame.add(central, BorderLayout.CENTER);
	
	    
	 	    
	}
	
	
}
	

