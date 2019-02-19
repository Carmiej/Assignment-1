package game;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

/*
 * The main of my game is the engine
 * this initializes all of the variables that i will need throughout the game such as the roomLayout array.
 * @author Blake Mclean 15904394
 */
public class Engine {
	
	public static int[][] roomLayout;
	public static int[] roomIndex;
	public static String[] descriptions; 
	public static boolean[] dagger;
	public static int[] gold;
	public static String[] items;
	public static String isGold = "gold";
	public static String isDagger = "dagger";
	//this will only let the monsters spawn after the first room;
	public static int monsterTimer;
	public static boolean[] exit;
	public static int roll = 0;
	
	public static final String OPEN = "open";
	public static final String DOOR = "door";
	public static final String PICKUP = "pickup";
	public static final String ITEM = "item";
	public static final String GOLD = "gold";
	public static final String DAGGER = "dagger";
	public static final String HELP = "help";
	public static final String SEARCH = "search";
	
	public static final String ATTACK = "attack";


	
	/*
	 * This method splits up strings by ",". This is for the number and item arrays.
	 * @param input is coming from the map file that i read in and send it to this method
	 * @author Blake Mclean 15904394
	 */
	public static String[] tokenizeInput(String input)
	{
		String numberLine = input;
		return numberLine.split(",");
	}
	
	/*
	 * This method splits up strings by " ". This is for interpreting commands from the player
	 * @param input is coming from the map file that i read in and send it to this method
	 * @author Blake Mclean 15904394
	 */
	public static String[] tokenizeString(String input)
	{
		String numberLine = input;
		return numberLine.split(" ");
	}

	/*
	 * This method takes in a string array and turns it into an int array
	 * @param tokens is the array of Strings created by the tokenizeString method
	 * @author Blake Mclean 15904394
	 */
	public static int[] toIntegerArray(String[] tokens)
	{
		int[] numbers;
	
		numbers = new int[tokens.length];
	
		for(int i=0;i<tokens.length;i++)
		{
			numbers[i] = new Integer(tokens[i]).intValue();
		}
		
		return numbers;
	}
	
	/*
	 * This method turns a single string into an int
	 * @param input is coming the command method to get the number of the door that the player used in the command.
	 * @author Blake Mclean 15904394
	 */
	public static int toInteger(String tokens)
	{
		int number;
			number = new Integer(tokens).intValue();
		
		return number;
	}
	
	/*
	 * This method checks if the items string contains the work gold and if it does it assigns a random amount to the room from 1 - 500.
	 * @param items is a string array that is coming from the mapLoad method
	 * @author Blake Mclean 15904394
	 */
	public static int goldCheck(String[] items)
	{
		int amount = 0;
		Random rand = new Random();
		int arrayLength = items.length;
		int counter = 0;
		
		
		while (counter < arrayLength)
		{
		
			if (items[counter].contains(isGold))
			{
				amount = (rand.nextInt(500)) + 1;
			}
			else
			{
				amount = 0;
			}
			++counter;
		}
		return amount;
	}
	
	/*
	 * This method checks if the items string array contains the work dagger if so it will set the array for that room to true
	 * @param items is a string array that is coming from the mapLoad method
	 * @author Blake Mclean 15904394
	 */
	public static boolean daggerCheck(String[] items)
	{
		boolean dagger = false;
		int arrayLength = items.length;
		int counter = 0;
		
		
		while (counter < arrayLength)
		{
		
			if (items[0].contains(isDagger))
			{
				dagger = true;
			}
			else
			{
				dagger = false;
			}
			++counter;
		}
		return dagger;
	}
	
