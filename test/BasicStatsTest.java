import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

import javax.swing.JButton;

import gui.BasicStats;
import gui.BasicStatsGUI;
import gui.view.CountView;
import gui.view.MeanView;
import gui.view.MedianView;
import gui.view.NumbersView;
import model.BasicStatsModel;

public class BasicStatsTest {
    private static double EPS = 1e-9;

    //Check Initial configuration
    @Test
    public void testInitialNumbersViewConfiguration() {
      //Check initial numbers
      BasicStatsGUI gui = new BasicStatsGUI();
      BasicStatsModel model = gui.getModel();
      assertTrue(Arrays.equals(new double[] {}, model.getArrayDouble()));

      NumbersView numbersView = new NumbersView();
      assertEquals("",numbersView.getComponent().getText());
    }

    @Test
    public void testInitialMeanViewConfiguration() {
      //Check initial mean
      MeanView meanView = new MeanView();
      assertEquals("Mean", meanView.getLabel());
      assertEquals("", meanView.getComponent().getText());
      assertEquals(0.0, meanView.getMean(), EPS);

    }

    @Test
    public void testInitialMedianViewConfiguration() {
      //Check initial median
      MedianView medianView = new MedianView();
      assertEquals("Median", medianView.getLabel());
      assertEquals("", medianView.getComponent().getText());
      assertEquals(0.0, medianView.getMedian(), EPS);
    }

    @Test
    public void testInitialCountViewConfiguration() {
      //Check initial count
      CountView countView = new CountView();
      assertEquals("Numbers", countView.getLabel());
      assertEquals("", countView.getComponent().getText());
      assertEquals(0.0, countView.getCount(), EPS);
    }

    @Test
    public void testNumbersViewUpdate() {
      NumbersView numbersView = new NumbersView();
      BasicStatsModel model = new BasicStatsModel();
      model.addNumber(2.0);
      numbersView.update(model);
      assertEquals("2.0,", numbersView.getComponent().getText());

      model.addNumber(10.0);
      numbersView.update(model);
      assertEquals("2.0,10.0,", numbersView.getComponent().getText());
    }

    @Test
    public void testMeanViewUpdate() {
      MeanView meanView = new MeanView();

      BasicStatsModel model = new BasicStatsModel();
      model.addNumber(2.0);
      meanView.update(model);
      assertEquals("2.0", meanView.getComponent().getText());
      assertEquals(2.0, meanView.getMean(), EPS);

      model.addNumber(10.0);
      meanView.update(model);
      assertEquals("6.0", meanView.getComponent().getText());
      assertEquals(6.0, meanView.getMean(), EPS);
    }

    @Test
    public void testMedianViewUpdate() {
      MedianView medianView = new MedianView();

      BasicStatsModel model = new BasicStatsModel();
      model.addNumber(2.0);
      medianView.update(model);
      assertEquals("2.0", medianView.getComponent().getText());
      assertEquals(2.0, medianView.getMedian(), EPS);

      model.addNumber(10.0);
      medianView.update(model);
      assertEquals("6.0", medianView.getComponent().getText());
      assertEquals(6.0, medianView.getMedian(), EPS);

      model.addNumber(5.0);
      medianView.update(model);
      assertEquals("5.0", medianView.getComponent().getText());
      assertEquals(5.0, medianView.getMedian(), EPS);
    }

    @Test
    public void testCountViewUpdate() {
      CountView countView = new CountView();
      BasicStatsModel model = new BasicStatsModel();
      model.addNumber(2.0);
      countView.update(model);
      assertEquals("1.0", countView.getComponent().getText());
      assertEquals(1.0, countView.getCount(), EPS);

      model.addNumber(10.0);
      countView.update(model);
      assertEquals("2.0", countView.getComponent().getText());
      assertEquals(2.0, countView.getCount(), EPS);

      model.addNumber(5.0);
      countView.update(model);
      assertEquals("3.0", countView.getComponent().getText());
      assertEquals(3.0, countView.getCount(), EPS);
    }

    @Test
    public void testDataReset() {
      BasicStatsModel model = new BasicStatsModel();
      model.addNumber(2.0);
      model.addNumber(4.0);
      model.reset();

      assertTrue(Arrays.equals(new double[] {}, model.getArrayDouble()));
    }

    @Test
    public void testNumbersViewReset() {
      NumbersView numbersView = new NumbersView();
      BasicStatsModel model = new BasicStatsModel();
      model.addNumber(2.0);
      model.addNumber(4.0);
      numbersView.update(model);

      numbersView.reset();
      assertEquals("", numbersView.getComponent().getText());

    }

    @Test
    public void testMeanViewReset() {
      MeanView meanView = new MeanView();
      BasicStatsModel model = new BasicStatsModel();
      model.addNumber(2.0);
      model.addNumber(4.0);
      meanView.update(model);

      meanView.reset();
      assertEquals("", meanView.getComponent().getText());
      assertEquals(0.0, meanView.getMean(), EPS);
    }

