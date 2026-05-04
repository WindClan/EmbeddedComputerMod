package org.windclan.embeddedcomputer.secure.item;

import net.minecraft.world.item.Item;
import org.windclan.embeddedcomputer.embedded.item.ComputerBlockItem;
import org.windclan.embeddedcomputer.registry;

public class SecureComputerBlockItem extends ComputerBlockItem {
    public SecureComputerBlockItem(Item.Properties s) {
        super(registry.SECURE_COMPUTER, s);
    }
}
