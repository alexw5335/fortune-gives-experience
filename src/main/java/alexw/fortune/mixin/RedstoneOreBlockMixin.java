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
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings({"unused", "RedundantCast"})
@Mixin(RedstoneOreBlock.class)
public class RedstoneOreBlockMixin {


	@Redirect(method = "onStacksDropped", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/RedstoneOreBlock;dropExperience(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;I)V"))
	private void onStacksDroppedRedirect(
		RedstoneOreBlock block,
		ServerWorld world,
		BlockPos pos,
		int size,
		BlockState state,
		ServerWorld world2,
		BlockPos pos2,
		ItemStack stack
	) {
		int experience = 1 + world.random.nextInt(5);
		int fortune = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack);
		experience = (int) Math.ceil(experience * (1 + fortune * FortuneGivesExperience.fortuneMultiplier));
		((BlockAccessor) (Object) this).invokeDropExperience(world, pos, experience);
	}


}