	/*
	 * This method loads the mapfile and stores all of the map details into array for later use.
	 * @param roomLayout is the array that stores all of the data about which rooms are connected to which.
	 * @param descriptions stores all of the descriptions for each room.
	 * @param roomIndex does nothing
	 * @param gold stores the amound of gold in each room
	 * @param dagger tells the engine if there is a dagger in a room or not
	 * @param exit tells the engine which room the exit is in
	 * @author Blake Mclean 15904394
	 */
	public static void MapLoad() throws IOException
	{
		Scanner scanner = new Scanner(new File("input/gamemap.txt"));
		
		int numberOfRooms = scanner.nextInt();
		
		roomLayout = new int[numberOfRooms][];
		descriptions = new String[5];
		roomIndex = new int[5];
		gold = new int[5];
		dagger = new boolean[5];
		items = new String[3];
		exit = new boolean[5];
		
		for (int x = 0; x < 5; ++x)
		{
			roomIndex[x] = scanner.nextInt();
			scanner.nextLine();
			scanner.nextLine();
			descriptions[x] = scanner.nextLine();
			scanner.nextLine();
			String[] tokens=tokenizeInput(scanner.nextLine());
			roomLayout[x] = toIntegerArray(tokens);
			scanner.nextLine();	
			items = tokenizeInput(scanner.nextLine());
			gold[x] = goldCheck(items);
			dagger[x] = daggerCheck(items);
		}
		
		exit[0] = false;
		exit[1] = false;
		exit[2] = false;
		exit[3] = true;
		exit[4] = false;
		
		System.out.println("Map has sucesfully loaded.");
		System.out.println("-----------------------------------------------------------------");	
		System.out.println("Welcome to my game Monsters And Dungeons?");
		System.out.println("Your objective is to move throgh the maze and find the exit in one if the rooms and have atleast $500 gold.");
		System.out.println("Each room will have a random amount of gold from 1-500 and each monster will have 250.");
		System.out.println("There is also a dagger in one of the rooms to help you in your endevours.");
		System.out.println("Caution - every command you type will have a 4 in 20 chance to spawn a monster.");
		System.out.println("So be strategic and plan your moves.");
		System.out.println("Goodluck and may the odds be ever in your favour");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("To play the game use the commands:");
		System.out.println("open door n - (to open the door marked n)");
		System.out.println("pickup n - (to pickup items n)");
		System.out.println("help - to see avaliable commands");
		System.out.println("When in battlemode use the commands:");
		System.out.println("attack - to attack the monster.");
		System.out.println("This game is hardcore there is no running from the monsters.");
		System.out.println("-----------------------------------------------------------------");
		
	}
	
	/*
	 * This method rolls a number from 1-20 and the numbers 1, 12, 19 and 20 all have a corresponding monster that will spawn
	 * if that number is rolled. to spawn the monster it calls the method battle(monsterName)
	 * @param roll if the randomly generated number
	 * @author Blake Mclean 15904394
	 */
	public static void randomEncounter(Player player1, Dragon dragon, Goblin goblin, Orc orc, Zombie zombie)
	{
		Random rand = new Random();
		
		roll = rand.nextInt(20)+1;
		
		if (roll == 1)
		{
			dragon.printDragon();
			if (dragon.getHealth() > 0)
			{
				battleDragon(player1, dragon);
				monsterTimer = -4;
				System.out.println("Please press enter to continue");
			}
			
		}
		if (roll == 19)
		{
			goblin.printGoblin();
			if (goblin.getHealth() > 0)
			{
			battleGoblin(player1, goblin);
			monsterTimer = -4;
			System.out.println("Please press enter to continue");
			}
			
		}
		if (roll == 12)
		{
			orc.printOrc();
			if (orc.getHealth() > 0)
			{
			battleOrc(player1,orc);
			monsterTimer = -4;
			System.out.println("Please press enter to continue");
			}
			
		}
		if (roll == 20)
		{
			zombie.printZombie();
			if (zombie.getHealth() > 0)
			{
			battleZombie(player1, zombie);
			monsterTimer = -4;
			System.out.println("Please press enter to continue");
			}
			
		}
	}
	
