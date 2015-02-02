/*
 * Copyright 2013 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rpgsystem.logic.component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.rpgsystem.logic.Modifier;
import org.terasology.entitySystem.Component;
import org.terasology.network.Replicate;

/**
 * RPG component is massive class that has all stats of character
 * @author Esereja
 */
public class RPGComponent implements Component {
	
	//characters range for actions
	@Replicate
	public float baseRange=1;
	private float range;
	//character action speed
	@Replicate
	public float baseActionSpeed=1;
	private float actionSpeed;
	
	//experience
	//you get physical experience by time,doing physical activities like running
	//fighting, crafting and so on anything to do whit Strength,Agility,Constitution
	
	//physicall experience can be used to buy atribute points or skills 
	//which are physical by nature. they can be also converted to universal xp 
	@Replicate
	public float physicalExperience;
	//you get mental experience by casting magic, learning, crafting, 
	//regonizing objects or stuff and so on anything to do whit Intelligence,Wisdom,Thaumaticy 
	//or charisma related stuff
	
	//mental experience can be used to buy atribute points or skills which are mental by nature
	//they can be also converted to universal xp
	@Replicate
	public float mentalExperience;
	//experience is gained by converting previous ones to it or by events dealling whit
	//luck,super natural, deities and other heroic or rare stuff 
	
	//experience can be used to buy any Atributes or skills if reguirments are filled(enough xp to buy it)
	@Replicate
	public float experience;
	
	//Atributes
	@Replicate
	public float baseStrength=1;
	@Replicate
	public float baseAgility=1;
	@Replicate
	public float baseConstitution=1;
	@Replicate
	public float baseIntelligence=1;	
	@Replicate
	public float baseWisdom=1;	
	@Replicate
	public float baseThaumaticy=1;	
	@Replicate
	public float baseCharisma=1;	
	@Replicate
	public float baseLuck=1;	
	
	private float agility;
	private float strength;
	private float constitution;
	private float intelligence;
	private float wisdom;
	private float thaumaticy;
	private float charisma;
	private float luck;
	
	//Statistics
	//how well you hit enemy, this in added to random and compared to DefenseSuccess
	private float physicalAttackSuccess;
	private float magicalAttackSuccess;
	//variance of random added to attack success. median of random is 0 
	private float magicalAttackSuccessVariance;
	private float physicalAttackSuccessVariance;
	
	//how big area of top AttackSuccess is counted as critical hit
	@Replicate
	public float baseCriticalPercentage;
	private float criticalPercentage;
	//how many times original dmg your hit does
	@Replicate
	public float baseCriticalRatio;
	private float criticalRatio;

	//if this is bigger than attack success, attack will be ignored.
	private float physicalDefenseSuccess;
	private float magicalDefenseSuccess;
	
	//plain attack damage done if attack goes trough. Only resistances are subtracted from this
	//base dmg(from dex and str and so on)
	private float dmg;
	private float bluntDmg;
	private float pierceDmg;
	private float magicalDmg;
	
	//variance of random number. median of random is 0. if corresponding dmg =0 random isn't calculated
	private float dmgVariance;
	private float bluntDmgVariance;
	private float pierceDmgVariance;
	private float magicalDmgVariance;
	
	//this is removed from dmg directly. treat whit care
	private float physicalResistance;
	private float bluntResistance;
	private float pierceResistance;
	private float magicalResistance;
	
	private HashSet<String> rangeSet;
	private HashSet<String> actionSpeedSet;
	
	private HashSet<String> strengthSet;
	private HashSet<String> agilitySet;
	private HashSet<String> constitutionSet;
	private HashSet<String> intelligenceSet;
	private HashSet<String> wisdomSet;
	private HashSet<String> thaumaticySet;
	private HashSet<String> charismaSet;
	private HashSet<String> luckSet;

	private HashSet<String> physicalAttackSuccessSet;
	private HashSet<String> magicalAttackSuccessSet;
	private HashSet<String> magicalAttackSuccessVarianceSet;
	private HashSet<String> physicalAttackSuccessVarianceSet;
	private HashSet<String> criticalPercentageSet;
	private HashSet<String> criticalRatioSet;
	
	private HashSet<String> physicalDefenseSuccessSet;
	private HashSet<String> magicalDefenseSuccessSet;
	
	private HashSet<String> dmgSet;
	private HashSet<String> bluntDmgSet;
	private HashSet<String> pierceDmgSet;
	private HashSet<String> magicalDmgSet;
	
	private HashSet<String> dmgVarianceSet;
	private HashSet<String> bluntDmgVarianceSet;
	private HashSet<String> pierceDmgVarianceSet;
	private HashSet<String> magicalDmgVarianceSet;
	
	private HashSet<String> physicalResistanceSet;
	private HashSet<String> bluntResistanceSet;
	private HashSet<String> pierceResistanceSet;
	private HashSet<String> magicalResistanceSet;
	
	private HashSet<String> speedSet;
	private HashSet<String> healthSet;
	private HashSet<String> hungerSet;
	private HashSet<String> thirstSet;
	private HashSet<String> staminaSet;
	private HashSet<String> thaumaSet;
	private HashSet<String> breathSet;
	
