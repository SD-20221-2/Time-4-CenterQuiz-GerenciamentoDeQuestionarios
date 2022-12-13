from xmlrpc.server import SimpleXMLRPCServer

from Ex9.home09.NaipeCarta import NaipeCarta
from Ex9.home09.ValorCarta import ValorCarta


def nomeCarta(valor, naipe):
    strNaipeCarta = ""

    if (naipe == 1):
        strNaipeCarta = strNaipeCarta, NaipeCarta.OUROS.value
    elif (naipe == 2):
        strNaipeCarta = strNaipeCarta, NaipeCarta.PAUS.value
    elif (naipe == 3):
        strNaipeCarta = strNaipeCarta, NaipeCarta.COPAS.value
    elif (naipe == 4):
        strNaipeCarta = strNaipeCarta, NaipeCarta.ESPADAS.value

    strValorCarta = ""

    if (valor == 1):
        strValorCarta = strValorCarta, ValorCarta.AS.value
    elif (valor == 2):
        strValorCarta = strValorCarta, ValorCarta.DOIS.value
    elif (valor == 3):
        strValorCarta = strValorCarta, ValorCarta.TRES.value
    elif (valor == 4):
        strValorCarta = strValorCarta, ValorCarta.QUATRO.value
    elif (valor == 5):
        strValorCarta = strValorCarta, ValorCarta.CINCO.value
    elif (valor == 6):
        strValorCarta = strValorCarta, ValorCarta.SEIS.value
    elif (valor == 7):
        strValorCarta = strValorCarta, ValorCarta.SETE.value
    elif (valor == 8):
        strValorCarta = strValorCarta, ValorCarta.OITO.value
    elif (valor == 9):
        strValorCarta = strValorCarta, ValorCarta.NOVE.value
    elif (valor == 10):
        strValorCarta = strValorCarta, ValorCarta.DEZ.value
    elif (valor == 11):
        strValorCarta = strValorCarta, ValorCarta.VALETE.value
    elif (valor == 12):
        strValorCarta = strValorCarta, ValorCarta.DAMA.value
    elif (valor == 13):
        strValorCarta = strValorCarta, ValorCarta.REI.value

    return strValorCarta, " de ", strNaipeCarta

server = SimpleXMLRPCServer(("localhost", 8080))
print("Servidor esperando por conexoes...")
server.register_function(nomeCarta, "nomeCarta")
server.serve_forever()