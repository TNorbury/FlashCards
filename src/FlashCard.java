import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
   private static ArrayList<Card> createCards(String filePath)
         throws FileNotFoundException
   {
      String question;
      String answer;
      ArrayList<Card> cards = new ArrayList<>();
      Scanner in = new Scanner(new File(filePath));

      // Set the scanner delimiter to delimit on commas and new lines
      in.useDelimiter("\\n|\\cr|\\:");

      // Iterate through the file creating flash cards until the end of file is
      // reached
      while (in.hasNext())
      {
         System.out.print("Question: " + in.next());
         System.out.println("\tAnswer: " + in.next());
      }

      return cards;
   }


   public static void main(String[] args)
   {
      ArrayList<Card> cards;
      try
      {
         createCards(args[0]);
      }
      catch (FileNotFoundException e)
      {
         System.err.println("Couldn't find the specified file, exiting...");
      }
   }

}
