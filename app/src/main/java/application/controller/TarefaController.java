package application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Tarefa;
import application.repository.TarefaRepository;

@RestController
public class TarefaController {
  @Autowired
  private TarefaRepository tarefaRepo;

  @GetMapping("/tarefa")
  public List<Tarefa> getTarefa() {
    return (List<Tarefa>) tarefaRepo.findAll();
  }

  @PostMapping("/tarefa")
  public Tarefa postTarefa(@RequestBody Tarefa tarefa) {
    if(tarefa.getDescricao() == null) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST, "O valor do campo 'descrição' não pode ser nulo"
      );
    }
    return tarefaRepo.save(tarefa);
  }

  @GetMapping("/tarefa/{id}")
  public Tarefa getTarefa(@PathVariable Long id) {
    Optional<Tarefa> resultado = tarefaRepo.findById(id);
      if(resultado.isEmpty()) {
        throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Tarefa não encontrada!"
        );
      }
    return resultado.get();
  }

  @PutMapping("/tarefa/{id}")
  public Tarefa putTarefa(@RequestBody Tarefa tarefa, @PathVariable Long id) {
    Optional<Tarefa> resposta = tarefaRepo.findById(id);
    if (resposta.isEmpty()) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Tarefa não encontrada!"
        );
    }
    if(tarefa.getDescricao() == null) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST, "O valor do campo 'descrição' não pode ser nulo"
      );
    }
    resposta.get().setDescricao(tarefa.getDescricao());
    resposta.get().setConcluido(tarefa.isConcluido());

    return tarefaRepo.save(resposta.get());
  }

  @PatchMapping("/tarefa/{id}")
  public Tarefa pathTarefa(@RequestBody Tarefa tarefa, @PathVariable Long id) {
    Tarefa resultado = tarefaRepo.findById(id).get();
    if(tarefa.getDescricao() != null ) {
      resultado.setDescricao(tarefa.getDescricao());
    }
    resultado.setConcluido(tarefa.isConcluido());
    return tarefaRepo.save(resultado);
  }

  @DeleteMapping("/tarefa/{id}")
  public void deleteTarefa(@PathVariable Long id) {
    if (tarefaRepo.existsById(id)) {
      tarefaRepo.deleteById(id);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Tarefa não encontrada!"
        );
    }
  }

}
