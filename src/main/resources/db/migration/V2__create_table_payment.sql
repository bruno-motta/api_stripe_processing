CREATE TABLE payment (
    id UUID PRIMARY KEY NOT NULL,
    user_id UUID NOT NULL REFERENCES users(id),
    amount DECIMAL(19,2) NOT NULL,
    currency VARCHAR(20) NOT NULL,
    description TEXT,
    status_payment VARCHAR(20) NOT NULL,
    payment_method VARCHAR(20) NOT NULL,
    payment_method_id VARCHAR(255) NOT NULL,
    gateway_transaction_id VARCHAR(255),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    retry INTEGER NOT NULL DEFAULT 0


)