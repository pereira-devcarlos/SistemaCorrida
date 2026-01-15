# 🏍️ Sistema de Corridas – Java (POO)

Projeto **console em Java** criado para praticar os **fundamentos de Programação Orientada a Objetos**, utilizando **persistência em arquivos de texto (.txt)** e servindo como base sólida para estudos futuros com **Spring Boot, JPA e aplicações Web**.

---

## 🎯 Objetivo do Projeto

O objetivo deste projeto é aplicar na prática os principais pilares da POO:

* Encapsulamento
* Herança
* Polimorfismo
* Abstração
* Uso de Interfaces

Além disso, o projeto simula a **lógica de backend** de um sistema de corridas (estilo *Uber Moto*), sem interface gráfica, focando totalmente na organização do código e nas regras de negócio.

---

## 🧠 Conceito do Sistema

O sistema permite:

* Login/Cadastro de usuários
* Login/Cadastro de motoqueiros
* Solicitação de corridas
* Associação de motoqueiro disponível
* Escolha da forma de pagamento

Tudo isso executado via **menu no console**, com os dados sendo **salvos e carregados a partir de arquivos `.txt`**, simulando uma camada de persistência.

---

## 🗂️ Estrutura do Projeto

```text
SistemaCorridas
 ├── data/
 │   ├── usuarios.txt
 │   ├── motoqueiros.txt
 │   └── corridas.txt
 └── src/
     └── br/com/corridas/
         ├── app/
         │   └── Main.java
         ├── enums/
         │   └── StatusCorrida.java
         ├── model/
         │   ├── Corrida.java
         │   ├── Motoqueiro.java
         │   ├── Usuario.java
         │   └── UsuarioBase.java
         ├── repository/
         │   ├── CorridaRepository.java
         │   ├── MotoqueiroRepository.java
         │   └── UsuarioRepository.java
         ├── service/
         │   ├── CorridaService.java
         │   ├── MotoqueiroService.java
         │   └── UsuarioService.java
         └── util/
             └── MenuUtil.java
```

---

## 📦 Descrição dos Pacotes

### `app`

Contém a classe `Main`, responsável por iniciar o sistema e exibir o menu.

### `model`

Contém as **entidades do sistema**, representando o mundo real:

* Usuario
* Motoqueiro
* Corrida

Essas classes possuem atributos, construtores e getters/setters.

### `service`

Contém as **regras de negócio**, como:

* Criar corrida
* Finalizar corrida
* Verificar disponibilidade de motoqueiro

### `repository`

Responsável pela **persistência dos dados em arquivos `.txt`**.

Simula um **banco de dados em memória + arquivo**, utilizando listas (`ArrayList`) e leitura/escrita em arquivos.

Os dados são armazenados no diretório:

```
data/
```

### `enums`

Contém valores fixos do sistema, evitando o uso de `String` soltas.

Exemplo:

* Status da corrida (SOLICITADA, EM_ANDAMENTO, FINALIZADA)

### `util`

Classes auxiliares para organização do código, como menus e leitura de dados.

---

## 🧩 Principais Conceitos Aplicados

### ✔ Interface

Usada para representar comportamentos, como forma de pagamento.

### ✔ Polimorfismo

Permite tratar diferentes implementações de forma uniforme.

### ✔ Classe Abstrata

Utilizada quando existe um conceito genérico que não deve ser instanciado diretamente.

### ✔ Enum

Garante segurança e clareza no controle de estados do sistema.

---

## ▶️ Como Executar o Projeto

1. Clone ou baixe o repositório
2. Abra em uma IDE Java (IntelliJ, Eclipse ou VS Code)
3. Execute a classe:

```java
Main.java
```

4. Utilize o menu no console para interagir com o sistema

---

## 💾 Persistência em Arquivo (.txt)

O sistema utiliza **arquivos de texto** para salvar os dados, permitindo que as informações persistam mesmo após o encerramento do programa.

Exemplo de formato dos arquivos:

```
1;Carlos;319999999
2;Ana;319888888
```

Cada linha representa um registro, e os campos são separados por `;`.

---

## 👨‍💻 Autor

**Carlos Eduardo Pereira dos Santos**
