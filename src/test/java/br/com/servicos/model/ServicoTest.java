package br.com.servicos.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ServicoTest {

    private final Servico servico = new Servico(1, "Teste", BigDecimal.TEN);

    @Test
    void getId() {
        assertThat(servico.getId().equals(1));
    }

    @Test
    void getNome() {
        assertThat(servico.getNome().equals("Teste"));
    }

    @Test
    void getValorUnitario() {
        assertThat(servico.getValorUnitario().equals(BigDecimal.TEN));
    }
}