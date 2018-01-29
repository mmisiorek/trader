#!/bin/bash

cd resources
rm -rf solidity
git clone https://github.com/mmisiorek/trader-solidity.git
mv trader-solidity solidity

cd solidity
npm install
truffle compile
cd ../..

for FILENAME in "PublicTrade" "SubaccountsOnlyTrade" "TradeBuyerStorage"
do
	web3j truffle generate resources/solidity/build/contracts/$FILENAME.json -o app/ -p contracts
done