	/**
	 * 
	 */
	public RPGComponent(){
		this.strengthSet=new HashSet<String>();
		this.agilitySet=new HashSet<String>();
		this.constitutionSet=new HashSet<String>();
		this.intelligenceSet=new HashSet<String>();
		this.wisdomSet=new HashSet<String>();
		this.thaumaticySet=new HashSet<String>();
		this.charismaSet=new HashSet<String>();
		this.luckSet=new HashSet<String>();
		
		this.strengthSet.add(new Modifier("baseStrength",this.baseStrength).serialize());
		this.agilitySet.add(new Modifier("baseAgility",this.baseAgility).serialize());
		this.constitutionSet.add(new Modifier("baseConstitution",this.baseConstitution).serialize());
		this.intelligenceSet.add(new Modifier("baseIntelligence",this.baseIntelligence).serialize());
		this.wisdomSet.add(new Modifier("baseWisdom",this.baseWisdom).serialize());
		this.thaumaticySet.add(new Modifier("baseThaumaticy",this.baseThaumaticy).serialize());
		this.charismaSet.add(new Modifier("baseCharisma",this.baseCharisma).serialize());
		this.luckSet.add(new Modifier("baseLuck",this.baseLuck).serialize());
		
		this.rangeSet=new HashSet<String>();
		this.actionSpeedSet=new HashSet<String>();
		
		this.rangeSet.add(new Modifier("baseRange",this.baseRange).serialize());
		this.actionSpeedSet.add(new Modifier("baseActionSpeed",this.baseActionSpeed).serialize());
		
		this.physicalAttackSuccessSet=new HashSet<String>();
		this.magicalAttackSuccessSet=new HashSet<String>();
		this.magicalAttackSuccessVarianceSet=new HashSet<String>();
		this.physicalAttackSuccessVarianceSet=new HashSet<String>();
		this.criticalPercentageSet=new HashSet<String>();
		this.criticalRatioSet=new HashSet<String>();
		
		this.criticalPercentageSet.add(new Modifier("baseCriticalPercentage",this.baseCriticalPercentage).serialize());
		this.criticalRatioSet.add(new Modifier("baseCriticalRatio",this.baseCriticalRatio).serialize());
		
		this.physicalDefenseSuccessSet=new HashSet<String>();
		this.magicalDefenseSuccessSet=new HashSet<String>();
		
		this.dmgSet=new HashSet<String>();
		this.bluntDmgSet=new HashSet<String>();
		this.pierceDmgSet=new HashSet<String>();
		this.magicalDmgSet=new HashSet<String>();
		
		this.dmgVarianceSet=new HashSet<String>();
		this.bluntDmgVarianceSet=new HashSet<String>();
		this.pierceDmgVarianceSet=new HashSet<String>();
		this.magicalDmgVarianceSet=new HashSet<String>();
		
		this.physicalResistanceSet=new HashSet<String>();
		this.bluntResistanceSet=new HashSet<String>();
		this.pierceResistanceSet=new HashSet<String>();
		this.magicalResistanceSet=new HashSet<String>();
		
		this.speedSet=new HashSet<String>();
		this.healthSet=new HashSet<String>();
		this.hungerSet=new HashSet<String>();
		this.thirstSet=new HashSet<String>();
		this.staminaSet=new HashSet<String>();
		this.thaumaSet=new HashSet<String>();
		this.breathSet=new HashSet<String>();
		//this.=new ArraySet<Modifier>();
	}
	
	/**
	 * calculate sum of modifiers
	 * @param set
	 * @return
	 */
	private float calculateSum(Set<String> set){
		float result=0;
		Iterator<String> it=set.iterator();
		while(it.hasNext()){
			result+=Modifier.deserialize( it.next() ).getValue();
		}
		return result;
	}
	
	/**
	 * find modifier of given name
	 * @param set
	 * @param id identifier name of modifier
	 * @return
	 */
	private String find(Set<String> set,String id){
		String result=null;
		Iterator<String> it=set.iterator();
		while(it.hasNext()){
			String temp =it.next();
			if(Modifier.deserialize(temp).getId().matches(id)){
				return temp;
			}
		}
		return result;
	}
	
	/**
	 * remove modifier in set
	 * @param set
	 * @param id
	 * @return
	 */
	private boolean remove(Set<String> set,String id){
		String object=find(set,id);
		if(object!=null){
			set.remove(object);
			return true;
		}
		return false;
	}
	
	/**
	 * find and replace 
	 * @param set
	 * @param modifier
	 */
	private void replace(Set<String> set,Modifier modifier){
		String object=find(set,modifier.getId());
		
		if(object!=null){
			set.remove(object);
			set.add(modifier.serialize());
		}else{
			set.add(modifier.serialize());
		}
		
	}
	
	/**
	 * Defines effects of strength.
	 * strength means physical fitness of character
	 */
	private void strengthEffects(){
		this.addModifier(new Modifier("Strength",this.strength*3),"Health");
		this.addModifier(new Modifier("Strength",this.strength*10),"stamina");
		this.addModifier(new Modifier("Strength",this.strength/4),"breath");
		//needs max carry capacity
		this.addModifier(new Modifier("Strength",this.strength*0.1f),"bluntResistance");
		this.addModifier(new Modifier("Strength",this.strength),"Dmg");
		this.addModifier(new Modifier("Strength",this.strength*0.2f),"BluntDmgVariance");
		this.addModifier(new Modifier("Strength",this.strength/10),"DmgVariance");
		
		this.addModifier(new Modifier("Strength",this.strength/4),"PhysicalAttackSuccess");
	}
	
	/**
	 * Defines effects of agility.
	 * Agility is speed and over all flexibility. like derecity  
	 */
	private void agilityEffects(){
		//primary
		this.addModifier(new Modifier("Agility",this.agility/10),"Speed");//TODO square root here

		this.addModifier(new Modifier("Agility",this.agility*1.1f),"PhysicalAttackSuccess");
		this.addModifier(new Modifier("Agility",this.agility),"PhysicalDefenseSuccess");
		this.addModifier(new Modifier("Agility",this.agility/10),"PhysicalAttackSuccessVariance");
		//secondary
		this.addModifier(new Modifier("Agility",this.agility/3),"Dmg");
		this.addModifier(new Modifier("Agility",(float) (this.agility*0.3)),"PierceDmg");
		this.addModifier(new Modifier("Agility",(float) (this.agility/50)),"range");//TODO square root here
	}
	
