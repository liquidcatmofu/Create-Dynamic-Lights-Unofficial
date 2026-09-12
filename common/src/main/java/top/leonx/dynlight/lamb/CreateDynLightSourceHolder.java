package top.leonx.dynlight.lamb;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.core.BlockPos;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CreateDynLightSourceHolder {

    public static final CreateDynLightSourceHolder INSTANCE = new CreateDynLightSourceHolder();

    private CreateDynLightSourceHolder() {
    }

    private final Map<LightSourceKey, CreateDynLightSource> lightSources = new HashMap<>();
    private final ReentrantReadWriteLock lightSourcesLock = new ReentrantReadWriteLock();

    public CreateDynLightSource create(AbstractContraptionEntity entity, BlockPos blockPos, int luminance) {
        var lightSource = CreateDynLightSourceCreator.createDynLightSource(entity, blockPos, luminance);
        lightSourcesLock.writeLock().lock();
        try {
            lightSources.put(new LightSourceKey(entity.getId(), blockPos), lightSource);
        } finally {
            lightSourcesLock.writeLock().unlock();
        }
        LambDynLightsDelegate.addLightSource(lightSource);
        return lightSource;
    }

    @SuppressWarnings("unused")
    public void remove(int entityId, BlockPos blockPos) {
        lightSourcesLock.writeLock().lock();
        try {
            var lightSource = lightSources.remove(new LightSourceKey(entityId, blockPos));
            if (lightSource != null) {
                LambDynLightsDelegate.removeLightSource(lightSource);
            }
        } finally {
            lightSourcesLock.writeLock().unlock();
        }
    }

    public void removeAll(AbstractContraptionEntity contraptionEntity) {
        var contraption = contraptionEntity.getContraption();
        if (contraption == null)
            return;
        lightSourcesLock.writeLock().lock();
        try {
            for (BlockPos blockPos : contraption.getBlocks().keySet()) {
                var lightSource = lightSources.remove(new LightSourceKey(contraptionEntity.getId(), blockPos));
                if (lightSource != null) {
                    LambDynLightsDelegate.removeLightSource(lightSource);
                }
            }
        } finally {
            lightSourcesLock.writeLock().unlock();
        }
    }

    public Optional<CreateDynLightSource> get(int entityId, BlockPos blockPos) {
        return get(new LightSourceKey(entityId, blockPos));
    }

    @SuppressWarnings("UnusedReturnValue")
    public CreateDynLightSource getOrCreate(AbstractContraptionEntity entity, BlockPos blockPos, int luminance) {
        return get(entity.getId(), blockPos).orElseGet(() -> create(entity, blockPos, luminance));
    }

    public Optional<CreateDynLightSource> get(LightSourceKey key) {
        lightSourcesLock.readLock().lock();
        try {
            return Optional.ofNullable(lightSources.get(key));
        } finally {
            lightSourcesLock.readLock().unlock();
        }
    }

    public static class LightSourceKey {
        private final int entityId;
        private final BlockPos blockPos;

        public LightSourceKey(int entityId, BlockPos blockPos) {
            this.entityId = entityId;
            this.blockPos = blockPos;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LightSourceKey that = (LightSourceKey) o;
            return entityId == that.entityId && Objects.equals(blockPos, that.blockPos);
        }

        @Override
        public int hashCode() {
            return Objects.hash(entityId, blockPos.asLong());
        }
    }
}
