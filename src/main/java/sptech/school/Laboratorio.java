package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.VacinaInvalidaException;
import school.sptech.exception.VacinaNaoEncontradaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Laboratorio {
    String nome;
    List<Vacina> vacinas;

    public Laboratorio(){
        this.vacinas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Vacina> getVacinas() {
        return vacinas;
    }

    public void adicionarVacina(Vacina vacina){
        if (vacina == null){
            throw new VacinaInvalidaException();
        } else if (vacina.getCodigo() == null || vacina.getCodigo().isEmpty()) {
            throw new VacinaInvalidaException();
        } else if (vacina.getNome() == null || vacina.getNome().isEmpty()) {
            throw new VacinaInvalidaException();
        } else if (vacina.getTipo() == null || vacina.getTipo().isEmpty()) {
            throw new VacinaInvalidaException();
        } else if (vacina.getPreco() <= 0 || vacina.getPreco().isNaN()) {
            throw new VacinaInvalidaException();
        } else if (vacina.getEficacia() > 5 || vacina.getEficacia() < 0) {
            throw new VacinaInvalidaException();
        } else if (vacina.getDataLancamento() == null || vacina.getDataLancamento().isAfter(LocalDate.now())) {
            throw new VacinaInvalidaException();
        }

        vacinas.add(vacina);
    }

    public Vacina buscarVacinaPorCodigo(String codigo){
        if (codigo == null || codigo.isEmpty() || codigo.isBlank()){
            throw new ArgumentoInvalidoException();
        }

        for (Vacina d: vacinas){
            if (d.getCodigo().contains(codigo)){
                return d;
            }
        }

        throw new VacinaNaoEncontradaException();
    }

    public void removerVacinaPorCodigo(String codigo){
        if (codigo == null || codigo.isEmpty() || codigo.isBlank()){
            throw new ArgumentoInvalidoException();
        }

        for (Vacina d: vacinas){
            if (d.getCodigo().contains(codigo)){
                vacinas.remove(d);
            }
        }

        throw new VacinaNaoEncontradaException();
    }

    public Vacina buscarVacinaComMelhorEficacia(){
        Vacina MaiorEficiencia = vacinas.getFirst();
        if (vacinas.isEmpty()){
            throw new VacinaNaoEncontradaException();
        }

        for (Vacina d: vacinas){
            LocalDate data = null;
            double eficacia = 0.0;

            if (d.getEficacia() > eficacia){
                eficacia = d.getEficacia();
            } else if (d.getEficacia() == eficacia && d.getDataLancamento().isAfter(MaiorEficiencia.getDataLancamento())) {
                MaiorEficiencia = d;
            }
        }

        return MaiorEficiencia;
    }

    public List<Vacina> buscarVacinaPorPeriodo(LocalDate dataInicio, LocalDate dataFim){
        if (dataInicio == null || dataFim == null || dataFim.isAfter(dataInicio)){
            throw new ArgumentoInvalidoException();
        }

        List<Vacina> buscaPeriodo = new ArrayList<>();
        for (Vacina d: vacinas){
            if (d.getDataLancamento().isAfter(dataInicio) && d.getDataLancamento().isBefore(dataFim)){
                buscaPeriodo.add(d);
            } else if (d.getDataLancamento().isEqual(dataInicio)) {
                buscaPeriodo.add(d);
            } else if (d.getDataLancamento().isEqual(dataFim)) {
                buscaPeriodo.add(d);
            }
        }
        return buscaPeriodo;
    }
}
