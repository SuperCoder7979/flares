package supercoder79.flares.client;

import supercoder79.flares.entity.FlaresEntityTypes;

import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public class FlaresClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(FlaresEntityTypes.FLARE, context -> new FlyingItemEntityRenderer<>(context, 1.0f, true));
		EntityRendererRegistry.INSTANCE.register(FlaresEntityTypes.THROWN_FLARE, context -> new FlyingItemEntityRenderer<>(context, 1.0f, true));
	}
}