	/** 
	 * Defines effects of constitution.
	 * Means physical health and fortitude.
	 */
	private void constitutionEffects(){
		this.addModifier(new Modifier("Constitution",this.constitution*20),"Health");
		this.addModifier(new Modifier("Constitution",this.constitution*10),"stamina");
		this.addModifier(new Modifier("Constitution",this.constitution/2),"breath");
		this.addModifier(new Modifier("Constitution",this.constitution),"thirst");
		this.addModifier(new Modifier("Constitution",this.constitution*1.5f),"hunger");
		
		this.addModifier(new Modifier("Constitution",this.constitution/10),"physicalResistance");
	}
	
	/**
	 * Defines effects of intelligence. Means knowledge of things, know how and so on. All kind of knowledge
	 */
	private void intelligenceEffects(){
		this.addModifier(new Modifier("intelligence",this.intelligence),"magicalDmg");
		this.addModifier(new Modifier("intelligence",this.intelligence/2),"magicalDefenseSuccess");
		this.addModifier(new Modifier("intelligence",this.intelligence/6),"magicalResistance");
		
		this.addModifier(new Modifier("intelligence",this.intelligence/10),"magicalAttackSuccessVariance");
		this.addModifier(new Modifier("intelligence",this.intelligence/10),"magicalDmgVariance");
		this.addModifier(new Modifier("intelligence",this.intelligence/10),"pierceResistance");
	}
	
	/**
	 * Defines effects of wisdom. Means ability apply knowledge, intuitive understanding of things
	 */
	private void wisdomEffects(){
		this.addModifier(new Modifier("Wisdom",this.wisdom/2),"Dmg");
		this.addModifier(new Modifier("Wisdom",this.wisdom/3),"magicalDmg");
		this.addModifier(new Modifier("Wisdom",this.wisdom),"magicalDefenseSuccess");
		this.addModifier(new Modifier("Wisdom",this.wisdom*1.1f),"magicalAttackSuccess");
		this.addModifier(new Modifier("Wisdom",this.wisdom/2),"PhysicalAttackSuccess");
		this.addModifier(new Modifier("Wisdom",this.wisdom/3),"PhysicalDefenseSuccess");
		this.addModifier(new Modifier("Wisdom",this.wisdom/15),"magicalResistance");
	}
	
	/**
	 * Defines effects of thaumaticy. Means connection to to "magic"
	 */
	private void thaumaticyEffects(){
		this.addModifier(new Modifier("thaumaticy",this.thaumaticy*10),"thauma");
		if(this.thaumaticy==0){
			this.addModifier(new Modifier("thaumaticy",100),"magicalResistance");
		}else{
			this.addModifier(new Modifier("thaumaticy",-this.thaumaticy/10),"magicalResistance");
		}
		this.addModifier(new Modifier("thaumaticy",this.thaumaticy/2),"magicalAttackSuccess");
		this.addModifier(new Modifier("thaumaticy",this.thaumaticy/10),"magicalAttackSuccessVariance");
	}
	
	/**
	 * Defines effects of charisma. charisma means strength of characters personality, perception. 
	 * ability to regonize diference between you and enviroment "empathy"
	 */
	private void charismaEffects(){
		//ability to regonize characters(names and stuff)
		//ability regonize monters
		//ability to know monster lvl
		//ability to resist charms, panic and such
		//max pets, minions
		//partially ability to notice invisble units and stuff 
	}
	
	/**
	 * Defines effects of luck
	 */
	private void luckEffects(){
		this.addModifier(new Modifier("Luck",this.luck/10),"CriticalRatio");
		this.addModifier(new Modifier("Luck",this.luck/100),"CriticalPercentage");//TODO square root here
		
		this.addModifier(new Modifier("Luck",this.luck/2),"PhysicalAttackSuccessVariance");
		this.addModifier(new Modifier("Luck",this.luck/2),"MagicalAttackSuccessVariance");
		
		this.addModifier(new Modifier("Luck",this.luck/8),"PhysicalDefenseSuccess");
		this.addModifier(new Modifier("Luck",this.luck/8),"MagicalDefenseSuccess");
		
		this.addModifier(new Modifier("Luck",this.luck/4),"DmgVariance");
		this.addModifier(new Modifier("Luck",this.luck/4),"BluntDmgVariance");
		this.addModifier(new Modifier("Luck",this.luck/4),"PierceDmgVariance");
		this.addModifier(new Modifier("Luck",this.luck/4),"MagicalDmgVariance");
	}
	
