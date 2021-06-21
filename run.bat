@ECHO OFF
For /f "tokens=2-4 delims=/ " %%a in ('date /t') do (set mydate=%%c-%%a-%%b)
For /f "tokens=1-2 delims=/:" %%a in ('time /t') do (set mytime=%%a%%b)

cd %~dp0

call mvn clean test -DfolderPath=C:\Users\rockm\Desktop\Newfolder\ -DwaitBetweenUpload=1 > Log_%mydate%_%mytime%.txt