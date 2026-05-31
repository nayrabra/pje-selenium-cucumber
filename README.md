# Selenium Automation - PJe TJCE

![Java](https://img.shields.io/badge/Java-17-blue)
![Selenium](https://img.shields.io/badge/Selenium-4-green)
![Cucumber](https://img.shields.io/badge/Cucumber-BDD-brightgreen)
![CI](https://github.com/nayrabra/pje-selenium-cucumber/actions/workflows/consulta-publica.yml/badge.svg)

Projeto de automação de testes web utilizando **Selenium WebDriver + Java + Cucumber + JUnit 5**, com foco na validação de fluxos do sistema **PJe TJCE**.

O projeto foi estruturado utilizando o padrão **Page Object Model (POM)**, separando páginas, steps e runners para facilitar a manutenção, reutilização e organização dos testes.

---

## Tecnologias utilizadas

* Linguagem de Programação: Java 17

* Automação de Web: Selenium WebDriver & ChromeDriver

* Abordagem BDD (Behavior-Driven Development) com Cucumber e Gherkin para escrita dos cenários de teste

* Framework de Testes / Runner: JUnit 5

* Gerenciador de Dependências e Build: Maven

* Integração Contínua (CI/CD): GitHub Actions

---

## Estrutura do projeto

```bash
.github
└── workflows
    └── consulta-publica.yml

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

## Integração Contínua (CI/CD)

O projeto utiliza GitHub Actions para execução automatizada dos testes de consulta pública.

A pipeline é acionada automaticamente a cada:

* Push para a branch principal;
* Pull Request direcionado para a branch principal.

Atualmente, a pipeline executa apenas os cenários da funcionalidade de consulta pública (@publico), pois os cenários autenticados dependem de autenticação MFA/2FA e exigem interação manual.

Benefícios da pipeline implementada:

* Execução automática dos testes;
* Validação contínua da aplicação;
* Detecção antecipada de falhas;
* Maior confiabilidade nas alterações do projeto.

---

## Objetivo do projeto

Este projeto foi desenvolvido com foco em estudos e evolução prática em:

* automação de testes;
* Selenium WebDriver;
* estruturação de frameworks;
* BDD;
* boas práticas de automação;
* organização de testes E2E.
* integração Contínua (CI/CD) com GitHub Actions;

---