	/*
	 * This method is the battlemode for fighting the dragon
	 * @author Blake Mclean 15904394
	 */
	public static void battleDragon(Player player1, Dragon dragon)
	{
		Scanner scanner = new Scanner(System.in);
		String command;
		
		int playerDamage = 0;
		int dragonDamage = 0;
		String[] tokenString;
		
		System.out.println("-----------------------------------------------------------------");
		System.out.println("You have enterd battlemode.");
		System.out.println("-----------------------------------------------------------------");
		
		while (dragon.getHealth() > 0)
		{
		
			System.out.println();
			System.out.print("Command? ");
			command = scanner.nextLine();
			command = command.toLowerCase();
			tokenString = tokenizeString(command);
		
			switch(tokenString[0])
			{
				case ATTACK:
				{
					playerDamage = player1.getDamage();
					dragon.setHealth(playerDamage);
					System.out.println(dragon.printName()+" has lost "+playerDamage+" damage.");
					System.out.println("-----------------------------------------------------------------");
					if (dragon.getHealth() <= 0)
					{
						System.out.println("-----------------------------------------------------------------");
						System.out.println(dragon.printName()+" has fallen");
						player1.setGold(dragon.getGold());
						System.out.println("Your gold count is now: "+player1.goldCount);
						System.out.println("-----------------------------------------------------------------");
					}
					else
					{
						dragonDamage = dragon.getDamage();
						player1.setHealth(dragonDamage);
						System.out.println("You take "+dragonDamage+" damage");
						System.out.println("-----------------------------------------------------------------");
					}
					if (player1.getHealth() < 0)
					{
						System.out.println("-----------------------------------------------------------------");
						System.out.println("You have died, better luck next time.");
						System.out.println("-----------------------------------------------------------------");
						System.exit(0);
						break;
					}
					else
					{
					// do nothing
					}
				}
				break;
			
			}
			System.out.println("-----------------------------------------------------------------");
		}
		
	}
	
	/*
	 * This method is the battlemode for fighting the goblin
	 * @author Blake Mclean 15904394
	 */
	public static void battleGoblin(Player player1, Goblin goblin)
	{
		Scanner scanner = new Scanner(System.in);
		String command;
		
		int playerDamage = 0;
		int dragonDamage = 0;
		String[] tokenString;
		
		System.out.println("-----------------------------------------------------------------");
		System.out.println("You have enterd battlemode.");
		System.out.println("-----------------------------------------------------------------");
		
		while (goblin.getHealth() > 0)
		{
		
			System.out.println();
			System.out.print("Command? ");
			command = scanner.nextLine();
			command = command.toLowerCase();
			tokenString = tokenizeString(command);
		
			switch(tokenString[0])
			{
				case ATTACK:
				{
					playerDamage = player1.getDamage();
					goblin.setHealth(playerDamage);
					System.out.println(goblin.printName()+" has lost "+playerDamage+" damage.");
					System.out.println("-----------------------------------------------------------------");
					if (goblin.getHealth() <= 0)
					{
						System.out.println("-----------------------------------------------------------------");
						System.out.println(goblin.printName()+" has fallen");
						player1.setGold(goblin.getGold());
						System.out.println("Your gold count is now: "+player1.goldCount);
						System.out.println("-----------------------------------------------------------------");
					}
					else
					{
						dragonDamage = goblin.getDamage();
						player1.setHealth(dragonDamage);
						System.out.println("You take "+dragonDamage+" damage");
						System.out.println("-----------------------------------------------------------------");
					}
					if (player1.getHealth() < 0)
					{
						System.out.println("-----------------------------------------------------------------");
						System.out.println("You have died, better luck next time.");
						System.out.println("-----------------------------------------------------------------");
						System.exit(0);
						break;
					}
					else
					{
					// do nothing
					}
				}
				break;
			
			}
			System.out.println("-----------------------------------------------------------------");
		}
		
	}
	
