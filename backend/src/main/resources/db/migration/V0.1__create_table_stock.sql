create extension if not exists "pgcrypto";

create table TB_STOCK(
        ID bigserial primary key,
        ID_EXTERNAL_UUID uuid not null default gen_random_uuid(),
        TICKER varchar(20) not null,
        ASSET_TYPE varchar(20) not null,
        MARKET varchar(20) not null,
        QUANTITY bigserial not null,
        CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
        UPDATED_AT TIMESTAMP WITH TIME ZONE,
        constraint tb_stock_external_uuid_unique unique (ID_EXTERNAL_UUID),
        constraint tb_stock_asset_type_ck check (ASSET_TYPE in ('STOCK', 'REIT', 'ETF', 'BDR', 'FIXED_INCOME', 'FUND', 'CRYPTO', 'CURRENCY')),
        constraint tb_stock_market_ck check (MARKET in ('BR', 'US'))
);
