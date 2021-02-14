package supercoder79.flares.item;

import supercoder79.flares.entity.FlaresEntityTypes;
import supercoder79.flares.entity.ThrownFlareEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ThrowableFlareItem extends Item {
	public ThrowableFlareItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (!world.isClient()) {
			ThrownFlareEntity entity = new ThrownFlareEntity(FlaresEntityTypes.THROWN_FLARE, world);
			entity.setPos(user.getX(), user.getEyeY() - 0.1, user.getZ());
			entity.setOwner(user);
			entity.setItem(new ItemStack(this, 1));
			entity.setProperties(user, user.pitch, user.yaw, 0.0F, 1.1f, 0.5f);
			world.spawnEntity(entity);
		}

		if (!user.getAbilities().creativeMode) {
			stack.decrement(1);
		}

		return TypedActionResult.success(stack);
	}
}
