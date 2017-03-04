package ss.labs.pokemon;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<PokemonData> pokedex = new ArrayList();
        HashMap<String, Integer> typeCount = new HashMap();
        HashMap<String, Integer> result = new HashMap();
        Random random = new Random();
        String[] poketype = new String[10];

        System.out.println("Enter the Number of pokemon in the pokedex:");
        int a = scanner.nextInt();   //TODO 0: scan the number of the testData from user input
        int k=0;

        //TODO use a loop to read several input data
        for (int i=0; i<a; i++) {
            String name = scanner.next();    //scan pokemon name and save to a variable from user input
            String type = scanner.next().toLowerCase();    //scan pokemon type and save to a variable
            String move = scanner.next();    //scan pokemon move's name

            PokemonData pokedata = new PokemonData(name, type, move);   //create a new pokemon data from the above variables
            pokedex.add(pokedata);  //add the pokemon data into pokedex

            //to initialize the result in order to check wether the type has been printed or not in the end
            if (!result.containsKey(type)) {
                result.put(type,0);
                typeCount.put(type,0);
                poketype[k] = type;
                k++;
            }
        }

        System.out.println ("Game start!");
        int score=0;
        int round=0;
        while(true) {   //the game routine
            int i = random.nextInt(a);  //randomly choose a pokemon in the pokedex
            //post the pokemon's information
            System.out.println ("name: "+pokedex.get(i).name+", type: "+pokedex.get(i).type+", move's name: "+pokedex.get(i).move);
            System.out.println ("Enter your reaction: ");
            String move = scanner.next();
            if (move.equals ("quit")) { //key in 'quit' to quit the game
                System.out.println ("You quit the Game");
                break;
            }
            String type = pokedex.get(i).type;
            type = type;
            switch (move) {
                case "catch" :
                    System.out.println ("You catch "+pokedex.get(i).name);
                    if (typeCount.containsKey(type)) {  //count the type of different kinds
                        typeCount.put (type, typeCount.get(type)+1);
                    } else {
                        typeCount.put (type, 1);
                    }
                    if (type.equals("grass")) score++;  //count the score by rule
                    else score--;
                    break;
                case "run" :
                    System.out.println ("Run away successfully!");
                    if (type.equals("grass")) score--;  //count the score by rule
                    else score++;
                    break;
                default :
                    System.out.println ("You type the wrong command! The pokemon runs away!");
                    break;
            }
        }
        System.out.println ("Score: "+score);
        for (int i=0; i<k; i++) {
            String type = poketype[i];

            if (typeCount.get(type)!=0) {
                if (result.get(type).equals(0)) {
                    System.out.println(type + ": " + typeCount.get(type));
                    result.put(type, 1); //to tell the system that you have already printed the amount of the type ypu caught
                }
            }
        }
    }
}
