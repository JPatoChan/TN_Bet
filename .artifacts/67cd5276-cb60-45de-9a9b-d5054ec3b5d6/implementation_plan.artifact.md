# Implementation Plan - Tennessee Theme & Splash Screen

This plan details the changes required to update the TN_Bet app with Tennessee state colors, a flag-based app logo, and a sports-themed startup animation.

## Proposed Changes

### 1. Color Palette Updates
Update the app's theme to use Tennessee state colors (Crimson Red, Blue, and White).

#### [MODIFY] [Color.kt](file:///C:/Users/pnanc/AndroidStudioProjects/TN_Bet/app/src/main/java/com/example/tn_bet/ui/theme/Color.kt)
- Add `TNRed` (`#C8102E`), `TNBlue` (`#002D62`), and `TNWhite` (`#FFFFFF`).

#### [MODIFY] [Theme.kt](file:///C:/Users/pnanc/AndroidStudioProjects/TN_Bet/app/src/main/java/com/example/tn_bet/ui/theme/Theme.kt)
- Update `LightColorScheme` and `DarkColorScheme` to use the new TN colors as primary, secondary, and background colors.
- Ensure font colors (`onPrimary`, `onBackground`, etc.) match the state palette.

---

### 2. App Logo (Tennessee State Flag)
Replace the default launcher icon with a custom Tennessee flag design.

#### [NEW] [ic_tn_flag.xml](file:///C:/Users/pnanc/AndroidStudioProjects/TN_Bet/app/src/main/res/drawable/ic_tn_flag.xml)
- A vector drawable representing the Tennessee state flag (Tristar in a blue circle on a red field).

#### [MODIFY] [ic_launcher_foreground.xml](file:///C:/Users/pnanc/AndroidStudioProjects/TN_Bet/app/src/main/res/drawable/ic_launcher_foreground.xml)
- Use the Tristar element from the flag as the foreground icon.

#### [MODIFY] [ic_launcher_background.xml](file:///C:/Users/pnanc/AndroidStudioProjects/TN_Bet/app/src/main/res/drawable/ic_launcher_background.xml)
- Set the background to `TNRed`.

---

### 3. Startup Animation (TN Sports)
Implement a splash screen with a Tennessee sports-related animation.

#### [MODIFY] [libs.versions.toml](file:///C:/Users/pnanc/AndroidStudioProjects/TN_Bet/gradle/libs.versions.toml)
- Add `androidx-core-splashscreen` dependency.

#### [MODIFY] [build.gradle.kts](file:///C:/Users/pnanc/AndroidStudioProjects/TN_Bet/app/build.gradle.kts)
- Include the splash screen library.

#### [NEW] [splash.xml](file:///C:/Users/pnanc/AndroidStudioProjects/TN_Bet/app/src/main/res/values/splash.xml)
- Define a `Theme.App.Starting` theme that inherits from `Theme.SplashScreen`.

#### [NEW] [ic_splash_sports.xml](file:///C:/Users/pnanc/AndroidStudioProjects/TN_Bet/app/src/main/res/drawable/ic_splash_sports.xml)
- A sports-themed icon (e.g., a football with the Tennessee Tristar).

#### [MODIFY] [MainActivity.kt](file:///C:/Users/pnanc/AndroidStudioProjects/TN_Bet/app/src/main/java/com/example/tn_bet/MainActivity.kt)
- Call `installSplashScreen()` in `onCreate`.
- Implement a custom exit animation (e.g., scaling or rotating the sports icon).

---

### 4. Font Color Integration

#### [MODIFY] [Type.kt](file:///C:/Users/pnanc/AndroidStudioProjects/TN_Bet/app/src/main/java/com/example/tn_bet/ui/theme/Type.kt)
- Ensure default typography uses `TNBlue` for body text in light mode and `TNWhite` in dark mode.

## Verification Plan

### Automated Tests
- Build the app to ensure no resource conflicts or compilation errors.

### Manual Verification
1. **Launch App:** Verify the splash screen appears with the sports animation.
2. **Check Logo:** Confirm the app icon on the home screen reflects the Tennessee flag.
3. **Verify Colors:** Inspect the UI to ensure font and component colors match the Tennessee palette.
