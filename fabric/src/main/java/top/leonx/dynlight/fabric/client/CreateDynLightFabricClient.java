package top.leonx.dynlight.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import top.leonx.dynlight.CreateDynLight;
import top.leonx.dynlight.dynamiclight.fabric.DynamicLightsDelegateImpl;
import top.leonx.dynlight.fabric.CreateDynLightModEventHandler;

public final class CreateDynLightFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FabricLoader loader = FabricLoader.getInstance();
        boolean hasSodiumDynamicLights = loader.isModLoaded("sodiumdynamiclights");
        boolean hasRyoamicLights = loader.isModLoaded("ryoamiclights");

        if (hasSodiumDynamicLights && hasRyoamicLights) {
            CreateDynLight.LOGGER.warn("Both Sodium Dynamic Lights and RyoamicLights are installed; using Sodium Dynamic Lights");
        } else if (hasRyoamicLights) {
            DynamicLightsDelegateImpl.useRyoamicLights();
        }

        if (hasSodiumDynamicLights || hasRyoamicLights) {
            CreateDynLightModEventHandler.register();
        }
    }
}
