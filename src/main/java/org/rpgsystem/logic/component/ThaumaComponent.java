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
 * @author UltimateBudgie <TheUltimateBudgie@gmail.com>
 * @author esereja <esereja@yahoo.co.uk>
 */
public class ThaumaComponent implements Component {
    //General Hunger Settings
    /**
     * The maximum amount of magic an entity can "contain".
     * The minimum is 0.
     */
    @Replicate
    public float maxThaumaCapacity = 100;
    @Replicate
    public float minThaumaCapacity = -10;

    @Replicate
    public float lastCalculatedThauma;

    @Replicate
    public long lastCalculationTime;


    
    @Replicate
    public float regenPerSecond = 0.5f;

    /**
     * The entity will begin to lose Stamina if their thauma is < this threshold. Set to 0, if you do not want
     * the entity to lose Stamina.
     */
    @Replicate
    public float staminaLossThreshold = 1;

    @Replicate
    public float staminaToThaumaRate = 0.5f;

    @Replicate
    public float exhangePerSecond = 1f;
    
    //if we run out of usable stamina use health instead
    @Replicate
    public float healthToThaumaRate = 2f;
    
}
