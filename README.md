# ![BSide Project](https://drive.google.com/file/d/1M_T7CeZ-pmHzxKE_80cQw9RW0wu3E030/view?usp=sharing)

## Overview

This project is a multiplatform application built using Kotlin, Java, and various other technologies. It supports Android, iOS, desktop, and web platforms. This README provides detailed instructions on setting up the development environment, installing necessary technologies, and building the project.

## Prerequisites

### Development Environment

For the best development experience, it is recommended to have both Android Studio and XCode installed.

- **Android Studio**: This IDE is essential for Android development. It provides tools for building, testing, and debugging Android applications.
  - **Installation**: Download and install from the [official website](https://developer.android.com/studio).

- **XCode**: This IDE is required for iOS development. It includes tools for designing, coding, and testing iOS applications.
  - **Installation**: Download and install from the [Mac App Store](https://apps.apple.com/us/app/xcode/id497799835?mt=12).

### Technologies

- **Kotlin**: This is the primary programming language used in the project. It is a modern, expressive, and powerful language that runs on the JVM.
    - **Installation**: Use SDKMAN to install Kotlin. See the [SDKMAN](#sdkman) section for more details.

### SDKMAN

SDKMAN is a tool for managing parallel versions of multiple Software Development Kits (SDKs) on most Unix-based systems. It is used to install and manage JVM items like Java and Kotlin.

#### Installation

```sh
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
```

#### Installing JVM Items

- **Java**:
  ```sh
  sdk install java 11.0.24-tem
  ```

- **Kotlin**:
  ```sh
  sdk install kotlin 2.0.10
  ```
  
- **Gradle**:
  ```sh
  sdk install gradle 8.10
  ```

### kdoctor

`kdoctor` is a tool to check if your system is prepared for Kotlin Multiplatform development.

#### Installation

- **Homebrew**:
  ```sh
  brew install kdoctor
  ```

- **Manual Installation**: Follow the instructions on the [kdoctor GitHub repository](https://github.com/Kotlin/kdoctor).

#### Running kdoctor

```sh
kdoctor
```

### direnv

`direnv` is a tool that allows you to automatically load and unload environment variables based on the directory you are in. It is used to manage environment variables for this project.

I really advocate using [direnv](https://direnv.net/) to manage SDKMan and NVM installs with a `.envrc` in a project root. To get the most out of `direnv`, create and add the following to a `~/.direnvrc` file, either `~/.direnv` or `~/.config/direnv/direnvrc` i.e.:
If on MacOs, you can install direnv via Homebrew. Other OS, [see the direnv installation page.](https://direnv.net/docs/installation.html)

#### Installation

- **macOS**: Install via Homebrew
  ```sh
  brew install direnv
  ```

- **Linux**: Install via package manager (e.g., apt for Debian/Ubuntu)
  ```sh
  sudo apt install direnv
  ```

- **Windows**: Install via Chocolatey
  ```sh
  choco install direnv
  ```

### .envrc File

The `.envrc` file contains environment variables and configurations required for the project. Here is an example of what it might look like:

```sh
export JAVA_HOME="$HOME/.sdkman/candidates/java/current"
export ANDROID_HOME="$HOME/Library/Android/sdk"
export PATH="$ANDROID_HOME/emulator:$ANDROID_HOME/tools:$ANDROID_HOME/tools/bin:$ANDROID_HOME/platform-tools:$PATH"
```

Then add your `.direnvrc` and `.envrc` files:

```shell
cat /dev/null > ./.direnvrc
cat <<EOF >> ./.direnvrc
use_sdk() {
  [[ -s "${SDKMAN_DIR}/bin/sdkman-init.sh" ]] && source "${SDKMAN_DIR}/bin/sdkman-init.sh"

  while (( "$#" >= 2 )); do
    local candidate=$1
    local candidate_version=$2
    SDK_OFFLINE_MODE=true sdk use $candidate $candidate_version

    shift 2
  done
}

use_nvm() {
  local node_version=$1

  nvm_sh=~/.nvm/nvm.sh
  if [[ -e $nvm_sh ]]; then
    source $nvm_sh
    nvm use $node_version
  fi
}
EOF
# Place something like the following in your `.envrc`:

cat <<EOF >> ./.envrc
# maybe set some ENV vars too
export BSIDE_HOME=`pwd`

use sdk java 11.0.24-tem
use sdk gradle 8.10
use sdk kotlin 2.0.10
EOF
```

To allow direnv, run this from the root where your `.envrc` file is:

```shell
direnv allow .
```

## Building the Project

### Gradle

Gradle is used to build the project. Ensure you have Gradle installed or use the Gradle wrapper included in the project.

#### Building the Project

```sh
./gradlew build
```

### Running the BSide App

The BSide app can be run on different platforms using Gradle tasks. Here are the commands for each platform:

- **Android**:
  ```sh
  gradle :composeApp:installDebug
  gradle :composeApp:runDebug
  ```

- **iOS**:
  ```sh
  gradle :composeApp:iosDeploy
  ```

- **Desktop**:
  ```sh
  gradle :composeApp:run
  ```

- **Web**:
  ```sh
  gradle :composeApp:wasmJsBrowserRun
  ```

### Running in IDE

You can also run the app directly from your IDE (e.g., Android Studio) by selecting the appropriate run configuration for the target platform.
These are configured in the top-middle-right of the IDE window. Where you will see a dropdown with the name of the project, click on it and select the target platform you want to run.
For example:
- **Android**:
  - Select `composeApp` > `Run 'composeApp'`
- **iOS**:
  - Select `iOSApp` > `Run 'iOSApp'`
- You will need to configure desktop and web run configurations manually:
- **Desktop**:
  - From the dropdown, select `Edit Configurations...`. In this window, click the `+` button in the top-left corner and select `Gradle`. In the `Name` field, enter something semantic like: `BSide Desktop Run [:composeApp:run]`. In the `Tasks` field, enter `:composeApp:run`. Click `OK` to save the configuration. Now you can select `BSide Desktop Run [:composeApp:run]` from the dropdown and click the green play button to run the desktop app.
- **Web**:
  - Similarly to desktop, for web WASM, from the dropdown, select `Edit Configurations...`. In this window, click the `+` button in the top-left corner and select `Gradle`. In the `Name` field, enter something semantic like `BSide Web WASM Run [:composeApp:wasmJsBrowserRun]`. In the `Tasks` field, enter `:composeApp:wasmJsBrowserRun`. Click `OK` to save the configuration. Now you can select `BSide Web WASM Run [:composeApp:wasmJsBrowserRun]` from the dropdown and click the green play button to run the desktop app.

## Conclusion

This README provides a comprehensive guide to setting up the development environment, installing necessary technologies, and building the BSide project. Follow the instructions carefully to ensure a smooth development experience.