package com.meuteste.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.meuteste.pages.ConsultaAutenticadaPage;
import com.meuteste.pages.LoginPage;
import com.meuteste.utils.WebDriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class ConsultaAutenticadaSteps {

    WebDriver driver;
    WebDriverWait wait;

    LoginPage loginPage;
    ConsultaAutenticadaPage consultaProcessoPage;

    @Before("@autenticado")
    public void setup() {
    	driver = WebDriverFactory.createChromeDriver();
  	    wait = WebDriverFactory.createWait(driver);

        loginPage = new LoginPage(driver);
        consultaProcessoPage = new ConsultaAutenticadaPage(driver, wait);
    }

    @After("@autenticado")
    public void finalizar() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Dado("que o usuário está autenticado no sistema")
    public void queOUsuarioEstaAutenticadoNoSistema() {
        loginPage.realizarLogin();
    }

    @E("acessa a tela de consulta de processo")
    public void acessaATelaDeConsultaDeProcesso() {
        consultaProcessoPage.selecionarPerfilConsultor();
        consultaProcessoPage.acessarTelaConsultaProcesso();
    }

    @Quando("informa um número de processo válido")
    public void informaUmNumeroDeProcessoValido() {
        consultaProcessoPage.preencherNumeroProcesso(
                "3000241",
                "",
                "2023",
                "8",
                "06",
                "0032"
        );
    }

    @Quando("informa um número de processo inválido")
    public void informaUmNumeroDeProcessoInvalido() {
        consultaProcessoPage.preencherNumeroProcesso(
                "0000000",
                "00",
                "0000",
                "8",
                "06",
                "0000"
        );
    }

    @Quando("realiza a pesquisa sem preencher os campos")
    public void realizaAPesquisaSemPreencherOsCampos() {
        consultaProcessoPage.pesquisar();
    }

    @E("realiza a pesquisa")
    public void realizaAPesquisa() {
        consultaProcessoPage.pesquisar();
    }

    @E("abre o processo retornado")
    public void abreOProcessoRetornado() {
        consultaProcessoPage.abrirProcesso();
    }

    @E("acessa a aba de expedientes")
    public void acessaAAbaDeExpedientes() throws InterruptedException {
        consultaProcessoPage.abrirExpedientes();
        Thread.sleep(3000);
        consultaProcessoPage.abrirMenuDownload();
    }

    @E("seleciona o tipo de documento {string}")
    public void selecionaOTipoDeDocumento(String tipoDocumento) {
        consultaProcessoPage.selecionarTipoDocumento(tipoDocumento);
    }

    @Então("o sistema deve abrir o PDF do despacho")
    public void oSistemaDeveAbrirOPdfDoDespacho() {
        String urlPdf = consultaProcessoPage.clicarDownloadEAbrirPdf();

        assertTrue(urlPdf.contains("https://minio-pjedocs.tjce.jus.br/"));

        System.out.println("PDF de Despacho aberto na URL: " + urlPdf);
    }

    @Então("o sistema deve exibir a mensagem para refinar a pesquisa")
    public void oSistemaDeveExibirAMensagemParaRefinarAPesquisa() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Por favor, refine sua pesquisa.')]")
        ));

        System.out.println("Consulta de número inválido de processo realizada.");
    }

    @Então("o sistema deve exibir a mensagem de obrigatoriedade de critérios")
    public void oSistemaDeveExibirAMensagemDeObrigatoriedadeDeCriterios() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Pelo menos um dos critérios de pesquisa deve ser informado.')]")
        ));

        System.out.println("Consulta com campos vazios realizada.");
    }
}
