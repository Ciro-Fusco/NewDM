package KATALONE

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import KATALONE.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


class Assistenza {
    static void main(String[] args) {
        Windows.startApplicationWithTitle('C:\\Users\\vinny\\Documents\\GitHub\\NewDM\\JAVA\\NewDMApp\\out\\artifacts\\NewDMApp\\bundles\\NewDMApp\\NewDMApp.exe',
                '')

        Windows.click(findWindowsObject('Object Repository/Button'))

        Windows.click(findWindowsObject('Object Repository/Edit'))

        Windows.setText(findWindowsObject('Object Repository/Text'), 'cirofu')

        Windows.click(findWindowsObject('Object Repository/Edit(1)'))

        Windows.setText(findWindowsObject('Object Repository/Text(1)'), 'cirofu')

        Windows.click(findWindowsObject('Object Repository/Button(1)'))

        Windows.click(findWindowsObject('Object Repository/Edit(2)'))

        Windows.setText(findWindowsObject('Object Repository/Text(2)'), 'a')

        Windows.click(findWindowsObject('Object Repository/Button(2)'))

        Windows.sendKeys(findWindowsObject('Object Repository/Window'), Keys.chord(Keys.ENTER))

        Windows.click(findWindowsObject('Object Repository/Edit(2)'))

        Windows.sendKeys(findWindowsObject('Object Repository/Window'), Keys.chord(Keys.BACK_SPACE))

        Windows.setText(findWindowsObject('Object Repository/Text(2)'), 'Franco Cirillo')

        Windows.click(findWindowsObject('Object Repository/Edit(3)'))

        Windows.setText(findWindowsObject('Object Repository/Text(3)'), 'V')

        Windows.click(findWindowsObject('Object Repository/Button(2)'))

        Windows.sendKeys(findWindowsObject('Object Repository/Window'), Keys.chord(Keys.ENTER))

        Windows.closeApplication()


    }
}
