package top.leonx.dynlight.dynamiclight.fabric.ryoamic;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.thinkingstudio.ryoamiclights.DynamicLightSource;
import top.leonx.dynlight.dynamiclight.CreateDynLightSource;

public class RyoamicDynamicLightSource extends CreateDynLightSource implements DynamicLightSource {
    public RyoamicDynamicLightSource(AbstractContraptionEntity entity, BlockPos localPos, int luminance) {
        super(entity, localPos, luminance);
    }

    @Override
    public double ryoamicLights$getDynamicLightX() {
        return super.getDynamicLightX();
    }

    @Override
    public double ryoamicLights$getDynamicLightY() {
        return super.getDynamicLightY();
    }

    @Override
    public double ryoamicLights$getDynamicLightZ() {
        return super.getDynamicLightZ();
    }

    @Override
    public Level ryoamicLights$getDynamicLightWorld() {
        return super.getDynamicLightLevel();
    }

    @Override
    public void ryoamicLights$resetDynamicLight() {
        super.resetDynamicLight();
    }

    @Override
    public int ryoamicLights$getLuminance() {
        return super.getLuminance();
    }

    @Override
    public void ryoamicLights$dynamicLightTick() {
        super.dynamicLightTick();
    }

    @Override
    public boolean ryoamicLights$shouldUpdateDynamicLight() {
        return super.shouldUpdateDynamicLight();
    }

    @Override
    public boolean ryoamiclights$updateDynamicLight(@NotNull LevelRenderer levelRenderer) {
        return super.updateDynamicLight(levelRenderer);
    }

    @Override
    public void ryoamiclights$scheduleTrackedChunksRebuild(@NotNull LevelRenderer levelRenderer) {
        super.scheduleTrackedChunksRebuild(levelRenderer);
    }
}
