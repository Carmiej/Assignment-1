package game;

import java.util.Random;

import java.util.Random;

public class Dragon {
	
	private static String name;
	private static String taunt;
	private static int health;
	private static int damage;
	private static int gold;
	
	public Dragon()
	{
		name = "Darilth";
		taunt =  "You dare to wake me!";
		health = 120;
		damage = 10;
		gold = 250;
	}
	
	public static int getDamage()
	{
		Random rand = new Random();
		int attackDamage = 0;
		
		attackDamage = (int)(rand.nextInt(damage));
		
		return attackDamage;
	}
	
	public static void setHealth(int input)
	{
		health = health - input;
	}
	
	public static void printDragon()
	{
		System.out.println();
		System.out.println();
		System.out.println(name+" has appeared");
		System.out.println(taunt);
		System.out.println(name+" has "+health+" health");
	}
	
	public static String printName()
	{
		return name;
	}
	
	public static int getHealth()
	{
		return health;
	}

	public static int getGold()
	{
		return gold;
	}
}

