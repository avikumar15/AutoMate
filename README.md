## AutoMate

App code for an E-Rickshaw tracker system.

### Prerequisites

- Install latest version of Java

  Visit [here](https://www.oracle.com/java/technologies/downloads/) and locate the version of java which matches your need.

- Install Android SDK

  Follow one of the following methodology to set up your system for building android projects.

1. Installing manually

   Install the commandline tools from [here](https://developer.android.com/studio/index.html#command-tools) and sdkmanager from [here](https://developer.android.com/studio/command-line/sdkmanager).

2. Install Android Studio

   Download android studio from [here](https://developer.android.com/studio) and unpack .zip file and execute `studio.sh` and complete the installation steps. Check [here](https://developer.android.com/studio/install) if you are stuck anywhere.

- Install Emulator or Have a physical Android device for testing applications

### Project Installation

- Fork the repository

- Clone the repository: 

```
git clone <forked-repo-url>
```

- Add upstream remote to this repository

```
git remote add upstream <repo-url>
```

- Check if gradle builds

```
./gradlew
```

### Running the Project

- Connect your physical device using USB or launch AVD device using

```
cd $ANDROID_SDK/tools/emulator
./emulator -list-avds
./emulator -avd <AVD_NAME>
```

- Install Apks

```
* cd to respective project root folder *
./gradlew
./gradlew installDebug
```

- Run App

  Navigate to the phone menu and launch app for testing

Alternatively, you could also use the studio to run app.