	/*
	 * This method is the battlemode for fighting the orc
	 * @author Blake Mclean 15904394
	 */
	public static void battleOrc(Player player1, Orc orc)
	{
		Scanner scanner = new Scanner(System.in);
		String command;
		
		int playerDamage = 0;
		int dragonDamage = 0;
		String[] tokenString;
		
		System.out.println("-----------------------------------------------------------------");
		System.out.println("You have enterd battlemode.");
		System.out.println("-----------------------------------------------------------------");
		
		while (orc.getHealth() > 0)
		{
		
			System.out.println();
			System.out.print("Command? ");
			command = scanner.nextLine();
			command = command.toLowerCase();
			tokenString = tokenizeString(command);
		
			switch(tokenString[0])
			{
				case ATTACK:
				{
					playerDamage = player1.getDamage();
					orc.setHealth(playerDamage);
					System.out.println(orc.printName()+" has lost "+playerDamage+" damage.");
					System.out.println("-----------------------------------------------------------------");
					if (orc.getHealth() <= 0)
					{
						System.out.println("-----------------------------------------------------------------");
						System.out.println(orc.printName()+" has fallen");
						player1.setGold(orc.getGold());
						System.out.println("Your gold count is now: "+player1.goldCount);
						System.out.println("-----------------------------------------------------------------");
					}
					else
					{
						dragonDamage = orc.getDamage();
						player1.setHealth(dragonDamage);
						System.out.println("You take "+dragonDamage+" damage");
						System.out.println("-----------------------------------------------------------------");
					}
					if (player1.getHealth() < 0)
					{
						System.out.println("-----------------------------------------------------------------");
						System.out.println("You have died, better luck next time.");
						System.out.println("-----------------------------------------------------------------");
						System.exit(0);
						break;
					}
					else
					{
					// do nothing
					}
				}
				break;
			
			}
			System.out.println("-----------------------------------------------------------------");
		}
		
	}
	
	/*
	 * This method is the battlemode for fighting the zombie
	 * @author Blake Mclean 15904394
	 */
	public static void battleZombie(Player player1, Zombie zombie)
	{
		Scanner scanner = new Scanner(System.in);
		String command;
		
		int playerDamage = 0;
		int dragonDamage = 0;
		String[] tokenString;
		
		System.out.println("-----------------------------------------------------------------");
		System.out.println("You have enterd battlemode.");
		System.out.println("-----------------------------------------------------------------");
		
		while (zombie.getHealth() > 0)
		{
		
			System.out.println();
			System.out.print("Command? ");
			command = scanner.nextLine();
			command = command.toLowerCase();
			tokenString = tokenizeString(command);
		
			switch(tokenString[0])
			{
				case ATTACK:
				{
					playerDamage = player1.getDamage();
					zombie.setHealth(playerDamage);
					System.out.println(zombie.printName()+" has lost "+playerDamage+" damage.");
					System.out.println("-----------------------------------------------------------------");
					if (zombie.getHealth() <= 0)
					{
						System.out.println("-----------------------------------------------------------------");
						System.out.println(zombie.printName()+" has fallen");
						player1.setGold(zombie.getGold());
						System.out.println("Your gold count is now: "+player1.goldCount);
						System.out.println("-----------------------------------------------------------------");
					}
					else
					{
						dragonDamage = zombie.getDamage();
						player1.setHealth(dragonDamage);
						System.out.println("You take "+dragonDamage+" damage");
						System.out.println("-----------------------------------------------------------------");
					}
					if (player1.getHealth() < 0)
					{
						System.out.println("-----------------------------------------------------------------");
						System.out.println("You have died, better luck next time.");
						System.out.println("-----------------------------------------------------------------");
						System.exit(0);
						break;
					}
					else
					{
					// do nothing
					}
				}
				break;
				case HELP:
				{
					System.out.println("attack - to attack the monster.");
				}
					break;
			
			}

		}
		
	}
	
