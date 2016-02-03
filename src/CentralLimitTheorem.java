import java.security.SecureRandom;
import java.util.*;

public class CentralLimitTheorem {

	public static void main(String[] args) {

		Display.initDisplay();

	}

	public static ArrayList<Double> generateRandom(double mean, double sd, int sampleSize, int numberSamples) {
		SecureRandom rand = new SecureRandom();
		ArrayList<Double> means = new ArrayList<Double>();

		double overallSum = 0;
		for (int x = 0; x < numberSamples; x++) {

			double tempSum = 0;
			for (int k = 0; k < sampleSize; k++) {
				tempSum += (rand.nextGaussian() * sd) + mean;

			}

			means.add(tempSum / sampleSize);

		}

		return means;
	}
}
