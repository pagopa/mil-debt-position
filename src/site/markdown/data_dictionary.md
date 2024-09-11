# Data Dictionary

| Etichetta                        | Descrizione                                                                                                                         |
| -------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------- |
| `<access token>`                 | Access token adoperato da mil-debt-position per richiedere a mil-auth l'estrazione del codice fiscale dall'access token del client. |
| `<access token duration>`        | Durata dell'access token adoperato da mil-debt-position.                                                                            |
| `<acquirer id>`                  | Acquirer ID del client.                                                                                                             |
| `<amount #n.m.p>`                | Importo dell'avviso di pagamento in centesimi di Euro.                                                                              |
| `<api key>`                      | API key per il servizio GPD Payments Pull.                                                                                          |
| `<channel>`                      | Canale del client.                                                                                                                  |
| `<client access token>`          | Access token inviato dal client.                                                                                                    |
| `<client id>`                    | Client ID di mil-debt-position.                                                                                                     |
| `<client request id>`            | ID della richiesta inviata dal client.                                                                                              |
| `<client secret>`                | Client secret di mil-debt-position.                                                                                                 |
| `<description #n.m.p>`           | Descrizione dell'avviso di pagamento.                                                                                               |
| `<due date #n.m.p>`              | Timestamp entro cui l’avviso di pagamento è pagabile.                                                                               |
| `<fiscal code>`                  | Codice fiscale in chiaro del Cittadino.                                                                                             |
| `<merchant id>`                  | Merchant ID del client, solo se il canale è `POS`.                                                                                  |
| `<notice number #n.m.p>`         | Numero dell'avviso di pagamento.                                                                                                    |
| `<pa tax code #n.m.p>`           | Codice fiscale dell'Ente Creditore.                                                                                                 |
| `<request id for access token>`  | ID della richiesta inviata da mil-debt-position a mil-auth per ottenere un access token.                                            |
| `<request id for introspection>` | ID della richiesta inviata da mil-debt-position a mil-auth per l'estrazione del codice fiscale dall'access token del client.        |
| `<terminal id>`                  | ID terminale deli client.                                                                                                           |


## Mapping gpd-payments-pull/mil-debt-position

| gpd-payments-pull                                    | Descrizione                                                                                                              | mil-debt-position           |                                                                                                   |
| ---------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------ | --------------------------- |
| `iupd #n`                                            | Identificativo univoco della posizione debitoria.                                                                        |                             |
| `debtorTaxCode #n`                                   | Codice fiscale del soggetto cui è intestata la posizione debitoria.                                                      |                             |
| `debtorFullName #n`                                  | Nome e cognome del soggetto cui è intestata la posizione debitoria.                                                      |                             |
| `debtorType #n`                                      | Tipologia del soggetto cui è intestata la posizione debitoria (fisico o giuridico).                                      |                             |
| `paTaxCode #n`                                       | Codice fiscale dell’Ente Creditore.                                                                                      |                             |
| `paFullName #n`                                      | Ragione sociale dell’Ente Creditore.                                                                                     |                             |
| `insertedDate #n`                                    | Timestamp di inserimento della posizione debitoria.                                                                      |                             |
| `publishDate #n`                                     | Timestamp di pubblicazione della posizione debitoria.                                                                    |                             |
| `validityDate #n`                                    | Timestamp di inizio validità della posizione debitoria. Se null diventa valida quando si fa la publish.                  |                             |
| `status #n`                                          | Stato della posizione debitoria: `VALID` o `PARTIALLY_PAID`.                                                             |                             |
| `lastUpdatedDate #n`                                 | Data di aggiornamento della posizione debitoria.                                                                         |                             |
| `paymentOptions #n`                                  | Struttura contenente le opzioni di pagamento (almeno 1).                                                                 |                             |
| `paymentOptions/description #n.m`                    | Descrizione dell'opzione di pagamento. Potrebbe essere assente.                                                          |                             |
| `paymentOptions/numberOfInstallments #n.m`           | Numero di rate (almeno 1) che compongono l'opzione di pagamento.                                                         |                             |
| `paymentOptions/amount #n.m`                         | Importo dell'opzione di pagamento. Potrebbe essere assente.                                                              |                             |
| `paymentOptions/dueDate #n.m`                        | Timestamp entro cui l’opzione di pagamento è pagabile. Potrebbe essere assente.                                          |                             |
| `paymentOptions/isPartialPayment #n.m`               | Indica se l’opzione di pagamento fa parte di un piano rateale. Potrebbe essere assente.                                  |                             |
| `paymentOptions/switchToExpired #n.m`                | Se `true` e la posizione debitoria è creata su GPD, superata la data di scadenza (`dueDate`) viene marcata come scaduta. |                             |
| `paymentOptions/installments #n.m`                   | Struttura contenente le rate (almeno 1) che compongono l'opzione di pagamento.                                           |                             |
| `paymentOptions/installments/nav #n.m.p`             | Codice avviso.                                                                                                           | `debtPosition/noticeNumber` |
| `paymentOptions/installments/iuv #n.m.p`             | Identificativo univoco.                                                                                                  |                             |
| `paymentOptions/installments/paTaxCode #n.m.p`       | Codice fiscale dell’Ente Creditore.                                                                                      | `debtPosition/paTaxCode`    |
| `paymentOptions/installments/paFullName #n.m.p`      | Ragione sociale dell’Ente Creditore.                                                                                     |                             |
| `paymentOptions/installments/amount #n.m.p`          | Importo dell’opzione di pagamento.                                                                                       | `debtPosition/amount`       |
| `paymentOptions/installments/description #n.m.p`     | Descrizione dell’opzione di pagamento.                                                                                   | `debtPosition/description`  |
| `paymentOptions/installments/dueDate #n.m.p`         | E' il timestamp entro cui l’opzione di pagamento è pagabile.                                                             | `debtPosition/dueDate`      |
| `paymentOptions/installments/retentionDate #n.m.p`   | Per usi futuri.                                                                                                          |                             |
| `paymentOptions/installments/insertedDate #n.m.p`    | Timestamp di inserimento dell'opzione di pagamento.                                                                      |                             |
| `paymentOptions/installments/notificationFee #n.m.p` | Spese di notifica di SEND. Potrebbe essere assente.                                                                      |                             |
| `paymentOptions/installments/status #n.m.p`          | Stato dell’opzione di pagamento: PO_UNPAID, PO_PAID, PO_PARTIALLY_REPORTED, PO_REPORTED.                                 |                             |
| `paymentOptions/installments/lastUpdatedDate #n.m.p` | Timestamp di aggiornamento dell’opzione di pagamento.                                                                    |                             |
