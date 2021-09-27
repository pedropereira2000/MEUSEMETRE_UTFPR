# Etapas para comcluir a atividade 1 (Segurança)

    1. DEFINIÇÃO DE ARTEFATOS
        - [x] 1.1 Uma chave privada e uma chave pública (para cada usuário)
        - [x] 1.2 Uma Chave simétrica usada para comunicação (deve ser transmitida de forma confidencial)
        - [x] 1.3 Ter um algoritimo de hash
            - 1.3.1 SHA256
            - 1.3.2 import hashlib
            - 1.3.3 Usando o algoritmo hash da função sign
        - [] 1.4 Ter um algoritimo assimétrico
            - 1.4.1 
        - [X] 1.5 Ter um algoritimo simétrico
            - 1.5.1 AES
            - 1.5.2 biblioteca pyaes para fazer a criptografia simétrica

    2. AÇÕES DO CLIENTE
       1. [x] Envia a chave publica;
       2. [x] Envia a chave simétrica criptografada com um algoritimo assimétrico + chave publica do servidor;
       3. [x] Envia o hash da chave simétrica, criptografa o hash com um algoritimo assimétrico + chave privada do cliente;
       4. [x] Envia a mensagem criptografada com a chave simétrica;
       5. [x] Envia o hash da mensagem
    
    3. AÇÕES DO SERVIDOR
       1. [x] Envia a chave publica;
       2. [x] Servidor recebe mensagem, decriptografa com algoritimo assimétrico + chave privada do servidor, salva;
       3. [x] Faz um hash com a chave simetrica obtida;
       4. [x] Servidor recebe mensagem, decriptografa com algoritimo assimétrico + chave publica do cliente e compara com a hash feita antes (se for igual garantiu o esperado);
       5. [x] Recebe a mensagem, passa no algoritimo simétrico + chave simétrica, faz um hash com a mensagem;
       6. [x] Recebe o hash e compara com o hash feito antes da mensagem
    4. CONSIDERAÇÕES FINAIS
       1. A maneira como montei os script e as escolhas da bibliotecas levaram a uma construção de um software não muito usual, pois ele demora muito para encriptar as mensagem e sempre devem ocorrer muitas trocas de mensagens para que ele possa realziar apenas a ação de enviar a mensagem.
