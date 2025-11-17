# AT2 - Cálculo de Média Geral 
Este projeto é um aplicativo Android desenvolvido para a Atividade 2 da matéria de PDMII, focado em demonstrar os conceitos básicos de Programação Orientada a Objetos (POO) e Jetpack Compose em Kotlin.

## Sobre o Projeto

O aplicativo calcula a média geral de um aluno com base em três notas parciais (TP1, TP2, TP3) e exibe seu status final de aprovação, reprovação ou ótimo aproveitamento.

## Requisitos Funcionais

* **Cadastro de Aluno:** Permite a inserção do nome completo.
* **Lançamento de Notas:** Permite a inserção das três notas (TP1, TP2, TP3).
* **Cálculo da Média:** Realiza o cálculo da média aritmética.
* **Avaliação de Desempenho:** Exibe o status final:
  * `Reprovado` (Média < 6.0)
  * `Aprovado` (Média ≥ 6.0 e ≤ 9.0)
  * `Ótimo Aproveitamento` (Média > 9.0)

## Requisitos Técnicos

* **POO:** A lógica de negócio é encapsulada na `data class Aluno` e no `enum class StatusAluno`.
* **Jetpack Compose:** A interface do usuário é construída 100% com componentes do Compose.
* **Gerenciamento de Estado:** O estado da UI (inputs e resultados) é gerenciado com `remember` e `mutableStateOf`.
* **Estrutura de Dados:** Foi utilizada uma `MutableList<Double>` dentro da classe `Aluno` para armazenar as notas, conforme exigido.

## Tecnologias Utilizadas

* [Kotlin](https://kotlinlang.org/)
* [Android Studio](https://developer.android.com/studio)
* [Jetpack Compose](https://developer.android.com/jetpack/compose)


---

## Aluno

* **Nome Completo:** Isabela Chaves Pedroso
