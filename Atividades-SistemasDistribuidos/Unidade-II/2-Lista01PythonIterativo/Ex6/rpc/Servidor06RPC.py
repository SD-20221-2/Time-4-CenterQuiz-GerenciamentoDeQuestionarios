from xmlrpc.server import SimpleXMLRPCServer

def reajustar(nivel, salarioBruto, numeroDependentes):
    if (nivel == "A"):
        if (numeroDependentes == 0):
            return salarioBruto * (1 - 0.03)
        else:
            return salarioBruto * (1 - 0.08)
    elif (nivel == "B"):
        if (numeroDependentes == 0):
            return salarioBruto * (1 - 0.05)
        else:
            return salarioBruto * (1 - 0.1)
    elif (nivel == "C"):
        if (numeroDependentes == 0):
            return salarioBruto * (1 - 0.08)
        else:
            return salarioBruto * (1 - 0.15)
    elif (nivel == "D"):
        if (numeroDependentes == 0):
            return salarioBruto * (1 - 0.1)
        else:
            return salarioBruto * (1 - 0.17)
    else:
        return salarioBruto

server = SimpleXMLRPCServer(("localhost", 8080))
print("Servidor esperando por conexoes...")
server.register_function(reajustar, "reajustar")
server.serve_forever()