	/**
	 * add modifier from modifier set of given name
	 * @param modifier to be added/replaced to set
	 * @param name of set to be accessed
	 * @return return false if set doesn't exist
	 */
	public boolean addModifier(Modifier modifier,String name){
		boolean succes=false;
		if(name.compareToIgnoreCase("Range")==0){
			replace(this.rangeSet,modifier);
			this.range=calculateSum(this.rangeSet);
			succes=true;
			
		}else if(name.compareToIgnoreCase("actionSpeed")==0){
			replace(this.actionSpeedSet,modifier);
			this.actionSpeed=calculateSum(this.actionSpeedSet);
			succes=true;
			
		}else if(name.compareToIgnoreCase("strength")==0){
			replace(this.strengthSet,modifier);
			this.strength=calculateSum(this.strengthSet);
			strengthEffects();
			succes=true;
			
		}else if(name.compareToIgnoreCase("agility")==0){
			replace(this.agilitySet,modifier);
			this.agility=calculateSum(this.agilitySet);
			agilityEffects();
			succes=true;
			
		}else if(name.compareToIgnoreCase("constitution")==0){
			replace(this.constitutionSet,modifier);
			this.constitution=calculateSum(this.constitutionSet);
			constitutionEffects();
			succes=true;
			
		}else if(name.compareToIgnoreCase("intelligence")==0){
			replace(this.intelligenceSet,modifier);
			this.intelligence=calculateSum(this.intelligenceSet);
			intelligenceEffects();
			succes=true;
			
		}else if(name.compareToIgnoreCase("wisdom")==0){
			replace(this.wisdomSet,modifier);
			this.wisdom=calculateSum(this.wisdomSet);
			wisdomEffects();
			succes=true;
			
		}else if(name.compareToIgnoreCase("thaumaticy")==0){
			replace(this.thaumaticySet,modifier);
			this.thaumaticy=calculateSum(this.thaumaticySet);
			thaumaticyEffects();
			succes=true;
			
		}else if(name.compareToIgnoreCase("charisma")==0){
			replace(this.charismaSet,modifier);
			this.charisma=calculateSum(this.charismaSet);
			charismaEffects();
			succes=true;
			
		}else if(name.compareToIgnoreCase("luck")==0){
			replace(this.luckSet,modifier);
			this.luck=calculateSum(this.luckSet);
			luckEffects();
			succes=true;
			
		}else if(name.compareToIgnoreCase("physicalAttackSuccess")==0){
			replace(this.physicalAttackSuccessSet,modifier);
			this.physicalAttackSuccess=calculateSum(this.physicalAttackSuccessSet);
			succes=true;
			
		}else if(name.compareToIgnoreCase("magicalAttackSuccess")==0){
			replace(this.magicalAttackSuccessSet,modifier);
			this.magicalAttackSuccess=calculateSum(this.magicalAttackSuccessSet);
			succes=true;
			
		}else if(name.compareToIgnoreCase("physicalAttackSuccessVariance")==0){
			replace(this.physicalAttackSuccessVarianceSet,modifier);
			this.physicalAttackSuccessVariance=calculateSum(this.physicalAttackSuccessVarianceSet);
			succes=true;
			
		}else if(name.compareToIgnoreCase("magicalAttackSuccessVariance")==0){
			replace(this.magicalAttackSuccessVarianceSet,modifier);
			this.magicalAttackSuccessVariance=calculateSum(this.magicalAttackSuccessVarianceSet);
			succes=true;
			
		}else if(name.compareToIgnoreCase("criticalPercentage")==0){
			replace(this.criticalPercentageSet,modifier);
			this.criticalPercentage=calculateSum(this.criticalPercentageSet);
			succes=true;
			
		}else if(name.compareToIgnoreCase("criticalRatio")==0){
			replace(this.criticalRatioSet,modifier);
			this.criticalRatio=calculateSum(this.criticalRatioSet);
			succes=true;
			
		}else if(name.compareToIgnoreCase("physicalDefenseSuccess")==0){
			replace(this.physicalDefenseSuccessSet,modifier);
			this.physicalDefenseSuccess=calculateSum(this.physicalDefenseSuccessSet);
			succes=true;
						
		}else if(name.compareToIgnoreCase("magicalDefenseSuccess")==0){
			replace(this.magicalDefenseSuccessSet,modifier);
			this.magicalDefenseSuccess=calculateSum(this.magicalDefenseSuccessSet);
			succes=true;
						
		}else if(name.compareToIgnoreCase("dmg")==0){
			replace(this.dmgSet,modifier);
			this.dmg=calculateSum(this.dmgSet);
			succes=true;
						
		}else if(name.compareToIgnoreCase("bluntDmg")==0){
			replace(this.bluntDmgSet,modifier);
			this.bluntDmg=calculateSum(this.bluntDmgSet);
			succes=true;
						
		}else if(name.compareToIgnoreCase("pierceDmg")==0){
			replace(this.pierceDmgSet,modifier);
			this.pierceDmg=calculateSum(this.pierceDmgSet);
			succes=true;
						
		}else if(name.compareToIgnoreCase("magicalDmg")==0){
			replace(this.magicalDmgSet,modifier);
			this.magicalDmg=calculateSum(this.magicalDmgSet);
			succes=true;
						
		}else if(name.compareToIgnoreCase("dmgVariance")==0){
			replace(this.dmgVarianceSet,modifier);
			this.dmgVariance=calculateSum(this.dmgVarianceSet);
			succes=true;
						
		}else if(name.compareToIgnoreCase("bluntDmgVariance")==0){
			replace(this.bluntDmgVarianceSet,modifier);
			this.bluntDmgVariance=calculateSum(this.bluntDmgVarianceSet);
			succes=true;
						
		}else if(name.compareToIgnoreCase("pierceDmgVariance")==0){
			replace(this.pierceDmgVarianceSet,modifier);
			this.pierceDmgVariance=calculateSum(this.pierceDmgVarianceSet);
			succes=true;
						
		}else if(name.compareToIgnoreCase("magicalDmgVariance")==0){
			replace(this.magicalDmgVarianceSet,modifier);
			this.magicalDmgVariance=calculateSum(this.magicalDmgVarianceSet);
			succes=true;
						
		}else if(name.compareToIgnoreCase("physicalResistance")==0){
			replace(this.physicalResistanceSet,modifier);
			this.physicalResistance=calculateSum(this.physicalResistanceSet);
			succes=true;
						
		}else if(name.compareToIgnoreCase("bluntResistance")==0){
			replace(this.bluntResistanceSet,modifier);
			this.bluntResistance=calculateSum(this.bluntResistanceSet);
			succes=true;
						
		}else if(name.compareToIgnoreCase("pierceResistance")==0){
			replace(this.pierceResistanceSet,modifier);
			this.pierceResistance=calculateSum(this.pierceResistanceSet);
			succes=true;
						
		}else if(name.compareToIgnoreCase("magicalResistance")==0){
			replace(this.magicalResistanceSet,modifier);
			this.magicalResistance=calculateSum(this.magicalResistanceSet);
			succes=true;
						
		}else if(name.compareToIgnoreCase("speed")==0){
			replace(this.speedSet,modifier);
			succes=true;
					
		}else if(name.compareToIgnoreCase("health")==0){
			replace(this.healthSet,modifier);
			succes=true;
						
		}else if(name.compareToIgnoreCase("hunger")==0){
			replace(this.hungerSet,modifier);
			succes=true;
					
		}else if(name.compareToIgnoreCase("thirst")==0){
			replace(this.thirstSet,modifier);
			succes=true;
						
		}else if(name.compareToIgnoreCase("stamina")==0){
			replace(this.staminaSet,modifier);
			succes=true;
						
		}else if(name.compareToIgnoreCase("thauma")==0){
			replace(this.thaumaSet,modifier);
			succes=true;
						
		}else if(name.compareToIgnoreCase("Set")==0){
			replace(this.breathSet,modifier);
			succes=true;
						
		}
		return succes;
	}
	
