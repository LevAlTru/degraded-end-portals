package levaltru.degraded_end_portals.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import levaltru.degraded_end_portals.DegradedEndPortals;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.EnderEyeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnderEyeItem.class)
public abstract class AddDegradedPortalToEnderEyeMixin {
    @WrapOperation(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean degradedEndPortals$addDegradedPortalToEnderEye$BlockUse(BlockState instance, Block block, Operation<Boolean> original) {
        return original.call(instance, block) || instance.isOf(DegradedEndPortals.DEGRADED_END_PORTAL_FRAME);
    }

    @WrapOperation(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean degradedEndPortals$addDegradedPortalToEnderEye$Use(BlockState instance, Block block, Operation<Boolean> original) {
        return original.call(instance, block) || instance.isOf(DegradedEndPortals.DEGRADED_END_PORTAL_FRAME);
    }
}
