package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    @Test
    public void getDescricaoQuandoRecebeDescricaoDevolveDescricao() {
        // Criar caso de teste
        Leilao console = new Leilao("Console");

        // Executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        // Testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void getMaiorLanceQuandoRecebeApenasUmLanceDevolveMaiorLance() {
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Robson"), 200.0));

        double maiorLanceDevolvidoDoConsole = console.getMaiorLance();

        // delta
        // verifica a diferença entre os valores de ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes
        assertEquals(200.0, maiorLanceDevolvidoDoConsole, 0.0001);
    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescenteDevolveMaiorLance() {
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Robson"), 100.0));
        computador.propoe(new Lance(new Usuario("Fran"), 200.0));

        double maiorLanceDevolvidoDoComputador = computador.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvidoDoComputador, 0.0001);
    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescenteDevolveMaiorLance() {
        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Robson"), 10000.0));
        carro.propoe(new Lance(new Usuario("Fran"), 9000.0));

        double maiorLanceDevolvidoDoCarro = carro.getMaiorLance();
        assertEquals(10000.0, maiorLanceDevolvidoDoCarro, 0.0001);
    }
}