	/**
	 * remove modifier from modifier set of given name
	 * @param id of modifier to be removed from set
	 * @param name of set to be accessed
	 * @return false if modifier do not exit on given set
	 */
	public boolean removeModifier(String id,String name){
		boolean result=false;
		if(name.compareToIgnoreCase("Range")==0){
			result=remove(this.rangeSet,id);
			this.range=calculateSum(this.rangeSet);
			
		}else if(name.compareToIgnoreCase("actionSpeed")==0){
			result=remove(this.actionSpeedSet,id);
			this.actionSpeed=calculateSum(this.actionSpeedSet);
			
		}else if(name.compareToIgnoreCase("strength")==0){
			result=remove(this.strengthSet,id);
			this.strength=calculateSum(this.strengthSet);
			strengthEffects();
			
		}else if(name.compareToIgnoreCase("agility")==0){
			result=remove(this.agilitySet,id);
			this.agility=calculateSum(this.agilitySet);
			agilityEffects();
			
		}else if(name.compareToIgnoreCase("constitution")==0){
			result=remove(this.constitutionSet,id);
			this.constitution=calculateSum(this.constitutionSet);
			constitutionEffects();
			
		}else if(name.compareToIgnoreCase("intelligence")==0){
			result=remove(this.intelligenceSet,id);
			this.intelligence=calculateSum(this.intelligenceSet);
			intelligenceEffects();
			
		}else if(name.compareToIgnoreCase("wisdom")==0){
			result=remove(this.wisdomSet,id);
			this.wisdom=calculateSum(this.wisdomSet);
			wisdomEffects();
			
		}else if(name.compareToIgnoreCase("thaumaticy")==0){
			result=remove(this.thaumaticySet,id);
			this.thaumaticy=calculateSum(this.thaumaticySet);
			thaumaticyEffects();
			
		}else if(name.compareToIgnoreCase("charisma")==0){
			result=remove(this.charismaSet,id);
			this.charisma=calculateSum(this.charismaSet);
			charismaEffects();
			
		}else if(name.compareToIgnoreCase("luck")==0){
			result=remove(this.luckSet,id);
			this.luck=calculateSum(this.luckSet);
			luckEffects();
			
		}else if(name.compareToIgnoreCase("physicalAttackSuccess")==0){
			result=remove(this.physicalAttackSuccessSet,id);
			this.physicalAttackSuccess=calculateSum(this.physicalAttackSuccessSet);
			
		}else if(name.compareToIgnoreCase("magicalAttackSuccess")==0){
			result=remove(this.magicalAttackSuccessSet,id);
			this.magicalAttackSuccess=calculateSum(this.magicalAttackSuccessSet);
			
		}else if(name.compareToIgnoreCase("physicalAttackSuccessVariance")==0){
			result=remove(this.physicalAttackSuccessVarianceSet,id);
			this.physicalAttackSuccessVariance=calculateSum(this.physicalAttackSuccessVarianceSet);
			
		}else if(name.compareToIgnoreCase("magicalAttackSuccessVariance")==0){
			result=remove(this.magicalAttackSuccessVarianceSet,id);
			this.magicalAttackSuccessVariance=calculateSum(this.magicalAttackSuccessVarianceSet);
			
		}else if(name.compareToIgnoreCase("criticalPercentage")==0){
			result=remove(this.criticalPercentageSet,id);
			this.criticalPercentage=calculateSum(this.criticalPercentageSet);
			
		}else if(name.compareToIgnoreCase("criticalRatio")==0){
			result=remove(this.criticalRatioSet,id);
			this.criticalRatio=calculateSum(this.criticalRatioSet);
			
		}else if(name.compareToIgnoreCase("physicalDefenseSuccess")==0){
			result=remove(this.physicalDefenseSuccessSet,id);
			this.physicalDefenseSuccess=calculateSum(this.physicalDefenseSuccessSet);
						
		}else if(name.compareToIgnoreCase("magicalDefenseSuccess")==0){
			result=remove(this.magicalDefenseSuccessSet,id);
			this.magicalDefenseSuccess=calculateSum(this.magicalDefenseSuccessSet);
						
		}else if(name.compareToIgnoreCase("dmg")==0){
			result=remove(this.dmgSet,id);
			this.dmg=calculateSum(this.dmgSet);
						
		}else if(name.compareToIgnoreCase("bluntDmg")==0){
			result=remove(this.bluntDmgSet,id);
			this.bluntDmg=calculateSum(this.bluntDmgSet);
						
		}else if(name.compareToIgnoreCase("pierceDmg")==0){
			result=remove(this.pierceDmgSet,id);
			this.pierceDmg=calculateSum(this.pierceDmgSet);
						
		}else if(name.compareToIgnoreCase("magicalDmg")==0){
			result=remove(this.magicalDmgSet,id);
			this.magicalDmg=calculateSum(this.magicalDmgSet);
						
		}else if(name.compareToIgnoreCase("dmgVariance")==0){
			result=remove(this.dmgVarianceSet,id);
			this.dmgVariance=calculateSum(this.dmgVarianceSet);
						
		}else if(name.compareToIgnoreCase("bluntDmgVariance")==0){
			result=remove(this.bluntDmgVarianceSet,id);
			this.bluntDmgVariance=calculateSum(this.bluntDmgVarianceSet);
						
		}else if(name.compareToIgnoreCase("pierceDmgVariance")==0){
			result=remove(this.pierceDmgVarianceSet,id);
			this.pierceDmgVariance=calculateSum(this.pierceDmgVarianceSet);
						
		}else if(name.compareToIgnoreCase("magicalDmgVariance")==0){
			result=remove(this.magicalDmgVarianceSet,id);
			this.magicalDmgVariance=calculateSum(this.magicalDmgVarianceSet);
						
		}else if(name.compareToIgnoreCase("physicalResistance")==0){
			result=remove(this.physicalResistanceSet,id);
			this.physicalResistance=calculateSum(this.physicalResistanceSet);
						
		}else if(name.compareToIgnoreCase("bluntResistance")==0){
			result=remove(this.bluntResistanceSet,id);
			this.bluntResistance=calculateSum(this.bluntResistanceSet);
						
		}else if(name.compareToIgnoreCase("pierceResistance")==0){
			result=remove(this.pierceResistanceSet,id);
			this.pierceResistance=calculateSum(this.pierceResistanceSet);
						
		}else if(name.compareToIgnoreCase("magicalResistance")==0){
			result=remove(this.magicalResistanceSet,id);
			this.magicalResistance=calculateSum(this.magicalResistanceSet);
						
		}else if(name.compareToIgnoreCase("speed")==0){
			result=remove(this.speedSet,id);
						
		}else if(name.compareToIgnoreCase("health")==0){
			result=remove(this.healthSet,id);
						
		}else if(name.compareToIgnoreCase("hunger")==0){
			result=remove(this.hungerSet,id);
						
		}else if(name.compareToIgnoreCase("thirst")==0){
			result=remove(this.thirstSet,id);
						
		}else if(name.compareToIgnoreCase("stamina")==0){
			result=remove(this.staminaSet,id);
						
		}else if(name.compareToIgnoreCase("thauma")==0){
			result=remove(this.thaumaSet,id);
						
		}else if(name.compareToIgnoreCase("Set")==0){
			result=remove(this.breathSet,id);			
		}

		return result;
	}
	
