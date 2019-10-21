#!/bin/bash
mvn -Dapp.url=https://localhost:5001/ -Dtest=CourseTeacherTest#teacherCourseMainTest,CourseStudentTest#studentCourseMainTest,LoggedForumTest#forumLoadEntriesTest,LoggedLinksTests#spiderLoggedTest,UserTest#loginTest -B -DforkCount=0 test
