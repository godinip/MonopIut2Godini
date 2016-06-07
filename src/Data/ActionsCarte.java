package Data;

public enum ActionsCarte {
    AP,//aller en prison
    SP,//carte sortie de prison
    RE,//reculer de X cases
    GP,//gagner X si X>0, payer -X si X<0
    MH,//payer X par maison et Y par hotel
    AN,//chaque joueur vous donne X€
    AV,//avancer jusqu'à la case numéro X
    DE;//reculer jusqu'à la case numéro X
}