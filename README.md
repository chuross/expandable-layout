# ExpandableLayout

ExpandableLayout can expand / collapse child views.

![](http://i.imgur.com/nY4tA5t.gif)

[demo](https://www.youtube.com/watch?v=e7WaAzMRvzA)

# Gradle

```
repositories {
    maven {
        url 'http://chuross.github.com/maven-repository/'
    }
}

dependencies {
    compile 'com.github.chuross:expandablelayout:1.0.3'
}
```

# Usage

ExpandableLayout has sample project.

## In your layout

```xml
<com.github.chuross.library.ExpandableLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    <!-- any child views -->
</com.github.chuross.library.ExpandableLayout>
```

## XML Attributes

|name|type|description|etc|
|:---|:---|:---|:---|
|exl_collapseHeight|dimention|The height of View when ExpandableLayout is collapse|exl_collapse_heighta and exl_collapseTargetId can not be mixed|
|exl_collapseTargetId|reference|To automatically calculate the height to collapse from child view id||
|exl_collapsePadding|dimention|||
|exl_duration|integer|duration for animate||
|exl_expanded|boolean|If true, ExpandableLayout expand when view is created.|default false|

## expand / collapse

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ExpandableLayout expandableLayout = (ExpandableLayout)findViewById(R.id.layout_expandable);

    expandableLayout.expand();// expand with animation
    expandableLayout.collapse();// collapse with animation

    expandableLayout.expand(false);// expand without animation
    expandableLayout.collapse(false);// collapse without animation
}
```

# License

```
Copyright 2015 chuross

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
