TP à préparer :
---- 

Nous poursuivons le même projet de calcul de remise.

L’objectif est de proposer une API REST permettant de créer une transaction de calcul de remise.

En entrée, l’API reçoit le montant de la transaction.

Elle crée ensuite une transaction de calcul en base de données.

La récupération d’une transaction par son identifiant permet d’obtenir les différentes informations associées :

le montant initial

le montant après remise

la date d’initialisation de la transaction