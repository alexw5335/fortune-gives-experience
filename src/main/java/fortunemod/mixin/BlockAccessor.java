package fortunemod.mixin;

import net.minecraft.block.Block;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Block.class)
public interface BlockAccessor {

	@Invoker("dropExperience")
	void invokeDropExperience(ServerWorld world, BlockPos pos, int size);

}
