package top.leonx.dynlight.dynamiclight.forge.cheap;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.misanthropy.cheaplighting.DynamicLightSource;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.leonx.dynlight.dynamiclight.CreateDynLightSource;

public class CheapDynamicLightSource extends CreateDynLightSource implements DynamicLightSource {
    public CheapDynamicLightSource(AbstractContraptionEntity entity, BlockPos localPos, int luminance) {
        super(entity, localPos, luminance);
    }

    @Override
    public double cl$getDynamicLightX() {
        return super.getDynamicLightX();
    }

    @Override
    public double cl$getDynamicLightY() {
        return super.getDynamicLightY();
    }

    @Override
    public double cl$getDynamicLightZ() {
        return super.getDynamicLightZ();
    }

    @Override
    public Level cl$getDynamicLightLevel() {
        return getDynamicLightLevel();
    }

    @Override
    public void cl$resetDynamicLight() {
        super.resetDynamicLight();
    }

    @Override
    public int cl$getLuminance() {
        return super.getLuminance();
    }

    @Override
    public void cl$dynamicLightTick() {
        super.dynamicLightTick();
    }

    @Override
    public boolean cl$shouldUpdateDynamicLight() {
        return super.shouldUpdateDynamicLight();
    }

    @Override
    public boolean cheaplighting$updateDynamicLight(@NotNull LevelRenderer levelRenderer) {
        return updateDynamicLight(levelRenderer);
    }

    @Override
    public void cheaplighting$scheduleTrackedChunksRebuild(@NotNull LevelRenderer levelRenderer) {
        scheduleTrackedChunksRebuild(levelRenderer);
    }
}
