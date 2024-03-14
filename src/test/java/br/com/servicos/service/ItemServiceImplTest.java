package br.com.servicos.service;

import br.com.servicos.model.Item;
import br.com.servicos.repository.ItemRepository;
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

class ItemServiceImplTest {

    @Mock
    ItemRepository itemRepository;

    Item item1 = new Item(1, "teste", BigDecimal.TEN);
    Item item2 = new Item(2, "teste2", BigDecimal.ONE);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll() {
        itemRepository.saveAll(List.of(item1, item2));

        Mockito.when(itemRepository.findAll()).thenReturn(List.of(item1, item2));

        List<Item> items = itemRepository.findAll();

        assertEquals(items, List.of(item1, item2));
    }

    @Test
    void getById() {
        itemRepository.save(item1);

        Mockito.when(itemRepository.findById(item1.getId())).thenReturn(Optional.of(item1));

        Item item = itemRepository.findById(item1.getId()).orElse(null);

        assertEquals(item, item1);
    }

    @Test
    void editItem() {
        itemRepository.save(item1);
        item1.setNome("Item editado");

        Mockito.when(itemRepository.findById(item1.getId())).thenReturn(Optional.of(item1));

        itemRepository.save(item1);
        Item item = itemRepository.findById(item1.getId()).orElse(null);

        assertEquals(item.getNome(), item1.getNome());
    }

    @Test
    void removeItem() {
        itemRepository.save(item1);

        Mockito.when(itemRepository.findById(item1.getId())).thenReturn(null);

        itemRepository.deleteById(item1.getId());
        Optional<Item> item = itemRepository.findById(item1.getId());

        assertEquals(item, null);
    }

    @Test
    void getByNome() {
        itemRepository.save(item1);
        itemRepository.save(item2);
        String nome = "teste";

        Mockito.when(itemRepository.findAll().stream().filter(item -> item.getNome().contains(nome)).collect(Collectors.toList())).thenReturn(List.of(item1, item2));

        List<Item> items = itemRepository.findAll().stream().filter(item -> item.getNome().contains(nome)).collect(Collectors.toList());

        assertEquals(items, List.of(item1, item2));
    }

    @Test
    void addItem() {
        Mockito.when(itemRepository.save(item1)).thenReturn(item1);

        Item item = itemRepository.save(item1);

        assertEquals(item, item1);
    }
}