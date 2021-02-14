package supercoder79.flares.entity;

import supercoder79.flares.block.FlaresBlocks;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ThrownFlareEntity extends FlareProjectileEntity{
	public ThrownFlareEntity(EntityType<? extends SnowballEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void createTrailGlowAir() {
		BlockPos pos = this.getBlockPos();
		if (this.world.getBlockState(pos).isAir()) {
			this.world.setBlockState(pos, FlaresBlocks.GLOW_AIR.getDefaultState(), 8 | 2 | 1);
			this.world.getLightingProvider().checkBlock(pos);
			this.world.getBlockTickScheduler().schedule(pos, FlaresBlocks.GLOW_AIR, 3 + this.world.random.nextInt(2));
		}
	}

	@Override
	protected void createTrailParticles() {
		double velocityX = (this.random.nextDouble() - 0.5) * 0.1;
		double velocityY = (this.random.nextDouble() - 0.5) * 0.05;
		double velocityZ = (this.random.nextDouble() - 0.5) * 0.1;
		this.world.addParticle(ParticleTypes.GLOW, this.getX(), this.getY() + 0.15D, this.getZ(), velocityX, velocityY, velocityZ);
	}

	@Override
	protected void createCollisionParticles() {
		for (int i = 0; i < 6; i++) {
			double velocityX = (this.random.nextDouble() - 0.5) * 0.75;
			double velocityY = (this.random.nextDouble() - 0.5) * 0.75;
			double velocityZ = (this.random.nextDouble() - 0.5) * 0.75;
			this.world.addParticle(ParticleTypes.GLOW, this.getX(), this.getY() + 0.15D, this.getZ(), velocityX, velocityY, velocityZ);
		}
	}

	@Override
	protected void createGlowAir(BlockPos center) {
		for(int x = -4; x <= 4; x++) {
			for(int z = -4; z <= 4; z++) {
				for(int y = -4; y <= 4; y++) {
					double rx = x / 4.0;
					double ry = y / 4.0;
					double rz = z / 4.0;

					if (rx * rx + ry * ry + rz * rz <= 1.25) {
						BlockPos local = center.add(x, y, z);

						if (this.world.getBlockState(local).isAir()) {
							this.world.setBlockState(local, FlaresBlocks.GLOW_AIR.getDefaultState(), 8 | 2 | 1);
							this.world.getLightingProvider().checkBlock(local);
							this.world.getBlockTickScheduler().schedule(local, FlaresBlocks.GLOW_AIR, 200 + this.world.random.nextInt(160));
						}
					}
				}
			}
		}
	}

	@Override
	protected float getGravity() {
		return 0.04f;
	}
}
