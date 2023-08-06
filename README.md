# SearchableSpinnerDialog

## Getting Started

### Prerequisites

You must add **maven { url 'https://jitpack.io' }** to settings.gradle as below.
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }  //Add this
    }
}
```
Or you can add to build.gradle(app) as below.
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' } //Add this
  }
}
```

You must implement library as below.
```gradle
dependencies{
  ...
  implementation 'com.github.emreaytac1990:SearchableSpinnerDialog:1.0'
}
```

## Example
```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = this.findViewById(R.id.helloWorld) as TextView

        val mList = arrayListOf(User("John",12), User("Michael",13), User("Jose",36), User("Florent",33), User("Sergio",22), User("Amanda",24), User("Adele",31), User("Catherine",52), User("Chris",12), User("Josh",35))
        val strList: List<String> = mList.map { i->  i.name }.toList()
        val dialog = SearchableSpinnerDialog(ctx = this, searchList = Pair(strList, mList), widthRate = 0.8, heightRate = 0.8, separatorColor = com.emreaytac.searchablespinnerdialog.R.color.black, searchHintText = "Search")

        tv.setOnClickListener {
            dialog.openDialog()
        }
        dialog.selectedItem.observe(this){ item ->
            tv.text = item.text
        }
    }
}

data class User(val name: String, val age: Int)
```
