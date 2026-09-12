package top.leonx.dynlight.dynamiclight.fabric.sdl;

import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import toni.sodiumdynamiclights.DynamicLightSource;
import toni.sodiumdynamiclights.SodiumDynamicLights;
import top.leonx.dynlight.dynamiclight.CreateDynLightSource;

public final class SodiumDynamicLightsDelegate {
    private SodiumDynamicLightsDelegate() {
    }

    public static void scheduleChunkRebuild(LevelRenderer levelRenderer, long pos) {
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

    public static int getDynamicLightsModeDelay() {
        return SodiumDynamicLights.get().config.getDynamicLightsMode().getDelay();
    }
}
