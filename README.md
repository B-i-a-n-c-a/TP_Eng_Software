 # **Título `Cafeteria Virtual`**  
 
**Objetivo: Desenvolver uma plataforma digital para uma cafeteria, permitindo que clientes façam pedidos online, personalizem seua bebida, participem de um programa de fidelidade e interajam com os baristas em tempo real.**  

## **Principais funcionalidades:**  

- Menu Interativo: Exibir o cardápio com opções de bebidas e alimentos.  

- Sistema de Pedidos: Clientes podem fazer pedidos online, escolher o horário de retirada ou entrega.  

- Programa de Fidelidade: Acumular pontos a cada compra, com recompensas e descontos.  

- Perfil do Cliente: Histórico de pedidos, preferências e saldo de pontos de fidelidade.  

- Chat com o Barista: Canal de comunicação para exclarecer dúvidas e solicitar recomendações de bebidas.  

- Avaliações e feedbacks: Sistema para clientes avaliarem produtos e serviços, com feedback diretamente ao estabelecimento.  

- Administração do Sistema: Painel para gerenciar o cardápio, pedidos, clientes e recompensas.  

- Notificações: Alertas sobre status do pedido, promoções e etc.  

- Pagamentos: Suporte para diversas formas de pagamento.  


## **Membros da Equipe:**
**Bianca:** `Scrum Master`

**Mateus:** `Desenvolvedor Backend`

**Tomaz:** `Desenvolvedor Frontend`

**Thiago:** `Desenvolvedor Backend`

## **Tecnologias:**
Pyhton

Flask

HTML/CSS/JavaScript

SQLite

# **Backlogs do produto:**

**História:**

Como cliente, quero visualizar o menu da cafeteria virtual para poder escolher meus pedidos.

**Critérios de aceitação:**

O menu deve ser exibido com todas as opções disponíveis.
Os itens devem incluir descrições, preços e imagens.

**História:**

Como cliente, quero poder adicionar itens ao meu carrinho de compras para fazer um pedido.

**Critérios de aceitação:**

O usuário pode selecionar quantidades diferentes para cada item.
O carrinho deve atualizar o total em tempo real.

**História:**

Como cliente, quero personalizar meu pedido (ex: sem açúcar, leite desnatado) para que ele atenda minhas preferências.

**Critérios de aceitação:**

Cada item do menu deve ter opções de personalização.
As personalizações devem ser refletidas no resumo do pedido.

**História:**

Como cliente, quero escolher a forma de entrega (retirada na loja ou entrega a domicílio) para receber meu pedido da maneira mais conveniente.

**Critérios de aceitação:**

O usuário deve poder selecionar o método de entrega antes de finalizar o pedido.
Informações sobre a localização da retirada ou endereço de entrega devem ser solicitadas conforme o método escolhido.

**História:**

Como cliente, quero visualizar um resumo do meu pedido antes de finalizar a compra para garantir que todos os detalhes estão corretos.

**Critérios de aceitação:**

O resumo deve listar todos os itens, personalizações e o total.
Deve haver uma opção para editar o pedido antes da finalização.

**História:**

Como cliente, quero poder pagar meu pedido online com diferentes métodos de pagamento para que seja conveniente e seguro.

**Critérios de aceitação:**

Suporte a cartão de crédito, débito, e carteira digital (ex: PayPal).
Confirmação imediata do pagamento e recibo por e-mail.

**História:**

Como cliente, quero receber uma confirmação de pedido por e-mail ou notificação para saber que meu pedido foi registrado com sucesso.

**Critérios de aceitação:**

Enviar um e-mail ou notificação com o número do pedido, resumo e tempo estimado de entrega.

**História:**

Como cliente, quero acompanhar o status do meu pedido em tempo real para saber quando estará pronto ou foi entregue.

**Critérios de aceitação:**

O status do pedido deve ser atualizado em tempo real (ex: "Preparando", "A caminho", "Entregue").
Notificações push ou SMS devem ser enviadas em cada etapa.

**História:**

Como administrador, quero gerenciar o menu da cafeteria para adicionar, editar ou remover itens conforme necessário.

**Critérios de aceitação:**

Interface para adicionar novos itens com descrição, preço e imagem.
Possibilidade de editar ou remover itens existentes.

**História:**

Como administrador, quero visualizar relatórios de vendas para analisar o desempenho da cafeteria virtual.

**Critérios de aceitação:**

Relatórios devem incluir dados de vendas por dia, semana, mês.            
Possibilidade de exportar relatórios em formatos como CSV ou PDF.

## **Sprint**

**História 1:**

Configuração do Ambiente de Desenvolvimento `[Bianca]`

Como desenvolvedor, quero configurar o ambiente de desenvolvimento para que eu possa começar a trabalhar no projeto com todas as ferramentas e tecnologias necessárias.

**Tarefas:**

Configurar o ambiente virtual com venv ou virtualenv.
Instalar as dependências do projeto listadas no requirements.txt.
Configurar o repositório Git e definir o fluxo de trabalho (branches, commits, etc.).

**História 2:**

Instalação e Configuração do Banco de Dados `[Bianca]`

Como desenvolvedor, quero instalar e configurar o banco de dados para que eu possa criar e gerenciar as tabelas necessárias para o sistema.

**Tarefas:**

Instalar o banco de dados escolhido e configurar as credenciais.
Criar um esquema inicial de banco de dados com tabelas principais (Usuários, Pedidos, Itens do Menu, etc.).
Configurar a conexão do banco de dados no projeto e testar a integração.

**História 3:**

Implementação das Telas de Cadastro e Login `[Tomaz]`

Como usuário, quero ter acesso às telas de cadastro e login para que eu possa criar uma conta e acessar o sistema.

**Tarefas:**

Desenvolver a interface de cadastro e login com HTML/CSS/JavaScript.
Implementar lógica de autenticação e autorização no back-end.
Testar o fluxo de cadastro, login e tratamento de erros.

**História 4:**

Criação do Sistema de Pedidos e Personalização `[Mateus]`

Como cliente, quero poder fazer pedidos e personalizar minhas bebidas para que eu possa escolher o que quero e como quero.

**Tarefas:**

Desenvolver a interface para criação e personalização de pedidos.
Implementar a lógica de backend para processar pedidos e personalizações.
Testar o fluxo de pedidos, incluindo a personalização e armazenamento no banco de dados.

**História 5:**

Implementação do Painel de Administração `[Thiago]`

Como administrador, quero ter acesso a um painel de administração para que eu possa gerenciar itens do menu, visualizar relatórios e acompanhar o desempenho do sistema.

**Tarefas:**

Desenvolver a interface do painel de administração com funcionalidades para gerenciamento de menu e visualização de relatórios.
Implementar a lógica para adicionar, editar e remover itens do menu.
Configurar permissões de acesso e testar a funcionalidade e segurança do painel.

