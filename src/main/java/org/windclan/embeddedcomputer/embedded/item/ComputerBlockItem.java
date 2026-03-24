/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package org.windclan.embeddedcomputer.embedded.item;

import dan200.computercraft.shared.ModRegistry;
import dan200.computercraft.shared.util.NonNegativeId;
import net.minecraft.block.Block;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.windclan.embeddedcomputer.registry;

import java.util.function.Consumer;

public class ComputerBlockItem extends BlockItem {
    public ComputerBlockItem(Block block, Item.Settings s) {
        super(block, s);
    }
    public ComputerBlockItem(Block block) {
        this(block, new Item.Settings().fireproof());
    }
    public ComputerBlockItem(Item.Settings s) {
        this(registry.EMBEDDED_COMPUTER, s);
    }
    public ItemStack newComputerItem(int id) {
        var stack = new ItemStack(this);
        if (id > 0) stack.set(ModRegistry.DataComponents.COMPUTER_ID.get(), new NonNegativeId.Computer(id));
        return stack;
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        NonNegativeId computerId = stack.get(ModRegistry.DataComponents.COMPUTER_ID.get());
        if (computerId == null) return;
        if (computerId.id() < 0) return;
        textConsumer.accept(Text.literal("Computer: "+computerId.id()).formatted(Formatting.DARK_GRAY));
    }
}
