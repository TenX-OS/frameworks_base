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
    public static final int DEVICE_THEME_TENX_CLEAR = 9;
    public static final int DEVICE_THEME_TENX_ULTRA_CLEAR = 10;

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

    public static final String[] TENX_CLEAR = {
            "com.android.theme.tenx_clear.system",
            "com.android.theme.tenx_clear.systemui",
    };

    public static final String[] TENX_ULTRA_CLEAR = {
            "com.android.theme.tenx_ultra_clear.system",
            "com.android.theme.tenx_ultra_clear.systemui",
    };

    // QS Tile Styles
    public static final String[] QS_TILE_THEMES = {
            "com.android.systemui.qstile.default",
            "com.android.systemui.qstile.circletrim",
            "com.android.systemui.qstile.dualtonecircletrim",
            "com.android.systemui.qstile.squircletrim",
            "com.android.systemui.qstile.attemptmountain",
            "com.android.systemui.qstile.dottedcircle",
            "com.android.systemui.qstile.ninja",
            "com.android.systemui.qstile.pokesign",
            "com.android.systemui.qstile.wavey",
            "com.android.systemui.qstile.squaremedo",
            "com.android.systemui.qstile.inkdrop",
            "com.android.systemui.qstile.cookie",
            "com.android.systemui.qstile.circleoutline",
            "com.android.systemui.qstile.neonlike",
            "com.android.systemui.qstile.triangles",
            "com.android.systemui.qstile.cosmos",
            "com.android.systemui.qstile.divided",
            "com.android.systemui.qstile.oos",
            "com.android.systemui.qstile.badge",
            "com.android.systemui.qstile.badgetwo",
            "com.android.systemui.qstile.diamond",
            "com.android.systemui.qstile.gear",
            "com.android.systemui.qstile.hexagon",
            "com.android.systemui.qstile.square",
            "com.android.systemui.qstile.squircle",
            "com.android.systemui.qstile.star",
            "com.android.systemui.qstile.teardrop",
    };

    // Switch themes
    private static final String[] SWITCH_THEMES = {
        "com.android.system.switch.oneplus", // 0
        "com.android.system.switch.aosp", // 1
        "com.android.system.switch.narrow", // 2
        "com.android.system.switch.contained", // 3
        "com.android.system.switch.telegram", // 4
        "com.android.system.switch.md2", // 5
        "com.android.system.switch.retro", // 6
        "com.android.system.switch.oos", // 7
    };

    // UI Styles
    public static final String[] UI_THEMES = {
            "com.android.systemui.ui.default", // 0
            "com.android.systemui.ui.nocornerradius", // 1
            "com.android.systemui.ui.rectangle", // 2
            "com.android.systemui.ui.roundlarge", // 3
            "com.android.systemui.ui.roundmedium", // 4
    };

    // Custom Statusbar Height
    public static final String[] STATUSBAR_HEIGHT = {
        "com.android.systemui.statusbar.height_small", // 0
        "com.android.systemui.statusbar.height_medium", // 1
        "com.android.systemui.statusbar.height_large", // 2
        "com.android.systemui.statusbar.height_xlarge", // 3
    };

    // Custom Seekbar styles
    public static final String[] SEEKBAR_STYLES = {
        "com.tenx.seekbarstyle.miui_accent",
        "com.tenx.seekbarstyle.miui_gradient",
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
            case DEVICE_THEME_TENX_CLEAR:
                return TENX_CLEAR;
            case DEVICE_THEME_TENX_ULTRA_CLEAR:
                return TENX_ULTRA_CLEAR;
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

    // Switches qs tile style to user selected.
    public static void updateTileStyle(IOverlayManager om, int userId, int qsTileStyle) {
        if (qsTileStyle == 0) {
            stockTileStyle(om, userId);
        } else {
            try {
                om.setEnabled(ThemesUtils.QS_TILE_THEMES[qsTileStyle],
                        true, userId);
            } catch (RemoteException e) {
                Log.w(TAG, "Can't change qs tile icon", e);
            }
        }
    }

    // Switches qs tile style back to stock.
    public static void stockTileStyle(IOverlayManager om, int userId) {
        // skip index 0
        for (int i = 0; i < ThemesUtils.QS_TILE_THEMES.length; i++) {
            String qstiletheme = ThemesUtils.QS_TILE_THEMES[i];
            try {
                om.setEnabled(qstiletheme,
                        false /*disable*/, userId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    // Switches Switch style to user selected.
    public static void updateSwitchStyle(IOverlayManager om, int userId, int switchStyle) {
        if (switchStyle == 1) {
            stockSwitchStyle(om, userId);
        } else {
            try {
                om.setEnabled(SWITCH_THEMES[switchStyle],
                        true, userId);
            } catch (RemoteException e) {
                Log.w(TAG, "Can't change switch theme", e);
            }
        }
    }

    // Switches Switch style back to stock.
    public static void stockSwitchStyle(IOverlayManager om, int userId) {
        for (int i = 0; i < SWITCH_THEMES.length; i++) {
            String switchtheme = SWITCH_THEMES[i];
            try {
                om.setEnabled(switchtheme,
                        false /*disable*/, userId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    // Switches UI Style to user selected.
    public static void updateUIStyle(IOverlayManager om, int userId, int uiStyle) {
        if (uiStyle == 0) {
            stockUIStyle(om, userId);
        } else {
            try {
                om.setEnabled(UI_THEMES[uiStyle],
                        true, userId);
            } catch (RemoteException e) {
                Log.w(TAG, "Can't change switch theme", e);
            }
        }
    }

    // Switches UI Style back to stock.
    public static void stockUIStyle(IOverlayManager om, int userId) {
        for (int i = 0; i < UI_THEMES.length; i++) {
            String uitheme = UI_THEMES[i];
            try {
                om.setEnabled(uitheme,
                        false /*disable*/, userId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
