package top.leonx.dynlight.dynamiclight.forge.cheap;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.core.BlockPos;
import top.leonx.dynlight.dynamiclight.CreateDynLightSource;

public final class CheapLightSourceFactory {
    private CheapLightSourceFactory() {
    }

    public static CreateDynLightSource create(AbstractContraptionEntity entity, BlockPos blockPos, int luminance) {
        return new CheapDynamicLightSource(entity, blockPos, luminance);
    }
}
