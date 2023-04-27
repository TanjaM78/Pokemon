import java.util.Random;
import java.util.Scanner;

public class PokemonGame {

    Pokemon playerPokemon;
    Pokemon pcPokemon;
    public void choosePokemon(Pokedex pokedex) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(pokedex.pokemonList.size());
        System.out.println("Choose your Pokemon:");
        System.out.println("Enter the index number or name of the Pokemon:");

        Random random = new Random();
        int pcChoice = random.nextInt((798) + 1);
        String userInput = "";
        int index;
        boolean correctChoice = false;

        while (!correctChoice) {
            try {
                userInput = scanner.nextLine();
                // Try to parse input as an integer index
                index = Integer.parseInt(userInput) - 1; // Subtract 1 because the list is 0-indexed
                if (index >= 0 && index < pokedex.pokemonList.size()) {
                    playerPokemon = pokedex.pokemonList.get(Integer.parseInt(userInput) - 1);
                    pcPokemon = pokedex.pokemonList.get(Integer.parseInt(String.valueOf(pcChoice)) - 1);
                    System.out.println();
                    System.out.println("Name:" + " " + playerPokemon.getName());
                    System.out.println("Type1:" + " " + playerPokemon.getType1());
                    System.out.println("Type2:" + " " + playerPokemon.getType2());
                    // System.out.println("(1) " + playerPokemon.getAttack() + "\t\t" + "(" + pcPokemon.getAttack()) ;
                    System.out.println("HP:" + " " + playerPokemon.getHp());
                    System.out.println("Attack:" + " " + playerPokemon.getAttack());
                    System.out.println("Defense:" + " " + playerPokemon.getDefense());
                    System.out.println();
                    System.out.println("Your adversary has choosen Pokemon:\n" + "Name:" + " " + pcPokemon.getName());
                    System.out.println("Type 1:" + " " + playerPokemon.getType1());
                    System.out.println("Type 2:" + " " + playerPokemon.getType2());
                    System.out.println("HP:" + " " + playerPokemon.getHp());
                    System.out.println("Attack:" + " " + playerPokemon.getAttack());
                    System.out.println("Defense:" + " " + playerPokemon.getDefense());
                    correctChoice = true;
                } else {
                    System.out.println("Invalid index. Please enter a number between 1 and " + pokedex.pokemonList.size());
                }
            } catch (NumberFormatException e) {
                // Input is not a number, try to match it as a name
                Pokemon pok;
                for (int i = 0; i < pokedex.pokemonList.size(); i++) {
                    pok = pokedex.pokemonList.get(i);
                    if (userInput.equalsIgnoreCase(pok.getName())) {
                        index = i;
                        playerPokemon = pokedex.pokemonList.get(index);
                        pcPokemon = pokedex.pokemonList.get(Integer.parseInt(String.valueOf(pcChoice)) - 1);
                        System.out.println();
                        System.out.println("Name:" + " " + playerPokemon.getName());
                        System.out.println("Type1:" + " " + playerPokemon.getType1());
                        System.out.println("Type2:" + " " + playerPokemon.getType2());
                        // System.out.println("(1) " + playerPokemon.getAttack() + "\t\t" + "(" + pcPokemon.getAttack()) ;
                        System.out.println("HP:" + " " + playerPokemon.getHp());
                        System.out.println("Attack:" + " " + playerPokemon.getAttack());
                        System.out.println("Defense:" + " " + playerPokemon.getDefense());
                        System.out.println();
                        System.out.println("Your adversary has choosen Pokemon:\n" + "Name:" + " " + pcPokemon.getName());
                        System.out.println("Type 1:" + " " + playerPokemon.getType1());
                        System.out.println("Type 2:" + " " + playerPokemon.getType2());
                        System.out.println("HP:" + " " + playerPokemon.getHp());
                        System.out.println("Attack:" + " " + playerPokemon.getAttack());
                        System.out.println("Defense:" + " " + playerPokemon.getDefense());
                        correctChoice = true;
                    }
                }
                System.out.println("Invalid name. Please enter a valid name or index number.");
            }

            // Ask for input again
//            userInput = scanner.nextLine();
        }
    }

    public void battle() {
        System.out.println("------------");
        int playerPokemonHP = playerPokemon.getHp();
        int pcPokemonHP = pcPokemon.getHp();


        while (playerPokemonHP > 0 && pcPokemonHP > 0) {
            // player's turn
            double attackMod = Math.random();
            double defenseMod = Math.random() * 0.5;

            int playerDamage = (int) ((playerPokemon.getAttack() * attackMod) - (pcPokemon.getDefense() * defenseMod));
            if (playerDamage > 0) {
                pcPokemonHP -= playerDamage;
                System.out.println(playerPokemon.getName() + " did " + playerDamage + " damage to " + pcPokemon.getName() + ".");
            } else {
                System.out.println(playerPokemon.getName() + " did no damage to " + pcPokemon.getName() + ".");
            }

            // computer's turn
            attackMod = Math.random();
            defenseMod = Math.random() * 0.5;

            int pcDamage = (int) ((pcPokemon.getAttack() * attackMod) - (playerPokemon.getDefense() * defenseMod));
            if (pcDamage > 0) {
                playerPokemonHP -= pcDamage;
                System.out.println(pcPokemon.getName() + " did " + pcDamage + " damage to " + playerPokemon.getName() + ".");
            } else {
                System.out.println(pcPokemon.getName() + " did no damage to " + playerPokemon.getName() + ".");
            }
        }

        // determine winner
        if (playerPokemonHP <= 0) {
            System.out.println("Computer wins!");
        } else {
            System.out.println("Player wins!");
        }
    }
}

