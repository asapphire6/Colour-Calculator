package calculator;

import java.util.List;

public class Calculator {

	private final static Double EPSILON = 216.00/24389;
	private final static Double K = 24389.00/27;
	
	private final static Double D65_WITH10DEG_X = 94.8056435;
	private final static Double D65_WITH10DEG_Y = 100.00; 
	private final static Double D65_WITH10DEG_Z = 107.3073784;
	
	public static Double calculateXratio(Double value) {
		return value/D65_WITH10DEG_X;
	}
	
	public static Double calculateYratio(Double value) {
		return value/D65_WITH10DEG_Y;
	}
	
	public static Double calculateZratio(Double value) {
		return value/D65_WITH10DEG_Z;
	}
	
	public static boolean compareToEpsilon(Double Yr) {
		return Yr > EPSILON;
	}
	
	public static Double calculate_f_whenGreaterThanEpsilon(Double whiteRatio) {
		return Math.cbrt(whiteRatio);
	}
	
	public static Double calculate_f_whenSmallerThanEpsilon(Double whiteRatio) {
		return ((K*whiteRatio)+16)/116.00;
	}
	
	public static Double calculate_L(Double fy) {
		return (double) Math.round(((116*fy)-16)*100.00)/100.00;	//returns L rounded up to 2 decimal points
	}
	
	public static Double calculate_a(Double fx, Double fy) {
		return Math.round((500*(fx-fy))*100.00)/100.00;				//returns a rounded up to 2 decimal points
	}
	
	public static Double calculate_b(Double fy, Double fz) {
		return Math.round((200*(fy-fz))*100.00)/100.00;				//returns b rounded up to 2 decimal points
	}
	
	public static Double calculateN_sum(List<Double> stObs, List<Double> illuminant, Double interval) {
		// if used to calculate the N value, pass yBar data as stObs, otherwise for XYZ calculation pass 
		// the relevant stObs value (i.e. xBar, yBar or zBar)
		Double _N = 0.0;
		for (int i = 0; i < illuminant.size(); i++) {
			_N += stObs.get(i) * illuminant.get(i) * interval;
		}
		return _N;
	}

	public static Double calculateTristimulusSum(List<Double> stObs, List<Double> illuminant, Double interval, List<Double> spectrumValue) {
		Double tristimulusSum = 0.0;
		for(int i = 0; i < illuminant.size(); i++) {
			tristimulusSum += (stObs.get(i) * illuminant.get(i) * interval) * spectrumValue.get(i);
		}
		return tristimulusSum;
	}
	
	public static Double calculateTristimulusValue(Double tristimulusSum, Double _Nsum) {
		return 100/_Nsum * tristimulusSum;
	}
}
 