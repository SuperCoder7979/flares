package supercoder79.flares.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FlaresItems {
	public static final Item FLARE_GUN = new FlareGunItem(new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item THROWABLE_FLARE = new ThrowableFlareItem(new Item.Settings().group(ItemGroup.MISC));
	public static void init() {
		Registry.register(Registry.ITEM, new Identifier("flares", "flare_gun"), FLARE_GUN);
		Registry.register(Registry.ITEM, new Identifier("flares", "throwable_flare"), THROWABLE_FLARE);
	}
}
