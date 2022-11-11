package gui.view;
import model.BasicStatsModel;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class CountView implements View {
    private double count = 0;
    private String label = "Numbers";
	private JTextField jtfCount;

    /*
     * Create a simple gui for the CountView and do basic initialization.
     */
	public CountView() {
        count = 0;
		jtfCount = new JTextField(5);
		jtfCount.setEditable(false);
	}

    /**
     * Compute and set the count of the numbers.
     * 
     * @param model The current BasicStatsModel to be visualized
     */
	@Override
	public void update(BasicStatsModel model) {
		count = model.getArrayDouble().length;
        jtfCount.setText("" + count);
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
     * Return an instance of the CountView.
     * 
     * @return A JTextComponent representing the instance for the CountView.
     */
    public JTextComponent getComponent() {
		return jtfCount;
	}

    /*
     * Modify and reset the count value in the view.
     */
    public void reset() {
		jtfCount.setText("");
        count = 0;
	}

    /**
	 * Return the count value.
	 * 
	 * @return Count for the numbers.
	 */
	public double getCount() {
        return count;
    }
}
