package alexw.fortunemod.mixin;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class TestMixin {


	@Inject(at = @At("RETURN"), method = "tick")
	private void getXpToDropInject(CallbackInfo ci) {
		System.out.println("LIVING ENTITY TICK");
	}


}
