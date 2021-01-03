#!/usr/bin/env bash

function check_file_exists {
  if ! [ -f $1 ]; then
    echo  -e "\tFile '$1' does not exists."
    exit 1
  fi
}

echo -e "\tChecking torrent file: '$1'"
check_file_exists $1

echo -e "\tKilling TMUX session."
tmux kill-session -t torrent

echo -e "\tStarting new TMUX session."
tmux new-session -d -s "torrent" rtorrent $1

echo -e "\tRemoving file: '$1'."
rm $1

echo -e "\tDone."
