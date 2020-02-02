#!/bin/bash
mvn -Dapp.url=https://localhost:5001/ -Dtest=FullTeachingTestE2EChat#oneToOneChatInSessionChrome,FullTeachingTestE2EVideoSession#oneToOneVideoAudioSessionChrome,LoggedVideoSession#sessionTest,FullTeachingTestE2EREST#attendersRestOperations -B -DforkCount=0 test

