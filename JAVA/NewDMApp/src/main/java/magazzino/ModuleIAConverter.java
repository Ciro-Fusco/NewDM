package magazzino;

import magazzino.Prodotto;
import magazzino.RandomForestClassifier;

public class ModuleIAConverter {

  public static int prevedi(
      Prodotto p, double sped, String stagione, String tipoSupermerc, String festività) {

    double[] features = new double[21];
    features[0] = p.getPrezzo();
    features[1] = sped;
    switch (p.getTipologia().toLowerCase()) {
      case "carne":
        features[2] = 1;
        break;
      case "casa":
        features[3] = 1;
        break;

      case "elettronica":
        features[4] = 1;
        break;

      case "fruttaverdura":
        features[5] = 1;
        break;

      case "pesce":
        features[6] = 1;
        break;

      default:
        return -1;
    }
    switch (stagione.toLowerCase()) {
      case "autunno":
        features[7] = 1;
        break;
      case "estate":
        features[8] = 1;
        break;
      case "inverno":
        features[9] = 1;
        break;
      case "primavera":
        features[10] = 1;
        break;

      default:
        return -1;
    }

    switch (tipoSupermerc.toLowerCase()) {
      case "periferia":
        features[11] = 1;
        break;
      case "residenziale":
        features[12] = 1;
        break;
      default:
        return -1;
    }

    switch (festività.toLowerCase()) {
      case "feriale":
        features[13] = 1;
        break;
      case "lavorativo":
        features[14] = 1;
        break;
      default:
        return -1;
    }

    switch (p.getScadenza().toLowerCase()) {
      case "breve":
        features[15] = 1;
        break;
      case "lunga":
        features[16] = 1;
        break;
      case "media":
        features[17] = 1;
        break;
      default:
        return -1;
    }

    switch (p.getDimensioneConfezione().toLowerCase()) {
      case "grande":
        features[18] = 1;
        break;
      case "media":
        features[19] = 1;
        break;
      case "piccola":
        features[20] = 1;
        break;
      default:
        return -1;
    }

    switch (RandomForestClassifier.predict(features)) {
      case 0:
        return 20;
      case 1:
        return 50;
      case 2:
        return 100;
      case 3:
        return 150;
      case 4:
        return 200;
      case 5:
        return 300;
      case 6:
        return 500;
      default:
        return -1;
    }

    /*costo(prezzo)
    spedizione(prezzo)
    tipologia(FruttaVerdura,Pesce,Carne,Casa,Elettronica)_Carne
    tipologia(FruttaVerdura,Pesce,Carne,Casa,Elettronica)_Casa
    tipologia(FruttaVerdura,Pesce,Carne,Casa,Elettronica)_Elettronica
    tipologia(FruttaVerdura,Pesce,Carne,Casa,Elettronica)_FruttaVerdura
    tipologia(FruttaVerdura,Pesce,Carne,Casa,Elettronica)_Pesce
    stagione_Autunno
    stagione_Estate
    stagione_Inverno
    stagione_Primavera
    zona supermercato(Periferia,Residenziale)_Periferia
    zona supermercato(Periferia,Residenziale)_Residenziale
    festività(Feriale,Lavorativo)_Feriale
    festività(Feriale,Lavorativo)_Lavorativo
    scadenza(Breve,Media,Lunga)_Breve
    scadenza(Breve,Media,Lunga)_Lunga
    scadenza(Breve,Media,Lunga)_Media
    dimensione confezione(Piccola,Media,Grande)_Grande
    dimensione confezione(Piccola,Media,Grande)_Media
    dimensione confezione(Piccola,Media,Grande)_Piccola



    0->20
    1->50
    2->100
    3->150
    4->200
    5->300
    6->500*/

  }
}
