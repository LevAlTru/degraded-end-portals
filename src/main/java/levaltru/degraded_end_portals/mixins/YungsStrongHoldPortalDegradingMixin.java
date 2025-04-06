package levaltru.degraded_end_portals.mixins;

import com.yungnickyoung.minecraft.betterstrongholds.world.processor.EndPortalFrameProcessor;
import levaltru.degraded_end_portals.DegradedEndPortals;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EndPortalFrameProcessor.class, remap = false)
public abstract class YungsStrongHoldPortalDegradingMixin extends StructureProcessor {
//    @Inject(method = "process", at = @At("RETURN"), cancellable = true)
//    private void degradedEndPortals$yungsStrongholdsDegradePortalFrames(WorldView levelReader, BlockPos jigsawPiecePos, BlockPos jigsawPieceBottomCenterPos, StructureTemplate.StructureBlockInfo blockInfoLocal, StructureTemplate.StructureBlockInfo blockInfoGlobal, StructurePlacementData structurePlacementData, CallbackInfoReturnable<StructureTemplate.StructureBlockInfo> cir) {
//        if (blockInfoGlobal.state.isOf(Blocks.END_PORTAL_FRAME)) {
//            Random randomSource = structurePlacementData.getRandom(blockInfoGlobal.pos.add(0, 5, 0));
//            if (randomSource.nextFloat() < .35f) {
//                BlockState blockState = DegradedEndPortals.DEGRADED_END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, blockInfoGlobal.state.get(EndPortalFrameBlock.FACING));
//                if (randomSource.nextFloat() < 0.15f) blockState = blockState.with(EndPortalFrameBlock.EYE, true);
//                cir.setReturnValue(
//                        new StructureTemplate.StructureBlockInfo(
//                                blockInfoGlobal.pos,
//                                blockState,
//                                blockInfoGlobal.nbt
//                        )
//                );
//            }
//        }
//    }

    @Inject(method = "process", at = @At(value = "RETURN"), cancellable = true)
    private void degradedEndPortals$yungsStrongholdsDegradePortalFrames(
            WorldView levelReader,
            BlockPos jigsawPiecePos,
            BlockPos jigsawPieceBottomCenterPos,
            StructureTemplate.StructureBlockInfo blockInfoLocal,
            StructureTemplate.StructureBlockInfo blockInfoGlobal,
            StructurePlacementData structurePlacementData,
            CallbackInfoReturnable<StructureTemplate.StructureBlockInfo> cir) {

        StructureTemplate.StructureBlockInfo returnValue = cir.getReturnValue();
        BlockState state = returnValue.state;
        if (state.isOf(Blocks.END_PORTAL_FRAME)) {
            if (structurePlacementData.getRandom(returnValue.pos).nextFloat() < DegradedEndPortals.DEGRADING_CHANCE) {
                state = DegradedEndPortals.DEGRADED_END_PORTAL_FRAME.getDefaultState()
                        .with(EndPortalFrameBlock.FACING, state.get(EndPortalFrameBlock.FACING))
                        .with(EndPortalFrameBlock.EYE, state.get(EndPortalFrameBlock.EYE));
                cir.setReturnValue(new StructureTemplate.StructureBlockInfo(returnValue.pos, state, returnValue.nbt));
            }
        } /*else if (block == Blocks.END_PORTAL) blockInfoGlobal = new StructureTemplate.StructureBlockInfo(blockInfoGlobal.pos, Blocks.AIR.getDefaultState(), blockInfoGlobal.nbt);*/ // prevent edge cases
        // i have no idea if it will work or not, so i'm disabling it
    }
}
