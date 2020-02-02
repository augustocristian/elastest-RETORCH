#!/bin/bash
mvn -Dapp.url=https://localhost:5001/ -Dtest=LoggedForumTest#forumNewCommentTest,LoggedForumTest#forumNewReply2CommentTest,FullTeachingTestE2EREST#courseRestOperations -B -DforkCount=0 test

