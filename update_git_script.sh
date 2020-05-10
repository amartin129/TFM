#!/bin/bash

pbcopy < /var/root/.ssh/id_rsa.pub
git add *
git commit -m "commit"
git push
