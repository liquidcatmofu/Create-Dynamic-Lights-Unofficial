package top.leonx.dynlight.dynamiclight.forge;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.core.BlockPos;
import top.leonx.dynlight.dynamiclight.CreateDynLightSource;
import top.leonx.dynlight.dynamiclight.forge.cheap.CheapLightSourceFactory;
import top.leonx.dynlight.dynamiclight.forge.ryoamic.RyoamicLightSourceFactory;
import top.leonx.dynlight.dynamiclight.forge.sdl.SodiumLightSourceFactory;

public class CreateDynLightSourceCreatorImpl {
    public static CreateDynLightSource createDynLightSource(AbstractContraptionEntity entity, BlockPos blockPos, int luminance) {
        // Factories keep optional backend interfaces out of this class's signatures.
        return switch (DynamicLightsDelegateImpl.getBackend()) {
            case SODIUM -> SodiumLightSourceFactory.create(entity, blockPos, luminance);
            case RYOAMIC -> RyoamicLightSourceFactory.create(entity, blockPos, luminance);
            case CHEAP -> CheapLightSourceFactory.create(entity, blockPos, luminance);
            case NONE -> throw new IllegalStateException("No dynamic lighting backend selected");
        };
    }
}
