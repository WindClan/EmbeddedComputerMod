package org.windclan.embeddedcomputer.storage.harddrive;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.windclan.embeddedcomputer.registry;

import java.util.function.Consumer;

public class HardDriveItem  extends BlockItem {
    public HardDriveItem(net.minecraft.item.Item.Settings settings) {
        super(registry.HARD_DRIVE, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        String uuid = stack.get(registry.uuid);
        if (uuid == null) return;
        if (uuid.isEmpty()) return;
        textConsumer.accept(Text.literal("Drive: "+uuid).formatted(Formatting.DARK_GRAY));
    }
}
