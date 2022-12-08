class Salario:
    def reajustar(cargo, salario):
        if cargo.lower() == "operador":
            return salario * 1.2
        elif (cargo.lower() == "programador") :
            return salario * 1.18
        else:
            return salario