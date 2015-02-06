package org.firebears.sensors;

import java.text.DecimalFormat;

import org.firebears.RobotMap;

public class Accelerometer {
	private static long curTime;
	private static long prevTime;
	private static double timeSec;
	private static double DX;
	private static double Constant;
	private static long n;
	private static double prevA;
	private static double curA;
	private static double totaldispX;
	static DecimalFormat dtime = new DecimalFormat("#.#");
	public static void reset(){
		curTime=0;
		prevTime=System.currentTimeMillis();
	}
	
	public static void updateTime(){
		curTime=System.currentTimeMillis()-prevTime;
		prevTime=System.currentTimeMillis();
		timeSec=curTime*1000;
		//System.out.println("curTime "+curTime);
		//System.out.println("timeSec "+timeSec);
	}
	
	public static void dispX(){
		curA=RobotMap.accelerometer.getX();
		Constant=(n*n)/10000;
		if(n!=0){
		DX=Constant*((n*(prevA+curA))+DX);
		totaldispX=totaldispX+DX;
		prevA=curA;
		n++;
		}
	}
	
	
	
}