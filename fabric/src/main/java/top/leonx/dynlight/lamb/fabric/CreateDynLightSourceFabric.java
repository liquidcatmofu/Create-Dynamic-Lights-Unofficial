package top.leonx.dynlight.lamb.fabric;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import toni.sodiumdynamiclights.DynamicLightSource;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.leonx.dynlight.lamb.CreateDynLightSource;

public class CreateDynLightSourceFabric extends CreateDynLightSource implements DynamicLightSource {
    public CreateDynLightSourceFabric(AbstractContraptionEntity entity, BlockPos localPos, int luminance) {
        super(entity, localPos, luminance);
    }

    @Override
    public double sdl$getDynamicLightX() {
        return super.getDynamicLightX();
    }

    @Override
    public double sdl$getDynamicLightY() {
        return super.getDynamicLightY();
    }

    @Override
    public double sdl$getDynamicLightZ() {
        return super.getDynamicLightZ();
    }

    @Override
    public Level sdl$getDynamicLightLevel() {
        return super.getDynamicLightLevel();
    }

    @Override
    public void sdl$resetDynamicLight() {
        super.resetDynamicLight();
    }

    @Override
    public int sdl$getLuminance() {
        return super.getLuminance();
    }

    @Override
    public void sdl$dynamicLightTick() {
        super.dynamicLightTick();
    }

    @Override
    public boolean sdl$shouldUpdateDynamicLight() {
        return super.shouldUpdateDynamicLight();
    }

    @Override
    public boolean sodiumdynamiclights$updateDynamicLight(@NotNull LevelRenderer levelRenderer) {
        return super.updateDynamicLight(levelRenderer);
    }

    @Override
    public void sodiumdynamiclights$scheduleTrackedChunksRebuild(@NotNull LevelRenderer levelRenderer) {
        super.scheduleTrackedChunksRebuild(levelRenderer);
    }
}
