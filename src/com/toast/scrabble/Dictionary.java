package com.toast.scrabble;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Dictionary
{
   public Dictionary(String filename)
   {
      loadDictionary(filename);
   }
   
   public boolean wordExists(String word)
   {
      return (dictionary.contains(word));
   }
   
   public Set<String> getWords(Set<String> strings)
   {
      Set<String> words = new HashSet<>();
      
      for (String word : strings)
      {
         if (wordExists(word))
         {
            words.add(word);
         }
      }
      
      return (words);
   }
   
   private void loadDictionary(String filename)
   {
      try
      {
         System.out.println(System.getProperty("user.dir"));
         
         FileReader fileReader = new FileReader(filename);
         
         BufferedReader bufferedReader = new BufferedReader(fileReader);
         
         String line = null;
         while ((line = bufferedReader.readLine()) != null)
         {
             dictionary.add(line);
         }
         
         bufferedReader.close();
      }
      catch (IOException e)
      {
         System.out.print(e);
      }
   }
   
   Set<String> dictionary = new HashSet<>();
}
