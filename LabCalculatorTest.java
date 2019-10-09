package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import calculator.Calculator;

@RunWith(Parameterized.class)
public class LabCalculatorTest {
	/**
	 *  For test parameters use the following (from the test files):
	 *  XYZ values:
	    {42.87, 44.48, 56.24}	//violet
		{52.94, 51.60, 33.03}	//orange
		{22.57, 12.23,  0.34}	//red
		{39.31, 40.64, 45.25}	//pink
		{74.04, 78.82, 85.30}	//white
		{23.93, 27.66,  3.28}	//yellow
		{ 2.32,  1.66, 12.28}	//blue
		{43.22, 59.04, 24.99}	//green
		
		Correspoding Lab (some of the values were incorrectly calculated in the original files):
		{72.55,  2.09, -8.58}	//violet
		{77.04, 10.69, 25.38}	//orange
		{41.58, 61.69, 66.75}	//red
		{69.92,  2.48, -1.84}	//pink
		{91.15, -1.43, -0.52}	//white
		{59.58, -9.79, 67.78}	//yellow
		{13.59, 17.61,-46.08}	//blue	
		{81.31,-34.65, 44.73}	//green
		
		Corresponding f values:
		{0.767535112, 0.763346279, 0.806262511}	//violet
		{0.823458537, 0.802077931, 0.675197596}	//orange
		{0.619761048, 0.496373764, 0.162604789}	//red	(Zr < EPSILON)
		{0.745672463, 0.740715160, 0.749895747}	//pink
		{0.920878982, 0.923730911, 0.926355547}	//white
		{0.631967373, 0.651554465, 0.312669830}	//yellow
		{0.290319642, 0.255095440, 0.485505497}	//blue
		{0.769618228, 0.838910151, 0.615247864}	//green		
	 */

	private Double fx, fy, fz;
	private Double expected_L, expected_a, expected_b;

	public LabCalculatorTest(Double fx, Double fy, Double fz, Double expected_L, Double expected_a, Double expected_b) {
		this.fx = fx;
		this.fy = fy;
		this.fz = fz;
		this.expected_L = expected_L;
		this.expected_a = expected_a;
		this.expected_b = expected_b;
	}
	@Parameters
	public static Double[][] getXYZdata(){
		Double[][] xyzData = new Double[][]{
				{0.767535112,0.763346279,0.806262511, 72.55,2.09,-8.58},		//violet
				{0.823458537,0.802077931,0.675197596, 77.04, 10.69, 25.38},		//orange
				{0.619761048,0.496373764,0.162604789, 41.58, 61.69, 66.75},		//red
				{0.745672463,0.74071516,0.749895747, 69.92,  2.48, -1.84},		//pink
				{0.920878982,0.923730911,0.926355547, 91.15, -1.43, -0.52},		//white
				{0.631967373,0.651554465,0.31266983, 59.58, -9.79, 67.78},		//yellow
				{0.290319642,0.25509544,0.485505497, 13.59, 17.61,-46.08},		//blue
				{0.769618228,0.838910151,0.615247864, 81.31,-34.65, 44.73}};	//green
		return xyzData;
	}
	
	@Test
	public void calculateL_Test() {
		assertEquals(Calculator.calculate_L(fy), expected_L);
	}
	
	@Test
	public void calculateA_Test() {
		assertEquals(Calculator.calculate_a(fx, fy), expected_a);
	}
	
	@Test
	public void calculateB_Test() {
		assertEquals(Calculator.calculate_b(fy, fz), expected_b);
	}
	
//	@Test
//	public void getD65With10degX_Test() {
//		assertEquals(Calculator.getD65With10degX(), (Double)94.8110);
//	}
//	
//	@Test
//	public void getD65With10degY_Test() {
//		assertEquals(Calculator.getD65With10degY(), (Double)100.00);
//	}
//	
//	@Test
//	public void getD65With10degZ_Test() {
//		assertEquals(Calculator.getD65With10degZ(), (Double)107.304);
//	}
}
