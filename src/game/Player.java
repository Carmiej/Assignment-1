package game;

import java.util.Random;

/*
 * This class defines the player with there health current room and such.
 * @author Blake Mclean 15904394
 */

public class Player 
{
	public static int health;
	public int currentRoom;
	public static int goldCount;
	public static boolean dagger;
	private static int damage;
	
	public Player()
	{
		
		dagger = false;
		goldCount = 0;
		health = 100;
		currentRoom = 1;
		//starting damage is 1-10 damage.
		damage = 30;
	}
	
	public void setHealth(int input)
	{
		health = input;
	}
	
	public void setRoom(int input)
	{
		currentRoom = new Integer(input);
	}
	
	public void setGold(int input)
	{
		goldCount = goldCount + input;
	}
	
	public static void setDagger(boolean input)
	{
		dagger = input;
	}
	
	//setDamage is called when the dagger is picked up and the damage is now 1-30 damage.
	public static void setDamage()
	{
		damage = 60;
	}
	
	public static int getDamage()
	{
		Random rand = new Random();
		int attackDamage = 0;

		attackDamage = (int)(rand.nextInt(damage) + 10);

		return attackDamage;
	}
	
	public static int getHealth()
	{
		return health;
	}
	
	public static int getGold()
	{
		return goldCount;
	}
}
