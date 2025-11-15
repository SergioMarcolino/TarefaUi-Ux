# Análise de Código e Teste de Caixa Branca

---

## 2. Análise de Fluxo (Método `verificarUsuario` Original)

### 2.1. Notação de Grafo de Fluxo


* **Nó 1 (Entrada):** Inicia o método, define `sql` e chama `conectarBD()`.
* **Nó 2 (Try):** Entra no bloco `try`, cria `Statement` e `ResultSet`.
* **Nó 3 (Decisão 1):** `if (rs.next())`
* **Nó 4 (If-True):** `result = true; nome = rs.getString("nome");`
* **Nó 5 (Catch):** `catch (Exception e)`
* **Nó 6 (Saída):** `return result;`

### 2.2. Cálculo da Complexidade Ciclomática (V(G))

A complexidade ciclomática define o número de caminhos de teste independentes.

**Método 1: V(G) = Predicados + 1**
* Predicado 1: `if (rs.next())`
* Predicado 2: Bloco `try...catch` 
* Cálculo: `V(G) = 2 + 1 = 3`

**Método 2: V(G) = Arestas - Nós + 2**
* Nós (N) = 6
* Arestas (A) = 7 (1->2, 2->3, 2->5, 3->4, 3->6, 4->6, 5->6)
* Cálculo: `V(G) = 7 - 6 + 2 = 3`

A complexidade ciclomática do método é 3.

### 2.3. Caminhos Básicos

Com base em V(G) = 3, existem 3 caminhos básicos independentes para teste:

1.  **Caminho 1 (Usuário não encontrado):**
    * Fluxo: `1 -> 2 -> 3 -> 6`
    * Descrição: O `try` funciona, o `if (rs.next())` é falso (login/senha errados) e o método retorna.
    * Teste: Usar um login e senha inválidos.

2.  **Caminho 2 (Usuário encontrado):**
    * Fluxo: `1 -> 2 -> 3 -> 4 -> 6`
    * Descrição: O `try` funciona, o `if (rs.next())` é verdadeiro, os dados são lidos e o método retorna.
    * Teste: Usar um login e senha válidos.

3.  **Caminho 3 (Exceção no BD):**
    * Fluxo: `1 -> 2 -> 5 -> 6`
    * Descrição: O `try` falha , a exceção é capturada e o método retorna.
    * Teste: Desligar o banco de dados ou fornecer uma query com sintaxe errada.

---
