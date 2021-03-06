package br.com.alura.leilao.model;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import br.com.alura.leilao.builder.LeilaoBuilder;
import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;

import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario ROBSON = new Usuario("Robson");

    // regras para nomear testes
    //[nome do método]_[estado de teste]_[resultado esperado]
    //[deve]_[resultado esperado]_[estado de teste]

    @Test
    public void deve_DevolveDescricao_QuandoRecebeDescricao() {
        // Executar ação esperada
        String descricaoDevolvida = CONSOLE.getDescricao();

        // Testar resultado esperado
        assertThat(descricaoDevolvida, is(equalTo("Console")));
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(ROBSON, 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        // delta
        // verifica a diferença entre os valores de ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes
        assertThat(maiorLanceDevolvido, closeTo(200.0, DELTA));
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        CONSOLE.propoe(new Lance(ROBSON, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, DELTA);
    }



    @Test
    public void deve_DevolveMenorLance_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(ROBSON, 200.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        // delta
        // verifica a diferença entre os valores de ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes
        assertEquals(200.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        CONSOLE.propoe(new Lance(ROBSON, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 200.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();
        assertEquals(100.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances() {
        CONSOLE.propoe(new Lance(ROBSON, 200.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 300.0));
        CONSOLE.propoe(new Lance(ROBSON, 400.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        //assertEquals(3, tresMaioresLancesDevolvidos.size());
        //assertThat(tresMaioresLancesDevolvidos, hasSize(equalTo(3)));

        //assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        //assertThat(tresMaioresLancesDevolvidos, hasItem(new Lance(ROBSON, 400.0)));

        //assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        //assertEquals(200.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);

        //assertThat(tresMaioresLancesDevolvidos, contains(
                //new Lance(ROBSON, 400.0),
                //new Lance(new Usuario("Fran"), 300.0),
                //new Lance(ROBSON, 200.0)));

        assertThat(tresMaioresLancesDevolvidos,
                both(Matchers.<Lance>hasSize(3))
                .and(contains(
                        new Lance(ROBSON, 400.0),
                        new Lance(new Usuario("Fran"), 300.0),
                        new Lance(ROBSON, 200.0))));
    }



    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLance() {
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(ROBSON, 200.0));
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasDoisLances() {
        CONSOLE.propoe(new Lance(ROBSON, 300.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 400.0));
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLances() {
        CONSOLE.propoe(new Lance(ROBSON, 300.0));
        final Usuario FRAN = new Usuario("Fran");

        CONSOLE.propoe(new Lance(FRAN, 400.0));
        CONSOLE.propoe(new Lance(ROBSON, 500.0));
        CONSOLE.propoe(new Lance(FRAN, 600.0));


        List<Lance> tresMaioresLancesDevolvidosParaQuatroLances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaQuatroLances.size());
        assertEquals(600.0, tresMaioresLancesDevolvidosParaQuatroLances.get(0).getValor(), DELTA);
        assertEquals(500.0, tresMaioresLancesDevolvidosParaQuatroLances.get(1).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidosParaQuatroLances.get(2).getValor(), DELTA);


        CONSOLE.propoe(new Lance(ROBSON, 700.0));
        List<Lance> tresMaioresLancesDevolvidosParaCincoLances = CONSOLE.tresMaioresLances();
        assertEquals(3, tresMaioresLancesDevolvidosParaCincoLances.size());
        assertEquals(700.0, tresMaioresLancesDevolvidosParaCincoLances.get(0).getValor(), DELTA);
        assertEquals(600.0, tresMaioresLancesDevolvidosParaCincoLances.get(1).getValor(), DELTA);
        assertEquals(500.0, tresMaioresLancesDevolvidosParaCincoLances.get(2).getValor(), DELTA);
    }



    @Test
    public void deve_DevolverValorZeroParaMaiorLance_QuandoNaoTiverLances() {
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMenorLance_QuandoNaoTiverLances() {
        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(0, menorLanceDevolvido, DELTA);
    }

    @Test(expected = LanceMenorQueUltimoLanceException.class)
    public void deve_LancarException_QuandoReceberLanceMenorQueMaiorLance() {
        CONSOLE.propoe(new Lance(ROBSON, 500.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 400.0));
    }


    @Test(expected = LanceSeguidoDoMesmoUsuarioException.class)
    public void deve_LancarException_QuandoForOMesmoUsuarioDoUltimoLance() {
        CONSOLE.propoe(new Lance(ROBSON, 500.0));
        CONSOLE.propoe(new Lance(ROBSON, 600.0));
    }

    @Test(expected = UsuarioJaDeuCincoLancesException.class)
    public void deve_LancarException_QuandoUsuarioDerCincoLances() {

        final Usuario FRAN = new Usuario("Fran");
        final Leilao console = new LeilaoBuilder("Console")
            .lance(ROBSON, 100.0)
            .lance(FRAN, 200.0)
            .lance(ROBSON, 300.0)
            .lance(FRAN, 400.0)
            .lance(ROBSON, 500.0)
            .lance(FRAN, 600.0)
            .lance(ROBSON, 700.0)
            .lance(FRAN, 800.0)
            .lance(ROBSON, 900.0)
            .lance(FRAN, 1000.0)
            .lance(ROBSON, 11000.0)
            .build();
    }
}