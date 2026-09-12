package top.leonx.dynlight.lamb;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
public class CreateDynLightSourceCreator {
    @ExpectPlatform
    public static CreateDynLightSource createDynLightSource(AbstractContraptionEntity entity, BlockPos blockPos, int luminance) {
        throw new AssertionError();
    }
}
