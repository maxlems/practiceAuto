Project
|_ main
|   |_ java
| 	|_ com.yourcompany.automation
|	    |_ pages
|		     |_ LoginPage
|	    |_ utils
|		     |_ DriverManager
|        |_ ExtentReportListener
|        |_ TestUtils  
|_ test
|   |_ java
|   |	|_ com.yourcompany.automation.tests
|   | 	   |_ BaseTest (DriverManager)
|   |	     |_ LoginPageTest (BaseTest, LoginPage)
|   |_ resources
|	  |_ webdriver
|		|_ chromedriver.exe
|_ pom.xml
|_ Spark.html (Extent Report)
