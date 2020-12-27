package Entity;


import java.sql.Date;
import java.util.List;

/**
 * Lo Scontrino che viene emesso al termine degli acquisti.
 *
 *
 */
public class Scontrino {

    private List<Long> l = null;
    private double tot,resto,versato;
    private Date data = new Date(System.currentTimeMillis());


    /**
     * Inizializza uno scontrino alla data corrente dati i parametri
     *
     * @param l
     * @param tot
     * @param resto
     * @param versato
     */

    public Scontrino(List<Long> l, double tot, double resto, double versato) {
        this.l = l;
        this.tot = tot;
        this.resto = resto;
        this.versato = versato;
    }
}
