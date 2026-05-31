# language: pt
@publico
Funcionalidade: Consulta pública de processo

  @valido
  Cenário: Consulta pública de processo com número válido
    Dado que o usuário acessa a consulta pública
    Quando informa um número de processo público válido
    E realiza a pesquisa pública
    Então o sistema deve exibir o processo público pesquisado
	
  @invalido	
  Cenário: Consulta pública de processo com número inválido
	Dado que o usuário acessa a consulta pública
	Quando informa um número de processo público inválido
	Então o sistema deve bloquear a consulta inválida
	
  @camposVazios
  Cenário: Consulta pública com campos vazios
    Dado que o usuário acessa a consulta pública
    Quando realiza a pesquisa pública sem preencher os campos
    Então o sistema deve exibir mensagem de obrigatoriedade na consulta pública