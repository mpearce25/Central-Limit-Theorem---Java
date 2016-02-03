import java.util.*;

public class CentralLimitTheorem {

	public static void main(String[] args) {

		Display.initDisplay();

	}

	public static ArrayList<Double> generateRandom(double mean, double sd, int sampleSize, int numberSamples) {
		Random rand = new Random();
		ArrayList<Double> means = new ArrayList<Double>();

		double overallSum = 0;
		for (int x = 0; x < numberSamples; x++) {

			double tempSum = 0;
			for (int k = 0; k < 5; k++) {
				tempSum += (rand.nextGaussian() * sd) + mean;

			}

			means.add(tempSum / 5);

		}

		return means;
	}
}
