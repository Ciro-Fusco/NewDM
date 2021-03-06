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

WebUI.callTestCase(findTestCase('BeforeMagazzino'), [:], FailureHandling.STOP_ON_FAILURE)

Windows.click(findWindowsObject('Object Repository/NomeProdottoMagazzino'))

Windows.setText(findWindowsObject('NomeProdottoMagazzinoText'), 'Orata')

Windows.click(findWindowsObject('QuantitaMagazzino'))

Windows.setText(findWindowsObject('QuantitaMagazzinoText'), '100')

Windows.click(findWindowsObject('CodProdottoMagazzino'))

Windows.setText(findWindowsObject('CodProdottoMagazzinoText'), '0123456789000')

Windows.click(findWindowsObject('PrezzoMagazzino'))

Windows.setText(findWindowsObject('PrezzoMagazzinoText'), '30')

Windows.click(findWindowsObject('TipologiaMagazzino'))

Windows.setText(findWindowsObject('TipologiaMagazzinoText'), 'Pesce')

Windows.click(findWindowsObject('DimMedioMagazzino'))

Windows.click(findWindowsObject('ScaMediaMagazzino'))

Windows.click(findWindowsObject('InserisciProdottoMagazzino'))

def text = Windows.getText(findWindowsObject('InserisciNuovoProdottoRiepilogoLabel'))

assert text == 'Inserisci nuovo prodotto - Riepilogo'

Windows.closeApplication()

