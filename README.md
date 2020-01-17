# labeled-floating-action-button
You can put both icon and text into a FloatingActionButton with this library.
The LabeledFloatingActionButton is built upon a original FloatingActionButton so that you can replace it easily.

![Demo](screenshot/FAB_sample.png)

## Installation
Step 1. Add the JitPack repository to your root build.gradle at the end of repositories:
```
  allprojects {
	  repositories {
		  ...
		  maven { url 'https://jitpack.io' }
	  }
  }
```
Step 2. Add the dependency
```
  dependencies {
    implementation 'com.github.hseung:labeled-floating-action-button:0.0.1'
  }
```

## Usage
Add the `com.buzzvil.labeledfab.LabeledFloatingActionButton` to your layout XML file, instead of `com.google.android.material.floatingactionbutton.FloatingActionButton`.

```
  <com.buzzvil.labeledfab.LabeledFloatingActionButton
    android:id="@+id/fab"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="bottom|end"
    android:layout_margin="@dimen/fab_margin"
    app:maxImageSize="46dp"
    app:fabCustomSize="72dp"
    app:labeledFabText="Mail"
    app:labeledFabTextColor="@android:color/white"
    app:labeledFabTextSize="18dp"
    app:labeledFabTextPadding="1dp"
    app:labeledFabTextPosition="bottom"
    app:labeledFabIcon="@android:drawable/ic_dialog_email"
    />
```

It is strongly recommended to adjust "maxImageSize" attribute in FAB because the default size is not sufficient to render a icon and a title at once.

### Properties
- labeledFabText: Text to put in a FAB
- labeledFabTextColor: Text color
- labeledFabTextSize: Text size
- labeledFabTextPadding: Padding between the icon and the text
- labeledFabTextPosition: Position of the text relative to the icon, it can be "top" or "bottom"
- labeledFabIcon: Resource for the icon

Please refer to the sample app for more detailed usage including programmatic api

