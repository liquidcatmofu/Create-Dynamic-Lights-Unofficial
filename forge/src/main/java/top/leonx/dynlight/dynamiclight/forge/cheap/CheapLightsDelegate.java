package top.leonx.dynlight.dynamiclight.forge.cheap;

import com.misanthropy.cheaplighting.CheapLighting;
import com.misanthropy.cheaplighting.DynamicLightSource;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import top.leonx.dynlight.dynamiclight.CreateDynLightSource;

import java.util.HashSet;
import java.util.Set;

public final class CheapLightsDelegate {
    // Keep ownership while Cheap Lighting is OFF or clears its renderer's sources.
    private static final Set<CreateDynLightSource> sources = new HashSet<>();

    private CheapLightsDelegate() {
    }

    public static void scheduleChunkRebuild(LevelRenderer renderer, long pos) {
        CheapLighting.scheduleChunkRebuild(renderer, pos);
    }

    public static void updateTrackedChunks(BlockPos.MutableBlockPos chunkPos, LongOpenHashSet old, LongOpenHashSet newPos) {
        CheapLighting.updateTrackedChunks(chunkPos, old, newPos);
    }

    public static void addLightSource(CreateDynLightSource source) {
        if (source instanceof DynamicLightSource light) {
            sources.add(source);
            CheapLighting.get().addLightSource(light);
        }
    }

    public static void removeLightSource(CreateDynLightSource source) {
        if (source instanceof DynamicLightSource light) {
            sources.remove(source);
            CheapLighting.get().removeLightSource(light);
        }
    }

    public static boolean getDynamicLightsModeEnabled() {
        return CheapLighting.get().config.getDynamicLightsMode().isEnabled();
    }

    public static int getDynamicLightsModeDelay() {
        return CheapLighting.get().config.getDynamicLightsMode().getDelay();
    }

    public static void tick() {
        var backend = CheapLighting.get();
        boolean enabled = getDynamicLightsModeEnabled();
        for (var source : sources) {
            var light = (DynamicLightSource) source;
            if (enabled && !backend.containsLightSource(light)) {
                source.syncPositionAndLuminance();
                backend.addLightSource(light);
                source.scheduleTrackedChunksRebuild(Minecraft.getInstance().levelRenderer);
            } else if (!enabled && backend.containsLightSource(light)) {
                backend.removeLightSource(light);
            }
        }
    }
}
