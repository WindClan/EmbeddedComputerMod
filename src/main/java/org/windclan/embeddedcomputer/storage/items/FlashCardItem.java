/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package org.windclan.embeddedcomputer.storage.items;

import org.windclan.embeddedcomputer.storage.MediaItem;
import org.windclan.embeddedcomputer.storage.ServerStorageConfig;

public class FlashCardItem extends MediaItem {
    public FlashCardItem(Settings settings) {
        super(settings);
    }
    @Override
    public int getMaxStorage() {
        return ServerStorageConfig.FLASH_CARD_STORAGE; // 2.5 Megabytes
    }

    @Override
    public String getMountName() {
        return "flashcard";
    }
}