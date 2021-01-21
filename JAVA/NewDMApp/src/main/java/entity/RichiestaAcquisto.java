package entity;

import db.RichiestaAcquistoDao;
import exceptions.DatabaseException;
import exceptions.ProdottoException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RichiestaAcquisto {

    Long codice,id;
    int quantity;
    String data,stato;
    private static final String DA_ESEGUIRE = "Da Eseguire";
    private static final String ESEGUITO = "Eseguito";

    public RichiestaAcquisto() {
       this.data=setData();
       this.stato= DA_ESEGUIRE;
    }

    public Long getCodice() {
        return codice;
    }

    public void setCodice(Long codice) {
        this.codice = codice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    private String setData() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return date.format(myFormatObj);
    }

    @Override
    public String toString() {
        return "RichiestaAcquisto{" +
                "codice=" + codice +
                ", quantity=" + quantity +
                ", data='" + data + '\'' +
                ", stato='" + stato + '\'' +
                '}';
    }

    public void save() throws DatabaseException {
        RichiestaAcquistoDao.save(this);
    }
}
