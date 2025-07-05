/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package org.windclan.embeddedcomputer.storage.items;
import org.windclan.embeddedcomputer.storage.MediaItem;

public class ZipDiskItem extends MediaItem {
    public ZipDiskItem(net.minecraft.item.Item.Settings settings) {
        super(settings);
    }
    @Override
    public int getMaxStorage() {
        return 5000000; // 5 Megabytes
    }
    @Override
    public String getMountName() {
        return "zipdisk";
    }
}