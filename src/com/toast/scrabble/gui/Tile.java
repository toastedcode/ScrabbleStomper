package com.toast.scrabble.gui;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Tile extends JLabel
{
   public Tile(char letter)
   {
      setLetter(letter);
   }
   
   public char getLetter()
   {
      return (letter);
   }
   
   public void setLetter(char letter)
   {
      this.letter = letter;

      if (tileImage == null)
      {
         try
         {
            tileImage = ImageIO.read(new File("./src/resources/scrabbleTiles.jpg"));
         }
         catch (IOException e)
         {
            System.out.println("Failed to load tile image.");
         }
      }
      
      if (tileImage != null)
      {
         Point position = getTilePosition(letter);
         setIcon(new ImageIcon(tileImage.getSubimage((position.x * TILE_WIDTH), 
                                                     (position.y * TILE_HEIGHT), 
                                                     TILE_WIDTH, 
                                                     TILE_HEIGHT).getScaledInstance((int)(TILE_WIDTH * TILE_SCALE), 
                                                                                    (int)(TILE_WIDTH * TILE_SCALE), 
                                                                                    0)));
      }
   }
   
   private Point getTilePosition(char letter)
   {
      if (tileMap.isEmpty())
      {
         tileMap.put('a',  new Point(1, 0));
         tileMap.put('b',  new Point(2, 0));
         tileMap.put('c',  new Point(3, 0));
         tileMap.put('d',  new Point(4, 0));
         tileMap.put('e',  new Point(0, 1));
         tileMap.put('f',  new Point(1, 1));
         tileMap.put('g',  new Point(2, 1));
         tileMap.put('h',  new Point(3, 1));
         tileMap.put('i',  new Point(4, 1));
         tileMap.put('j',  new Point(5, 1));
         tileMap.put('k',  new Point(0, 2));
         tileMap.put('l',  new Point(1, 2));
         tileMap.put('m',  new Point(2, 2));
         tileMap.put('n',  new Point(3, 2));
         tileMap.put('o',  new Point(4, 2));
         tileMap.put('p',  new Point(5, 2));
         tileMap.put('q',  new Point(0, 3));
         tileMap.put('r',  new Point(1, 3));
         tileMap.put('s',  new Point(2, 3));
         tileMap.put('t',  new Point(3, 3));
         tileMap.put('u',  new Point(4, 3));
         tileMap.put('v',  new Point(5, 3));
         tileMap.put('w',  new Point(1, 4));
         tileMap.put('x',  new Point(2, 4));
         tileMap.put('y',  new Point(3, 4));
         tileMap.put('z',  new Point(4, 4));
      }
      
      return (tileMap.get(letter));
   }
   
   private static Map<Character, Point> tileMap = new HashMap<>();
   
   private final static int TILE_WIDTH = 133;
   
   private final static int TILE_HEIGHT = 133;
   
   private final static float TILE_SCALE = 0.3f;
   
   static private BufferedImage tileImage;
   
   char letter;
}
