/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rpgsystem.logic;

import org.rpgsystem.logic.component.HungerComponent;
import org.rpgsystem.logic.component.RPGComponent;
import org.rpgsystem.logic.component.StaminaComponent;
import org.rpgsystem.logic.component.ThaumaComponent;
import org.rpgsystem.logic.component.ThirstComponent;
import org.terasology.engine.Time;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.logic.health.EngineDamageTypes;
import org.terasology.logic.health.HealthComponent;
import org.terasology.logic.health.OnDamagedEvent;
import org.terasology.registry.CoreRegistry;

/**
 * 
 * @author orginal Marcin Sciesinski <marcins78@gmail.com>
 * @author esereja <esereja@yahoo.co.uk>
 */
public final class SHTUtils {
	
    private SHTUtils() {}

    /**
     * get current stamina and update it
     * @param entity
     * @return
     */
    public static float getStaminaForEntity(EntityRef entity) {
        StaminaComponent stamina = entity.getComponent(StaminaComponent.class);
        if (stamina == null) {
            return 0;
        }

        long gameTime = CoreRegistry.get(Time.class).getGameTimeInMs();
        
        float reGen = 0;
        float seconds=(gameTime - stamina.lastCalculationTime) / 1000f;

    	int s=0;
    	float currentStamina=stamina.lastStamina;
    	while(s<seconds){
    		if(currentStamina<stamina.decreasedReGenThreshold){
    			stamina.decreaseTimeLeft+=stamina.ThresholdPunishmentPerSec;
    		}
    		if(stamina.decreaseTimeLeft>0){
    			stamina.decreaseTimeLeft-=1000;
    			reGen+=stamina.reGenPerSecond*stamina.decreasedReGenRatio;
    		}else{
    			reGen+=stamina.reGenPerSecond;
    		}
    		if(stamina.isRunning){
    			reGen-=stamina.sprintStaminaPerSec;
    		}
    		s++;
    	}
		addHungerForEntity(entity,seconds*stamina.hungerPerReGenRatio);
		addThirstForEntity(entity,seconds*stamina.thirstPerReGenRatio);
		stamina.lastCalculationTime=gameTime;
		stamina.lastStamina=Math.min(Math.max(stamina.minStamina, stamina.lastStamina + reGen),stamina.maxStamina);
		entity.saveComponent(stamina);

        return stamina.lastStamina;
    }
    
    /**
     * add value to stamina
     * @param entity
     * @param ammount
     * @return
     */
    public static float addStaminaForEntity(EntityRef entity,float ammount) {
        StaminaComponent stamina = entity.getComponent(StaminaComponent.class);
        if (stamina == null) {
            return 0;
        }
        stamina.lastStamina=Math.min(Math.max(stamina.minStamina, stamina.lastStamina + ammount),stamina.maxStamina);
        entity.saveComponent(stamina);
        
        return  stamina.lastStamina;
    }
    
    /**
     * calculate current thauma and update it in progress
     * @param entity
     * @return
     */
    public static float getThaumaForEntity(EntityRef entity) {
        ThaumaComponent thauma = entity.getComponent(ThaumaComponent.class);
        if (thauma == null) {
            return 0;
        }

        long gameTime = CoreRegistry.get(Time.class).getGameTimeInMs();
        float result=thauma.lastCalculatedThauma+thauma.regenPerSecond * (gameTime - thauma.lastCalculationTime) / 1000f;
         
        if(result<thauma.staminaLossThreshold){
            StaminaComponent stamina = entity.getComponent(StaminaComponent.class);
            if (stamina == null) {
                return 0;
            }
            //update stamina
            getStaminaForEntity(entity);
            
            HealthComponent health = entity.getComponent(HealthComponent.class);
            if (health == null) {
                return 0;
            }
            
            float s=0;
            float end=(gameTime - thauma.lastCalculationTime) / 1000f;
            float currentHealth=health.currentHealth;
        	while(s<end && result<thauma.staminaLossThreshold){
        		if(stamina.lastStamina-stamina.minStamina>thauma.exhangePerSecond){
        			addStaminaForEntity(entity,-thauma.exhangePerSecond);
        			result+=thauma.exhangePerSecond*thauma.staminaToThaumaRate;
        		}else{
        			currentHealth-=thauma.exhangePerSecond;
        			result+=thauma.exhangePerSecond*thauma.healthToThaumaRate;
        		}
        		s++;
        	}
       
        	if(currentHealth!=health.currentHealth){
        		health.currentHealth=(int) currentHealth;
        		entity.send(
        				new OnDamagedEvent(
        						(int)(currentHealth-health.currentHealth), (int) (currentHealth-health.currentHealth), EngineDamageTypes.HEALING.get(), entity));
        	}

        	
        	entity.saveComponent(stamina);
        	entity.saveComponent(health);        	
        }
       
        thauma.lastCalculatedThauma=gameTime;
        thauma.lastCalculatedThauma=Math.max(Math.min(result, thauma.maxThaumaCapacity),thauma.minThaumaCapacity);
        entity.saveComponent(thauma);
        return  result;
    }
    
    /**
     * add value to thauma value
     * @param entity
     * @param ammount
     * @return
     */
    public static float addThaumaForEntity(EntityRef entity,float ammount) {
        ThaumaComponent thauma = entity.getComponent(ThaumaComponent.class);
        if (thauma == null) {
            return 0;
        }
        
        thauma.lastCalculatedThauma=Math.min(Math.max(thauma.minThaumaCapacity, thauma.lastCalculatedThauma + ammount),thauma.maxThaumaCapacity);
        entity.saveComponent(thauma);
        
        return  thauma.lastCalculatedThauma;
    }
    
    /**
     * get current hunger and update it same time
     * @param entity
     * @return
     */
    public static float getHungerForEntity(EntityRef entity) {
        HungerComponent hunger = entity.getComponent(HungerComponent.class);
        if (hunger == null) {
            return 0;
        }
        long gameTime = CoreRegistry.get(Time.class).getGameTimeInMs();
        
        float decay = hunger.foodDecayPerSecond * (gameTime - hunger.lastCalculationTime) / 1000f;
        hunger.lastCalculatedFood=Math.min(hunger.lastCalculatedFood - decay,hunger.maxFoodCapacity);
        entity.saveComponent(hunger);
        return  hunger.lastCalculatedFood;
    }
    
    /**
     * add value to hunger value    
     * @param entity
     * @param ammount
     * @return
     */
    public static float addHungerForEntity(EntityRef entity,float ammount) {
        HungerComponent hunger = entity.getComponent(HungerComponent.class);
        if (hunger == null) {
            return 0;
        }
        hunger.lastCalculatedFood=Math.min(hunger.lastCalculatedFood - ammount,hunger.maxFoodCapacity);
        entity.saveComponent(hunger);
        return  hunger.lastCalculatedFood;
    }
    
    /**
     * get current thirst and update it.
     * @param entity
     * @return
     */
    public static float getThirstForEntity(EntityRef entity) {
        ThirstComponent thirst = entity.getComponent(ThirstComponent.class);
        if (thirst == null) {
            return 0;
        }

        long gameTime = CoreRegistry.get(Time.class).getGameTimeInMs();
        
        float decay = thirst.decayPerSecond * (gameTime - thirst.lastCalculationTime) / 1000f;
        thirst.lastCalculatedWater=Math.min(thirst.lastCalculatedWater - decay,thirst.maxWaterCapacity);
        entity.saveComponent(thirst);
        return  thirst.lastCalculatedWater;
    }
    
    /**
     * add value to thirst value.
     * @param entity
     * @param ammount
     * @return
     */
    public static float addThirstForEntity(EntityRef entity,float ammount) {
        ThirstComponent thirst = entity.getComponent(ThirstComponent.class);
        if (thirst == null) {
            return 0;
        }
        thirst.lastCalculatedWater=Math.min(thirst.lastCalculatedWater - ammount,thirst.maxWaterCapacity);
        entity.saveComponent(thirst);
        return  thirst.lastCalculatedWater;
    }
    
    public static float getPlayerLvl(EntityRef entity){
    	RPGComponent rpg = entity.getComponent(RPGComponent.class);
        if (rpg == null) {
            return 0;
        }
        float lvl=0;
        
        
        
        return lvl;
    }
    
    
}
