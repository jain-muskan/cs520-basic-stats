package gui;

import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import model.BasicStatsModel;
import gui.view.CountView;
import gui.view.MeanView;
import gui.view.MedianView;
import gui.view.View;


/**
 * Create a simple GUI that includes:
 * - a text field and a button that allows the user to enter numbers.
 * - a button that allows the user to clear all entered numbers.
 * - a panel with labels and text fields for count, median, and mean.
 * - a text area that shows all numbers.
 *
 * For the MVC architecture pattern, these are the views and controllers.
 */
public class BasicStatsGUI implements View
{
    public static final String APP_TITLE = "Simple stats";
    
    private static BasicStatsModel model = new BasicStatsModel();
    CountView countView = new CountView();
	MeanView meanView = new MeanView();
	MedianView medianView = new MedianView();
    private JTextArea jtaNumbers;
    private JFrame jfMain = new JFrame(APP_TITLE);

    public BasicStatsGUI() {	
	// Create the main frame of the application, and set size and position
	jfMain.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	jfMain.setSize(600,400);
	jfMain.setLocationRelativeTo(null);
	
	// Panel that shows stats about the numbers
	JPanel jpStats = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	jpStats.add(new JLabel(countView.getLabel()));
	jpStats.add(countView.getComponent());
	jfMain.getContentPane().add(jpStats, BorderLayout.CENTER);
	
	jpStats.add(new JLabel(medianView.getLabel()));
	jpStats.add(medianView.getComponent());
	jfMain.getContentPane().add(jpStats, BorderLayout.CENTER);
	
	jpStats.add(new JLabel(meanView.getLabel()));
	jpStats.add(meanView.getComponent());
	jfMain.getContentPane().add(jpStats, BorderLayout.CENTER);
	
	// TextArea that shows all the numbers
	jtaNumbers = new JTextArea(10,50);
	jtaNumbers.setEditable(false);
	jfMain.getContentPane().add(jtaNumbers, BorderLayout.SOUTH);
	
	
	// Panel with a text field/button to enter numbers and a button to reset the application
	JButton jbReset = new JButton("Reset");
	jbReset.addActionListener(new ActionListener() {
		// The interface ActionListener defines a call-back method actionPerformed,
		// which is invoked if the user interacts with the GUI component -- in this
		// case, if the user clicks the button.
		public void actionPerformed(ActionEvent e) {
		    // Clear the ArrayList and all text fields
		    model.reset();

		    update(model);
		}
	    });
	JTextField jtfNumber = new JTextField(5);
	JButton jbAdd = new JButton("Add number");
	jbAdd.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    // Parse input and add number to the ArrayList
		    
		    Double num = Double.parseDouble(jtfNumber.getText());
		    model.addNumber(num);

		    update(model);
		}
	    });
	JPanel jpInput = new JPanel(new FlowLayout(FlowLayout.CENTER));
	jpInput.add(jtfNumber);
	jpInput.add(jbAdd);
	jpInput.add(jbReset);
	
	
	jfMain.getContentPane().add(jpInput, BorderLayout.NORTH);
    }

    public void update(BasicStatsModel model) {
	if (model.getArrayDouble().length == 0) {
	    jtaNumbers.setText("");
	    countView.reset();
	    medianView.reset();
	    meanView.reset();
	}
	else {
	    // Update the displayed list of numbers
	    double num = model.getArrayDouble()[model.getArrayDouble().length - 1];
	    jtaNumbers.append(num + ",");
	    
	    // Compute and set the count
	    countView.update(model);
	    
	    // Compute and set the mean
	    meanView.update(model);
	    
	    // Compute and set the median
	    medianView.update(model);    
	}
    }

    public void show() {
	// Show the frame
	jfMain.setVisible(true);
    }
    
}
