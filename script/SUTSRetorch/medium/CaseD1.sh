#!/bin/bash
mvn -Dapp.url=https://localhost:5001/ -Dtest=CourseTeacherTest#teacherCreateAndDeleteCourseTest,CourseTeacherTest#teacherEditCourseValues,CourseTeacherTest#teacherDeleteCourseTest,LoggedForumTest#forumNewEntryTest, -B -DforkCount=0 test
