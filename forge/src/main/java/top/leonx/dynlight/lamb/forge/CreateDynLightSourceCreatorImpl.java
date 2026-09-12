package top.leonx.dynlight.lamb.forge;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.core.BlockPos;
import top.leonx.dynlight.lamb.CreateDynLightSource;

public class CreateDynLightSourceCreatorImpl {
    public static CreateDynLightSource createDynLightSource(AbstractContraptionEntity entity, BlockPos blockPos, int luminance) {
        return new CreateDynLightSourceForge(entity, blockPos, luminance);
    }
}
