# Payment Processing - Stripe

*Link do notion:* https://app.notion.com/p/Payment-Processing-3542e9416e6b80f3a23bc93a3979ddc1

Projeto criado exclusivamente para estudo, com foco em arquitetura Hexagonal e conceitos como mensageria, idempotência, 
transações e cloud(AWS).

## Stacks

- Java 21 + Spring Boot 3
- Spring security 6 + JWT 
- Spring Data JPA + Flyway + PostgreSQL
- Apache Kafka - Event Publishing
- Stripe API - payment gateway (sandbox)
- Docker compose 
- AWS - EC2
- CI/CD - GitHub Actions

---

## Arquitetura 

Arquitetura Hexagonal (Ports and Adapters) — o domínio não possui nenhuma dependência de frameworks ou infraestrutura.

De maneira geral, isolamos a regra de negócio central da aplicação de elementos externos como, bancos de dados, interfaces de usuário ou APIs e utilizaremos
portas (interfaces) e adpadores(implementações) para permitir a troca fácil de tecnológias sem alterar o dóminio.

- Domínio: Concentra a lógica de negócios pura da aplicação, sem conhecer detalhes de infraestrutura ou frameworks.
- Portas de Entrada(Ports/in): Interfaces acionadas por agentes externos, como requisições HTTP de uma API REST ou comandos de terminal.
- Adaptadores de Entrada(Adapters/in):  Implementam as portas de entrada convertendo chamadas externas para o formato que o domínio entende.
- Portas de Saída(Ports/out):  Interfaces que o domínio usa para falar com o exterior, como salvar dados em um repositório. 
- Adaptadores de Saída(Adapters/out): Implementam as portas de saída conversando diretamente com bancos de dados, filas de mensagens ou serviços de terceiros.

---

## Máquina de estado

```
PENDING → PROCESSING → APPROVED → REFUNDED
                     ↘ FAILED (retry up to 3x)
```
ps: após a falha da 3° tentativa o pagamento volta para PENDING.

___

## Como rodar localmente

**Pré-Requisitos:**  Docker, Java 21, Maven.

```
# 1. Clone o repositório
git clone https://https://github.com/bruno-motta/
cd payment-processing-api

# 2. Configure as cheves tanto do stripe, kafka e variavéis de ambiente
stripe.secret-key=sk_test_...
stripe.webhook-secret=whsec_...

# 3. Rode o conteiner
docker compose up -d

# 4. Rode a aplicação
.mvn spring-boot:run
```