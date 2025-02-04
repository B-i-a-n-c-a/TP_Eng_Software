



# Sitema de gestão de vacinas entre usuários  

Objetivo: Desenvolver um sistema de gerenciamento de vacinação que permita o cadastro e acompanhamento do histórico vacinal dos usuários, possibilitando a catalogação de vacinas, registro de doses aplicadas, envio de alertas sobre vacinas pendentes e fornecimento de um cartão de vacinação digital detalhado.

### Principais funcionalidades
- Cadastro de usuários
- Cadastro de profissionais da área da saúde
- Catalogação de vacinas
- Atualização de status de usuários
- Associação de profissionais ao registro de vacinação dos usuários
- Informar alertas sobre vacinas pendentes
- Indicação de vacinas já recomendadas pela idade
- Cartão de vacinação detalhado

### 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:
- [Java](https://www.java.com/pt-BR/)

### Histórias de usuário:
1. Como usuário, quero me cadastrar no sistema para que eu possa acessar e acompanhar meu histórico de vacinação.
2. Como usuário, quero receber alertas sobre vacinas pendentes para que eu possa tomar as doses necessárias no prazo adequado.
3. Como usuário, quero visualizar um cartão de vacinação que exiba a data das vacinas aplicadas, a necessidadComoe de outra dose, o profissional responsável e quais vacinas ainda preciso toma para facilitar o acompanhamento da minha imunização.
4. Como usuário, quero que o sistema me informe quais vacinas eu já deveria ter tomado com base na minha idade para garantir que meu esquema vacinal esteja em dia.

### Histórias de administrador:  
1. Como administrador do sistema, quero cadastrar profissionais da saúde para que eles possam registrar e atualizar informações sobre a vacinação dos usuários.
2. Como administrador do sistema, quero catalogar vacinas disponíveis para que os profissionais da saúde possam registrar corretamente as doses aplicadas.

### Histórias de profissional:  
1. Como profissional, quero registrar minha identificação ao aplicar uma vacina para que o histórico do usuário contenha informações sobre quem administra a dose.
2. Como profissional, quero atualizar o status de vacinação dos usuários para que o histórico de vacinação esteja sempre correto e atualizado.

### 🖥️ Casos de uso  
- [ ] 1. Cadastro de usuários  
**Descrição:** Permite que um cidadão realize seu cadastro no sistema.  
**Ator:** Usuário  
**Fluxo básico:**
  - [ ] O usuário acessa a página de cadastro.
  - [ ] Preenche as informações obrigatórias (nome, CPF, data de nascimento, etc.).
  - [ ] Confirma os dados e envia o formulário.
  - [ ] O sistema valida os dados e cria a conta do usuário.
  - [ ] O usuário recebe uma confirmação do cadastro.
     
- [ ] 2. Cadastro de profissionais da área da saúde  
**Descrição:** Permite que administradores do sistema cadastrem profissionais da saúde.  
**Ator:** Administrador do sistema  
**Fluxo básico:**  
  - [ ] O administrador acessa a área de cadastro de profissionais.
  - [ ] Preenche os dados necessários (nome, CRM/COREN, especialidade, unidade de saúde).
  - [ ] Envia o formulário.
  - [ ] O sistema valida e registra o profissional.
  - [ ] O administrador recebe uma confirmação do cadastro.

- [ ] 3. Catalogação de vacinas  
**Descrição:** Permite que o sistema mantenha um catálogo atualizado de vacinas disponíveis.  
**Ator:** Administrador do sistema  
**Fluxo básico:**  
  - [ ] O administrador acessa o módulo de catalogação de vacinas.
  - [ ] Insere as informações da vacina (nome, fabricante, doses necessárias, intervalo entre doses).
  - [ ] Confirma o cadastro.
  - [ ] O sistema valida e armazena as informações da vacina.
  - [ ] A vacina passa a estar disponível para registro na aplicação de doses.

- [ ] 4. Atualização de status dos usuários  
**Descrição:** Permite que um profissional de saúde atualize o status de vacinação de um usuário.  
**Ator:** Profissional de saúde  
**Fluxo básico:**  
  - [ ] O profissional acessa o sistema e busca o usuário pelo CPF ou nome.
  - [ ] Visualiza o histórico de vacinação do usuário.
  - [ ] Registra uma nova vacina administrada.
  - [ ] O sistema atualiza o status do usuário e exibe as próximas doses (se houver).
  - [ ] O usuário pode visualizar a atualização em seu histórico de vacinação.

- [ ] 5. Associação de profissionais ao registro de vacinação dos usuários  
**Descrição:** Permite que cada dose administrada seja associada ao profissional que a aplicou.  
**Atores:** Profissional de saúde, usuário  
**Fluxo básico:**  
  - [ ] O profissional registra a aplicação da vacina.
  - [ ] O sistema solicita a identificação do profissional responsável.
  - [ ] O profissional confirma seus dados.
  - [ ] O sistema salva a associação entre o profissional e a vacina aplicada.
  - [ ] O usuário pode visualizar quem aplicou cada dose no histórico de vacinação.

- [ ] 6. Informar alertas sobre vacinas pendentes  
**Descrição:** Envia notificações ao usuário sobre vacinas que ainda não foram tomadas ou doses que precisam ser reforçadas.  
**Ator:** Usuário  
**Fluxo básico:**  
  - [ ] O sistema verifica periodicamente as vacinas pendentes de cada usuário.
  - [ ] Caso uma vacina esteja em atraso, o sistema gera um alerta.
  - [ ] O usuário recebe uma notificação via e-mail, SMS ou aplicativo.
  - [ ] O usuário pode acessar o sistema para verificar detalhes e agendar sua vacinação.

- [ ] 7. Indicação de vacinas já recomendadas pela idade  
**Descrição:** O sistema verifica as vacinas que o usuário deveria ter tomado com base na sua idade.  
**Ator:** Usuário  
**Fluxo básico:**  
  - [ ] O usuário acessa seu perfil no sistema.
  - [ ] O sistema consulta a tabela de vacinas recomendadas por faixa etária.
  - [ ] O sistema compara as vacinas registradas com as que deveriam ter sido tomadas.
  - [ ] O sistema exibe quais vacinas estão em dia e quais estão pendentes.
  - [ ] O usuário pode agendar a aplicação de vacinas pendentes.

- [ ] 8. Cartão de vacinação detalhado  
**Descrição:** Exibe um cartão digital de vacinação para que o usuário acompanhe seu histórico.  
**Ator:** Usuário  
**Fluxo básico:**  

  O usuário acessa a seção "Meu Cartão de Vacinação".
  - [ ] O sistema exibe uma lista com:
     - Data das vacinas já tomadas
     - Necessidade de próximas doses
     - Nome do profissional que aplicou a última dose
     - Lista de vacinas que ainda precisam ser administradas
  - [ ] O usuário pode baixar ou imprimir o cartão de vacinação.







> [!TIP]
**Participantes:**  
> `Bianca Fernandes`  
> `Felipe Fialho`  
> `Jonas Kretli`
