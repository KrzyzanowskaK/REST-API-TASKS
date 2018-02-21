call runcrud.bat
if "%ERRORLEVEL%" == "0" goto browser
echo.
echo Script is not running.
goto fail


:browser
start chrome "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Browser is not working.
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished