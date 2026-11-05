package top.leonx.dynlight.dynamiclight.forge;

import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import top.leonx.dynlight.dynamiclight.CreateDynLightSource;
import top.leonx.dynlight.dynamiclight.forge.cheap.CheapLightsDelegate;
import top.leonx.dynlight.dynamiclight.forge.ryoamic.RyoamicLightsDelegate;
import top.leonx.dynlight.dynamiclight.forge.sdl.SodiumDynamicLightsDelegate;

public class DynamicLightsDelegateImpl {
    private static DynamicLightsBackend backend = DynamicLightsBackend.NONE;

    public static void setBackend(DynamicLightsBackend selected) {
        backend = selected;
    }

    public static DynamicLightsBackend getBackend() {
        return backend;
    }

    public static void useRyoamicLights() {
        setBackend(DynamicLightsBackend.RYOAMIC);
    }

    public static boolean isUsingRyoamicLights() {
        return backend == DynamicLightsBackend.RYOAMIC;
    }

    public static void tick() {
        if (backend == DynamicLightsBackend.CHEAP) {
            CheapLightsDelegate.tick();
        }
    }

    public static void scheduleChunkRebuild(LevelRenderer levelRenderer, long pos){
        switch (backend) {
            case SODIUM -> SodiumDynamicLightsDelegate.scheduleChunkRebuild(levelRenderer, pos);
            case RYOAMIC -> RyoamicLightsDelegate.scheduleChunkRebuild(levelRenderer, pos);
            case CHEAP -> CheapLightsDelegate.scheduleChunkRebuild(levelRenderer, pos);
            case NONE -> { }
        }
    }


    public static void updateTrackedChunks(BlockPos.MutableBlockPos chunkPos, LongOpenHashSet trackedLitChunkPositions, LongOpenHashSet newPos) {
        switch (backend) {
            case SODIUM -> SodiumDynamicLightsDelegate.updateTrackedChunks(chunkPos, trackedLitChunkPositions, newPos);
            case RYOAMIC -> RyoamicLightsDelegate.updateTrackedChunks(chunkPos, trackedLitChunkPositions, newPos);
            case CHEAP -> CheapLightsDelegate.updateTrackedChunks(chunkPos, trackedLitChunkPositions, newPos);
            case NONE -> { }
        }
    }

    public static void addLightSource(CreateDynLightSource lightSource) {
        switch (backend) {
            case SODIUM -> SodiumDynamicLightsDelegate.addLightSource(lightSource);
            case RYOAMIC -> RyoamicLightsDelegate.addLightSource(lightSource);
            case CHEAP -> CheapLightsDelegate.addLightSource(lightSource);
            case NONE -> { }
        }
    }

    public static void removeLightSource(CreateDynLightSource lightSource) {
        switch (backend) {
            case SODIUM -> SodiumDynamicLightsDelegate.removeLightSource(lightSource);
            case RYOAMIC -> RyoamicLightsDelegate.removeLightSource(lightSource);
            case CHEAP -> CheapLightsDelegate.removeLightSource(lightSource);
            case NONE -> { }
        }
    }

    public static boolean getDynamicLightsModeEnabled() {
        return switch (backend) {
            case SODIUM -> SodiumDynamicLightsDelegate.getDynamicLightsModeEnabled();
            case RYOAMIC -> RyoamicLightsDelegate.getDynamicLightsModeEnabled();
            case CHEAP -> CheapLightsDelegate.getDynamicLightsModeEnabled();
            case NONE -> false;
        };
    }

    public static int getDynamicLightsModeDelay(){
        return switch (backend) {
            case SODIUM -> SodiumDynamicLightsDelegate.getDynamicLightsModeDelay();
            case RYOAMIC -> RyoamicLightsDelegate.getDynamicLightsModeDelay();
            case CHEAP -> CheapLightsDelegate.getDynamicLightsModeDelay();
            case NONE -> 0;
        };
    }
}