	/**
	 * remove modifier from modifier set of given name
	 * @param id of modifier to be removed from set
	 * @param name of set to be accessed
	 * @return false if modifier do not exit on given set
	 */
	public boolean removeModifier(Modifier modifier,String name){
		return removeModifier(modifier.getId(),name);
	}	
		
//--------------------------------------------------------------------------------	

	/**
	 * @param baseRange the baseRange to set
	 */
	public final void setBaseRange(float baseRange) {
		this.baseRange = baseRange;
		replace(this.rangeSet,new Modifier("baseRange",this.baseRange));
	}

	/**
	 * @param baseStrength the baseStrength to set
	 */
	public final void setBaseStrength(float baseStrength) {
		this.baseStrength = baseStrength;
		replace(this.strengthSet,new Modifier("baseStrength",this.baseStrength));
	}

	/**
	 * @param baseAgility the baseAgility to set
	 */
	public final void setBaseAgility(float baseAgility) {
		this.baseAgility = baseAgility;
		replace(this.agilitySet,new Modifier("baseAgility",this.baseAgility));
	}

	/**
	 * @param baseConstitution the baseConstitution to set
	 */
	public final void setBaseConstitution(float baseConstitution) {
		this.baseConstitution = baseConstitution;
		replace(this.constitutionSet,new Modifier("baseConstitution",this.baseConstitution));
	}

	/**
	 * @param baseIntelligence the baseIntelligence to set
	 */
	public final void setBaseIntelligence(float baseIntelligence) {
		this.baseIntelligence = baseIntelligence;
		replace(this.intelligenceSet,new Modifier("baseIntelligence",this.baseIntelligence));
	}

	/**
	 * @param baseWisdom the baseWisdom to set
	 */
	public final void setBaseWisdom(float baseWisdom) {
		this.baseWisdom = baseWisdom;
		replace(this.wisdomSet,new Modifier("baseWisdom",this.baseWisdom));
	}

	/**
	 * @param baseThaumaticy the baseThaumaticy to set
	 */
	public final void setBaseThaumaticy(float baseThaumaticy) {
		this.baseThaumaticy = baseThaumaticy;
		replace(this.thaumaticySet,new Modifier("baseThaumaticy",this.baseThaumaticy));
	}

	/**
	 * @param baseCharisma the baseCharisma to set
	 */
	public final void setBaseCharisma(float baseCharisma) {
		this.baseCharisma = baseCharisma;
		replace(this.charismaSet,new Modifier("baseCharisma",this.baseCharisma));
	}


	/**
	 * @param baseLuck the baseLuck to set
	 */
	public final void setBaseLuck(float baseLuck) {
		this.baseLuck = baseLuck;
		replace(this.luckSet,new Modifier("baseLuck",this.baseLuck));
	}


	/**
	 * @param baseCriticalPercentage the baseCriticalPercentage to set
	 */
	public final void setBaseCriticalPercentage(float baseCriticalPercentage) {
		this.baseCriticalPercentage = baseCriticalPercentage;
		replace(this.criticalPercentageSet,new Modifier("baseCriticalPercentage",this.baseCriticalPercentage));
	}

	/**
	 * @param baseCriticalRatio the baseCriticalRatio to set
	 */
	public final void setBaseCriticalRatio(float baseCriticalRatio) {
		this.baseCriticalRatio = baseCriticalRatio;
		replace(this.criticalRatioSet,new Modifier("baseCriticalRatio",this.baseCriticalRatio));
	}

