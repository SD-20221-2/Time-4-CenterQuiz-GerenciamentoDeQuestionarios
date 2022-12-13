class Banco:
    def credito(saldo):
        resposta = ""
        if (saldo <= 200):
            resposta = "Saldo Medio: ", saldo, "\nNenhum Credito"
        elif (saldo <= 400):
            resposta = "Saldo Medio: ",  saldo,  "\nValor do Credito: ", saldo * 0.2
        elif (saldo <= 600):
            resposta = "Saldo Medio: ", saldo, "\nValor do Credito: ", saldo * 0.3
        else:
            resposta = "Saldo Medio: ", saldo, "\nValor do Credito: ", saldo * 0.4
        
        return resposta
