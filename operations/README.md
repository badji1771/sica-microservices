Projet micro-service operation


# OPERATIONS

Le projet Opéartions  est un micro-service du projet SICA qui traite les opérations issues des participants

## Diagramme de classe de l'application

![Logo du Projet](Operations.png)


## Les service
- TypeCompensationControlleur
  <br> &nbsp;&nbsp;findTypeCompensationByCode()
  <br> &nbsp;&nbsp; findAll()
  <br> &nbsp;&nbsp; createTypeCompensation()


- JourneeCompensationController
  <br> &nbsp;&nbsp;create()
  <br> &nbsp;&nbsp;update()
  <br> &nbsp;&nbsp; delete()
  <br> &nbsp;&nbsp;ouvrirJournee()
  <br> &nbsp;&nbsp;fermerJournee() 

- TypeRejetController
  <br> &nbsp;&nbsp;create()
  <br> &nbsp;&nbsp;update()
  <br> &nbsp;&nbsp; delete()
  <br> &nbsp;&nbsp;getTypeRejet()


- TypeOperationControlleur
  <br> &nbsp;&nbsp;createTypeOperation()
  <br> &nbsp;&nbsp; findAll()
  <br> &nbsp;&nbsp; findTypeOperationByCode()


- OperationRejetController
  <br> &nbsp;&nbsp;create()
  <br> &nbsp;&nbsp;update()
  <br> &nbsp;&nbsp; delete()
  <br> &nbsp;&nbsp;getOperationRejet()


- OperationControlleur
 <br> &nbsp;&nbsp;getAllOperation()
 <br> &nbsp;&nbsp;findById(Long id)
 <br> &nbsp;&nbsp;rejetOperation()
 <br> &nbsp;&nbsp;getOperationsParJourneeCompense


## Installation


