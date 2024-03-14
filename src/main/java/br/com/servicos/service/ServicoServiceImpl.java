package br.com.servicos.service;

import br.com.servicos.model.Item;
import br.com.servicos.model.Servico;
import br.com.servicos.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoServiceImpl implements ServicoService {

    @Autowired
    ServicoRepository servicoRepository;

    @Override
    public List<Servico> getAll() {
        return servicoRepository.findAll();
    }

    @Override
    public Servico getById(String id) {
        return servicoRepository.findById(Integer.parseInt(id)).orElse(null);
    }

    @Override
    public Servico editServico(Servico servico, String id) {
        Servico original = servicoRepository.findById(Integer.parseInt(id)).orElse(null);
        if(original != null) {
            servico.setId(original.getId());
            return servicoRepository.save(servico);
        }
        return null;
    }

    @Override
    public Boolean removeServico(String id) {
        Servico original = servicoRepository.findById(Integer.parseInt(id)).orElse(null);
        if(original != null) {
            servicoRepository.deleteById(original.getId());
            return true;
        }
        return false;
    }

    @Override
    public List<Servico> getByNome(String nome) {
        return servicoRepository.findAll().stream().filter(servico -> servico.getNome().contains(nome)).collect(Collectors.toList());
    }

    @Override
    public Servico addServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    @Override
    public List<Servico> createServicos() {
        List<Servico> servicos = randomServicos();
        return servicoRepository.saveAll(servicos);
    }

    private List<Servico> randomServicos() {
        Servico servico1 = new Servico("Café da Manhã", BigDecimal.valueOf(59.99));
        Servico servico2 = new Servico("Almoço", BigDecimal.valueOf(75.99));
        Servico servico3 = new Servico("Jantar", BigDecimal.valueOf(85.99));
        Servico servico4 = new Servico("Massagem", BigDecimal.valueOf(99.99));
        Servico servico5 = new Servico("Manicure", BigDecimal.valueOf(69.99));
        Servico servico6 = new Servico("Corte Cabelo", BigDecimal.valueOf(120.99));
        return List.of(servico1, servico2, servico3, servico4, servico5, servico6);
    }
}
