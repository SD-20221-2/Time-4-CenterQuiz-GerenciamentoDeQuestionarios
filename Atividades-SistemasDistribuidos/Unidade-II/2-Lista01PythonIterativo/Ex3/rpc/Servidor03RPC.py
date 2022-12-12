from xmlrpc.server import SimpleXMLRPCServer

def verificarAprovado(n1, n2, n3):
    M = float(n1 + n2) / 2

    if (M >= 7.0):
        return True
    elif (M > 3.0 and M < 7.0):
        if ((M + n3) / 2 >= 5.0):
            return True

    return False

server = SimpleXMLRPCServer(("localhost", 8080))
print("Servidor esperando por conexoes...")
server.register_function(verificarAprovado, "verificarAprovado")
server.serve_forever()