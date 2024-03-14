package br.com.servicos.service;

import br.com.servicos.model.Item;
import br.com.servicos.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item getById(String id) {
        return itemRepository.findById(Integer.parseInt(id)).orElse(null);
    }

    @Override
    public Item editItem(Item itemAtualizado, String id) {
        Item item = itemRepository.findById(Integer.parseInt(id)).orElse(null);
        if(item != null) {
            itemAtualizado.setId(item.getId());
            return itemRepository.save(itemAtualizado);
        }
        return null;
    }

    @Override
    public Boolean removeItem(String id) {
        Item item = itemRepository.findById(Integer.parseInt(id)).orElse(null);
        if(item != null) {
            itemRepository.deleteById(Integer.parseInt(id));
            return true;
        }
        return false;
    }

    @Override
    public List<Item> getByNome(String nome) {
        return itemRepository.findAll().stream().filter(item -> item.getNome().contains(nome)).collect(Collectors.toList());
    }

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> createItems() {
        List<Item> items = randomItems();
        return itemRepository.saveAll(items);
    }

    private List<Item> randomItems() {
        Item item1 = new Item("Chocolate Fino", BigDecimal.valueOf(59.99));
        Item item2 = new Item("Chocolate Humilde", BigDecimal.valueOf(5.99));
        Item item3 = new Item("Refrigerante", BigDecimal.valueOf(7.99));
        Item item4 = new Item("Biscoito", BigDecimal.valueOf(9.99));
        Item item5 = new Item("Água", BigDecimal.valueOf(3.99));
        Item item6 = new Item("Suco", BigDecimal.valueOf(12.99));
        Item item7 = new Item("Cerveja Nacional", BigDecimal.valueOf(10.99));
        Item item8 = new Item("Cerveja Importada", BigDecimal.valueOf(17.99));
        return List.of(item1, item2, item3, item4, item5, item6, item7, item8);
    }
}
