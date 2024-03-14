package br.com.servicos.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


class ItemTest {

    private final Item item = new Item(1,"Teste", BigDecimal.TEN);

    @Test
    void getId() {
        assertThat(item.getId().equals(1));
    }

    @Test
    void getNome() {
        assertThat(item.getNome().equals("Teste"));
    }

    @Test
    void getValorUnitario() {
        assertThat(item.getValorUnitario().equals(BigDecimal.TEN));
    }
}