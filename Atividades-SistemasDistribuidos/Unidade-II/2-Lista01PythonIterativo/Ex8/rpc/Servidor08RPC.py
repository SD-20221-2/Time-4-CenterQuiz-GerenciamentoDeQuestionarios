from xmlrpc.server import SimpleXMLRPCServer

def credito(saldo):
    resposta = ""
    if (saldo <= 200):
        resposta = "Saldo Medio: ", saldo, "\nNenhum Credito"
    elif (saldo <= 400):
        resposta = "Saldo Medio: ", saldo, "\nValor do Credito: ", saldo * 0.2
    elif (saldo <= 600):
        resposta = "Saldo Medio: ", saldo, "\nValor do Credito: ", saldo * 0.3
    else:
        resposta = "Saldo Medio: ", saldo, "\nValor do Credito: ", saldo * 0.4

    return resposta

server = SimpleXMLRPCServer(("localhost", 8080))
print("Servidor esperando por conexoes...")
server.register_function(credito, "credito")
server.serve_forever()