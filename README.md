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