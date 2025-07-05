/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package org.windclan.embeddedcomputer.storage.items;

import org.windclan.embeddedcomputer.storage.MediaItem;

public class DebugMediaItem extends MediaItem {
    public DebugMediaItem(net.minecraft.item.Item.Settings settings) {
        super(settings);
    }
    @Override
    public int getMaxStorage() {
        return 2147483647; // ~2 Gigabytes
    }

    @Override
    public String getMountName() {
        return "disk";
    }
}