package com.example.personafitnessapplication.model;

public class Exercicio {

    public long idAluno;
    public String exercicio;
    public int repeticao;
    public int frequencia;
    public int tempo;
    public String mensagem;

    public Exercicio(){

    }
    public Exercicio(String exercicio, int repeticao, int frequencia) {
        this.exercicio = exercicio;
        this.repeticao = repeticao;
        this.frequencia = frequencia;
    }


    public long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(long idAluno) {
        this.idAluno = idAluno;
    }

    public String getExercicio() {
        return exercicio;
    }

    public void setExercicio(String exercicio) {
        this.exercicio = exercicio;
    }

    public int getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(int repeticao) {
        this.repeticao = repeticao;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }


}
