// Jordan White
// 3/7/2021
// Take Home Assessment 6: AnagramSolver
//
// AnagramSolver uses a given group of words to come up with
// anagrams for a word or phrase that is provided by the user,
// and the user can choose how many words they want for the
// anagram 

import java.util.*;

public class AnagramSolver	{
   private Map<String, LetterInventory> myDictionary;
   private List<String> order;
	
	//	pre:
	//	takes	dictionary of given words
	//	post:
	//	creates associations	of	words	from dictionary to their respective
	//	amounts of letters
   public AnagramSolver(List<String> dictionary) {
      myDictionary =	new HashMap<String, LetterInventory>();
      order = dictionary;
      for (String	s : dictionary) {
         myDictionary.put(s,	new LetterInventory(s));
      }
   }

	// Pre:
   // takes input text and max number of words allowed to be used
   // Post:
   // prints out all possible combinations of words in dictionary that make up anagram
   // for given text, throws IllegalArgumentException error if the number given for the
   // maximum number of words is negative, and there is no upper limit for the number of
   // words in the anagram when the max is set to zero
   public void	print(String text, int max) {
      if (max < 0) {
         throw new IllegalArgumentException();
      }
      List<String> result = new ArrayList<String>();
      LetterInventory textInventory	= new	LetterInventory(text);
      print(textInventory,	max, result, prune(textInventory));
   }

	// Pre:
   // takes stored counts of letters for input text, maximum number of words allowed to be used,
   // list of words that work for the anagram, and list of words that are compatible with
   // input text
   // Post:
   // prints out all possible combinations of words in dictionary that make up anagram
   // for given text
   private void print(LetterInventory textInventory, int	max, List<String>	result, 
                                                               List<String> usables) {
      if	(textInventory.isEmpty()) {
         System.out.println(result);
      } else if (result.size() < max || max == 0) {
         for (String	s : usables)	{
            LetterInventory check = textInventory.subtract(myDictionary.get(s));
            if (check != null) {
               result.add(s);
               print(check, max, result, usables);
               result.remove(result.size() - 1);
            }
         }
      }
   }

   // Pre:
   // takes stored counts of letters for input text
   // Post:
   // returns a list of words that are able to be removed from that LetterInventory
   private List<String> prune(LetterInventory textInventory) {
      List<String> usables = new ArrayList<String>();
      for (String s : order) {
         if (textInventory.subtract(myDictionary.get(s)) != null) {
            usables.add(s);
         }
      }
      return usables;
   }
}
