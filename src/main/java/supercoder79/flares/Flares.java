package supercoder79.flares;

import supercoder79.flares.block.FlaresBlocks;
import supercoder79.flares.entity.FlaresEntityTypes;
import supercoder79.flares.item.FlaresItems;

import net.fabricmc.api.ModInitializer;

public class Flares implements ModInitializer {
	@Override
	public void onInitialize() {
		FlaresBlocks.init();
		FlaresItems.init();
		FlaresEntityTypes.init();
	}
}
