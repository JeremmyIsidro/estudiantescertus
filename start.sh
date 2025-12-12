#!/bin/bash
echo "Esperando a MySQL en mysql:3306..."
timeout=60
while ! nc -z mysql 3306; do
  timeout=$((timeout - 1))
  if [ $timeout -le 0 ]; then
    echo "ERROR: MySQL no respondio a tiempo"
    exit 1
  fi
  echo "Intento $((60 - timeout))/60 - MySQL no esta listo..."
  sleep 2
done

echo "MySQL esta listo!"
echo "Esperando 5 segundos adicionales para asegurar..."
sleep 5

echo "Iniciando aplicacion Spring Boot..."
exec java -jar app.war