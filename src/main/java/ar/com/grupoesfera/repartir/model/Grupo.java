package ar.com.grupoesfera.repartir.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Entity
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nombre;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "miembro")
    private List<String> miembros;

    private BigDecimal total;

    public Grupo() {
    }

    public Grupo(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<String> miembros) {
        this.miembros = miembros;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean estaFormado() {

        return (miembros != null) && (miembros.size() > 1);
    }

    public boolean tieneMiembrosRepetidos() {

        if (miembros == null) {
            return false;
        }

        Set<String> nombres = new HashSet<>();

        for (String miembro : miembros) {

            if (!nombres.add(sinDistinguirMayusculas(miembro))) {
                return true;
            }
        }

        return false;
    }

    private String sinDistinguirMayusculas(String miembro) {

        return (miembro == null) ? null : miembro.toLowerCase(Locale.ROOT);
    }
}
