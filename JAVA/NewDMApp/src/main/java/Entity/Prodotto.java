package Entity;

import db.DatabaseConnection;
import db.query;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prodotto {

    private int acquistato;
    private double prezzo;
    private long codice;
    private String Nome;
    private int quantità;

    public Prodotto(double prezzo, long codice, String Nome, int quantità) {
        this.prezzo = prezzo;
        this.codice = codice;
        this.Nome = Nome;
        this.quantità = quantità;
    }

    /**
     *
     * @param cod
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
     *
     *
     */
    public int updateAcquistato(int q)
    {
        return this.acquistato+=q;
    }

    /**
     * Restituisce il prezzo del Prodotto che lo invoca
     * @return prezzo del prodotto
     */
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

    public int getQuantità() {
        return quantità;
    }

    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }
}
