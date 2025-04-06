package levaltru.degraded_end_portals.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import levaltru.degraded_end_portals.DegradedEndPortals;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(StrongholdGenerator.PortalRoom.class)
public abstract class EnderPortalsMixin extends StrongholdGenerator.Piece {

    protected EnderPortalsMixin(StructurePieceType structurePieceType, int i, BlockBox blockBox) {
        super(structurePieceType, i, blockBox);
    }

    @WrapOperation(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/structure/StrongholdGenerator$PortalRoom;addBlock(Lnet/minecraft/world/StructureWorldAccess;Lnet/minecraft/block/BlockState;IIILnet/minecraft/util/math/BlockBox;)V"))
    protected void degradedEndPortals$replaceEyesWithDegradedPortalFrames(
            StrongholdGenerator.PortalRoom instance,
            StructureWorldAccess structureWorldAccess,
            BlockState blockState,
            int x,
            int y,
            int z,
            BlockBox blockBox,
            Operation<Void> original,
            @Local(ordinal = 0, argsOnly = true) Random random) {

        Block block = blockState.getBlock();
        if (block == Blocks.END_PORTAL_FRAME && random.nextFloat() < DegradedEndPortals.DEGRADING_CHANCE) {
            blockState = DegradedEndPortals.DEGRADED_END_PORTAL_FRAME.getDefaultState()
                    .with(EndPortalFrameBlock.EYE, blockState.get(EndPortalFrameBlock.EYE))
                    .with(EndPortalFrameBlock.FACING, blockState.get(EndPortalFrameBlock.FACING));
        } else if (block == Blocks.END_PORTAL) {
            return; // prevent all eyes edge case
        }

        original.call(instance, structureWorldAccess, blockState, x, y, z, blockBox);
    }
}
