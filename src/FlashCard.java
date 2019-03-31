import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayDeque;

/**
 * Creates a collection of flash cards read from a given file and then allows
 * the user to read through the cards in a random order and test themselves.
 *
 * @author TNorbury
 *
 */
public class FlashCard
{
    private static int QUESTION_INDEX = 0;
    private static int ANSWER_INDEX = 1;

    private static ArrayList<Card> createCards(String filePath)
         throws FileNotFoundException
    {
      String question;
      String answer;
      ArrayList<Card> cards = new ArrayList<>();
      Scanner in = new Scanner(new File(filePath), "UTF-8");
      String[] questionAndAnswer;

      // Iterate through the file creating flash cards until the end of file is
      // reached
      try
      {
         while (in.hasNext())
         {
            //Parse and split each line of the input.
            questionAndAnswer = in.nextLine().split(":", 2);

            //Get the question and answer from the line
            question = questionAndAnswer[QUESTION_INDEX];
            answer = questionAndAnswer[ANSWER_INDEX];

            // Create a new card and add it to the array list
            cards.add(new Card(question, answer));
         }
      }
      catch (NoSuchElementException e)
      {
         // This exception what thrown if there was a blank line(s) at the end
         // of the file. If this exception is thrown it can be assumed that the
         // EOF has been reached.
      }

      in.close();

      return cards;
    }


    private static void goThroughWrong(ArrayDeque<Card> wrongCards, Scanner userInput)
    {
        // Go through all the wrong cards until there aren't any left
        while (!wrongCards.isEmpty())
        {
            Card currentCard = wrongCards.poll();
            String input = answerCard(currentCard, userInput);
            if (!input.equals(""))
            {
                wrongCards.offer(currentCard);
                System.out.println("");
            }
        }
    }

    /*
     * Returns the user's response to the question
     */
    private static String answerCard(Card card, Scanner userInput)
    {
        // Display the card's question
        System.out.println(card.getQuestion());

        // Wait for the user to press enter before showing the answer
        System.out.println("Press enter to show the answer...");
        userInput.nextLine();
        System.out.println(card.getAnswer().trim());

        // Wait for for the user to press enter before going to the next
        // card
        System.out.println("Enter something if this card is wrong, then press enter, or press enter to continue\n");
        System.out.println(
        "=========================================================="
        + "=====================");

        return userInput.nextLine();
    }

    private static int parseArgForInt(String arg)
    {
        int num = 0;
        try
        {
            num = Integer.parseInt(arg);
        }
        catch (NumberFormatException e)
        {
            System.err.println(arg + " is not a number. This argument is being ignored");
        }

        return num;
    }

    public static void main(String[] args)
    {
        boolean again = true;
        boolean displayHelp = false;
        boolean filePathGiven = false;
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<String> cardFiles = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);
        String flashCardFilePath = "";
        int numQuestions = 0;
        int numRepeats = 0;
        boolean useRepeats = false;
        ArrayDeque<Card> wrong = new ArrayDeque<Card>();
        Card currentCard;

        // Print out a blank line to separate the program text from the command line invocation
        System.out.println("");

        // Iterate over the command line arguments and parse them
        if (args.length > 0)
        {
            for (int i = 0; i < args.length; i++)
            {
                switch (args[i])
                {
                    // Set the filepath of the cards
                    case "-f":
                    case "--filePath":
                        filePathGiven = true;
                        cardFiles.add(args[i + 1]);
                        break;

                    // Display the help dialogue
                    case "--help":
                        displayHelp = true;
                        break;
                    case "--flip":
                        QUESTION_INDEX = 1;
                        ANSWER_INDEX = 0;
                        break;
                    case "--num":
                        numQuestions = parseArgForInt(args[i + 1]);
                        break;
                    case "-r":
                    case "--repeat":
                        useRepeats = true;
                        numRepeats = parseArgForInt(args[i + 1]);
                        break;
                }
            }
        }

        // If no arguments were passed, then display the help dialogue
        else
        {
            displayHelp = true;
        }

        // if the displayHelp flag was set, then display delp
        if (displayHelp)
        {
            System.out.println("\nThis program takes a file given by the user and creates a set of flash cards from it.");
            System.out.println("USAGE:");
            System.out.println("\tjava FlashCards.class -f|--file filePathToCard [options]\n");
            System.out.println("OPTIONS:");
            System.out.println("\t-f | --filePath file path \tGive the program the filepath of the notecard file."
            + "\n\t\t\t\t\tNote: if this is not set then the program will not run\n");
            System.out.println("\t--flip \t\t\t\tTreat the backside of the card as the front\n");
            System.out.println("\t--help \t\t\t\tDisplay this help dialogue\n");
            System.out.println("\t--num int\t\t\t\tNumber of cards to go through\n");
            System.out.println("\t-r | --repeat int\t\t\t\tNumber of times to repeat going through the cards\n");
            System.out.println("FLASH CARD FORMAT:");
            System.out.println("\tThe format for the flashcard file is as follows:");
            System.out.println("\t\tquestion:answer");
            System.out.println("\t\tquestion:answer");
            System.out.println("\t\tquestion:answer");

            System.exit(0);
        }


        for (int i = 0; i < cardFiles.size(); i++)
        {
            try
            {
              cards.addAll(createCards(cardFiles.get(i)));
              again = true;
            }
            catch (FileNotFoundException e)
            {
              System.err.println("Couldn't find the specified file:" + cardFiles.get(i));
            }
        }

        // If the number of question cards to go through wasn't given as a
        // parameter, then we'll go through all the cards!
        if (numQuestions == 0)
        {
            numQuestions = cards.size();
        }

        System.out.println(
        "=========================================================="
        + "=====================\n");

        while (again)
        {
            // Shuffle the cards so that they're in random order.
            Collections.shuffle(cards);


            for (int i = 0; i < numQuestions; i++)
            {
                // Once all the cards have been read, shuffle them
                if ((i % cards.size()) == 0)
                {
                    // Shuffle the cards so that they're in random order.
                    Collections.shuffle(cards);
                    goThroughWrong(wrong, userInput);
                }

                currentCard = cards.get((i % cards.size()));
                String input = answerCard(currentCard, userInput);
                if (!input.equals(""))
                {
                    wrong.offer(currentCard);
                    System.out.println("");
                }
            }

            goThroughWrong(wrong, userInput);

            // If the user said how many times they want to repeat, then don't ask
            // them if they want to repeat
            if (useRepeats)
            {
                if (numRepeats > 0)
                {
                    again = true;
                    numRepeats --;
                }
                else
                {
                    again = false;
                }
            }

            else
            {
                // Ask the user if the want to go through the cards again
                System.out.print("Do you want to go through the cards again?(y/n) ");
                if (userInput.nextLine().toLowerCase().equals("y"))
                {
                    again = true;
                    System.out.println(
                    "=========================================================="
                    + "=====================\n");
                }
                else
                {
                    again = false;
                }
            }
        }
        userInput.close();
    }

}
