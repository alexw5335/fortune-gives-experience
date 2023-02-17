package fortunemod.mixin;

import fortunemod.FortuneGivesExperience;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin {

	@Shadow
	protected abstract void dropExperience(ServerWorld serverWorld, BlockPos blockPos, int i);

	@Inject(method = "dropExperienceWhenMined", at = @At("HEAD"))
	void mixin1(ServerWorld world, BlockPos pos, ItemStack stack, IntProvider provider, CallbackInfo ci) {
		int experience = provider.get(world.random);
		if(EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) != 0 || experience == 0) return;
		var fortune = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack);
		if(fortune == 0) return;
		experience = (int) Math.ceil(experience * fortune * FortuneGivesExperience.fortuneMultiplier);
		dropExperience(world, pos, experience);
	}

}
