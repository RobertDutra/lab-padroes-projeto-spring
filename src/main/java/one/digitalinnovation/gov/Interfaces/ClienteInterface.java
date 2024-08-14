package one.digitalinnovation.gov.Interfaces;

import one.digitalinnovation.gov.exceptions.EntityNotFoundException;
import one.digitalinnovation.gov.model.Cliente;

public interface ClienteInterface {

    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId(Long id) throws EntityNotFoundException;
    Cliente inserir(Cliente cliente);
    Cliente atualizar(Long id, Cliente cliente) throws EntityNotFoundException;
    void deletar(Long id) throws EntityNotFoundException;
}
