import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Creates a collection of flash cards read from a given file and then allows
 * the user to read through the cards in a random order and test themselves.
 * 
 * @author TNorbury
 *
 */
public class FlashCard
{
   private static final int QUESTION_INDEX = 0;
   private static final int ANSWER_INDEX = 1;
   
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


   public static void main(String[] args)
   {
      boolean again = true;
      boolean displayHelp = false;
      boolean filePathGiven = false;
      ArrayList<Card> cards = null;
      Scanner userInput = new Scanner(System.in);
      String flashCardFilePath = "";

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
                  flashCardFilePath = args[i + 1];
                  break;

               // Display the help dialogue
               case "--help":
                  displayHelp = true;
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
         System.out.println("\tjava FlashCards.class [options]\n");
         System.out.println("OPTIONS:");
         System.out.println("\t-f | --filePath file path \tGive the program the filepath of the notecard file."
            + "\n\t\t\t\t\tNote: if this is not set then the program will not run\n");
         System.out.println("\t--help \t\t\t\tDisplay this help dialogue\n");
         System.out.println("FLASH CARD FORMAT:");
         System.out.println("\tThe format for the flashcard file is as follows:");
         System.out.println("\t\tquestion:answer");
         System.out.println("\t\tquestion:answer");
         System.out.println("\t\tquestion:answer");

         System.exit(0);
      }

      try
      {
         cards = createCards(flashCardFilePath);
      }
      catch (FileNotFoundException e)
      {
         System.err.println("Couldn't find the specified file, exiting...");
         System.exit(1);
      }

      while (again)
      {
         // Shuffle the cards so that they're in random order.
         Collections.shuffle(cards);

         for (int i = 0; i < cards.size(); i++)
         {

            // Iterate through each card.
            // Display the card's question
            System.out.println(cards.get(i).getQuestion());

            // Wait for the user to press enter before showing the answer
            System.out.println("Press enter to show the answer...");
            userInput.nextLine();
            System.out.println(cards.get(i).getAnswer().trim());

            // Wait for for the user to press enter before going to the next
            // card
            System.out.println("Press enter to go to the next card");
            userInput.nextLine();
            System.out.println(
                  "=========================================================="
                        + "=====================\n");
         }

         // Ask the user if the want to go through the cards again
         System.out.print("Do you want to go through the cards again?(y/n) ");
         if (userInput.nextLine().toLowerCase().equals("y"))
         {
            again = true;
         }
         else
         {
            again = false;
         }
      }
      userInput.close();
   }

}
