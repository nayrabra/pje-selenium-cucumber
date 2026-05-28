# language: pt
@autenticado
Funcionalidade: Consulta autenticada de processo

  Cenário: Consultar processo com sucesso e abrir PDF de despacho
    Dado que o usuário está autenticado no sistema
    E acessa a tela de consulta de processo
    Quando informa um número de processo válido
    E realiza a pesquisa
    E abre o processo retornado
    E acessa a aba de expedientes
    E seleciona o tipo de documento "Despacho"
    Então o sistema deve abrir o PDF do despacho

  Cenário: Consultar processo com número inválido
    Dado que o usuário está autenticado no sistema
    E acessa a tela de consulta de processo
    Quando informa um número de processo inválido
    E realiza a pesquisa
    Então o sistema deve exibir a mensagem para refinar a pesquisa

  Cenário: Consultar processo com campos vazios
    Dado que o usuário está autenticado no sistema
    E acessa a tela de consulta de processo
    Quando realiza a pesquisa sem preencher os campos
    Então o sistema deve exibir a mensagem de obrigatoriedade de critérios