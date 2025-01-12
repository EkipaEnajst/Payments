package org.ekipaenajst.entitete;

import javax.persistence.*;
import java.io.Serializable;
import java.time.*;

@Entity
@Table(name="parkirnina")
@NamedQueries(value = {
        @NamedQuery(name = "Parkirnina.findId", query="SELECT p from Parkirnina p WHERE p.id= :idParam"),
        @NamedQuery(name = "Parkirnina.findAvto", query="SELECT p from Parkirnina p WHERE p.avto_id= :avtoParam ORDER BY p.konec DESC"),
        @NamedQuery(name = "Parkirnina.findUser", query="SELECT p from Parkirnina p WHERE p.uporabnik_id= :userParam ORDER BY p.konec DESC"),
})
public class Parkirnina implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private int uporabnik_id;

    private int avto_id;

    private LocalDateTime zacetek;

    private LocalDateTime konec;

    private int parkirisce_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getZacetek() {
        return zacetek;
    }

    public void setZacetek(LocalDateTime zacetek) {
        this.zacetek = zacetek;
    }

    public LocalDateTime getKonec() {
        return konec;
    }

    public void setKonec(LocalDateTime konec) {
        this.konec = konec;
    }

    public int getParkirisce() {
        return parkirisce_id;
    }

    public void setParkirisce(int parkirisce_id) {this.parkirisce_id = parkirisce_id;}

    public int getAvto_id() {
        return avto_id;
    }

    public void setAvto_id(int parkirisce) {
        this.avto_id = avto_id;
    }

    public int getUporabnik() {return uporabnik_id;}

    public void setUporabnik(int uporabnik_id) {
        this.uporabnik_id = uporabnik_id;
    }

}
