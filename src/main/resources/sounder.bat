@echo off
set idArg=%1
set pathArg=%2
set namePrefix=DreamClockSounder
set taskName=%namePrefix%%idArg%
schtasks /DELETE /TN %taskName% /F
set buildPath=%pathArg%\target
cd %buildPath%
java -jar Sounder-jar-with-dependencies.jar %idArg%
pause