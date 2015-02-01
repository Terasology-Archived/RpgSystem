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
package org.rpgsystem.logic.authority;

import org.rpgsystem.logic.SHTUtils;
import org.rpgsystem.logic.component.StaminaComponent;
import org.terasology.engine.Time;
import org.terasology.entitySystem.entity.EntityManager;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.entity.lifecycleEvents.BeforeDeactivateComponent;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterMode;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.characters.CharacterMoveInputEvent;
import org.terasology.logic.characters.CharacterMovementComponent;
import org.terasology.logic.characters.MovementMode;
import org.terasology.logic.console.commandSystem.annotations.Command;
import org.terasology.logic.console.commandSystem.annotations.CommandParam;
import org.terasology.logic.players.event.OnPlayerSpawnedEvent;
import org.terasology.network.ClientComponent;
import org.terasology.registry.In;

/**
 * @author esereja <esereja@yahoo.co.uk>
 */

@RegisterSystem(RegisterMode.AUTHORITY)
public class StaminaAuthoritySystem extends BaseComponentSystem {

    @In
    private EntityManager entityManager;

    @In
    private Time time;

    /*@ReceiveEvent
    public void onHealthRegen(BeforeHealEvent event, EntityRef entity,
                              HungerComponent hunger) {
        if (event.getInstigator() == entity
                && HungerAndThirstUtils.getHungerForEntity(entity) < hunger.healthStopRegenThreshold) {
            event.consume();
        }
    }*/

    @ReceiveEvent
    public void onPlayerRespawn(OnPlayerSpawnedEvent event, EntityRef player,
                                StaminaComponent stamina) {
        stamina.lastStamina = stamina.maxStamina;
        stamina.lastCalculationTime = time.getGameTimeInMs();
        player.saveComponent(stamina);
    }

    @ReceiveEvent
    public void onPlayerFirstSpawn(OnPlayerSpawnedEvent event, EntityRef player) {
        if (!player.hasComponent(StaminaComponent.class)) {
        	StaminaComponent stamina = new StaminaComponent();
        	stamina.lastStamina = stamina.maxStamina;
        	stamina.lastCalculationTime = time.getGameTimeInMs();
            player.addComponent(stamina);
        }
    }

    @ReceiveEvent
    public void beforeRemoval(BeforeDeactivateComponent event, EntityRef entity, StaminaComponent hunger) {
        hunger.lastStamina = SHTUtils.getHungerForEntity(entity);
        hunger.lastCalculationTime = time.getGameTimeInMs();
        entity.saveComponent(hunger);
    }

    @Command(shortDescription = "Checks your stamina level.", runOnServer = true)
    public String staminaCheck(EntityRef client) {
        EntityRef character = client.getComponent(ClientComponent.class).character;
        if (character.hasComponent(StaminaComponent.class)) {
        	StaminaComponent stamina = character.getComponent(StaminaComponent.class);
            return "Current stamina Level(min/current/maX): " +stamina.minStamina+"/"+ SHTUtils.getStaminaForEntity(character) + "/" + stamina.maxStamina;
        } else {
            return "You don't have a stamina level.";
        }
    }

    @Command(shortDescription = "Sets your current hunger level.", runOnServer = true)
    public String staminaSet(@CommandParam(value = "currentStamina") float newFood, EntityRef client) {
        EntityRef character = client.getComponent(ClientComponent.class).character;
        if (!character.hasComponent(StaminaComponent.class)) {
            return "You don't have a stamina level.";
        }
        StaminaComponent stamina = character.getComponent(StaminaComponent.class);
        if (newFood < stamina.minStamina) {
            stamina.lastStamina = stamina.minStamina;
            stamina.lastCalculationTime = time.getGameTimeInMs();
            character.saveComponent(stamina);
            return "stamina level cannot be below min stamina Capacity"+stamina.minStamina+". Setting to "+stamina.minStamina+".";
        }
        if (newFood > stamina.maxStamina) {
            stamina.lastStamina = stamina.maxStamina;
            stamina.lastCalculationTime = time.getGameTimeInMs();
            character.saveComponent(stamina);
            return "stamina level cannot be above Max stamina Capacity. Setting to Max(" + stamina.maxStamina + ")";
        }
        stamina.lastStamina = newFood;
        stamina.lastCalculationTime = time.getGameTimeInMs();
        character.saveComponent(stamina);
        return "stamina level successfully set to: " + newFood;
    }

    @Command(shortDescription = "Sets your max stamina level.", runOnServer = true)
    public String staminaSetMax(@CommandParam(value = "MaxStamina") float newMax, EntityRef client) {
        EntityRef character = client.getComponent(ClientComponent.class).character;
        if (!character.hasComponent(StaminaComponent.class)) {
            return "You don't have a stamina level.";
        }
        StaminaComponent stamina = character.getComponent(StaminaComponent.class);
        if (newMax <= 0) {
            stamina.maxStamina = 100;
            character.saveComponent(stamina);
            return "Max Stamina Level cannot be below or equal to 0. Setting to default (100)";
        }
        stamina.maxStamina = newMax;
        character.saveComponent(stamina);
        return "Max Stamina Level successfully set to: " + newMax;
    }
    
    @ReceiveEvent
    public void characterMoved(CharacterMoveInputEvent event, EntityRef character, StaminaComponent stamina) {
        if (event.isRunning()) {
        	if(SHTUtils.getStaminaForEntity(character)< stamina.decreasedReGenThreshold){
                if (!character.hasComponent(CharacterMovementComponent.class)) {
                    return;
                }
                CharacterMovementComponent movement = character.getComponent(CharacterMovementComponent.class);
        		movement.mode=MovementMode.WALKING;
        		character.saveComponent(movement);
            	stamina.isRunning=false;
        	}else{
        		stamina.isRunning=true;
        	}
        }else{
        	stamina.isRunning=false;
        }
        	
        character.saveComponent(stamina);
    }
}