	/**
	 * @param baseActionSpeed the baseActionSpeed to set
	 */
	public void setBaseActionSpeed(float baseActionSpeed) {
		this.baseActionSpeed = baseActionSpeed;
		replace(this.actionSpeedSet ,new Modifier("baseActionSpeed",this.baseActionSpeed));
	}
	
	//------------------------------------------------------------------------------------------
	//fully generated stuff	
	
	/**
	 * @return the baseRange
	 */
	public final float getBaseRange() {
		return baseRange;
	}

	/**
	 * @return the range
	 */
	public float getRange() {
		return range;
	}

	/**
	 * @return the baseActionSpeed
	 */
	public float getBaseActionSpeed() {
		return baseActionSpeed;
	}

	/**
	 * @return the actionSpeed
	 */
	public float getActionSpeed() {
		return actionSpeed;
	}

	/**
	 * @return the physicalExperience
	 */
	public float getPhysicalExperience() {
		return physicalExperience;
	}

	/**
	 * @return the mentalExperience
	 */
	public float getMentalExperience() {
		return mentalExperience;
	}

	/**
	 * @return the experience
	 */
	public float getExperience() {
		return experience;
	}

	/**
	 * @return the baseStrength
	 */
	public float getBaseStrength() {
		return baseStrength;
	}

	/**
	 * @return the baseAgility
	 */
	public float getBaseAgility() {
		return baseAgility;
	}

	/**
	 * @return the baseConstitution
	 */
	public float getBaseConstitution() {
		return baseConstitution;
	}

	/**
	 * @return the baseIntelligence
	 */
	public float getBaseIntelligence() {
		return baseIntelligence;
	}

	/**
	 * @return the baseWisdom
	 */
	public float getBaseWisdom() {
		return baseWisdom;
	}

	/**
	 * @return the baseThaumaticy
	 */
	public float getBaseThaumaticy() {
		return baseThaumaticy;
	}

	/**
	 * @return the baseCharisma
	 */
	public float getBaseCharisma() {
		return baseCharisma;
	}

	/**
	 * @return the baseLuck
	 */
	public float getBaseLuck() {
		return baseLuck;
	}

	/**
	 * @return the agility
	 */
	public float getAgility() {
		return agility;
	}

	/**
	 * @return the strength
	 */
	public float getStrength() {
		return strength;
	}

	/**
	 * @return the constitution
	 */
	public float getConstitution() {
		return constitution;
	}

	/**
	 * @return the intelligence
	 */
	public float getIntelligence() {
		return intelligence;
	}

	/**
	 * @return the wisdom
	 */
	public float getWisdom() {
		return wisdom;
	}

	/**
	 * @return the thaumaticy
	 */
	public float getThaumaticy() {
		return thaumaticy;
	}

	/**
	 * @return the charisma
	 */
	public float getCharisma() {
		return charisma;
	}

	/**
	 * @return the luck
	 */
	public float getLuck() {
		return luck;
	}

	/**
	 * @return the physicalAttackSuccess
	 */
	public float getPhysicalAttackSuccess() {
		return physicalAttackSuccess;
	}

	/**
	 * @return the magicalAttackSuccess
	 */
	public float getMagicalAttackSuccess() {
		return magicalAttackSuccess;
	}

	/**
	 * @return the magicalAttackSuccessVariance
	 */
	public float getMagicalAttackSuccessVariance() {
		return magicalAttackSuccessVariance;
	}

	/**
	 * @return the physicalAttackSuccessVariance
	 */
	public float getPhysicalAttackSuccessVariance() {
		return physicalAttackSuccessVariance;
	}

	/**
	 * @return the baseCriticalPercentage
	 */
	public float getBaseCriticalPercentage() {
		return baseCriticalPercentage;
	}

	/**
	 * @return the criticalPercentage
	 */
	public float getCriticalPercentage() {
		return criticalPercentage;
	}

	/**
	 * @return the baseCriticalRatio
	 */
	public float getBaseCriticalRatio() {
		return baseCriticalRatio;
	}

	/**
	 * @return the criticalRatio
	 */
	public float getCriticalRatio() {
		return criticalRatio;
	}

	/**
	 * @return the physicalDefenseSuccess
	 */
	public float getPhysicalDefenseSuccess() {
		return physicalDefenseSuccess;
	}

	/**
	 * @return the magicalDefenseSuccess
	 */
	public float getMagicalDefenseSuccess() {
		return magicalDefenseSuccess;
	}

	/**
	 * @return the dmg
	 */
	public float getDmg() {
		return dmg;
	}

	/**
	 * @return the bluntDmg
	 */
	public float getBluntDmg() {
		return bluntDmg;
	}

	/**
	 * @return the pierceDmg
	 */
	public float getPierceDmg() {
		return pierceDmg;
	}

	/**
	 * @return the magicalDmg
	 */
	public float getMagicalDmg() {
		return magicalDmg;
	}

	/**
	 * @return the dmgVariance
	 */
	public float getDmgVariance() {
		return dmgVariance;
	}

	/**
	 * @return the bluntDmgVariance
	 */
	public float getBluntDmgVariance() {
		return bluntDmgVariance;
	}

	/**
	 * @return the pierceDmgVariance
	 */
	public float getPierceDmgVariance() {
		return pierceDmgVariance;
	}

	/**
	 * @return the magicalDmgVariance
	 */
	public float getMagicalDmgVariance() {
		return magicalDmgVariance;
	}

	/**
	 * @return the physicalResistance
	 */
	public float getPhysicalResistance() {
		return physicalResistance;
	}

	/**
	 * @return the bluntResistance
	 */
	public float getBluntResistance() {
		return bluntResistance;
	}

