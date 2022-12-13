class Aposentadoria:
    def verificar(idade, tempoServico):
        if (idade >= 65
            and tempoServico >= 30
            and idade >= 60
            and tempoServico >= 25):
            return True
        else:
            return False