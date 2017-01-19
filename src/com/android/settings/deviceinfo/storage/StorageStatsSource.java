/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.android.settings.deviceinfo.storage;

import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.os.UserHandle;

/**
 * StorageStatsSource wraps the StorageStatsManager for testability purposes.
 */
public class StorageStatsSource {
    private StorageStatsManager mSsm;

    public StorageStatsSource(Context context) {
        mSsm = context.getSystemService(StorageStatsManager.class);
    }

    public ExternalStorageStats getExternalStorageStats(String volumeUuid, UserHandle user) {
        return new ExternalStorageStats(mSsm.queryExternalStatsForUser(volumeUuid, user));
    }

    public static class ExternalStorageStats {
        public long totalBytes;
        public long audioBytes;
        public long videoBytes;
        public long imageBytes;

        public ExternalStorageStats(long totalBytes, long audioBytes, long videoBytes,
                long imageBytes) {
            this.totalBytes = totalBytes;
            this.audioBytes = audioBytes;
            this.videoBytes = videoBytes;
            this.imageBytes = imageBytes;
        }

        public ExternalStorageStats(android.app.usage.ExternalStorageStats stats) {
            totalBytes = stats.getTotalBytes();
            audioBytes = stats.getAudioBytes();
            videoBytes = stats.getVideoBytes();
            imageBytes = stats.getImageBytes();
        }
    }
}
