# TabbedViewPager

AppBar + SlidingTabLayout + ViewPager

##安裝 - Installation#

使用 Android Studio 開發， Android 2.2+

在 build.gradle 的 dependencies 中加入
```gradle
dependencies{
  compile 'libs.ben:tabbed-viewpager:1.2.0'
}
```

注意： gradle 中 repositories 需設定為 jcenter
```gradle
repositories {
  jcenter()
}
```
## 使用 - Usage

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="status_bar_color">#ffff0000</color>
    <color name="app_bar_color">@color/status_bar_color</color>
    <color name="tab_bar_color">@color/status_bar_color</color>
    <color name="navigation_bar_color">@color/status_bar_color</color>
    <color name="tab_bar_indicator_color">#ffffffff</color>
    <color name="window_background_color">#ffffffff</color>
    
    <dimen name="tabbar_elevation_height">3dp</dimen>
</resources>
```
詳細使用範例請參考 sample module
