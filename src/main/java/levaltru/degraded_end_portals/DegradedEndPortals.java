package levaltru.degraded_end_portals;

import levaltru.degraded_end_portals.blocks.DegradedEndPortalFrameBlock;
import net.fabricmc.api.ModInitializer;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DegradedEndPortals implements ModInitializer {
	public static final String MOD_ID = "degraded_end_portals";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Block DEGRADED_END_PORTAL_FRAME = registerBlock(
			"degraded_end_portal_frame",
			new DegradedEndPortalFrameBlock(AbstractBlock.Settings.copy(Blocks.END_PORTAL_FRAME)));
	public static final float DEGRADING_CHANCE = 0.3f;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

//		LOGGER.info("Hello Fabric world!");
	}

	private static Block registerBlock(String name, Block block) {
		BlockItem blockItem = new BlockItem(block, new Item.Settings());
		Registry.register(Registry.ITEM, id(name), blockItem);
		return Registry.register(Registry.BLOCK, id(name), block);
	}

	public static Identifier id(String string) {
		return Identifier.of(MOD_ID, string);
	}
}