# Freenow Assignment

This automation suite is for finding comments on a post by user

There are 3 tests which are added in testng.xml file:

1. TestGetAllUsers: Get the user Id based on username (username comes from config file or from excel)
2. TestGetAllGetAllPosts: the posts based on user Id
3. TestGetCommentsOnPost: the comments on the posts which are found in above step


1. E2E test: which contains all above steps and can be run individually as testng test.


## Execution

Different ways to run project
1. Right click on testng.xml file and run as TestNG Suite
2. Run Individual E2E test
3. Run using maven
4. The project is added in circleCI, can be executed using circleCI
https://app.circleci.com/pipelines/github/swapbamnote/Freenow


Logs are generated in restApi file under logs folder
Reports are generated inside allure-results folder



