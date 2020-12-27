package Entity;

import db.DatabaseConnection;
import db.query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prodotto {

    private int acquistato;
    private double prezzo;
    private long codice;
    private String Nome;
    private int quantity;

    public Prodotto(double prezzo, long codice, String Nome, int quantity) {
        this.prezzo = prezzo;
        this.codice = codice;
        this.Nome = Nome;
        this.quantity = quantity;
    }

    /**
     *Cerca un prodotto nel Database dato il codice
     * @param cod codice del prodotto
     */
    public static Prodotto search(Long cod) {

        PreparedStatement prep = null;
        try {
            prep = DatabaseConnection.con.prepareStatement(query.prodotto);
            prep.setLong(1,cod);
            ResultSet res = prep.executeQuery();

        if(res == null)
            return null;
        else{
            res.next();
            return new Prodotto(res.getDouble("Prezzo"),res.getLong("Codice"),
                    res.getString("Nome"),res.getInt("quantità"));
        }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }


    }

    /**
     *Aggiorna le unità di prodotto inserite nello scontrino presente nello scontrino
     * @param q unità da aggiungere
     * @return quantità totale nello scontrino
     */
    public int updateAcquistato(int q)
    {
        return this.acquistato+=q;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public String getNome() {
        return this.Nome;
    }

    public int getAcquistato() {
        return acquistato;
    }

    public void setAcquistato(int acquistato) {
        this.acquistato = acquistato;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public long getCodice() {
        return codice;
    }

    public void setCodice(long codice) {
        this.codice = codice;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Legge dal DB la quantità attuale del prodotto
     */
    private void updateQuantity(){

        PreparedStatement prep = null;
        try {
            prep = DatabaseConnection.con.prepareStatement("select prod.quantità from Prodotto as p where p.id="+this.codice);
            ResultSet res = prep.executeQuery();
            res.next();
            setQuantity(res.getInt("quantità"));
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Aggiorna la quantità del prodotto nel DB
     */

    public void updateDB() {

        PreparedStatement prep = null;
        try {
            prep = DatabaseConnection.con.prepareStatement(query.upQuant);
            updateQuantity();
            prep.setInt(1,this.quantity -this.acquistato);
            prep.setLong(2,codice);
            prep.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
