package fortunemod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
@Mixin(Entity.class)
public interface EntityMixin {

	@Accessor
	World getWorld();

	@Accessor
	Vec3d getPos();

}
