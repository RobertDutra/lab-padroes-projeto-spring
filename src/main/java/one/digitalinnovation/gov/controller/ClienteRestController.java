package one.digitalinnovation.gov.controller;

import io.swagger.v3.oas.annotations.Operation;
import one.digitalinnovation.gov.exceptions.EntityNotFoundException;
import one.digitalinnovation.gov.model.Cliente;
import one.digitalinnovation.gov.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

    @Autowired
    private ClienteService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Serviço para buscar todos usuários.")
    public Iterable<Cliente> buscarTodos(){
        return service.buscarTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Serviço para buscar usuário por id.")
    public Cliente buscarPorId(@PathVariable Long id) throws EntityNotFoundException {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Serviço para inserir usuário.")
    public Cliente inserir(@RequestBody Cliente cliente){
       return service.inserir(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Serviço para atualizar usuário por id.")
    public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente cliente) throws EntityNotFoundException {
        return service.atualizar(id, cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Serviço para deletar usuário por id.")
    public void deletar(@PathVariable Long id) throws EntityNotFoundException {
        service.deletar(id);
    }

}
