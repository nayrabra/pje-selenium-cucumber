# Selenium Automation - PJe TJCE

Projeto de automação de testes web utilizando **Selenium WebDriver + Java + Cucumber + JUnit 5**, com foco na validação de fluxos do sistema **PJe TJCE**.

O projeto foi estruturado utilizando o padrão **Page Object Model (POM)**, separando páginas, steps e runners para facilitar a manutenção, reutilização e organização dos testes.

---

## Tecnologias utilizadas

* Java 17
* Selenium WebDriver
* Cucumber
* Gherkin
* JUnit 5
* Maven
* ChromeDriver

---

## Estrutura do projeto

```bash
src/test/java
├── com.meuteste.pages
│   ├── LoginPage.java
│   ├── ConsultaAutenticadaPage.java
│   └── ConsultaPublicaPage.java
│
├── com.meuteste.runner
│   ├── RunnerAutenticadoTest.java
│   └── RunnerPublicoTest.java
│
├── com.meuteste.steps
│   ├── ConsultaAutenticadaSteps.java
│   └── ConsultaPublicaSteps.java
│
└── com.meuteste.utils
    └── Credenciais.java

src/test/resources
└── features
    ├── consulta_autenticada.feature
    └── consulta_publica.feature
```

---

## Funcionalidades automatizadas

### Consulta autenticada

* Login no sistema PJe
* Seleção de perfil
* Consulta de processo
* Abertura do processo em nova aba
* Navegação até expedientes
* Download de documento PDF
* Validação da URL do PDF
* Cenários positivos e negativos

### Consulta pública

* Consulta pública de processos
* Pesquisa com número válido
* Pesquisa com número inválido
* Validação de alertas do sistema
* Validação de obrigatoriedade de preenchimento

---

## Padrões utilizados

### Page Object Model (POM)

O projeto foi estruturado utilizando Page Objects para:

* separar elementos e ações da página;
* evitar repetição de código;
* facilitar manutenção;
* melhorar reutilização dos métodos.

---

## BDD com Cucumber

Os cenários foram escritos utilizando Gherkin:

```gherkin
Cenário: Consulta pública de processo com número válido
  Dado que o usuário acessa a consulta pública
  Quando informa um número de processo público válido
  E realiza a pesquisa pública
  Então o sistema deve exibir o processo público pesquisado
```

---

## Execução dos testes

### Executar testes autenticados

```bash
mvn test -Dtest=RunnerAutenticadoTest
```

### Executar testes públicos

```bash
mvn test -Dtest=RunnerPublicoTest
```

Ou execute diretamente os runners pela IDE:

```text
Run As → JUnit Test
```

---

## Observações importantes

Os testes autenticados utilizam autenticação MFA/2FA.

Por esse motivo, atualmente o login exige confirmação manual do código autenticador durante a execução automatizada.

Foi utilizada uma pausa controlada via `Scanner` para permitir a inserção manual do código antes da continuação do fluxo automatizado.

---

## CI/CD

A pipeline de integração contínua ainda não foi implementada neste projeto.

A proposta futura é:

* utilizar GitHub Actions e/ou Jenkins;
* executar automaticamente apenas os cenários públicos (`@publico`);
* manter os cenários autenticados fora da pipeline devido ao MFA/2FA.

---

## Objetivo do projeto

Este projeto foi desenvolvido com foco em estudos e evolução prática em:

* automação de testes;
* Selenium WebDriver;
* estruturação de frameworks;
* BDD;
* boas práticas de automação;
* organização de testes E2E.

---
