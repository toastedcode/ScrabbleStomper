package com.toast.scrabble;

import java.awt.BorderLayout;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.toast.scrabble.gui.Board;
import com.toast.scrabble.gui.Hand;
import com.toast.scrabble.gui.Solution;
import com.toast.scrabble.gui.Tile;

public class ScrabbleStomper
{
   //static final String DICTIONARY_FILE = "enable1.txt";
   static final String DICTIONARY_FILE = "sowpods.txt";

   
   public static void main(final String args[])
   {
      JFrame frame = new JFrame("Scrabble Stomper");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      JPanel centerPanel = new JPanel();
      
      Board hand = new Board(7, 1);
      hand.addTiles("a",  0,  0,  SwingConstants.HORIZONTAL);
      
      Board board = new Board(10, 10);
      board.addTile('a', 1, 1);
      
      Solver solver = new Solver(hand, board);
      
      centerPanel.add(hand);
      centerPanel.add(board);
      centerPanel.add(new Solution(solver));
      
      frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
      
      frame.setSize(800, 850);
      frame.setVisible(true); 
   }
}
