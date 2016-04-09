#!/bin/bash

CURRENT_DIR=`pwd`

CLASSPATH=${CURRENT_DIR}/${artifactId}-${version}.jar:${CURRENT_DIR}/configuration:${CURRENT_DIR}/lib/*

java -cp $CLASSPATH ${mail.server} com.kron.ftp.Main $1