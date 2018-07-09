package com.algaworks.cobranca.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
public class Titulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotEmpty(message = "Descrição  não pode ser nulo")
    @Size(max = 60, min = 3, message = "Descrição tem no mínimo 3 e no máximo 60 caracteres ")
    private String descricao;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Data  não pode ser nulo")
    private Date dataVencimento;

    @NumberFormat(pattern = "#,##0.00")
    @NotNull(message = "Valor não pode ser nulo")
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private StatusTitulo status;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public StatusTitulo getStatus() {
        return status;
    }

    public void setStatus(StatusTitulo status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Titulo titulo = (Titulo) o;
        return Objects.equals(codigo, titulo.codigo);
    }

    public boolean isPendente(){
        return  StatusTitulo.PENDENTE.equals(status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(codigo);
    }
}
