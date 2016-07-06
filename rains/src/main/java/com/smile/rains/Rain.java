package com.smile.rains;

import java.util.Random;

public class Rain {
	Coordinate coordinate;
	public int speed;//速度
	
	public Rain(int x, int y, int speed){
		coordinate = new Coordinate(x, y);
		System.out.println("Speed:"+speed);
		this.speed = speed;
		if(this.speed == 0) {
			this.speed =1;
		}
	}
	
}
