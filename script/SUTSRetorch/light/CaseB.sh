#!/bin/bash
gnome-terminal -e "bash -ic \"mvn -Dapp.url=https://localhost:5001/ -Dtest=CourseTeacherTest#teacherCourseMainTest -B -DforkCount=0 test; exec bash\""
gnome-terminal -e "bash -ic \"mvn -Dapp.url=https://localhost:5001/ -Dtest=CourseStudentTest#studentCourseMainTest -B -DforkCount=0 test; exec bash\""
gnome-terminal -e "bash -ic \"mvn -Dapp.url=https://localhost:5001/ -Dtest=LoggedForumTest#forumLoadEntriesTest -B -DforkCount=0 test; exec bash\""
gnome-terminal -e "bash -ic \"mvn -Dapp.url=https://localhost:5001/ -Dtest=LoggedLinksTests#spiderLoggedTest -B -DforkCount=0 test; exec bash\""
gnome-terminal -e "bash -ic \"mvn -Dapp.url=https://localhost:5001/ -Dtest=UserTest#loginTest -B -DforkCount=0 test; exec bash\""
