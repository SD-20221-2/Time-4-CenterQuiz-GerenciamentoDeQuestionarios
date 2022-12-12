import xmlrpc.client

nome = input("Digite o nome: ")
altura = input("Digite a altura: ")
sexo = input("Digite o sexo: ")

with xmlrpc.client.ServerProxy("http://localhost:8080/") as proxy:
    print("Olá ", nome, "\nSeu peso ideal é ", proxy.calcular(float(altura), sexo))