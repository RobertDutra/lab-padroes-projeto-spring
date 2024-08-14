package one.digitalinnovation.gov.service;

import one.digitalinnovation.gov.Interfaces.ClienteInterface;
import one.digitalinnovation.gov.Interfaces.ViaCepInterface;
import one.digitalinnovation.gov.exceptions.EntityNotFoundException;
import one.digitalinnovation.gov.model.Cliente;
import one.digitalinnovation.gov.model.Endereco;
import one.digitalinnovation.gov.repository.ClienteRepository;
import one.digitalinnovation.gov.repository.EnderecoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements ClienteInterface {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepInterface cepService;
    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) throws EntityNotFoundException {
        return clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id não encontrado!"));
    }

    @Override
    public Cliente inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
        return cliente;
    }

    @Override
    public Cliente atualizar(Long id, Cliente cliente) throws EntityNotFoundException {
        Cliente clienteBuscado = buscarPorId(id);
        BeanUtils.copyProperties(cliente, clienteBuscado, "id");
        clienteRepository.save(clienteBuscado);
        return clienteBuscado;
    }

    @Override
    public void deletar(Long id) throws EntityNotFoundException {
        buscarPorId(id);
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente){
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() ->{
            Endereco novoEndereco = cepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
