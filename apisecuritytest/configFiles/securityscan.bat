@echo on
echo Welcome to StackHowTo!
echo %USERPROFILE%\%POSTMAN_SCANS_RESULTS%
C:
START "Test" newman run %POSTMAN_COLLECTION% -e %POSTMAN_SCANS_DIR%\PostmanEnv.json -r csv --reporter-csv-export %POSTMAN_SCANS_RESULTS%
Exit /B 5
echo Welcome to StackHowTo2!
