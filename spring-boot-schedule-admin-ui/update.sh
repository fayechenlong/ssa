echo '--start build--'
npm run build

echo '-delete resources file-'
rm -rf ../spring-boot-schedule-admin-autoconfigure/src/main/resources/templates/*
rm -rf ../spring-boot-schedule-admin-autoconfigure/src/main/resources/META-INF/resources/ssa/*

echo '--copy file to ui resource--'

cp ./dist/index.html ../spring-boot-schedule-admin-autoconfigure/src/main/resources/templates/ssa.html
cp -R ./dist/. ../spring-boot-schedule-admin-autoconfigure/src/main/resources/META-INF/resources/ssa
