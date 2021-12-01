package fortunemod.mixin;

import net.minecraft.block.OreBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(OreBlock.class)
public interface OreBlockAccessor {

	@Accessor
	UniformIntProvider getExperienceDropped();

}

