class Nota:
    def verificarAprovado(n1, n2, n3):
        M = float(n1 + n2) / 2

        if (M >= 7.0):
            return True
        elif (M > 3.0 and M < 7.0):
            if ((M + n3) / 2 >= 5.0):
                return True

        return False