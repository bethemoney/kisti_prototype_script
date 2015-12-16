package Word2Vec;

import java.util.List;

public class kullback {
	public static double getDistance(document document1, document document2) {
		double kullbackValue = 0;
		for (int i = 0; i < document1.size(); i++) {
			double Value = document1.getValue(i) / document2.getValue(i);
			double logValue = Math.log10(Value);
			kullbackValue += document1.getValue(i) * logValue;
		}
		return kullbackValue;
	}
}
