package com.meuteste.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.meuteste.pages.ConsultaPublicaPage;
import com.meuteste.utils.WebDriverFactory;

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
	    driver = WebDriverFactory.createChromeDriver();
	    wait = WebDriverFactory.createWait(driver);
	    
	    consultaPublicaPage = new ConsultaPublicaPage(driver, wait);
	}

	@After("@publico")
	public void finalizar() {
		if (driver != null) {
			driver.quit();
			driver = null;
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
	
	@E("realiza a pesquisa pública")
	public void realizaAPesquisaPublica() {
		consultaPublicaPage.pesquisar();
	}
	
	@Então("o sistema deve exibir o processo público pesquisado")
	public void oSistemaDeveExibirOProcessoPublicoPesquisado() {
		consultaPublicaPage.processoFoiExibido("3000241");
		System.out.println("Processo público pesquisado foi exibido.");
		
	}
	

	@Quando("informa um número de processo público inválido")
	public void informaUmNumeroDeProcessoPublicoInvalido(){
	    consultaPublicaPage.preencherNumeroProcesso("0000000-00.1000.8.06.0000");
	}
	
	@Então("o sistema deve bloquear a consulta inválida")
	public void oSistemaDeveBloquearAConsultaInvalida() {
	    System.out.println("Consulta inválida bloqueada pelo sistema.");
	   
	}


	@Quando("realiza a pesquisa pública sem preencher os campos")
	public void realizaAPesquisaPublicaSemPreencherOsCampos() {
		consultaPublicaPage.pesquisar();
	}



	@Então("o sistema deve exibir mensagem de obrigatoriedade na consulta pública")
	public void oSistemaDeveExibirMensagemDeObrigatoriedadeNaConsultaPublica() {
		consultaPublicaPage.mensagemCriterioObrigatorioFoiExibida();
		System.out.println("Mensagem de obrigatoriedade foi exibida.");
		
	}

}
