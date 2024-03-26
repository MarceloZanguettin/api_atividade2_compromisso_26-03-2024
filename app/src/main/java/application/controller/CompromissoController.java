package application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Compromisso;
import application.repository.CompromissoRepository;

@RestController
public class CompromissoController {
  @Autowired
  private CompromissoRepository compromissoRepo;

  @GetMapping("/compromisso")
  public List<Compromisso> getCompromisso() {
    return (List<Compromisso>) compromissoRepo.findAll();
  }

  @PostMapping("/compromisso")
  public Compromisso postCompromisso(@RequestBody Compromisso compromisso) {
    return compromissoRepo.save(compromisso);
  }

  @GetMapping("/compromisso/{id}")
  public Compromisso getCompromisso(@PathVariable Long id) {
    return compromissoRepo.findById(id).get();
  }

  @PutMapping("/compromisso/{id}")
  public Compromisso putCompromisso(@RequestBody Compromisso compromisso, @PathVariable Long id) {
    Optional<Compromisso> resposta = compromissoRepo.findById(id);
    if (resposta.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    resposta.get().setDescricao(compromisso.getDescricao());
    resposta.get().setDataInicio(compromisso.getDataInicio());
    resposta.get().setDataFim(compromisso.getDataFim());
    resposta.get().setHoraInicio(compromisso.getHoraInicio());
    resposta.get().setHoraFim(compromisso.getHoraFim());

    return compromissoRepo.save(resposta.get());
  }

  @DeleteMapping("/compromisso/{id}")
  public void deleteCompromisso(@PathVariable Long id) {
    if (compromissoRepo.existsById(id)) {
      compromissoRepo.deleteById(id);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

}
