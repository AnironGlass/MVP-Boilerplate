# Android Project Boilerplate

Boilerplate for new Android app based on [Ribot](https://github.com/ribot/android-boilerplate) and [Google](https://github.com/googlesamples/android-architecture/) projects and some common sense

Libraries and tools included:

- Support libraries
- RecyclerView, CardView, ConstraintLayout, Design and Annotations
- [RxJava](https://github.com/ReactiveX/RxJava), [RxAndroid](https://github.com/ReactiveX/RxAndroid) and [RxLifecycle](https://github.com/trello/RxLifecycle)
- [Retrolambda](https://github.com/orfjackal/retrolambda) with [Gradle Retrolambda Plugin](https://github.com/evant/gradle-retrolambda)
- [Retrofit 2](http://square.github.io/retrofit/)
- [Dagger 2](http://google.github.io/dagger/)
- [SqlBrite](https://github.com/square/sqlbrite)
- [Butterknife](https://github.com/JakeWharton/butterknife)
- [Timber](https://github.com/JakeWharton/timber)
- [Glide](https://github.com/bumptech/glide)
- [LeakCanary](https://github.com/square/leakcanary)
- Functional tests with [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/index.html)
- [Robolectric](http://robolectric.org/)
- [Mockito](http://mockito.org/)
- [Truth](https://github.com/google/truth)
- [Checkstyle](http://checkstyle.sourceforge.net/), [PMD](https://pmd.github.io/) and [Findbugs](http://findbugs.sourceforge.net/) for code analysis
- [Dexcount Gradle Plugin](https://github.com/KeepSafe/dexcount-gradle-plugin)
- Checks for dependencies updates with [Gradle Versions Plugin](https://github.com/ben-manes/gradle-versions-plugin)

## Requirements

- JDK 1.8
- Android SDK
- Android Nougat (API 25)
- Latest Android SDK Tools and build tools

## Architecture

This project follows ribot's Android architecture guidelines that are based on [MVP (Model View Presenter)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter). Read more about them [here](https://github.com/ribot/android-guidelines/blob/master/architecture_guidelines/android_architecture.md). 

![](https://github.com/ribot/android-guidelines/raw/master/architecture_guidelines/architecture_diagram.png)

### How to implement a new screen following MVP

Imagine you have to implement a sign in screen. 

1. Create a new package under `ui` called `signin`
2. Create an new Activity called `ActivitySignIn`. You could also use a Fragment.
3. Define the view interface that your Activity is going to implement. Create a new interface called `SignInMvpView` that extends `MvpView`. Add the methods that you think will be necessary, e.g. `showSignInSuccessful()`
4. Create a `SignInPresenter` class that extends `BasePresenter<SignInMvpView>`
5. Implement the methods in `SignInPresenter` that your Activity requires to perform the necessary actions, e.g. `signIn(String email)`. Once the sign in action finishes you should call `getMvpView().showSignInSuccessful()`.
6. Create a `SignInPresenterTest`and write unit tests for `signIn(email)`. Remember to mock the  `SignInMvpView` and also the `DataManager`.
7. Make your  `ActivitySignIn` implement `SignInMvpView` and implement the required methods like `showSignInSuccessful()`
8. In your activity, inject a new instance of `SignInPresenter` and call `presenter.attachView(this)` from `onCreate` and `presenter.detachView()` from `onDestroy()`. Also, set up a click listener in your button that calls `presenter.signIn(email)`.

## Code Quality

This project integrates a combination of unit tests, functional test and code analysis tools. 

### Tests

To run **unit** tests on your machine:

``` 
./gradlew test
``` 

To run **functional** tests on connected devices:

``` 
./gradlew connectedAndroidTest
``` 

Note: For Android Studio to use syntax highlighting for Automated tests and Unit tests you **must** switch the Build Variant to the desired mode.

### Code Analysis tools 

The following code analysis tools are set up on this project:

* [PMD](https://pmd.github.io/): It finds common programming flaws like unused variables, empty catch blocks, unnecessary object creation, and so forth. See [this project's PMD ruleset](config/quality/pmd/pmd-ruleset.xml).

``` 
./gradlew pmd
```

* [Findbugs](http://findbugs.sourceforge.net/): This tool uses static analysis to find bugs in Java code. Unlike PMD, it uses compiled Java bytecode instead of source code.

```
./gradlew findbugs
```

* [Checkstyle](http://checkstyle.sourceforge.net/): It ensures that the code style follows [our Android code guidelines](https://github.com/ribot/android-guidelines/blob/master/project_and_code_guidelines.md). See our [checkstyle config file](config/quality/checkstyle/checkstyle-config.xml).

```
./gradlew checkstyle
```

### The check task

To ensure that your code is valid and stable use check: 

```
./gradlew check
```

This will run all the code analysis tools and unit tests in the following order:

![Check Diagram](images/check-task-diagram.png)
 
### Checks for dependencies updates

To check for dependencies updates use following task:

```
./gradlew dependencyUpdates
```

## New project setup 

To quickly start a new project from this boilerplate follow the next steps:

* Download this [repository as a zip](https://github.com/AnironGlass/MVP-Template/archive/master.zip).
* Change the package name. 
  * Rename packages in `androidTest`, `commonTest`, `main`, `main/src/debug` and `test` using Android Studio.
  * In `app/build.gradle` file, `applicationId`.
  * In `src/main/AndroidManifest.xml` and `src/debug/AndroidManifest.xml`.
* Change MainActivity activity label in `src/res/values/strings.xml`.
* Change MainActivity and LeakCanary labels in `src/debug/res/values/strings.xml`.
* Create a new git repository, [see GitHub tutorial](https://help.github.com/articles/adding-an-existing-project-to-github-using-the-command-line/).
* Replace the example code with your app code following the same architecture.
* Update `proguard-rules.pro` to keep models (see TODO in file) and add extra rules to file if needed.
* Update README with information relevant to the new project.
* Update LICENSE to match the requirements of the new project.

## TODO list

* Add [Moxy](https://github.com/Arello-Mobile/Moxy)
* Replace SyncService with JobScheduler or GCM Network Manager
* Add AutoValue
* Upgrade ReactiveX to 2.x
* Add splash activity

## License

```
    Copyright 2016 AnironGlass.

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

