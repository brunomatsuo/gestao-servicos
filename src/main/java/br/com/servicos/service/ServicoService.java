package br.com.servicos.service;

import br.com.servicos.model.Servico;

import java.util.List;

public interface ServicoService {
    List<Servico> getAll();
    Servico getById(String id);
    Servico editServico(Servico servico, String id);
    Boolean removeServico(String id);

    List<Servico> getByNome(String nome);

    Servico addServico(Servico servico);
    List<Servico> createServicos();
}
