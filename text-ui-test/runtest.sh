#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../out" ]
then
    mkdir ../out
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.txt
fi

# delete save.txt if still exists
if [ -e "data/save.txt" ]
then
    rm data/save.txt
fi

# delete data directory if still exists
if [ -e "data" ]
then
    rmdir data
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../out ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.txt
java -classpath ../out Duke < input.txt > ACTUAL.txt

# convert to UNIX format
cp EXPECTED.txt EXPECTED-UNIX.txt
dos2unix ACTUAL.txt EXPECTED-UNIX.txt

# compare the output to the expected output
diff ACTUAL.txt EXPECTED-UNIX.txt
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi