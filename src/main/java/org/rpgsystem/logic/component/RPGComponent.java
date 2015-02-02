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

import java.util.ArrayList;
import java.util.List;

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
	
	private ArrayList<Modifier> rangeList;
	private ArrayList<Modifier> actionSpeedList;
	
	private ArrayList<Modifier> strengthList;
	private ArrayList<Modifier> agilityList;
	private ArrayList<Modifier> constitutionList;
	private ArrayList<Modifier> intelligenceList;
	private ArrayList<Modifier> wisdomList;
	private ArrayList<Modifier> thaumaticyList;
	private ArrayList<Modifier> charismaList;
	private ArrayList<Modifier> luckList;

	private ArrayList<Modifier> physicalAttackSuccessList;
	private ArrayList<Modifier> magicalAttackSuccessList;
	private ArrayList<Modifier> magicalAttackSuccessVarianceList;
	private ArrayList<Modifier> physicalAttackSuccessVarianceList;
	private ArrayList<Modifier> criticalPercentageList;
	private ArrayList<Modifier> criticalRatioList;
	
	private ArrayList<Modifier> physicalDefenseSuccessList;
	private ArrayList<Modifier> magicalDefenseSuccessList;
	
	private ArrayList<Modifier> dmgList;
	private ArrayList<Modifier> bluntDmgList;
	private ArrayList<Modifier> pierceDmgList;
	private ArrayList<Modifier> magicalDmgList;
	
	private ArrayList<Modifier> dmgVarianceList;
	private ArrayList<Modifier> bluntDmgVarianceList;
	private ArrayList<Modifier> pierceDmgVarianceList;
	private ArrayList<Modifier> magicalDmgVarianceList;
	
	private ArrayList<Modifier> physicalResistanceList;
	private ArrayList<Modifier> bluntResistanceList;
	private ArrayList<Modifier> pierceResistanceList;
	private ArrayList<Modifier> magicalResistanceList;
	
	private ArrayList<Modifier> speedList;
	private ArrayList<Modifier> healthList;
	private ArrayList<Modifier> hungerList;
	private ArrayList<Modifier> thirstList;
	private ArrayList<Modifier> staminaList;
	private ArrayList<Modifier> thaumaList;
	private ArrayList<Modifier> breathList;
	
	/**
	 * 
	 */
	public RPGComponent(){
		this.strengthList=new ArrayList<Modifier>();
		this.agilityList=new ArrayList<Modifier>();
		this.constitutionList=new ArrayList<Modifier>();
		this.intelligenceList=new ArrayList<Modifier>();
		this.wisdomList=new ArrayList<Modifier>();
		this.thaumaticyList=new ArrayList<Modifier>();
		this.charismaList=new ArrayList<Modifier>();
		this.luckList=new ArrayList<Modifier>();
		
		this.strengthList.add(new Modifier("baseStrength",this.baseStrength));
		this.agilityList.add(new Modifier("baseAgility",this.baseAgility));
		this.constitutionList.add(new Modifier("baseConstitution",this.baseConstitution));
		this.intelligenceList.add(new Modifier("baseIntelligence",this.baseIntelligence));
		this.wisdomList.add(new Modifier("baseWisdom",this.baseWisdom));
		this.thaumaticyList.add(new Modifier("baseThaumaticy",this.baseThaumaticy));
		this.charismaList.add(new Modifier("baseCharisma",this.baseCharisma));
		this.luckList.add(new Modifier("baseLuck",this.baseLuck));
		
		this.rangeList=new ArrayList<Modifier>();
		this.actionSpeedList=new ArrayList<Modifier>();
		
		this.rangeList.add(new Modifier("baseRange",this.baseRange));
		this.actionSpeedList.add(new Modifier("baseActionSpeed",this.baseActionSpeed));
		
		this.physicalAttackSuccessList=new ArrayList<Modifier>();
		this.magicalAttackSuccessList=new ArrayList<Modifier>();
		this.magicalAttackSuccessVarianceList=new ArrayList<Modifier>();
		this.physicalAttackSuccessVarianceList=new ArrayList<Modifier>();
		this.criticalPercentageList=new ArrayList<Modifier>();
		this.criticalRatioList=new ArrayList<Modifier>();
		
		this.criticalPercentageList.add(new Modifier("baseCriticalPercentage",this.baseCriticalPercentage));
		this.criticalRatioList.add(new Modifier("baseCriticalRatio",this.baseCriticalRatio));
		
		this.physicalDefenseSuccessList=new ArrayList<Modifier>();
		this.magicalDefenseSuccessList=new ArrayList<Modifier>();
		
		this.dmgList=new ArrayList<Modifier>();
		this.bluntDmgList=new ArrayList<Modifier>();
		this.pierceDmgList=new ArrayList<Modifier>();
		this.magicalDmgList=new ArrayList<Modifier>();
		
		this.dmgVarianceList=new ArrayList<Modifier>();
		this.bluntDmgVarianceList=new ArrayList<Modifier>();
		this.pierceDmgVarianceList=new ArrayList<Modifier>();
		this.magicalDmgVarianceList=new ArrayList<Modifier>();
		
		this.physicalResistanceList=new ArrayList<Modifier>();
		this.bluntResistanceList=new ArrayList<Modifier>();
		this.pierceResistanceList=new ArrayList<Modifier>();
		this.magicalResistanceList=new ArrayList<Modifier>();
		
		this.speedList=new ArrayList<Modifier>();
		this.healthList=new ArrayList<Modifier>();
		this.hungerList=new ArrayList<Modifier>();
		this.thirstList=new ArrayList<Modifier>();
		this.staminaList=new ArrayList<Modifier>();
		this.thaumaList=new ArrayList<Modifier>();
		this.breathList=new ArrayList<Modifier>();
		//this.=new ArrayList<Modifier>();
	}
	
	/**
	 * calculate sum of modifiers
	 * @param list
	 * @return
	 */
	private float calculateSum(List<Modifier> list){
		int i=0;
		float r=0;
		while(i<list.size()){
			r+=list.get(i).getValue();
			i++;
			
		}
		return r;
	}
	
	/**
	 * find modifier of given name
	 * @param list
	 * @param id
	 * @return
	 */
	private int find(List<Modifier> list,String id){
		int i=0;
		boolean found=false;
		while(i<list.size()){
			if(list.get(i).getId().matches(id)){
				found=true;
				break;
				}
			i++;
		}
		if(!found)
			i=-1;
		return i;
	}
	
	/**
	 * remove modifier in list
	 * @param list
	 * @param id
	 * @return
	 */
	private boolean remove(List<Modifier> list,String id){
		int i=find(list,id);
		if(i==-1){
			return false;
			}
		this.strengthList.remove(i);
		return true;
	}
	
	/**
	 * find and replace 
	 * @param list
	 * @param modifier
	 */
	private void replace(List<Modifier> list,Modifier modifier){
		int i=find(list,modifier.getId());
		if(i!=-1){
			list.remove(i);
			list.add(modifier);
		}else{
			list.add(modifier);
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
	 * add modifier from modifier list of given name
	 * @param modifier to be added/replaced to list
	 * @param name of list to be accessed
	 * @return return false if list doesn't exist
	 */
	public boolean addModifier(Modifier modifier,String name){
		boolean succes=false;
		if(name.compareToIgnoreCase("Range")==0){
			replace(this.rangeList,modifier);
			this.range=calculateSum(this.rangeList);
			succes=true;
			
		}else if(name.compareToIgnoreCase("actionSpeed")==0){
			replace(this.actionSpeedList,modifier);
			this.actionSpeed=calculateSum(this.actionSpeedList);
			succes=true;
			
		}else if(name.compareToIgnoreCase("strength")==0){
			replace(this.strengthList,modifier);
			this.strength=calculateSum(this.strengthList);
			strengthEffects();
			succes=true;
			
		}else if(name.compareToIgnoreCase("agility")==0){
			replace(this.agilityList,modifier);
			this.agility=calculateSum(this.agilityList);
			agilityEffects();
			succes=true;
			
		}else if(name.compareToIgnoreCase("constitution")==0){
			replace(this.constitutionList,modifier);
			this.constitution=calculateSum(this.constitutionList);
			constitutionEffects();
			succes=true;
			
		}else if(name.compareToIgnoreCase("intelligence")==0){
			replace(this.intelligenceList,modifier);
			this.intelligence=calculateSum(this.intelligenceList);
			intelligenceEffects();
			succes=true;
			
		}else if(name.compareToIgnoreCase("wisdom")==0){
			replace(this.wisdomList,modifier);
			this.wisdom=calculateSum(this.wisdomList);
			wisdomEffects();
			succes=true;
			
		}else if(name.compareToIgnoreCase("thaumaticy")==0){
			replace(this.thaumaticyList,modifier);
			this.thaumaticy=calculateSum(this.thaumaticyList);
			thaumaticyEffects();
			succes=true;
			
		}else if(name.compareToIgnoreCase("charisma")==0){
			replace(this.charismaList,modifier);
			this.charisma=calculateSum(this.charismaList);
			charismaEffects();
			succes=true;
			
		}else if(name.compareToIgnoreCase("luck")==0){
			replace(this.luckList,modifier);
			this.luck=calculateSum(this.luckList);
			luckEffects();
			succes=true;
			
		}else if(name.compareToIgnoreCase("physicalAttackSuccess")==0){
			replace(this.physicalAttackSuccessList,modifier);
			this.physicalAttackSuccess=calculateSum(this.physicalAttackSuccessList);
			succes=true;
			
		}else if(name.compareToIgnoreCase("magicalAttackSuccess")==0){
			replace(this.magicalAttackSuccessList,modifier);
			this.magicalAttackSuccess=calculateSum(this.magicalAttackSuccessList);
			succes=true;
			
		}else if(name.compareToIgnoreCase("physicalAttackSuccessVariance")==0){
			replace(this.physicalAttackSuccessVarianceList,modifier);
			this.physicalAttackSuccessVariance=calculateSum(this.physicalAttackSuccessVarianceList);
			succes=true;
			
		}else if(name.compareToIgnoreCase("magicalAttackSuccessVariance")==0){
			replace(this.magicalAttackSuccessVarianceList,modifier);
			this.magicalAttackSuccessVariance=calculateSum(this.magicalAttackSuccessVarianceList);
			succes=true;
			
		}else if(name.compareToIgnoreCase("criticalPercentage")==0){
			replace(this.criticalPercentageList,modifier);
			this.criticalPercentage=calculateSum(this.criticalPercentageList);
			succes=true;
			
		}else if(name.compareToIgnoreCase("criticalRatio")==0){
			replace(this.criticalRatioList,modifier);
			this.criticalRatio=calculateSum(this.criticalRatioList);
			succes=true;
			
		}else if(name.compareToIgnoreCase("physicalDefenseSuccess")==0){
			replace(this.physicalDefenseSuccessList,modifier);
			this.physicalDefenseSuccess=calculateSum(this.physicalDefenseSuccessList);
			succes=true;
						
		}else if(name.compareToIgnoreCase("magicalDefenseSuccess")==0){
			replace(this.magicalDefenseSuccessList,modifier);
			this.magicalDefenseSuccess=calculateSum(this.magicalDefenseSuccessList);
			succes=true;
						
		}else if(name.compareToIgnoreCase("dmg")==0){
			replace(this.dmgList,modifier);
			this.dmg=calculateSum(this.dmgList);
			succes=true;
						
		}else if(name.compareToIgnoreCase("bluntDmg")==0){
			replace(this.bluntDmgList,modifier);
			this.bluntDmg=calculateSum(this.bluntDmgList);
			succes=true;
						
		}else if(name.compareToIgnoreCase("pierceDmg")==0){
			replace(this.pierceDmgList,modifier);
			this.pierceDmg=calculateSum(this.pierceDmgList);
			succes=true;
						
		}else if(name.compareToIgnoreCase("magicalDmg")==0){
			replace(this.magicalDmgList,modifier);
			this.magicalDmg=calculateSum(this.magicalDmgList);
			succes=true;
						
		}else if(name.compareToIgnoreCase("dmgVariance")==0){
			replace(this.dmgVarianceList,modifier);
			this.dmgVariance=calculateSum(this.dmgVarianceList);
			succes=true;
						
		}else if(name.compareToIgnoreCase("bluntDmgVariance")==0){
			replace(this.bluntDmgVarianceList,modifier);
			this.bluntDmgVariance=calculateSum(this.bluntDmgVarianceList);
			succes=true;
						
		}else if(name.compareToIgnoreCase("pierceDmgVariance")==0){
			replace(this.pierceDmgVarianceList,modifier);
			this.pierceDmgVariance=calculateSum(this.pierceDmgVarianceList);
			succes=true;
						
		}else if(name.compareToIgnoreCase("magicalDmgVariance")==0){
			replace(this.magicalDmgVarianceList,modifier);
			this.magicalDmgVariance=calculateSum(this.magicalDmgVarianceList);
			succes=true;
						
		}else if(name.compareToIgnoreCase("physicalResistance")==0){
			replace(this.physicalResistanceList,modifier);
			this.physicalResistance=calculateSum(this.physicalResistanceList);
			succes=true;
						
		}else if(name.compareToIgnoreCase("bluntResistance")==0){
			replace(this.bluntResistanceList,modifier);
			this.bluntResistance=calculateSum(this.bluntResistanceList);
			succes=true;
						
		}else if(name.compareToIgnoreCase("pierceResistance")==0){
			replace(this.pierceResistanceList,modifier);
			this.pierceResistance=calculateSum(this.pierceResistanceList);
			succes=true;
						
		}else if(name.compareToIgnoreCase("magicalResistance")==0){
			replace(this.magicalResistanceList,modifier);
			this.magicalResistance=calculateSum(this.magicalResistanceList);
			succes=true;
						
		}else if(name.compareToIgnoreCase("speed")==0){
			replace(this.speedList,modifier);
			succes=true;
					
		}else if(name.compareToIgnoreCase("health")==0){
			replace(this.healthList,modifier);
			succes=true;
						
		}else if(name.compareToIgnoreCase("hunger")==0){
			replace(this.hungerList,modifier);
			succes=true;
					
		}else if(name.compareToIgnoreCase("thirst")==0){
			replace(this.thirstList,modifier);
			succes=true;
						
		}else if(name.compareToIgnoreCase("stamina")==0){
			replace(this.staminaList,modifier);
			succes=true;
						
		}else if(name.compareToIgnoreCase("thauma")==0){
			replace(this.thaumaList,modifier);
			succes=true;
						
		}else if(name.compareToIgnoreCase("List")==0){
			replace(this.breathList,modifier);
			succes=true;
						
		}
		return succes;
	}
	
	/**
	 * remove modifier from modifier list of given name
	 * @param id of modifier to be removed from list
	 * @param name of list to be accessed
	 * @return false if modifier do not exit on given list
	 */
	public boolean removeModifier(String id,String name){
		boolean result=false;
		if(name.compareToIgnoreCase("Range")==0){
			result=remove(this.rangeList,id);
			this.range=calculateSum(this.rangeList);
			
		}else if(name.compareToIgnoreCase("actionSpeed")==0){
			result=remove(this.actionSpeedList,id);
			this.actionSpeed=calculateSum(this.actionSpeedList);
			
		}else if(name.compareToIgnoreCase("strength")==0){
			result=remove(this.strengthList,id);
			this.strength=calculateSum(this.strengthList);
			strengthEffects();
			
		}else if(name.compareToIgnoreCase("agility")==0){
			result=remove(this.agilityList,id);
			this.agility=calculateSum(this.agilityList);
			agilityEffects();
			
		}else if(name.compareToIgnoreCase("constitution")==0){
			result=remove(this.constitutionList,id);
			this.constitution=calculateSum(this.constitutionList);
			constitutionEffects();
			
		}else if(name.compareToIgnoreCase("intelligence")==0){
			result=remove(this.intelligenceList,id);
			this.intelligence=calculateSum(this.intelligenceList);
			intelligenceEffects();
			
		}else if(name.compareToIgnoreCase("wisdom")==0){
			result=remove(this.wisdomList,id);
			this.wisdom=calculateSum(this.wisdomList);
			wisdomEffects();
			
		}else if(name.compareToIgnoreCase("thaumaticy")==0){
			result=remove(this.thaumaticyList,id);
			this.thaumaticy=calculateSum(this.thaumaticyList);
			thaumaticyEffects();
			
		}else if(name.compareToIgnoreCase("charisma")==0){
			result=remove(this.charismaList,id);
			this.charisma=calculateSum(this.charismaList);
			charismaEffects();
			
		}else if(name.compareToIgnoreCase("luck")==0){
			result=remove(this.luckList,id);
			this.luck=calculateSum(this.luckList);
			luckEffects();
			
		}else if(name.compareToIgnoreCase("physicalAttackSuccess")==0){
			result=remove(this.physicalAttackSuccessList,id);
			this.physicalAttackSuccess=calculateSum(this.physicalAttackSuccessList);
			
		}else if(name.compareToIgnoreCase("magicalAttackSuccess")==0){
			result=remove(this.magicalAttackSuccessList,id);
			this.magicalAttackSuccess=calculateSum(this.magicalAttackSuccessList);
			
		}else if(name.compareToIgnoreCase("physicalAttackSuccessVariance")==0){
			result=remove(this.physicalAttackSuccessVarianceList,id);
			this.physicalAttackSuccessVariance=calculateSum(this.physicalAttackSuccessVarianceList);
			
		}else if(name.compareToIgnoreCase("magicalAttackSuccessVariance")==0){
			result=remove(this.magicalAttackSuccessVarianceList,id);
			this.magicalAttackSuccessVariance=calculateSum(this.magicalAttackSuccessVarianceList);
			
		}else if(name.compareToIgnoreCase("criticalPercentage")==0){
			result=remove(this.criticalPercentageList,id);
			this.criticalPercentage=calculateSum(this.criticalPercentageList);
			
		}else if(name.compareToIgnoreCase("criticalRatio")==0){
			result=remove(this.criticalRatioList,id);
			this.criticalRatio=calculateSum(this.criticalRatioList);
			
		}else if(name.compareToIgnoreCase("physicalDefenseSuccess")==0){
			result=remove(this.physicalDefenseSuccessList,id);
			this.physicalDefenseSuccess=calculateSum(this.physicalDefenseSuccessList);
						
		}else if(name.compareToIgnoreCase("magicalDefenseSuccess")==0){
			result=remove(this.magicalDefenseSuccessList,id);
			this.magicalDefenseSuccess=calculateSum(this.magicalDefenseSuccessList);
						
		}else if(name.compareToIgnoreCase("dmg")==0){
			result=remove(this.dmgList,id);
			this.dmg=calculateSum(this.dmgList);
						
		}else if(name.compareToIgnoreCase("bluntDmg")==0){
			result=remove(this.bluntDmgList,id);
			this.bluntDmg=calculateSum(this.bluntDmgList);
						
		}else if(name.compareToIgnoreCase("pierceDmg")==0){
			result=remove(this.pierceDmgList,id);
			this.pierceDmg=calculateSum(this.pierceDmgList);
						
		}else if(name.compareToIgnoreCase("magicalDmg")==0){
			result=remove(this.magicalDmgList,id);
			this.magicalDmg=calculateSum(this.magicalDmgList);
						
		}else if(name.compareToIgnoreCase("dmgVariance")==0){
			result=remove(this.dmgVarianceList,id);
			this.dmgVariance=calculateSum(this.dmgVarianceList);
						
		}else if(name.compareToIgnoreCase("bluntDmgVariance")==0){
			result=remove(this.bluntDmgVarianceList,id);
			this.bluntDmgVariance=calculateSum(this.bluntDmgVarianceList);
						
		}else if(name.compareToIgnoreCase("pierceDmgVariance")==0){
			result=remove(this.pierceDmgVarianceList,id);
			this.pierceDmgVariance=calculateSum(this.pierceDmgVarianceList);
						
		}else if(name.compareToIgnoreCase("magicalDmgVariance")==0){
			result=remove(this.magicalDmgVarianceList,id);
			this.magicalDmgVariance=calculateSum(this.magicalDmgVarianceList);
						
		}else if(name.compareToIgnoreCase("physicalResistance")==0){
			result=remove(this.physicalResistanceList,id);
			this.physicalResistance=calculateSum(this.physicalResistanceList);
						
		}else if(name.compareToIgnoreCase("bluntResistance")==0){
			result=remove(this.bluntResistanceList,id);
			this.bluntResistance=calculateSum(this.bluntResistanceList);
						
		}else if(name.compareToIgnoreCase("pierceResistance")==0){
			result=remove(this.pierceResistanceList,id);
			this.pierceResistance=calculateSum(this.pierceResistanceList);
						
		}else if(name.compareToIgnoreCase("magicalResistance")==0){
			result=remove(this.magicalResistanceList,id);
			this.magicalResistance=calculateSum(this.magicalResistanceList);
						
		}else if(name.compareToIgnoreCase("speed")==0){
			result=remove(this.speedList,id);
						
		}else if(name.compareToIgnoreCase("health")==0){
			result=remove(this.healthList,id);
						
		}else if(name.compareToIgnoreCase("hunger")==0){
			result=remove(this.hungerList,id);
						
		}else if(name.compareToIgnoreCase("thirst")==0){
			result=remove(this.thirstList,id);
						
		}else if(name.compareToIgnoreCase("stamina")==0){
			result=remove(this.staminaList,id);
						
		}else if(name.compareToIgnoreCase("thauma")==0){
			result=remove(this.thaumaList,id);
						
		}else if(name.compareToIgnoreCase("List")==0){
			result=remove(this.breathList,id);			
		}

		return result;
	}
	
	/**
	 * remove modifier from modifier list of given name
	 * @param id of modifier to be removed from list
	 * @param name of list to be accessed
	 * @return false if modifier do not exit on given list
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
		replace(this.rangeList,new Modifier("baseRange",this.baseRange));
	}

	/**
	 * @param baseStrength the baseStrength to set
	 */
	public final void setBaseStrength(float baseStrength) {
		this.baseStrength = baseStrength;
		replace(this.strengthList,new Modifier("baseStrength",this.baseStrength));
	}

	/**
	 * @param baseAgility the baseAgility to set
	 */
	public final void setBaseAgility(float baseAgility) {
		this.baseAgility = baseAgility;
		replace(this.agilityList,new Modifier("baseAgility",this.baseAgility));
	}

	/**
	 * @param baseConstitution the baseConstitution to set
	 */
	public final void setBaseConstitution(float baseConstitution) {
		this.baseConstitution = baseConstitution;
		replace(this.constitutionList,new Modifier("baseConstitution",this.baseConstitution));
	}

	/**
	 * @param baseIntelligence the baseIntelligence to set
	 */
	public final void setBaseIntelligence(float baseIntelligence) {
		this.baseIntelligence = baseIntelligence;
		replace(this.intelligenceList,new Modifier("baseIntelligence",this.baseIntelligence));
	}

	/**
	 * @param baseWisdom the baseWisdom to set
	 */
	public final void setBaseWisdom(float baseWisdom) {
		this.baseWisdom = baseWisdom;
		replace(this.wisdomList,new Modifier("baseWisdom",this.baseWisdom));
	}

	/**
	 * @param baseThaumaticy the baseThaumaticy to set
	 */
	public final void setBaseThaumaticy(float baseThaumaticy) {
		this.baseThaumaticy = baseThaumaticy;
		replace(this.thaumaticyList,new Modifier("baseThaumaticy",this.baseThaumaticy));
	}

	/**
	 * @param baseCharisma the baseCharisma to set
	 */
	public final void setBaseCharisma(float baseCharisma) {
		this.baseCharisma = baseCharisma;
		replace(this.charismaList,new Modifier("baseCharisma",this.baseCharisma));
	}


	/**
	 * @param baseLuck the baseLuck to set
	 */
	public final void setBaseLuck(float baseLuck) {
		this.baseLuck = baseLuck;
		replace(this.luckList,new Modifier("baseLuck",this.baseLuck));
	}


	/**
	 * @param baseCriticalPercentage the baseCriticalPercentage to set
	 */
	public final void setBaseCriticalPercentage(float baseCriticalPercentage) {
		this.baseCriticalPercentage = baseCriticalPercentage;
		replace(this.criticalPercentageList,new Modifier("baseCriticalPercentage",this.baseCriticalPercentage));
	}

	/**
	 * @param baseCriticalRatio the baseCriticalRatio to set
	 */
	public final void setBaseCriticalRatio(float baseCriticalRatio) {
		this.baseCriticalRatio = baseCriticalRatio;
		replace(this.criticalRatioList,new Modifier("baseCriticalRatio",this.baseCriticalRatio));
	}

	/**
	 * @param baseActionSpeed the baseActionSpeed to set
	 */
	public void setBaseActionSpeed(float baseActionSpeed) {
		this.baseActionSpeed = baseActionSpeed;
		replace(this.actionSpeedList ,new Modifier("baseActionSpeed",this.baseActionSpeed));
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
	public final float getRange() {
		return range;
	}

	/**
	 * @return the actionSpeed
	 */
	public final float getActionSpeed() {
		return actionSpeed;
	}

	/**
	 * @return the baseStrength
	 */
	public final float getBaseStrength() {
		return baseStrength;
	}

	/**
	 * @return the baseAgility
	 */
	public final float getBaseAgility() {
		return baseAgility;
	}

	/**
	 * @return the baseConstitution
	 */
	public final float getBaseConstitution() {
		return baseConstitution;
	}

	/**
	 * @return the baseIntelligence
	 */
	public final float getBaseIntelligence() {
		return baseIntelligence;
	}

	/**
	 * @return the baseWisdom
	 */
	public final float getBaseWisdom() {
		return baseWisdom;
	}

	/**
	 * @return the baseThaumaticy
	 */
	public final float getBaseThaumaticy() {
		return baseThaumaticy;
	}

	/**
	 * @return the baseCharisma
	 */
	public final float getBaseCharisma() {
		return baseCharisma;
	}

	/**
	 * @return the baseLuck
	 */
	public final float getBaseLuck() {
		return baseLuck;
	}

	/**
	 * @return the agility
	 */
	public final float getAgility() {
		return agility;
	}

	/**
	 * @return the strength
	 */
	public final float getStrength() {
		return strength;
	}

	/**
	 * @return the constitution
	 */
	public final float getConstitution() {
		return constitution;
	}

	/**
	 * @return the intelligence
	 */
	public final float getIntelligence() {
		return intelligence;
	}

	/**
	 * @return the wisdom
	 */
	public final float getWisdom() {
		return wisdom;
	}

	/**
	 * @return the thaumaticy
	 */
	public final float getThaumaticy() {
		return thaumaticy;
	}

	/**
	 * @return the charisma
	 */
	public final float getCharisma() {
		return charisma;
	}

	/**
	 * @return the luck
	 */
	public final float getLuck() {
		return luck;
	}

	/**
	 * @return the physicalAttackSuccess
	 */
	public final float getPhysicalAttackSuccess() {
		return physicalAttackSuccess;
	}

	/**
	 * @return the magicalAttackSuccess
	 */
	public final float getMagicalAttackSuccess() {
		return magicalAttackSuccess;
	}

	/**
	 * @return the magicalAttackSuccessVariance
	 */
	public final float getMagicalAttackSuccessVariance() {
		return magicalAttackSuccessVariance;
	}

	/**
	 * @return the physicalAttackSuccessVariance
	 */
	public final float getPhysicalAttackSuccessVariance() {
		return physicalAttackSuccessVariance;
	}

	/**
	 * @return the baseCriticalPercentage
	 */
	public final float getBaseCriticalPercentage() {
		return baseCriticalPercentage;
	}

	/**
	 * @return the criticalPercentage
	 */
	public final float getCriticalPercentage() {
		return criticalPercentage;
	}

	/**
	 * @return the baseCriticalRatio
	 */
	public final float getBaseCriticalRatio() {
		return baseCriticalRatio;
	}

	/**
	 * @return the criticalRatio
	 */
	public final float getCriticalRatio() {
		return criticalRatio;
	}

	/**
	 * @return the physicalDefenseSuccess
	 */
	public final float getPhysicalDefenseSuccess() {
		return physicalDefenseSuccess;
	}

	/**
	 * @return the magicalDefenseSuccess
	 */
	public final float getMagicalDefenseSuccess() {
		return magicalDefenseSuccess;
	}

	/**
	 * @return the dmg
	 */
	public final float getDmg() {
		return dmg;
	}

	/**
	 * @return the bluntDmg
	 */
	public final float getBluntDmg() {
		return bluntDmg;
	}

	/**
	 * @return the pierceDmg
	 */
	public final float getPierceDmg() {
		return pierceDmg;
	}

	/**
	 * @return the magicalDmg
	 */
	public final float getMagicalDmg() {
		return magicalDmg;
	}

	/**
	 * @return the dmgVariance
	 */
	public final float getDmgVariance() {
		return dmgVariance;
	}

	/**
	 * @return the bluntDmgVariance
	 */
	public final float getBluntDmgVariance() {
		return bluntDmgVariance;
	}

	/**
	 * @return the pierceDmgVariance
	 */
	public final float getPierceDmgVariance() {
		return pierceDmgVariance;
	}

	/**
	 * @return the magicalDmgVariance
	 */
	public final float getMagicalDmgVariance() {
		return magicalDmgVariance;
	}

	/**
	 * @return the physicalResistance
	 */
	public final float getPhysicalResistance() {
		return physicalResistance;
	}

	/**
	 * @return the bluntResistance
	 */
	public final float getBluntResistance() {
		return bluntResistance;
	}

	/**
	 * @return the pierceResistance
	 */
	public final float getPierceResistance() {
		return pierceResistance;
	}

	/**
	 * @return the magicalResistance
	 */
	public final float getMagicalResistance() {
		return magicalResistance;
	}

	/**
	 * @return the rangeList
	 */
	public final List<Modifier> getRangeList() {
		return rangeList;
	}

	/**
	 * @return the actionSpeedList
	 */
	public final List<Modifier> getActionSpeedList() {
		return actionSpeedList;
	}

	/**
	 * @return the strengthList
	 */
	public final List<Modifier> getStrengthList() {
		return strengthList;
	}

	/**
	 * @return the agilityList
	 */
	public final List<Modifier> getAgilityList() {
		return agilityList;
	}

	/**
	 * @return the constitutionList
	 */
	public final List<Modifier> getConstitutionList() {
		return constitutionList;
	}

	/**
	 * @return the intelligenceList
	 */
	public final List<Modifier> getIntelligenceList() {
		return intelligenceList;
	}

	/**
	 * @return the wisdomList
	 */
	public final List<Modifier> getWisdomList() {
		return wisdomList;
	}

	/**
	 * @return the thaumaticyList
	 */
	public final List<Modifier> getThaumaticyList() {
		return thaumaticyList;
	}

	/**
	 * @return the charismaList
	 */
	public final List<Modifier> getCharismaList() {
		return charismaList;
	}

	/**
	 * @return the luckList
	 */
	public final List<Modifier> getLuckList() {
		return luckList;
	}

	/**
	 * @return the physicalAttackSuccessList
	 */
	public final List<Modifier> getPhysicalAttackSuccessList() {
		return physicalAttackSuccessList;
	}

	/**
	 * @return the magicalAttackSuccessList
	 */
	public final List<Modifier> getMagicalAttackSuccessList() {
		return magicalAttackSuccessList;
	}

	/**
	 * @return the magicalAttackSuccessVarianceList
	 */
	public final List<Modifier> getMagicalAttackSuccessVarianceList() {
		return magicalAttackSuccessVarianceList;
	}

	/**
	 * @return the physicalAttackSuccessVarianceList
	 */
	public final List<Modifier> getPhysicalAttackSuccessVarianceList() {
		return physicalAttackSuccessVarianceList;
	}

	/**
	 * @return the criticalPercentageList
	 */
	public final List<Modifier> getCriticalPercentageList() {
		return criticalPercentageList;
	}

	/**
	 * @return the criticalRatioList
	 */
	public final List<Modifier> getCriticalRatioList() {
		return criticalRatioList;
	}

	/**
	 * @return the physicalDefenseSuccessList
	 */
	public final List<Modifier> getPhysicalDefenseSuccessList() {
		return physicalDefenseSuccessList;
	}

	/**
	 * @return the magicalDefenseSuccessList
	 */
	public final List<Modifier> getMagicalDefenseSuccessList() {
		return magicalDefenseSuccessList;
	}

	/**
	 * @return the dmgList
	 */
	public final List<Modifier> getDmgList() {
		return dmgList;
	}

	/**
	 * @return the bluntDmgList
	 */
	public final List<Modifier> getBluntDmgList() {
		return bluntDmgList;
	}

	/**
	 * @return the pierceDmgList
	 */
	public final List<Modifier> getPierceDmgList() {
		return pierceDmgList;
	}

	/**
	 * @return the magicalDmgList
	 */
	public final List<Modifier> getMagicalDmgList() {
		return magicalDmgList;
	}

	/**
	 * @return the dmgVarianceList
	 */
	public final List<Modifier> getDmgVarianceList() {
		return dmgVarianceList;
	}

	/**
	 * @return the bluntDmgVarianceList
	 */
	public final List<Modifier> getBluntDmgVarianceList() {
		return bluntDmgVarianceList;
	}

	/**
	 * @return the pierceDmgVarianceList
	 */
	public final List<Modifier> getPierceDmgVarianceList() {
		return pierceDmgVarianceList;
	}

	/**
	 * @return the magicalDmgVarianceList
	 */
	public final List<Modifier> getMagicalDmgVarianceList() {
		return magicalDmgVarianceList;
	}

	/**
	 * @return the physicalResistanceList
	 */
	public final List<Modifier> getPhysicalResistanceList() {
		return physicalResistanceList;
	}

	/**
	 * @return the bluntResistanceList
	 */
	public final List<Modifier> getBluntResistanceList() {
		return bluntResistanceList;
	}

	/**
	 * @return the pierceResistanceList
	 */
	public final List<Modifier> getPierceResistanceList() {
		return pierceResistanceList;
	}

	/**
	 * @return the magicalResistanceList
	 */
	public final List<Modifier> getMagicalResistanceList() {
		return magicalResistanceList;
	}

	/**
	 * @return the speedList
	 */
	public final List<Modifier> getSpeedList() {
		return speedList;
	}

	/**
	 * @return the healthList
	 */
	public final List<Modifier> getHealthList() {
		return healthList;
	}

	/**
	 * @return the hungerList
	 */
	public final List<Modifier> getHungerList() {
		return hungerList;
	}

	/**
	 * @return the thirstList
	 */
	public final List<Modifier> getThirstList() {
		return thirstList;
	}

	/**
	 * @return the staminaList
	 */
	public final List<Modifier> getStaminaList() {
		return staminaList;
	}

	/**
	 * @return the thaumaList
	 */
	public final List<Modifier> getThaumaList() {
		return thaumaList;
	}

	/**
	 * @return the breathList
	 */
	public final List<Modifier> getBreathList() {
		return breathList;
	}

	/**
	 * @return the baseActionSpeed
	 */
	public final float getBaseActionSpeed() {
		return baseActionSpeed;
	}

}