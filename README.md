![LOGO](https://github.com/touchlane/Bootstrap-Android/blob/master/logo.svg)

# Android Bootstrap

A project fostering the development of an Android app from scratch by setting up and configuring some of the basic things an Android project might need.

## Description

The existing git branches apart from `master` and `develop` provide a sample 2-screen app implementation using a specific tech stack to enforce usage of certain practices, solutions and patterns.

#### Project structure

This template uses the default Android Studio project structure with modular approach. It is organized with Clean Architecture principles in mind. The project uses 3 gradle modules: `data` (android module), `domain` (java module) and `presentation` (android module).

#### Static analysis and formatting

By default the project uses [Ktlint](https://github.com/pinterest/ktlint) to analyze the code. You can use `ktlintCheckAll` gradle task to run the `ktlintCheck` task for all modules at once. You can utilize Ktlint's code formatting as well.

#### Test coverage

By default the project uses [JaCoCo](https://www.eclemma.org/jacoco/) to analyze test coverage. `jacocoTestReports` gralde task can be used to generate reports for each module at once.

#### CI

GitHub Workflows with a pre-configured Android CI lane is used by default. Builds are triggered on pull-requests and include running unit tests, performing code analysis and runnning Android instrumented tests on emulated devices.

## How to use

1. Clone this repository:

```bash
git clone https://github.com/touchlane/Bootstrap-Android.git
```

2. Execute the `rename_project.sh` script, supplying the new app id (package), the new app name and the new gradle project root name as arguments:

```bash
./rename_project.sh "my.company.name.app" "My App" "my-app"
```

3. Remove the `.git` folder:

```bash
rm -rf .git
```

4. Create a new git repository:

```bash
git init
git add .
git commit -m 'Initial commit'
git push
```
Alternatively, you can click `Use this template` button on the GitHub page and then run the `rename_project.sh` script as described above.
