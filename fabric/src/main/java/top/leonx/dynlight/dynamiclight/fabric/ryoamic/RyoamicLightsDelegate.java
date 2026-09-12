package top.leonx.dynlight.dynamiclight.fabric.ryoamic;

import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import org.thinkingstudio.ryoamiclights.DynamicLightSource;
import org.thinkingstudio.ryoamiclights.RyoamicLights;
import top.leonx.dynlight.dynamiclight.CreateDynLightSource;

public final class RyoamicLightsDelegate {
    private RyoamicLightsDelegate() {
    }

    public static void scheduleChunkRebuild(LevelRenderer levelRenderer, long pos) {
        RyoamicLights.scheduleChunkRebuild(levelRenderer, pos);
    }

    public static void updateTrackedChunks(BlockPos.MutableBlockPos chunkPos, LongOpenHashSet trackedLitChunkPositions, LongOpenHashSet newPos) {
        RyoamicLights.updateTrackedChunks(chunkPos, trackedLitChunkPositions, newPos);
    }

    public static void addLightSource(CreateDynLightSource lightSource) {
        if (lightSource instanceof DynamicLightSource dynamicLightSource) {
            RyoamicLights.get().addLightSource(dynamicLightSource);
        }
    }

    public static void removeLightSource(CreateDynLightSource lightSource) {
        if (lightSource instanceof DynamicLightSource dynamicLightSource) {
            RyoamicLights.get().removeLightSource(dynamicLightSource);
        }
    }

    public static boolean getDynamicLightsModeEnabled() {
        return RyoamicLights.get().config.getDynamicLightsMode().isEnabled();
    }

    public static int getDynamicLightsModeDelay() {
        return RyoamicLights.get().config.getDynamicLightsMode().getDelay();
    }
}
