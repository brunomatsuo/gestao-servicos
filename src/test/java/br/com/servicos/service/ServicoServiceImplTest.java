package br.com.servicos.service;

import br.com.servicos.model.Servico;
import br.com.servicos.repository.ServicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ServicoServiceImplTest {

    @Mock
    ServicoRepository servicoRepository;

    Servico servico1 = new Servico(1, "teste", BigDecimal.TEN);
    Servico servico2 = new Servico(2, "teste2", BigDecimal.ONE);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll() {
        servicoRepository.saveAll(List.of(servico1, servico2));

        Mockito.when(servicoRepository.findAll()).thenReturn(List.of(servico1, servico2));

        List<Servico> servicos = servicoRepository.findAll();

        assertEquals(servicos, List.of(servico1, servico2));
    }

    @Test
    void getById() {
        servicoRepository.save(servico1);

        Mockito.when(servicoRepository.findById(servico1.getId())).thenReturn(Optional.of(servico1));

        Servico servico = servicoRepository.findById(servico1.getId()).orElse(null);

        assertEquals(servico, servico1);
    }

    @Test
    void editServico() {
        servicoRepository.save(servico1);
        servico1.setNome("Item editado");

        Mockito.when(servicoRepository.findById(servico1.getId())).thenReturn(Optional.of(servico1));

        servicoRepository.save(servico1);
        Servico servico = servicoRepository.findById(servico1.getId()).orElse(null);

        assertEquals(servico.getNome(), servico1.getNome());
    }

    @Test
    void removeServico() {
        servicoRepository.save(servico1);

        Mockito.when(servicoRepository.findById(servico1.getId())).thenReturn(null);

        servicoRepository.deleteById(servico1.getId());
        Optional<Servico> servico = servicoRepository.findById(servico1.getId());

        assertEquals(servico, null);
    }

    @Test
    void getByNome() {
        servicoRepository.save(servico1);
        servicoRepository.save(servico2);
        String nome = "teste";

        Mockito.when(servicoRepository.findAll().stream().filter(servico -> servico.getNome().contains(nome)).collect(Collectors.toList())).thenReturn(List.of(servico1, servico2));

        List<Servico> servicos = servicoRepository.findAll().stream().filter(servico -> servico.getNome().contains(nome)).collect(Collectors.toList());

        assertEquals(servicos, List.of(servico1, servico2));
    }

    @Test
    void addServico() {
        Mockito.when(servicoRepository.save(servico1)).thenReturn(servico1);

        Servico servico = servicoRepository.save(servico1);

        assertEquals(servico, servico1);
    }
}