package br.com.servicos.service;

import br.com.servicos.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> getAll();
    Item getById(String id);
    Item editItem(Item item, String id);
    Boolean removeItem(String id);

    List<Item> getByNome(String nome);

    Item addItem(Item item);

    List<Item> createItems();
}
