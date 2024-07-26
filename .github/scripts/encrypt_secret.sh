#!/bin/sh

# Encrypt the file

gpg --quiet --batch --yes --symmetric --cipher-algo AES256 --passphrase="$LARGE_SECRET_PASSPHRASE" \
--output ./.github/secrets/8257B447.gpg.gpg ./8257B447.gpg

gpg --quiet --batch --yes --symmetric --cipher-algo AES256 --passphrase="$LARGE_SECRET_PASSPHRASE" \
--output ./.github/secrets/local.properties.gpg ./local.properties
