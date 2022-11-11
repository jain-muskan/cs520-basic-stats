package gui.view;
import gui.BasicStats;
import model.BasicStatsModel;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class MeanView implements View {
	private double mean = 0;
    private String label = "Mean";
	private JTextField jtfMean;

    /*
     * Create a simple gui for the MeanView and do basic initialization.
     */
	public MeanView() {
        mean = 0;
		jtfMean = new JTextField(5);
		jtfMean.setEditable(false);
	}

    /**
     * Compute and set the mean of the numbers.
     * 
     * @param model The current BasicStatsModel to be visualized
     */
	@Override
	public void update(BasicStatsModel model) {
		mean = BasicStats.mean(model.getArrayDouble());
        jtfMean.setText("" + mean);
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
     * Return an instance of the MeanView.
     * 
     * @return A JTextComponent representing the instance for the MeanView.
     */
    public JTextComponent getComponent() {
		return jtfMean;
	}

    /*
     * Modify and reset the mean value in the view.
     */
    public void reset() {
		jtfMean.setText("");
        mean = 0;
	}

    /**
	 * Return the mean value.
	 * 
	 * @return Mean value of the numbers.
	 */
	public double getMean() {
        return mean;
    }
}
