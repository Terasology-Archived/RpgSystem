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

import org.terasology.entitySystem.Component;
import org.terasology.network.Replicate;

/**
 * @author 
 */
public class StaminaComponent implements Component {
    //General Stamina Settings
    /**
     * The maximum amount of Stamina an entity can have.
     * The minimum is 0.
     */
    @Replicate
    public float maxStamina = 100;
    
    @Replicate
    public float minStamina = -10;

    @Replicate
    public float lastStamina;

    @Replicate
    public long lastCalculationTime;

    /**
     * The amount of Stamina re generated at each second (below)
     */
    @Replicate
    public float reGenPerSecond = 0.5f;

    public float hungerPerReGenRatio=0.05f;
    public float thirstPerReGenRatio=0.1f;
    
    @Replicate
    public float decreasedReGenThreshold = 0;

    @Replicate
    public float decreasedReGenRatio = 0.3f;
    
    //milli seconds added to decreaseTimeLeft for every second under decreasedReGenThreshold stamina
    @Replicate
    public long ThresholdPunishmentPerSec=300;
    
    @Replicate
    public long decreaseTimeLeft;
}
