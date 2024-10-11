# API para Sistema de Avaliação de Créditos

---

Uma empresa de empréstimo precisa criar um sistema de análise de solicitação de crédito. Sua tarefa será criar uma API REST SPRING BOOT E KOTLIN 🍃💜 para a empresa fornecer aos seus clientes as seguintes funcionalidades:

### Cliente (Customer):

- Cadastrar: 
  1. Request: firstName, lastName, cpf, income, email, password, zipCode e street
  2. Response: String

- Editar cadastro:
  1. Request: id, firstName, lastName, income, zipCode, street
  2. Response: firstName, lastName, income, cpf, email, income, zipCode, street
 
- Visualizar perfil:
  1. Request: id
  2. Response: firstName, lastName, income, cpf, email, income, zipCode, street

- Deletar cadastro:
  1. Request: id
  2. Response: sem retorno

### Solicitação de Empréstimo (Credit):

- Cadastrar:
  1. Request: creditValue, dayFirstOfInstallment, numberOfInstallments e customerId
  2. Response: String

- Listar todas as solicitações de emprestimo de um cliente:
  1. Request: customerId
  2. Response: creditCode, creditValue, numberOfInstallment

- Visualizar um emprestimo:
  1. Request: customerId e creditCode
  2. Response: creditCode, creditValue, numberOfInstallment, status, emailCustomer e incomeCustomer

### Regras de negócio

Implemente as regras de negócio a seguir para a solicitação de empréstimo:

  1. O máximo de parcelas permitido será 48
  2. Data da primeira parcela deverá ser no máximo 90 dias após o dia atual

### Instrução de Uso

No Terminal/Console:

  1. Faça um clone do projeto na sua máquina: git clone github.com/agpsl/credit-application-system.git
  2. Entre na pasta raiz do projeto: cd
  3. Execute o comando: ./gradlew bootrun