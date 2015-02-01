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

import org.rpgsystem.logic.SHTUtils;
import org.rpgsystem.logic.component.HungerComponent;
import org.rpgsystem.logic.component.StaminaComponent;
import org.rpgsystem.logic.component.ThaumaComponent;
import org.rpgsystem.logic.component.ThirstComponent;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.logic.players.LocalPlayer;
import org.terasology.registry.CoreRegistry;
import org.terasology.rendering.nui.databinding.Binding;
import org.terasology.rendering.nui.layers.hud.CoreHudWidget;
import org.terasology.rendering.nui.widgets.UILoadBar;

/**
 * @author orginal Marcin Sciesinski <marcins78@gmail.com>
 */
public class SHTWindow extends CoreHudWidget {
    @Override
    public void initialise() {
        UILoadBar hunger = find("hunger", UILoadBar.class);
        hunger.bindValue(
                new Binding<Float>() {
                    public Float get() {
                        EntityRef character = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();
                        HungerComponent hungerComp = character.getComponent(HungerComponent.class);
                        return SHTUtils.getHungerForEntity(character) / hungerComp.maxFoodCapacity;
                    }

                    public void set(Float value) {
                    }
                });

        UILoadBar thirst = find("thirst", UILoadBar.class);
        thirst.bindValue(
                new Binding<Float>() {
                    public Float get() {
                        EntityRef character = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();
                        ThirstComponent thirstComp = character.getComponent(ThirstComponent.class);
                        return SHTUtils.getThirstForEntity(character) / thirstComp.maxWaterCapacity;
                    }

                    public void set(Float value) {
                    }
                });
            UILoadBar stamina = find("stamina", UILoadBar.class);
            stamina.bindValue(
                    new Binding<Float>() {
                        public Float get() {
                            EntityRef character = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();
                            StaminaComponent staminaComp = character.getComponent(StaminaComponent.class);
                            return SHTUtils.getStaminaForEntity(character) / staminaComp.maxStamina;
                        }

                        public void set(Float value) {
                        }
                    });
            UILoadBar thauma = find("thauma", UILoadBar.class);
            thauma.bindValue(
                    new Binding<Float>() {
                        public Float get() {
                            EntityRef character = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();
                            ThaumaComponent thaumaComp = character.getComponent(ThaumaComponent.class);
                            return SHTUtils.getThaumaForEntity(character) / thaumaComp.maxThaumaCapacity;
                        }

                        public void set(Float value) {
                        }
                    });
        }
}
