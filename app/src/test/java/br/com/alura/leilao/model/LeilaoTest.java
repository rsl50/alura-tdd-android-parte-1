package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario ROBSON = new Usuario("Robson");

    // regras para nomear testes
    //[nome do método]_[estado de teste]_[resultado esperado]
    //[deve]_[resultado esperado]_[estado de teste]

    @Test
    public void deve_DevolveDescricao_QuandoRecebeDescricao_() {
        // Executar ação esperada
        String descricaoDevolvida = CONSOLE.getDescricao();

        // Testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(ROBSON, 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        // delta
        // verifica a diferença entre os valores de ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes
        assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        CONSOLE.propoe(new Lance(ROBSON, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        CONSOLE.propoe(new Lance(ROBSON, 10000.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 9000.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();
        assertEquals(10000.0, maiorLanceDevolvido, 0.0001);
    }



    @Test
    public void deve_DevolveMenorLance_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(ROBSON, 200.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        // delta
        // verifica a diferença entre os valores de ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes
        assertEquals(200.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        CONSOLE.propoe(new Lance(ROBSON, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 200.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();
        assertEquals(100.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        CONSOLE.propoe(new Lance(ROBSON, 10000.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 9000.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();
        assertEquals(9000.0, menorLanceDevolvido, 0.0001);
    }
}