package top.leonx.dynlight.lamb.fabric;

import toni.sodiumdynamiclights.DynamicLightSource;
import toni.sodiumdynamiclights.SodiumDynamicLights;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import top.leonx.dynlight.lamb.CreateDynLightSource;

public class LambDynLightsDelegateImpl {

    public static void scheduleChunkRebuild(LevelRenderer levelRenderer, long pos){
        SodiumDynamicLights.scheduleChunkRebuild(levelRenderer, pos);
    }


    public static void updateTrackedChunks(BlockPos.MutableBlockPos chunkPos, LongOpenHashSet trackedLitChunkPositions, LongOpenHashSet newPos) {
        SodiumDynamicLights.updateTrackedChunks(chunkPos, trackedLitChunkPositions, newPos);
    }

    public static void addLightSource(CreateDynLightSource lightSource) {
        if (lightSource instanceof DynamicLightSource dynamicLightSource) {
            SodiumDynamicLights.get().addLightSource(dynamicLightSource);
        }
    }

    public static void removeLightSource(CreateDynLightSource lightSource) {
        if (lightSource instanceof DynamicLightSource dynamicLightSource) {
            SodiumDynamicLights.get().removeLightSource(dynamicLightSource);
        }
    }

    public static boolean getDynamicLightsModeEnabled() {
        return SodiumDynamicLights.get().config.getDynamicLightsMode().isEnabled();
    }

    public static int getDynamicLightsModeDelay(){
        return SodiumDynamicLights.get().config.getDynamicLightsMode().getDelay();
    }
}
