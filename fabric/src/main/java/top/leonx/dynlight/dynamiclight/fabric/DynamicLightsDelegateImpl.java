package top.leonx.dynlight.dynamiclight.fabric;

import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import top.leonx.dynlight.dynamiclight.CreateDynLightSource;
import top.leonx.dynlight.dynamiclight.fabric.ryoamic.RyoamicLightsDelegate;
import top.leonx.dynlight.dynamiclight.fabric.sdl.SodiumDynamicLightsDelegate;

public class DynamicLightsDelegateImpl {
    private static boolean useRyoamicLights;

    public static void useRyoamicLights() {
        useRyoamicLights = true;
    }

    public static boolean isUsingRyoamicLights() {
        return useRyoamicLights;
    }

    public static void scheduleChunkRebuild(LevelRenderer levelRenderer, long pos){
        if (useRyoamicLights) {
            RyoamicLightsDelegate.scheduleChunkRebuild(levelRenderer, pos);
        } else {
            SodiumDynamicLightsDelegate.scheduleChunkRebuild(levelRenderer, pos);
        }
    }


    public static void updateTrackedChunks(BlockPos.MutableBlockPos chunkPos, LongOpenHashSet trackedLitChunkPositions, LongOpenHashSet newPos) {
        if (useRyoamicLights) {
            RyoamicLightsDelegate.updateTrackedChunks(chunkPos, trackedLitChunkPositions, newPos);
        } else {
            SodiumDynamicLightsDelegate.updateTrackedChunks(chunkPos, trackedLitChunkPositions, newPos);
        }
    }

    public static void addLightSource(CreateDynLightSource lightSource) {
        if (useRyoamicLights) {
            RyoamicLightsDelegate.addLightSource(lightSource);
        } else {
            SodiumDynamicLightsDelegate.addLightSource(lightSource);
        }
    }

    public static void removeLightSource(CreateDynLightSource lightSource) {
        if (useRyoamicLights) {
            RyoamicLightsDelegate.removeLightSource(lightSource);
        } else {
            SodiumDynamicLightsDelegate.removeLightSource(lightSource);
        }
    }

    public static boolean getDynamicLightsModeEnabled() {
        return useRyoamicLights
                ? RyoamicLightsDelegate.getDynamicLightsModeEnabled()
                : SodiumDynamicLightsDelegate.getDynamicLightsModeEnabled();
    }

    public static int getDynamicLightsModeDelay(){
        return useRyoamicLights
                ? RyoamicLightsDelegate.getDynamicLightsModeDelay()
                : SodiumDynamicLightsDelegate.getDynamicLightsModeDelay();
    }
}
