package gui.view;
import gui.BasicStats;
import model.BasicStatsModel;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class MaxNumberView implements View {
    private double maxNum = Double.NEGATIVE_INFINITY;
    private String label = "Max Number";
	private JTextField jtfMaxNum;

    /*
     * Create a simple gui for the MaxNumberView and do basic initialization.
     */
	public MaxNumberView() {
        maxNum = Double.NEGATIVE_INFINITY;
		jtfMaxNum = new JTextField(5);
		jtfMaxNum.setEditable(false);
	}

    /**
     * Compute and set the maximum value from the numbers.
     * 
     * @param model The current BasicStatsModel to be visualized
     */
	@Override
	public void update(BasicStatsModel model) {
		maxNum = BasicStats.maximum(model.getArrayDouble());
        jtfMaxNum.setText("" + maxNum);
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
     * Return an instance of the MaxNumberView.
     * 
     * @return A JTextComponent representing the instance for the MaxNumberView.
     */
    public JTextComponent getComponent() {
		return jtfMaxNum;
	}

    /*
     * Modify and reset the maximum number in the view.
     */
    public void reset() {
		jtfMaxNum.setText("");
        maxNum = Double.NEGATIVE_INFINITY;
	}
}
