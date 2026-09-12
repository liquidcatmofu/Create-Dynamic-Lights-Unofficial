package top.leonx.dynlight.forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.leonx.dynlight.CreateDynLight;
import top.leonx.dynlight.config.forge.CreateDynLightAllConfigsImpl;
import top.leonx.dynlight.dynamiclight.forge.DynamicLightsDelegateImpl;

@Mod(CreateDynLight.MOD_ID)
public final class CreateDynLightForge {

    public CreateDynLightForge()
    {

        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        CreateDynLightAllConfigsImpl.register(modLoadingContext);

        // Register the setup method for modloading
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(EventPriority.LOWEST, this::commonSetup);

        modEventBus.addListener(this::modInit);
    }

    private void setup(FMLClientSetupEvent t) {
        CreateDynLight.LOGGER.info("CreateDynLight Initialized");

        boolean hasSodiumDynamicLights = ModList.get().isLoaded("sodiumdynamiclights");
        boolean hasRyoamicLights = ModList.get().isLoaded("ryoamiclights");

        if (hasSodiumDynamicLights && hasRyoamicLights) {
            CreateDynLight.LOGGER.warn("Both Sodium Dynamic Lights and RyoamicLights are installed; using Sodium Dynamic Lights");
        } else if (hasRyoamicLights) {
            DynamicLightsDelegateImpl.useRyoamicLights();
        }

        if (hasSodiumDynamicLights || hasRyoamicLights) {
            t.enqueueWork(()->{
                var forgeEventBus = MinecraftForge.EVENT_BUS;
                forgeEventBus.addListener(CreateDynLightModEventHandler::onEntityJoinWorld);
                forgeEventBus.addListener(CreateDynLightModEventHandler::onEntityLeaveWorld);
                forgeEventBus.addListener(CreateDynLightModEventHandler::onTick);
            });
        }
    }

    private void commonSetup(FMLCommonSetupEvent evt){
        evt.enqueueWork(CreateDynLight::init);
    }

    private void modInit(FMLLoadCompleteEvent evt){
        CreateDynLight.LOGGER.info("CreateDynLight Loaded");
    }
}
