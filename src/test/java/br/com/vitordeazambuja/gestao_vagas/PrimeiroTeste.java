package br.com.vitordeazambuja.gestao_vagas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PrimeiroTeste {

    @Test
    public void deve_ser_possivel_calcular_dois_numeros(){
        var resultado = calculate(2, 3);
        assertEquals(resultado,5);
    }

    @Test
    public void validar_valor_incorreto(){
        var resultado = calculate(2, 3);
        assertNotEquals(resultado,4);
    }

    public static int calculate(int num1, int num2){
        return num1 + num2;
    }
}
