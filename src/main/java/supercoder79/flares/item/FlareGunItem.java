package supercoder79.flares.item;

import supercoder79.flares.entity.FlareProjectileEntity;
import supercoder79.flares.entity.FlaresEntityTypes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class FlareGunItem extends Item {
	public FlareGunItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (!world.isClient()) {
			FlareProjectileEntity entity = new FlareProjectileEntity(FlaresEntityTypes.FLARE, world);
			entity.setPos(user.getX(), user.getEyeY() - 0.1, user.getZ());
			entity.setOwner(user);
			entity.setItem(new ItemStack(Items.SNOWBALL, 1));
			entity.setProperties(user, user.pitch, user.yaw, 0.0F, 2.5F, 0.5f);
			world.spawnEntity(entity);
		}

		return TypedActionResult.success(stack);
	}
}
