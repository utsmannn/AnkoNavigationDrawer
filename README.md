# Anko Navigation Drawer
[ ![Download](https://api.bintray.com/packages/kucingapes/kucingapes/com.utsman.kucingapes/images/download.svg) ](https://bintray.com/kucingapes/kucingapes/com.utsman.kucingapes/_latestVersion)

Easy implementation Navigation Drawer in [Anko Layout](https://github.com/Kotlin/anko/) support **New Material Design**, **multi styles** and **custom icon/drawable navigation**

|*DEFAULT STYLE*|*NEW MATERIAL STYLE*|*GOOGLE KEEP STYLE*|
|--|--|--|
|![](https://image.ibb.co/bLwjPf/Screenshot-Anko-Navigation-Drawer-20181115-230204.png)|![](https://image.ibb.co/kxEhAL/Screenshot-Anko-Navigation-Drawer-20181115-230252.png)|![](https://image.ibb.co/j70Hc0/Screenshot-Anko-Navigation-Drawer-20181115-230341.png)|

## SETUP
### 1. Add gradle dependency
```gradle
implementation 'com.utsman.kucingapes:ankodrawer:0.2.4'

//required anko libraries
implementation "org.jetbrains.anko:anko:$anko_version"
implementation "org.jetbrains.anko:anko-design:$anko_version"
```

### 2. Add drawer in ```onCreate```
Implement ```AnDrawerClickListener``` on your activity class.
Add after ```super.onCreate(savedInstanceState)```
```kotlin
val drawer = AnDrawer(clickListener, colorDrawer)
frameLayout { anDrawerLayoutWithToolbar(drawer) }
```

Example
```kotlin
class MainActivity : AppCompatActivity(), AnDrawerClickListener {
    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        
        // code your drawer
        val drawer = AnDrawer(this, R.color.colorPrimary)
        frameLayout { anDrawerLayoutWithToolbar(drawer) }
    }
}
```

### 3. Add MainUI class
- **Don't apply MainUI with ```MainUI().setContentView(this)```**
- **Extend MainUI with ```AnkoComponent<ViewGroup>```**

Example
```kotlin
class MainUi : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        coordinatorLayout {
            relativeLayout {
                textView("MAIN VIEW").lparams { centerInParent() }
            }.lparams(matchParent, matchParent)
        }
    }
}
```
<br>

Add after ```frameLayout { anDrawerLayoutWithToolbar(drawer) }```

```kotlin
AnDrawerInit.setupMainView(this, MainUi())
```

## Items Navigation
**Simple**
```kotlin
val item = AnDrawerItem("Title item")
drawer.addItems().add(item)
```

**Divider**
```kotlin
val divider = AnDrawerItem(AnDrawerItem.DIVIDER)
drawer.addItems().add(divider)
```

### Optional <br>
**- Add icon (very recommended)**
```kotlin
val item = AnDrawerItem("Title item").addIcon(R.drawable.ic_face)
```

**- Add identifier (very recommended)**
```kotlin
val item = AnDrawerItem("Title item").addIdentifier(1)
```

**- Disable focus**<br>
When item disable focus, item can't be highlight on click, suitable with intent
```kotlin
val item = AnDrawerItem("Title item").setFocusable(false)
```

**- Selected item**<br>
Select item on first launch app, need identifier on item
```kotlin
drawer.setSelectedItem(identifier)
```

Example
```kotlin
drawer.addItems().apply {
    val item1 = AnDrawerItem("Item 1")
        .addIcon(R.drawable.ic_emoticon)
        .addIdentifier(1)

    val item2 = AnDrawerItem("Item 2")
        .addIcon(R.drawable.ic_face)
        .addIdentifier(2)
        
    val divider = AnDrawerItem(AnDrawerItem.DIVIDER)
    
    add(divider)
    add(item1)
    add(item2)
    drawer.setSelectedItem(2)
}
```

## Item Clicked
Override function ```onDrawerClick``` from ```AnDrawerClickListener``` and setup click listener with identifier item
```kotlin
override fun onDrawerClick(identifier: Int) {
    super.onDrawerClick(identifier)
    when (identifier) {
        1 -> toast("wah")
        2 -> toast("gile")
        3 -> toast("lu")
    }
}
```

## Custom Toolbar (very recommended)
1. Change style parent to ```Theme.AppCompat.Light.NoActionBar``` in styles.xml

2. Add ```toolbar``` or ```themedToolbar``` from ```org.jetbrains.anko.appcompat.v7``` in your MainUI class
```kotlin
class MainUi : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        coordinatorLayout {
        
            // toolbar
            themedToolbar(R.style.ThemeOverlay_AppCompat_Dark) {
                backgroundColorResource = R.color.colorPrimary
                id = R.id.toolbar
                title = context.getString(R.string.app_name)
            }.lparams(matchParent, dimenAttr(R.attr.actionBarSize))
            // end toolbar
            
            relativeLayout {
                textView("MAIN VIEW").lparams { centerInParent() }
            }.lparams(matchParent, matchParent)
        }
    }
}
```
And add ```customToolbar(activity, toolbar)``` after ```setupMainView```
```kotlin
AnDrawerInit.setupMainView(this, MainUi())

// setup toolbar
AnDrawerInit.customToolbar(this, find(R.id.toolbar))
```

|Without custom toolbar|with custom toolbar|
|--|--|
|![](https://image.ibb.co/kEApPf/Screenshot-Anko-Navigation-Drawer-20181115-230645.png)|![](https://image.ibb.co/kxEhAL/Screenshot-Anko-Navigation-Drawer-20181115-230252.png)|

## Icon Navigation Toolbar
You can add custom toolbar with ```customToolbar(activity, toolbar, int_icon)``` or ```customToolbar(activity, toolbar, drawable)```
- **Add your own custom toolbar after ```setupMainView```**
```kotlin
AnDrawerInit.setupMainView(this, MainUi())
// your toolbar with custom icon
AnDrawerInit.customToolbar(this, find(R.id.toolbar), icon)
```

**SPEC ICON**
<br>
width = 24dp<br>
height = 24dp


or for **drawable**<br>
width = 30dp<br>
height = 30dp

You can find material icon in https://material.io/tools/icons/, convert svg to xml in http://inloop.github.io/svg2android/

**ICON DRAWABLE**
<br>I recommend use Picasso and Picasso Transformations for load and make it circle

In gradle
```gradle
implementation 'com.squareup.picasso:picasso:2.71828'
implementation 'jp.wasabeef:picasso-transformations:2.2.1'
```
Add ```customToolbar``` in **Target Picasso** after ```setupMainView```
```kotlin
AnDrawerInit.setupMainView(this, MainUi())
// get drawable and circle it using picasso
Picasso.get()
    .load(R.drawable.cat)
    .transform(CropCircleTransformation()) // transform to circle
    .into(object : Target {
        override fun onPrepareLoad(placeHolderDrawable: Drawable) {}

        override fun onBitmapFailed(e: Exception, errorDrawable: Drawable) {}

        override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
             
            // add drawable from bitmap
            val drawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, dip(30), dip(30), true))
            
            // add custom toolbar
            AnDrawerInit.customToolbar(this@MainActivity, find(R.id.toolbar), drawable)
            
        }

    })
```

|With default icon|With custom icon|With drawable icon|
|--|--|--|
|![](https://image.ibb.co/ivGXS0/image.png)|![](https://image.ibb.co/kZJhuf/image.png)|![](https://image.ibb.co/jRFLEf/image.png)|


## Header Navigation (optional)
You need anko class header with extend **```AnkoComponent<ViewGroup>```**, and setup with
```kotlin
AnDrawerInit.setupHeader(this, HeaderUi())
```

Example HeaderUI
```kotlin
class HeaderUi : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        relativeLayout {
            lparams(matchParent, dip(200))
            backgroundColorResource = R.color.colorPrimary
            textView("CUSTOM HEADER") {
                textColorResource = android.R.color.white
                typeface = Typeface.DEFAULT_BOLD
                textSize = 20f
            }.lparams { centerInParent() }
        }
    }
}
```

## Styles (optional)
Available style:
1. ```AnDrawerView.STYLE.DEFAULT```
2. ```AnDrawerView.STYLE.NEW_MATERIAL```
3. ```AnDrawerView.STYLE.GOOGLE_KEEP```

Setup style
```kotlin
drawer.setNavigationStyle(AnDrawerView.STYLE.GOOGLE_KEEP)
```

## Open and Close Drawer
```kotlin
AnDrawerInit.openDrawer(activity) // open drawer
AnDrawerInit.closeDrawer(activity) // close drawer 
```

## Drawer StatusBar Color
```kotlin
drawer.setDrawerStatusBar(int_color)
```

## Drawer Custom Font
```kotlin
drawer.setFont("fonts/GoogleSans-Medium.ttf") // font from assets
```

## Example
Example class [MainActivity.kt](https://github.com/kucingapes/AnkoNavigationDrawer/blob/master/app/src/main/java/com/kucingapes/ankonavigationdrawer/MainActivity.kt)

---
# END
