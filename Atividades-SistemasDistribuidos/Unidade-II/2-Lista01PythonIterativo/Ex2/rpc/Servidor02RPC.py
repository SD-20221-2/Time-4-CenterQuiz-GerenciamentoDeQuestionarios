from xmlrpc.server import SimpleXMLRPCServer

def verificar(sexo, idade):
    idade = int(idade)
    if (sexo == "M"):
        if (idade >= 18):
            return True
        else:
            return False
    elif (sexo == "F"):
        if (idade >= 21):
            return True
        else:
            return False

server = SimpleXMLRPCServer(("localhost", 8080))
print("Servidor esperando por conexoes...")
server.register_function(verificar, "verificar")
server.serve_forever()