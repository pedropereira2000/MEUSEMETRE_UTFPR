# -*- coding: utf-8 -*- 

import socket
import hashlib
import Crypto
import base64
import rsa
from simplecrypt import encrypt, decrypt
from Crypto.PublicKey import RSA
from Crypto.Util.randpool import RandomPool
from Crypto import Random
from Crypto.Cipher import PKCS1_OAEP 
from binascii import hexlify
from Crypto import Random
from base64 import b64encode, b64decode
from Crypto.Hash import SHA256
from Crypto.Signature import PKCS1_v1_5

# Gerando Chaves Assimetricas
private_key = RSA.generate(1024)

public_key = private_key.publickey() 
# Convertendo em String
private_pem = private_key.export_key().decode() 

public_pem = public_key.export_key().decode() 
# Salvando as Chaves
with open('Cli_private_pem.pem', 'w') as pr: 
    pr.write(private_pem) 
with open('Cli_public_pem.pem', 'w') as pu : 
    pu.write(public_pem) 

pr_key = RSA.import_key(open('Cli_private_pem.pem', 'r').read())
pu_key = RSA.import_key(open('Cli_public_pem.pem', 'r').read())

#cipher = PKCS1_OAEP.new(key=pu_key)

#cipher_text = cipher.encrypt(text)

#decrypt = PKCS1_OAEP.new(key=pr_key)

#decrypted_message = decrypt.decrypt(cipher_text)

# Assinando
def sign(message, priv_key, hashAlg="SHA-256"):
    global hash
    hash = hashAlg
    signer = PKCS1_v1_5.new(priv_key)
    if (hash == "SHA-512"):
        digest = SHA512.new()
    elif (hash == "SHA-384"):
        digest = SHA384.new()
    elif (hash == "SHA-256"):
        digest = SHA256.new()
    elif (hash == "SHA-1"):
        digest = SHA.new()
    else:
        digest = MD5.new()
    digest.update(message)
    return signer.sign(digest)

    
# Verificando assinatura
def verify(message, signature, pub_key):
    signer = PKCS1_v1_5.new(pub_key)
    if (hash == "SHA-512"):
        digest = SHA512.new()
    elif (hash == "SHA-384"):
        digest = SHA384.new()
    elif (hash == "SHA-256"):
        digest = SHA256.new()
    elif (hash == "SHA-1"):
        digest = SHA.new()
    else:
        digest = MD5.new()
    digest.update(message)
    return signer.verify(digest, signature)

kSim = '1a2b3c4d5e6f7g8h'

def client(host = 'localhost', port=5535): 
    # Create a TCP/IP socket 
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
    # Connect the socket to the server 
    server_address = (host, port) 
    print ("Connecting to %s port %s" % server_address) 
    name = raw_input("Enter the User Name to be Used\n>>")
    sock.connect(server_address) 
    
    # Enviando caminho da chave publica
    sock.sendall(public_pem)
    # Recebendo a chave publica
    data = sock.recv(2048) 
    # Lendo chave publica
    Serv_pu_key = RSA.import_key(data)

    while 1:
        try: 
            # Criptografando a key simetrica com key pub do servidor
            cipher_serv = PKCS1_OAEP.new(key=Serv_pu_key)
            cipher_key = cipher_serv.encrypt(kSim)
            sock.sendall(cipher_key)
            # Fazendo o hash e Criptografando com key priv do client
            digitalSign = sign(kSim, pr_key,'SHA-256')
            sock.sendall(digitalSign)
            #Recebendo Key
            data = sock.recv(2048) 
            decrypt1 = PKCS1_OAEP.new(key=pr_key)
            decrypted_Key = decrypt1.decrypt(data)
            #Recebendo o hash da Key
            data = sock.recv(2048)
            #Pegando o hash e verificando se é igual ao hash da chave enviada
            if verify(decrypted_Key, data, Serv_pu_key):
                # Send data 
                message = raw_input("Enter the massage \n>>")
                # Se for igual, verifica se a mensagem digita é exit pois se for 
                # encerra a conexão
                if message == "exit" or message == "Exit":
                    print "Closing Connection..."
                    msg = encrypt(decrypted_Key,message)
                    sock.sendall(msg) 
                    digitalSign = sign(message, pr_key,'SHA-256')
                    sock.sendall(digitalSign)
                else:
                    # Se não for, pega a mensagem e criptografa para enviar com o hash
                    message = name + ": " + message
                    msg = encrypt(decrypted_Key,message)
                    sock.sendall(msg)
                    digitalSign = sign(message, pr_key,'SHA-256')
                    sock.sendall(digitalSign)

                # Recebendo a mensagem
                data = sock.recv(2048)
                msgDecrypt = decrypt(kSim, data)
                data = sock.recv(2048)
                if verify(msgDecrypt, data, Serv_pu_key):
                    if msgDecrypt == "exit" or msgDecrypt == "Exit":
                        print "Closing Connection..."
                        message = "Exit"
                        msgCrypt = encrypt(decrypted_Key, message)
                        client.sendall(msgCrypt)
                        client.close()
                        break
                    else:
                        # Se a mensagem não for exit recebe o hash da mensagem assinado
                        print msgDecrypt
            # Se não for igual cancela a conexão
            else:
                print "Não foi possível estabelecer uma cominicação segura"
            data = sock.recv(2048) 
            while data[i] != "#":
                i = i + 1 
            msg = data[0:i]
            hashed = data[i+1:]
            msgDecrypt = decrypt(kSim, msg)
            if (hashlib.sha256(msgDecrypt).hexdigest() == hashed):
                print (msgDecrypt)
            if msg == "exit" or msg == "Exit":
                print ("Closing connection to the server\n>>") 
                sock.close() 
                break
        except socket.error as e: 
            print ("Socket error: %s" %str(e))
         
client()