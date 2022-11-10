package gui.view;
import gui.BasicStats;
import model.BasicStatsModel;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class MedianView implements View {
    private double median;
    private String label;
	private JTextField jtfMedian;

	/*
     * Create a simple gui for the MedianView and do basic initialization.
     */
	public MedianView() {
        median = 0;
        label = "Median";
		jtfMedian = new JTextField(5);
		jtfMedian.setEditable(false);
	}

	/**
     * Compute and set the median of the numbers.
     * 
     * @param model The current BasicStatsModel to be visualized
     */
	@Override
	public void update(BasicStatsModel model) {
		median = BasicStats.median(model.getArrayDouble());
        jtfMedian.setText("" + median);
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
     * Return an instance of the MedianView.
     * 
     * @return A JTextComponent representing the instance for the MedianView.
     */
    public JTextComponent getComponent() {
		return jtfMedian;
	}

	/*
     * Modify and reset the median value in the view.
     */
    public void reset() {
		jtfMedian.setText("");
		median = 0;
	}

	/**
	 * Return the median value.
	 * 
	 * @return median value of the numbers.
	 */
	public double getMedian() {
        return median;
    }
}
