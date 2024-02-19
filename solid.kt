fun main() {
    println("Hello, world!!!") 

    /********************** SOLID *********************************/

    
  /**************** S: Single Resp..********************/

    //exemplo ruim
    class Funcionario {
        fun calcularSalario() {
            // Lógica para calcular o salário
        }

        fun gerarRelatorio() {
            // Lógica para gerar um relatório do funcionário
        }

        fun atualizarInformacoes() {
            // Lógica para atualizar informações do funcionário
        }
    }

    //um bom exemplo
    class Funcionario {
        fun calcularSalario() {
            // Lógica para calcular o salário
        }
    }

    class Relatorio {
        fun gerarRelatorio(funcionario: Funcionario) {
            // Lógica para gerar um relatório do funcionário
        }
    }

    class SistemaRH {
        fun atualizarInformacoes(funcionario: Funcionario) {
            // Lógica para atualizar informações do funcionário
        }
    }

    /**************** O: Open/Closed..********************/
    // efeito "plug"
    
    //exemplo péssimo
    enum class TipoConta {
        CONTA_CORRENTE,
        CONTA_POUPANCA
    }

    class ContaBancaria(var saldo: Double, var tipo: TipoConta) {
        fun calcularTaxa(): Double {
            return when (tipo) {
                TipoConta.CONTA_CORRENTE -> saldo * 0.05
                TipoConta.CONTA_POUPANCA -> saldo * 0.03
            }
        }
    }
    // se aparecerem outros tipos de contas, será necessário mudar a       
    // classe TipoConta...

    //exemplo bom
    interface CalculadoraTaxa {
        fun calcularTaxa(saldo: Double): Double
    }

    class ContaCorrenteTaxa : CalculadoraTaxa {
        override fun calcularTaxa(saldo: Double): Double {
            return saldo * 0.05
        }
    }

    class ContaPoupancaTaxa : CalculadoraTaxa {
        override fun calcularTaxa(saldo: Double): Double {
            return saldo * 0.03
        }
    }

    class ContaBancaria(var saldo: Double, var calculadoraTaxa: 
      CalculadoraTaxa) {
        fun calcularTaxa(): Double {
            return calculadoraTaxa.calcularTaxa(saldo)
        }
    }
    
  /**************** L: Liskov..********************/

  //exemplo nada legal...
  open class Retangulo(var altura: Int, var largura: Int) {
      open fun calcularArea(): Int {
          return altura * largura
      }
  }

  class Quadrado(lado: Int) : Retangulo(lado, lado) {
      override fun calcularArea(): Int {
          return lado * lado
      }
  }

  //exemplo aconselhável
  interface Forma {
      fun calcularArea(): Int
  }

  class Retangulo(var altura: Int, var largura: Int) : Forma {
      override fun calcularArea(): Int {
          return altura * largura
      }
  }

  class Quadrado(var lado: Int) : Forma {
      override fun calcularArea(): Int {
          return lado * lado
      }
  }
  
  /**************** I: segregação de interfaces..********************/
  //bad examble
  interface Documento {
      fun abrir()
      fun salvar()
      fun fechar()
  }

  class DocumentoWord : Documento {
      override fun abrir() {
          // Implementação para abrir um documento do Word
      }

      override fun salvar() {
          // Implementação para salvar um documento do Word
      }

      override fun fechar() {
          // Implementação para fechar um documento do Word
      }
  }

  //fancy example
  interface Abrivel {
      fun abrir()
  }

  interface Salvavel {
      fun salvar()
  }

  interface Fechavel {
      fun fechar()
  }

  class DocumentoWord : Abrivel, Salvavel, Fechavel {
      override fun abrir() {
          // Implementação para abrir um documento do Word
      }

      override fun salvar() {
          // Implementação para salvar um documento do Word
      }

      override fun fechar() {
          // Implementação para fechar um documento do Word
      }
  }
  
  /**************** D: inversão de dependência..********************/

  //exemplo abominável
  class GeradorRelatorio {
      private val bancoDeDados = BancoDeDados()

      fun gerarRelatorio(): String {
          val dados = bancoDeDados.obterDados()
          // Lógica para gerar o relatório com os dados
          return "Relatório gerado"
      }
  }

  class BancoDeDados {
      fun obterDados(): List<String> {
          // Lógica para obter os dados do banco de dados
          return listOf("Dado 1", "Dado 2", "Dado 3")
      }
  }

  //exemplo bom
  interface FonteDados {
      fun obterDados(): List<String>
  }

  class GeradorRelatorio(private val fonteDados: FonteDados) {
      fun gerarRelatorio(): String {
          val dados = fonteDados.obterDados()
          // Lógica para gerar o relatório com os dados
          return "Relatório gerado"
      }
  }

  class BancoDeDados : FonteDados {
      override fun obterDados(): List<String> {
          // Lógica para obter os dados do banco de dados
          return listOf("Dado 1", "Dado 2", "Dado 3")
      }
  }

}