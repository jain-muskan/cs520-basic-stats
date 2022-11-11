package gui.view;
import model.BasicStatsModel;

import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

public class NumbersView implements View {
    private String label = "";
	private JTextArea jtaNumbers;

    /*
     * Create a simple gui for the NumbersView and do basic initialization.
     */
	public NumbersView() {
		jtaNumbers = new JTextArea(10,50);
	    jtaNumbers.setEditable(false);
	}

    /**
     * Update the displayed list of numbers
     * 
     * @param model The current BasicStatsModel to be visualized
     */
	@Override
	public void update(BasicStatsModel model) {
		double num = model.getArrayDouble()[model.getArrayDouble().length - 1];
	    jtaNumbers.append(num + ",");
	}

    /**
     * Return the label for this view.
     * 
     * @return A string representing the label for the view
     */
    public String getLabel() {
		return label;
	}

    /**
     * Return an instance of the NumbersView.
     * 
     * @return A JTextComponent representing the instance for the NumbersView.
     */
    public JTextComponent getComponent() {
		return jtaNumbers;
	}

    /*
     * Modify and reset the numbers in the view.
     */
    public void reset() {
		jtaNumbers.setText("");
	}
}
