package top.leonx.dynlight.fabric;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import top.leonx.dynlight.dynamiclight.ContraptionEntityEventHandler;

public class CreateDynLightModEventHandler {
    public static void register(){
        ClientEntityEvents.ENTITY_LOAD.register(CreateDynLightModEventHandler::onClientEntityLoad);
        ClientEntityEvents.ENTITY_UNLOAD.register(CreateDynLightModEventHandler::onClientEntityUnload);
        ClientTickEvents.END_WORLD_TICK.register(CreateDynLightModEventHandler::onTick);
    }

    private static void onClientEntityLoad(Entity entity, ClientLevel clientLevel) {
        if (entity instanceof AbstractContraptionEntity contraptionEntity) {
            ContraptionEntityEventHandler.onContraptionEntityJoin(contraptionEntity);
        }
    }

    private static void onClientEntityUnload(Entity entity, ClientLevel clientLevel) {
        if (entity instanceof AbstractContraptionEntity contraptionEntity) {
            ContraptionEntityEventHandler.onContraptionEntityLeave(contraptionEntity);
        }
    }

    private static void onTick(ClientLevel clientLevel) {
        ContraptionEntityEventHandler.onTick();
    }
}
