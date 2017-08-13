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
      ArrayList<Card> cards = null;
      Scanner userInput = new Scanner(System.in);

      // Iterate over the command line arguments and parse them
      if (args.length > 0)
      {
      
      }
      else
      {
      
      }

      try
      {
         cards = createCards(args[0]);
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
