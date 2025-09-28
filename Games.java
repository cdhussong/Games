import java.util.*;

public class Games {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char choice;

        do {
            displayMenu();
            System.out.print("Choose a game (1-6) or Q to quit: ");
            choice = sc.next().toUpperCase().charAt(0);

            switch (choice) {
                case '1': playLottery(sc); break;
                case '2': playCraps(sc); break;
                case '3': playScraps(sc); break;
                case '4': playRPS(sc); break;
                case '5': playRPSSpock(sc); break;
                case '6': playBlackjack(sc); break;
                case 'Q': System.out.println("Thanks for playing!"); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 'Q');

        sc.close();
    }

    // --- Menu ---
    public static void displayMenu() {
        System.out.println("\n--- Game Menu ---");
        System.out.println("1. Lottery");
        System.out.println("2. Craps");
        System.out.println("3. Scraps");
        System.out.println("4. Rock, Paper, Scissors");
        System.out.println("5. Rock, Paper, Scissors, Spock");
        System.out.println("6. Blackjack");
        System.out.println("Q. Quit");
    }

    // --- Game 1: Lottery ---
    public static void playLottery(Scanner sc) {
        Random rand = new Random();
        int lottery = rand.nextInt(90) + 10; // 2-digit number 10-99
        System.out.print("Enter a 2-digit number: ");
        int guess = sc.nextInt();

        int lotteryTens = lottery / 10;
        int lotteryOnes = lottery % 10;
        int guessTens = guess / 10;
        int guessOnes = guess % 10;

        System.out.println("Lottery number: " + lottery);

        if (guess == lottery) {
            System.out.println("Exact match! You win $10,000");
        } else if ((guessTens == lotteryOnes && guessOnes == lotteryTens) || 
                   (guessTens == lotteryTens && guessOnes == lotteryOnes)) {
            System.out.println("All digits match! You win $3,000");
        } else if (guessTens == lotteryTens || guessTens == lotteryOnes ||
                   guessOnes == lotteryTens || guessOnes == lotteryOnes) {
            System.out.println("One digit match! You win $1,000");
        } else {
            System.out.println("No match. Better luck next time!");
        }
    }

    // --- Game 2: Craps ---
    public static void playCraps(Scanner sc) {
        Random rand = new Random();
        double netWorth = 50.0;

        while (netWorth > 0) {
            System.out.printf("You have $%.2f\n", netWorth);
            System.out.print("Place your bet: ");
            double bet = sc.nextDouble();
            if (bet > netWorth || bet <= 0) {
                System.out.println("Invalid bet. Try again.");
                continue;
            }

            int die1 = rand.nextInt(6) + 1;
            int die2 = rand.nextInt(6) + 1;
            int total = die1 + die2;
            System.out.println("You rolled " + die1 + " + " + die2 + " = " + total);

            if (total == 7 || total == 11) {
                System.out.println("You win!");
                netWorth += bet;
            } else if (total == 2 || total == 3 || total == 12) {
                System.out.println("You lose!");
                netWorth -= bet;
            } else {
                int point = total;
                System.out.println("Point is set to " + point);
                boolean rolling = true;
                while (rolling) {
                    die1 = rand.nextInt(6) + 1;
                    die2 = rand.nextInt(6) + 1;
                    total = die1 + die2;
                    System.out.println("You rolled " + total);
                    if (total == point) {
                        System.out.println("You hit the point! You win!");
                        netWorth += bet;
                        rolling = false;
                    } else if (total == 7) {
                        System.out.println("Rolled a 7! You lose!");
                        netWorth -= bet;
                        rolling = false;
                    }
                }
            }

            if (netWorth <= 0) {
                System.out.println("You ran out of money!");
                break;
            }
        }
    }

    // --- Game 3: Scraps ---
    public static void playScraps(Scanner sc) {
        Random rand = new Random();
        double netWorth = 50.0;

        while (netWorth > 0) {
            System.out.printf("You have $%.2f\n", netWorth);
            System.out.print("Place your bet: ");
            double bet = sc.nextDouble();
            if (bet > netWorth || bet <= 0) {
                System.out.println("Invalid bet. Try again.");
                continue;
            }

            int d1 = rand.nextInt(8) + 1;
            int d2 = rand.nextInt(8) + 1;
            int d3 = rand.nextInt(8) + 1;
            int sum = d1 + d2 + d3;

            System.out.println("Rolled: " + d1 + ", " + d2 + ", " + d3 + " (Sum = " + sum + ")");

            boolean win = false, lose = false;
            if (d1 == 8 || d2 == 8 || d3 == 8) win = true;
            else if (sum == 9 || sum == 10 || sum == 14) win = true;
            else if (d1 == 1 || d2 == 1 || d3 == 1) lose = true;
            else if (sum == 8 || sum == 20 || sum == 23 || sum == 24) lose = true;
            else {
                int point = sum;
                System.out.println("Point is set to " + point);
                boolean rolling = true;
                while (rolling) {
                    d1 = rand.nextInt(8) + 1;
                    d2 = rand.nextInt(8) + 1;
                    d3 = rand.nextInt(8) + 1;
                    sum = d1 + d2 + d3;
                    System.out.println("Rolled: " + d1 + ", " + d2 + ", " + d3 + " (Sum = " + sum + ")");
                    if (d1 == 8 || d2 == 8 || d3 == 8) {
                        win = true;
                        rolling = false;
                    } else if (sum == 15) {
                        lose = true;
                        rolling = false;
                    } else if (sum == point) {
                        win = true;
                        rolling = false;
                    }
                }
            }

            if (win) {
                System.out.println("You win!");
                netWorth += bet;
            } else if (lose) {
                System.out.println("You lose!");
                netWorth -= bet;
            }

            if (netWorth <= 0) {
                System.out.println("You ran out of money!");
                break;
            }
        }
    }

