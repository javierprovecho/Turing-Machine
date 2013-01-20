package turing.gridworld;

/* 
 * This program is part of an exercise in Think Java (thinkapjava.com)
 * Copyright(c) 2011 Allen B. Downey (http://allendowney.com)
 *
 * It is based on the AP(r) Computer Science GridWorld Case Study.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Allen B. Downey
 */

import java.util.ArrayList;

import turing.addons.Rule;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

/**
 *
 */
public class Head extends Bug {
    Flower flower;

    Flower dropped = null;
    Location dropLoc = null;
    public ArrayList<Rule> ruleList = null;
    public int headPosition = 0;
    public int turingMachineStatus = 1;

    public Head(ArrayList<Rule> ruleList2) {
    	ruleList = ruleList2;
    	ruleList2 = null;
	}
    public void moveLeft(){
    	setDirection(getDirection()-90);
    	move();
    	setDirection(getDirection()+90);
    }
    public void moveRight(){
    	setDirection(getDirection()+90);
    	move();
    	setDirection(getDirection()-90);
    }
	/*
     * Does that thing that termites do. 
     */
    public void act() {
    	if (getGrid() == null) return;
    	moveLeft();
    	moveLeft();
    	moveRight();
    	System.out.print(this.getLocation().getCol());
    	System.out.println(this.getLocation().getRow());
//        if (getGrid() == null) return;
//        
//        // get head symbol
//        char headSymbol = 'h';
//        if (getNeighbor() instanceof Symbol_0){
//        	headSymbol = '0';
//        } else if (getNeighbor() instanceof Symbol_1){
//        	headSymbol = '1';
//        } else if (getNeighbor() instanceof Symbol_a){
//        	headSymbol = 'a';
//        } else if (getNeighbor() instanceof Symbol_b){
//        	headSymbol = 'b';
//        } else if (getNeighbor() instanceof Symbol_h){
//        	headSymbol = 'h';
//        } else if (getNeighbor() instanceof Symbol_x){
//        	headSymbol = 'x';
//        } else if (getNeighbor() instanceof Symbol_y){
//        	headSymbol = 'y';
//        }else if (getNeighbor() == null){
//        	headSymbol = 'h';
//        }
//        
//        // search rule
//        int index = -1;
//		for (int i = 0; i < ruleList.size(); i++){
//			if (ruleList.get(i).e == headSymbol && ruleList.get(i).q == turingMachineStatus) {
//				index = i;
//				break;
//			}
//		}
//		
//		// exception rule not found
//		try{
//			if (index == -1){
//				Exception e = null;
//				throw e;
//			}
//		}catch (Exception e){
//			
//		} 
//		
//		// write to grid
//        if (ruleList.get(index).f == '0'){
//        	new Symbol_0().putSelfInGrid(getGrid(), getNextLocation());
//        } else if (ruleList.get(index).f == '1'){
//        	new Symbol_1().putSelfInGrid(getGrid(), getNextLocation());
//        } else if (ruleList.get(index).f == 'a'){
//        	new Symbol_a().putSelfInGrid(getGrid(), getNextLocation());
//        } else if (ruleList.get(index).f == 'b'){
//        	new Symbol_b().putSelfInGrid(getGrid(), getNextLocation());
//        } else if (ruleList.get(index).f == 'h'){
//        	new Symbol_h().putSelfInGrid(getGrid(), getNextLocation());
//        } else if (ruleList.get(index).f == 'x'){
//        	new Symbol_x().putSelfInGrid(getGrid(), getNextLocation());
//        } else if (ruleList.get(index).f == 'y'){
//        	new Symbol_y().putSelfInGrid(getGrid(), getNextLocation());
//        }
//        
//        // write to state
//		turingMachineStatus = ruleList.get(index).p;
//		
//		// move head
//		if (ruleList.get(index).m == 1){
//			turn(90);
//			move();
//			turn(-90);
//		}else if(ruleList.get(index).m == -1){
//			turn(-90);
//			move();
//			turn(90);
//		}
//		headPosition = headPosition + ruleList.get(index).m;
//		
    }

    /*
     * Turns left or right 45 degrees at random.
     */
//    public void randomTurn() {
//    	if (Math.random() < 0.5) {
//    		turn(-45);
//    	} else {
//    		turn(45);
//    	}
//    }

    /*
     * Turns the given number of degrees.
     */
//    public void turn(int degrees) {
//	setDirection(getDirection() + degrees);
//    }

    /*
     * Checks whether the termite can move.
     * Note: unlike most Bugs, termites can't move onto flowers.
     */
    public boolean canMove() {
        Location next = getNextLocation();
        return (isValid(next) && getNeighbor() == null);
    }
  

    /*
     * Moves in the direction the termite is facing.
     * Drops a flower, if one has been dropped, in the old location.
     */
    public void move() {
    	Location next = getNextLocation();
        if (isValid(next)) {
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
        if (dropped != null) {
        	dropped.putSelfInGrid(getGrid(), dropLoc);
        	dropped = null;
        }
    }

    /*
     * Returns true if the termite has a flower.
     */
    public boolean hasFlower() {
	return flower != null;
    }

    /*
     * Returns true if the termite is facing a flower.
     */
    public boolean seeFlower() {
	return getNeighbor() instanceof Flower;
    }

    /*
     * Creates a flower unless the termite already has one.
     */
    public void createFlower() {
	if (hasFlower()) {
	    System.out.println("createFlower? You already have one!");
	    return;
	}
	flower = new Flower();
    }

    /*
     * Drops the flower in the termites current location.
     * 
     * Note: only one Actor can occupy a grid cell, so the effect of
     * dropping a flower is delayed until the termite moves.
     */
    public void dropFlower() {
	if (!hasFlower()) {
	    System.out.println("dropFlower?  You don't have one!");
	    return;
	}
	dropped = flower;
	dropLoc = getLocation();
	flower = null;
    }

    /*
     * Throws the flower into the location the termite is facing.
     * 
     */
    public void throwFlower() {
	if (!hasFlower()) {
	    System.out.println("throwFlower?  You don't have one!");
	    return;
	}
	if (getNeighbor() != null) {
	    System.out.println("throwFlower?  There's someone there!");
	    return;
	}
	Location next = getNextLocation();
	if (!isValid(next)) {
	    System.out.println("throwFlower?  Invalid location!");
	    return;
	}
	flower.putSelfInGrid(getGrid(), next);
	flower = null;
    }

    /*
     * Picks up the flower the termite is facing, if there is one,
     * and if the termite doesn't already have a flower.
     */
    public void pickUpFlower() {
	if (hasFlower()) {
	    System.out.println("pickUpFlower? You already have one!");
	    return;
	}
	Actor neighbor = getNeighbor();
	if (neighbor instanceof Flower) {
	    flower = (Flower) neighbor;
	    flower.removeSelfFromGrid();
	} else {
	    System.out.println("pickUpFlower? There is no flower!");
	}
    }

    /*
     * Gets the location the termite is facing.
     */
    private Location getNextLocation() {
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
	return next;
    }

    /*
     * Gets the contents of the location the termite is facing.
     * Returns null if the location is empty or invalid.
     */
    private Actor getNeighbor() {
	Location next = getNextLocation();
	if (getGrid().isValid(next)) {
	    return getGrid().get(next);
	} else {
	    return null;
	}
    }

    /*
     * Checks whether a location is valid in the termite's grid.
     */
    private boolean isValid(Location loc) {
	return getGrid().isValid(loc);
    }

}