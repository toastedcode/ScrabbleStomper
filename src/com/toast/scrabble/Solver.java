package com.toast.scrabble;

import java.util.HashSet;
import java.util.Set;

import javax.swing.SwingConstants;

import com.toast.scrabble.gui.Board;
import com.toast.scrabble.Dictionary;

public class Solver
{
   static final String DICTIONARY_FILE = "enable1.txt";
   
   public Solver(Board hand, Board board)
   {
      this.hand = hand;
      this.board = board;
      
      dictionary = new Dictionary(DICTIONARY_FILE);
   }
   
   public Set<String> solve()
   {
      Set<String> words = new HashSet<>();
      
      String handLetters = hand.getLetters(0,  0, SwingConstants.HORIZONTAL);
      
      char selectedLetter = board.getSelectedLetter();
      
      if ((!handLetters.isEmpty()) &&
          (selectedLetter != (char)0))
      {
         String letters = handLetters + String.valueOf(board.getSelectedLetter());;
         
         Set<String> permutations = Permutations.getAllPermutations(letters);
         words = dictionary.getWords(permutations); 
      }
      
      return (words);
   }
   
   Board hand;
   
   Board board;
   
   Dictionary dictionary;
}
