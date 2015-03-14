package com.toast.scrabble.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.toast.scrabble.Solver;

@SuppressWarnings("serial")
public class Solution extends JPanel
{
   public Solution(Solver solver)
   {
      this.solver = solver;
      
      setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
      
      // Solution button.
      JButton solutionButton = new JButton("Solve");
      solutionButton.setMaximumSize(new Dimension(100, 50));
      solutionButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent arg0)
         {
            solve();
         }
      });
      add(solutionButton);
      
      add(Box.createHorizontalStrut(20));
      
      // Solution.
      solution = new JTextArea();
      JScrollPane scrollPane = new JScrollPane(solution);
      scrollPane.setPreferredSize(new Dimension(300, 200));
      add(new JScrollPane(scrollPane));
   }
   
   private void solve()
   {
      Set<String> words = solver.solve();
      
      solution.setText("There are " + words.size() + " possible solutions.\n");
      
      for (String word : words)
      {
         solution.append(word + "\n");
      }
   }
   
   private Solver solver;
   
   private JTextArea solution;
}
