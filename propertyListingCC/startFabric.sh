#!/bin/bash
#
# Copyright IBM Corp All Rights Reserved
#
# SPDX-License-Identifier: Apache-2.0
#
# Exit on first error
set -e

# don't rewrite paths for Windows Git Bash users
export MSYS_NO_PATHCONV=1
starttime=$(date +%s)
CC_SRC_LANGUAGE="javascript"
CC_SRC_PATH="../../propertyListingCC/chainCode/"
CC_Name="fabproperty"
CC_init="InitLedger"


# clean out any old identites in the wallets
rm -rf javaApp/wallet/*

# launch network; create channel and join peer to channel
pushd ../fabric-samples/test-network
./network.sh down
echo COMPOSE_PROJECT_NAME=docker >> .env
./network.sh up createChannel -ca -s couchdb
./network.sh deployCC -ccn ${CC_Name} -ccv 1 -cci ${CC_init} -ccl ${CC_SRC_LANGUAGE} -ccp ${CC_SRC_PATH}
popd

cat <<EOF

Succesfully Start Fabric and Depoly Chaincode.

Total setup execution time : $(($(date +%s) - starttime)) secs ...

Try using Sample Application to interact ans Test the network.

EOF
