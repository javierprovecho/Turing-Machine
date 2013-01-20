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
package gridWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

import turing.addons.Rule;
import turing.gridworld.Head;
import turing.gridworld.Symbol_0;
import turing.gridworld.Symbol_1;
import turing.gridworld.Symbol_a;
import turing.gridworld.Symbol_b;
import turing.gridworld.Symbol_h;
import turing.gridworld.Symbol_x;
import turing.gridworld.Symbol_y;

/**
 * This class runs a world that contains Termites. <br />
 */
public class TermiteRunner {
	public static ArrayList<Rule> ruleList = new ArrayList<Rule>();
	public static ArrayList<Character> tape = new ArrayList<Character>();
    public static void main(String[] args) {
    	/*
    	 * unbounded world creation
    	 */
    	
    	ActorWorld world = new ActorWorld(new UnboundedGrid<Actor>());
    	
        /*
         * rule add
         *
         * these rules convert from zero to one and viceversa
         */
    	
        ruleList.add(new Rule(1, '1', 1, '0', 1));
		ruleList.add(new Rule(1, '0', 1, '1', 1));
		
		/*
		 * ask for tape
		 */
				/*
				 * fill in advance
				 */
		
					for(int i =0; i<10; i++){
						tape.add(i, 'h');
					}
				
				/*
				 * fill tape
				 */
				
					Scanner keyboard = new Scanner(System.in);
					System.out.print("Type initial tape composition and press 'Enter' : ");
					String text = null;
					try{
						text = keyboard.nextLine();
						int i = 0;
						for (i=0; i <= text.length()-1; i++){
							if(text.charAt(i)==' '){
								text = text.substring(0, i) + 'h' + text.substring(i + 1);
							}
							if("01abxyh".indexOf(text.charAt(i)) == -1){
								System.out.println("Wrong tape composition. Closing...");
								System.exit(0);
							}
							tape.add(text.charAt(i));
						}
					}
					catch(Exception e){
						System.out.println("Wrong tape composition. Closing...");
						System.exit(0);
					}
					
				/*
				 * fill the end
				 */
					
					for(int i = 0; i<10; i++){
						tape.add(' ');
					}
					
				/*
				 * end
				 */
		
		/*
		 * fill world w/ tape
		 */
					
        fillWorld(world, tape);
        
        
        Head head = new Head(ruleList);
        head.setColor(Color.blue);
        world.add(new Location(1, 0), head);
        
        world.show();
    }
    public static void fillWorld(ActorWorld world, ArrayList<Character> tape){
    	for(int i = 0; i<tape.size(); i++){
    		if(tape.get(i) == '0'){
    			world.add(new Location(0, i),new Symbol_0());
    		} else if (tape.get(i) == '1'){
    			world.add(new Location(0, i),new Symbol_1());
    		} else if (tape.get(i) == 'a'){
    			world.add(new Location(0, i),new Symbol_a());
    		} else if (tape.get(i) == 'b'){
    			world.add(new Location(0, i),new Symbol_b());
    		} else if (tape.get(i) == 'h'){
    			world.add(new Location(0, i),new Symbol_h());
    		} else if (tape.get(i) == 'x'){
    			world.add(new Location(0, i),new Symbol_x());
    		} else if (tape.get(i) == 'y'){
    			world.add(new Location(0, i),new Symbol_y());
    		}
    	}
    }
}