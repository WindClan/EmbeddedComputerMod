/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package org.windclan.embeddedcomputer.storage.harddrive;

import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.filesystem.WritableMount;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.windclan.embeddedcomputer.registry;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

import static java.util.Objects.isNull;

public class HardDriveBlockEntity extends BlockEntity  {
    public HardDriveBlockEntity(BlockPos pos, BlockState state) {
        super(registry.HARD_DRIVE_ENTITY,pos, state);
    }
    private final HardDrivePeripheral periph = new HardDrivePeripheral(this);
    public String uuid = "";
    public String mount;

    public static void tick(World world1, BlockPos pos, BlockState state1, BlockEntity be) {}
    public WritableMount makeMount() {
        if (uuid.isEmpty()) {
            uuid = UUID.randomUUID().toString();
            markDirty();
        }
        return ComputerCraftAPI.createSaveDirMount(world.getServer(), "hdd/" + uuid, 25000000); // 25 Megabytes
    }
    public boolean attach(IComputerAccess computer, @Nullable String str) {
        if (isNull(str)) {
            str = "drive";
        }
        mount = computer.mountWritable(str,makeMount());
        return !isNull(mount);
    }
    public boolean detach(IComputerAccess computer, @Nullable String str) {
        if (isNull(str)) {
            str = "drive";
        }
        try {
            computer.unmount(str);
            return true;
        } catch(Exception ignored) {
            return false;
        }
    }
    @Override
    public void writeData(WriteView view) {
        view.putString("uuid", uuid);
        super.writeData(view);
    }

    @Override
    public void readData(ReadView view) {
        super.readData(view);
        uuid = view.getString("uuid","");
        if (uuid.isEmpty()) {
            uuid = UUID.randomUUID().toString();
            markDirty();
        }
    }

    @Override
    protected void readComponents(ComponentsAccess components) {
        super.readComponents(components);
        uuid = components.getOrDefault(registry.uuid,"");
        if (uuid.isEmpty()) {
            uuid = UUID.randomUUID().toString();
            markDirty();
        }
    }

    @Override
    protected void addComponents(ComponentMap.Builder componentMapBuilder) {
        super.addComponents(componentMapBuilder);
        componentMapBuilder.add(registry.uuid,this.uuid);
    }

    @Override
    public void removeFromCopiedStackData(WriteView view) {
        view.remove("uuid");
    }
    public IPeripheral peripheral() {
        return periph;
    }
}
