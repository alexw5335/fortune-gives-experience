package fortunemod.mixin;

import fortunemod.FortuneGivesExperience;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("unused")
@Mixin(MobEntity.class)
public class LivingEntityMixin {


	@Inject(at = @At("RETURN"), method = "getXpToDrop", cancellable = true)
	private void getXpToDropInject(PlayerEntity entity, CallbackInfoReturnable<Integer> info) {
		if(entity == null) return;
		int looting = EnchantmentHelper.getLevel(Enchantments.LOOTING, entity.getMainHandStack());
		int experience = (int) Math.ceil(info.getReturnValue() * (1 + looting * FortuneGivesExperience.lootingMultiplier));
		info.setReturnValue(experience);
		info.cancel();
	}


}
