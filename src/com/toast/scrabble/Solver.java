package com.toast.scrabble;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.SwingConstants;

import com.toast.scrabble.gui.Board;
import com.toast.scrabble.Dictionary;

public class Solver
{
   static final String DICTIONARY_FILE = "enable1.txt";
   
   public class Solution
   {
      public Solution(String word, int score)
      {
         this.word = word;
         this.score = score;
      }
      
      public final String word;
      
      public final int score;
   }
   
   private class SolutionComparator implements Comparator<Solution>
   {
      @Override
      public int compare(Solution left, Solution right)
      {
         int result = 0;
         
         if (left.score > right.score)
         {
            result = -1;
         }
         else if (left.score < right.score)
         {
            result = 1;
         }
         
         return (result);
      }      
   }
   
   public Solver(Board hand, Board board)
   {
      this.hand = hand;
      this.board = board;
      
      dictionary = new Dictionary(DICTIONARY_FILE);
   }
   
   public List<Solution> solve()
   {
      Set<String> words = new HashSet<>();
      List<Solution> solutionList = new ArrayList<>();
      
      String handLetters = hand.getLetters(0,  0, SwingConstants.HORIZONTAL);
      
      char selectedLetter = board.getSelectedLetter();
      
      if ((!handLetters.isEmpty()) &&
          (selectedLetter != (char)0))
      {
         String letters = handLetters + String.valueOf(board.getSelectedLetter());;
         
         Set<String> permutations = Permutations.getAllPermutations(letters);
         words = getPlayableWords(dictionary.getWords(permutations), selectedLetter); 
         solutionList = getSolutionList(words);
      }
      
      return (solutionList);
   }
   
   private Set<String> getPlayableWords(Set<String> words, char letter)
   {
      Set<String> playableWords = new HashSet<>();
      
      for (String word : words)
      {
         if (word.contains(String.valueOf(letter)))
         {
            playableWords.add(word);
         }
      }
      
      return (playableWords);
   }
   
   private List<Solution> getSolutionList(Set<String> words)
   {
      List<Solution> solutionlist = new ArrayList<>();
      
      for (String word : words)
      {
         solutionlist.add(new Solution(word, Scorer.score(word)));
      }
      
      solutionlist.sort(new SolutionComparator());
      
      return (solutionlist);
   }
   
   Board hand;
   
   Board board;
   
   Dictionary dictionary;
}
