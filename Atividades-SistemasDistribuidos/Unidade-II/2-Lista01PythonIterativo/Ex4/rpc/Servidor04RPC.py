from xmlrpc.server import SimpleXMLRPCServer

def calcular(altura, sexo):
    if (sexo == "M"):
        return (72.7 * altura) - 58
    else:
        return (62.1 * altura) - 44.7

server = SimpleXMLRPCServer(("localhost", 8080))
print("Servidor esperando por conexoes...")
server.register_function(calcular, "calcular")
server.serve_forever()