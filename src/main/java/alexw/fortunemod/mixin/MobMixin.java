package alexw.fortunemod.mixin;

import alexw.fortunemod.Config;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public class MobMixin {


	@Inject(at = @At("RETURN"), method = "getExperienceReward", cancellable = true)
	private void getXpToDropInject(Player entity, CallbackInfoReturnable<Integer> info) {
		System.out.println("TEST");
		if(entity == null) return;
		int looting = EnchantmentHelper.getMobLooting(entity);
		int experience = (int) Math.ceil(info.getReturnValue() * (1 + looting * Config.lootingMultiplier()));
		info.setReturnValue(experience);
		info.cancel();
	}


}