    // --- Game 4: Rock, Paper, Scissors ---
    public static void playRPS(Scanner sc) {
        String[] options = {"Rock", "Paper", "Scissors"};
        Random rand = new Random();

        System.out.print("Enter 0 (Rock), 1 (Paper), 2 (Scissors): ");
        int user = sc.nextInt();
        int comp = rand.nextInt(3);

        System.out.println("You chose " + options[user]);
        System.out.println("Computer chose " + options[comp]);

        if (user == comp) System.out.println("It's a tie!");
        else if ((user == 0 && comp == 2) || (user == 1 && comp == 0) || (user == 2 && comp == 1))
            System.out.println("You win!");
        else System.out.println("You lose!");
    }

    // --- Game 5: Rock, Paper, Scissors, Spock ---
    public static void playRPSSpock(Scanner sc) {
        String[] options = {"Rock", "Paper", "Scissors", "Spock"};
        Random rand = new Random();

        System.out.print("Enter 0 (Rock), 1 (Paper), 2 (Scissors), 3 (Spock): ");
        int user = sc.nextInt();
        int comp = rand.nextInt(4);

        System.out.println("You chose " + options[user]);
        System.out.println("Computer chose " + options[comp]);

        if (user == comp) System.out.println("It's a tie!");
        else if ((user == 0 && (comp == 2)) || // Rock crushes scissors
                 (user == 1 && (comp == 0 || comp == 3)) || // Paper covers Rock or exposes Spock
                 (user == 2 && (comp == 1)) || // Scissors cuts Paper
                 (user == 3 && (comp == 0 || comp == 2))) // Spock crushes Rock & scissors
            System.out.println("You win!");
        else System.out.println("You lose!");
    }

    // --- Game 6: Blackjack ---
    public static void playBlackjack(Scanner sc) {
        ArrayList<String> deck = createDeck();
        Collections.shuffle(deck);

        ArrayList<String> player = new ArrayList<>();
        ArrayList<String> dealer = new ArrayList<>();

        // Initial deal
        player.add(deck.remove(0));
        dealer.add(deck.remove(0));
        player.add(deck.remove(0));
        dealer.add(deck.remove(0));

        boolean playerTurn = true;
        while (playerTurn) {
            System.out.println("Your hand: " + player + " (Total = " + handValue(player) + ")");
            if (handValue(player) > 21) {
                System.out.println("Busted! You lose.");
                return;
            }
            System.out.print("Hit or Stand? (H/S): ");
            char choice = sc.next().toUpperCase().charAt(0);
            if (choice == 'H') player.add(deck.remove(0));
            else playerTurn = false;
        }

        // Dealer turn
        while (handValue(dealer) <= 17) dealer.add(deck.remove(0));
        System.out.println("Dealer hand: " + dealer + " (Total = " + handValue(dealer) + ")");

        int playerTotal = handValue(player);
        int dealerTotal = handValue(dealer);

        if (dealerTotal > 21 || playerTotal > dealerTotal) System.out.println("You win!");
        else if (playerTotal == dealerTotal) System.out.println("Push!");
        else System.out.println("Dealer wins!");
    }

    // --- Blackjack helpers ---
    public static ArrayList<String> createDeck() {
        String[] suits = {"♠", "♥", "♦", "♣"};
        String[] values = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        ArrayList<String> deck = new ArrayList<>();
        for (String suit : suits) {
            for (String value : values) deck.add(value + suit);
        }
        return deck;
    }

    public static int handValue(ArrayList<String> hand) {
        int total = 0;
        int aceCount = 0;
        for (String card : hand) {
            String value = card.substring(0, card.length() - 1);
            if ("JQK".contains(value)) total += 10;
            else if (value.equals("A")) {
                total += 11;
                aceCount++;
            } else total += Integer.parseInt(value);
        }
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }
        return total;
    }
}
