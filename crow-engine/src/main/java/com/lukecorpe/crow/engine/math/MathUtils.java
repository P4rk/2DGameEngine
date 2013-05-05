package com.lukecorpe.crow.engine.math;
public class MathUtils {
	  public static double clamp (double i, double low, double high) {
	    return java.lang.Math.max (java.lang.Math.min (i, high), low);
	  }


	  public static float clamp (float i, float low, float high) {
	    return java.lang.Math.max (java.lang.Math.min (i, high), low);
	  }


	  public static int clamp (int i, int low, int high) {
	    return java.lang.Math.max (java.lang.Math.min (i, high), low);
	  }


	  public static long clamp (long i, long low, long high) {
	    return java.lang.Math.max (java.lang.Math.min (i, high), low);
	  }
	  
	  public static float positiveDelta(float f1, float f2){
		return (float) Math.sqrt((float)(f1-f2)*(float)(f1-f2));
	  }
}

