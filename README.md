# Salesman

Este projeto foi criado para processar um arquivo de entrada, onde os dados são separados por um caractere delimitador, possuindo três tipos de dados, os vendedores, os clientes e as vendas. A partir destes dados de entrada tem-se como objetivo gerar um relatório com os seguintes dados: 

- Quantidade de clientes
- Quantidade de vendedores
- Pior vendedor 
- Venda mais cara

Destaque do que foi utilizado para o desenvolvimento deste projeto:
- Java 8
- Spring Boot
- Gradle
- JUnit

# Como utilizar

- git clone https://github.com/guilhermearmange/salesman.git
- cd Salesman
- ./gradlew build bootRun

# Configurações possiveis
- separator: ç
    - Utilizado para definir o separador do arquivo
    
- file.output.regex: (.*)(\.dat)
    - Utilizado para definir a regex de replace para gerar o nome do arquivo de output

- file.output.replace: $1.done$2
    - Utilizado para definir o replacement para gerar o nome do arquivo de output

- file.output.folder: /data/out
    - Utilizado para definir a pasta que será gravado os arquivos de saida. Caminho relativo da user home do usuário.

- file.input.folder: /data/in
    - Utilizado para definir a pasta da qual será lido os arquivos. Caminho relativo da user home do usuário. 

- file.input.matcher: "glob:*.dat"
    - Pattern para validar os tipos de arquivos a serem processados 
    
- file.input.max-wait-time: -1
    - Tempo maximo de espera para escrita do proximo arquivo. 
        - Negativo para sem timeout ou um número positivo para configurar o timeout máximo em milissegundos