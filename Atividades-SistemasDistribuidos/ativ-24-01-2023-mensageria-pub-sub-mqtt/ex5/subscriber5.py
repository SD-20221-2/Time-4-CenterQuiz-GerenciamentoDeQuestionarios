import random
import json

from paho.mqtt import client as mqtt_client

broker = 'broker.emqx.io'
port = 1883
topic = "Tópico do exercício 5"
# gerar ID do cliente com prefixo de pub aleatoriamente
client_id = f'python-mqtt-{random.randint(0, 100)}'
username = 'mqttbrokertest'
password = 'mqttbrokertest'

def connect_mqtt() -> mqtt_client:
    def on_connect(client, userdata, flags, rc):
        if rc == 0:
            print("Subscriber conectado ao MQTT Broker! Tópico: ", topic)
        else:
            print("Falha de conexão, retorno %d\n", rc)

    client = mqtt_client.Client(client_id)
    client.username_pw_set(username, password)
    client.on_connect = on_connect
    client.connect(broker, port)
    return client

def subscribe(client: mqtt_client):
    def on_message(client, userdata, msg):
            m_decode = str(msg.payload.decode("utf-8", "ignore"))
            if is_json(m_decode): # garantindo que a mensagem é uma string de json
                m_in = json.loads(m_decode)  # decode json data

                idade = m_in["idade"]

                if (idade >= 5 and idade <= 7):
                    print("Categoria infantil A")
                elif (idade >= 8 and idade <= 10):
                    print("Categoria infantil B")
                elif (idade >= 11 and idade <= 13):
                    print("Categoria juvenil A")
                elif (idade >= 14 and idade <= 17):
                    print("Categoria juvenil B")
                elif (idade > 18):
                    print("Categoria adulto")

    client.subscribe(topic)
    client.on_message = on_message

def is_json(myjson):
  try:
    json.loads(myjson)
  except ValueError as e:
    return False
  return True


def run():
    client = connect_mqtt()
    subscribe(client)
    client.loop_forever()


if __name__ == '__main__':
    run()