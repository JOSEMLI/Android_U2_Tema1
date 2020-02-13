package com.example.android_u2_tema1;

public class Cliente  {
  private String codigo;
  private String nombre;
  private String Apellido;


  private String Sexo;
  private String celular;
  private String Domicilio;


  public Cliente(String codigo, String nombre, String apellido, String sexo, String celular, String domicilio) {
    this.codigo = codigo;
    this.nombre = nombre;
    Apellido = apellido;
    Sexo = sexo;
    this.celular = celular;
    Domicilio = domicilio;
  }

  public Cliente(String codigo, String nombre, String Apellido) {
    this.codigo = codigo;
    this.nombre = nombre;
    this.Apellido= Apellido;
  }
  public String getcodigo() {
    return codigo;
  }
  public String getNombre() {
    return nombre;
  }
  public String getApellido() {
    return Apellido;
  }

  public String getSexo() {
    return Sexo;
  }

  public void setSexo(String sexo) {
    Sexo = sexo;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public String getDomicilio() {
    return Domicilio;
  }

  public void setDomicilio(String domicilio) {
    Domicilio = domicilio;
  }
}
