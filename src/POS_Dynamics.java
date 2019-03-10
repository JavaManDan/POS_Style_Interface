import java.awt.Toolkit;
import java.util.HashMap;


import javax.swing.SwingConstants;
public class POS_Dynamics {
	//some sort of list to store prices
	//some sort of generic gui pop up for quantity and cost using the list changing only the image and the item from another array list
	//takes as param so machine knows what item is what 
	private HashMap<String, Double> menu;
	private HashMap<String, Integer> order;
	private double total;
	public POS_Dynamics() {
		menuCreation();
	}
	
	// used these for images etc. 
	//	java.net.URL img1 = getClass().getResource("/find.PNG");
	//  find = Toolkit.getDefaultToolkit().getImage(img1);
	
	public void menuCreation() {
		menu = new HashMap<>();
		order = new HashMap<>();
		//9 burgers
		menu.put("BeefCheese", 4.50);
		menu.put("BeefBacon", 5.50);
		menu.put("BeefCheeseBacon", 6.00);
		menu.put("Chicken", 4.50);
		menu.put("ChickenBacon", 5.50);
		menu.put("ChickenLettuceT", 6.00);
		menu.put("Veggie", 5.50);
		menu.put("SausageB", 5.50);
		menu.put("SausageEgg", 5.50);
		//9 pizzas
		menu.put("Marg",  6.50);
		menu.put("Mush", 7.50);
		menu.put("MushHam",  8.50);
		menu.put("Hawaiiii", 8.50);
		menu.put("Meaty", 9.50);
		menu.put("Special",  10.50);
		menu.put("VeggieP", 7.50);
		menu.put("Fish",  7.50);
		menu.put("BaconPepper", 8.50);
		//9 sides
		menu.put("RChicken",  1.50);
		menu.put("BChicken",  2.00);
		menu.put("SChicken",  2.00);
		menu.put("Fries",  1.50);
		menu.put("Beans",  1.00);
		menu.put("BRibs", 2.50);
		menu.put("SRibs",  2.50);
		menu.put("GBread",  1.500);
		menu.put("CBread",  1.50);
		//6 drinks
		menu.put("ColaC",  1.00);
		menu.put("LemonC",  1.00);
		menu.put("OrangeC", 1.00);
		menu.put("ColaB",  1.50);
		menu.put("LemonB",  1.50);
		menu.put("OrangeB",  1.50);
		//6 desserts
		menu.put("Vanilla",  2.00);
		menu.put("Strawberry",  2.00);
		menu.put("Chocolate",  2.00);
		menu.put("Mint",  2.00);
		menu.put("Blueberry",  2.00);
		menu.put("Cherry", 2.00);
		
		
	}
	
	public void addItem(String itemName, Integer quant) {
		//given order price
		//if the order contains this item already then change the qu
		if(order.containsKey(itemName)) {
			int newquant = order.get(itemName) + quant;
			order.put(itemName, newquant);
		}
		else {
			order.put(itemName, quant);
		}
		
		double output = 0.00;
		//the given item
		double price = menu.get(itemName);
		//quant * price
		output = price * quant;
		total += output;		
		
	}
	
	public String addToReceipt(String key, int space) {
		// get item name the key and find the quantity the user ordered, then multiply quant by the double price using the same item name on 
		// the menu map
		
		//price current item
		double priceCI = menu.get(key);
		//quantity
		int quant = order.get(key);
		//the item price e.g 2 cheeseburgers is 9.00 1 is 4.50 
		double price4Item = quant * priceCI;
		//return string the to receipt
		
		//set spacer to 0 regardless of length ,so the whitespace code will make the price inline
		
		
		return quant + "x - " +  key  +  String.format("%-" + space + "s" ,"" ) +  "  £" + String.format("%.2f", price4Item , SwingConstants.EAST) + "\n" ;

	}
	
	//hash map get order
	public HashMap<String , Integer> getOrder(){		
		return order;
	}
	//reset the hash
	public void reset() {
		order = null;
		order = new HashMap<String, Integer>();
		total = 0;
		
	}
	//total retrieval
	public double getTotal() {
		
		return total;
	}
	
}
