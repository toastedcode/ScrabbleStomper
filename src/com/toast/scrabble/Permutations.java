package com.toast.scrabble;

import java.util.HashSet;
import java.util.Set;

public class Permutations
{
   static Set<String> getAllPermutations(String string)
   {
      Set<String> permutations = new HashSet<>();
      
      Set<String> substrings = getSubstrings(string);
      
      for (String substring : substrings)
      {
         permutations.addAll(getPermutations(substring));
      }
      
      return (permutations);
   }
   
   public static Set<String> getPermutations(String str)
   { 
      return (getPermutations("", str)); 
   }
   
   // http://www.programmingsimplified.com/java/source-code/java-program-find-substrings-of-string
   private static Set<String> getSubstrings(String string)
   {
      Set<String> substrings = new HashSet<>();
      
      for (int i = 0 ; i < string.length() ; i++)
      {
         for (int j = 1 ; j <= string.length() - i ; j++)
         {
            substrings.add(string.substring(i, i + j));
         }
      }
      
      return (substrings);
   }

   // http://stackoverflow.com/questions/4240080/generating-all-permutations-of-a-given-string
   private static Set<String> getPermutations(String prefix, String str)
   {
      Set<String> permutations = new HashSet<>(); 
      
       int n = str.length();
       if (n == 0)
       {
          permutations.add(prefix);
       }
       else
       {
          for (int i = 0; i < n; i++)
          {
             permutations.addAll(getPermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n)));
          }
       }
      
       return (permutations);
   }
}
