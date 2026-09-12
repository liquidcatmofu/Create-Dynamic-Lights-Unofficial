package top.leonx.dynlight.dynamiclight.fabric;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.core.BlockPos;
import top.leonx.dynlight.dynamiclight.CreateDynLightSource;
import top.leonx.dynlight.dynamiclight.fabric.ryoamic.RyoamicLightSourceFactory;
import top.leonx.dynlight.dynamiclight.fabric.sdl.SodiumLightSourceFactory;

public class CreateDynLightSourceCreatorImpl {
    public static CreateDynLightSource createDynLightSource(AbstractContraptionEntity entity, BlockPos blockPos, int luminance) {
        if (DynamicLightsDelegateImpl.isUsingRyoamicLights()) {
            return RyoamicLightSourceFactory.create(entity, blockPos, luminance);
        }
        return SodiumLightSourceFactory.create(entity, blockPos, luminance);
    }
}