    @Test 
    public void testMedianViewReset() {
      MedianView medianView = new MedianView();
      BasicStatsModel model = new BasicStatsModel();
      model.addNumber(2.0);
      model.addNumber(4.0);
      medianView.update(model);

      medianView.reset();
      assertEquals("", medianView.getComponent().getText());
      assertEquals(0.0, medianView.getMedian(), EPS);
    }

    @Test
    public void testCountViewReset() {
      CountView countView = new CountView();
      BasicStatsModel model = new BasicStatsModel();
      model.addNumber(2.0);
      model.addNumber(4.0);
      countView.update(model);

      countView.reset();
      assertEquals("", countView.getComponent().getText());
      assertEquals(0.0, countView.getCount(), EPS);
    }

    @Test
    public void testNumberAdd() {
      //Adding a positive number
      BasicStatsModel model = new BasicStatsModel();
      model.addNumber(1.0);
      assertTrue(Arrays.equals(new double[] {1}, model.getArrayDouble()));

      //Adding a negative number
      model.addNumber(-1.0);
      assertTrue(Arrays.equals(new double[] {1, -1}, model.getArrayDouble()));

      // //Adding zero
      model.addNumber(0.0);
      assertTrue(Arrays.equals(new double[] {1, -1, 0}, model.getArrayDouble()));
    } 

    @Test(expected = IllegalArgumentException.class)
    public void testNumberAddFails() {
      BasicStatsModel model = new BasicStatsModel();
      model.addNumber(null);
    } 
 
    @Test
    public void testMaximum() {
      //Maximum should be 21
      double[] numbers = {9, 11, 1, 4, 7, 21};
      double max = BasicStats.maximum(numbers);
      assertEquals(21.0, max, EPS);

      //Maximum should be 7 
      double[] numbers2 = {9, 1, 4, 7};
      max = BasicStats.maximum(numbers2);
      assertEquals(9, max, EPS);

      //Maximum should be 3 since there's only one element
      double[] numbers3 = {3};
      max = BasicStats.maximum(numbers3);
      assertEquals(3, max, EPS);

    }

    @Test
    public void testNegativeMaximum() {
      //Maximum should be 21
      double[] numbers = {-9, -11, -1, -4, -7, -21};
      double max = BasicStats.maximum(numbers);
      assertEquals(-1, max, EPS);

      //Maximum should be 7 
      double[] numbers2 = {-9, -4, -7, 0};
      max = BasicStats.maximum(numbers2);
      assertEquals(0, max, EPS);

      //Maximum should be 3 since there's only one element
      double[] numbers3 = {-3};
      max = BasicStats.maximum(numbers3);
      assertEquals(-3, max, EPS);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxNullException() {
      double max = BasicStats.maximum(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxZeroLengthException() {
      double[] numbers = {};
      double max = BasicStats.maximum(numbers);
    }
    // reset

    @Test
    public void testCentralTendency() {
        double[] numbers = {2, 2, 3, 3, 3, 4, 4};
        double mean   = BasicStats.mean(numbers);
        assertEquals (3, mean, EPS);
        double median = BasicStats.median(numbers);
        assertEquals (3, median, EPS);
        double mode   = BasicStats.mode(numbers);
        assertEquals (3, mode, EPS);
    }

    @Test
    public void testMedian() {
      //Median should be 8.0 since size is even
      /* double[] numbers = {1, 4, 7, 9, 11, 21}; */
      double[] numbers = {9, 11, 1, 4, 7, 21};

      double median = BasicStats.median(numbers);
      assertEquals(8.0, median, EPS);

      //Median should be 7 since size is odd
      double[] numbers2 = {9, 1, 4, 7, 21};
      median = BasicStats.median(numbers2);
      assertEquals(7, median, EPS);

      //Median should be 3 since size is 1
      double[] numbers3 = {3};
      median = BasicStats.median(numbers3);
      assertEquals(3, median, EPS);

      //Median should be 0 since size is 0
      double[] numbers4 = {};
      median = BasicStats.median(numbers4);
      assertEquals(0, median, EPS);
    }

    @Test
    public void testMode() {
      //Mode should be 1 since we are taking the first mode
      double[] numbers = {1, 4, 7, 9, 11, 21};
      double mode   = BasicStats.mode(numbers);
      assertEquals (1, mode, EPS);

      //Mode should be 4
      double[] numbers2 = {1, 4, 4, 7, 9, 11, 21};
      mode   = BasicStats.mode(numbers2);
      assertEquals (4, mode, EPS);

      //Mode should be 7
      double[] numbers3 = {4, 4, 7, 7, 7, 11, 11};
      mode   = BasicStats.mode(numbers3);
      assertEquals (7, mode, EPS);

      //Mode should be 7
      double[] numbers4 = {7};
      mode   = BasicStats.mode(numbers4);
      assertEquals (7, mode, EPS);

      //Mode should be 0
      double[] numbers5 = {};
      mode   = BasicStats.mode(numbers5);
      assertEquals (0, mode, EPS);
    }
}
