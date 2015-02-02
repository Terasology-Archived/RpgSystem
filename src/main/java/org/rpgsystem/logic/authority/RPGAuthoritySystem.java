package org.rpgsystem.logic.authority;

import org.rpgsystem.logic.component.RPGComponent;
import org.terasology.engine.Time;
import org.terasology.entitySystem.entity.EntityManager;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.entity.lifecycleEvents.BeforeDeactivateComponent;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterMode;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.console.commandSystem.annotations.Command;
import org.terasology.logic.console.commandSystem.annotations.CommandParam;
import org.terasology.logic.players.event.OnPlayerSpawnedEvent;
import org.terasology.network.ClientComponent;
import org.terasology.registry.In;

/**
 * @author esereja <esereja@yahoo.co.uk>
 */

@RegisterSystem(RegisterMode.AUTHORITY)
public class RPGAuthoritySystem extends BaseComponentSystem {
	
	@In
    private EntityManager entityManager;

    @In
    private Time time;

    @ReceiveEvent
    public void onPlayerRespawn(OnPlayerSpawnedEvent event, EntityRef player,
    		RPGComponent rpg) {
    	//TODO this prop don't need any stuff here
        player.saveComponent(rpg);
    }

    @ReceiveEvent
    public void onPlayerFirstSpawn(OnPlayerSpawnedEvent event, EntityRef player) {
        if (!player.hasComponent(RPGComponent.class)) {
        	RPGComponent rpg = new RPGComponent();
        	rpg.physicalExperience=0;
        	rpg.mentalExperience=0;
        	rpg.experience=0;
            //TODO more stuff here
            player.addComponent(rpg);
        }
    }

    @ReceiveEvent
    public void beforeRemoval(BeforeDeactivateComponent event, EntityRef entity, RPGComponent rpg) {
    	//TODO this is probably unneeded
        entity.saveComponent(rpg);
    }
    
    //TODO catch weapon activation events
    
    @Command(shortDescription = "Sets your agility.", runOnServer = true)
    public String agilitySet(@CommandParam(value = "agility") float newMax, EntityRef client) {
        EntityRef character = client.getComponent(ClientComponent.class).character;
        if (!character.hasComponent(RPGComponent.class)) {
        	RPGComponent rpg = new RPGComponent();
        	rpg.physicalExperience=0;
        	rpg.mentalExperience=0;
        	rpg.experience=0;
            rpg.setBaseAgility(newMax);
            
        	character.addComponent(rpg);
            return "You didn't have a RPG system on you, thus we added one. \nThis is bug report it!";
        }
        RPGComponent rpg =character.getComponent(RPGComponent.class);
        rpg.setBaseAgility(newMax);
        return "Agility successfully set to: " + newMax;
    }
    
    @Command(shortDescription = "Sets your strength.", runOnServer = true)
    public String strengthSet(@CommandParam(value = "strength") float newMax, EntityRef client) {
        EntityRef character = client.getComponent(ClientComponent.class).character;
        if (!character.hasComponent(RPGComponent.class)) {
        	RPGComponent rpg = new RPGComponent();
        	rpg.physicalExperience=0;
        	rpg.mentalExperience=0;
        	rpg.experience=0;
            rpg.setBaseStrength(newMax);
            
        	character.addComponent(rpg);
            return "You didn't have a RPG system on you, thus we added one. \nThis is bug report it!";
        }
        RPGComponent rpg =character.getComponent(RPGComponent.class);
        rpg.setBaseStrength(newMax);
        return "Strength successfully set to: " + newMax;
    }
    
    @Command(shortDescription = "Sets your constitution.", runOnServer = true)
    public String constitutionSet(@CommandParam(value = "constitution") float newMax, EntityRef client) {
        EntityRef character = client.getComponent(ClientComponent.class).character;
        if (!character.hasComponent(RPGComponent.class)) {
        	RPGComponent rpg = new RPGComponent();
        	rpg.physicalExperience=0;
        	rpg.mentalExperience=0;
        	rpg.experience=0;
            rpg.setBaseConstitution(newMax);
            
        	character.addComponent(rpg);
            return "You didn't have a RPG system on you, thus we added one. \nThis is bug report it!";
        }
        RPGComponent rpg =character.getComponent(RPGComponent.class);
        rpg.setBaseConstitution(newMax);
        return "Constitution successfully set to: " + newMax;
    }
    
