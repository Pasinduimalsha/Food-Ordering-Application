#!/bin/sh
set -e

host="$1"
shift
cmd="$@"

echo "⏳ Waiting for MySQL at $host..."

until mysqladmin ping -h "$host" --silent; do
  sleep 2
done

echo "✅ MySQL is up - executing command"
exec $cmd
