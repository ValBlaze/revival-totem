package net.valblaze.revivaltotem.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.valblaze.revivaltotem.RevivalTotem;
import net.valblaze.revivaltotem.item.custom.TotemOfRevivalItem;

public class ModItems {

    public static final Item TOTEM_OF_REVIVAL = registerItem("totem_of_revival", new TotemOfRevivalItem(new FabricItemSettings().maxCount(1).food(ModFoodComponents.TOTEM_OF_REVIVAL).rarity(Rarity.EPIC)));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(TOTEM_OF_REVIVAL);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(RevivalTotem.MOD_ID, name), item);
    }

    public static void registerModItems() {
        RevivalTotem.LOGGER.info("Registering mod items for " + RevivalTotem.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToIngredientItemGroup);
    }
}
