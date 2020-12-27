package Entity;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Exceptions.ProdottoNotFoundException;
import com.mysql.cj.PreparedQuery;
import db.DatabaseConnection;
import db.query;
import org.apache.commons.codec.digest.DigestUtils;


/**
 * Lo Scontrino che viene emesso al termine degli acquisti.
 *
 *
 */
public class Scontrino {

    private List<Prodotto> l;
    private double tot = 0,resto,versato;
    private final String data = new Date(System.currentTimeMillis()).toString();
    private String riepilogo;
    private Long id;


    /**
     * Crea uno scontrino vuoto alla data corrente
     *
     */

    public Scontrino() {

    }

    /**
     *  Aggiunge un prodotto alla lista dei prodotti. Se la lista &egrave; vuota la inizializza
     *  e poi procede ad aggiungere.
     *  Se il prodotto richiesto non esiste lancia una eccezione.
     *
     * @param cod codice del prodotto
     * @throws ProdottoNotFoundException
     */
   public void addProdotto(Long cod) throws ProdottoNotFoundException {
        if(l==null)
            l=new ArrayList<Prodotto>();
        Prodotto p = Prodotto.search(cod);
        if(p==null)
            throw new ProdottoNotFoundException("Prodotto non trovato");
        else {
            if (l.contains(p)) {
                riepilogo.replaceFirst(p.getNome() + "   x " + p.getAcquistato() + "     " + p.getPrezzo() * p.getAcquistato(), p.getNome() + "   x " + p.updateAcquistato(1) + "     " + p.getPrezzo() * p.getAcquistato());
            }
            riepilogo += "\n" + p.getNome() + "   x " + p.updateAcquistato(1) + "     " + p.getPrezzo() * p.getAcquistato();
        }
   }

    /**
     * Calcola il totale dello scontrino
     */
   public void calcolaTot() {

       l.forEach((p) -> {
           this.tot += p.getPrezzo()*p.getAcquistato();
       });
   }

    /**
     * Calcola il resto da dare al Cliente
     */
   private void calcolaResto(){

        this.resto=this.versato-this.tot;

   }

    public void setVersato(double versato) {
        this.versato = versato;
        calcolaResto();
    }

    public double getTot() {
        return tot;
    }

    public double getResto() {
        return resto;
    }

    public String getRiepilogo() {
        return riepilogo;
    }



    public void save() throws SQLException {

        PreparedStatement prep = DatabaseConnection.con.prepareStatement(query.login);
        prep.setString(1,this.data);
        prep.setDouble(2,this.versato);
        prep.setDouble(3,this.tot);
        prep.setDouble(4,this.resto);
        ResultSet res = prep.executeQuery();
        res.next();
        this.id=res.getLong("id");

    }
}
