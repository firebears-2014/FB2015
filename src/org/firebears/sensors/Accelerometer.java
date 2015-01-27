package org.firebears.sensors;

import java.text.DecimalFormat;

import org.firebears.RobotMap;

public class Accelerometer {
	private static long curTime;
	private static long prevTime;
	private static double veloX;
	private static double initVeloX;
	private static double veloY;
	private static double initVeloY;
	private static double accX;
	private static double accY;
	private static double dispX;
	private static double dispY;
	private static double timeSec;
	private static boolean veloXinitCalc;
	private static boolean dispXinitCalc;
	private static boolean veloYinitCalc;
	private static boolean dispYinitCalc;
	private static double totaldispX;
	private static double totaldispY;
	static DecimalFormat dtime = new DecimalFormat("#.#");
	
	public static void update(){
		displacementX();
		displacementY();
		updateTime();
	}
	
	public static double totalX(){
		return totaldispX;
	}
	public static double totalY(){
		return totaldispY;
	}
	public static void reset(){
		curTime=0;
		veloX=0;
		initVeloX=0;
		veloY=0;
		initVeloY=0;
		prevTime=System.currentTimeMillis();
		accX=0;
		accY=0;
		dispX=0;
		dispY=0;
		veloXinitCalc=true;
		dispXinitCalc=true;
		veloYinitCalc=true;
		dispYinitCalc=true;
		totaldispX=0;
		totaldispY=0;
	}
	
	public static void updateTime(){
		curTime=System.currentTimeMillis()-prevTime;
		prevTime=System.currentTimeMillis();
		timeSec=curTime*.001;
		System.out.println("curTime "+curTime);
		System.out.println("timeSec "+timeSec);
	}
	
	
	
	public static void updateaccX(){
		accX= RobotMap.accelerometer.getX(); 
	    accX=Double.valueOf(dtime.format(accX));
	}
	
	public static void updateVeloX(){
		updateaccX();
		double at= accX*timeSec;
		if (veloXinitCalc==true){
		veloX=initVeloX+at;
		veloXinitCalc=false;
		}else{
		veloX=veloX+at;
		}
	}
	
	public static void displacementX(){
		updateVeloX();
		if(dispXinitCalc==true){
			dispX=(.5*accX*timeSec*timeSec);
			dispXinitCalc=false;
		}else{
			dispX=(veloX*timeSec)+(.5*accX*timeSec*timeSec);
		}
		totaldispX=totaldispX+dispX;
	}
	
	
	

	public static void updateaccY(){
		//new DecimalFormat("#.#").format(accY);
		accY= RobotMap.accelerometer.getY(); 
	    accY=Double.valueOf(dtime.format(accY));
	}
	
	public static void updateVeloY(){
		updateaccY();
		double at= accY*timeSec;
		if (veloYinitCalc==true){
		veloY=initVeloY+at;
		veloYinitCalc=false;
		}else{
		veloY=veloY+at;
		}
	}
	
	public static void displacementY(){
		updateVeloY();
		if(dispYinitCalc==true){
			dispY=(.5*accY*timeSec*timeSec);
			dispYinitCalc=false;
		}else{
			dispY=(veloY*timeSec)+(.5*accY*timeSec*timeSec);
		}
		totaldispY=totaldispY+dispY;
	}
}