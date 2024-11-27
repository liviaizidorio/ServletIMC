package org.example.servletimc.model;

public class IMC {
    private int id;
    private String nome;
    private String cpf;
    private int altura;
    private double peso;
    private double imc;

    //Métodos construtores
    public IMC() {
    }
    public IMC(int id, String nome, String cpf, int altura, double peso, double imc) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.altura = altura;
        this.peso = peso;
        this.imc = imc;
    }

    //GETTERS E SETTERS
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getAltura() {
        return altura;
    }

    public double getPeso() {
        return peso;
    }

    public double getImc() {
        return imc;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }
}
