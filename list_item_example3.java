<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="40dp"
    android:orientation="horizontal"
    android:showDividers="middle"
    android:divider="?android:dividerVertical"
    android:dividerPadding="8dp"

    android:background="#d3020202"
    android:weightSum="1"
    android:focusable="false"
    android:focusableInTouchMode="false">
    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#00ffffff"
        android:focusable="false"
        android:focusableInTouchMode="false">
        <!-- Dummy text view that will display the name of a random country. -->

        <TextView android:id="@+id/wordName"
            android:layout_width="wrap_content"
            android:layout_marginTop="2dp"
            android:layout_marginLeft="30dp"

            android:layout_height="wrap_content"
            android:textSize="20sp"
            android:typeface="sans"
            android:textColor="#ffffff"
            android:width="170dp"
            android:text="12:50"
            android:shadowColor="#ffdddddd"
            android:focusable="false"
            android:focusableInTouchMode="false"/>
        <Switch
            android:layout_width="50dp"
            android:layout_height="20dp"
            android:layout_marginLeft="250dp"
            android:id="@+id/switchButton"
            android:checked="true"
            android:layout_alignParentRight="true"
            android:layout_marginRight="20dp"/>
    </RelativeLayout>
</LinearLayout>
