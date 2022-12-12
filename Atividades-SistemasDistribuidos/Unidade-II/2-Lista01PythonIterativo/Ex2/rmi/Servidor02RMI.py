import Pyro4

@Pyro4.expose
class Servidor02RMI(object):

    def verificar(self, sexo, idade):
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

        return False


daemon = Pyro4.Daemon.serveSimple({Servidor02RMI: 'Servidor02RMI', }, host="localhost", port=8000, ns=False, verbose=True)
ns = Pyro4.locateNS()
server = Servidor02RMI()
uri = daemon.register(server)
ns.register('serverRMI', uri)
print('Server ...')
daemon.requestLoop()