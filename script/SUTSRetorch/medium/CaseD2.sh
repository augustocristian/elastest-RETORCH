#!/bin/bash
mvn -Dapp.url=https://localhost:5001/ -Dtest=LoggedForumTest#forumNewReply2CommentTest,FullTeachingTestE2EREST#courseRestOperations,FullTeachingTestE2EREST#courseInfoRestOperations,FullTeachingTestE2EREST#sessionRestOperations,FullTeachingTestE2EREST#forumRestOperations,FullTeachingTestE2EREST#filesRestOperations -B -DforkCount=0 test
