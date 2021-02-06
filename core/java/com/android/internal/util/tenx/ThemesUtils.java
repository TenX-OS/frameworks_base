/*
 * Copyright (C) 2014 The Android Open Source Project
 * Copyright (C) 2021 TenX-OS
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
 * limitations under the License.
 */

package com.android.internal.util.tenx;

import static android.os.UserHandle.USER_SYSTEM;

import android.app.UiModeManager;
import android.content.Context;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.util.Log;

public class ThemesUtils {

    private Context mContext;
    private UiModeManager mUiModeManager;
    private IOverlayManager overlayManager;

    public ThemesUtils(Context context) {
        mContext = context;
        mUiModeManager = context.getSystemService(UiModeManager.class);
        overlayManager = IOverlayManager.Stub
                .asInterface(ServiceManager.getService(Context.OVERLAY_SERVICE));
    }

    public static final String TAG = "ThemeUtils";

    public static final int DEVICE_THEME_LIGHT = 1;
    public static final int DEVICE_THEME_DARK = 2;
    public static final int DEVICE_THEME_SOLARIZED_DARK = 3;
    public static final int DEVICE_THEME_BAKED_GREEN = 4;
    public static final int DEVICE_THEME_CHOCO_X = 5;
    public static final int DEVICE_THEME_PITCH_BLACK = 6;
    public static final int DEVICE_THEME_DARK_GREY = 7;
    public static final int DEVICE_THEME_MATERIAL_OCEAN = 8;

    public static final String[] SOLARIZED_DARK = {
            "com.android.theme.solarizeddark.system",
            "com.android.theme.solarizeddark.systemui",
    };

    public static final String[] BAKED_GREEN = {
            "com.android.theme.bakedgreen.system",
            "com.android.theme.bakedgreen.systemui",
    };

    public static final String[] CHOCO_X = {
            "com.android.theme.chocox.system",
            "com.android.theme.chocox.systemui",
    };

    public static final String[] PITCH_BLACK = {
            "com.android.theme.pitchblack.system",
            "com.android.theme.pitchblack.systemui",
    };

    public static final String[] DARK_GREY = {
            "com.android.theme.darkgrey.system",
            "com.android.theme.darkgrey.systemui",
    };
    public static final String[] MATERIAL_OCEAN = {
            "com.android.theme.materialocean.system",
            "com.android.theme.materialocean.systemui",
    };

    public String[] getTheme(int theme) {
        switch (theme) {
            case DEVICE_THEME_LIGHT:
            case DEVICE_THEME_DARK:
                break;
            case DEVICE_THEME_SOLARIZED_DARK:
                return SOLARIZED_DARK;
            case DEVICE_THEME_BAKED_GREEN:
                return BAKED_GREEN;
            case DEVICE_THEME_CHOCO_X:
                return CHOCO_X;
            case DEVICE_THEME_PITCH_BLACK:
                return PITCH_BLACK;
            case DEVICE_THEME_DARK_GREY:
                return DARK_GREY;
            case DEVICE_THEME_MATERIAL_OCEAN:
                return MATERIAL_OCEAN;
        }
        return null;
    }

    public void setTheme(int theme) {
	int mCurrentTheme = Settings.System.getIntForUser(mContext.getContentResolver(),
                Settings.System.SYSTEM_THEME_STYLE, 0, USER_SYSTEM);

        if (theme != mCurrentTheme) {
            setEnabled(getTheme(mCurrentTheme), false);
        } else if (theme == mCurrentTheme) {
            return;
        }

        if (theme == DEVICE_THEME_LIGHT) {
            mUiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
        }
        else if(theme == DEVICE_THEME_DARK) {
            mUiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
        }
        else {
            mUiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
            setEnabled(getTheme(theme), true);
        }

        Settings.System.putInt(mContext.getContentResolver(), Settings.System.SYSTEM_THEME_STYLE, theme);
    }

    public void setEnabled(String[] themes, boolean enabled) {

        if (themes == null)
            return;

        for (String theme : themes) {
            try {
                overlayManager.setEnabled(theme, enabled, USER_SYSTEM);
            } catch (RemoteException e) {
                Log.e(TAG, "Can't change theme", e);
            }
        }
    }
}

