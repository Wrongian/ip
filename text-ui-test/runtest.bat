@ECHO OFF

REM create out directory if it doesn't exist
if not exist ..\out mkdir ..\out

REM delete output from previous run
if exist ACTUAL.txt del ACTUAL.txt

REM compile the code into the out folder
javac  -cp ..\src\main\java -Xlint:none -d ..\out ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\out Demacia < input.txt > ACTUAL.txt

REM compare the output to the expected output
FC ACTUAL.txt EXPECTED.txt
