package alexw.fortune.mixin;

import alexw.fortune.FortuneGivesExperience;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings({"unused"})
@Mixin(RedstoneOreBlock.class)
public class RedstoneOreBlockMixin {


	@Inject(method = "onStacksDropped", at = @At("HEAD"), cancellable = true)
	private void inject(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, boolean bl, CallbackInfo ci) {
		if(!bl || EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) return;
		var fortune = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack);
		if(fortune <= 0) return;
		var experience = 1 + world.random.nextInt(5);
		experience = (int) Math.ceil(experience * (1 + fortune * FortuneGivesExperience.fortuneMultiplier));
		((BlockMixin) (Object) this).invokeDropExperience(world, pos, experience);
		ci.cancel();
	}



}