    @Command(shortDescription = "Sets your intelligence.", runOnServer = true)
    public String intelligenceSet(@CommandParam(value = "intelligence") float newMax, EntityRef client) {
        EntityRef character = client.getComponent(ClientComponent.class).character;
        if (!character.hasComponent(RPGComponent.class)) {
        	RPGComponent rpg = new RPGComponent();
        	rpg.physicalExperience=0;
        	rpg.mentalExperience=0;
        	rpg.experience=0;
            rpg.setBaseIntelligence(newMax);
            
        	character.addComponent(rpg);
            return "You didn't have a RPG system on you, thus we added one. \nThis is bug report it!";
        }
        RPGComponent rpg =character.getComponent(RPGComponent.class);
        rpg.setBaseIntelligence(newMax);
        return "Intelligence successfully set to: " + newMax;
    }
    
    @Command(shortDescription = "Sets your wisdom.", runOnServer = true)
    public String wisdomSet(@CommandParam(value = "wisdom") float newMax, EntityRef client) {
        EntityRef character = client.getComponent(ClientComponent.class).character;
        if (!character.hasComponent(RPGComponent.class)) {
        	RPGComponent rpg = new RPGComponent();
        	rpg.physicalExperience=0;
        	rpg.mentalExperience=0;
        	rpg.experience=0;
            rpg.setBaseWisdom(newMax);
            
        	character.addComponent(rpg);
            return "You didn't have a RPG system on you, thus we added one. \nThis is bug report it!";
        }
        RPGComponent rpg =character.getComponent(RPGComponent.class);
        rpg.setBaseWisdom(newMax);
        return "Wisdom successfully set to: " + newMax;
    }
    
    @Command(shortDescription = "Sets your thaumaticy.", runOnServer = true)
    public String thaumaticySet(@CommandParam(value = "thaumaticy") float newMax, EntityRef client) {
        EntityRef character = client.getComponent(ClientComponent.class).character;
        if (!character.hasComponent(RPGComponent.class)) {
        	RPGComponent rpg = new RPGComponent();
        	rpg.physicalExperience=0;
        	rpg.mentalExperience=0;
        	rpg.experience=0;
            rpg.setBaseThaumaticy(newMax);
            
        	character.addComponent(rpg);
            return "You didn't have a RPG system on you, thus we added one. \nThis is bug report it!";
        }
        RPGComponent rpg =character.getComponent(RPGComponent.class);
        rpg.setBaseThaumaticy(newMax);
        return "Thaumaticy successfully set to: " + newMax;
    }
    
    @Command(shortDescription = "Sets your charisma.", runOnServer = true)
    public String charismaSet(@CommandParam(value = "charisma") float newMax, EntityRef client) {
        EntityRef character = client.getComponent(ClientComponent.class).character;
        if (!character.hasComponent(RPGComponent.class)) {
        	RPGComponent rpg = new RPGComponent();
        	rpg.physicalExperience=0;
        	rpg.mentalExperience=0;
        	rpg.experience=0;
            rpg.setBaseCharisma(newMax);
            
        	character.addComponent(rpg);
            return "You didn't have a RPG system on you, thus we added one. \nThis is bug report it!";
        }
        RPGComponent rpg =character.getComponent(RPGComponent.class);
        rpg.setBaseCharisma(newMax);
        return "Charisma successfully set to: " + newMax;
    }
    
    @Command(shortDescription = "Sets your luck.", runOnServer = true)
    public String luckSet(@CommandParam(value = "luck") float newMax, EntityRef client) {
        EntityRef character = client.getComponent(ClientComponent.class).character;
        if (!character.hasComponent(RPGComponent.class)) {
        	RPGComponent rpg = new RPGComponent();
        	rpg.physicalExperience=0;
        	rpg.mentalExperience=0;
        	rpg.experience=0;
            rpg.setBaseLuck(newMax);
            
        	character.addComponent(rpg);
            return "You didn't have a RPG system on you, thus we added one. \nThis is bug report it!";
        }
        RPGComponent rpg =character.getComponent(RPGComponent.class);
        rpg.setBaseLuck(newMax);
        return "Luck successfully set to: " + newMax;
    }
}