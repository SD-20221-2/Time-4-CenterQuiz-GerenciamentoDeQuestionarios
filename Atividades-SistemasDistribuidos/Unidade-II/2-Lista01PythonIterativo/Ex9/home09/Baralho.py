from Ex9.home09.NaipeCarta import NaipeCarta
from Ex9.home09.ValorCarta import ValorCarta

class Baralho:
    def nomeCarta(valor, naipe):
        return Baralho.obterValorCarta(valor) , " de " , Baralho.obterNaipeCarta(naipe)

    def obterNaipeCarta(naipe):
        strNaipeCarta = ""

        if (naipe == 1):
            strNaipeCarta = strNaipeCarta, NaipeCarta.OUROS.value
        elif (naipe == 2):
            strNaipeCarta = strNaipeCarta, NaipeCarta.PAUS.value
        elif (naipe == 3):
            strNaipeCarta = strNaipeCarta, NaipeCarta.COPAS.value
        elif (naipe == 4):
            strNaipeCarta = strNaipeCarta, NaipeCarta.ESPADAS.value

        return strNaipeCarta

    def obterValorCarta(valor):
        strValorCarta = ""

        if(valor == 1):
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

        return strValorCarta
