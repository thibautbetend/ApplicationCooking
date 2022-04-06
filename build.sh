#!/bin/sh


requiredDirectories="backend frontend"

for directory in $requiredDirectories ; do
  if [ ! -d "$directory" ]; then
    echo "Cannot find the $directory directory, aborting build.."
    exit 1
  fi
done

cd frontend || exit
echo 'Resolving dependencies... 🔎'
npm install > ../front-buildinfo.log 2>&1
if [ $? -ne 0 ]; then
  echo 'Unable to resolve dependencies, check the "front-buildinfo.log" file for more information'
  exit 2
fi

echo "🧰 Building frontend 🧰"
npm run build >> ../front-buildinfo.log 2>&1
if [ $? -ne 0 ]; then
  echo 'Unable to build front-end, check the "front-buildinfo.log" file for more information'
  exit 3
fi


echo "🧰 Building backend 🧰"
cd ../backend || exit
mvn clean package > ../back-buildinfo.log 2>&1
if [ $? -ne 0 ]; then
  echo 'Unable to build back-end, check the "back-buildinfo.log" file for more information'
  exit 4
fi

echo
echo 'The application has been successfully built and is available under the "CookieTheWorld" directory'
echo 'To run it, simply launch this command: java -jar CookingTheWorld.jar'
echo 'Enjoy ! 🍞 🍽'
