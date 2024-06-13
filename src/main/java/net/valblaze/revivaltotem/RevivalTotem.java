package net.valblaze.revivaltotem;

import net.fabricmc.api.ModInitializer;

import net.valblaze.revivaltotem.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RevivalTotem implements ModInitializer {
	public static final String MOD_ID = "revivaltotem";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}