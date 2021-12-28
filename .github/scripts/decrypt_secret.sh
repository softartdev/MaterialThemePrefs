#!/bin/sh

# Decrypt the file
# --batch to prevent interactive command --yes to assume "yes" for questions

gpg --quiet --batch --yes --decrypt --passphrase="$LARGE_SECRET_PASSPHRASE" \
--output ./8257B447.gpg ./.github/secrets/8257B447.gpg.gpg

gpg --quiet --batch --yes --decrypt --passphrase="$LARGE_SECRET_PASSPHRASE" \
--output ./local.properties ./.github/secrets/local.properties.gpg
