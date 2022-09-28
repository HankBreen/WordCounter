import java.util.ArrayList;

/**
 * The WordCount class is an object which holds a word 
 * and how many times that word appears in a text file.
 * @author hankbreen
 *
 */
public class WordCount {
    
   
   private String word;
   private int count;
    
   /**
    * A WordCount object 
    * @param str the word in the file
    * @param c the count of that word
    */
   public WordCount(String str, int c) {
       word = str;
       count = c;
   }
    
   /**
    * Sets the word to the inputted string
    * @param s a string in the file
    */
   public void setString(String s) {
       this.word = s;
   }
    
   /**
    * Gets the word in the WordCount object
    * @return a string
    */
   public String getWord() {
       return this.word;
   }
    
   /**
    * Gets the count in the WordCount object
    * @return the int count
    */
   public int getCount() {
       return this.count;
   }
    
   /**
    * Adds one to the count of the object
    */
   public void addToCount() {
       count += 1;
   }
    
   /**
    * returns the WordCount object who is assigned that word
    * @param wList the arraylist to search
    * @param str the string you want to find the object for
    * @return the object you want to find
    */
   public WordCount getString(ArrayList<WordCount> wList, String str) {
       for (int i = 0 ; i < wList.size() ; ++i) {
           if (str.equals(wList.get(i).getWord())) {
               return wList.get(i);
           }
       }
       return null;
   }
    
   /**
    * Checks if two WordCount object words are equal
    * @param w a WordCount object
    * @return a boolean value
    */
   public boolean isEqual(WordCount w) {
       if (this.getWord().equals(w.getWord())) {
           return true;
       }
       return false;
   }
    
   /**
    * Outputs a WordCount object's data in String format
    */
   public String toString() {
       return (this.getWord() + ", " + this.getCount());
   }

}
