package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	XRatioTest.class,
	YRatioTest.class,
	ZRatioTest.class,
	LabCalculatorTest.class,
	TestsWithConstants.class,
	CorrectIntervalTest.class,
	IncorrectIntervalTest.class,
	calculateXYZTest.class,
	calculateNTest.class,
	ReturnMatchingDataTest.class,
	ReturnDataRangeTest.class
})

public class LabCalculatorTestSuite {

}
