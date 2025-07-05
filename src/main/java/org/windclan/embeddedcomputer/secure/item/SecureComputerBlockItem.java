package org.windclan.embeddedcomputer.secure.item;

import net.minecraft.item.Item;
import org.windclan.embeddedcomputer.embedded.item.ComputerBlockItem;
import org.windclan.embeddedcomputer.registry;

public class SecureComputerBlockItem extends ComputerBlockItem {
    public SecureComputerBlockItem(Item.Settings s) {
        super(registry.SECURE_COMPUTER, s);
    }
}
