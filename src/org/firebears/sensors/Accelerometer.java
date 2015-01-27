package org.firebears.sensors;

import org.firebears.RobotMap;

public class Accelerometer {
	private long curTime;
	private long prevTime;
	private double veloX;
	private double initVeloX;
	private double veloZ;
	private double initVeloZ;
	private long initTime=System.currentTimeMillis();
	private double accX;
	private double accZ;
	private double dispX;
	private double dispZ;
	private boolean veloXinitCalc;
	private boolean dispXinitCalc;
	private boolean veloZinitCalc;
	private boolean dispZinitCalc;
	private double totaldispX;
	private double totaldispZ;
	
	public void update(){
		displacementX();
		displacementZ();
	}
	
	public void reset(){
		curTime=0;
		veloX=0;
		initVeloX=0;
		veloZ=0;
		initVeloZ=0;
		prevTime=0;
		initTime=System.currentTimeMillis();
		accX=0;
		accZ=0;
		dispX=0;
		dispZ=0;
		veloXinitCalc=true;
		dispXinitCalc=true;
		veloZinitCalc=true;
		dispZinitCalc=true;
		totaldispX=0;
		totaldispZ=0;
	}
	
	public void updateTime(){
		prevTime=curTime;
		curTime=System.currentTimeMillis()-initTime;
	}
	
	
	
	
	public void updateaccX(){
		accX= RobotMap.accelerometer.getX();
	}
	
	public void updateVeloX(){
		updateaccX();
		updateTime();
		double at= accX*curTime;
		if (veloXinitCalc==true){
		veloX=initVeloX+at;
		veloXinitCalc=false;
		}else{
		veloX=veloX+at;
		}
	}
	
	public double displacementX(){
		updateVeloX();
		if(dispXinitCalc==true){
			dispX=(0*curTime)+(.5*accX*curTime*curTime);
			dispXinitCalc=false;
		}else{
			dispX=(veloX*curTime)+(.5*accX*curTime*curTime);
		}
		totaldispX=totaldispX+dispX;
		return dispX;
	}
	
	
	

	public void updateaccZ(){
		accZ= RobotMap.accelerometer.getZ();
	}
	
	public void updateVeloZ(){
		updateaccZ();
		updateTime();
		double at= accZ*curTime;
		if (veloZinitCalc==true){
		veloZ=initVeloZ+at;
		veloZinitCalc=false;
		}else{
		veloZ=veloZ+at;
		}
	}
	
	public double displacementZ(){
		updateVeloZ();
		if(dispZinitCalc==true){
			dispZ=(0*curTime)+(.5*accZ*curTime*curTime);
			dispZinitCalc=false;
		}else{
			dispZ=(veloZ*curTime)+(.5*accZ*curTime*curTime);
		}
		totaldispZ=totaldispZ+dispZ;
		return dispZ;
	}
}