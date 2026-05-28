package com.meuteste.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConsultaAutenticadaPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	public ConsultaAutenticadaPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		this.js = (JavascriptExecutor) driver;
	}

	public void selecionarPerfilConsultor() {
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.dropdown-toggle"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Consultor')]"))).click();
	}

	public void acessarTelaConsultaProcesso() {
		wait.until(ExpectedConditions.elementToBeClickable(By.className("botao-menu"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Processo"))).click();

		WebElement itemMenu = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href*='listView.seam']")));

		js.executeScript("arguments[0].click();", itemMenu);
	}

	public void preencherNumeroProcesso(String numeroSequencial, String digitoVerificador, String ano,
			String ramoJustica, String tribunal, String orgaoJustica) {
		preencherCampoPorId("fPP:numeroProcesso:numeroSequencial", numeroSequencial);
		preencherCampoPorId("fPP:numeroProcesso:numeroDigitoVerificador", digitoVerificador);
		preencherCampoPorId("fPP:numeroProcesso:Ano", ano);
		preencherCampoPorId("fPP:numeroProcesso:ramoJustica", ramoJustica);
		preencherCampoPorId("fPP:numeroProcesso:respectivoTribunal", tribunal);
		preencherCampoPorId("fPP:numeroProcesso:NumeroOrgaoJustica", orgaoJustica);
	}

	public void pesquisar() {
		WebElement btnPesquisar = wait.until(ExpectedConditions.elementToBeClickable(By.id("fPP:searchProcessos")));

		js.executeScript("arguments[0].click();", btnPesquisar);
	}

	public void abrirProcesso() {
		WebElement processo = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@id,'processosTable') and contains(@class,'btn-link')]")));

		js.executeScript("arguments[0].click();", processo);

		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		for (String janela : driver.getWindowHandles()) {
			driver.switchTo().window(janela);
		}

		driver.switchTo().defaultContent();
	}

	public void abrirExpedientes() {
		WebElement envelope = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[@title='Expedientes' or contains(@id, 'linkAbaExpedientes')]")));

		js.executeScript("arguments[0].click();", envelope);

		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//li[contains(@class,'dropdown')]//a[contains(@class,'btn-menu-abas')]")));

		System.out.println("Aba Expedientes carregada.");
	}

	public void abrirMenuDownload() {
		WebElement btnIconeDownload = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//li[contains(@class,'dropdown')]//a[contains(@class,'btn-menu-abas')]")));

		js.executeScript("arguments[0].click();", btnIconeDownload);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navbar:botoesDownload")));

		System.out.println("Menu de download aberto.");
	}

	public void selecionarTipoDocumento(String tipoDocumento) {

		WebElement gatilho = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("select2-navbar:cbTipoDocumento-container")));

		gatilho.click();

		WebElement campoBusca = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.select2-search__field")));

		campoBusca.sendKeys(tipoDocumento);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(.,'" + tipoDocumento + "')]"))).click();

		System.out.println(tipoDocumento + " selecionado.");
	}

	public String clicarDownloadEAbrirPdf() {
		Set<String> abasAntes = driver.getWindowHandles();

		WebElement btnDownload = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Download']")));

		js.executeScript("arguments[0].click();", btnDownload);

		wait.until(driver -> driver.getWindowHandles().size() > abasAntes.size());

		for (String aba : driver.getWindowHandles()) {
			if (!abasAntes.contains(aba)) {
				driver.switchTo().window(aba);
				break;
			}
		}

		return driver.getCurrentUrl();
	}

	private void preencherCampoPorId(String id, String valor) {
		WebElement campo = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));

		js.executeScript("arguments[0].value = arguments[1];", campo, valor);
		js.executeScript("arguments[0].dispatchEvent(new Event('change'));", campo);
	}
}