fun main(
    showBoard()
)


fun resetBoard(): Unit{
    board=Array(size:3) {Array(size:3) {EMPTY_SPACE}}
}

fun showBoard(): {
    var num=0

    board.forEach{row->
        row.forEach { space ->
            print(
                "|${
                    if (space == EMPTY_SPACE) {
                        num.toString()
                    } else {
                        space
                    }
                }"
            )
            num++
        }
                    println("|")
    }
}

<activity
android:name=".MainActivity"
android:label="YourAppName"
android:theme="@style/AppTheme.NoActionBar" >
<intent-filter>
<action android:name="android.intent.action.MAIN" />

<category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
</activity>
