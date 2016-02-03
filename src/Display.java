import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Display {


	private static JFrame frame;
	private static DefaultListModel model;
	private static ArrayList<Double> meansArrayList;
	private static boolean firstRun = true;

	public static void initDisplay() {

		Double mean = 0.0;
		Double sd = 0.0;
		Integer sampleSize = 0;
		Integer numberSamples = 0;

		// Create and populate the panel.
		JPanel panel1 = new JPanel(new GridBagLayout());
		panel1.setBorder(new EmptyBorder(5,10,5,5));

		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.ipadx = 50;
		c.ipady = 10;
		c.fill = GridBagConstraints.HORIZONTAL;

		JLabel meanLabel = new JLabel("Mean", JLabel.LEADING);
		c.gridx = 0;
		c.gridy = 0;
		panel1.add(meanLabel, c);

		JTextField meanTextField = new JTextField(10);
		meanLabel.setLabelFor(meanTextField);
		c.gridx = 1;
		c.gridy = 0;
		panel1.add(meanTextField, c);

		JLabel sdLabel = new JLabel("Standard Deviation", JLabel.LEADING);
		c.gridx = 0;
		c.gridy = 1;
		panel1.add(sdLabel, c);

		JTextField sdTextField = new JTextField(10);
		sdLabel.setLabelFor(sdTextField);
		c.gridx = 1;
		c.gridy = 1;
		panel1.add(sdTextField, c);

		JLabel sampleSizeLabel = new JLabel("Sample Size", JLabel.LEADING);
		c.gridx = 0;
		c.gridy = 2;
		panel1.add(sampleSizeLabel, c);
		JTextField sampleSizeTextField = new JTextField(10);
		sampleSizeLabel.setLabelFor(sampleSizeTextField);
		c.gridx = 1;
		c.gridy = 2;
		panel1.add(sampleSizeTextField, c);

		JLabel numberSamplesLabel = new JLabel("Number of Samples", JLabel.LEADING);
		c.gridx = 0;
		c.gridy = 3;
		panel1.add(numberSamplesLabel, c);
		JTextField numberSamplesTextField = new JTextField(10);
		numberSamplesLabel.setLabelFor(numberSamplesTextField);
		c.gridx = 1;
		c.gridy = 3;
		panel1.add(numberSamplesTextField, c);

		JLabel statsLabel1 = new JLabel("Mean of Means", JLabel.LEADING);
		c.gridx = 0;
		c.gridy = 5;
		panel1.add(statsLabel1, c);
		JTextField statsTextField1 = new JTextField(10);
		statsLabel1.setLabelFor(statsTextField1);
		c.gridx = 1;
		c.gridy = 5;
		panel1.add(statsTextField1, c);
		
		JLabel statsLabel2 = new JLabel("SD of Means", JLabel.LEADING);
		c.gridx = 0;
		c.gridy = 6;
		panel1.add(statsLabel2, c);
		JTextField statsTextField2 = new JTextField(10);
		statsLabel2.setLabelFor(statsTextField2);
		c.gridx = 1;
		c.gridy = 6;
		panel1.add(statsTextField2, c);

		JButton generateRandomButton = new JButton("Generate Random");
		generateRandomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Double mean = Double.parseDouble(meanTextField.getText());
				Double sd = Double.parseDouble(sdTextField.getText());
				Integer sampleSize = Integer.parseInt(sampleSizeTextField.getText());
				Integer numberSamples = Integer.parseInt(numberSamplesTextField.getText());
				
				//meansArrayList = CentralLimitTheorem.generateRandom(mean, sd, sampleSize, numberSamples); //populates then clera to avoid null
				if (!firstRun){
					meansArrayList.clear();
					model.clear();
				}
				firstRun = false;
				meansArrayList = CentralLimitTheorem.generateRandom(mean, sd, sampleSize, numberSamples);
				populateArray(meansArrayList);
				statsTextField1.setText(Double.toString(calculateMean(meansArrayList)));
				statsTextField2.setText(Double.toString(calculateSD(meansArrayList)));

			}
		});
		c.gridx = 0;
		c.gridy = 4;
		panel1.add(generateRandomButton, c);

		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				meanTextField.setText("");
				sdTextField.setText("");
				sampleSizeTextField.setText("");
				numberSamplesTextField.setText("");
				statsTextField1.setText("");
				statsTextField2.setText("");
				meansArrayList.clear();
				model.clear();

			}
		});
		c.gridx = 1;
		c.gridy = 4;
		panel1.add(clearButton, c);

		JPanel totalPanel = new JPanel(new BorderLayout());
		totalPanel.add(panel1, BorderLayout.WEST);

		model = new DefaultListModel();
		JList list = new JList(model);
		JScrollPane scrollPane = new JScrollPane(list);
		totalPanel.add(scrollPane, BorderLayout.EAST);

		// Create and set up the window.
		frame = new JFrame("Central Limit Theorem");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		panel1.setOpaque(true); // content panes must be opaque

		frame.setContentPane(totalPanel);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void populateArray(ArrayList<Double> array) {
		for (Double randomMean : array) {
			model.addElement(randomMean);
		}
	}

	public static Double calculateMean(ArrayList<Double> array) {
		double total = 0;
		for (Double doub : array) {
			total += doub;
		}

		return total / array.size();
	}
	public static Double calculateSD(ArrayList<Double> array) {
		Double mean = calculateMean(array); 
		Double total = 0.0;
		
		for (Double doub: array){
			total += Math.pow(doub - mean, 2);
			
		}
		double sd = Math.sqrt(total / (array.size() - 1));

		return sd;
	}
}
