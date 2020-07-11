package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    private Leilao console = new Leilao("Console");
    private Usuario robson = new Usuario("Robson");
    private Usuario fran = new Usuario("Fran");


    // regras para nomear testes
    //[nome do método]_[estado de teste]_[resultado esperado]
    //[deve]_[resultado esperado]_[estado de teste]

    @Test
    public void deve_DevolveDescricao_QuandoRecebeDescricao_() {
        // Executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        // Testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance() {
        console.propoe(new Lance(robson, 200.0));

        double maiorLanceDevolvido = console.getMaiorLance();

        // delta
        // verifica a diferença entre os valores de ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes
        assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        console.propoe(new Lance(robson, 100.0));
        console.propoe(new Lance(fran, 200.0));

        double maiorLanceDevolvido = console.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        console.propoe(new Lance(robson, 10000.0));
        console.propoe(new Lance(fran, 9000.0));

        double maiorLanceDevolvido = console.getMaiorLance();
        assertEquals(10000.0, maiorLanceDevolvido, 0.0001);
    }



    @Test
    public void deve_DevolveMenorLance_QuandoRecebeApenasUmLance() {
        console.propoe(new Lance(robson, 200.0));

        double menorLanceDevolvido = console.getMenorLance();

        // delta
        // verifica a diferença entre os valores de ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes
        assertEquals(200.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        console.propoe(new Lance(robson, 100.0));
        console.propoe(new Lance(fran, 200.0));

        double menorLanceDevolvido = console.getMenorLance();
        assertEquals(100.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        console.propoe(new Lance(robson, 10000.0));
        console.propoe(new Lance(fran, 9000.0));

        double menorLanceDevolvido = console.getMenorLance();
        assertEquals(9000.0, menorLanceDevolvido, 0.0001);
    }
}