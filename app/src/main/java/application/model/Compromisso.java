package application.model;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "compromissos")
public class Compromisso {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String descricao;
  // private String dataInicio;
  // private String dataFim;
  // private String horaInicio;
  // private String horaFim;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private Calendar dataHoraInicio;
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private Calendar dataHoraFim;
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getDescricao() {
    return descricao;
  }
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  public Calendar getDataHoraInicio() {
    return dataHoraInicio;
  }
  public void setDataHoraInicio(Calendar dataHoraInicio) {
    this.dataHoraInicio = dataHoraInicio;
  }
  public Calendar getDataHoraFim() {
    return dataHoraFim;
  }
  public void setDataHoraFim(Calendar dataHoraFim) {
    this.dataHoraFim = dataHoraFim;
  }
  
  // public String getDataInicio() {
  //   return dataInicio;
  // }
  // public void setDataInicio(String dataInicio) {
  //   this.dataInicio = dataInicio;
  // }
  // public String getDataFim() {
  //   return dataFim;
  // }
  // public void setDataFim(String dataFim) {
  //   this.dataFim = dataFim;
  // }
  // public String getHoraInicio() {
  //   return horaInicio;
  // }
  // public void setHoraInicio(String horaInicio) {
  //   this.horaInicio = horaInicio;
  // }
  // public String getHoraFim() {
  //   return horaFim;
  // }
  // public void setHoraFim(String horaFim) {
  //   this.horaFim = horaFim;
  // }
}
