package br.com.alura.leilao.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MoedaUtil {

    public static String formataParaBrasileiro(double valor) {
        NumberFormat moeda = DecimalFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return moeda.format(valor).replace("-R$", "R$-");
    }
}
