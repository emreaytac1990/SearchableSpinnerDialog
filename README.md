# SearchableSpinnerDialog

## Getting Started

### Prerequisites

You must add **maven { url 'https://jitpack.io' }** to settings.gradle as below.
```
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
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' } //Add this
  }
}
```

You must implement library as below.
```
dependencies{
  ...
  implementation 'com.github.emreaytac1990:SearchableSpinnerDialog:1.0'
}
```

