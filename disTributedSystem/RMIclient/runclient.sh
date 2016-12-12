#!/bin/sh
#write a shell script to start the client
#Question (4.4)

cd `dirname $0`
base=`pwd`

[ ! -d "bin" ] && mkdir bin

# Compile
javac -d bin/ -sourcepath ./src src/CO3090/assignment2/client/RFSClient.java

# Build jar
pushd bin
jar cvf ../client.jar CO3090/assignment2/*.class CO3090/assignment2/client/*.class
popd

# Run client
java -cp ./bin/ -Djava.rmi.server.codebase=file:$base/client.jar -Djava.rmi.server.useCodebaseOnly=false -Djava.security.policy=file:$base/policy.permission CO3090.assignment2.client.RFSClient
