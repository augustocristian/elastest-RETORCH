#!/bin/bash
mvn -Dapp.url=https://localhost:5001/ -Dtest=FullTeachingTestE2EREST#courseInfoRestOperations,FullTeachingTestE2EREST#sessionRestOperations,FullTeachingTestE2EREST#forumRestOperations,FullTeachingTestE2EREST#filesRestOperations -B -DforkCount=0 test
