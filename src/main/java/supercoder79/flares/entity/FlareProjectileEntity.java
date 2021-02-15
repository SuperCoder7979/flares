package supercoder79.flares.entity;

import supercoder79.flares.block.FlaresBlocks;
import supercoder79.flares.block.GlowAirBlock;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FlareProjectileEntity extends SnowballEntity {
	public FlareProjectileEntity(EntityType<? extends SnowballEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.world.isClient) {
			createTrailGlowAir();
		} else {
			this.world.getLightingProvider().checkBlock(this.getBlockPos());
			MinecraftClient.getInstance().worldRenderer.updateBlock(this.world, this.getBlockPos(), null, null, 8);
			createTrailParticles();
		}
	}

	@Override
	protected void onCollision(HitResult hitResult) {
		if (!(hitResult instanceof BlockHitResult)) {
			return;
		}

		BlockPos center = ((BlockHitResult)hitResult).getBlockPos();
		if (this.world.isClient()) {
			createCollisionParticles();

			for(int x = -8; x <= 8; x++) {
				for (int z = -8; z <= 8; z++) {
					for (int y = -8; y <= 8; y++) {
						this.world.getLightingProvider().checkBlock(center.add(x, y, z));
						MinecraftClient.getInstance().worldRenderer.updateBlock(this.world, center.add(x, y, z), null, null, 8);
					}
				}
			}
			return;
		}

		createGlowAir(center);
		this.discard();
	}

	protected void createTrailGlowAir() {
		BlockPos pos = this.getBlockPos();
		if (this.world.getBlockState(pos).isAir()) {
			this.world.setBlockState(pos, FlaresBlocks.GLOW_AIR.getDefaultState().with(GlowAirBlock.BRIGHT, true), 8 | 2 | 1);
			this.world.getLightingProvider().checkBlock(pos);
			this.world.getBlockTickScheduler().schedule(pos, FlaresBlocks.GLOW_AIR, 4 + this.world.random.nextInt(3));
		}
	}

	protected void createTrailParticles() {
		double velocityX = (this.random.nextDouble() - 0.5) * 0.1;
		double velocityY = (this.random.nextDouble() - 0.5) * 0.05;
		double velocityZ = (this.random.nextDouble() - 0.5) * 0.1;
		this.world.addParticle(ParticleTypes.END_ROD, this.getX(), this.getY() + 0.15D, this.getZ(), velocityX, velocityY, velocityZ);
	}

	protected void createCollisionParticles() {
		for (int i = 0; i < 16; i++) {
			double velocityX = (this.random.nextDouble() - 0.5) * 1.5;
			double velocityY = (this.random.nextDouble() - 0.5) * 1.5;
			double velocityZ = (this.random.nextDouble() - 0.5) * 1.5;
			this.world.addParticle(ParticleTypes.END_ROD, this.getX(), this.getY() + 0.15D, this.getZ(), velocityX, velocityY, velocityZ);
		}
	}

	protected void createGlowAir(BlockPos center) {
		for(int x = -8; x <= 8; x++) {
			for(int z = -8; z <= 8; z++) {
				for(int y = -8; y <= 8; y++) {
					double rx = x / 8.0;
					double ry = y / 8.0;
					double rz = z / 8.0;
					if (rx * rx + ry * ry + rz * rz <= 1.25) {
						BlockPos local = center.add(x, y, z);

						if (this.world.getBlockState(local).isAir()) {
							this.world.setBlockState(local, FlaresBlocks.GLOW_AIR.getDefaultState().with(GlowAirBlock.BRIGHT, true), 8 | 2 | 1);
							this.world.getLightingProvider().checkBlock(local);
							this.world.getBlockTickScheduler().schedule(local, FlaresBlocks.GLOW_AIR, 300 + this.world.random.nextInt(320));
						}
					}
				}
			}
		}
	}

	@Override
	protected float getGravity() {
		return 0.0f;
	}
}
