package org.rpgsystem.logic;
/**
 * Copyright (c) 2015 Esa-Petri Tirkkonen and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/epl-v10.html
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is class to use evolutionary computation on RoboCode coding games robot to evolve it.
 * 
 * @author Esa-Petri Tirkkonen
 */
public class Evolution {
	private List<Gene> population;
	private Random rand;
	
	private int populationSize;
	
	//differential weight [0,2]
	final double F=1;
	//crossover probability [0,1]
	final double CR=0.5;
	
	private int orginall=0;

	public Evolution(){
		this.population=new ArrayList<Gene>();
		this.rand=new Random();
		this.populationSize=20;
	}
	
	public Evolution(int populationSize){
		this.population=new ArrayList<Gene>();
		this.rand=new Random();
		this.populationSize=populationSize;
	}
	
	public void init(){
			int i=0;
			while(i<populationSize){
				this.population.add(createGene());
				i++;
			}
	}
	

	private Gene createGene(){
		Gene g=new Gene();
		return g;
	}
	
	private Gene clone(Gene g2){
		Gene g=new Gene();
		return g;
	}

	private Gene DifrentialEvolution(){

		
		//pick random point from population
		int x=(int) Math.floor(rand.nextDouble()*(this.population.size()-1));
		int a=-1,b=-1,c=-1;

		//pick three different random points from population
		do{
			a=(int) Math.floor(rand.nextDouble()*(this.population.size()-1));
		}while(a==x);
		
		do{
			b=(int) Math.floor(rand.nextDouble()*(this.population.size()-1));
		}while(b==x| b==a);
		
		do{
			c=(int) Math.floor(rand.nextDouble()*(this.population.size()-1));
		}while(c==x | c==a | c==b);
		
		// Pick a random index 0-Dimensionality
		final int N=12;
		final int R=rand.nextInt()%N;
		
		//Compute the agent's potentially new position
		Gene g=clone(this.population.get(x));
		this.orginall=x;
		Gene g1=this.population.get(a);
		Gene g2=this.population.get(b);
		Gene g3=this.population.get(c);
		
		double i=rand.nextDouble();
		//if(i==R | i<CR)
		//a+f*(b-c)
		//else
		//x

		if(i==R | i<CR){	
			g.setFloatingpoint(
					(g1.getFloatingpoint()+F*(g2.getFloatingpoint()-g3.getFloatingpoint()))	
					);
			}
		g.setFitness(Long.MIN_VALUE);
		return g;
	}

	
	public Gene getValues(){
		Gene g = DifrentialEvolution();
		this.population.add(g);
		return g;
	}
	
	public void setScore(Long fitness){
		this.population.get( this.population.size()-1).setFitness(fitness);
		//decide whether remove
		Gene g=this.population.get( this.population.size()-1);
		Gene g2=this.population.get(this.orginall);
		if(g.getFitness()<g2.getFitness()){
			population.remove(this.population.size()-1);
		}else{
			population.remove(this.orginall);
		}
		
	}

}

class Gene {
	
	private double fitness;
	
	private int integer;
	private double floatingpoint;
	private boolean bool;
	
	public Gene(){
		this.fitness=Double.MIN_VALUE;
		
		this.integer=0;
		this.floatingpoint=0;
		this.bool=false;
	}

	/**
	 * @return the fitness
	 */
	public double getFitness() {
		return fitness;
	}

	/**
	 * @param fitness the fitness to set
	 */
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	/**
	 * @return the integer
	 */
	public int getInteger() {
		return integer;
	}

	/**
	 * @param integer the integer to set
	 */
	public void setInteger(int integer) {
		this.integer = integer;
	}

	/**
	 * @return the floatingpoint
	 */
	public double getFloatingpoint() {
		return floatingpoint;
	}

	/**
	 * @param floatingpoint the floatingpoint to set
	 */
	public void setFloatingpoint(double floatingpoint) {
		this.floatingpoint = floatingpoint;
	}

	/**
	 * @return the bool
	 */
	public boolean isBool() {
		return bool;
	}

	/**
	 * @param bool the bool to set
	 */
	public void setBool(boolean bool) {
		this.bool = bool;
	}
}

