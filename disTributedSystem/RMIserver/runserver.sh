#!/bin/sh
#write a shell script to start the RMI server
#Question (4.4)

cd `dirname $0`
base=`pwd`

[ ! -d "bin" ] && mkdir bin

# Compile
javac -d bin/ -sourcepath ./src src/CO3090/assignment2/server/RFSServer.java

# Build jar
pushd bin
jar cvf ../server.jar CO3090/assignment2/*.class CO3090/assignment2/server/*.class
popd

# Run server
java -cp ./bin/ -Djava.rmi.server.codebase=file:$base/server.jar -Djava.rmi.server.useCodebaseOnly=false -Djava.security.policy=file:$base/policy.permission CO3090.assignment2.server.RFSServer
