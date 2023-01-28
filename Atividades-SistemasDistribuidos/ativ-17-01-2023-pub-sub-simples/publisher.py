import time
import zmq

def main():

    context = zmq.Context()
    publisher = context.socket(zmq.PUB)
    publisher.bind("tcp://*:5555")

    while True:
        #Exercicio 1 :
        publisher.send_multipart([b"funcionario", b'{ "nome":"Sergio", "cargo":"operador", "salario":100}'])
        publisher.send_multipart([b"funcionario", b'{ "nome":"Daniel", "cargo":"programador", "salario":1000}'])
        publisher.send_multipart([b"funcionario", b'{ "nome":"Isabela", "cargo":"programador", "salario":1100}'])
        #Exercicio 2 :

        # publisher.send_multipart([b"maioridade", b'{ "nome":"Sergio", "sexo":"masculino", "idade":50}'])
        publisher.send_multipart([b"maioridade", b'{ "nome":"Daniel", "sexo":"masculino", "idade":25}'])
        publisher.send_multipart([b"maioridade", b'{ "nome":"Isabela", "sexo":"feminino", "idade":23}'])
        publisher.send_multipart([b"maioridade", b'{ "nome":"Maria", "sexo":"feminino", "idade":14}'])

        #Exercicio 3 :
        publisher.send_multipart([b"aluno", b'{ "n1":5, "n2":7, "n3":8}'])
        publisher.send_multipart([b"aluno", b'{ "n1":1, "n2":2, "n3":3}'])
        #Exercicio 4 :
        publisher.send_multipart([b"peso", b'{ "altura":1.71, "sexo":"masculino"}'])
        publisher.send_multipart([b"peso", b'{ "altura":1.45, "sexo":"feminino"}'])
        publisher.send_multipart([b"peso", b'{ "altura":1.63, "sexo":"masculino"}'])
        #Exercicio 5 :
        publisher.send_multipart([b"esporte", b'{ "idade":5 }'])
        publisher.send_multipart([b"esporte", b'{ "idade":8 }'])
        publisher.send_multipart([b"esporte", b'{ "idade":11 }'])
        publisher.send_multipart([b"esporte", b'{ "idade":14 }'])
        publisher.send_multipart([b"esporte", b'{ "idade":19 }'])
        #Exercicio 7 :
        publisher.send_multipart([b"aposentadoria", b'{ "idade":56, "tempo":40}'])
        publisher.send_multipart([b"aposentadoria", b'{ "idade":24, "tempo":14}'])
        #Exercicio 8:
        publisher.send_multipart([b"credito", b'{ "saldo":345}'])
        publisher.send_multipart([b"credito", b'{ "saldo":240}'])
        time.sleep(1)

    publisher.close()
    context.term()

if __name__ == "__main__":
    main()