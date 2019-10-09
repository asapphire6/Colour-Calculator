package calculator;

import java.awt.color.ColorSpace;

public class CIELabColourSpace extends ColorSpace {

	public CIELabColourSpace(int type, int numcomponents) {
		// type = 1 for CIELab
		// numcomponents = 3 (L, a, b)
		super(type, numcomponents);
	}

	@Override
	public float[] fromCIEXYZ(float[] colorvalue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float[] fromRGB(float[] rgbvalue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float[] toCIEXYZ(float[] colorvalue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float[] toRGB(float[] colorvalue) {
		// transforms CIE XYZ values to sRGB for display
		double var_X = colorvalue[0]/100;
		double var_Y = colorvalue[1]/100;
		double var_Z = colorvalue[2]/100;
		
		double var_R = (float) (var_X * 3.2406 + var_Y * (-1.5372) + var_Z * (-0.4986));
		double var_G = (float) (var_X * (-0.9689) + var_Y *  1.8758 + var_Z *  0.0415);
		double var_B = (float) (var_X *  0.0557 + var_Y * (-0.2040) + var_Z *  1.0570);
		
		Double sR = convertToSRGB(var_R) * 255;
		Double sG = convertToSRGB(var_G) * 255;
		Double sB = convertToSRGB(var_B) * 255;
		
		float[] sRGB = new float[3];
		
		sRGB[0] = checkIfValueInRGBRange(sR);
//		System.out.println(sRGB[0]);
		sRGB[1] = checkIfValueInRGBRange(sG);
//		System.out.println(sRGB[1]);
		sRGB[2] = checkIfValueInRGBRange(sB);
//		System.out.println(sRGB[2]);
		return sRGB;
	}

	private double convertToSRGB(double var_RGB) {
		if ( var_RGB > 0.0031308 ) {
			var_RGB =  1.055 * (java.lang.Math.pow(var_RGB, (1 / 2.4))) - 0.055;
		} else {
			var_RGB = (12.92 * var_RGB);
		}
		return var_RGB;
	}
	
	private float checkIfValueInRGBRange(Double RGB_value) {
		if(RGB_value < 0.0) {
			return 0;
		} else {
			return RGB_value.floatValue();
		}
	}

}









