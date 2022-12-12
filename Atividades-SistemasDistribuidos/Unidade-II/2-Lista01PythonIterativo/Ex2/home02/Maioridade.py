class Maioridade:
    def verificar(sexo, idade):
        if (sexo == "M"):
            return idade >= 18
        elif (sexo == "F"):
            return idade >= 21

        return False