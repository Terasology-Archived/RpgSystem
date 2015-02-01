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
package org.rpgsystem.logic.ui;

import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.logic.players.LocalPlayer;
import org.terasology.registry.CoreRegistry;
import org.terasology.rendering.nui.databinding.Binding;
import org.terasology.rendering.nui.layers.hud.CoreHudWidget;
import org.terasology.rendering.nui.widgets.UILoadBar;

/**
 * @author Marcin Sciesinski <marcins78@gmail.com>
 */
public class HungerAndThirstWindow extends CoreHudWidget {
    @Override
    public void initialise() {
        UILoadBar hunger = find("hunger", UILoadBar.class);
        hunger.bindValue(
                new Binding<Float>() {
                    @Override
                    public Float get() {
                        EntityRef character = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();
                        HungerComponent hunger = character.getComponent(HungerComponent.class);
                        return HungerAndThirstUtils.getHungerForEntity(character) / hunger.maxFoodCapacity;
                    }

                    @Override
                    public void set(Float value) {
                    }
                });

        UILoadBar thirst = find("thirst", UILoadBar.class);
        thirst.bindValue(
                new Binding<Float>() {
                    @Override
                    public Float get() {
                        EntityRef character = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();
                        ThirstComponent thirst = character.getComponent(ThirstComponent.class);
                        return HungerAndThirstUtils.getThirstForEntity(character) / thirst.maxWaterCapacity;
                    }

                    @Override
                    public void set(Float value) {
                    }
                });
            UILoadBar hunger = find("stamina", UILoadBar.class);
            hunger.bindValue(
                    new Binding<Float>() {
                        @Override
                        public Float get() {
                            EntityRef character = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();
                            StaminaComponent stamina = character.getComponent(StaminaComponent.class);
                            return StaminaUtils.getStaminaForEntity(character) / stamina.maxStamina;
                        }

                        @Override
                        public void set(Float value) {
                        }
                    });
            UILoadBar hunger = find("thauma", UILoadBar.class);
            hunger.bindValue(
                    new Binding<Float>() {
                        @Override
                        public Float get() {
                            EntityRef character = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();
                            StaminaComponent stamina = character.getComponent(StaminaComponent.class);
                            return StaminaUtils.getStaminaForEntity(character) / stamina.maxStamina;
                        }

                        @Override
                        public void set(Float value) {
                        }
                    });
        }
}
