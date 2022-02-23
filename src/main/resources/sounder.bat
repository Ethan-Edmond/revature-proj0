@echo off
set idArg=%1
set pathArg=%2
set namePrefix=DreamClockSounder
set taskName=%namePrefix%%idArg%
set buildPath=%pathArg%\target
cd %buildPath%
java -jar Sounder-jar-with-dependencies.jar %idArg% %taskName%
pause