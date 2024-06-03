/*
 * Copyright (C) 2022-2024 XAOSP Project
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

package com.android.settings.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;

public class MonetUtils {

    public static final String KEY_MONET_ACCENT_COLOR = "monet_engine_accent_color";
    public static final String KEY_MONET_RICHER_COLORS = "monet_engine_richer_colors";
    public static final String KEY_MONET_TINT_BACKGROUND = "monet_engine_tint_background";
    public static final String KEY_MONET_CHROMA_FACTOR = "monet_engine_chroma_factor";
    public static final String KEY_MONET_LUMINANCE_FACTOR = "monet_engine_luminance_factor";

    public static final int ACCENT_COLOR_DEFAULT = 0;
    public static final boolean RICHER_COLORS_DEFAULT = false;
    public static final boolean TINT_BACKGROUND_DEFAULT = true;

    public static final int CHROMA_FACTOR_DEFAULT = 100;
    public static final int CHROMA_FACTOR_MIN = 25;
    public static final int CHROMA_FACTOR_MAX = 225;

    public static final int LUMINANCE_FACTOR_DEFAULT = 100;
    public static final int LUMINANCE_FACTOR_MIN = 25;
    public static final int LUMINANCE_FACTOR_MAX = 225;

    private Context mContext;

    public MonetUtils(Context context) {
        mContext = context;
    }

    /*
     * Private helper functions.
     */

    // Obtain integer value from Settings.Secure key.
    private int getInt(String key, int defaultValue) {
        return Settings.Secure.getInt(mContext.getContentResolver(), key, defaultValue);
    }

    // Set Settings.Secure key to integer value.
    private void putInt(String key, int value) {
        Settings.Secure.putInt(mContext.getContentResolver(), key, value);
    }

    // Obtain boolean value (0 or 1) from Settings.Secure key.
    private boolean getBoolean(String key, boolean defaultValue) {
        return Settings.Secure.getInt(mContext.getContentResolver(), key,
                defaultValue ? 1 : 0) != 0;
    }

    // Set Settings.Secure key to boolean value (0 or 1).
    private void putBoolean(String key, boolean value) {
        Settings.Secure.putInt(mContext.getContentResolver(), key, value ? 1 : 0);
    }

    /*
     * Public class functions.
     */

    // Returns true if richer colors is enabled, false if not.
    public boolean isRicherColorsEnabled() {
        return getBoolean(KEY_MONET_RICHER_COLORS, RICHER_COLORS_DEFAULT);
    }

    // Enables or disables richer accent colors.
    public void setRicherColorsEnabled(boolean enable) {
        putBoolean(KEY_MONET_RICHER_COLORS, enable);
    }

    // Returns true if accent color is set, false if not.
    public boolean isAccentColorSet() {
        return getAccentColor() != ACCENT_COLOR_DEFAULT;
    }

    // Returns the current accent color.
    public int getAccentColor() {
        return getInt(KEY_MONET_ACCENT_COLOR, ACCENT_COLOR_DEFAULT);
    }

    // Sets the accent color. Setting to ACCENT_COLOR_DEFAULT removes the custom accent color and
    // returns the system to using the color obtained from the current wallpaper.
    public void setAccentColor(int color) {
        putInt(KEY_MONET_ACCENT_COLOR, color);
    }

    // Returns true if background color tinting is enabled, false if not.
    public boolean isTintBackgroundEnabled() {
        return getBoolean(KEY_MONET_TINT_BACKGROUND, TINT_BACKGROUND_DEFAULT);
    }

    // Enables or disables background color tinting.
    public void setTintBackgroundEnabled(boolean enable) {
        putBoolean(KEY_MONET_TINT_BACKGROUND, enable);
    }

    // Returns the current chroma factor value.
    public int getChromaFactor() {
        return getInt(KEY_MONET_CHROMA_FACTOR, CHROMA_FACTOR_DEFAULT);
    }

    // Sets the chroma factor value. Value cannot be set higher than CHROMA_FACTOR_MAX or lower
    // than CHROMA_FACTOR_MIN.
    public void setChromaFactor(int value) {
        if (value < CHROMA_FACTOR_MIN) {
            putInt(KEY_MONET_CHROMA_FACTOR, CHROMA_FACTOR_MIN);
        } else if (value > CHROMA_FACTOR_MAX) {
            putInt(KEY_MONET_CHROMA_FACTOR, CHROMA_FACTOR_MAX);
        } else {
            putInt(KEY_MONET_CHROMA_FACTOR, value);
        }
    }

    // Returns the current luminance factor value.
    public int getLuminanceFactor() {
        return getInt(KEY_MONET_LUMINANCE_FACTOR, LUMINANCE_FACTOR_DEFAULT);
    }

    // Sets the luminance factor value. Value cannot be set higher than LUMINANCE_FACTOR_MAX or lower
    // than LUMINANCE_FACTOR_MIN.
    public void setLuminanceFactor(int value) {
        if (value < LUMINANCE_FACTOR_MIN) {
            putInt(KEY_MONET_LUMINANCE_FACTOR, LUMINANCE_FACTOR_MIN);
        } else if (value > LUMINANCE_FACTOR_MAX) {
            putInt(KEY_MONET_LUMINANCE_FACTOR, LUMINANCE_FACTOR_MAX);
        } else {
            putInt(KEY_MONET_LUMINANCE_FACTOR, value);
        }
    }
}
