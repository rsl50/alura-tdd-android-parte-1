package br.com.alura.leilao.util;

import org.junit.Test;

import br.com.alura.leilao.builder.LeilaoBuilder;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

import static br.com.alura.leilao.util.MoedaUtil.formataParaBrasileiro;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class MoedaUtilTest {

    private final Leilao CONSOLE = new LeilaoBuilder("Console")
            .lance(new Usuario("Robson"), 100.0)
            .lance(new Usuario("Fran"), 200.0)
            .build();

    @Test
    public void deve_DevolveMaiorLanceNoFormatoBrasileiro_QuandoRecebeValor() {
        String valorFormatadoDevolvido = formataParaBrasileiro(CONSOLE.getMaiorLance());
        assertThat(valorFormatadoDevolvido, is(equalTo("R$ 200,00")));
    }

    @Test
    public void deve_DevolveMenorLanceNoFormatoBrasileiro_QuandoRecebeValor() {
        String valorFormatadoDevolvido = formataParaBrasileiro(CONSOLE.getMenorLance());
        assertThat(valorFormatadoDevolvido, is(equalTo("R$ 100,00")));
    }

    @Test
    public void deve_DevolveLanceNoFormatoBrasileiro_QuandoRecebeValorZero() {
        String valorFormatadoDevolvido = formataParaBrasileiro(0.0);
        assertThat(valorFormatadoDevolvido, is(equalTo("R$ 0,00")));
    }

    @Test
    public void deve_DevolveLanceNoFormatoBrasileiro_QuandoRecebeValorNegativo() {
        String valorFormatadoDevolvido = formataParaBrasileiro(-100.0);
        assertThat(valorFormatadoDevolvido, is(equalTo("R$- 100,00")));
    }
}
