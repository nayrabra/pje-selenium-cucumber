package com.meuteste.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.meuteste.pages.ConsultaPublicaPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class ConsultaPublicaSteps {

	WebDriver driver;
	WebDriverWait wait;

	ConsultaPublicaPage consultaPublicaPage;

	@Before("@publico")
	public void setup() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-blink-features=AutomationControlled");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		consultaPublicaPage = new ConsultaPublicaPage(driver, wait);
	}

	@After("@publico")
	public void finalizar() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Dado("que o usuário acessa a consulta pública")
	public void queOUsuarioAcessaAConsultaPublica() {
		consultaPublicaPage.acessarConsultaPublica();
	}

	@Quando("informa um número de processo público válido")
	public void informaUmNumeroDeProcessoPublicoValido() {
		consultaPublicaPage.preencherNumeroProcesso("3000241-76.2023.8.06.0032");
		System.out.println("Número de processo válido foi preenchido.");
	}

	@Quando("informa um número de processo público inválido")
	public void informaUmNumeroDeProcessoPublicoInvalido() {
	    consultaPublicaPage.preencherNumeroProcesso("0000000-00.1000.8.06.0000");

	    assertTrue(
	            consultaPublicaPage.alertaNumeroInvalidoFoiExibido()
	    );

	    System.out.println("Alerta de número de processo inválido foi exibido.");
	}

	@Quando("realiza a pesquisa pública sem preencher os campos")
	public void realizaAPesquisaPublicaSemPreencherOsCampos() {
		consultaPublicaPage.pesquisar();
	}

	@E("realiza a pesquisa pública")
	public void realizaAPesquisaPublica() {
		consultaPublicaPage.pesquisar();
	}
	
	@Então("o sistema deve exibir o processo público pesquisado")
	public void oSistemaDeveExibirOProcessoPublicoPesquisado() {
		assertTrue(consultaPublicaPage.processoFoiExibido("3000241"));
		System.out.println("Processo público pesquisado foi exibido.");
	}

	@Então("o sistema deve bloquear a consulta inválida")
	public void oSistemaDeveBloquearAConsultaInvalida() {
	    System.out.println("Consulta inválida bloqueada pelo sistema.");
	}

	@Então("o sistema deve exibir mensagem de obrigatoriedade na consulta pública")
	public void oSistemaDeveExibirMensagemDeObrigatoriedadeNaConsultaPublica() {
		assertTrue(consultaPublicaPage.mensagemCriterioObrigatorioFoiExibida());
		System.out.println("Mensagem de obrigatoriedade foi exibida.");
	}

}
