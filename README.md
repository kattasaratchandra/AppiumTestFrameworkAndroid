1. Used Java program able to create new driver session. Have used Desired capabilities
2. The difference between appium 2x and 1x was so far:
   1. while creating url object we send local
   host url as argument in that no need to send /wb/hub anymore in 2x
   2. we install server, drivers, plugins separately in 2x this may change in the future.
   3. driver.findElementBy got depreciated and instead we use driver.findelement(AppiumBy.)
   4. MobileElement depreciated instead we use WebElement
   5. MobileBy depreciated instead we use AppiumBy
3. The other way of giving capabilities is using options class of respective drivers - UiAutomator2, 
Xcuittest.
4. if the element has accessibility id and id are different methods use accordingly
5. we usually use click gesture when click and tap methods wont work
6. As of now the latest testng only works on version java 11 or above it wont work on 8, 9,10
7. TDD FRAMEWORK:
8. created baseTest class in com.qa package with @before and @After Tests such that we initialize driver. here 
the intention is to initialize driver only once and use it across all the test classes and quit after it with 
the help of before and after tests
9. created config.properties file for global variables. so that we dont hard code the values of desired 
capabilities and in resources created app folder for the android apk's and ios ipa builds we take app from there
10. if we have properties file in resource folder then the entire path is not required to give. and we use input
stream instead fileinputstream
11. Base Tests:
    1. initialize the appium driver in before tests with desired capabilities. quit the driver using after test
methods.
    2. for desired capabilities used properties file for some values and device specific values used testng 
parameterization.
    3. initialised properties file.
    4. used try catch block to get the exception immediate
    5. created explicit wait. using it created common methods for click, sendkeys and get attribute.
12. created test utils for constants, created packages for tests and pages.
13. In page's I defined the webElement and created methods for actions using common methods in base test
14. In Tests used before method for creating home page object.
15. created testng listeners to handle exceptions. we call listeners two ways through test class and testng.xml.
it's better to call from xml so that no need to call from each test class every time we create
16. Have used stringWriter, printWriter to log exceptions in testng and console.
17. Have used json file as test data and org.json library to create json object and get data. 
18. It's recommended/good practice to use udid capability rather platform version as appium may confuse while we using 
multiple devices.
19. As we maintain same framework for both ios and android we use switch statement based on platform value we run
the code.
20. we create common pages fragments for other pages and extend them for all the common pages. this way we can
avoid creating elements again(code duplication)
21. When we extend the commons with other pages we can use other pages instances And call commons as 
well in that way its more readability and that’s the best of using inheritance.
22. Interacts with app close app and activate app depreciated instead we use terminate app and activate app using
interactWithApps. created methods for the same in base test. This way we make test classes independent of each 
other.
23. To make tests independent.In before or after method usually we use close and active app methods or do login/
logout
24. ui scrollable no need of casting we can use directly. one disadvantage is we can't use xpath as locator. we
use content description/accessibility id. and also we won't get compilation error. it returns web element
25. if there is only one scrollable element then instead parent locator we can simplye give .scrollable(true)
26. we can get global parameters from testng.xml using ITestResult in ItestListeners. returns the values in form
of key-pair hashmap.
27. we cast driver to canRecordeScreen and use method startRecordingScreen to record videos. usually we keep in
before and after methods
28. parallel execution can be achieved in mobile automation by running tests parallel on different devices.
29. In web we usually run tests parallel at methods level. In mobile its preferred to run tests parallel in
tests level.
30. we use adb devices for android and ios-deploy -c to get udid's of devices

