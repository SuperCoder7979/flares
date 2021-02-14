package supercoder79.flares.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FlaresBlocks {
	public static final Block GLOW_AIR = new GlowAirBlock(AbstractBlock.Settings.of(Material.AIR).ticksRandomly().luminance(state -> state.get(GlowAirBlock.BRIGHT) ? 15 : 7).noCollision().dropsNothing().air());

	public static void init() {
		Registry.register(Registry.BLOCK, new Identifier("flares", "glow_air"), GLOW_AIR);
	}
}
