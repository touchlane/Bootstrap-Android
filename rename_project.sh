#!/bin/bash
# 1) Create the new package in all modules and sourcesets
# 2) Move all files
# 3) Delete all empty packages
# 4) Replace all old package text occurencies
# 5) Change the app name
# 6) Change the project name (e.g. in the root build.gradle file)
#
# Arguments:
# $1 - the new package, e.g. "com.company.product"
# $2 - the new app name, the default is "Android Bootstrap"
# $3 - the new gradle project name, the default is "android-bootstrap"

# 0) Check that the arguments are supplied

if [ $# -ne 3 ]
  then
    echo "Can't rename the project. Please, pass the package name, the app name and the project name as arguments"
    exit 1
fi

# 1) Create the new package in all modules and sourcesets

createPackage() {
  IFS='.' read -ra PACKAGES <<< "$1"
  for i in "${PACKAGES[@]}"; do
    mkdir "$i"
    cd "$i"
  done
  for i in "${PACKAGES[@]}"; do
    cd "../"
  done
  pwd
}

cd "presentation/src/androidTest/java"
createPackage $1
cd "../../../../"

cd "presentation/src/main/java"
createPackage $1
cd "../../../../"

cd "presentation/src/test/java"
createPackage $1
cd "../../../../"

cd "data/src/test/java"
createPackage $1
cd "../../../../"

cd "data/src/main/java"
createPackage $1
cd "../../../../"

cd "domain/src/test/kotlin"
createPackage $1
cd "../../../../"

cd "domain/src/main/kotlin"
createPackage $1
cd "../../../../"

# 2) Move all files

# Just convert "com.company.product" to "com/company/product"

newpackage=$1
newdir=${newpackage//.//}

# Move the files

mv -v "presentation/src/androidTest/java/com/touchlane/android/bootstrap/"* "presentation/src/androidTest/java/$newdir"

mv -v "presentation/src/main/java/com/touchlane/android/bootstrap/"* "presentation/src/main/java/$newdir"

mv -v "presentation/src/test/java/com/touchlane/android/bootstrap/"* "presentation/src/test/java/$newdir"

mv -v "data/src/main/java/com/touchlane/android/bootstrap/"* "data/src/main/java/$newdir"

mv -v "data/src/test/java/com/touchlane/android/bootstrap/"* "data/src/test/java/$newdir"

mv -v "domain/src/main/kotlin/com/touchlane/android/bootstrap/"* "domain/src/main/kotlin/$newdir"

mv -v "domain/src/test/kotlin/com/touchlane/android/bootstrap/"* "domain/src/test/kotlin/$newdir"

# 3) Delete all empty packages

find . -type d -empty -delete

# 4) Replace all old package text occurencies

find . -type f \( -name "*.xml" -o -name "*.java" -o -name "*.kt" -o -name "*.gradle" -o -name "*.kts" \) -exec sed -i "s/com.touchlane.android.bootstrap/$1/g" {} +

# 5) Change the app name

find . -type f \( -name "*.xml" -o -name "*.java" -o -name "*.kt" -o -name "*.gradle" -o -name "*.kts" \) -exec sed -i "s/Android Bootstrap/$2/g" {} +

# 6) Change the project name (e.g. in the root build.gradle file)

find . -type f \( -name "*.xml" -o -name "*.java" -o -name "*.kt" -o -name "*.gradle" -o -name "*.kts" \) -exec sed -i "s/android-bootstrap/$3/g" {} +
