package com.emmanuelrufasha.pocketbeastscardgame;

import interfaces.IAttackStrategy;
import interfaces.ICard;
import interfaces.IPlayerObserver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {  
    public static String getPrompt(String prompt, String[] validResponse){
        System.out.print(prompt);
        
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();
        
        if (Arrays.stream(validResponse).anyMatch(response::equals)) {
            return response;
        }
        
        return getPrompt(prompt, validResponse);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // --------------------------------------------------------------------- GAME INTRO ---------------------------------------------------------------------
        
        System.out.println("");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("Welcome to PocketBeasts!");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("");
        System.out.println("This basic console application tests our underlying software design patterns.");
        System.out.println("");
        System.out.println("Here's a key for each card:");
        System.out.println("");
        System.out.println("                             +----------+ ");
        System.out.println("                             |        M | ");
        System.out.println(" M = Mana Cost               |          | ");
        System.out.println("ID = Card identifier:        |    ID    | ");
        System.out.println(" A = Attack:                 |          | ");
        System.out.println(" H = Health:                 |          | ");        
        System.out.println("                             |A       H | ");
        System.out.println("                             +----------+ ");
        System.out.println("");
        System.out.println("New players each start with 15 Health and 1 Mana to spend on playing cards.");
        System.out.println("At the start of the game each player draws 4 cards from their deck to hand.");
        System.out.println("");
        System.out.println("Players each take turns. Each turn consists four phases:");
        System.out.println("1. Add mana (mana increases by one each turn and replenishes in full).");
        System.out.println("2. Draw a card.");
        System.out.println("3. Cycle through your cards in play (if any), choosing whether to attack.");
        System.out.println("   a. Attacking the other player directly with your card inflicts damage to their health.");
        System.out.println("      equal to the attack power of the card.");
        System.out.println("   b. Attacking another players beast will damage both cards (equal to their attack values).");
        System.out.println("   c. Any beast with <= 0 health is removed from the play field and placed into the graveyard.");
        System.out.println("4. Play cards from hand.");
        System.out.println("");
        
        // ---------------------------------------------------------- PLAYER INITIALISATION ----------------------------------------------------------

        System.out.println("> Welcome to PocketBeasts! \n  Press ENTER to continue...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        
        // Prompt for the number of players       
        int numberOfPlayersParticipating;      
        Scanner scanner = new Scanner(System.in);

        // Handle multiple players : 
        
        System.out.println(); // This line will print an empty line
        while (true) {
            System.out.println("> How many players will be participating in this game ? : ");
            if (scanner.hasNextInt()) {
                numberOfPlayersParticipating = scanner.nextInt();
//                System.out.println(""); // This line will print an empty line
                if (numberOfPlayersParticipating > 1) {
                    break; // valid input, exit the loop here
                } else {
                    System.out.println("> INVALID INPUT! Please enter a digit greater than '1'. \n");
                }
            } else {
                System.out.println("\n> INVALID INPUT! Please enter a digit. \n  In addition, ensure that your entry is a digit greater than '1' \n");
                scanner.next(); // clear invalid input
            }
        }
        scanner.nextLine();
        
        
        
        
        // Create a new instance of the DeckFactory
        DeckFactory deckFactory = new DeckFactory(); 
        //ADD PLAYERS FUBCTION IN THE PLAYERS CLASS*********************************************************************************************************************
        //Collection of all players playing.
        List<Player> players = new ArrayList<>();
        
        //Based on how many players playing, ask for their individual names. 
        for (int i = 1; i <= numberOfPlayersParticipating; i++) {
            while (true) {
                System.out.println("\n> Player " + i + ", enter your name: ");
                String playerName = scanner.next();
                //System.out.println(); // This line will print an empty line
                // Check if the name is valid using a regular expression
                if (playerName.matches("^[a-zA-Z][a-zA-Z0-9]*$")) {
                    players.add(new Player(playerName, deckFactory.createNewDeck()));
                    break; // valid input, exit the loop
                } 
                System.out.println("> INVALID INPUT! A Name cannot start with a symbol or number and cannot contain spaces.");
            }
        }scanner.nextLine();  // clears out the newline
        

        // Start the game
        System.out.println("\n> LET THE GAMES BEGIN... \n  Good Luck!");  
        //System.out.println("\n"); // Print an empty line
        
        
        System.out.println("\n>  Press ENTER to start the game...");
        Scanner start = new Scanner(System.in);
        start.nextLine();
        
        
        
        System.out.println(" ---------------------------------------------------------------------- Initial Game Board ----------------------------------------------------------------------  \n\n"); 
        for (Player player : players) {
            player.newGame();
            System.out.println(player);
        }
        System.out.println("\n ----------------------------------------------------------------------------------------------------------------------------------------------------------------  \n\n");
        
// MAIN METHOD SHOULD CREATE THE GAME CLASS THEN RUN IT
//ADD THE LOOP BELOW TO QA GAMELOGIC CLASS 

    // Create an instance of the AttackStrategy
    IAttackStrategy attackStrategy = new AttackStrategy();

    String winningMessage = "";
    Boolean run = true;
    while(run) {
        for (Player player : players) {
            // Add mana and draw card
            player.addMana();
            player.drawCard();
            System.out.println(player); // display cards
            
//            System.out.println(player.getName() + ", it's your turn! \n"); 


            // Get a list of all potential targets (other players)
            List<Player> potentialTargets = new ArrayList<>(players);
            potentialTargets.remove(player); // remove the current player from the list of targets

            if (potentialTargets.isEmpty()) {
                winningMessage = "Something has gone terribly wrong...";
                run = false;
                break;
            }

            
            
        // Loop through each player to play cards from their hand
        for (Player currentPlayer : players) {
            ArrayList<ICard> toRemove = new ArrayList<>();
            for (ICard card : currentPlayer.getHand().getCards()) {
                if (card.getManaCost() <= currentPlayer.getManaAvailable()) {         
                
                System.out.println("> " + player.getName() + ", it's your turn! \n");
                System.out.println(card.toString() + "\n\n> " + currentPlayer.getName() + " play " + card.getName() + "? (Yes/No): ");
                String play = scanner.nextLine().trim().toLowerCase();

                // Validate the input:
                while (!Arrays.asList("yes", "y", "no", "n").contains(play)) {
                    System.out.println("Invalid input. Please enter Yes or No.");
                    System.out.println("\n" + currentPlayer.getName() + ", would you like to play " + card.getName() + "? (Yes/No): ");
                    play = scanner.nextLine().trim().toLowerCase();
                }

                if (play.equals("yes") || play.equals("y")) {
                    currentPlayer.getInPlay().add(card);
                    currentPlayer.useMana(card.getManaCost());
                    toRemove.add(card);
                }
           
                    
                }
            }
            currentPlayer.getHand().removeAll(toRemove);
        }
            
            
            
            
            
            // Attack Loop
            for (ICard card : player.getInPlay().getCards()) {
                //System.out.println(card.toString() + "\n"); //print out cards


                System.out.println("\n" + card.toString() + "\n\n> "  + player.getName() + " attack with " + card.getName() + "? (Yes/No): ");
                String attack = scanner.nextLine().trim().toLowerCase();

                // Now you might want to validate the input:
                while (!Arrays.asList("yes", "y", "no", "n").contains(attack)) {
                    System.out.println("Invalid input. Please enter Yes or No.");
                    System.out.println(player.getName() + " attack with " + card.getName() + "? (Yes/No): ");
                    attack = scanner.nextLine().trim().toLowerCase();
                }

                if (attack.equalsIgnoreCase("Yes") || attack.equalsIgnoreCase("y")) {
                    // Choose a target player to attack
                    System.out.println("\n" + player.getName() + ", choose a player to attack:");

                    int selectedPlayerIndex = -1;
                    while (selectedPlayerIndex < 1 || selectedPlayerIndex > potentialTargets.size()) {
                        for (int i = 0; i < potentialTargets.size(); i++) {
                            System.out.println((i + 1) + ". " + potentialTargets.get(i).getName());
                        }
                        selectedPlayerIndex = scanner.nextInt(); 
                        scanner.nextLine();
                        if (selectedPlayerIndex < 1 || selectedPlayerIndex > potentialTargets.size()) {
                            System.out.println("Invalid choice. Please enter a valid number between 1 and " + potentialTargets.size() + ".");
                        } 
                    }

                    Player targetPlayer = potentialTargets.get(selectedPlayerIndex - 1);
                    System.out.println("\n" + player.getName() + ", you chose to attack " + targetPlayer.getName() + ". \n \n");

                    // Decide whether to attack the target player directly or one of their beasts
                    System.out.println("Which player would you like to attack? ");
                    System.out.println("1. " + targetPlayer.getName());

                    List<String> prompts = new ArrayList<>();
                    prompts.add("1");
                    int attackChoice = 2;
                    for (ICard otherCard : targetPlayer.getInPlay().getCards()) {
                        System.out.println(attackChoice + ". " + otherCard);
                        prompts.add(String.valueOf(attackChoice));
                        attackChoice++;
                    }

                    String target = getPrompt("Choose a number: ", prompts.toArray(new String[0]));

                    // If Target is "1" then we attack the player directly...
                    if (target.equals("1")) { 
                        attackStrategy.performAttack(player, targetPlayer, card);
                        if (targetPlayer.damage(card.getAttack())) {
                            winningMessage = player.getName() + " wins!";
                            run = false;
                            break;
                        }
                        for (IPlayerObserver observer : targetPlayer.getObservers()) {
                            observer.onHealthChange(targetPlayer.getName(), targetPlayer.getHealth());
                        }
                    }
                    // Else if Target is "2" or higher then we attack the selected beast...
                    else {
                        ICard targetCard = targetPlayer.getInPlay().getCard(Integer.parseInt(target) - 2);
                        attackStrategy.performAttack(player, targetPlayer, card, targetCard);
                    }
                }
            }
        if (!run) {
            break;
        }


        // Cycle through all players' cards and remove "dead" cards (health <= 0)
        for (Player currentPlayer : players) {
            ArrayList<ICard> toRemove = new ArrayList<>();
            for (ICard card : currentPlayer.getInPlay().getCards()) {
                if (card.getHealth() <= 0) {
                    toRemove.add(card);
                    currentPlayer.getGraveyard().add(card);
                }
            }
            currentPlayer.getInPlay().removeAll(toRemove);
        }

        // Print final play state for each player
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        for (Player currentPlayer : players) {
            System.out.println(currentPlayer);
        }

        }
        }
        
        System.out.println(winningMessage);
    }
}




                // Cycle through cards in play to attack - Attack LOOP
                //Print out all the cards in play for me ]
                //print out all the cards that are still in play for all the other players
                //ask current player, would you like to attack
                //what would you like to attack with
                //who would you like to attack?