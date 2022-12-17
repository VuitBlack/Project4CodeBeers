package codebeers.Hibernate;

import codebeers.modelo.Cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Clientes")
public class Clientes_ORM {

    @Id
    @Column(name="email")
    public String email;

    @Column(name="nombre")
    public String nombre;

    @Column(name="domicilio")
    public String domicilio;

    @Column(name="nif")
    public String nif;

    @Column(name="tipoCliente")
    public String tipoCliente;

    public Clientes_ORM() {
    }

    public Clientes_ORM(String email, String nombre, String domicilio, String nif, String tipoCliente) {
        this.email = email;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
        this.tipoCliente = tipoCliente;
    }

    public Clientes_ORM(Cliente cliente){
        email = cliente.getEmail();
        nombre = cliente.getNombre();
        domicilio = cliente.getDomicilio();
        nif = cliente.getNif();
        tipoCliente = cliente.tipoCliente();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public String toString() {
        return "cliente{" +
                "email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", nif='" + nif + '\'' +
                ", tipoCliente='" + tipoCliente + '\'' +
                '}';
    }
}
