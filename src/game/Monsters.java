package game;

import java.util.Random;

public class Monsters {
	
	public static String name;
	private static int maxDamage;
	private static int health;
	private static float toughness;
	private static String taunt;
	private static int gold;

	
	public Monsters()
	{

	}
	
	
	public static void setHealth(int input)
	{
		health = (int) (health - (input * toughness));
	}
	
	public static int getDamage()
	{
		Random rand = new Random();
		int damage;
		
		damage = (int)(rand.nextInt(maxDamage));
		
		return damage;
	}
	
	public static void setName(String input)
	{
		name = input;
	}
	
	public static void setTaunt(String input)
	{
		taunt = input;
	}
	
	public static void printMonster()
	{
		System.out.println();
		System.out.println();
		System.out.println(name+" has appeared");
		System.out.println(taunt);
	}


}
