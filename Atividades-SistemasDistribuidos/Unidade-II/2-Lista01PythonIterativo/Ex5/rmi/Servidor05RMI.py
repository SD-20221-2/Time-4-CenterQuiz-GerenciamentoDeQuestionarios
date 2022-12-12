import Pyro4

@Pyro4.expose
class Servidor05RMI(object):

    def obterCategoria(self, idade):
        if (idade >= 5 and idade <= 7):
            return "infantil A"
        elif (idade >= 8 and idade <= 10):
            return "infantil B"
        elif (idade >= 11 and idade <= 13):
            return "juvenil A"
        elif (idade >= 14 and idade <= 17):
            return "juvenil B"
        elif (idade >= 18):
            return "adulta"
        else:
            return "sem categoria"

daemon = Pyro4.Daemon.serveSimple({Servidor05RMI: 'Servidor05RMI', }, host="localhost", port=8000, ns=False, verbose=True)
ns = Pyro4.locateNS()
server = Servidor05RMI()
uri = daemon.register(server)
ns.register('serverRMI', uri)
print('Server ...')
daemon.requestLoop()