	/*
	 * This method interprets the commands given by the player and calls the right method
	 * @author Blake Mclean 15904394
	 */
	public static void command(Player player1, Dragon dragon, Goblin goblin, Orc orc, Zombie zombie)
	{
		if (monsterTimer > 0)
		{
		randomEncounter(player1, dragon, goblin, orc, zombie);
		}
		++monsterTimer;
		
		
		Scanner scanner = new Scanner(System.in);
		String command;
		String[] tokenString;
		
		System.out.println();
		System.out.print("Command? ");
		command = scanner.nextLine();
		command = command.toLowerCase();
		tokenString = tokenizeString(command);		
		
		switch(tokenString[0])
		{
			case OPEN:
				if (tokenString.length == 3)
				{
					switch (tokenString[1])
					{
						case DOOR:
							if (tokenString.length == 3)
							{
								int arrayLength = new Integer(roomLayout[player1.currentRoom-1].length);
								int counter = 0;
								int number = toInteger(tokenString[2]);
								while (counter < arrayLength)
								{
									if (number == (roomLayout[player1.currentRoom-1][counter]))
									{
										player1.setRoom(new Integer(tokenString[2]).intValue());
										break;
									}
									++counter;
								}
							}
							else
							{
								System.out.println("Invalid command");
							}
							break;
					}
				}
				else
				{
					System.out.println("Invalid command");
				}
					break;
			case PICKUP:
				switch (tokenString[1])
				{
					case GOLD:
						if (gold[player1.currentRoom-1] > 0)
						{
							player1.setGold(gold[player1.currentRoom-1]);
							gold[player1.currentRoom-1] = 0;
							System.out.println("Your gold count is now: "+player1.goldCount);
						}
						else
						{
							System.out.println("There is no gold in this room.");
						}
						break;
					case DAGGER:
						if (dagger[player1.currentRoom-1] == true)
						{
							player1.setDagger(true);
							dagger[player1.currentRoom-1] = false;
							System.out.println("you now carry a dagger and your damage is increased.");
						}
						else
						{
							System.out.println("There is no dagger in this room");
						}
						break;
				}
				break;
			case "":
			{
				System.out.println("Please enter a command");
				break;
			}
			case HELP:
			{
				System.out.println("open door n - (to open the door marked n)");
				System.out.println("pickup n - (to pickup items n)");
				System.out.println("help - to see avaliable commands");
				System.out.println("When in battlemode use the commands:");
				break;
			}
			case SEARCH:
				if (exit[player1.currentRoom-1] == true)
				{
					if (player1.getGold() >= 500)
					{
						System.out.println("-----------------------------------------------------------------");
						System.out.println("Congratulations you have completed the game and you found "+player1.getGold()+" gold");
						System.out.println("-----------------------------------------------------------------");
						System.exit(0);
					}
					else
					{
						System.out.println("-----------------------------------------------------------------");
						System.out.println("Congratulations you found the exit but you do not have enough gold");
						System.out.println("Come back when you have 500 gold or more");
					}
				}
				else
				{
					System.out.println("There is no exit in here.");
				}
				break;
		}
		System.out.println("-----------------------------------------------------------------");
	}

	/*
	 * This method prints out the current room using the player1.currentRoom function from the class player
	 * @author Blake Mclean 15904394
	 */			
	public static void printRoom(Player player1, Dragon dragon, Goblin goblin, Orc orc, Zombie zombie)
	{
		System.out.println(descriptions[player1.currentRoom - 1]);
		
		if (gold[player1.currentRoom-1] > 0)
		{
			System.out.println("There is "+gold[player1.currentRoom-1]+" gold coins on the ground infront of you.");
		}
		
		if (dagger[player1.currentRoom-1] == true)
		{
			System.out.println("There is a dagger on the floor infront of you.");
		}
		System.out.print("There are doors with numbers: ");
		int counter = 0;
		int arrayLength = roomLayout[player1.currentRoom-1].length;
		while (counter < arrayLength)
		{
			System.out.print(roomLayout[player1.currentRoom-1][counter]+" ");
			++counter;
		}
		command(player1, dragon, goblin, orc, zombie);
	}
	
	/*
	 * This is the main where all monsters and the player are initialized and calls the printroom function.
	 * @author Blake Mclean 15904394
	 */
	public static void main(String[] args) throws IOException
	{
		MapLoad();
		Player player1 = new Player();
		
		Dragon dragon = new Dragon();
		Goblin goblin = new Goblin();
		Orc orc = new Orc();
		Zombie zombie = new Zombie();
		
		while (player1.health > 0)
		{
			printRoom(player1, dragon, goblin, orc, zombie);
		}
	}

}
