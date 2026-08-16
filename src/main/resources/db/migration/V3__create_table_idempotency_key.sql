CREATE TABLE idempotency_key(
    id UUID PRIMARY KEY NOT NULL,
    key UUID NOT NULL UNIQUE,
    payment_id UUID NOT NULL REFERENCES payment(id),
    response_body VARCHAR(255) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
)