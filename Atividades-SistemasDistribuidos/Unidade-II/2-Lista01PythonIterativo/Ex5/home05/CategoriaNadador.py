class CategoriaNadador:
    def obterCategoria(idade):
        if (idade >= 5 and idade <= 7):
            return "infantil A"
        elif (idade >= 8 and idade <= 10):
            return "infantil B"
        elif (idade >= 11 and idade <= 13):
            return "juvenil A"
        elif (idade >= 14 and idade <= 17):
            return "juvenil B"
        elif (idade >= 18):
            return "adulta"
        else:
            return "sem categoria"