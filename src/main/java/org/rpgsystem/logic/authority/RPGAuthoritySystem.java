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
import org.terasology.logic.players.event.OnPlayerSpawnedEvent;
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
    
}