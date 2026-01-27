#!/bin/bash
# wait-for-it.sh: wait for a TCP service to be ready

host=$1
port=$2
shift 2
cmd="$@"

wait_for() {
    echo "Waiting for $host:$port..."
    while ! nc -z "$host" "$port" 2>/dev/null; do
        echo "."
        sleep 1
    done
    echo "$host:$port is available!"
}

wait_for "$host" "$port"
exec $cmd
