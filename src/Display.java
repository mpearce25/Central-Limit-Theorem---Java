import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class Display {


	private static JFrame frame;
	private static DefaultListModel model;
	private static ArrayList<Double> meansArrayList;

	public static void initDisplay() {

		Double mean = 0.0;
		Double sd = 0.0;
		Integer sampleSize = 0;
		Integer numberSamples = 0;

		// Create and populate the panel.
		JPanel panel1 = new JPanel(new GridBagLayout());

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

		JLabel statsLabel = new JLabel("Mean of Means", JLabel.LEADING);
		c.gridx = 0;
		c.gridy = 5;
		panel1.add(statsLabel, c);
		JTextField statsTextField = new JTextField(10);
		statsLabel.setLabelFor(statsTextField);
		c.gridx = 1;
		c.gridy = 5;
		panel1.add(statsTextField, c);

		JButton closeButton = new JButton("Generate Random");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Double mean = Double.parseDouble(meanTextField.getText());
				Double sd = Double.parseDouble(sdTextField.getText());
				Integer sampleSize = Integer.parseInt(sampleSizeTextField.getText());
				Integer numberSamples = Integer.parseInt(numberSamplesTextField.getText());

				meansArrayList = CentralLimitTheorem.generateRandom(mean, sd, sampleSize, numberSamples);
				populateArray(meansArrayList);
				statsTextField.setText(Double.toString(calculateMean(meansArrayList)));

			}
		});
		c.gridx = 0;
		c.gridy = 4;
		panel1.add(closeButton, c);

		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				meanTextField.setText("");
				sdTextField.setText("");
				sampleSizeTextField.setText("");
				numberSamplesTextField.setText("");
				statsTextField.setText("");
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
}