package com.toast.scrabble.gui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Board extends JPanel
{
   private static final Color GRID_COLOR = Color.GRAY;
   
   private static final Color SELECTED_COLOR = Color.GREEN;
   
   private static final String ALPHABET = "abcdefghijklmnopqurstuvwxyz";
   
   private static final char SPACE = ' ';
   
   public Board(int width, int height)
   {
      boardDimension = new Dimension(width, height);
      
      board = new JPanel[boardDimension.width][boardDimension.height];
      
      setLayout(new GridLayout(boardDimension.height, boardDimension.width));
      this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      
      createBoard();
      
      setFocusable(true);
      addKeyListener(new KeyListener()
      {
         @Override
         public void keyPressed(KeyEvent event)
         {
         }

         @Override
         public void keyReleased(KeyEvent event)
         {
         }

         @Override
         public void keyTyped(KeyEvent event)
         {
            char letter = event.getKeyChar();
            boolean isLetter = ALPHABET.contains(String.valueOf(letter));
            boolean isSpace = (letter == SPACE);
            
            System.out.println(letter);
            
            if (selected != null)
            {
               if (isLetter || isSpace)
               {
                  selected.removeAll();
                  
                  if (isLetter)
                  {
                     selected.add(new Tile(letter));
                  }
                  
                  selected.revalidate();
                  selected.repaint();
               }
            }            
         }
      });
   }
   
   public void addTile(char letter, int x, int y)
   {
      if (ALPHABET.contains(String.valueOf(letter)))
      {
         JPanel cell = board[x][y];
         cell.add(new Tile(letter));
      }
   }
   
   public void addTiles(String letters, int x, int y, int direction)
   {
      
      for (int i = 0; i < letters.length(); i++)
      {
         if ((x < boardDimension.width) &&
             (y < boardDimension.height))
         {
            addTile(letters.charAt(i), x, y);               
         }
         
         if (direction == SwingConstants.HORIZONTAL)
         {
            x++;
         }
         else
         {
            y++;
         }
      }
   }
   
   public String getLetters(int x, int y, int direction)
   {
      String letters = new String();
      
      while((x < boardDimension.width) &&
            (y < boardDimension.height))
      {
         JPanel cell = board[x][y];
         
         if (cell.getComponentCount() > 0)
         {
            Tile tile = (Tile)cell.getComponent(0);
            
            letters += tile.getLetter();
         }
         
         if (direction == SwingConstants.HORIZONTAL)
         {
            x++;
         }
         else
         {
            y++;
         }
      }
      
      return (letters);
   }
   
   public char getSelectedLetter()
   {
      char letter = (char)0;
      
      if (selected != null)
      {
         Tile tile = (Tile)selected.getComponent(0);
         if (tile != null)
         {
            letter = tile.getLetter();
         }
      }
      
      return (letter);
   }
   
   private void createBoard()
   {
      for (int c = 0; c < boardDimension.width; c++)
      {
         for (int r = 0; r < boardDimension.height; r++)
         {
            JPanel cell = new JPanel();
            cell.setBorder(BorderFactory.createLineBorder(GRID_COLOR));
            
            cell.addMouseListener(new MouseListener()
            {
               @Override
               public void mouseClicked(MouseEvent event)
               {
                  select((JPanel)event.getSource());
               }

               @Override
               public void mouseEntered(MouseEvent event)
               {
               }

               @Override
               public void mouseExited(MouseEvent event)
               {
               }

               @Override
               public void mousePressed(MouseEvent event)
               {
               }

               @Override
               public void mouseReleased(MouseEvent event)
               {
               }
            });
            
            board[c][r] = cell;
            add(cell);
         }
      }
   }
   
   private void select(JPanel selected)
   {
      if (this.selected != null)
      {
         this.selected.setBorder(BorderFactory.createLineBorder(GRID_COLOR));         
      }
      
      if (selected != this.selected)
      {
         this.selected = selected;
         this.selected.setBorder(BorderFactory.createLineBorder(SELECTED_COLOR));
      }
   }
   
   Dimension boardDimension;
   
   JPanel[][] board;
   
   JPanel selected = null;
}
