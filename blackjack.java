package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class main {
    public static void deckshuffle() {
        ArrayList<String> deck = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        String[] suits = {"♥", "♣", "♦", "♠"};
        String[] ranks = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

        int t = 0;
        int dealeracecounter = 0;

        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                String card = suits[i] + ranks[j];
                deck.add(card);
            }
        }

        Collections.shuffle(deck);

        ArrayList<String> playerhand = new ArrayList<>();	
        ArrayList<String> dealerhand = new ArrayList<>();

        for (int p = 0; p < 2; p++) {
            String firstcard = deck.remove(0);
            String secondcard = deck.remove(0);
            playerhand.add(firstcard);
            dealerhand.add(secondcard);
        }

        int sumplayerhand = 0;
        int acecounter = 0;

        for (int n = 0; n < playerhand.size(); n++) {
            t = n;

            String card = playerhand.get(n);
            String value = card.substring(1);

            if (value.equals("A")) {
                acecounter += 1;
            }

            int cardvalue = calc(playerhand, n);
            sumplayerhand += cardvalue;
        }

        int sumdealerhand = 0;

        for (int n = 0; n < dealerhand.size(); n++) {
            t = n;

            String card = dealerhand.get(n);
            String value = card.substring(1);

            if (value.equals("A")) {
                dealeracecounter++;
            }

            int cardvalue = calc(dealerhand, n);
            sumdealerhand += cardvalue;

            while (sumdealerhand > 21 && dealeracecounter >= 1) {
                sumdealerhand -= 10;
                dealeracecounter -= 1;
            }
        }
        if(sumplayerhand == 21 &&playerhand.size()==2 && sumdealerhand != 21) {
        	System.out.println("BLACKJACK! YOU WON!");
        	System.out.println("Your starting hand was " + playerhand);
            System.out.println("The value of your hand was " + sumplayerhand);
            System.out.println("Dealer hand was " + dealerhand);
        	return;
        	
        	
        }
        if(sumdealerhand == 21 &&dealerhand.size()==2 && sumplayerhand != 21) {
        	
        	System.out.println("BLACKJACK! the dealer WON!");
        	System.out.println("Dealer hand was " + dealerhand);
        	System.out.println("Your starting hand was " + playerhand);
            System.out.println("The value of hand was " + sumplayerhand);
            
        	return;
        	
        	
        }

        System.out.println("Your starting hand is " + playerhand);
        System.out.println("The value of your hand is " + sumplayerhand);
        System.out.println("Dealer hand is " + dealerhand.get(0) + " ??");

        System.out.println("would you like to H or S?");
        char playerdecision = scanner.next().charAt(0);

        while (sumplayerhand <= 21) {
            if (playerdecision != 'H' && playerdecision != 'S') {
                System.out.println("would you like to H or S?");
                playerdecision = scanner.next().charAt(0);
            }

            if (playerdecision == 'H') {
                String firstcard = deck.remove(0);
                playerhand.add(firstcard);
                int cardvalue = calc(playerhand, playerhand.size() - 1);
                sumplayerhand += cardvalue;

                
                    if (firstcard.substring(1).equals("A")) {
                        acecounter++;
                    }
                    while (sumplayerhand > 21 && acecounter >= 1) {
                        sumplayerhand -= 10;
                        acecounter -= 1;
                    }
                

                

                System.out.println("your added card is " + firstcard);
                System.out.println("your points is " + sumplayerhand);

                if (sumplayerhand > 21) {
                    System.out.println("youve LOST!");
                    break;
                }

                System.out.println("would you like to H or S?");
                playerdecision = scanner.next().charAt(0);
            }

            if(playerdecision == 'S') {
            		
            	while(sumdealerhand <17) {
            		
                     	String secondcard = deck.remove(0);
                         dealerhand.add(secondcard);
                         int dealercardvalue = calc(dealerhand, dealerhand.size() - 1);
                         sumdealerhand += dealercardvalue;
                        

                         
                         if (secondcard.substring(1).equals("A")) {
                             dealeracecounter++;
                         }
                         while (sumdealerhand > 21 && dealeracecounter >= 1) {
                             sumdealerhand -= 10;
                             dealeracecounter -= 1;
                         }
                 
            	}
            	 System.out.println("the dealer hand is " + dealerhand);
                 System.out.println("the dealer total points is " + sumdealerhand);
            	
            		
            		
              
                	if (sumdealerhand > 21) {
                        System.out.println("youve WON!");

                        break;
                    }
                	
                	else if(sumplayerhand > sumdealerhand){
                    	System.out.println("youve WON");
                    	break;
                    	
                    }
                	
                	else if(sumdealerhand >sumplayerhand) {
                		System.out.println("youve LOST!");
                		break;
                	}
                	
                	else if(sumplayerhand == sumdealerhand) {
                		System.out.print("tie!");
                		break;
                	}
                  
            }
             
        }
        
    }
        
                


            
        
    

    public static int calc(ArrayList<String> hand, int p) {
        String value = hand.get(p).substring(1);

        if (value.equals("J") || value.equals("Q") || value.equals("K")) return 10;
        if (value.equals("A")) return 11;

        return Integer.parseInt(value);
    }

    public static void main(String[] args) {
        deckshuffle();
    }
}
