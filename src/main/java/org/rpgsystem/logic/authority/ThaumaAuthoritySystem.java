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
import org.rpgsystem.logic.component.ThaumaComponent;
import org.terasology.engine.Time;
import org.terasology.entitySystem.entity.EntityManager;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.entity.lifecycleEvents.BeforeDeactivateComponent;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterMode;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.players.event.OnPlayerSpawnedEvent;
import org.terasology.registry.In;

/**
 * @author UltimateBudgie <TheUltimateBudgie@gmail.com>
 */

@RegisterSystem(RegisterMode.AUTHORITY)
public class ThaumaAuthoritySystem extends BaseComponentSystem {

    @In
    private EntityManager entityManager;

    @In
    private Time time;

    @ReceiveEvent
    public void onPlayerRespawn(OnPlayerSpawnedEvent event, EntityRef player,
                                ThaumaComponent Thauma) {
        Thauma.lastCalculatedThauma = Thauma.maxThaumaCapacity;
        Thauma.lastCalculationTime = time.getGameTimeInMs();
        player.saveComponent(Thauma);
    }

    @ReceiveEvent
    public void onPlayerFirstSpawn(OnPlayerSpawnedEvent event, EntityRef player) {
        if (!player.hasComponent(ThaumaComponent.class)) {
        	ThaumaComponent thauma = new ThaumaComponent();
            thauma.lastCalculatedThauma = thauma.maxThaumaCapacity;
            thauma.lastCalculationTime = time.getGameTimeInMs();
            player.addComponent(thauma);
        }
    }

    @ReceiveEvent
    public void beforeRemoval(BeforeDeactivateComponent event, EntityRef entity, ThaumaComponent thauma) {
    	thauma.lastCalculatedThauma = SHTUtils.getThaumaForEntity(entity);
    	thauma.lastCalculationTime = time.getGameTimeInMs();
        entity.saveComponent(thauma);
    }


    /*@Command(shortDescription = "Checks your hunger/food level.", runOnServer = true)
    public String hungerCheck(EntityRef client) {
        EntityRef character = client.getComponent(ClientComponent.class).character;
        if (character.hasComponent(HungerComponent.class)) {
            HungerComponent hunger = character.getComponent(HungerComponent.class);
            return "Current Food Level: " + HungerAndThirstUtils.getHungerForEntity(character) + "/" + hunger.maxFoodCapacity;
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
    }*/
}
