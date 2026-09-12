package top.leonx.dynlight.dynamiclight.fabric.ryoamic;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.core.BlockPos;
import top.leonx.dynlight.dynamiclight.CreateDynLightSource;

public final class RyoamicLightSourceFactory {
    private RyoamicLightSourceFactory() {
    }

    public static CreateDynLightSource create(AbstractContraptionEntity entity, BlockPos blockPos, int luminance) {
        return new RyoamicDynamicLightSource(entity, blockPos, luminance);
    }
}
