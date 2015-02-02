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
import org.rpgsystem.logic.component.FoodComponent;
import org.rpgsystem.logic.component.HungerComponent;
import org.terasology.engine.Time;
import org.terasology.entitySystem.entity.EntityManager;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.entity.lifecycleEvents.BeforeDeactivateComponent;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterMode;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.entitySystem.systems.UpdateSubscriberSystem;
import org.terasology.logic.common.ActivateEvent;
import org.terasology.logic.console.commandSystem.annotations.Command;
import org.terasology.logic.console.commandSystem.annotations.CommandParam;
import org.terasology.logic.health.BeforeHealEvent;
import org.terasology.logic.health.DoDamageEvent;
import org.terasology.logic.players.event.OnPlayerSpawnedEvent;
import org.terasology.network.ClientComponent;
import org.terasology.registry.In;

/**
 * @author UltimateBudgie <TheUltimateBudgie@gmail.com>
 */

@RegisterSystem(RegisterMode.AUTHORITY)
public class HungerAuthoritySystem extends BaseComponentSystem implements UpdateSubscriberSystem {

	//private static final Logger logger = LoggerFactory.getLogger(HungerAuthoritySystem.class);
	
    @In
    private EntityManager entityManager;

    @In
    private Time time;

    public void update(float delta) {
        long gameTime = time.getGameTimeInMs();
        for (EntityRef entity : entityManager.getEntitiesWith(HungerComponent.class)) {
            HungerComponent hunger = entity.getComponent(HungerComponent.class);

            //Check to see if health should be decreased
            if (SHTUtils.getHungerForEntity(entity) < hunger.healthLossThreshold) {
                if (gameTime >= hunger.nextHealthDecreaseTick) {
                    entity.send(new DoDamageEvent(hunger.healthDecreaseAmount));
                    hunger.nextHealthDecreaseTick = gameTime + hunger.healthDecreaseInterval;
                }
            }
        }
    }

    @ReceiveEvent
    public void onHealthRegen(BeforeHealEvent event, EntityRef entity,
                              HungerComponent hunger) {
        if (event.getInstigator() == entity
                && SHTUtils.getHungerForEntity(entity) < hunger.healthStopRegenThreshold) {
            event.consume();
        }
    }

    @ReceiveEvent
    public void onPlayerRespawn(OnPlayerSpawnedEvent event, EntityRef player,
                                HungerComponent hunger) {
        hunger.lastCalculatedFood = hunger.maxFoodCapacity;
        hunger.lastCalculationTime = time.getGameTimeInMs();
        player.saveComponent(hunger);
    }

    @ReceiveEvent
    public void onPlayerFirstSpawn(OnPlayerSpawnedEvent event, EntityRef player) {
        if (!player.hasComponent(HungerComponent.class)) {
            HungerComponent hunger = new HungerComponent();
            hunger.lastCalculatedFood = hunger.maxFoodCapacity;
            hunger.lastCalculationTime = time.getGameTimeInMs();
            player.addComponent(hunger);
        }
    }

    @ReceiveEvent
    public void beforeRemoval(BeforeDeactivateComponent event, EntityRef entity, HungerComponent hunger) {
        hunger.lastCalculatedFood = SHTUtils.getHungerForEntity(entity);
        hunger.lastCalculationTime = time.getGameTimeInMs();
        entity.saveComponent(hunger);
    }

    @ReceiveEvent
    public void foodConsumed(ActivateEvent event, EntityRef item, FoodComponent food) {
        float filling = food.filling;
        EntityRef instigator = event.getInstigator();
        HungerComponent hunger = instigator.getComponent(HungerComponent.class);
        if (hunger != null) {
            hunger.lastCalculatedFood = Math.min(hunger.maxFoodCapacity, SHTUtils.getHungerForEntity(instigator) + filling);
            hunger.lastCalculationTime = time.getGameTimeInMs();
            instigator.saveComponent(hunger);
        }
    }


    @Command(shortDescription = "Checks your hunger/food level.", runOnServer = true)
    public String hungerCheck(EntityRef client) {
        EntityRef character = client.getComponent(ClientComponent.class).character;
        if(character ==null)
        	return "no character entity ref";
        if (character.hasComponent(HungerComponent.class)) {
            HungerComponent hunger = character.getComponent(HungerComponent.class);
            return "Current Food Level: " + SHTUtils.getHungerForEntity(character) + "/" + hunger.maxFoodCapacity;
        } else {
            return "You don't have a hunger level.";
        }
    }

    @Command(shortDescription = "Sets your current hunger level.", runOnServer = true)
    public String hungerSet(@CommandParam(value = "FoodLevel") float newFood, EntityRef client) {
        EntityRef character = client.getComponent(ClientComponent.class).character;
        if (!character.hasComponent(HungerComponent.class)) {
            return "You don't have a hunger level.";
        }
        HungerComponent hunger = character.getComponent(HungerComponent.class);
        if (newFood < 0) {
            hunger.lastCalculatedFood = 0;
            hunger.lastCalculationTime = time.getGameTimeInMs();
            character.saveComponent(hunger);
            return "Food level cannot be below 0. Setting to 0.";
        }
        if (newFood > hunger.maxFoodCapacity) {
            hunger.lastCalculatedFood = hunger.maxFoodCapacity;
            hunger.lastCalculationTime = time.getGameTimeInMs();
            character.saveComponent(hunger);
            return "Food level cannot be above Max Food Capacity. Setting to Max(" + hunger.maxFoodCapacity + ")";
        }
        hunger.lastCalculatedFood = newFood;
        hunger.lastCalculationTime = time.getGameTimeInMs();
        character.saveComponent(hunger);
        return "Food level successfully set to: " + newFood;
    }

    @Command(shortDescription = "Sets your max food level.", runOnServer = true)
    public String hungerSetMax(@CommandParam(value = "MaxFoodLevel") float newMax, EntityRef client) {
        EntityRef character = client.getComponent(ClientComponent.class).character;
        if (!character.hasComponent(HungerComponent.class)) {
            return "You don't have a hunger level.";
        }
        HungerComponent hunger = character.getComponent(HungerComponent.class);
        if (newMax <= 0) {
            hunger.maxFoodCapacity = 100;
            character.saveComponent(hunger);
            return "Max Food Level cannot be below or equal to 0. Setting to default (100)";
        }
        hunger.maxFoodCapacity = newMax;
        character.saveComponent(hunger);
        return "Max Food Level successfully set to: " + newMax;
    }
}
