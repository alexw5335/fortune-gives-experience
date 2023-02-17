package fortunemod.mixin;

import fortunemod.FortuneGivesExperience;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {


	@Shadow
	protected abstract void dropXp();

	@Shadow
	public abstract int getXpToDrop();

	@Shadow
	public abstract boolean isExperienceDroppingDisabled();

	@Shadow
	protected abstract boolean shouldAlwaysDropXp();

	@Shadow
	protected int playerHitTimer;

	@Shadow
	public abstract boolean shouldDropXp();



	@Inject(method = "drop", at = @At("HEAD"))
	private void inject(DamageSource damageSource, CallbackInfo ci) {
		if(
			FortuneGivesExperience.lootingMultiplier <= 0F ||
			!(damageSource instanceof EntityDamageSource) ||
			!(damageSource.getSource() instanceof ServerPlayerEntity player)
		) return;
		var looting = EnchantmentHelper.getLooting(player);
		if(looting <= 0) return;

		var experience = (int) Math.ceil(getXpToDrop() * (looting * FortuneGivesExperience.lootingMultiplier));

		var entity = (EntityMixin) (Object) (this);
		var world = entity.getWorld();
		var pos = entity.getPos();

		// taken from LivingEntity::dropXp
		if(world instanceof ServerWorld && !this.isExperienceDroppingDisabled() && (this.shouldAlwaysDropXp() || this.playerHitTimer > 0 && this.shouldDropXp() && world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)))
			ExperienceOrbEntity.spawn((ServerWorld) world, pos, experience);
	}


}
