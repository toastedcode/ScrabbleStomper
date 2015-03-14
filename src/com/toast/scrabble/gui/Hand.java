package com.toast.scrabble.gui;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Hand extends JPanel
{
   public Hand()
   {
      setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
   }
   
   public void addTiles(String letters)
   {
      for (int i = 0; i < letters.length(); i++)
      {
         add(new Tile(letters.charAt(i)));
      }
   }
   
   public String getLetters()
   {
      String letters = new String();
      
      for (int i = 0; i < getComponentCount(); i++)
      {
         JComponent child = (JComponent)getComponent(i);
         
         if (child instanceof Tile)
         {
            letters += ((Tile)child).getLetter();
         }
      }
      
      return (letters);
   }
}
