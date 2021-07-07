$Env:PORT="8080"
$Env:DATASOURCE_URL="jdbc:mysql://localhost:3306/HHTEDDB"
$Env:DATASOURCE_USERNAME="DHBRED"
$Env:DATASOURCE_PASSWORD="develop"
./gradlew  employee-directory-services:bootRun