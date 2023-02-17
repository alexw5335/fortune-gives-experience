package fortunemod.mixin;

import fortunemod.FortuneGivesExperience;
import net.minecraft.block.OreBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings({"unused"})
@Mixin(OreBlock.class)
public abstract class OreBlockMixin {


	@Redirect(
		method = "onStacksDropped",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/block/OreBlock;dropExperienceWhenMined(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/item/ItemStack;Lnet/minecraft/util/math/intprovider/IntProvider;)V"
		)
	)
	private void redirect(OreBlock instance, ServerWorld world, BlockPos pos, ItemStack stack, IntProvider provider) {
		var experience = provider.get(world.random);
		var fortune = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack);
		experience = (int) Math.ceil(experience * (1 + fortune * FortuneGivesExperience.fortuneMultiplier));
		((BlockMixin) (Object) this).invokeDropExperience(world, pos, experience);
	}


}