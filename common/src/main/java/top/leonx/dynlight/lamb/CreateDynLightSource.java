package top.leonx.dynlight.lamb;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.createmod.catnip.math.VecHelper;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import top.leonx.dynlight.config.CreateDynLightAllConfigs;

public abstract class CreateDynLightSource {
    private final AbstractContraptionEntity contraptionEntity;
    private final BlockPos localPos;
    private Vec3 lastPosition;
    private Vec3 position;
    private int luminance;
    private int lastLuminance;

    private long lastUpdate;

    private ChunkPos chunkPosition;

    private BlockPos blockPos;

    private LongOpenHashSet trackedLitChunkPositions = new LongOpenHashSet();

    public CreateDynLightSource(AbstractContraptionEntity entity, BlockPos localPos, int luminance) {
        this.contraptionEntity = entity;
        this.luminance = luminance;
        lastPosition = Vec3.ZERO;
        this.localPos = localPos;

        var worldPos = entity.toGlobalVector(VecHelper.getCenterOf(localPos), 1);
        setPosition(worldPos);
    }

    private void setPosition(Vec3 position) {
        this.position = position;

        int x = Mth.floor(position.x());
        int y = Mth.floor(position.y());
        int z = Mth.floor(position.z());
        blockPos = new BlockPos(x, y, z);
        chunkPosition = new ChunkPos(blockPos);
    }

    public BlockPos blockPosition() {
        return blockPos;
    }


    public double getDynamicLightX() {
        return position.x;
    }


    public double getDynamicLightY() {
        return position.y;
    }


    public double getDynamicLightZ() {
        return position.z;
    }


    public Level getDynamicLightLevel() {
        return contraptionEntity.level();
    }


    public void resetDynamicLight() {
        this.luminance = 0;
    }


    public int getLuminance() {
        return (int) (luminance * CreateDynLightAllConfigs.client().luminanceMultiplier.get());
    }


    public void dynamicLightTick() {
    }

    public void syncPositionAndLuminance() {
        if (contraptionEntity.level() == Minecraft.getInstance().level) {
            var newPosition = contraptionEntity.toGlobalVector(VecHelper.getCenterOf(localPos), 1);
            setPosition(newPosition);

            var blockInfo = contraptionEntity.getContraption().getBlocks().get(localPos);
            if (blockInfo != null) {
                var newLuminance = blockInfo.state().getLightEmission();
                if (newLuminance != luminance) {
                    luminance = newLuminance;
                }
            }
        }
    }

    public boolean shouldUpdateDynamicLight() {
        if (!CreateDynLightAllConfigs.client().enableLambDynamicLight.get() || !LambDynLightsDelegate.getDynamicLightsModeEnabled())
            return false;

        int delay = CreateDynLightAllConfigs.client().getUpdateInterval();
        if (delay > 0) {
            long currentTime = System.currentTimeMillis();
            if (currentTime < this.lastUpdate + delay) {
                return false;
            }

            this.lastUpdate = currentTime;
        }
        return true;
    }


    public boolean updateDynamicLight(@NotNull LevelRenderer renderer) {
        if (!this.shouldUpdateDynamicLight())
            return false;

        syncPositionAndLuminance();

        double deltaX = this.position.x() - this.lastPosition.x();
        double deltaY = this.position.y() - this.lastPosition.y();
        double deltaZ = this.position.z() - this.lastPosition.z();

        int luminance = this.getLuminance();

        if (Math.abs(deltaX) > 0.1D || Math.abs(deltaY) > 0.1D || Math.abs(deltaZ) > 0.1D || luminance != this.lastLuminance) {
            this.lastPosition = new Vec3(this.position.x(), this.position.y(), this.position.z());
            this.lastLuminance = luminance;

            var newPos = new LongOpenHashSet();

            if (luminance > 0) {
                var entityChunkPos = this.chunkPosition;
                var chunkPos = new BlockPos.MutableBlockPos(entityChunkPos.x, SectionPos.posToSectionCoord(this.position.y), entityChunkPos.z);

                LambDynLightsDelegate.scheduleChunkRebuild(renderer, chunkPos);
                LambDynLightsDelegate.updateTrackedChunks(chunkPos, this.trackedLitChunkPositions, newPos);

                var directionX = (this.blockPosition().getX() & 15) >= 8 ? Direction.EAST : Direction.WEST;
                var directionY = (this.blockPosition().getY() & 15) >= 8 ? Direction.UP : Direction.DOWN;
                var directionZ = (this.blockPosition().getZ() & 15) >= 8 ? Direction.SOUTH : Direction.NORTH;

                for (int i = 0; i < 7; i++) {
                    if (i % 4 == 0) {
                        chunkPos.move(directionX); // X
                    } else if (i % 4 == 1) {
                        chunkPos.move(directionZ); // XZ
                    } else if (i % 4 == 2) {
                        chunkPos.move(directionX.getOpposite()); // Z
                    } else {
                        chunkPos.move(directionZ.getOpposite()); // origin
                        chunkPos.move(directionY); // Y
                    }
                    LambDynLightsDelegate.scheduleChunkRebuild(renderer, chunkPos);
                    LambDynLightsDelegate.updateTrackedChunks(chunkPos, this.trackedLitChunkPositions, newPos);
                }
            }

            // Schedules the rebuild of removed chunks.
            this.scheduleTrackedChunksRebuild(renderer);
            // Update tracked lit chunks.
            this.trackedLitChunkPositions = newPos;
            return true;
        }
        return false;
    }


    public void scheduleTrackedChunksRebuild(@NotNull LevelRenderer renderer) {
        if (this.contraptionEntity.level() == Minecraft.getInstance().level)
            for (long pos : this.trackedLitChunkPositions) {
                LambDynLightsDelegate.scheduleChunkRebuild(renderer, pos);
            }
    }
}
