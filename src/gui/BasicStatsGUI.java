package gui;

import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import model.BasicStatsModel;
import gui.view.CountView;
import gui.view.MaxNumberView;
import gui.view.MeanView;
import gui.view.MedianView;
import gui.view.NumbersView;
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
	MaxNumberView maxNumberView = new MaxNumberView();
    NumbersView numbersView = new NumbersView();
    private JFrame jfMain = new JFrame(APP_TITLE);
	private JButton jbAdd;
	private JButton jbReset;
	private JTextField jtfNumber;

    public BasicStatsGUI() {	
	// Create the main frame of the application, and set size and position
	jfMain.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	jfMain.setSize(600,400);
	jfMain.setLocationRelativeTo(null);
	
	// Panel that shows stats about the numbers
	JPanel jpStats = new JPanel(new FlowLayout(FlowLayout.CENTER));

	jpStats.add(new JLabel(countView.getLabel()));
	jpStats.add(countView.getComponent());
	
	jpStats.add(new JLabel(medianView.getLabel()));
	jpStats.add(medianView.getComponent());
	
	jpStats.add(new JLabel(meanView.getLabel()));
	jpStats.add(meanView.getComponent());

	jpStats.add(new JLabel(maxNumberView.getLabel()));
	jpStats.add(maxNumberView.getComponent());
	jfMain.getContentPane().add(jpStats, BorderLayout.CENTER);
	
	// TextArea that shows all the numbers
	jfMain.getContentPane().add(numbersView.getComponent(), BorderLayout.SOUTH);
	
	
	// Panel with a text field/button to enter numbers and a button to reset the application
	jbReset = new JButton("Reset");
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

	jtfNumber = new JTextField(5);
	jbAdd = new JButton("Add number");
	jbAdd.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    // Parse input and add number to the ArrayList
		    
			try {
				Double num = Double.parseDouble(jtfNumber.getText());
				model.addNumber(num);
				update(model);
			} catch (NullPointerException ne) {
				//JOptionPane.showMessageDialog (jfMain, "The input must be a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Error message: " + ne.getMessage());
				// Clear the input number field
				jtfNumber.setText("");

			} catch (NumberFormatException ne) {
				//JOptionPane.showMessageDialog (jfMain, "The input must be a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Error message: " + ne.getMessage());
				// Clear the input number field
				jtfNumber.setText("");

			}
		    
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
		jtfNumber.setText("");
	    numbersView.reset();
	    countView.reset();
	    medianView.reset();
	    meanView.reset();
		maxNumberView.reset();
	}
	else {
		// Clear the input number field
		jtfNumber.setText("");

	    // Update the displayed list of numbers
	    numbersView.update(model);
	    
	    // Compute and set the count
	    countView.update(model);
	    
	    // Compute and set the mean
	    meanView.update(model);
	    
	    // Compute and set the median
	    medianView.update(model);    

		// Compute and set the maximum number
		maxNumberView.update(model);
	}
    }

    public void show() {
	// Show the frame
	jfMain.setVisible(true);
    }

	public JFrame getComponent() {
		return jfMain;
	}

	public BasicStatsModel getModel() {
		return model;
	}

	public JButton getAddButton() {
		return jbAdd;
	}

	public JButton getResetButton() {
		return jbReset;
	}
    
	public void setInput(double num) {
		jtfNumber.setText(num+"");
	}
}