	/**
	 * @return the pierceResistance
	 */
	public float getPierceResistance() {
		return pierceResistance;
	}

	/**
	 * @return the magicalResistance
	 */
	public float getMagicalResistance() {
		return magicalResistance;
	}

	/**
	 * @return the rangeSet
	 */
	public HashSet<String> getRangeSet() {
		return rangeSet;
	}

	/**
	 * @return the actionSpeedSet
	 */
	public HashSet<String> getActionSpeedSet() {
		return actionSpeedSet;
	}

	/**
	 * @return the strengthSet
	 */
	public HashSet<String> getStrengthSet() {
		return strengthSet;
	}

	/**
	 * @return the agilitySet
	 */
	public HashSet<String> getAgilitySet() {
		return agilitySet;
	}

	/**
	 * @return the constitutionSet
	 */
	public HashSet<String> getConstitutionSet() {
		return constitutionSet;
	}

	/**
	 * @return the intelligenceSet
	 */
	public HashSet<String> getIntelligenceSet() {
		return intelligenceSet;
	}

	/**
	 * @return the wisdomSet
	 */
	public HashSet<String> getWisdomSet() {
		return wisdomSet;
	}

	/**
	 * @return the thaumaticySet
	 */
	public HashSet<String> getThaumaticySet() {
		return thaumaticySet;
	}

	/**
	 * @return the charismaSet
	 */
	public HashSet<String> getCharismaSet() {
		return charismaSet;
	}

	/**
	 * @return the luckSet
	 */
	public HashSet<String> getLuckSet() {
		return luckSet;
	}

	/**
	 * @return the physicalAttackSuccessSet
	 */
	public HashSet<String> getPhysicalAttackSuccessSet() {
		return physicalAttackSuccessSet;
	}

	/**
	 * @return the magicalAttackSuccessSet
	 */
	public HashSet<String> getMagicalAttackSuccessSet() {
		return magicalAttackSuccessSet;
	}

	/**
	 * @return the magicalAttackSuccessVarianceSet
	 */
	public HashSet<String> getMagicalAttackSuccessVarianceSet() {
		return magicalAttackSuccessVarianceSet;
	}

	/**
	 * @return the physicalAttackSuccessVarianceSet
	 */
	public HashSet<String> getPhysicalAttackSuccessVarianceSet() {
		return physicalAttackSuccessVarianceSet;
	}

	/**
	 * @return the criticalPercentageSet
	 */
	public HashSet<String> getCriticalPercentageSet() {
		return criticalPercentageSet;
	}

	/**
	 * @return the criticalRatioSet
	 */
	public HashSet<String> getCriticalRatioSet() {
		return criticalRatioSet;
	}

	/**
	 * @return the physicalDefenseSuccessSet
	 */
	public HashSet<String> getPhysicalDefenseSuccessSet() {
		return physicalDefenseSuccessSet;
	}

	/**
	 * @return the magicalDefenseSuccessSet
	 */
	public HashSet<String> getMagicalDefenseSuccessSet() {
		return magicalDefenseSuccessSet;
	}

	/**
	 * @return the dmgSet
	 */
	public HashSet<String> getDmgSet() {
		return dmgSet;
	}

	/**
	 * @return the bluntDmgSet
	 */
	public HashSet<String> getBluntDmgSet() {
		return bluntDmgSet;
	}

	/**
	 * @return the pierceDmgSet
	 */
	public HashSet<String> getPierceDmgSet() {
		return pierceDmgSet;
	}

	/**
	 * @return the magicalDmgSet
	 */
	public HashSet<String> getMagicalDmgSet() {
		return magicalDmgSet;
	}

	/**
	 * @return the dmgVarianceSet
	 */
	public HashSet<String> getDmgVarianceSet() {
		return dmgVarianceSet;
	}

	/**
	 * @return the bluntDmgVarianceSet
	 */
	public HashSet<String> getBluntDmgVarianceSet() {
		return bluntDmgVarianceSet;
	}

	/**
	 * @return the pierceDmgVarianceSet
	 */
	public HashSet<String> getPierceDmgVarianceSet() {
		return pierceDmgVarianceSet;
	}

	/**
	 * @return the magicalDmgVarianceSet
	 */
	public HashSet<String> getMagicalDmgVarianceSet() {
		return magicalDmgVarianceSet;
	}

	/**
	 * @return the physicalResistanceSet
	 */
	public HashSet<String> getPhysicalResistanceSet() {
		return physicalResistanceSet;
	}

	/**
	 * @return the bluntResistanceSet
	 */
	public HashSet<String> getBluntResistanceSet() {
		return bluntResistanceSet;
	}

	/**
	 * @return the pierceResistanceSet
	 */
	public HashSet<String> getPierceResistanceSet() {
		return pierceResistanceSet;
	}

	/**
	 * @return the magicalResistanceSet
	 */
	public HashSet<String> getMagicalResistanceSet() {
		return magicalResistanceSet;
	}

	/**
	 * @return the speedSet
	 */
	public HashSet<String> getSpeedSet() {
		return speedSet;
	}

	/**
	 * @return the healthSet
	 */
	public HashSet<String> getHealthSet() {
		return healthSet;
	}

	/**
	 * @return the hungerSet
	 */
	public HashSet<String> getHungerSet() {
		return hungerSet;
	}

	/**
	 * @return the thirstSet
	 */
	public HashSet<String> getThirstSet() {
		return thirstSet;
	}

	/**
	 * @return the staminaSet
	 */
	public HashSet<String> getStaminaSet() {
		return staminaSet;
	}

	/**
	 * @return the thaumaSet
	 */
	public HashSet<String> getThaumaSet() {
		return thaumaSet;
	}

	/**
	 * @return the breathSet
	 */
	public HashSet<String> getBreathSet() {
		return breathSet;
	}

}