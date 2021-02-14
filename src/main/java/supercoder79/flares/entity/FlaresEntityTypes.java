package supercoder79.flares.entity;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

public class FlaresEntityTypes {
	public static final EntityType<FlareProjectileEntity> FLARE = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier("flares", "flare"),
			FabricEntityTypeBuilder.create(SpawnGroup.MISC, FlareProjectileEntity::new)
					.dimensions(EntityDimensions.fixed(0.25F, 0.25F))
					.build());

	public static final EntityType<ThrownFlareEntity> THROWN_FLARE = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier("flares", "thrown_flare"),
			FabricEntityTypeBuilder.create(SpawnGroup.MISC, ThrownFlareEntity::new)
					.dimensions(EntityDimensions.fixed(0.25F, 0.25F))
					.build());
	public static void init() {

	}
}
