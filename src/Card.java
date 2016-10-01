
/**
 * Class that represents a single flash card which has a front (question) and
 * back (answer) side.
 * 
 * @author TNorbury
 *
 */
public class Card
{
   // Question and answer for the flash card.
   private String _question;
   private String _answer;


   /**
    * Create a new card with a question and answer
    * 
    * @param question
    *           The question on the card
    * @param answer
    *           The answer on the card
    */
   Card(String question, String answer)
   {
      _question = question;
      _answer = answer;
   }


   /**
    * @return the question on the card
    */
   public String getQuestion()
   {
      return _question;
   }


   /**
    * @return the answer on the card
    */
   public String getAnswer()
   {
      return _answer;
   }
   
   
   public String toString()
   {
      return "Question: " + _question + "\nAnswer: " + _answer + "\n";
   }
}
