package com.meuteste.pages;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.meuteste.utils.Credenciais;

public class LoginPage {
	WebDriver driver;

    public LoginPage(WebDriver driver) {

        this.driver = driver;
    }

    public void realizarLogin() {

        driver.get("https://pje.tjce.jus.br/pje2grau");

        driver.findElement(By.id("username"))
                .sendKeys(Credenciais.getUsuario());

        driver.findElement(By.id("password"))
                .sendKeys(Credenciais.getSenha());

        driver.findElement(By.id("kc-login")).click();
        
        Scanner scanner = new Scanner(System.in);

        System.out.println(
            "Digite o código do autenticador e pressione ENTER para continuar..."
        );

        scanner.nextLine();
    }

}
