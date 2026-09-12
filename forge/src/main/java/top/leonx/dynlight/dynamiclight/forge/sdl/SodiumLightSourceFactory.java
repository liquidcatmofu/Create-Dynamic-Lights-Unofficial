package top.leonx.dynlight.dynamiclight.forge.sdl;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.core.BlockPos;
import top.leonx.dynlight.dynamiclight.CreateDynLightSource;

public final class SodiumLightSourceFactory {
    private SodiumLightSourceFactory() {
    }

    public static CreateDynLightSource create(AbstractContraptionEntity entity, BlockPos blockPos, int luminance) {
        return new SodiumDynamicLightSource(entity, blockPos, luminance);
    }
}
