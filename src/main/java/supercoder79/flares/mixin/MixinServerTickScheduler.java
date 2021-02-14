package supercoder79.flares.mixin;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import supercoder79.flares.block.GlowAirBlock;

import net.minecraft.server.world.ServerTickScheduler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.ScheduledTick;

@Mixin(ServerTickScheduler.class)
public class MixinServerTickScheduler<T> {
	@Mutable
	@Shadow @Final protected Predicate<T> invalidObjPredicate;

	@Inject(method = "<init>", at = @At("RETURN"))
	private void handleConstructor(ServerWorld world, Predicate<T> invalidObjPredicate, Function<T, Identifier> idToName, Consumer<ScheduledTick<T>> consumer, CallbackInfo ci) {
		this.invalidObjPredicate = (obj) -> {
			if (obj instanceof GlowAirBlock) {
				return false;
			} else {
				return invalidObjPredicate.test(obj);
			}
		};
	}
}
