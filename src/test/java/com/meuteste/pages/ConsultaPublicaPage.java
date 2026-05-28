package com.meuteste.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConsultaPublicaPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	public ConsultaPublicaPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		this.js = (JavascriptExecutor) driver;
	}

	public void acessarConsultaPublica() {
		driver.get("https://pje-consulta.tjce.jus.br/pje1grau/ConsultaPublica/listView.seam");
	}

	public void preencherNumeroProcesso(String numeroProcesso) {
		WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("fPP:numProcesso-inputNumeroProcessoDecoration:numProcesso-inputNumeroProcesso")));

		campo.clear();
		campo.sendKeys(numeroProcesso);
	}

	public void pesquisar() {
		WebElement btnPesquisar = wait.until(ExpectedConditions.elementToBeClickable(By.id("fPP:searchProcessos")));

		js.executeScript("arguments[0].click();", btnPesquisar);
	}

	public boolean alertaNumeroInvalidoFoiExibido() {
	    wait.until(ExpectedConditions.alertIsPresent());

	    String mensagem = driver.switchTo().alert().getText();

	    System.out.println("ALERTA EXIBIDO: " + mensagem);

	    boolean mensagemCorreta = mensagem.contains("Número de processo inválido.");

	    driver.switchTo().alert().accept();

	    return mensagemCorreta;
	}

	public boolean mensagemCriterioObrigatorioFoiExibida() {
		WebElement mensagem = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[contains(text(),'Pelo menos um dos critérios de pesquisa deve ser informado.')]")));

		return mensagem.isDisplayed();
	}

	public boolean processoFoiExibido(String numeroProcesso) {
		WebElement resultado = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + numeroProcesso + "')]")));

		return resultado.isDisplayed();
	}

}