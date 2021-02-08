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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('BeforeAssistenza'), [:], FailureHandling.STOP_ON_FAILURE)

Windows.click(findWindowsObject('Object Repository/NomeCognomeAssistenza'))

Windows.setText(findWindowsObject('Object Repository/NomeCognomeAssistenzaText'), 'Franco Cirillo')

Windows.click(findWindowsObject('Object Repository/IndirizzoAssistenza'))

Windows.setText(findWindowsObject('Object Repository/IndirizzoAssistenzaText'), 'Via Roma')

Windows.click(findWindowsObject('TelefonoClienteAssistenza'))

Windows.setText(findWindowsObject('Object Repository/TelefonoClienteAssistenzaText'), '0123456789')

Windows.click(findWindowsObject('NomeProdottoAssistenza'))

Windows.setText(findWindowsObject('Object Repository/NomeProdottoAssistenzaText'), 'Notebook Asus')

Windows.click(findWindowsObject('TipoProdottoAssistenza'))

Windows.setText(findWindowsObject('Object Repository/TipoProdottoAssistenzaText'), 'N')

Windows.click(findWindowsObject('Object Repository/AvantiAssistenza'))

Windows.switchToWindowTitle('Errore')

def text =Windows.getText(findWindowsObject('Object Repository/MessaggioErrore'))

assert text == 'Inserire un tipo di prodotto valido'

Windows.closeApplication()
